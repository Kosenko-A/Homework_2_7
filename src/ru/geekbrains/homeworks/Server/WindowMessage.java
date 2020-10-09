package ru.geekbrains.homeworks.Server;

import javax.swing.*;
import java.awt.*;

public class WindowMessage extends JFrame {
    public WindowMessage() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 200, 700, 400);
        setVisible(true);
        setLayout(new GridLayout(2,1));

        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.setBackground(Color.cyan);
        JTextField textField = new JTextField();
        textField.setEditable(false);
        add(textField);

        JPanel active = new JPanel();
        active.setLayout(new GridLayout(1,2));
        JTextField inputField = new JTextField();
        add(inputField);
        JButton sendButtom = new JButton("Отправить");
        add(sendButtom);





    }
}
