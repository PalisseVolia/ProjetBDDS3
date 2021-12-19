package com.example.application.bdd;

import java.sql.*;
import java.util.*;


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

    //-----------------------------------------------------------------
    //           METHODES D'AJOUT D'ELEMENTS DANS UNE TABLE 
    //-----------------------------------------------------------------

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
    
    public static void AjoutAdmin(Connection con,  String nom, String prenom, String adresse, String mdp, String date) throws SQLException
    {
        //méthode permettant d'ajouter un administrateur à la base de donnée
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

    public static void AjoutSemestre(Connection con, String annee, String numero, String ng, String nc) throws SQLException
    {
        //méthode permettant d'ajouter un module à la base de donnée
        try (PreparedStatement pst = con.prepareStatement(
            """
                    INSERT INTO Semestre (annee,numero, ng, nc)
                    VALUES (?,?,?,?)
                    """)){
        con.setAutoCommit(false);
        pst.setInt(1, Integer.parseInt(annee));
        pst.setInt(2, Integer.parseInt(numero));
        pst.setInt(3, Integer.parseInt(ng));
        pst.setInt(4, Integer.parseInt(nc));
        pst.executeUpdate();
        con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during AjoutSemestre");
        }
    }

    public static void AjoutGrpModule(Connection con, String idsemestre, String idGrp, String module) throws SQLException {
        //méthode permettant d'ajouter un groupe de module
        try ( PreparedStatement pst = con.prepareStatement(
                """
                insert into GrpModule (idSemestre,idGroupe,idModule) values (?,?,?)
                """)) {
            con.setAutoCommit(false);
            pst.setInt(1, Integer.parseInt(idsemestre));
            pst.setInt(2, Integer.parseInt(idGrp));
            pst.setInt(3, Integer.parseInt(module));
            pst.executeUpdate();
            con.commit();
        }catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during AjoutGrpModule");
        }
    }

    public static void AjoutVoeux(Connection con, String idsemestre, String idetudiant, String idmodule) throws SQLException {
        //méthode permettant d'ajouter un groupe de module
        try ( PreparedStatement pst = con.prepareStatement(
                """
                insert into Voeux (idSemestre,idEtudiant,idModule) values (?,?,?)
                """)) {
            con.setAutoCommit(false);
            pst.setInt(1, Integer.parseInt(idsemestre));
            pst.setInt(2, Integer.parseInt(idetudiant));
            pst.setInt(3, Integer.parseInt(idmodule));
            pst.executeUpdate();
            con.commit();
        }catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during AjoutVoeux");
        }
    }



    //-----------------------------------------------------------------
    //          METHODES DE RECUPERATION D'ELEMENTS DE LA BDD 
    //-----------------------------------------------------------------

    public static List<String> getColonne(Connection con, String table, String c) throws SQLException {
        //méthode permettant de recuperer une colonne c de la table "table"
        try (Statement st = con.createStatement();
                ResultSet rres = st.executeQuery(
                        "select "+ c + " from " + table)) {
            List<String> res = new ArrayList<>();
            while (rres.next()) {
                res.add(rres.getString(c));
            }
            return res;
        }
    }

    public static void afficheModTest(Connection con) throws SQLException {
        //méthode test : affiche dans la console tous les modules du grp 1 du s2 de 2019
        try ( Statement st = con.createStatement()) {
             try ( ResultSet tla = st.executeQuery(
                    """
                    select intitule from Module join grpModule on grpModule.idmodule = module.id
                    join Semestre on grpModule.idsemestre = Semestre.id 
                    where Semestre.id=2 and grpmodule.idgroupe=1

                     """)) {
                System.out.println("liste des modules :");
                System.out.println("------------------");
                while (tla.next()) {
                     System.out.println(tla.getString(1));
                 }
            }
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
   
