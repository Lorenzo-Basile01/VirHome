package com.example.domain;

public class Sensore extends Dispositivo{

    private int sensibilità;

    public Sensore(String area, int sensibilità)throws Exception  {
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

    public void setSensibilità(int sensibilità) throws Exception{
        if(sensibilità < 1){
            throw new Exception("Valore di sensibilità non valido");
        }
        this.sensibilità = sensibilità;
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
