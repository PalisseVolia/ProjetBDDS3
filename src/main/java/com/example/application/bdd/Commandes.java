package com.example.application.bdd;

import java.sql.*;

public class Commandes 
{

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

    public static void tabledrop(Connection con, String nomtable) throws SQLException {
        //méthode permettant d'effacer une table de la base de donnée
        try {
            try (Statement st = con.createStatement()) {
                st.executeUpdate("drop table " + nomtable);
            }
        } catch (Exception e) {
            System.out.println("table " + nomtable + " inexistante, première éxécution ?");
        }
    }

    public static void AjoutEtudiant(Connection con, String nom, String prenom, String adresse, String mdp, String date, String dispo, String classe) throws SQLException
    {
       //méthode permettant d'ajouter un étudiant à la table contenant tous les étudiants
        try (PreparedStatement pst = con.prepareStatement(
            """
                    INSERT INTO Etudiant (nom,prenom,adresse,mdp,dateNaissance,disponibilite,classe)
                    VALUES (?,?,?,?,?,?,?)
                    """)){
        con.setAutoCommit(false);
        pst.setString(1, nom);
        pst.setString(2, prenom);
        pst.setString(3, adresse);
        pst.setString(4, mdp);
        pst.setDate(5, java.sql.Date.valueOf(date));
        pst.setString(6, dispo);
        pst.setString(7, classe);
        pst.executeUpdate();
        con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during AjoutEtudiant");
        }
    }
    public static void AjoutAdmin(Connection con,  String nom, String prenom, String adresse, String mdp, String date) throws SQLException{
        try (PreparedStatement pst = con.prepareStatement(
            """
                    INSERT INTO Admin (nom,prenom,adresse,mdp,dateNaissance)
                    VALUES (?,?,?,?,?)
                    """)){
        con.setAutoCommit(false);
        pst.setString(1, nom);
        pst.setString(2, prenom);
        pst.setString(3, adresse);
        pst.setString(4, mdp);
        pst.setDate(5, java.sql.Date.valueOf(date));
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during AjoutAdmin");
        }
    }
    public static void AjoutModule(Connection con, String intitule, String description, String nbplacemax, String nbplacemin, String classeacceptee) throws SQLException
    {
        //méthode permettant d'ajouter un module à la base de donnée
        try (PreparedStatement pst = con.prepareStatement(
            """
                    INSERT INTO Module (intitule,description,nbPlaceMax,nbPlaceMin,classeacceptee)
                    VALUES (?,?,?,?,?)
                    """)){
        con.setAutoCommit(false);
        System.out.println(intitule);
        System.out.println(description);
        System.out.println(nbplacemax);
        System.out.println(nbplacemin);
        System.out.println(classeacceptee);
        pst.setString(1, intitule);
        pst.setString(2, description);
        pst.setInt(3, Integer.parseInt(nbplacemax));
        pst.setInt(4, Integer.parseInt(nbplacemin));
        pst.setString(5, classeacceptee);
        pst.executeUpdate();
        con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during AjoutModule");
        }
    }
        
 
}
    // exemple drop table
    // public static void tabledrop(Connection con) throws SQLException {
    //     try {
    //         try (Statement st = con.createStatement()) {
    //             st.executeUpdate("drop table person");
    //         }
    //     } catch (Exception e) {
    //         System.out.println("table person inexistante");
    //     }
    // }
    // exemple creation table
    // public static void table(Connection con) throws SQLException {
    //     try (Statement st = con.createStatement()) {
    //         st.executeUpdate("""
    //                 create table Person(
    //                 id integer primary key generated always as identity,
    //                 nom varchar(50) not null,
    //                 dateNaissance date
    //                 )
    //                 """);
    //     }
    // }
    // exemple création d'entrée
    // public static void createPerson(Connection con, String nom, java.sql.Date dateNaiss) throws SQLException {
    //     try (PreparedStatement pst = con.prepareStatement("""
    //             insert into Person (nom,dateNaissance)
    //               values (?,?)
    //             """)) {
    //         pst.setString(1, nom);
    //         pst.setDate(2, dateNaiss);
    //         pst.executeUpdate();
    //     }
    // }
    // exemple affichage
    // public static void afficheToutesPersonnes(Connection con) throws SQLException {
    //     try (Statement st = con.createStatement()) {
    //         ResultSet res = st.executeQuery("select * from person");
    //         while (res.next()) {
    //             // on peut accéder à une colonne par son nom
    //             int id = res.getInt("id");
    //             String nom = res.getString("nom");
    //             // on peut aussi y accéder par son numéro
    //             // !! numéro 1 pour la première
    //             java.sql.Date dn = res.getDate(3);
    //             System.out.println(id + " : " + nom + " né le " + dn);
    //         }
    //     }
    // }
