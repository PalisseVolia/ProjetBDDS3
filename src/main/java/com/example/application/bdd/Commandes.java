package com.example.application.bdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Commandes 
{
    public static void main(String[] args) {
        //pour faire des tests
        try (Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass")) {
            Commandes.afficheModTest(con);
            System.out.println("Méthode sans preparedstatement :");
            Commandes.login(con, "PaulineGiroux@insa-strasbourg.fr", "Milita!recreux55");
            Commandes.deleteEtudiant(con, 2);
        } catch (Exception err) {
            System.out.println("Error : Commandes.java main() "+err);
        }

    }

    //-----------------------------------------------------------------
    //           METHODES GENERALES
    //-----------------------------------------------------------------

    public static Connection connect(String host, int port, String database, String user, String pass) throws ClassNotFoundException, SQLException {
        // teste la présence du driver postgresql
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + database,database, pass);
        // fixe le plus haut degré d'isolation entre transactions
        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return con;
    }

    public static void tabledrop(Connection con, String nomtable) throws SQLException {
        //méthode permettant d'effacer une table de la base de donnée
        try (PreparedStatement pst = con.prepareStatement(
                """
                    DROP TABLE IF EXISTS ?
                    """)) {
            con.setAutoCommit(false);
            pst.setString(1,nomtable);
            pst.executeUpdate();
            con.commit();
        }
    }

    public static void SupprimeContrainte(Connection con, String nomtable, String contrainte) throws SQLException {
        //méthode permettant d'effacer les contraintes sur une table
        try {
            try (Statement st = con.createStatement()) {
                st.executeUpdate("alter table " + nomtable + "drop constraint " + contrainte);
            }
        } catch (Exception e) {
            System.out.println("Erreur dans la suppression de la contrainte "+ contrainte);
        }
    }

    public static void login(Connection con, String adresse, String mdp) throws SQLException {
        //permet de verifier si une adresse mail et un mdp appartiennent a la bdd
        // String mdphash = security.CreateHash(mdp);
        final String requete ="SELECT * from etudiant WHERE adresse = '"+adresse+"' and mdp = '"+mdp+"'";
        try ( Statement st = con.createStatement()) {
            try ( ResultSet tla = st.executeQuery(
                    requete)) {

                System.out.println("liste des Etudiant :");
                System.out.println("------------------");
                while (tla.next()) {
                    System.out.println(tla.getString(1));
                    System.out.println(tla.getString(2));
                    System.out.println(tla.getString(3));
                }
            }
        }

    }

    //-----------------------------------------------------------------
    //           METHODES DE MODIFICATION DE TABLE
    //-----------------------------------------------------------------

    public static void AjoutPersonne(Connection con, String nom, String prenom, String dateNaissance, String adresseMail, String mdp) throws SQLException {
        //Ajout d'une personne dans la table "Personnes"
        String mdpHash = security.CreateHash(mdp);
        if(nom.length()<200 || prenom.length()<200 || adresseMail.length()<200 || mdpHash.length()<200){
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
            if(nom.length()>=200){System.out.println("ERROR: AjoutPersonne : nom trop long");}
            if(prenom.length()>=200){System.out.println("ERROR: AjoutPersonne : prenom trop long");}
            if(adresseMail.length()>=200){System.out.println("ERROR: AjoutPersonne : adresseMail trop long");}
            if(mdpHash.length()>=200){System.out.println("ERROR: AjoutPersonne : mdpHash trop long");}
        }
    }

    public static void AjoutEtudiant(Connection con, String nom, String prenom, String adresse, String mdp, String date, String dispo, String classe) throws SQLException {
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

    public static void AjoutAdmin(Connection con,  String nom, String prenom, String adresse, String mdp, String date) throws SQLException {
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

    public static void AjoutModule(Connection con, String intitule, String description, String nbplacemax, String nbplacemin, String classeacceptee) throws SQLException {
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

    public static void AjoutSemestre(Connection con, String annee, String numero, String ng, String nc) throws SQLException {
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

    public static void deleteEtudiant(Connection con, int id) throws SQLException{
        //méthode qui permet de supprimer un étudiant grace a son id


        try ( PreparedStatement pst = con.prepareStatement(
                """
                delete from Etudiant where id = ?
                """)) {
            con.setAutoCommit(false);
            pst.setInt(1, id);
            pst.executeUpdate();
            con.commit();
        }catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during deleteEtudiant");
        }
    }

    public static void deleteModule(Connection con, int id) throws SQLException{
        //méthode qui permet de supprimer un module grâce à son id
        try ( PreparedStatement pst = con.prepareStatement(
                """
                delete from Module where id = ?
                """)) {
            con.setAutoCommit(false);
            pst.setInt(1, id);
            pst.executeUpdate();
            con.commit();
        }catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during deleteModule");
        }


    }

    public static void deleteGroupe(Connection con, int id) throws SQLException{
        //méthode qui permet de supprimer un groupe de module
        try ( PreparedStatement pst = con.prepareStatement(
                """
                delete from GrpModule where idGroupe = ?
                """)) {
            con.setAutoCommit(false);
            pst.setInt(1, id);
            pst.executeUpdate();
            con.commit();
        }catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during deleteGroupe");
        }


    }

    public static int findSemestre(Connection con, int annee, int numero) throws SQLException {
        int idSemestre = -1;
        try (PreparedStatement pst = con.prepareStatement(
                """
                SELECT id FROM Semestres WHERE annee = '?' and numero = '?'
                """)) {
            pst.setInt(1,annee);
            pst.setInt(2,numero);
            idSemestre = Integer.parseInt(String.valueOf(pst.executeQuery()));
        }
        return idSemestre; // Gérer les cas où l'on a rien trouvé
    }

    //TODO méthodes à faire

    public static void ModificationGrp(Connection con){
        //méthode qui permet de à un admin de modifier les groupes de modules


    }

    public static void ModificationVoeux(Connection con, int idEtudiant, int idSemestre){
        //méthode qui permet à un étudiant de modifier ses voeux s'il change d'avis, ne fonctionne que pour le semestre actuel (on ne modifie pas l'historique)
    }

    //-----------------------------------------------------------------
    //          METHODES DE RECUPERATION D'ELEMENTS DE LA BDD
    //-----------------------------------------------------------------

    public static List<String> getColonne(Connection con, String table, String c) throws SQLException {
        //méthode permettant de récuperer une colonne c de la table "table"
        try (PreparedStatement pst = con.prepareStatement(
                """
                SELECT ? FROM ?
                """)) {
            pst.setString(1,c);
            pst.setString(2,table);
            ResultSet rres = pst.executeQuery();
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

    public static List<String> ModulesDuSemestre(Connection con, int idSemestre) throws SQLException {
        //méthode qui permet à un étudiant ou un admin de voir la liste des modules et leur groupe
        final String requete ="SELECT Modules.id FROM Semestres JOIN GrpModule ON GrpModule.idSemestre = Semestres.id Join Modules ON Modules.id = GrpModule.idGroupe WHERE Semestres.id '"+idSemestre+"'";
        try ( Statement st = con.createStatement()) {
            try ( ResultSet rres = st.executeQuery(requete)) {
                List<String> res = new ArrayList<>();
                int i = 1;
                while (rres.next()) {
                    res.add(rres.getString(i));
                    i++;
                }
                return res;
            }
        }

    }

    //TODO méthodes à faire

    public static void ModulesPossible(Connection con){
        //méthode qui permet à un étudiant de voir la liste des modules auxquels il peut s'inscrire

    }

    public static void EtudiantDispo(Connection con){
        //méthode qui permet d'avoir la liste des étudiants pouvant s'inscrire à un semestre. Pour ça, finir la disponibilité
    }

    public static void historique(Connection con, int idEtud){
        //méthode pour récuperer l'historique des voeux de modules d'un étudiant
    }

}