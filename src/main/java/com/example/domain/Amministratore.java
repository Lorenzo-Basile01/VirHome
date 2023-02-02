package com.example.domain;

public class Amministratore {
    private String nome;
    private int codice;
    private String domanda;
    private int telefono;

    public Amministratore(String nome, int codice, String domanda, int telefono) {
        this.nome = nome;
        this.codice = codice;
        this.domanda = domanda;
        this.telefono = telefono;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getDomanda() {
        return domanda;
    }

    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Amministratore{" +
                "nome='" + nome + '\'' +
                ", codice=" + codice +
                ", domanda='" + domanda + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
