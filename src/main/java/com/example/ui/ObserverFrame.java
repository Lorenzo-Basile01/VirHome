package com.example.ui;

import com.example.domain.Observer;
import com.example.domain.VirHome;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ObserverFrame extends JFrame implements Observer {
    private VirHome virhome;
    private JPanel observerPanel;
    private JLabel dispositivoAttivato;
    private JButton chiudiButton;
    private Clip clip;

    public ObserverFrame(VirHome v) {
        this.virhome = v;
        setTitle("observerFrame");
        setContentPane(observerPanel);
        setSize(550,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(false);
        settings();
        eventHandler();
        try {
            // Usa URL (invece di File) per leggere dal disco.
            File fileSuono = new File("alarm.wav");
            // Crea uno stream d'input audio dal file del suono.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileSuono);
            // Ottieni il clip.
            clip = AudioSystem.getClip();
            // Apri l'audio del clip.
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    private void settings(){
        Font font = new Font("Serif",Font.BOLD,18);
        dispositivoAttivato.setFont(font);
        dispositivoAttivato.setForeground(Color.red);
    }

    public void playClip() {

        if (clip.isRunning())
            clip.stop();   // Ferma il suono se è ancora in esecuzione.
        clip.setFramePosition(0); // Riavvolgi il suono.
        clip.start();     // Esegui il suono.

    }

    private void eventHandler(){
        chiudiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    @Override
    public void update(String area, int codiceDispositivo) {
        setVisible(true);
        dispositivoAttivato.setText("Dispositivo " + codiceDispositivo + " ha rilevato movimento in area " + area);
        playClip();
    }

}
