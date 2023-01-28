package com.example.domain;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DatabaseTest {

    static Database d = new Database();

    @AfterAll
    static void removeUserDatabase(){
        d.rimuoviUser("lorenzo1", 12345);
    }

    @Test
    void addUserDatabase() {
        assertTrue(d.addUserDatabase("lorenzo1", 12345, 12345, "domanda", 323232));
        assertFalse(d.addUserDatabase("lorenzo1", 12345, 12345, "domanda", 323232));
        assertFalse(d.addUserDatabase("", 12345, 12345, "", 323232));
        assertFalse(d.addUserDatabase("provaUser", 123456, 12345, "prova", 323232));
    }

    @Test
    void verifyUser() {
        d.addUserDatabase("lorenzo1", 12345, 12345, "domanda", 323232);
        assertTrue(d.verifyUser(12345,"lorenzo1"));
        assertFalse(d.verifyUser(12345,"lorenzo2"));
        assertFalse(d.verifyUser(123456,"lorenzo1"));
        assertFalse(d.verifyUser(123456,"lorenzo2"));
        assertFalse(d.verifyUser(12345,""));
        assertFalse(d.verifyUser(12345,null));
        assertFalse(d.verifyUser(9999,"lorenzo1"));
    }


    @Test
    void verifyQuestion() {
        assertFalse(d.verifyQuestion("","prova"));
        assertFalse(d.verifyQuestion(null,"prova"));
        assertFalse(d.verifyQuestion("lorenzo1",""));
        assertFalse(d.verifyQuestion("lorenzo1",null));
    }

    @Test
    void modificaDati() {
        d.addUserDatabase("lorenzoProva", 12345, 12345, "domanda", 323232);
        assertTrue(d.modificaDati("lorenzoProva", 12345, 123456, "domanda", 323232));
        assertFalse(d.modificaDati("lorenzoProva", 12345, 1234, "domanda", 323232));
        assertFalse(d.modificaDati("lorenzoProva", 1234, 12345, "domanda", 323232));
        assertFalse(d.modificaDati("", 12345, 12345, "", 323232));
    }

    @Test
    void rimuoviUser() {
        d.addUserDatabase("lorenzo1", 12345, 12345, "domanda", 323232);
        assertTrue(d.rimuoviUser("lorenzo1",12345));
        assertFalse(d.rimuoviUser("lorenzo2",12345));
        assertFalse(d.rimuoviUser("lorenzo1",123456));
    }
}