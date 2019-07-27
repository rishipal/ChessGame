package com.rishi.gui;
import javax.swing.*;
import java.awt.*;

public class Table {
    private JFrame gameFrame;

    public Table() {
        gameFrame = new JFrame();
        JMenuBar jMenuBar = new JMenuBar();
        gameFrame.setTitle("Rishi Chess Game");
        gameFrame.setPreferredSize(new Dimension(500, 400));
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
    }



}