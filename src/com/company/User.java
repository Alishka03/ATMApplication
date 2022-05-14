package com.company;

public class User {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String numberOfCard;
    private String pinCode;
    private double moneyAmount;
    private double hascredit;
    private int monthofcredit;
    private boolean blocked;

    public User(String name, String surname, String username, String password, String numberOfCard, String pinCode, double moneyAmount, double hascredit, int monthofcredit, boolean blocked) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.numberOfCard = numberOfCard;
        this.pinCode = pinCode;
        this.moneyAmount = moneyAmount;
        this.hascredit = hascredit;
        this.monthofcredit = monthofcredit;
        this.blocked = blocked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumberOfCard() {
        return numberOfCard;
    }

    public void setNumberOfCard(String numberOfCard) {
        this.numberOfCard = numberOfCard;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public double getHascredit() {
        return hascredit;
    }

    public void setHascredit(double hascredit) {
        this.hascredit = hascredit;
    }

    public int getMonthofcredit() {
        return monthofcredit;
    }

    public void setMonthofcredit(int monthofcredit) {
        this.monthofcredit = monthofcredit;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return surname + " "+name+" "+" , "+username+" ,password: "+password+" , "+numberOfCard+" ,PIN:"+pinCode+" , "+moneyAmount+" ₸";
    }
    public String getInfo(){
        return surname + " "+name+" "+" , "+username+" , "+password+" , "+numberOfCard+" , "+" , "+moneyAmount;
    }
}