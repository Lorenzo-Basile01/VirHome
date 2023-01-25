package com.example.ui;

import com.example.domain.VirHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImpostazioniFrame extends JFrame {
    private VirHome virhome;
    private JPanel impostazioniPanel;
    private JTextField nometextField1;
    private JLabel nome;
    private JLabel codice;
    private JLabel domanda;
    private JLabel telefono;
    private JButton effettuaModificheButton;
    private JTextField domandatextField2;
    private JTextField telefonotextField3;
    private JButton rimuoviUserButton;
    private JPasswordField passwordField1;
    private JButton menùButton;
    private JButton modificaDatiButton;
    private JLabel errore;
    private JLabel nuovoCodice;
    private JPasswordField nuovaPasswordField2;
    private JLabel label;


    public ImpostazioniFrame(VirHome v){
        this.virhome =v;
        setTitle("Settings");
        setContentPane(impostazioniPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        eventHandler();
        nome.setEnabled(false);
        nometextField1.setEnabled(false);
        codice.setEnabled(false);
        passwordField1.setEnabled(false);
        nuovoCodice.setEnabled(false);
        nuovaPasswordField2.setEnabled(false);
        label.setEnabled(false);
        label.setFont(new Font("Arial",Font.BOLD,8));
        domanda.setEnabled(false);
        domandatextField2.setEnabled(false);
        telefono.setEnabled(false);
        telefonotextField3.setEnabled(false);
        effettuaModificheButton.setEnabled(false);

    }

    private void eventHandler(){

        menùButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuFrame(virhome);
                setVisible(false);
            }
        });

        rimuoviUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nome.setEnabled(true);
                nometextField1.setEnabled(true);
                codice.setEnabled(true);
                passwordField1.setEnabled(true);
                effettuaModificheButton.setEnabled(true);
                nuovoCodice.setEnabled(false);
                nuovaPasswordField2.setEnabled(false);
                label.setEnabled(false);
                domanda.setEnabled(false);
                domandatextField2.setEnabled(false);
                telefono.setEnabled(false);
                telefonotextField3.setEnabled(false);
                errore.setText("");
            }
        });

        modificaDatiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nome.setEnabled(true);
                nometextField1.setEnabled(true);
                codice.setEnabled(true);
                passwordField1.setEnabled(true);
                nuovoCodice.setEnabled(true);
                nuovaPasswordField2.setEnabled(true);
                label.setEnabled(true);
                domanda.setEnabled(true);
                domandatextField2.setEnabled(true);
                telefono.setEnabled(true);
                telefonotextField3.setEnabled(true);
                effettuaModificheButton.setEnabled(true);
                errore.setText("");
            }
        });

        effettuaModificheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificaActionPreformed(e);
            }
        });
    }

    private void modificaActionPreformed(ActionEvent e){

        if(domandatextField2.isEnabled() && telefonotextField3.isEnabled() && nuovaPasswordField2.isEnabled()){
            if(!nometextField1.getText().equals("") && !passwordField1.getText().equals("") && !domandatextField2.getText().equals("") && !telefonotextField3.getText().equals("") && !nuovaPasswordField2.getText().equals("") && nuovaPasswordField2.getText().length() >4){
                if(virhome.modificaDati(nometextField1.getText(),Integer.parseInt(passwordField1.getText()),Integer.parseInt(nuovaPasswordField2.getText()),domandatextField2.getText(),Integer.parseInt(telefonotextField3.getText()))){
                    System.out.println("Modifiche effettuate");
                }else {
                    System.out.println("Mofifiche non effettuate");
                }
            }else{
                errore.setText("Compilare tutti i campi o\n inserire un codice corretto");
            }

        }else{ //rimuovi user
            if(!nometextField1.getText().equals("") && !passwordField1.getText().equals("")){
                if(virhome.rimuoviUser(nometextField1.getText(),Integer.parseInt(passwordField1.getText()))){
                    System.out.println("User eliminato");
                }else{
                    System.out.println("User ancora presente");
                }
            }else{
                errore.setText("Compilare tutti i campi");
            }

        }
       nometextField1.setText("");
       passwordField1.setText("");
       nuovaPasswordField2.setText("");
       domandatextField2.setText("");
       telefonotextField3.setText("");
       errore.setText("");
    }

}
