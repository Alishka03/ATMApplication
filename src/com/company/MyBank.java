package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import static javax.swing.SwingConstants.LEFT;

public class MyBank extends Container {

    public static String myusernumberofcard;
    private Double myBalanceFromQuery = 0.0;
    private JLabel background;
    private ImageIcon backImage;
    private JButton back;
    private JLabel numberofcardLabel;
    public static JLabel numberofcardTextField;
    private JLabel nameandsurname;
    public static JLabel nameandsurnameLabel;
    private JLabel pinlabel;
    public static JLabel pinlabelTextField;
    private JButton balanceButton;
    public static JLabel balanceTextField;
    public static User myUser;
    String s;
    private static final String getDataAboutMyUser ="select * from account where username = ? and password = ?";
    private static final String getBalanceMyBank = "select moneyamount from atmusers where username = ? and password = ?;";

    public MyBank()  {
        setSize(750,550);
        setLayout(null);
        setBackground(Color.CYAN);
        ArrayList<User> list = new ArrayList<User>();

        backImage = new ImageIcon("images/мойбанк.png");
        background = new JLabel();
        background.setBounds(0,0,750,550);
        background.setIcon(backImage);


        nameandsurname = new JLabel("Имя,фамилия:");
        nameandsurname.setBounds(170,115,300,30);
        nameandsurname.setFont((new Font("Tahoma", 1, 23)));
        add(nameandsurname);

        nameandsurnameLabel = new JLabel();
        nameandsurnameLabel.setBounds(350,115,300,30);
        nameandsurnameLabel.setFont((new Font("Tahoma",1,23)));
        add(nameandsurnameLabel);
        numberofcardLabel = new JLabel("Номер карты :");
        numberofcardLabel.setBounds(170,165,300,30);
        numberofcardLabel.setFont((new Font("Tahoma", 1, 23)));
        add(numberofcardLabel);
//        myusernumberofcard = "";


        numberofcardTextField = new JLabel(getString(Main.guestnumberofcard));
        numberofcardTextField.setFont((new Font("Tahoma", 1, 23)));
        numberofcardTextField.setBounds(350,165,300,30);
        add(numberofcardTextField);

        pinlabel = new JLabel("PIN CODE:");
        pinlabel.setFont(new Font("Tahoma",1,23));
        pinlabel.setBounds(170,215,300,30);
        add(pinlabel);

        pinlabelTextField = new JLabel();
        pinlabelTextField.setBounds(350,215,300,30);
        pinlabelTextField.setFont((new Font("Tahoma",1,23)));
        add(pinlabelTextField);
        balanceTextField = new JLabel();
        balanceTextField.setBounds(350,265,300,30);
        balanceTextField.setFont((new Font("Tahoma",1,23)));
        add(balanceTextField);
        balanceButton = new JButton("Баланс:");
        balanceButton.setBounds(65,265,300,30);
        balanceButton.setContentAreaFilled(false);
        balanceButton.setBorderPainted(false);
        balanceButton.setFont((new Font("Tahoma",1,23)));
        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    DBWorker DBWorker = new DBWorker();
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
        back.setFont((new Font("Tahoma", 1, 18)));
        back.setBackground(Color.black.brighter());
        back.setForeground(Color.WHITE);
        back.setBounds(190,390,300,30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberofcardTextField.setText(Main.guestnumberofcard);
                Main.frame.MyBankWindow.setVisible(false);
                Main.frame.PersonalAreaWindow.setVisible(true);
            }
        });
        add(back);





        add(background);

    }

    public static String getString(String x) {
        return (String) x;
    }

}
