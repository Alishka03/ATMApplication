package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends Container {
    private JButton back;
    private JButton allUsers;
    private JButton addUser;
    private JButton deleteUser;
    private ImageIcon img;
    private JLabel background;
    private ImageIcon backImage;
    private JButton addmoney;
    public AdminPanel() {
        setSize(750, 550);
        setLayout(null);
        //images/backcolor.png
        backImage = new ImageIcon("images/backcolor.png");
        background = new JLabel();
        background.setBounds(0,0,750,550);
        background.setIcon(backImage);

        allUsers = new JButton("Пользователи");

        allUsers.setBounds(50,140,300,40);
        allUsers.setFont(new Font("Tahoma",1,20));
        allUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.AdminPanel.setVisible(false);
                Main.frame.AllUsersWindow.setVisible(true);
            }
        });
        add(allUsers);

        addUser = new JButton("Добавить пользователя");
        addUser.setBounds(400,140,300,40);
        addUser.setFont(new Font("Tahoma",1,15));
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.AdminPanel.setVisible(false);
                Main.frame.AdminAddUserWindow.setVisible(true);;
            }
        });
        add(addUser);

        deleteUser = new JButton("Удалить пользователя");
        deleteUser.setBounds(50,210,300,40);
        deleteUser.setFont(new Font("Tahoma",1,15));
        deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.AdminPanel.setVisible(false);
                Main.frame.AdminDeleteUserWindow.setVisible(true);;
            }
        });
        add(deleteUser);

        addmoney = new JButton("Пополнить денег");

        addmoney.setBounds(400,210,300,40);
        addmoney.setFont(new Font("Tahoma",1,15));
        addmoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.AdminPanel.setVisible(false);
                Main.frame.AdminDepositMoneyWindow.setVisible(true);;
            }
        });
        add(addmoney);
        back = new JButton("Назад");
        back.setFont((new Font("Tahoma", 1, 20)));
        back.setBackground(Color.black.brighter());
        back.setForeground(Color.WHITE);
        back.setBounds(500,440,150,40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int reply = JOptionPane.showConfirmDialog(null, "Хотите выйти с аккаунта?", "Сообщение", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    Main.frame.AdminPanel.setVisible(false);
                    Main.frame.homeWindow.setVisible(true);
                    Main.frame.homeWindow.usernameField.setText("");
                    Main.frame.homeWindow.passwordField.setText("");

                } else {
                }
            }
        });
        add(back);
        add(background);
    }
}
