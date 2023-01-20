package com.example.ui;

import com.example.domain.VirHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticationFrame extends JFrame{
    private JPanel AuthenticationPanel;
    private JTextField nometextField1;
    private JTextField codicetextField2;
    private JButton autenticatiButton;
    private JLabel intro;
    private JLabel nameField;
    private JLabel codiceField;
    private JLabel error;

    private VirHome virHome;
    public AuthenticationFrame(VirHome v) throws HeadlessException {
        this.virHome=v;
        setTitle("Main");
        setContentPane(AuthenticationPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
                authenticationActionPerformed(e);
            }
        });
    }

    private void authenticationActionPerformed(ActionEvent e){
        if(virHome.verifyUser(Integer.parseInt(codicetextField2.getText()), nometextField1.getText()) == true){
            System.out.println("autenticazione avventuta");
            new InserisciDispotivoFrame(virHome); // da cambiare
            setVisible(false);
        }
        else{
            error.setText("nome e/o codice errato");
        }
    }

}
