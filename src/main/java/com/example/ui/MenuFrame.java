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
    private JButton button1; //questo sarà disarma e passerai d nel costruttore
    private JLabel t;
    private JButton back;


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
}
