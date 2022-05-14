package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminDeleteUser extends Container {
    private JLabel background;
    private JButton back;
    private ImageIcon backImage;
    private JLabel numberLabel;
    private JTextField numberField;
    private JButton searchButton;
    private JLabel receiverNameLabel;
    private JTextField receiverNameField;
    private String receiverNameRes = "";
    private String receivernumber;
    private JButton deleteButton;
    private static final String getreceiverName = "select name,surname from atmusers where numberofcard = ?;";
    private static final String DELETEUSER = "delete from atmusers where numberofcard = (?);";
    public AdminDeleteUser() {
        setSize(750, 550);
        setLayout(null);
        backImage = new ImageIcon("images/backcolor.png");
        background = new JLabel();
        background.setBounds(0,0,750,550);
        background.setIcon(backImage);
        numberLabel = new JLabel("Номер карты пользователя");
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

        receiverNameLabel = new JLabel("Имя пользователя");
        receiverNameLabel.setFont((new Font("Tahoma", 1, 23)));
        receiverNameLabel.setBounds(150, 205, 300, 30);
        add(receiverNameLabel);

        deleteButton = new JButton("Удалить");
        deleteButton.setBounds(150, 350, 300, 30);
        deleteButton.setFont(new Font("Tahoma", 1, 23));
        deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Хотите удалить пользователя?", "Сообщение", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        DBWorker DBWorker = new DBWorker();
                        Connection c = DriverManager.getConnection(DBWorker.url, DBWorker.name, DBWorker.pass);
                        PreparedStatement ps = c.prepareStatement(DELETEUSER);
                        ps.setString(1, receivernumber);
                        ps.execute();
                        JOptionPane.showMessageDialog(null,"Пользователь удален","Message",JOptionPane.PLAIN_MESSAGE);
                        receiverNameField.setText("");
                        numberField.setText("");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {

                }


            }
        });
        add(deleteButton);
        back = new JButton("Назад");
        back.setFont((new Font("Tahoma", 1, 20)));
        back.setBackground(Color.black.brighter());
        back.setForeground(Color.WHITE);
        back.setBounds(495, 440, 150, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.AdminDeleteUserWindow.setVisible(false);
                Main.frame.AdminPanel.setVisible(true);
            }
        });
        add(back);
        add(background);
    }
}
