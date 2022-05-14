package com.company;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static MainMenu homeWindow;
    public static RegisterWindow RegisterWindow;
    public static PersonalAreaWindow PersonalAreaWindow;
    public static MyBank MyBankWindow;
    public static Transactions TransactionsWindow;
    public static DepositMoney DepositMoneyWindow;
    public static Payments PaymentsWindow;
    public static GetCredit GetCreditWindow;
    public static History HistoryWindow;
    public static PayCredit PayCreditWindow;
    public static AdminPanel AdminPanel;
    public static AdminAllUsers AllUsersWindow;
    public static AdminAddUser AdminAddUserWindow;
    public static AdminDeleteUser AdminDeleteUserWindow;
    public static AdminDepositMoney AdminDepositMoneyWindow;
    private ImageIcon logo;

    public MainFrame() {
        logo = new ImageIcon(("images/banklogo.png"));
        setIconImage(logo.getImage());
        setSize(750, 550);
        setResizable(false);
        setLayout(null);
        setTitle("Bank Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        homeWindow = new MainMenu();
        setLocation(0, 0);
        add(homeWindow);

        RegisterWindow = new RegisterWindow();
        setLocation(0, 0);
        RegisterWindow.setVisible(false);
        add(RegisterWindow);

        PersonalAreaWindow = new PersonalAreaWindow();
        setLocation(0, 0);
        PersonalAreaWindow.setVisible(false);
        add(PersonalAreaWindow);

        AdminDepositMoneyWindow = new AdminDepositMoney();
        setLocation(0, 0);
        AdminDepositMoneyWindow.setVisible(false);
        add(AdminDepositMoneyWindow);

        MyBankWindow = new MyBank();
        setLocation(0, 0);
        MyBankWindow.setVisible(false);
        add(MyBankWindow);

        TransactionsWindow = new Transactions();
        setLocation(0, 0);
        TransactionsWindow.setVisible(false);
        add(TransactionsWindow);

        DepositMoneyWindow = new DepositMoney();
        setLocation(0, 0);
        DepositMoneyWindow.setVisible(false);
        add(DepositMoneyWindow);

        PaymentsWindow = new Payments();
        setLocation(0, 0);
        PaymentsWindow.setVisible(false);
        add(PaymentsWindow);


        GetCreditWindow = new GetCredit();
        setLocation(0, 0);
        GetCreditWindow.setVisible(false);
        add(GetCreditWindow);

        HistoryWindow = new History();
        setLocation(0, 0);
        HistoryWindow.setVisible(false);
        add(HistoryWindow);

        AdminPanel = new AdminPanel();
        setLocation(0, 0) ;
        AdminPanel.setVisible(false);
        add(AdminPanel);

        AllUsersWindow = new AdminAllUsers();
        setLocation(0, 0) ;
        AllUsersWindow.setVisible(false);
        add(AllUsersWindow);


        AdminAddUserWindow = new AdminAddUser();
        setLocation(0, 0) ;
        AdminAddUserWindow.setVisible(false);
        add(AdminAddUserWindow);

        AdminDeleteUserWindow = new AdminDeleteUser();
        setLocation(0, 0) ;
        AdminDeleteUserWindow.setVisible(false);
        add(AdminDeleteUserWindow);


//        PayCreditWindow = new PayCredit();
//        setLocation(0, 0);
//        PayCreditWindow.setVisible(false);
//        add(PayCreditWindow);
    }
}
