package com.example.ui;

import com.example.domain.Dispositivo;
import com.example.domain.VirHome;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;

public class AttivationFrame extends JFrame {
    private JComboBox selezionaAreaBox;
    private JPanel AttivationPanel;
    private JComboBox selezionaDispositivoBox;
    private JButton Attiva;
    private JTextArea textArea1;
    private JButton menuButton;
    private JButton testDispositiviButton;

    private VirHome virHome;

    public AttivationFrame(VirHome v) {
        this.virHome=v;
        setTitle("Attivation");
        setContentPane(AttivationPanel);
        setSize(700,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        textArea1.setEditable(false);
        selezionaDispositivoBox.setEnabled(false);
        attivaAntifurto();
        eventHandler();
    }
    private void attivaAntifurto(){
        for(String s : virHome.attivaAntifurto()) {
            selezionaAreaBox.addItem(s);//aggiungo alla combo box una lista di oggetti
        }
    }

    private void eventHandler(){
        selezionaAreaBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attivaSelezioneDispositivoActionPerformed(e);
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuActionPerformed(e);
            }
        });

        Attiva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attivaActionPerformed(e);
            }
        });
        testDispositiviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {testDispositiviActionPerformed(e);
            }
        });
    }

    private void menuActionPerformed(ActionEvent e){
        new MenuFrame(virHome);
        setVisible(false);
    }

    private void attivaSelezioneDispositivoActionPerformed(ActionEvent e){
        textArea1.setText("");
        textArea1.append(selezionaAreaBox.getSelectedItem().toString()+"\n");
        //aggiungo alla combo box una lista di oggetti dispositivi
        selezionaDispositivoBox.removeAllItems();
        stampaMappa();

        selezionaDispositivoBox.setEnabled(true);

    }

    private void attivaActionPerformed(ActionEvent e){
        textArea1.setText("");
        if(selezionaDispositivoBox.getSelectedItem() != null && selezionaAreaBox.getSelectedItem() != null) {
            boolean attivo = virHome.selezionaDispositivoDaAttivare(Integer.parseInt(selezionaDispositivoBox.getSelectedItem().toString()), selezionaAreaBox.getSelectedItem().toString());
            virHome.addObserver(selezionaDispositivoBox.getSelectedItem().toString());
            if (!attivo) {
                textArea1.append("Dispositivo " + selezionaDispositivoBox.getSelectedItem().toString() + " già attivo");
            } else {
                selezionaDispositivoBox.removeAllItems();
                stampaMappa();
                selezionaDispositivoBox.setEnabled(false);
            }
        }else{
            textArea1.append("Nessun Dispostivo da attivare");
        }
    }

    private void stampaMappa(){

        Map<String, Dispositivo> map = virHome.selezionaArea(selezionaAreaBox.getSelectedItem().toString());
        //selezionaArea torna una mappa di dispositivi
        Iterator<Map.Entry<String, Dispositivo>> dispositivi = map.entrySet().iterator();
        //creo l'iterator
        while(dispositivi.hasNext()){
            Map.Entry<String, Dispositivo> dispositivo = dispositivi.next(); //scorro l'iterator
            selezionaDispositivoBox.addItem(dispositivo.getKey()); //aggiungo alla combo box i dispositivi
            textArea1.append(dispositivo.getValue().toString());
            textArea1.append("\n");
        }
    }

    private void testDispositiviActionPerformed(ActionEvent e){
        setVisible(false);
        new testDispositiviFrame(virHome);
    }



}
