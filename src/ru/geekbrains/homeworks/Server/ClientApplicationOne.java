package ru.geekbrains.homeworks.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientApplicationOne {
    public static void main(String[] args) {
        new Window();
        try {
            Socket clientSocket = new Socket("localhost", 8888);
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            out.writeUTF("-auth l1 p1");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String message = in.readUTF();                                               //????
                            System.out.println(message);
                            if (message.contains("Incorrect login or password")) {
                                out.writeUTF("-auth l1 p1");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }).start();

            out.writeUTF("-exit");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
