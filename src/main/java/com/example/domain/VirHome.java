package com.example.domain;

import java.net.SocketOption;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VirHome {

    private static VirHome virhome;
    private Map<String,AreaVigilata> elencoAree;
    private AreaVigilata areaCorrente;

    private Database database;

    private VirHome() {
        this.elencoAree = new HashMap<>();
        this.database =  new Database();
        loadAree();
    }

    public static VirHome getInstance(){ //tutti possono accedere all'istanza di virhome
        if(virhome == null){
            virhome = new VirHome();
        }else{
            System.out.println("istanza già creata");
        }
        return virhome;
    }

    public Map<String, AreaVigilata> getElencoAree() {
        return elencoAree;
    }

    public AreaVigilata getAreaCorrente() {
        return areaCorrente;
    }

    public void loadAree(){
        AreaVigilata bagno = new AreaVigilata("Bagno1");
        AreaVigilata salotto = new AreaVigilata("Salotto1");
        AreaVigilata cucina = new AreaVigilata("Cucina1");
        AreaVigilata cameretta = new AreaVigilata("Cameretta1");
        this.elencoAree.put("Bagno1", bagno);
        this.elencoAree.put("Salotto1", salotto);
        this.elencoAree.put("Cucina1", cucina);
        this.elencoAree.put("Cameretta1", cameretta);
        System.out.println("caricamento aree completato");
    }

    //ITERAZIONE 1
    public boolean addUserDatabase(String nome, int codice, int confermaCod, String domanda, int telefono){
        boolean a = database.addUserDatabase(nome,codice,confermaCod,domanda,telefono);
        return a;
    }

    public void inserisciDispositivo(String codiceArea, char tipoDispositivo) throws Exception{

        AreaVigilata A = elencoAree.get(codiceArea);
        this.areaCorrente = A;
        if(A != null){
            A.inserisciDispositivo(tipoDispositivo);
        }else{
            System.out.println("area inesistente");
        }
    }

    public void confermaInserimento(){
        this.areaCorrente.confermaInserimento();
    }

    //ITERAZIONE 2

    public boolean verifyUser(int codice, String nome){
        boolean a = database.verifyUser(codice,nome);
        /*if(a == true){
            database.closeConnection();
            System.out.println("connessione chiusa");
        }*/
        return a;
    }

    public boolean verifyQuestion(String nome, String domanda){
        return database.verifyQuestion(nome, domanda);
    }

    public Set<String> attivaAntifurto(){
        return elencoAree.keySet();
    }

    public Map<String, Dispositivo> selezionaArea(String codiceArea){
         AreaVigilata s = elencoAree.get(codiceArea);
         return s.getElencoDispositivi();
         //ottengo un insieme di dispositivi, non sai ancora quale dei dispositivi vuoi attivare
    }

    public boolean selezionaDispositivoDaAttivare(int codiceDispositivo, String codiceArea){
        //sai il dispositivo da attivare e in quale area
        AreaVigilata A = elencoAree.get(codiceArea);
        return A.selezionaDispositivoDaAttivare(codiceDispositivo);
    }

}
