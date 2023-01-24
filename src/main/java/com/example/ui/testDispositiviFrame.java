package com.example.ui;

import com.example.domain.Dispositivo;
import com.example.domain.VirHome;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;

public class testDispositiviFrame extends JFrame{

    private JPanel testPanel;
    private JComboBox testComboBox;
    private JButton testButton;
    private JButton menuButton;
    private JLabel error;
    private VirHome virHome;

    public testDispositiviFrame(VirHome v) {
        this.virHome=v;
        setTitle("Test");
        setContentPane(testPanel);
        setSize(700,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        stampaMappa();
        eventHandler();
    }

    private void eventHandler(){
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(testComboBox.getSelectedItem() == null){
                    error.setText("Nessun dispostivo attivo");
                }else {
                    virHome.testDispositivo(testComboBox.getSelectedItem().toString());
                }
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuFrame(virHome);
                setVisible(false);
            }
        });
    }

    private void stampaMappa(){
        Map<String, Dispositivo> map = virHome.getElencoDispositiviAttivi();
        //selezionaArea torna una mappa di dispositivi
        Iterator<Map.Entry<String, Dispositivo>> dispositivi = map.entrySet().iterator();
        //creo l'iterator
        while(dispositivi.hasNext()){
            Map.Entry<String, Dispositivo> dispositivo = dispositivi.next(); //scorro l'iterator
            testComboBox.addItem(dispositivo.getKey()); //aggiungo alla combo box i dispositivi
        }
    }

}
