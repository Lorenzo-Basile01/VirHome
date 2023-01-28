package com.example.ui;

import com.example.domain.VirHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    private VirHome virhome;
    private JPanel menuPanel;
    private JButton attivaAntifurtoButton;
    private JButton inserisciDispositivoButton;
    private JButton disarmaAntifurtoButton; //questo sarà disarma e passerai d nel costruttore
    private JLabel t;
    private JButton back;
    private JButton visualizzaDispositiviAttiviButton;
    private JButton impostazioniButton;

    public MenuFrame(VirHome v) throws HeadlessException {
        this.virhome=v;
        setTitle("Main");
        setContentPane(menuPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        eventHandler();
        settings();
    }

    private void settings(){
        Font font = new Font("Magneto",Font.BOLD,30);
        t.setFont(font);
        t.setForeground(Color.gray);
    }
    private void eventHandler(){
        inserisciDispositivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InserisciDispotivoFrame(virhome);
                setVisible(false);
            }
        });

        attivaAntifurtoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AuthenticationFrame(virhome,'a');
                setVisible(false);
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AccessFrame();
                setVisible(false);
            }
        });

        disarmaAntifurtoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disarmaActionPerformed(e);
            }
        });

        visualizzaDispositiviAttiviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizzaDispositiviActionperformed(e);
            }
        });

        impostazioniButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                impostazioniActionPerformed(e);
            }
        });
    }

    private void disarmaActionPerformed(ActionEvent e){
        setVisible(false);
        new AuthenticationFrame(virhome,'d');
        if(virhome.disarmAntifurto() == true){
            //rimuovo l'ooservatore
            try {
                virhome.removeObserver();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else{
            System.out.println("Nessun dispositivo attivo");
        }
    }

    private void visualizzaDispositiviActionperformed(ActionEvent e){
        new VisualizzaDispAttiFrame(virhome);
        setVisible(false);
    }

    private void impostazioniActionPerformed(ActionEvent e){
        new ImpostazioniFrame(virhome);
        setVisible(false);
    }
}
