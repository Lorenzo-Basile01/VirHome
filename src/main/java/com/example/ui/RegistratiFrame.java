package com.example.ui;

import com.example.domain.VirHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistratiFrame extends JFrame{
    private JTextField nomeField;
    private JTextField telefonoField;
    private JTextField domandaField;
    private JButton registratiButton;
    private JPasswordField codiceField;
    private JPasswordField confermaCodiceField;
    private JPanel registerPanel;
    private JLabel error;
    private JLabel titolo;
    private JButton back;
    private JLabel label;

    private VirHome virhome;

    public RegistratiFrame(VirHome v)throws HeadlessException {
        this.virhome= v;

        setTitle("Registration");
        setContentPane(registerPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        settings();
        eventHandler();
    }
     private void settings(){
         Font font = new Font("Magneto",Font.BOLD,15);
         label.setFont(new Font("Arial",Font.BOLD,8));
         titolo.setFont(font);
         titolo.setForeground(Color.gray);
     }
    private void eventHandler(){
        registratiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registratiActionPerformed(e);
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AccessFrame();
                setVisible(false);
            }
        });
    }

    private void registratiActionPerformed(ActionEvent e){
        if(nomeField.getText().equals("") || codiceField.getText().equals("") || confermaCodiceField.getText().equals("") || domandaField.getText().equals("") || telefonoField.getText().equals("")){
            error.setText("compilare tutti i campi");
        }
        else if(Integer.parseInt(codiceField.getText()) == Integer.parseInt(confermaCodiceField.getText()) && codiceField.getText().length() > 4){

            if (virhome.addUserDatabase(nomeField.getText(), Integer.parseInt(codiceField.getText()),Integer.parseInt(confermaCodiceField.getText()), domandaField.getText(), Integer.parseInt(telefonoField.getText())) == true) {
                System.out.println("registrazione avvenuta");
                new MenuFrame(virhome);
                setVisible(false);
            }else{
                error.setText("utente già esistente");
            }
        }
        else{
            error.setText("password diverse o lunghezza minore di 4");
        }

    }
}
