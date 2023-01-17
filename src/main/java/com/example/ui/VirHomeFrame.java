package com.example.ui;

import com.example.domain.VirHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VirHomeFrame extends javax.swing.JFrame{

    private javax.swing.JPanel VirHomeFrame;
    private javax.swing.JButton btnAccedi;
    private javax.swing.JLabel title;
    private javax.swing.JTextField textField1;

    private VirHome virHome;

    public VirHomeFrame() {

        virHome = VirHome.getInstance();
        this.title = new javax.swing.JLabel();
        VirHomeFrame.setSize(200,800);
        this.title.setFont(new java.awt.Font("JetBrains Mono",0,36));

        btnAccedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    accediActionPerformed(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void accediActionPerformed(ActionEvent e) throws Exception {
        //new MainPanel(virHome);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VirHomeFrame");
        frame.setContentPane(new VirHomeFrame().VirHomeFrame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
