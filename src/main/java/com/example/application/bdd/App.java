package com.example.application.bdd;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        try (Connection con = connect("localhost", 5432, "postgres", "postgres", "pass")) {
            tabledrop(con);
            table(con);
            java.sql.Date date = java.sql.Date.valueOf("2002-08-02");
            System.out.println("combien de personnes voulez vous ajouter ?");
            int k = Lire.i();
            for (int i = 0; i < k; i++) {
                System.out.println("donnez le nom de la personne");
                String nom = Lire.S();
                createPerson(con, nom, date);
            }
            afficheToutesPersonnes(con);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Connection connect(String host, int port, String database, String user, String pass)
            throws ClassNotFoundException, SQLException {
        // teste la présence du driver postgresql
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + database,
                database, pass);
        // fixe le plus haut degré d'isolation entre transactions
        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return con;
    }

    public static void tabledrop(Connection con) throws SQLException {
        try {
            try (Statement st = con.createStatement()) {
                st.executeUpdate("drop table person");
            }
        } catch (Exception e) {
            System.out.println("table person inexistante");
        }
    }

    public static void table(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.executeUpdate("""
                    create table Person(
                    id integer primary key generated always as identity,
                    nom varchar(50) not null,
                    dateNaissance date
                    )
                    """);
        }
    }

    public static void createPerson(Connection con, String nom, java.sql.Date dateNaiss) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement("""
                insert into Person (nom,dateNaissance)
                  values (?,?)
                """)) {
            pst.setString(1, nom);
            pst.setDate(2, dateNaiss);
            pst.executeUpdate();
        }
    }

    public static void afficheToutesPersonnes(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            ResultSet res = st.executeQuery("select * from person");
            while (res.next()) {
                // on peut accéder à une colonne par son nom
                int id = res.getInt("id");
                String nom = res.getString("nom");
                // on peut aussi y accéder par son numéro
                // !! numéro 1 pour la première
                java.sql.Date dn = res.getDate(3);
                System.out.println(id + " : " + nom + " né le " + dn);
            }
        }
    }
}