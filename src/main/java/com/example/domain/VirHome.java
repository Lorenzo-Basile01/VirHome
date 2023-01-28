package com.example.domain;

import com.example.ui.ObserverFrame;

import java.net.SocketOption;
import java.sql.SQLOutput;
import java.util.*;

public class VirHome {

    private static VirHome virhome;
    private Map<String,AreaVigilata> elencoAree;
    private AreaVigilata areaCorrente;
    private Map<String,Dispositivo> elencoDispositiviAttivi;

    private Database database;

    private VirHome() {
        this.elencoAree = new HashMap<>();
        this.database =  new Database();
        this.elencoDispositiviAttivi = new HashMap<>();
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

    public Map<String, Dispositivo> getElencoDispositiviAttivi() {
        return elencoDispositiviAttivi;
    }

    public void setElencoDispositiviAttivi(Map<String, Dispositivo> elencoDispositiviAttivi) {
        this.elencoDispositiviAttivi = elencoDispositiviAttivi;
    }

    public Map<String, AreaVigilata> getElencoAree() {
        return elencoAree;
    }

    public void setElencoAree(Map<String, AreaVigilata> elencoAree) {
        this.elencoAree = elencoAree;
    }

    public AreaVigilata getAreaCorrente() {
        return areaCorrente;
    }

    public void setAreaCorrente(AreaVigilata areaCorrente) {
        this.areaCorrente = areaCorrente;
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

    public void confermaInserimento() throws Exception {
        if(areaCorrente == null){
            throw new Exception();
        }else {
            this.areaCorrente.confermaInserimento();
        }
    }

    //ITERAZIONE 2

    public boolean verifyUser(int codice, String nome) throws Exception {
        boolean a = database.verifyUser(codice,nome);
        return a;
    }

    public boolean verifyQuestion(String nome, String domanda) throws Exception {
        return database.verifyQuestion(nome, domanda);
    }

    public Set<String> attivaAntifurto(){
        return elencoAree.keySet();
    }

    public Map<String, Dispositivo> selezionaArea(String codiceArea) throws Exception {
        if(codiceArea == null || codiceArea.equals("")){
            throw new Exception("codice area nullo o vuoto");
        }else {
            AreaVigilata A = elencoAree.get(codiceArea);
            return A.getElencoDispositivi();
        }
         //ottengo un insieme di dispositivi, non sai ancora quale dei dispositivi vuoi attivare
    }

    public boolean selezionaDispositivoDaAttivare(int codiceDispositivo, String codiceArea) throws Exception {
        //sai il dispositivo da attivare e in quale area
        if(codiceArea == null || codiceArea.equals("")) {
            throw new Exception("codice area nullo o vuoto");
        }else {
            AreaVigilata A = elencoAree.get(codiceArea);
            Dispositivo dAttivo = A.selezionaDispositivoDaAttivare(codiceDispositivo);
            if (dAttivo != null) {
                elencoDispositiviAttivi.put(String.valueOf(codiceDispositivo), dAttivo);
                return true;
            }
        }
            return false;
    }

    public void addObserver(String codiceDispositivo) throws Exception {  //register
        if(Integer.parseInt(codiceDispositivo)<=0) {
            throw new Exception("codice dispositivo non valido");
        }else {
            Observer observerFrame = new ObserverFrame(virhome);
            Dispositivo d = elencoDispositiviAttivi.get(codiceDispositivo);
            if(d != null)
                d.addObserver(observerFrame);
            else
                throw new Exception("Dispositivo non esistente");
        }
    }

    public void testDispositivo(String codiceDispositivo) throws Exception {
        if(Integer.parseInt(codiceDispositivo)<=0) {
            throw new Exception("codice dispositivo non valido");
        }else {
            Dispositivo dAttivo = elencoDispositiviAttivi.get(codiceDispositivo);
            if(dAttivo != null)
                dAttivo.setMovimento(true);
            else
                throw new Exception("Dispositivo non esistente");
        }
    }

    public boolean disarmAntifurto(){
        if(elencoDispositiviAttivi != null) {
            Map<String, Dispositivo> map = elencoDispositiviAttivi;
            Iterator<Map.Entry<String, Dispositivo>> dispositivi = map.entrySet().iterator();
            while (dispositivi.hasNext()) {
                Map.Entry<String, Dispositivo> dispositivo = dispositivi.next(); //scorro l'iterator
                Dispositivo d = dispositivo.getValue();
                if (d.attivo) {
                    d.setAttivo(false);
                }
            }
            elencoDispositiviAttivi.keySet().removeAll(elencoDispositiviAttivi.keySet());
            return true;
        }
        return false;
    }

    public void removeObserver() throws Exception {
        if(elencoDispositiviAttivi == null){
            throw new Exception("nessun dispositivo attivo");
        }
        Collection<Dispositivo> dAttivi = elencoDispositiviAttivi.values();
        for(Dispositivo d: dAttivi) {
            d.removeObserver();
        }
    }

    public void setSensibilita(int sensibilita) throws Exception {
       this.areaCorrente.setSensibilita(sensibilita);
    }

    public void annullaInserimento(){
        this.areaCorrente.annullaInserimento();
    }

    public boolean modificaDati(String nome,int codice, int nuovoCod,String domanda, int telefono){
       return database.modificaDati(nome,codice,nuovoCod,domanda, telefono);
    }

    public boolean rimuoviUser(String nome, int codice){
        return database.rimuoviUser(nome,codice);
    }

}