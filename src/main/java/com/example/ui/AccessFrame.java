package com.example.ui;

import com.example.domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccessFrame extends JFrame{
    private JPanel AccessPanel;
    private JTextField textField1;
    private JButton accediButton;
    private JButton clearButton;
    private JLabel title;

    private VirHome virHome;

    public AccessFrame() {

        virHome = VirHome.getInstance();

        setTitle("VirHome access");
        setContentPane(AccessPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        title.setFont(new java.awt.Font("Arial",0,30));
        textField1.setSize(100,30);
        textField1.setMargin(new java.awt.Insets(0,20,0,20));

        eventHandler();

    }

    private void eventHandler(){
        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        AccessFrame accessFrame = new AccessFrame();
    }
}
