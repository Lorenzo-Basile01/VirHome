package com.example.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AreaVigilataTest {

    static AreaVigilata A1;

    @BeforeAll
    public static void initTest(){
        A1 = new AreaVigilata("Cucina1");
    }

    @Test
    void inserisciDispositivoCodiceAreaNull() {
        AreaVigilata A = new AreaVigilata("Cucina1");
        A.setCodiceArea(null);
        assertThrows(NullPointerException.class,
                () ->{
                    A.inserisciDispositivo('t');
        });
    }


    @Test
    void inserisciDispositivoTelecamera(){
        AreaVigilata A = new AreaVigilata("Cucina1");
        try {
            A.inserisciDispositivo('t');
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(A.getDispositivoCorrente());
    }

    @Test
    void confermaInserimentoTelecamera(){
        AreaVigilata A = new AreaVigilata("Cucina1");
        try {
            A.inserisciDispositivo('t');
            A.confermaInserimento();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(A.getElencoDispositivi());
        A.setElencoDispositivi(null);
    }

    @Test
    void inserisciDispositivoSensore(){
        AreaVigilata A = new AreaVigilata("Cucina1");
        try {
            A.inserisciDispositivo('s');
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(A.getDispositivoCorrente());
    }

    @Test
    void confermaInserimentoSensore(){
        AreaVigilata A = new AreaVigilata("Cucina1");
        try {
            A.inserisciDispositivo('s');
            A.confermaInserimento();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(A.getElencoDispositivi());
        A.setElencoDispositivi(null);
    }

    @Test
    void confermaInserimentoDispositivoCorrenteNull() {
        AreaVigilata A = new AreaVigilata("Cucina1");
        assertThrows(Exception.class,
                () ->{
            A.confermaInserimento();
        });
    }

    @Test
    void selezionaDispositivoDaAttivare() {
        try {
            A1.inserisciDispositivo('t');
            A1.confermaInserimento();
            Dispositivo D = A1.selezionaDispositivoDaAttivare(1);
            assertNotNull(D);//prima attivazione
            assertTrue(D.isAttivo());
            assertNull(A1.selezionaDispositivoDaAttivare(1)); //seconda attivazione (gia attivo)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void selezionaDispositivoDaAttivareCodiceNonValido() {
        try {
            A1.inserisciDispositivo('t');
            A1.confermaInserimento();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThrows(Exception.class,
                ()->{
            A1.selezionaDispositivoDaAttivare(-1);
        });
        assertThrows(Exception.class,
                ()->{
            A1.selezionaDispositivoDaAttivare(0);
        });
        assertThrows(Exception.class,
                ()->{
                    A1.selezionaDispositivoDaAttivare(50000);
        });
    }

    @Test
    void selezionaDispositivoDaAttivareNonCreato() {

        AreaVigilata A = new AreaVigilata("Cucina1");
        assertThrows(Exception.class,
                () -> {
            A.selezionaDispositivoDaAttivare(-1);
        });
        assertThrows(Exception.class,
                () -> {
            A.selezionaDispositivoDaAttivare(0);
        });
        assertThrows(Exception.class,
                () -> {
            A.selezionaDispositivoDaAttivare(1);
        });
        assertThrows(Exception.class,
                () -> {
            A.selezionaDispositivoDaAttivare(500);
        });
    }

    @Test
    void setSensibilita(){
        AreaVigilata A = new AreaVigilata("Cucina1");
        try {
            A.inserisciDispositivo('s');
            A.confermaInserimento();
            assertDoesNotThrow(
                    ()->{
                A.setSensibilita(10);
            });
            assertDoesNotThrow(
                    ()->{
                A.setSensibilita(1);
            });
            assertDoesNotThrow(
                    ()->{
                A.setSensibilita(0);
            });
            assertDoesNotThrow(
                    ()->{
                        A.setSensibilita(-1);
                    });
            assertDoesNotThrow(
                    ()->{
                A.setSensibilita(11);
            });

            A.inserisciDispositivo('t');
            A.confermaInserimento();
            assertThrows(Exception.class,()->{
                A.setSensibilita(5);
            });

            A.setDispositivoCorrente(null);
            assertThrows(Exception.class,()->{
                A.setSensibilita(5);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}