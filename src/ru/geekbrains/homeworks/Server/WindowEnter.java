package ru.geekbrains.homeworks.Server;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class WindowEnter extends JFrame {
    public WindowEnter() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 200, 700, 400);
        setVisible(true);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        Dimension labelSize = new Dimension(300, 60);
        Font font = new Font("Verdana", Font.BOLD, 48);
        Font fontButtons = new Font("Verdana", Font.ITALIC, 20);
        JLabel topLabel = new JLabel("ВХОД");
        topLabel.setPreferredSize(labelSize);
        topLabel.setVerticalAlignment(JLabel.TOP);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setForeground(Color.blue);
        topLabel.setFont(font);
        add(topLabel);

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

        JButton reg = new JButton("Войти");
        reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = areaLog.getText();
                String password = areaPass.getText();
                AuthenticationService.ClientService c = new AuthenticationService.ClientService(login,password);
                try {
                    if (c.findByLoginAndPassword(login,password)){               //!!!
                        System.out.println("Произошел вход");
                        new WindowMessage();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                System.out.println("Не произошел вход");
                new ErrorWindow();
            }
        });
        reg.setFont(fontButtons);
        add(reg);
        setVisible(true);

    }
}
