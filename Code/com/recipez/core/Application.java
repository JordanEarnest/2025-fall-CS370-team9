package com.recipez.core;

import com.recipez.util.Log;

import javax.swing.*;
import java.io.IOException;

public class Application {

    private JFrame window;


    public Application() {
        initWindow();
    }

    private void initWindow() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1280, 720);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
