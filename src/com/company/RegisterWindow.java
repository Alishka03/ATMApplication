package com.company;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;


public class RegisterWindow extends Container{
    private JLabel regLabel;
    private ImageIcon reglogo;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel surnameLabel;
    private JTextField surnameField;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JLabel numberLabel;
    private JTextField numberField;
    private JLabel pincodeLabel;
    private JPasswordField pincodeField;
    public static JButton accept;
    private JButton back;
    private JLabel warning;
    private JLabel warning2;
    private JLabel background;
    private ImageIcon backImage;


    private static final String INSERT = "insert into atmusers (name,surname,username,password,numberofcard,pincode,moneyamount) values (?,?,?,?,?,?,?)";
    public RegisterWindow() {


        setSize(850,550);
        setLayout(null);
        setBackground(Color.CYAN);
        addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                setBackground(Color.cyan);
            }
        });
        backImage = new ImageIcon("images/registration.png");
        background = new JLabel();
        background.setBounds(0,-20,750,550);
        background.setIcon(backImage);
        nameLabel = new JLabel("Имя");
        nameLabel.setBounds(80,100,300,30);
        nameLabel.setFont((new Font("Tahoma", 1, 15)));
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(190,100,300,30);
        add(nameField);

        surnameLabel = new JLabel("Фамилия");
        surnameLabel.setFont((new Font("Tahoma", 1, 15)));
        surnameLabel.setBounds(80,140,300,30);
        add(surnameLabel);

        surnameField = new JTextField();
        surnameField.setBounds(190,140,300,30);
        add(surnameField);

        usernameLabel = new JLabel("Почта");
        usernameLabel.setBounds(80,180,300,30);
        usernameLabel.setFont((new Font("Tahoma", 1, 15)));
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(190,180,300,30);
        add(usernameField);

        passwordLabel = new JLabel("Пароль");
        passwordLabel.setBounds(80,220,300,30);
        passwordLabel.setFont((new Font("Tahoma", 1, 15)));
        add(passwordLabel);


        passwordField = new JTextField();
        passwordField.setBounds(190,220,300,30);
        add(passwordField);

        numberLabel = new JLabel("Номер карты");
        numberLabel.setBounds(80,260,300,30);
        numberLabel.setFont((new Font("Tahoma", 1, 15)));
        add(numberLabel);

        numberField = new JTextField();
        numberField.setBounds(190,260,300,30);
        add(numberField);

        pincodeLabel = new JLabel("PIN");
        pincodeLabel.setBounds(80,300,300,30);
        pincodeLabel.setFont((new Font("Tahoma", 1, 15)));
        add(pincodeLabel);


        pincodeField = new JPasswordField();
        pincodeField.setBounds(190,300,300,30);
        add(pincodeField);

        accept = new JButton("Регистрация");
        accept.setFont((new Font("Tahoma", 1, 18)));
        accept.setBackground(Color.black.brighter());
        accept.setForeground(Color.WHITE);
        accept.setBounds(190,350,300,30);
        warning = new JLabel("Заполните все поля!", SwingConstants.CENTER);
        warning.setFont((new Font("Tahoma", 1, 18)));
        warning.setBounds(190,420,300,30);
        warning.setVisible(false);
        warning.setForeground(Color.red);
        add(warning);
        warning2 = new JLabel("Регистрация прошла успешно!", SwingConstants.CENTER);
        warning2.setFont((new Font("Tahoma", 1, 18)));
        warning2.setBounds(190,420,300,30);
        warning2.setVisible(false);
        warning2.setForeground(Color.red);
        add(warning2);
        accept.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().toString();
                String surname = surnameField.getText().toString();
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();
                String numberOfCard = numberField.getText().toString();
                String pinCode = pincodeField.getText();
                String myPass=String.valueOf(pincodeField.getPassword()).toString();
                double moneyAmount = 0;

                warning.setVisible(false);

                if(name.equals("") || surname.equals("") || username.equals("") || password.equals("") || numberOfCard.equals("") || myPass.equals(" ") ){
                    warning.setVisible(true);
                }
                else {
                    try {
                        JOptionPane.showMessageDialog(null ,"Регистрация прошла успешна","Сообщение",JOptionPane.PLAIN_MESSAGE);
                        Connection c = DriverManager.getConnection(DBWorker.url, DBWorker.name, DBWorker.pass);
                        PreparedStatement ps = c.prepareStatement(INSERT);
                        ps.setString(1, name);
                        ps.setString(2, surname);
                        ps.setString(3, username);
                        ps.setString(4, password);
                        ps.setString(5, numberOfCard);
                        ps.setString(6, myPass);
                        ps.setDouble(7, 0);
                        ps.execute();
                        warning2.setVisible(true);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                nameField.setText("");
                surnameField.setText("");
                usernameField.setText("");
                passwordField.setText("");
                numberField.setText("");
                pincodeField.setText("");
            }
        });
        add(accept);

        back = new JButton("Назад");
        back.setFont((new Font("Tahoma", 1, 18)));
        back.setBackground(Color.black.brighter());
        back.setForeground(Color.WHITE);
        back.setBounds(190,390,300,30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.RegisterWindow.setVisible(false);
                Main.frame.homeWindow.setVisible(true);
            }
        });
        add(back);
        add(background);
    }
}
