package com.example.ui;

import com.example.domain.VirHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticationFrame extends JFrame{

    private static int count = 0;

    private JPanel AuthenticationPanel;
    private JTextField nometextField1;
    private JButton autenticatiButton;
    private JPasswordField codiceTextField;
    private JLabel intro;
    private JLabel nameField;
    private JLabel codiceField;
    private JLabel error;
    private JTextField domandaTextField;
    private JLabel domandaLabel;
    private char gestisci;

    private VirHome virHome;
    public AuthenticationFrame(VirHome v) throws HeadlessException {
        this.virHome=v;
        setTitle("Authentication");
        setContentPane(AuthenticationPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        eventHandler();
        settings();
    }

    public AuthenticationFrame(VirHome v, char g) throws HeadlessException {
        this.virHome=v;
        this.gestisci = g;
        setTitle("Authentication");
        setContentPane(AuthenticationPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        domandaTextField.setEnabled(false);
        setVisible(true);
        eventHandler();
        settings();
    }

    private void settings(){
        Font font = new Font("Magneto",Font.BOLD,18);
        intro.setFont(font);
        intro.setForeground(Color.gray);
    }

    private void eventHandler(){
        autenticatiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    authenticationActionPerformed(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void authenticationActionPerformed(ActionEvent e) throws Exception {
        if((codiceTextField.getText().equals("") || nometextField1.getText().equals("")) && codiceTextField.isEnabled()){
            error.setText("Compilare tutti i campi");
        }
        else if(count > 1) {
            domandaTextField.setEnabled(true);
            codiceTextField.setText("");
            codiceTextField.setEnabled(false);

            if (virHome.verifyQuestion(nometextField1.getText(), domandaTextField.getText())) {
                new AttivationFrame(virHome);
                setVisible(false);
            }
        }else {

            if (virHome.verifyUser(Integer.parseInt(codiceTextField.getText()), nometextField1.getText()) == true) {
                System.out.println("autenticazione avventuta");
                if (this.gestisci == 'a') {
                    new AttivationFrame(virHome);
                    setVisible(false);
                } else if(this.gestisci == 'd'){
                    new MenuFrame(virHome);
                    setVisible(false);
                }

            } else {
                error.setText("nome e/o codice errato");
                count++;
            }
        }
    }

}
