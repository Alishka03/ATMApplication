package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class History extends Container {
    private JLabel background;
    public static JTextArea historyField;

    private ImageIcon backImage;
    private JButton back;
    public static ArrayList<String> result;
    private JButton listHistory;
    public History() {
        setSize(750, 550);

        historyField = new JTextArea();
        historyField.setBounds(125, 100, 450, 270);

        historyField.setFont(new Font("Tahoma", 1, 18));
        add(historyField);
        backImage = new ImageIcon("images/History.png");
        background = new JLabel();
        background.setBounds(0, -20, 750, 550);
        background.setIcon(backImage);


        result = new ArrayList<>();
        listHistory = new JButton("Показать историю");
        listHistory.setFont(new Font("Tahoma",1,20));
        listHistory.setBounds(125, 385,300,40);
        listHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "";
                StringBuffer sb = new StringBuffer();
                for (String i:result){
                    s+=i+"\n";
                    sb.append(i+"\n");
                    historyField.setText((String) sb.toString()+"\n");
                }
            }
        });
        add(listHistory);
        back = new JButton("Назад");
        back.setFont((new Font("Tahoma", 1, 20)));
        back.setBackground(Color.black.brighter());
        back.setForeground(Color.WHITE);
        back.setBounds(495, 440, 150, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.HistoryWindow.setVisible(false);
                Main.frame.PersonalAreaWindow.setVisible(true);
            }
        });
        add(back);
        add(background);
    }
}
