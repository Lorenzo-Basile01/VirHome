package com.example.ui;

import com.example.domain.Database;
import com.example.domain.VirHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccessFrame extends JFrame{

    private JPanel accesPanel;
    private JButton accediButton;
    private JButton registratiButton;
    private JLabel title;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JLabel error;

    private VirHome virHome;


    public AccessFrame() {

        virHome = VirHome.getInstance();

        setTitle("Access");
        setContentPane(accesPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        settings();
        eventHandler();
    }

    private void settings(){
        Font font = new Font("Magneto",Font.BOLD,20);

        title.setFont(font);
        title.setForeground(Color.gray);
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
                registratiActionPerformed(e);
            }
        });
    }

    private void accediActionPerformed(ActionEvent e){
        if(virHome.verifyUser(Integer.parseInt(passwordField1.getText()),textField1.getText()) == true){
            new MenuFrame(virHome);
            setVisible(false);
        }
        else{
            error.setText("nome e/o codice errato");
        }
    }

    private void registratiActionPerformed(ActionEvent e){
        new RegistratiFrame(virHome);
        setVisible(false);
    }

    public static void main(String[] args) {
        AccessFrame accessFrame = new AccessFrame();
    }
}
