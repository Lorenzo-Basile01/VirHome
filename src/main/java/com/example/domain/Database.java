package com.example.domain;

import com.sun.jdi.connect.Connector;

import java.sql.*;

public class Database {

    final String DB_URL = "jdbc:mysql://localhost:3306/virhome";
    final String USERNAME = "root";
    final String PASSWORD = "12345";
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

    public boolean verifyQuestion(String nome, String domanda){
        try {
            Statement stm = con.createStatement();
            String query = "SELECT domandaSicurezza from user where nome = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,nome);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next() == true) {
                if (rs.getString(1).equals(domanda)) {
                    System.out.println("accesso eseguito");
                    return true;
                } else {
                    System.out.println("domanda errata");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addUserDatabase(String nome, int codice, int confermaCod, String domanda, int telefono){
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
        return false;
    }
    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean modificaDati(String nome, int codice, int nuovoCod,String domanda, int telefono){
        try {
            Statement stm = con.createStatement();
            String query = "UPDATE user set codice = ?, domandaSicurezza = ?, telefono = ? where nome = ? and codice = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, nuovoCod);
            preparedStatement.setString(2, domanda);
            preparedStatement.setInt(3, telefono);
            preparedStatement.setString(4, nome);
            preparedStatement.setInt(5, codice);
            int n = preparedStatement.executeUpdate();
            if (n > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean rimuoviUser(String nome, int codice){
        try {
            Statement stm = con.createStatement();
            String query = "DELETE from user where nome = ? and codice = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, nome);
            preparedStatement.setInt(2, codice);
            int n = preparedStatement.executeUpdate();
            if (n > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
