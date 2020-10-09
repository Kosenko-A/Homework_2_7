package ru.geekbrains.homeworks.Server;

import javax.swing.*;
import java.awt.*;

public class ErrorWindow extends JFrame {
    public ErrorWindow() {
        setTitle("Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 200, 700, 400);
        setVisible(true);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        Dimension labelSize = new Dimension(300, 60);
        Font font = new Font("Verdana", Font.BOLD, 48);
        JLabel topLabel = new JLabel("ОШИБКА");
        topLabel.setPreferredSize(labelSize);
        topLabel.setVerticalAlignment(JLabel.CENTER);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setForeground(Color.red);
        topLabel.setFont(font);
        add(topLabel);
    }
}
