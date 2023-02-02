package com.example.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmministratoreDbDAOTest {

    static AmministratoreDbDAO d = new AmministratoreDbDAO();

    @Test
    void verifyUser() {
        d.addUserDatabase(new Amministratore("lorenzo1", 12345, "domanda", 323232));
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

}