package com.example.ui;

import com.example.domain.VirHome;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {
    private VirHome virhome;
    private JPanel menuPanel;

    public MenuFrame(VirHome v) throws HeadlessException {
        this.virhome=v;
        setTitle("Menù");
        setContentPane(menuPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
