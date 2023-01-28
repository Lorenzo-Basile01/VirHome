package com.example.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AreaVigilata {

    private Dispositivo dispositivoCorrente;

    private String codiceArea;

    private Map<String,Dispositivo> elencoDispositivi;

    public AreaVigilata(String codiceArea) {
        this.codiceArea = codiceArea;
        this.elencoDispositivi = new HashMap<>();
    }

    public Dispositivo getDispositivoCorrente() {
        return dispositivoCorrente;
    }

    public void setDispositivoCorrente(Dispositivo dispositivoCorrente) {
        this.dispositivoCorrente = dispositivoCorrente;
    }

    public String getCodiceArea() {
        return codiceArea;
    }

    public void setCodiceArea(String codiceArea) {
        this.codiceArea = codiceArea;
    }

    public Map<String, Dispositivo> getElencoDispositivi() {
        return elencoDispositivi;
    }

    public void setElencoDispositivi(Map<String, Dispositivo> elencoDispositivi) {
        this.elencoDispositivi = elencoDispositivi;
    }

    @Override
    public String toString() {
        return "AreaVigilata{"+
                "codiceArea='" + codiceArea + '\'' +
                "elencoDispositivi=" + elencoDispositivi +
                "}\n";
    }

    public void inserisciDispositivo(char tipoDispositivo)throws Exception{
        if(this.codiceArea == null){
            throw new NullPointerException("codice area nullo");
        }
        if(tipoDispositivo == 's'){
            Dispositivo D = new Sensore(this.codiceArea);
            this.dispositivoCorrente = D;
            System.out.println("Sensore creato");
        }else if(tipoDispositivo == 't'){
            Dispositivo D = new Telecamera(this.codiceArea);
            this.dispositivoCorrente = D;
            System.out.println("Telecamera creata");
        }else{
            throw new Exception("dispositivo insesistente");
        }
    }

    public void confermaInserimento() throws Exception {
        if(this.dispositivoCorrente != null){//get codice dispositivo lo facciamo qua
            elencoDispositivi.put(String.valueOf(this.dispositivoCorrente.getCodiceDispositivo()),this.dispositivoCorrente);
            System.out.println("operazione di inserimento conclusa");
        }else{
            throw new Exception("nessun dispositivo corrente");
        }
    }

    public Dispositivo selezionaDispositivoDaAttivare(int codiceDispositivo) throws Exception {
        Dispositivo D = elencoDispositivi.get(String.valueOf(codiceDispositivo));
        if(D == null){
            throw new Exception("nessun dispositivo creato, o nessun dispositivo esistente con tale codice");
        }else if(codiceDispositivo<=0){
            throw new Exception("codice non valido");
        }
        else if(!D.isAttivo()){
            D.setAttivo(true);
            return D;
        }
        return null;
    }

    public void setSensibilita(int sensibilita) throws Exception {
        if(dispositivoCorrente == null){
            throw new Exception("nessun dispositivo corrente");
        }
        if(dispositivoCorrente instanceof Sensore){
            try {
                ((Sensore) dispositivoCorrente).setSensibilità(sensibilita);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            throw new Exception("non puoi settare sensibilità telecamera");
        }
    }

    private void annullaDispositivoCorrente(){
        this.dispositivoCorrente = null;
    }

    public void annullaInserimento(){
        if(dispositivoCorrente != null) {
            dispositivoCorrente.decrementaCounter();
            annullaDispositivoCorrente();
        }
    }


}
