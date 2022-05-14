package com.company;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.io.*;
import java.net.*;
public class Main {
    public static MainFrame frame;
    public static ArrayList<User> users = new ArrayList<>();
    public static String guestname;
    public static String guestsurname;
    public static String guestusername;
    public static String guestpassword;
    public static String guestnumberofcard;
    public static String guestpincode;
    public static Double guestmoney;
    public static Double guesthascredit;
    public static int guestmonth;
    public static boolean isblocked;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Socket socket = new Socket("127.0.0.1",8000);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        System.out.println("You have connected to server");
        while (true){
            frame = new MainFrame();
            frame.setVisible(true);
            String text = reader.readLine();
            System.out.println(text);
            oos.writeObject(text);
            String txt = (String) ois.readObject();
            System.out.println(txt);

        }
    }
}