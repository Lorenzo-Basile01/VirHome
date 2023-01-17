package com.example.ui;

import com.example.domain.VirHome;

import javax.swing.*;

public class MainPanel extends javax.swing.JFrame{
    private VirHome virHome;
    private JButton button1;
    private JLabel PROVA;

    public MainPanel(VirHome v) {
        this.virHome = v;
        pack();
        setVisible(true);
        setSize(200,200);

    }


}
