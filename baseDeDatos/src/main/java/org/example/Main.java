package org.example;

import java.sql.*;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {

        /*try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            System.out.println("Driver registrado correctamente");
        } catch (Exception e) {
            System.err.println("Error al registrar el driver");
            return;
        }*/

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException nf) {
            System.out.println("No se puede abrir el driver");
            return;
        }
        String query = "SELECT * FROM FILM";
        String s = "ALASKA PHANTOM";
        String query2 = "SELECT * " +
                "FROM FILM " +
                "WHERE TITLE = ? ";
        String query3 = "SELECT * FROM LANGUAGE" ;
        String insert = "INSERT INTO LANGUAGE(LANGUAGE_ID, NAME, LAST_UPDATE) " +
                "VALUES(?, ?, ?)";

        try (Connection con = DriverManager.getConnection(
                "jdbc:sqlite:sakila_master.db"
        );
             PreparedStatement insertStmt = con.prepareStatement(insert);
             PreparedStatement stmt = con.prepareStatement(query3);) {

            //Seteo de parametros
            insertStmt.setInt(1,7);
            insertStmt.setString(2, "Spanish");
            insertStmt.setTimestamp(3, Timestamp.from(Instant.now()));

            //Ejecutamos
            int cantidad = insertStmt.executeUpdate();
            System.out.println("filas afectadas: " + cantidad);

            //stmt.setString(1, s);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("ID: %d - Titulo %s\n",
                            rs.getInt("LANGUAGE_ID"), rs.getString("NAME"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
//Mirar DER de la base de datos.
/*
* Levantar todos los films
* crear clase film y tirar query, creando una instancia, luego agregar en una lista de peliculas esa query
* sacar el lenguaje del film
* */