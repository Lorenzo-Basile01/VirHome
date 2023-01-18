package com.example.ui;

import com.example.domain.Database;
import com.example.domain.VirHome;

import javax.swing.*;
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
    private Database database;

    public AccessFrame() {

        virHome = VirHome.getInstance();
        database = new Database();

        setTitle("VirHome access");
        setContentPane(accesPanel);
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
                registratiActionPerformed(e);
            }
        });
    }

    private void accediActionPerformed(ActionEvent e){

        if(database.verifyUser(Integer.parseInt(passwordField1.getText()),textField1.getText()) == true){
            new MenuFrame(virHome);
            database.closeConnection();
            setVisible(false);
        }else{
            error.setText("nome e/o codice errato");
        }
    }

    private void registratiActionPerformed(ActionEvent e){
        new RegistratiFrame(virHome);
        setVisible(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AccessFrame");
        AccessFrame accessFrame = new AccessFrame();
    }
}
