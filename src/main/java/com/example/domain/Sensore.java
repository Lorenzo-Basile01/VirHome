package com.example.domain;

public class Sensore extends Dispositivo{

    private int sensibilità;

    public Sensore(String area, int sensibilità){
        super(area);
        setSensibilità(sensibilità);
    }

    public Sensore(String area) {
        super(area);
        setSensibilità();
    }

    public int getSensibilità() {
        return sensibilità;
    }

    public void setSensibilità(){
        this.sensibilità = 5;
    }

    public void setSensibilità(int sensibilità){
        if(sensibilità < 1 || sensibilità > 10){
            System.out.println("valore della sensibilità non valido, valore minimo impostato");
            this.sensibilità = 1;
        }else {
            this.sensibilità = sensibilità;
        }
    }

    @Override
    public String toString() {
        return "Sensore{" +
                "codiceDispositivo=" + codiceDispositivo +
                ", area='" + area + '\'' +
                ", attivo=" + attivo +
                ", sensibilità=" + sensibilità +
                '}';
    }

}
