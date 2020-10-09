package ru.geekbrains.homeworks.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    public Window() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 200, 700, 400);
        setVisible(true);
        setLayout(new FlowLayout());

        Dimension labelSize = new Dimension(300, 60);
        Font font = new Font("Verdana", Font.BOLD, 48);
        Font fontButtons = new Font("Verdana", Font.ITALIC, 20);
        JLabel topLabel = new JLabel("МИНИ-ЧАТ");
        topLabel.setPreferredSize(labelSize);
        topLabel.setVerticalAlignment(JLabel.TOP);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setForeground(Color.BLUE);
        topLabel.setFont(font);
        add(topLabel);

        JButton enter = new JButton("Вход");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WindowEnter();
            }
        });
        JButton registration = new JButton("Регистрация");
        registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new WindowRegistration();
            }
        });
        enter.setFont(fontButtons);
        registration.setFont(fontButtons);
        add(enter);
        add(registration);
        setVisible(true);


    }
}
