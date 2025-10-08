package com.recipez.ui;

import javax.swing.*;

public class Window extends JFrame {
    public Window(String TITLE, int WIDTH, int HEIGHT) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle(TITLE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/assets/logo.png")).getImage());
    }
    public void display() {
        setVisible(true);
    }
}
