package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminDepositMoney extends Container {
    private JLabel background;
    private ImageIcon backImage;
    private JButton back;
    private JLabel numbercardLabel;
    private JTextField numbercardTextField;
    private JLabel pincodeLabel;
    private JTextField pincodeTextField;
    private JLabel moneyLabel;
    private JTextField moneyTextField;
    private JButton acceptButton;
    private static final String DepositMoneyQuery = "update atmusers set moneyamount = moneyamount + ? where numberofcard = ? and pincode = ?;";
    private static final String SearchAccountQuery = "select name from atmusers where numberofcard = ? and pincode = ?;";
    private String check = "";

    public AdminDepositMoney() {
        setSize(750, 550);
        setLayout(null);
        backImage = new ImageIcon("images/depositmoney.png");
        background = new JLabel();
        background.setBounds(0, 0, 750, 550);
        background.setIcon(backImage);
        numbercardLabel = new JLabel("Введите номер карты:");
        numbercardLabel.setBounds(150, 135, 350, 30);
        numbercardLabel.setFont((new Font("Tahoma", 1, 23)));
        add(numbercardLabel);

        numbercardTextField = new JTextField();
        numbercardTextField.setBounds(150, 170, 175, 30);
        numbercardTextField.setFont((new Font("Tahoma", 1, 18)));
        add(numbercardTextField);


        pincodeLabel = new JLabel("Введите PINCODE:");
        pincodeLabel.setBounds(150, 210, 300, 30);
        pincodeLabel.setFont((new Font("Tahoma", 1, 23)));
        add(pincodeLabel);
        pincodeTextField = new JTextField();
        pincodeTextField.setFont((new Font("Tahoma", 1, 18)));
        pincodeTextField.setBounds(150, 240, 100, 30);
        add(pincodeTextField);

        moneyLabel = new JLabel("Введите сумму ₸:");
        moneyLabel.setBounds(150, 270, 400, 30);
        moneyLabel.setFont((new Font("Tahoma", 1, 23)));
        add(moneyLabel);

        moneyTextField = new JTextField();
        moneyTextField.setFont((new Font("Tahoma", 1, 18)));
        moneyTextField.setBounds(150, 300, 200, 30);
        add(moneyTextField);


        acceptButton = new JButton("Пополнить");
        acceptButton.setBounds(150, 340, 200, 30);
        acceptButton.setBackground(Color.orange);
        acceptButton.setFont((new Font("Tahoma", 1, 23)));
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBWorker DBWorker = new DBWorker();
                try {
                    Connection c = DriverManager.getConnection(DBWorker.url, DBWorker.name, DBWorker.pass);
                    PreparedStatement psearch = c.prepareStatement(SearchAccountQuery);
                    psearch.setString(1, numbercardTextField.getText());
                    psearch.setString(2, pincodeTextField.getText());
                    ResultSet rs = psearch.executeQuery();

                    while (rs.next()) {
                        check = rs.getString(1);
                    }
                    if (!check.equals("")) {
                        PreparedStatement ps = c.prepareStatement(DepositMoneyQuery);
                        ps.setString(2, numbercardTextField.getText());
                        ps.setString(3, pincodeTextField.getText());
                        ps.setString(1, moneyTextField.getText());
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Процесс успешно выполнена", "Сообщение", JOptionPane.PLAIN_MESSAGE);
                        History.result.add("Пополнение :" + Main.guestnumberofcard + " " + moneyTextField.getText());

                    } else {
                        JOptionPane.showMessageDialog(null, "Ошибка!!!", "Сообщение", JOptionPane.ERROR_MESSAGE);
                    }


                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                numbercardTextField.setText("");
                pincodeTextField.setText("");
                moneyTextField.setText("");
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
                Main.frame.AdminDepositMoneyWindow.setVisible(false);
                Main.frame.AdminPanel.setVisible(true);
                numbercardTextField.setText("");
                moneyTextField.setText("");
                pincodeTextField.setText("");
            }
        });
        add(back);
        add(background);
    }
}