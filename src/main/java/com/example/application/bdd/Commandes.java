/* Les commandes déjà réalisées sont :

    - connect () -> retourne une connection à la bdd
    - tabledrop(con,table) -> efface la table "table" de la bdd lié à la connection "con"
    -
    -

 */

package com.example.application.bdd;

import static com.example.application.bdd.security.CreateHash;
import java.sql.*;

public class Commandes 
{
    public static void AjoutPersonne(Connection con, String nom, String prenom, String dateNaissance, String adresseMail, String mdp) throws SQLException {
        //Ajout d'une personne dans la table "Personnes"
        String mdpHash = CreateHash(mdp);
        if(nom.length()>50 || prenom.length()>50 || adresseMail.length()>100 || mdpHash.length()>50){
            try (PreparedStatement pst = con.prepareStatement(
                    """
                            INSERT INTO Personnes (nom,prenom,dateNaissance,adresse,mdp)
                            VALUES (?,?,?,?,?)
                            """)){
                con.setAutoCommit(false);
                pst.setString(1, nom);
                pst.setString(2, prenom);
                pst.setDate(3, java.sql.Date.valueOf(dateNaissance));
                pst.setString(4, adresseMail);
                pst.setString(5, mdpHash);
                pst.executeUpdate();
                con.commit();
            }
        }else{
            if(nom.length()>50){System.out.println("ERROR: AjoutPersonne : nom trop long");}
            if(prenom.length()>50){System.out.println("ERROR: AjoutPersonne : prenom trop long");}
            if(adresseMail.length()>100){System.out.println("ERROR: AjoutPersonne : adresseMail trop long");}
            if(mdpHash.length()>50){System.out.println("ERROR: AjoutPersonne : mdpHash trop long");}
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
            pst.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during AjoutAdmin");
        }
    }

    public static int findPersonne(Connection con, String table, String nom, String prenom) throws SQLException {
        //Trouve la PREMIERE personne qui a ce nom et prénom et renvoie son identifiant
        int id = -1;
        try (PreparedStatement pst = con.prepareStatement(
                """
                SELECT id FROM ? WHERE nom = '?' and prenom = '?'
                """)) {
            pst.setString(1,table);
            pst.setString(2,nom);
            pst.setString(3,prenom);
            id = Integer.parseInt(String.valueOf(pst.executeQuery()));
        }
        return id;
    }

    public static Connection connect(String host, int port, String database, String user, String pass) throws ClassNotFoundException, SQLException {
        // teste la présence du driver postgresql
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + database,database, pass);
        // fixe le plus haut degré d'isolation entre transactions
        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return con;
    }

    public static void tabledrop(Connection con, String table) throws SQLException {
        //méthode permettant d'effacer une table de la base de donnée
        try (PreparedStatement pst = con.prepareStatement(
                """
                    DROP TABLE IF EXISTS ?
                    """)) {
                con.setAutoCommit(false);
                pst.setString(1,table);
                pst.executeUpdate();
                con.commit();
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
