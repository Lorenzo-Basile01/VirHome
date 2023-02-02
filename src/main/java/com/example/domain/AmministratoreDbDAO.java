package com.example.domain;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AmministratoreDbDAO implements AmministratoreDAO{
    final String DB_URL = "jdbc:mysql://localhost:3306/virhome";
    final String USERNAME = "root";
    final String PASSWORD = "0000";
    Connection con;

    public AmministratoreDbDAO() {
        createConnection();
    }

    private void createConnection(){
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
    @Override
    public boolean addUserDatabase(Amministratore amministratore){
            try {
                String query1 = "SELECT * from user where nome = ? ";
                PreparedStatement preparedStatement1 = con.prepareStatement(query1);
                preparedStatement1.setString(1, amministratore.getNome());
                ResultSet rs = preparedStatement1.executeQuery();
                if (rs.next()) {
                    return false;
                }
                Statement stm = con.createStatement();
                String query = "INSERT into user (nome, codice, domandaSicurezza, telefono)" + "values(?,?,?,?)";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, amministratore.getNome());
                preparedStatement.setInt(2, amministratore.getCodice());
                preparedStatement.setString(3, amministratore.getDomanda());
                preparedStatement.setInt(4, amministratore.getTelefono());
                int n = preparedStatement.executeUpdate();
                if (n > 0) {
                    return true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        return false;
    }
//    public void closeConnection(){
//        try {
//            con.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @Override
    public boolean verifyUser(int codice, String nome){
        if(nome == null || nome.equals("")){
            return false;
        }else if(codice <= 9999){
            return false;
        }
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
    @Override
    public boolean verifyQuestion(String nome, String domanda) {
        if(nome == null || nome.equals("")){
            return false;
        }else if(domanda == null || domanda.equals("")){
            return false;
        }
        try {
            Statement stm = con.createStatement();
            String query = "SELECT domandaSicurezza from user where nome = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, nome);
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

    @Override
    public boolean modificaDati(Amministratore amministratore){
        try {
            Statement stm = con.createStatement();
            String query = "UPDATE user set codice = ?, domandaSicurezza = ?, telefono = ? where nome = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, amministratore.getCodice());
            preparedStatement.setString(2, amministratore.getDomanda());
            preparedStatement.setInt(3, amministratore.getTelefono());
            preparedStatement.setString(4, amministratore.getNome());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    @Override
    public boolean rimuoviUser(Amministratore amministratore){

        try {
            Statement stm = con.createStatement();
            String query = "DELETE from user where nome = ? and codice = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, amministratore.getNome());
            preparedStatement.setInt(2, amministratore.getCodice());
            int n = preparedStatement.executeUpdate();
            if (n > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Amministratore> getAllAmministratore() { // serve nell'interfaccia grafica per modifica DATI
        String query1 = "SELECT * from user";
        PreparedStatement preparedStatement1 = null;
        List<Amministratore> amministratoreList = new ArrayList<>();
        try {
            preparedStatement1 = con.prepareStatement(query1);
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()){
                amministratoreList.add(new Amministratore(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getInt(4)));
            }
            return amministratoreList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
    }
}
