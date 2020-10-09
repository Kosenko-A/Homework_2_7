package ru.geekbrains.homeworks.Server;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class ClientHandler {
    private String name;
    Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    FileWriter fw = new FileWriter("output.txt");

    public String getName() {
        return name;
    }

    public ClientHandler(Socket clientSocket, Server server) throws IOException {
        this.clientSocket = clientSocket;

        try {
            if (authenticate()) {
                this.in = new DataInputStream(clientSocket.getInputStream());
                this.out = new DataOutputStream(clientSocket.getOutputStream());
                start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.server = server;
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(authenticate()){
                    //authenticate();
                    readMessage();
                    }                                                                 //!!!!!!!!!!!!!!
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }
        }).start();

    }

    public boolean authenticate() throws IOException {
        makeTimer();
        while (true) {
            String loginInfo = in.readUTF();
            if (loginInfo.startsWith("-auth")) {
                String[] splittedLoginInfo = loginInfo.split("//s");
                AuthenticationService.Client maybeClient = server.getAuthenticationService().findByLoginAndPassword(
                        splittedLoginInfo[1],
                        splittedLoginInfo[2]
                );
                if (maybeClient != null) {
                    if (!server.chechLogin(maybeClient.getName())) {
                        sendMessage("status: auth ok");
                        name = maybeClient.getName();
                        server.broadcast(String.format("%s came in", name));
                        System.out.println("Client auth complited");
                        server.subscribe(this);
                        return true;
                    } else {
                        sendMessage(String.format("%s already logged in", maybeClient.getName()));
                        return false;
                    }
                } else {
                    sendMessage("Incorrect login or password");
                    return false;
                }
            }
        }

    }

    public void closeConnection() {
        server.unsubscribe(this);
        server.broadcast(String.format("%s left", name));
        // try {
        // in.close();
        // } catch (IOException e) {
        //  e.printStackTrace();
        // }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage() throws IOException {
        while (true) {
            String message = in.readUTF();
            String formatterMessage = String.format("Incoming message from %s: %s", name, message);
            System.out.println(formatterMessage);
            if (message.equalsIgnoreCase("-exit")) {
                return;
            }
            server.broadcast(formatterMessage);
        }

    }

    public void sendMessage(String message) {
        int point = 0;
        try {
            out.writeUTF(message);
            point++;
            while (point<=100){
                BufferedWriter bufferedWriter = new BufferedWriter(fw);
                bufferedWriter.newLine();
                bufferedWriter.write(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serverStop() {
        server.unsubscribe(this);
    }

    public void makeTimer() {
        TimerTask time = new TimerTask() {
            @Override
            public void run() {
                try {
                    sendMessage("You are disconnect");
                    serverStop();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(time, 120000);
    }
}
