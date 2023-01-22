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

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
    }

    /* TEMPLATE GOF
    protected abstract void doGet(String a);
    protected abstract void doSet(String b);

    public void prova(){ metodo che gestisce l'algoritmo che chiama al suo interno un metodo primitivo
        doGet("ciao");          dichiarato abstract nella superclasse che poi verra implementato dalla sottoclasse
        doSet("ciao");
    }

    }*/

    public boolean isMovimento() {
        return movimento;
    }

    public void setMovimento(boolean movimento) {
        this.movimento = movimento;
        notify(this.area, this.codiceDispositivo);
    }

    public void addObserver(Observer o){
        this.observer = o;
    }

    public void notify(String area, int codiceDispositivo){
        observer.update(area, codiceDispositivo);
    }
}
