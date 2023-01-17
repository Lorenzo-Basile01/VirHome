package com.example.domain;

public class Telecamera extends Dispositivo {

    public Telecamera(String area) {
        super(area);
    }

    @Override
    public String toString() {
        return "Telecamera{" +
                "codiceDispositivo=" + codiceDispositivo +
                ", area='" + area + '\'' +
                ", attivo=" + attivo +
                '}';
    }
}
