package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PersonalAreaWindow extends Container {
    private JButton transitionButton;
    private JButton paymentButton;
    private JButton myBankButton;
    private JButton getCreditButton;
    private JButton back;
    private JLabel background;
    private ImageIcon backImage;
    private JButton addBalanceButton;
    private static final String getBalanceMyBank = "select moneyamount from atmusers where username = ? and password = ?;";
    private Double myBalanceFromQuery = 0.0;
    private JButton historyButton;
    private JButton payCreditButton;
    public PersonalAreaWindow() {

        setSize(750, 550);
        setLayout(null);
        setBackground(Color.CYAN);

        backImage = new ImageIcon("images/mainmenu.png");
        background = new JLabel();
        background.setBounds(0, -10, 750, 550);
        background.setIcon(backImage);


        transitionButton = new JButton("Переводы");
        transitionButton.setBounds(80, 140, 250, 50);
        transitionButton.setFont((new Font("Tahoma", Font.BOLD, 18)));
        transitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.PersonalAreaWindow.setVisible(false);
                Main.frame.TransactionsWindow.setVisible(true);
            }
        });
        add(transitionButton);


        paymentButton = new JButton("Платежи");
        paymentButton.setFont((new Font("Tahoma", Font.BOLD, 18)));
        paymentButton.setBounds(380, 140, 250, 50);
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.PersonalAreaWindow.setVisible(false);
                Main.frame.PaymentsWindow.setVisible(true);
                try {
                    DBWorker DBWorker = new DBWorker();
                    Connection c = DriverManager.getConnection(DBWorker.url, DBWorker.name, DBWorker.pass);
                    PreparedStatement ps = c.prepareStatement(getBalanceMyBank);
                    ps.setString(1, Main.guestusername);
                    ps.setString(2, Main.guestpassword);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        myBalanceFromQuery = rs.getDouble(1);

                    }
                    Main.frame.MyBankWindow.balanceTextField.setText(Double.toString(myBalanceFromQuery));

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(paymentButton);

        myBankButton = new JButton("Мой банк");
        myBankButton.setFont((new Font("Tahoma", Font.BOLD, 18)));
        myBankButton.setBounds(80, 210, 250, 50);
        myBankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.PersonalAreaWindow.setVisible(false);
                Main.frame.MyBankWindow.setVisible(true);

            }
        });
        add(myBankButton);

        getCreditButton = new JButton("Оформить кредит");
        getCreditButton.setFont((new Font("Tahoma", Font.BOLD, 18)));
        getCreditButton.setBounds(380, 210, 250, 50);
        getCreditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.PersonalAreaWindow.setVisible(false);
                Main.frame.GetCreditWindow.setVisible(true);
            }
        });
        add(getCreditButton);

        addBalanceButton = new JButton("Пополнить");
        addBalanceButton.setFont((new Font("Tahoma", Font.BOLD, 18)));
        addBalanceButton.setBounds(80, 285, 250, 50);
        addBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.PersonalAreaWindow.setVisible(false);
                Main.frame.DepositMoneyWindow.setVisible(true);
            }
        });
        add(addBalanceButton);
        historyButton = new JButton("История");
        historyButton.setFont((new Font("Tahoma", 1, 18)));
        historyButton.setBounds(380, 285, 250, 50);
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.PersonalAreaWindow.setVisible(false);
                Main.frame.HistoryWindow.setVisible(true);
            }
        });
        add(historyButton);
        add(historyButton);
        back = new JButton("Выйти");
        back.setFont((new Font("Tahoma", 1, 18)));
        back.setBackground(Color.black.brighter());
        back.setForeground(Color.WHITE);
        back.setBounds(240, 365, 250, 50);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Хотите выйти с аккаунта?", "Сообщение", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    Main.frame.PersonalAreaWindow.setVisible(false);
                    Main.frame.homeWindow.setVisible(true);
                    MainMenu.temp = 0;
                } else {

                }

            }
        });

//        payCreditButton = new JButton("Оплата кредитов");
//
//        payCreditButton.setFont((new Font("Tahoma", 1, 18)));
//        payCreditButton.setBounds(80, 365, 250, 50);
//        payCreditButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Main.frame.PersonalAreaWindow.setVisible(false);
//                Main.frame.PayCreditWindow.setVisible(true);
//            }
//        });
//        add(payCreditButton);
        add(back);
        add(background);
    }
}

