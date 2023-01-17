package com.example.domain;

public abstract class Dispositivo {
    private static int counter = 0;

    protected int codiceDispositivo;
    protected String area;
    protected boolean attivo;

    public Dispositivo(String area) {
        this.codiceDispositivo = counter++;
        this.area = area;
        this.attivo = false;
    }

    public int getCodiceDispositivo() {
        return codiceDispositivo;
    }

    public void setCodiceDispositivo(int codiceDispositivo) {
        this.codiceDispositivo = codiceDispositivo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }
}
