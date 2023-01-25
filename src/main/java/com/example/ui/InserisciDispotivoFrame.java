package com.example.ui;

import com.example.domain.VirHome;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InserisciDispotivoFrame extends JFrame{
    private VirHome virhome;
    private JPanel inserisciPanel;
    private JButton confermaInserimentoButton;
    private JComboBox areaVig;
    private JComboBox tipoDispositivo;
    private JButton inserisciButton;
    private JLabel selection1;
    private JLabel selection2;
    private JButton menuButton;
    private JSpinner spinner1;
    private JButton annullaButton;

    public InserisciDispotivoFrame(VirHome v) {
        this.virhome= v;
        aggiungiAreeAlComboBox();


        setTitle("Select device");
        setContentPane(inserisciPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        confermaInserimentoButton.setEnabled(false);
        spinner1.setEnabled(false);
        annullaButton.setEnabled(false);
        eventHandler();
    }

    private void aggiungiAreeAlComboBox(){
        for(String s : virhome.getElencoAree().keySet())
            areaVig.addItem(s);
    }

    private void eventHandler(){
        tipoDispositivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tipoDispositivo.getSelectedItem().toString() == "telecamera"){
                    spinner1.setEnabled(false);
                }
                else{
                    spinner1.setEnabled(true);
                }
            }
        });
        inserisciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    inserisciActionPerformed(e);
            }
        });

        confermaInserimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confermaInserimentoActionPerformed(e);
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { ritornaAlMenuActionPerformed(e);}
        });
        annullaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annullaActionPerformed(e);
            }
        });
    }

    private void inserisciActionPerformed(ActionEvent e){


        if(tipoDispositivo.getSelectedItem().toString() == "sensore"){
            try {
                virhome.inserisciDispositivo(areaVig.getSelectedItem().toString(),'s');
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }else{
            try {
                virhome.inserisciDispositivo(areaVig.getSelectedItem().toString(),'t');
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        confermaInserimentoButton.setEnabled(true);
        inserisciButton.setEnabled(false);
        spinner1.setEnabled(false);
        areaVig.setEnabled(false);
        tipoDispositivo.setEnabled(false);
        annullaButton.setEnabled(true);
    }

    private void confermaInserimentoActionPerformed(ActionEvent e){
        virhome.confermaInserimento();
        virhome.setSensibilita(Integer.parseInt(spinner1.getValue().toString()));
        inserisciButton.setEnabled(true);
        confermaInserimentoButton.setEnabled(false);
        areaVig.setEnabled(true);
        tipoDispositivo.setEnabled(true);
        annullaButton.setEnabled(false);
    }

    private void ritornaAlMenuActionPerformed(ActionEvent e){
        new MenuFrame(virhome);
        setVisible(false);
    }

    private void annullaActionPerformed(ActionEvent e){
        areaVig.setEnabled(true);
        tipoDispositivo.setEnabled(true);
        inserisciButton.setEnabled(true);
        confermaInserimentoButton.setEnabled(false);
        annullaButton.setEnabled(false);
        virhome.annullaInserimento();
    }
}
