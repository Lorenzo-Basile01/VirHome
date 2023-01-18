package com.example.ui;

import com.example.domain.Database;
import com.example.domain.VirHome;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccessFrame extends JFrame{

    private JPanel panel1;
    private JButton accediButton;
    private JButton registratiButton;
    private JLabel title;
    private JPasswordField passwordField1;
    private JTextField textField1;

    private VirHome virHome;
    private Database database;

    public AccessFrame() {

        virHome = VirHome.getInstance();
        database = new Database();

        setTitle("VirHome access");
        setContentPane(panel1);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        settings();
        eventHandler();


    }

    private void settings(){
        //textField1.setSize(100,30);
        //textField1.setMargin(new java.awt.Insets(0,20,0,20));
        //add(accediButton);


    }

    private void eventHandler(){

        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accediActionPerformed(e);
            }
        });

        registratiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    private void accediActionPerformed(ActionEvent e){
        System.out.println(textField1.getText());
        System.out.println(passwordField1.getText());

        database.verifyUser(Integer.parseInt(passwordField1.getText()), textField1.getText());


    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("AccessFrame");
        AccessFrame accessFrame = new AccessFrame();
    }
}
