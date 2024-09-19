package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        //Mirar DER de la base de datos.
        /*
         * Levantar todos los films
         * crear clase film y tirar query, creando una instancia, luego agregar en una lista de peliculas esa query
         * sacar el lenguaje del film
         * */
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("No se puede abrir el driver");
            return;
        }

        Film film = new Film();
        String query = "SELECT F.TITLE, L.NAME " +
                "FROM FILM F, LANGUAGE L" +
                "WHERE F.LANGUAGE_ID = L.LANGUAGE_ID";

        try (Connection con = DriverManager.getConnection(
                "jdbc:sqlite:sakila_master.db"
        );
/*             PreparedStatement insertStmt = con.prepareStatement();*/
             PreparedStatement stmt = con.prepareStatement(query);) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("LANGUAGE_ID");
                    String name = rs.getString("NAME");
                    String titulo = rs.getString("TITLE");
                    //Crear instancia titulo.
                    Title title = new Title(titulo);
                    Lenguage lenguage = new Lenguage(id, name);
                    //Agregar titulo al film
                    film.agregarTitulos(title);
                    film.agregarLenguage(lenguage);
                }
                film.mostrarPeliculas();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}