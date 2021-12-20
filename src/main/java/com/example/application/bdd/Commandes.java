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

    public static void SupprimeContrainte(Connection con, String nomtable, String contrainte) throws SQLException {
        //méthode permettant d'effacer une table de la base de donnée
        try {
            try (Statement st = con.createStatement()) {
                st.executeUpdate("alter table " + nomtable + "drop constraint " + contrainte);
            }
        } catch (Exception e) {
            System.out.println("Erreur dans la suppression de la contrainte "+ contrainte);
        }
    }

    //-----------------------------------------------------------------
    //           METHODES DE MODIFICATION DE TABLE 
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
                insert into GrpModule (idSemestre,idGroupe,idModule) 
                values (?,?,?)
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
                insert into Voeux (idSemestre,idEtudiant,idModule) 
                values (?,?,?)
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

    //TODO méthodes à faire

    public static void deleteEtudiant(Connection con){
        //méthode qui permet de supprimer un étudiant


    }

    public static void deleteModule(Connection con){
        //méthode qui permet de supprimer un module


    }

    public static void deleteGroupe(Connection con){
        //méthode qui permet de supprimer un groupe de module


    }

    public static void ModificationGrp(Connection con){
        //méthode qui permet de à un admin de modifier les groupes de modules


    }

    public static void ModificationVoeux(Connection con){
        //méthode qui permet de à un étudiant de modifier ses voeux si il change d'avis
        //ne fonctionne que pour le semestre actuel (on ne modifie pas l'historique)


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

    //TODO les méthodes a partir d'ici sont à faire j'ai mis void pour chacune mais faudra changer

    public static void login(Connection con, String adresse, String mdp) throws SQLException {
        //ne marche pas pour l'instant
        //permet de verifier si une adresse mail et un mdp appartiennent a la bdd
        System.out.println("Requete :");
        System.out.println("SELECT * from etudiant WHERE adresse = '"+adresse+"' and mdp = '"+mdp+"'");
        try ( Statement st = con.createStatement()) {
             try ( ResultSet tla = st.executeQuery(
                    """
                    SELECT * from etudiant WHERE adresse = '"+adresse+"' and mdp = '"+mdp+"'

                     """)) {
                
                System.out.println("liste des Etudiant :");
                System.out.println("------------------");
                while (tla.next()) {
                    int id = tla.getInt("id");
                    System.out.println(id);
                    String nom = tla.getString("nom");
                    String prenom= tla.getString("prenom");  
                    System.out.println("id : "+id+ "nom : "+nom+" ; prenom : "+ prenom);
                 }
            }
        }

    }

    public static void ModulesDuSemestre(Connection con){
        //méthode qui permet à un étudiant ou un admin de voir la liste des modules et leur groupe


    }

    public static void ModulesPossible(Connection con){
        //méthode qui permet à un étudiant de voir la liste des modules auxquels il peut s'inscrire 


    }

    public static void EtudiantDispo(Connection con){
        //méthode qui permet d'avoir la liste des étudiants pouvant s'inscrire à un semestre
        //pour ca, finir la disponibilite


    }


    public static void historique(Connection con, int idEtud){
        //méthode pour recuperer l'historique des modules d'un etudiant


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
   
