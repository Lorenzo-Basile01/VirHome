package com.example.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import java.sql.Array;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VirHomeTest {

    static VirHome virHome;

    @BeforeClass
    public static void initTest(){
        virHome = VirHome.getInstance();
    }


    @Test
    void inserisciDispositivoTelecamera(){
        VirHome virHome = VirHome.getInstance();
        assertDoesNotThrow(
                () ->{
                    virHome.inserisciDispositivo("Bagno1", 't');
                });
    }

    @Test
    void confermaInserimentoTelecamera(){
        VirHome virHome = VirHome.getInstance();
        try {
            virHome.inserisciDispositivo("Bagno1", 't');
            assertDoesNotThrow(()->{
                virHome.confermaInserimento();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void inserisciDispositivoSensore(){
        VirHome virHome = VirHome.getInstance();
        assertDoesNotThrow(
                () ->{
                    virHome.inserisciDispositivo("Bagno1", 's');
                });
    }

    @Test
    void confermaInserimentoSensore(){
        VirHome virHome = VirHome.getInstance();
        try {
            virHome.inserisciDispositivo("Bagno1", 's');
            assertDoesNotThrow(()->{
                virHome.confermaInserimento();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void inserisciDispositivoBlankCodiceArea() {
        VirHome virHome = VirHome.getInstance();
        try {
            virHome.inserisciDispositivo("", 't');
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNull(virHome.getAreaCorrente());
    }

    @Test
    void inserisciTipoDispositivoErrato() {
        VirHome virHome = VirHome.getInstance();
        assertThrows(Exception.class,
                () ->{
            virHome.inserisciDispositivo("Bagno1", 'h');
        });
    }

    @Test
    void confermaInserimentoAreaNull() {
        VirHome virHome = VirHome.getInstance();
        AreaVigilata A = virHome.getAreaCorrente();
        virHome.setAreaCorrente(null);
        assertThrows(Exception.class,
                () ->{
                    virHome.confermaInserimento();
                });
        virHome.setAreaCorrente(A);
    }

    @AfterAll
    static void removeUserDatabase(){
        VirHome virHome = VirHome.getInstance();
        virHome.rimuoviUser("lorenzo1", 12345);
    }

    @Test
    void verifyUserTrue() {
        VirHome virHome = VirHome.getInstance();
        virHome.addUserDatabase("lorenzo1", 12345, 12345, "domanda", 323232);
        try {
            assertTrue(virHome.verifyUser(12345,"lorenzo1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void verifyUserFalse() {
        VirHome virHome = VirHome.getInstance();
        virHome.addUserDatabase("lorenzo1", 12345, 12345, "domanda", 323232);
        try {
            assertFalse(virHome.verifyUser(12345,"lorenzo2"));
            assertFalse(virHome.verifyUser(123456,"lorenzo1"));
            assertFalse(virHome.verifyUser(123456,"lorenzo2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        }


    @Test
    void attivaAntifurto() {
        VirHome virHome = VirHome.getInstance();

        HashMap<String, AreaVigilata> elencoAreeProva = new HashMap<>();
        AreaVigilata bagno = new AreaVigilata("Bagno1");
        AreaVigilata salotto = new AreaVigilata("Salotto1");
        AreaVigilata cucina = new AreaVigilata("Cucina1");
        AreaVigilata cameretta = new AreaVigilata("Cameretta1");
        elencoAreeProva.put("Bagno1", bagno);
        elencoAreeProva.put("Salotto1", salotto);
        elencoAreeProva.put("Cucina1", cucina);
        elencoAreeProva.put("Cameretta1", cameretta);

        assertArrayEquals(elencoAreeProva.keySet().toArray(),virHome.attivaAntifurto().toArray());
    }

    @Test
    void attivaAntifurtoMappaVuota() {
        VirHome virHome = VirHome.getInstance();
        Map<String, AreaVigilata> elencoAreeProva = new HashMap<>();
        elencoAreeProva = virHome.getElencoAree();
        assertNotNull(virHome.attivaAntifurto());

        virHome.setElencoAree(null);

        assertThrows(Exception.class,
                ()->{
                    virHome.attivaAntifurto();
                });

        virHome.setElencoAree(elencoAreeProva);
    }

    @Test
    void selezionaArea() {
        VirHome virHome = VirHome.getInstance();
        try {
            virHome.inserisciDispositivo("Cucina1", 't');
            virHome.confermaInserimento();
            virHome.inserisciDispositivo("Cucina1", 's');
            virHome.confermaInserimento();
            virHome.inserisciDispositivo("Cameretta1", 's');
            virHome.confermaInserimento();
            assertNotNull(virHome.selezionaArea("Cucina1"));
            assertNotNull(virHome.selezionaArea("Cameretta1"));
            assertEquals(2,virHome.selezionaArea("Cucina1").size());
            assertEquals(1,virHome.selezionaArea("Cameretta1").size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void selezionaAreaCodiceNulloVuoto() {
        VirHome virHome = VirHome.getInstance();

        assertThrows(Exception.class, ()->{
            virHome.selezionaArea("");
        });
        assertThrows(Exception.class, ()->{
            virHome.selezionaArea(null);
        });

    }

    @Test
    void selezionaDispositivoDaAttivare() {
        VirHome virHome = VirHome.getInstance();
        try {
            virHome.inserisciDispositivo("Salotto1", 't');
            virHome.confermaInserimento();
            Map<String, Dispositivo> Disp = virHome.selezionaArea("Salotto1");
            for(Dispositivo d: Disp.values()){
                assertTrue(virHome.selezionaDispositivoDaAttivare(d.getCodiceDispositivo(),"Salotto1"));
            }
            assertThrows(Exception.class, ()-> {
                virHome.selezionaDispositivoDaAttivare(5000, "Salotto1");
            });
            assertThrows(Exception.class, ()-> {
                virHome.selezionaDispositivoDaAttivare(1, "Soffitta");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void addObserver(){
        VirHome virHome = VirHome.getInstance();
        try {
            virHome.inserisciDispositivo("Salotto1", 't');
            virHome.confermaInserimento();
            Map<String, Dispositivo> Disp = virHome.selezionaArea("Salotto1");
            for (Dispositivo d : Disp.values()) {
                virHome.selezionaDispositivoDaAttivare(d.getCodiceDispositivo(), "Salotto1");
                virHome.addObserver(String.valueOf(d.getCodiceDispositivo()));
                assertNotNull(d.getObserver());
            }
            //casi d'errore
            assertThrows(Exception.class, ()->{
                virHome.addObserver("0");
            });
            assertThrows(Exception.class, ()->{
                virHome.addObserver("-1");
            });
            assertThrows(Exception.class, ()->{
                virHome.addObserver("5000");
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDispositivo(){
        VirHome virHome = VirHome.getInstance();
        try {
            Map<String, Dispositivo> Disp = virHome.selezionaArea("Salotto1");
            for (Dispositivo d : Disp.values()) {
                virHome.selezionaDispositivoDaAttivare(d.getCodiceDispositivo(), "Salotto1");
                virHome.addObserver(String.valueOf(d.getCodiceDispositivo()));
                virHome.testDispositivo(String.valueOf(d.getCodiceDispositivo()));
                assertTrue(d.isMovimento());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        assertThrows(Exception.class, ()->{
            virHome.testDispositivo("0");
        });
        assertThrows(Exception.class, ()->{
            virHome.testDispositivo("-1");
        });
        assertThrows(Exception.class, ()->{
            virHome.testDispositivo("5000");
        });
    }
    @Test
    void disarmAntifurto(){
        VirHome virHome = VirHome.getInstance();
        Map<String, Dispositivo> Disp = virHome.getElencoDispositiviAttivi();
        virHome.setElencoDispositiviAttivi(null);
        try {
            assertFalse(virHome.disarmAntifurto());
        }catch (Exception e) {
            e.printStackTrace();
        }
        virHome.setElencoDispositiviAttivi(Disp);

        assertTrue(virHome.disarmAntifurto());
        Array[] a = new Array[0];
        assertArrayEquals(a,virHome.getElencoDispositiviAttivi().values().toArray());
    }

    @Test
    void removeObserver(){
        VirHome virHome = VirHome.getInstance();
        Map<String, Dispositivo> Disp = virHome.getElencoDispositiviAttivi();
        //settiamo a null elencoDispositivi in mod tale che non ci sia alcun observer
        virHome.setElencoDispositiviAttivi(null);
        try {
            assertThrows(Exception.class,
                    () -> {
                        virHome.removeObserver();
                    });

            virHome.setElencoDispositiviAttivi(Disp);
            virHome.removeObserver();
            Collection<Dispositivo> dAttivi = virHome.getElencoDispositiviAttivi().values();
            for(Dispositivo d: dAttivi) {
                assertNull(d.getObserver());
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}