package com.company;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        ObjectOutputStream oos = null;

        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Waiting for a client");
        Socket socket = serverSocket.accept();
        System.out.println("Client has connected!");
        oos =  new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        String message ;
        try {
            while ((message = (String) ois.readObject()) != null) {
                if (message.equalsIgnoreCase("stop")) {

                    oos.writeObject("WE are Stopping our server");
                    break;
                }
                String text = "Server text :";
                System.out.println("CLIENT MESSAGE : " + message);
                oos.writeObject(text);
                oos.flush();
            }
        }catch(SocketException e){
            System.out.println("You have left ATMApplication!");
        }
    }
}
