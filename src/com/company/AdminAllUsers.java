package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminAllUsers extends Container{
    private JButton back;
    private JTextArea AllUsersList;
    private JButton sortbymoney;
    private JButton sortbyname;
    private JButton sortbysurname;
    private JLabel background;
    private ImageIcon backImage;
    public AdminAllUsers() {
        setSize(750, 550);
        setLayout(null);
        backImage = new ImageIcon("images/backcolor.png");
        background = new JLabel();
        background.setBounds(0,0,750,550);
        background.setIcon(backImage);
        sortbyname = new JButton("Сорт.по имени");
        sortbyname.setBounds(50,40,200,30);
        sortbyname.setFont(new Font("Tahoma",0,18));
        sortbyname.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                DBWorker dbWorker = new DBWorker();
                ArrayList<User> list = dbWorker.getUsersByName();
                int counter = 1;
                StringBuilder sb = new StringBuilder("");
                for (User user : list){
                    sb.append(counter+". "+user+"\n");
                    counter++;
                }
                AllUsersList.setText(sb.toString());
            }
        });
        add(sortbyname);
        sortbysurname = new JButton("Сорт.по фамилии");
        sortbysurname.setBounds(250,40,200,30);
        sortbysurname.setFont(new Font("Tahoma",0,18));
        sortbysurname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBWorker dbWorker = new DBWorker();
                ArrayList<User> list = dbWorker.getUsersBySurName();
                StringBuilder sb = new StringBuilder("");
                int counter = 1;
                for (User user : list){

                    sb.append(counter+". "+user+"\n");
                    counter++;
                }
                AllUsersList.setText(sb.toString());
            }
        });
        add(sortbysurname);

        sortbymoney = new JButton("Сорт.по деньгам");
        sortbymoney.setBounds(450,40,200,30);
        sortbymoney.setFont(new Font("Tahoma",0,18));
        sortbymoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int counter = 1;
                DBWorker dbWorker = new DBWorker();
                ArrayList<User> list = dbWorker.getUsersByMoney();
                StringBuilder sb = new StringBuilder("");
                for (User user : list){

                    sb.append(counter+". "+user+"\n");
                    counter++;
                }
                AllUsersList.setText(sb.toString());
            }
        });
        add(sortbymoney);
        AllUsersList = new JTextArea();
        AllUsersList.setBounds(50,70,600,350);
        AllUsersList.setFont(new Font("Times New Roman",0,15));
        add(AllUsersList);

        back = new JButton("Назад");
        back.setFont((new Font("Tahoma", 1, 20)));
        back.setBackground(Color.black.brighter());
        back.setForeground(Color.WHITE);
        back.setBounds(495, 440, 150, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.AllUsersWindow.setVisible(false);
                Main.frame.AdminPanel.setVisible(true);
                AllUsersList.setText(" ");
            }
        });
        add(back);
        add(background);

    }
}
