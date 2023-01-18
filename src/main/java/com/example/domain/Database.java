package com.example.domain;

import java.sql.*;

public class Database{

    final String DB_URL = "jdbc:mysql://localhost:3306/virhome";
    final String USERNAME = "root";
    final String PASSWORD = "";
    Connection con;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); //driveManager usata per avare connessione con db
            System.out.println("connessione stabilita");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUserDatabase(String nome, int codice, String domanda, int telefono){

    }

    public void verifyUser(int codice, String nome){
        try {
            Statement stm = con.createStatement();
            String query = "SELECT codice FROM User where nome = "+ nome;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            System.out.println(preparedStatement.executeQuery());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
