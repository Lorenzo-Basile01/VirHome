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

    public InserisciDispotivoFrame(VirHome v) {
        this.virhome= v;

        setTitle("Select device");
        setContentPane(inserisciPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        confermaInserimentoButton.setEnabled(false);

        eventHandler();
    }

    private void eventHandler(){
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
    }

    private void confermaInserimentoActionPerformed(ActionEvent e){
        virhome.confermaInserimento();
        System.out.println(virhome.getElencoAree());
        inserisciButton.setEnabled(true);
        confermaInserimentoButton.setEnabled(false);
    }

}
