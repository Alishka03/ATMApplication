package com.company;


import java.sql.*;
import java.util.ArrayList;

public class DBWorker {
    public static String url = "jdbc:mysql://localhost.:3306/mydbtest";
    public static String pass = "root";
    public static String name = "root";
    private Connection connection;


    public DBWorker() {
        try {
            connection = DriverManager.getConnection(url, name, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM atmusers");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String account_name;
                String account_surname;
                String account_username;
                String account_password;
                String account_numberofcard;
                String account_pincode;
                Double account_money;
                Double account_hascredit;
                int account_month;
                boolean account_isblocked;
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
                users.add(new User(account_name, account_surname, account_username, account_password, account_numberofcard, account_pincode, account_money, account_hascredit, account_month, account_isblocked));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public ArrayList<User> getUsersByName() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM atmusers order by name asc");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String account_name;
                String account_surname;
                String account_username;
                String account_password;
                String account_numberofcard;
                String account_pincode;
                Double account_money;
                Double account_hascredit;
                int account_month;
                boolean account_isblocked;
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
                users.add(new User(account_name, account_surname, account_username, account_password, account_numberofcard, account_pincode, account_money, account_hascredit, account_month, account_isblocked));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public ArrayList<User> getUsersBySurName() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM atmusers order by surname asc");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String account_name;
                String account_surname;
                String account_username;
                String account_password;
                String account_numberofcard;
                String account_pincode;
                Double account_money;
                Double account_hascredit;
                int account_month;
                boolean account_isblocked;
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
                users.add(new User(account_name, account_surname, account_username, account_password, account_numberofcard, account_pincode, account_money, account_hascredit, account_month, account_isblocked));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public ArrayList<User> getUsersByMoney() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM atmusers order by moneyamount desc");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String account_name;
                String account_surname;
                String account_username;
                String account_password;
                String account_numberofcard;
                String account_pincode;
                Double account_money;
                Double account_hascredit;
                int account_month;
                boolean account_isblocked;
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
                users.add(new User(account_name, account_surname, account_username, account_password, account_numberofcard, account_pincode, account_money, account_hascredit, account_month, account_isblocked));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public User getUserInfo(){
        User user = null;
        return user;
    }
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    url, name, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }


}
