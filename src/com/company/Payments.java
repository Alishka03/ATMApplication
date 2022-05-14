package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Payments extends Container {
    private ImageIcon backImage;
    private JButton back;
    private JLabel background;
    private String [] thingsforpayment = {"Газ","Горячая вода и отопление","Холодная вода и канализация","Вывоз мусора","Электроэнергия","Квитанция за квартиру"};
    private JLabel wantopayLabel;
    private JComboBox payComboBox;
    private JLabel cardLabel;
    private JTextField cardField;
    private JButton searchButton;
    private JLabel amountLabel;
    private Double myBalanceFromQuery = 0.0;
    private JTextField amountField;
    private JButton balanceButton;
    private JButton accept;
    private Double tocheck;
    public static JLabel balanceTextField;
    private static final String getBalanceMyBank = "select moneyamount from atmusers where username = ? and password = ?;";
    private static final String paymentforHome =  "update atmusers set moneyamount = (moneyamount - ?) where numberofcard = (?);";

    public Payments() {

        setSize(750, 550);
        setLayout(null);
        backImage = new ImageIcon("images/payments.png");
        background = new JLabel();
        background.setBounds(0,-10,750,550);
        background.setIcon(backImage);


        wantopayLabel = new JLabel("Что хотите оплатить?");
        wantopayLabel.setBounds(60,100,300,30);
        wantopayLabel.setFont((new Font("Tahoma",1,23)));
        add(wantopayLabel);

        payComboBox = new JComboBox(thingsforpayment);
        payComboBox.setEditable(false);
        payComboBox.setBounds(50,140,330,30);
        payComboBox.setFont((new Font("Tahoma",1,18)));
        add(payComboBox);


        cardLabel = new JLabel("Выберите карту");

        cardLabel.setBounds(60,180,330,30);
        cardLabel.setFont((new Font("Tahoma",1,23)));
        add(cardLabel);

        cardField = new JTextField();
        cardField.setBounds(50,220,200,30);
        cardField.setFont((new Font("Tahoma",1,21)));
        add(cardField);

        searchButton = new JButton("Поиск");
        searchButton.setBounds(260,220,120,30);
        searchButton.setFont((new Font("Tahoma",1,18)));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardField.setText(Main.guestnumberofcard);
            }
        });
        add(searchButton);


        amountLabel = new JLabel("Введите сумму");

        amountLabel.setBounds(60,260,240,30);
        amountLabel.setFont((new Font("Tahoma",1,21)));
        add(amountLabel);

        amountField = new JTextField("");
        amountField.setBounds(50,300,200,30);
        amountField.setFont((new Font("Tahoma",1,21)));
        add(amountField);

        DBWorker DBWorker = new DBWorker();
        accept = new JButton("Оплатить");
        accept.setBounds(50,340,200,30);
        accept.setFont(new Font("Tahoma",1,23));
        accept.setForeground(Color.black);
        accept.setBackground(Color.orange);
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Connection c = DriverManager.getConnection(DBWorker.url, DBWorker.name, DBWorker.pass);
                    PreparedStatement ps = c.prepareStatement(paymentforHome);
                    PreparedStatement checkbalance = c.prepareStatement(getBalanceMyBank);
                    checkbalance.setString(1,Main.guestusername);
                    checkbalance.setString(2,Main.guestpassword);
                    ps.setDouble(1,Double.parseDouble(amountField.getText()));
                    ps.setString(2,Main.guestnumberofcard);
                    ResultSet rs = checkbalance.executeQuery();
                    while(rs.next()) {
                        tocheck = rs.getDouble(1);
                    }
                    if(Double.parseDouble(amountField.getText())>tocheck){
                        JOptionPane.showMessageDialog(null ,"Недостаточно средств","Сообщение",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        ps.executeUpdate();
                        String choice = String.valueOf(payComboBox.getSelectedItem());
                        String money = amountField.getText();
                        History.result.add("Платежи :"+" " + choice+" "+money+" ₸");
                        JOptionPane.showMessageDialog(null, "Вы оплатили " + money + "₸ на " + choice, "Сообщение", JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                amountField.setText("");
                cardField.setText("");
            }
        });
        add(accept);

        balanceTextField = new JLabel();
        balanceTextField.setBounds(550,100,300,30);
        balanceTextField.setFont((new Font("Tahoma",1,23)));
        add(balanceTextField);
        balanceButton = new JButton("Баланс:");
        balanceButton.setBounds(420,100,150,30);
        balanceButton.setContentAreaFilled(false);
        balanceButton.setBorderPainted(false);
        balanceButton.setFont((new Font("Tahoma",1,23)));
        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    Connection c = DriverManager.getConnection(DBWorker.url, DBWorker.name, DBWorker.pass);
                    PreparedStatement ps = c.prepareStatement(getBalanceMyBank);
                    ps.setString(1,Main.guestusername);
                    ps.setString(2,Main.guestpassword);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        myBalanceFromQuery=rs.getDouble(1);

                    }
                    balanceTextField.setText(Double.toString(myBalanceFromQuery));

                }catch (SQLException ex){
                    ex.printStackTrace();
                }

            }
        });
        add(balanceButton);



        back = new JButton("Назад");
        back.setFont((new Font("Tahoma", 1, 20)));
        back.setBackground(Color.black.brighter());
        back.setForeground(Color.WHITE);
        back.setBounds(450,440,150,40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.PaymentsWindow.setVisible(false);
                Main.frame.PersonalAreaWindow.setVisible(true);
                amountField.setText("");

            }
        });
        add(back);
        add(background);

    }
}
