package com.example.ui;

import com.example.domain.Dispositivo;
import com.example.domain.VirHome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;

public class VisualizzaDispAttiFrame extends JFrame {
    private VirHome virhome;
    private JPanel visulaizationPanel;
    private JButton menuButton;
    private JTextArea textArea1;

    public VisualizzaDispAttiFrame(VirHome v){
        this.virhome=v;
        setTitle("Visualization");
        setContentPane(visulaizationPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        eventHandler();
        textArea1.setEditable(false);
        textArea1.setFont(new Font("Serif",Font.BOLD,15));
        stampaDispAttivi();
    }

    private void eventHandler(){
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuFrame(virhome);
                setVisible(false);
            }
        });
    }

    private void stampaDispAttivi(){
        Map<String, Dispositivo> map = virhome.getElencoDispositiviAttivi();
        Iterator<Map.Entry<String, Dispositivo>> dispositivi = map.entrySet().iterator();
        while(dispositivi.hasNext()){
            Map.Entry<String, Dispositivo> dispositivo = dispositivi.next(); //scorro l'iterator
            System.out.println(dispositivo);
            textArea1.append(dispositivo.getValue().toString());
            textArea1.append("\n");
        }
    }
}
