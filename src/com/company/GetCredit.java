package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.Statement;
import java.sql.*;

public class GetCredit extends Container {
    private JLabel background;
    private ImageIcon backImage;
    private JButton back;
    private JLabel chooseamountLabel;
    private JTextField chooseamountTextField;
    private JSlider forex;
    private JLabel choosemonthLabel;
    private JTextField monthLabel;
    private JTextField percents;
    private JButton issue;
    private JLabel payforMonthLabel;
    private JTextField payforMonthTextField;
    private JLabel finalAmountLabel;
    private JTextField finalAmountTextField;
    private int finalsumm = 0;
    private JButton acceptButton;
    private int checking;
    private String check = "select hascredit from atmusers where numberofcard = ?;";
    private final String UpdateInfo = "update atmusers set hascredit = (?),monthofcredit = (?) where numberofcard = (?)";
    private static String gettingCredit = "update atmusers set moneyamount = (moneyamount + ?) where numberofcard = (?);";
    private static String changeInfo  = "update atmusers set hascredit = (?)";
    private static String GetQuery = "update atmusers set moneyamount = ( moneyamount + ?) , hascredit = (?), monthofcredit = (?) where numberofcard = (?);";
    public GetCredit() {
        setSize(750, 550);
        setLayout(null);
        backImage = new ImageIcon("images/getcredit.png");
        background = new JLabel();
        background.setBounds(0, -10, 750, 550);
        background.setIcon(backImage);


        chooseamountLabel = new JLabel("Введите сумму");
        chooseamountLabel.setBounds(70, 120, 200, 30);
        chooseamountLabel.setFont(new Font("Tahoma", 1, 23));
        add(chooseamountLabel);

        chooseamountTextField = new JTextField();
        chooseamountTextField.setBounds(270, 120, 200, 30);
        chooseamountTextField.setFont(new Font("Tahoma", 1, 23));
        add(chooseamountTextField);

        choosemonthLabel = new JLabel("Срок кредита(месяц)");

        choosemonthLabel.setBounds(70, 170, 300, 30);
        choosemonthLabel.setFont(new Font("Tahoma", 1, 23));
        add(choosemonthLabel);

        monthLabel = new JTextField();
        monthLabel.setBounds(380, 210, 50, 50);
        monthLabel.setText("3");
        monthLabel.setFont(new Font("Tahoma", 1, 18));
        add(monthLabel);

        percents = new JTextField();
        percents.setBounds(450, 210, 50, 50);
        percents.setFont(new Font("Tahoma", 1, 18));

        add(percents);
        forex = new JSlider(1, 24, 3);
        forex.setBounds(70, 210, 300, 50);
        forex.setPaintTicks(true);
        forex.setMinorTickSpacing(3);
        forex.setPaintTrack(true);
        forex.setMajorTickSpacing(3);
        forex.setPaintLabels(true);
        forex.setFont(new Font("Tahoma", 1, 15));
        forex.setForeground(Color.black);
        forex.setBackground(Color.orange);
        forex.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                monthLabel.setText(String.valueOf(forex.getValue()));
                percents.setText(String.valueOf(forex.getValue() / 2 + 3) + "%");
            }
        });
        add(forex);

        payforMonthLabel = new JLabel("Оплата в месяц");
        payforMonthLabel.setBounds(70, 310, 200, 30);
        payforMonthLabel.setFont(new Font("Tahoma", 1, 18));
        add(payforMonthLabel);
        payforMonthTextField = new JTextField();
        payforMonthTextField.setBounds(70, 350, 150, 30);
        payforMonthTextField.setFont(new Font("Tahoma", 1, 18));
        add(payforMonthTextField);
        finalAmountLabel = new JLabel("Финальная сумма");
        finalAmountLabel.setBounds(280, 310, 200, 30);
        finalAmountLabel.setFont(new Font("Tahoma", 1, 18));
        add(finalAmountLabel);
        finalAmountTextField = new JTextField();
        finalAmountTextField.setBounds(280, 350, 150, 30);
        finalAmountTextField.setFont(new Font("Tahoma", 1, 18));
        add(finalAmountTextField);
        issue = new JButton("Оформить");
        issue.setBounds(70, 270, 300, 30);
        issue.setFont(new Font("Tahoma", 1, 23));
        issue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = Integer.valueOf(forex.getValue());
                finalsumm = Integer.parseInt(chooseamountTextField.getText()) + ((Integer.parseInt(chooseamountTextField.getText()) * (forex.getValue() / 2 + 3)) / 100);
                finalAmountTextField.setText(String.valueOf(finalsumm) + " ₸");
                payforMonthTextField.setText(String.valueOf(finalsumm / x) + " ₸");
            }
        });
        add(issue);


        acceptButton = new JButton("Принять");
        acceptButton.setBounds(70, 400, 150, 50);
        acceptButton.setBackground(Color.orange);
        acceptButton.setFont(new Font("Tahoma", 1, 23));
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBWorker DBWorker = new DBWorker();
                Connection con = null;
                try {
                    con = DriverManager.getConnection(DBWorker.url, DBWorker.name, DBWorker.pass);
                    PreparedStatement psforcheck = con.prepareStatement(check);
                    psforcheck.setString(1, Main.guestnumberofcard);
                    ResultSet trueorfalse = psforcheck.executeQuery();
                    while (trueorfalse.next()) {
                        checking = trueorfalse.getInt(1);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                if (checking == 0) {
                    try {
                        Connection c = DriverManager.getConnection(DBWorker.url, DBWorker.name, DBWorker.pass);
                        PreparedStatement ps = c.prepareStatement(GetQuery);


                        ps.setString(1, chooseamountTextField.getText());
                        ps.setString(2, "1");
                        ps.setString(3, monthLabel.getText());
                        ps.setString(4, Main.guestnumberofcard);
                        ps.executeUpdate();
//                    ps.setString(1, chooseamountTextField.getText());
//                    ps.setString(2, Main.guestnumberofcard);
//                    PreparedStatement infoUpdate = c.prepareStatement(UpdateInfo);

                        JOptionPane.showMessageDialog(null, "Процесс успешно выполнена", "Сообщение", JOptionPane.PLAIN_MESSAGE);
                        History.result.add("Кредит :" + chooseamountTextField.getText() + " ,в месяц-" + payforMonthTextField.getText() + " ," + percents.getText() + "\n");
                        chooseamountTextField.setText("");
                        finalAmountTextField.setText("");
                        payforMonthTextField.setText("");

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"У вас уже есть кредит!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        add(acceptButton);


        back = new JButton("Назад");
        back.setFont((new Font("Tahoma", 1, 20)));
        back.setBackground(Color.black.brighter());
        back.setForeground(Color.WHITE);
        back.setBounds(495, 440, 150, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.GetCreditWindow.setVisible(false);
                Main.frame.PersonalAreaWindow.setVisible(true);
                chooseamountTextField.setText("");
                finalAmountTextField.setText("");
                payforMonthTextField.setText("");
            }
        });
        add(back);
        add(background);


    }
}
