package com.example.domain;

import java.util.HashMap;
import java.util.Map;

public class AreaVigilata {

    private Dispositivo dispositivoCorrente;

    private String codiceArea;

    private Map<String,Dispositivo> elencoDispositivi;

    public AreaVigilata(String codiceArea) {
        this.codiceArea = codiceArea;
        this.elencoDispositivi = new HashMap<>();
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
        return "AreaVigilata{" +
                "codiceArea='" + codiceArea + '\'' +
                ", elencoDispositivi=" + elencoDispositivi +
                '}';
    }

    public void inserisciDispositivo(char tipoDispositivo)throws Exception{
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

    public void confermaInserimento(){
        if(this.dispositivoCorrente != null){
            elencoDispositivi.put(String.valueOf(this.dispositivoCorrente.getCodiceDispositivo()),this.dispositivoCorrente);
            System.out.println("operazione di inserimento conclusa");
        }
    }
}
