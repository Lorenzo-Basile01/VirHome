package com.example.domain;

public abstract class Dispositivo {
    private static int counter = 0;

    protected int codiceDispositivo;
    protected String area;
    protected boolean attivo;

    protected boolean movimento;

    private Observer observer;

    public Dispositivo(String area) {
        this.codiceDispositivo = ++counter;
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

    public void decrementaCounter(){
        Dispositivo.counter--;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    public boolean isMovimento() {
        return movimento;
    }

    public Observer getObserver() {
        return observer;
    }

    public void setMovimento(boolean movimento) {
        this.movimento = movimento;
        notify(this.area, this.codiceDispositivo);
    }

    public void addObserver(Observer o){
        this.observer = o;
    }

    public void removeObserver(){
        this.observer = null;
    }

    private void notify(String area, int codiceDispositivo){
        observer.update(area, codiceDispositivo);
    }
}
