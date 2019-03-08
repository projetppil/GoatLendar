package com.example.dell.goatlendar;

import java.sql.*;

public class BDD {
    Connection connection;
    public BDD(){
        String url="jdbc:mysql://127.0.0.1:3307/test";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection(url,"root","");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection Impossible \n");
            e.printStackTrace();
        }
        System.out.println("Connection réussi \n");
    }
    public int SelectIdUser(String mail, String motDePasse){
        int idUser=-1;
        boolean exec;
        String req="SELECT * FROM utilisateurs WHERE Mdp = ? and Mail = ?";

        try {
            PreparedStatement pstm=connection.prepareStatement(req);
            pstm.setString(1,motDePasse);
            pstm.setString(2,mail);
            ResultSet rs=pstm.executeQuery();
            while (rs.next()) {
                idUser = rs.getInt("IdUser");

            }
            return idUser;

        } catch (SQLException e) {
            System.out.println("probleme requete \n");
            return idUser;
        }


    }
}
