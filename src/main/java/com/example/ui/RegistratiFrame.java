package com.example.ui;

import com.example.domain.Database;
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

    private Database database;

    private VirHome virhome;

    public RegistratiFrame(VirHome v)throws HeadlessException {
        this.virhome= v;
        database = new Database();

        setTitle("registrazione");
        setContentPane(registerPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        eventHandler();
    }

    private void eventHandler(){
        registratiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registratiActionPerformed(e);
            }
        });
    }

    private void registratiActionPerformed(ActionEvent e){
        if (database.addUserDatabase(nomeField.getText(), Integer.parseInt(codiceField.getText()),Integer.parseInt(confermaCodiceField.getText()), domandaField.getText(), Integer.parseInt(telefonoField.getText())) == true)
        {
            System.out.println("registrazione avvenuta");
            new MenuFrame(virhome);
            setVisible(false);
        }
        else{
            error.setText("password diverse");
        }

    }
}
