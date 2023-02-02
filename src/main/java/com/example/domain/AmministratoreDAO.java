package com.example.domain;

import java.util.List;

public interface AmministratoreDAO {
    boolean addUserDatabase(Amministratore amministratore);
    boolean verifyUser(int codice, String nome);
    boolean verifyQuestion(String nome, String domanda);
    boolean modificaDati(Amministratore amministratore);
    boolean rimuoviUser(Amministratore amministratore);
    List<Amministratore> getAllAmministratore();
}
