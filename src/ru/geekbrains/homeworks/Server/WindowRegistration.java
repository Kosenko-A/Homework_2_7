package ru.geekbrains.homeworks.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowRegistration extends JFrame {
    public WindowRegistration() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 200, 700, 400);
        setVisible(true);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        Dimension labelSize = new Dimension(300, 60);
        Font font = new Font("Verdana", Font.BOLD, 48);
        Font fontButtons = new Font("Verdana", Font.ITALIC, 20);
        JLabel topLabel = new JLabel("РЕГИСТРАЦИЯ");
        topLabel.setPreferredSize(labelSize);
        topLabel.setVerticalAlignment(JLabel.TOP);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setForeground(Color.blue);
        topLabel.setFont(font);
        add(topLabel);

        Dimension p00 = new Dimension(300, 60);
        Font font0 = new Font("Verdana", Font.BOLD, 20);
        JLabel p0 = new JLabel("Имя:");
        p0.setPreferredSize(p00);
        p0.setHorizontalAlignment(JLabel.CENTER);
        p0.setForeground(Color.black);
        p0.setFont(font0);
        add(p0);

        JTextField areaName = new JTextField();
        add(areaName);

        Dimension p11 = new Dimension(300, 60);
        Font font1 = new Font("Verdana", Font.BOLD, 20);
        JLabel p1 = new JLabel("Логин:");
        p1.setPreferredSize(p11);
        p1.setHorizontalAlignment(JLabel.CENTER);
        p1.setForeground(Color.black);
        p1.setFont(font1);
        add(p1);

        JTextField areaLog = new JTextField();
        add(areaLog);

        JLabel p2 = new JLabel("Пароль:");
        Font font2 = new Font("Verdana", Font.BOLD, 20);
        p2.setPreferredSize(p11);
        p2.setHorizontalAlignment(JLabel.CENTER);
        p2.setForeground(Color.black);
        p2.setFont(font2);
        add(p2);

        JTextField areaPass = new JTextField();
        add(areaPass);

        JButton reg = new JButton("Зарегистрироваться");
        reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = areaLog.getText();
                String password = areaPass.getText();
                String name = areaName.getText();
                AuthenticationService.Client c = new AuthenticationService.Client(name, login,password);
                try {
                    c.addToSql(name,login,password);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println("Произошла регистрация");
                new WindowEnter();
            }
        });
        reg.setFont(fontButtons);
        add(reg);
        setVisible(true);


    }
}
