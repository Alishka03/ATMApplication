package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Transactions extends Container {
    private JLabel numberLabel;
    private JTextField numberField;
    private JLabel background;
    private JButton searchButton;
    private ImageIcon backImage;
    private JButton back;
    private JLabel receiverNameLabel;
    private JTextField receiverNameField;
    private JLabel amountLabel;
    private JTextField amountField;
    private String receiverNameRes = "";
    private String receivernumber;
    private JButton acceptTransaction;
    private static final String getreceiverName = "select name,surname from atmusers where numberofcard = ?;";
    private static String transactionForReceiver = "update atmusers set moneyamount = (moneyamount + ?) where numberofcard = (?);";
    private static final String transactionForHome = "update atmusers set moneyamount = (moneyamount - ?) where numberofcard = (?);";
    private static final  String MoneyOfUser = "select moneyamount from atmusers where numberofcard = ?;";

    public Transactions() {
        setSize(750, 550);
        setLayout(null);
        backImage = new ImageIcon("images/transactionsWindow.png");
        background = new JLabel();
        background.setBounds(0, -10, 750, 550);
        background.setIcon(backImage);

        numberLabel = new JLabel("Номер карты получателя");
        numberLabel.setBounds(150, 135, 350, 30);
        numberLabel.setFont((new Font("Tahoma", 1, 23)));
        add(numberLabel);


        numberField = new JTextField();
        numberField.setBounds(150, 170, 175, 30);
        numberField.setFont((new Font("Tahoma", 1, 18)));
        add(numberField);
        receiverNameField = new JTextField();
        receiverNameField.setFont((new Font("Tahoma", 1, 18)));
        receiverNameField.setBounds(150, 240, 300, 30);
        add(receiverNameField);
        searchButton = new JButton("Поиск");
        searchButton.setFont((new Font("Tahoma", 1, 18)));
        searchButton.setBounds(335, 170, 120, 30);
        final double[] MoneyOfUserToCheckTransaction = {0.0};
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receivernumber = numberField.getText();

                try {
                    DBWorker DBWorker = new DBWorker();
                    Connection c = DriverManager.getConnection(DBWorker.url, DBWorker.name, DBWorker.pass);
                    PreparedStatement ps = c.prepareStatement(getreceiverName);
                    ps.setString(1, receivernumber);
                    PreparedStatement getMoney = c.prepareStatement(MoneyOfUser);
                    getMoney.setString(1, Main.guestnumberofcard);
                    ResultSet moneyamount = getMoney.executeQuery();
                    while (moneyamount.next()) {
                        MoneyOfUserToCheckTransaction[0] = Double.parseDouble(moneyamount.getString(1));
                    }
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        receiverNameRes = rs.getString(1) + " " + rs.getString(2);
                    }
                    receiverNameField.setText(receiverNameRes);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                receiverNameField.setText(receiverNameRes);
            }
        });
        add(searchButton);

        receiverNameLabel = new JLabel("Имя получателя");
        receiverNameLabel.setFont((new Font("Tahoma", 1, 23)));
        receiverNameLabel.setBounds(150, 205, 300, 30);
        add(receiverNameLabel);


        amountLabel = new JLabel("Введите сумму");
        amountLabel.setFont((new Font("Tahoma", 1, 23)));
        amountLabel.setBounds(150, 275, 300, 30);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 310, 300, 30);
        amountField.setFont(new Font("Tahoma", 1, 23));
        add(amountField);

        acceptTransaction = new JButton("Перевести");
        acceptTransaction.setBounds(150, 350, 300, 30);
        acceptTransaction.setFont(new Font("Tahoma", 1, 23));
        acceptTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if(MoneyOfUserToCheckTransaction[0]>Double.parseDouble(amountField.getText())) {
                        Connection connection = DriverManager.getConnection(DBWorker.url, DBWorker.name, DBWorker.pass);
                        PreparedStatement psforreceiver = connection.prepareStatement(transactionForReceiver);
                        psforreceiver.setDouble(1, Double.parseDouble((amountField.getText())));
                        psforreceiver.setString(2, numberField.getText());
                        psforreceiver.executeUpdate();
                        PreparedStatement psforhome = connection.prepareStatement(transactionForHome);
                        psforhome.setDouble(1, Double.parseDouble((amountField.getText())));
                        psforhome.setString(2, Main.guestnumberofcard);
                        psforhome.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Процесс успешно выполнена", "Сообщение", JOptionPane.PLAIN_MESSAGE);
                        History.result.add("Перевод : "+receiverNameField.getText()+" ,"+receivernumber+" ,"+amountField.getText()+" \n");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Ошибка", "Сообщение", JOptionPane.ERROR_MESSAGE);

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                numberField.setText("");
                amountField.setText("");
                receiverNameField.setText("");
            }
        });
        add(acceptTransaction);
        back = new JButton("Назад");
        back.setFont((new Font("Tahoma", 1, 20)));
        back.setBackground(Color.black.brighter());
        back.setForeground(Color.WHITE);
        back.setBounds(495, 440, 150, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.TransactionsWindow.setVisible(false);
                Main.frame.PersonalAreaWindow.setVisible(true);
            }
        });
        add(back);
        add(background);
    }
}
