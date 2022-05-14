package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PayCredit extends Container {
    private String moneyofcredit = "select hascredit from atmusers where numberofcard = ?;";
    private String monthofcredit =  "select monthofcredit from atmusers where numberofcard = ?;";
    public JButton InfoButton;
    public JTextArea InfoArea;
    private String res = "";
    private int month = 0;
    private StringBuffer resultofquery = new StringBuffer("");
    public PayCredit(){
        setSize(750, 550);
        setLayout(null);
        InfoButton = new JButton("Мои кредиты");
        InfoButton.setBounds(400,50,300,30);
        InfoButton.setFont((new Font("Tahoma",1,23)));
        InfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBWorker DBWorker  = new DBWorker();
                try {
                    Connection c = DriverManager.getConnection(DBWorker.url, DBWorker.name, DBWorker.pass);
                    PreparedStatement ps1 = c.prepareStatement(moneyofcredit);
                    PreparedStatement ps2 = c.prepareStatement(monthofcredit);
                    ps1.setString(1,Main.guestnumberofcard);
                    ps2.setString(1,Main.guestnumberofcard);
                    ResultSet r1 = ps1.executeQuery();
                    ResultSet r2 = ps2.executeQuery();
                    while (r1.next()) {
                        res = r1.getString(1);
                    }
                    while(r2.next()) {
                        month = Integer.parseInt(r2.getString(1));
                    }
                    System.out.println(res);
                    resultofquery.append("Остаток: "+res+"T"+"\n");
//                    InfoArea.setText("Остаток: "+res+"T"+"\n");

                    resultofquery.append("Месяц: "+month+"\n");
                    InfoArea.setText(resultofquery.toString());

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(InfoButton);

        InfoArea = new JTextArea();
        InfoArea.setBounds(400,85,300,340);
        InfoArea.setFont((new Font("Tahoma",1,23)));
        add(InfoArea);


        JButton back = new JButton("Назад");
        back.setFont((new Font("Tahoma", 1, 20)));
        back.setBackground(Color.black.brighter());
        back.setForeground(Color.WHITE);
        back.setBounds(495, 440, 150, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.PayCreditWindow.setVisible(false);
                Main.frame.PersonalAreaWindow.setVisible(true);
            }
        });

        add(back);
    }
}
