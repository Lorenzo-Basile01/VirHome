package com.example.domain;

import java.sql.*;

public class Database{

    final String DB_URL = "jdbc:mysql://localhost:3306/VirHome";
    final String USERNAME = "root";
    final String PASSWORD = "";
    Connection con;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); //driveManager usata per avare connessione con db
            System.out.println("connessione database stabilita");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifyUser(int codice, String nome){
        try {
            Statement stm = con.createStatement();
            String query = "SELECT codice from user where nome = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,nome);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next() == true) {

                if (Integer.parseInt(rs.getString(1)) == codice) {
                    System.out.println("accesso eseguito");
                    return true;
                } else {
                    System.out.println("codice errato");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addUserDatabase(String nome, int codice, int confermaCod, String domanda, int telefono){
        if(codice == confermaCod) {
            try {
                Statement stm = con.createStatement();
                String query = "INSERT into user (nome, codice, domandaSicurezza, telefono)" + "values(?,?,?,?)";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, nome);
                preparedStatement.setInt(2, codice);
                preparedStatement.setString(3, domanda);
                preparedStatement.setInt(4, telefono);
                int n = preparedStatement.executeUpdate();
                if (n > 0) {
                    return true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
