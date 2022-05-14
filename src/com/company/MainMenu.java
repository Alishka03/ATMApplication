package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;


public class MainMenu extends Container {

    public static String account_name;
    public static String account_surname;
    public static String account_username;
    public static String account_password;
    public static String account_numberofcard;
    public static String account_pincode;
    public static Double account_money;
    public static Double account_hascredit;
    public static int account_month;
    public static boolean account_isblocked;
    public static User this_user;
    private ImageIcon logo;
    private JPanel newpanel;
    private JLabel usernameLabel;
    public static JTextField usernameField;
    private JLabel passwordLabel;
    public static JPasswordField passwordField;
    private JLabel bankLabel;
    private JButton login;
    private JPanel oldpanel;
    private JButton authorization;
    private ImageIcon bankicon;
    private JLabel background;
    private ImageIcon backImage;
    private JLabel warning;
    public static String myLog;
    public static String myPass;
    public static int temp = 0;
    private static final String check = "select 1 from atmusers where username=? and password=?";
    private static final String getDataAboutUser = "select * from atmusers";

    public MainMenu() {
        logo = new ImageIcon(("images/banklogo.png"));

        int index = 0;
        setSize(750, 550);
        setLayout(null);

        backImage = new ImageIcon("images/mainpage.png");
        background = new JLabel();
        background.setBounds(0, 0, 750, 550);
        background.setIcon(backImage);
        usernameLabel = new JLabel("Почта:");
        usernameLabel.setFont((new Font("Tahoma", 1, 18)));

        usernameLabel.setBounds(90, 220, 150, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont((new Font("Tahoma", Font.PLAIN, 18)));
        usernameField.setBounds(200, 220, 300, 30);
        add(usernameField);

        passwordLabel = new JLabel("Пароль:");
        passwordLabel.setFont((new Font("Tahoma", 1, 18)));
        passwordLabel.setBounds(90, 260, 80, 30);
        add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setForeground(new Color(81, 81, 101));
        passwordField.setFont(new Font("Arial", Font.BOLD, 23));
        passwordField.setBounds(200, 260, 300, 30);
        add(passwordField);

        login = new JButton("Войти");
        login.setBounds(200, 300, 150, 40);
        login.setBorderPainted(true);
        login.setBackground(Color.BLACK.brighter());
        login.setFont(new Font("Tahoma", 1, 15));
        login.setForeground(Color.WHITE);
        warning = new JLabel("Невозможно выполнить вход!", SwingConstants.CENTER);
        warning.setFont((new Font("Tahoma", 1, 18)));
        warning.setBounds(190, 370, 300, 30);
        warning.setVisible(false);
        warning.setForeground(Color.red);
        add(warning);

        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String myLog = usernameField.getText();
                String myPass = String.valueOf(passwordField.getPassword()).toString();
                Main.guestusername = usernameField.getText();
                if (myLog.equals("admin") && myPass.equals("admin")) {
                    Main.frame.homeWindow.setVisible(false);
                    Main.frame.AdminPanel.setVisible(true);
                } else {
                    try {
                        DBWorker dbWorker = new DBWorker();
                        Connection c = DriverManager.getConnection(DBWorker.url, DBWorker.name, DBWorker.pass);
                        PreparedStatement ps = c.prepareStatement(check);
                        ps.setString(1, myLog);
                        ps.setString(2, myPass);
                        ps.execute();
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            temp = rs.getInt(1);
                        }
                        ArrayList<User> list = getUsers();
                        for (User i : list) {
                            if (myLog.equals(i.getUsername()) && myPass.equals(i.getPassword())) {
                                Main.guestname = i.getName();
                                Main.guestsurname = i.getSurname();
                                Main.guestusername = i.getUsername();
                                Main.guestpassword = i.getPassword();
                                Main.guestnumberofcard = i.getNumberOfCard();
                                Main.guestpincode = i.getPinCode();
                                Main.guestmoney = i.getMoneyAmount();
                                Main.guesthascredit = i.getHascredit();
                                Main.guestmonth = i.getMonthofcredit();
                                Main.isblocked = i.isBlocked();
                            }
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    if (temp == 1) {
                        Main.frame.PersonalAreaWindow.setVisible(true);
                        Main.frame.homeWindow.setVisible(false);
                        MyBank.numberofcardTextField.setText(Main.guestnumberofcard);
                        MyBank.nameandsurnameLabel.setText(Main.guestname + " " + Main.guestsurname);
                        MyBank.pinlabelTextField.setText(Main.guestpincode);
                        MyBank.balanceTextField.setText(String.valueOf(Main.guestmoney));
                        System.out.println(Main.guestname + " " + Main.guestsurname);
                        usernameField.setText("");
                        passwordField.setText("");
                        warning.setVisible(false);
                        System.out.println(Main.guestnumberofcard);
                    } else {
                        warning.setVisible(true);
                        JOptionPane.showMessageDialog(null, "Невозможно выполнить вход", "Сообщение", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
        add(login);

        authorization = new JButton("Регистрация");
        authorization.setBounds(350, 300, 150, 40);
        authorization.setFont(new Font("Tahoma", 1, 15));
        authorization.setBackground(Color.BLACK.brighter());
        authorization.setForeground(Color.WHITE);
        authorization.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.RegisterWindow.setVisible(true);
                Main.frame.homeWindow.setVisible(false);
            }
        });
        add(authorization);
        add(background);
    }

    public static ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        try {
            DBWorker dbWorker = new DBWorker();
            Statement statement = dbWorker.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(getDataAboutUser);
            while (rs.next()) {
                account_name = rs.getString(2);
                account_surname = rs.getString(3);
                account_username = rs.getString(4);
                account_password = rs.getString(5);
                account_numberofcard = rs.getString(6);
                account_pincode = rs.getString(7);
                account_money = (Double.parseDouble(rs.getString(8)));
                account_hascredit = Double.valueOf(rs.getString(9));
                account_month = Integer.parseInt(rs.getString(10));
                account_isblocked = Boolean.parseBoolean(rs.getString(11));
                users.add(new User(account_name,account_surname,account_username,account_password,account_numberofcard,account_pincode,account_money,account_hascredit,account_month,account_isblocked));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;

    }
}