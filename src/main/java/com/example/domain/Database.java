package com.example.domain;

import java.sql.*;

public class Database{

    final String DB_URL = "jdbc:mysql://localhost:3306/VirHome";
    final String USERNAME = "root";
    final String PASSWORD = "0000";
    Connection con;

    public Database() {
        try {
            con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("database creato");
    }

    public void addUserDatabase(String nome, int codice, String domanda, int telefono){

    }

    public void verifyUser(int codice, String nome){
        try {
            Statement stm = con.createStatement();
            String query = "SELECT codice FROM User Where nome=" + nome;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            System.out.println(preparedStatement.executeQuery());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
