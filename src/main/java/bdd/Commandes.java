package bdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import classes.Admin;
import classes.Module;
import classes.Etudiant;
import classes.Personne;
import classes.Semestre;


public class Commandes 
{
    public static void main(String[] args) {
        //pour faire des tests
        try (Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass")) {
            System.out.println("Méthode sans preparedstatement :");
            Commandes.login(con, "PaulineGiroux@insa-strasbourg.fr", "Milita!recreux55");
            List<Module> res = new ArrayList<Module>();
            res= getModule(con, 1, 1);
            for(int i=0;i<res.size();i++){
                System.out.println(res.get(i).toString());
            }
            ArrayList<String> f= getVoeux(con,120);
            for(int i=0;i<f.size();i++){
                System.out.println(f.get(i));
            }


            
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
        try {
            try (Statement st = con.createStatement()) {
                st.executeUpdate("drop table " + nomtable+" cascade");
            }
        } catch (Exception e) {
            System.out.println("table " + nomtable + " inexistante, première éxécution ?");
        }
    }

    public static void dataBaseDrop(Connection con, String nomDataBase) throws SQLException {
        //méthode permettant d'effacer une table de la base de donnée
        try (PreparedStatement pst = con.prepareStatement(
                """
                    DROP TABLE IF EXISTS ?
                    """)) {
            con.setAutoCommit(false);
            pst.setString(1,nomDataBase);
            pst.executeUpdate();
            con.commit();
        }
    }

    public static void SupprimeContrainte(Connection con, String nomtable, String contrainte) throws SQLException {
        //méthode permettant d'effacer les contraintes sur une table
        try (PreparedStatement pst = con.prepareStatement(
                """
                        IF EXISTS (SELECT * FROM ? WHERE CONNECTION_NAME = '?') BEGIN (alter table ? DROP CONSTRAINT '?') END
                    """)){
            //ALTER TABLE ? DROP CONSTRAINT '?' WHERE EXISTS (SELECT * FROM ? WHERE CONNECTION_NAME = '?')
            con.setAutoCommit(false);
            pst.setString(1, nomtable);
            pst.setString(2, contrainte);
            pst.setString(3, nomtable);
            pst.setString(4, contrainte);
            pst.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during AjoutEtudiant");
        }
    }

    //-----------------------------------------------------------------
    //           METHODES DE MODIFICATION DE TABLE
    //-----------------------------------------------------------------

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
            pst.setString(4,(mdp));
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

    public static void AjoutGrpModule(Connection con, int idSemestre,int idGrpModule, int idModule) throws SQLException {
    //méthode permettant d'ajouter un groupe de module
   
        try ( PreparedStatement pst = con.prepareStatement(
                """
                insert into GrpModule (idSemestre,idGroupe,idModule)
                values (?,?,?)
                """)) {
            con.setAutoCommit(false);
            pst.setInt(1, idSemestre);
            pst.setInt(2, idGrpModule);
            pst.setInt(3, idModule);
            pst.executeUpdate();
            con.commit();
        }catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during AjoutGrpModule");
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

    public static void AjoutVoeux(Connection con, int idsemestre, int idetudiant, int idmodule, int numeroVoeux) throws SQLException {
        //méthode permettant d'ajouter un groupe de module
        try ( PreparedStatement pst = con.prepareStatement(
                """
                insert into Voeux (idSemestre,idEtudiant,idModule,numeroVoeux)
                values (?,?,?,?)
                """)) {
            con.setAutoCommit(false);
            pst.setInt(1, (idsemestre));
            pst.setInt(2, (idetudiant));
            pst.setInt(3, (idmodule));
            pst.setInt(4, (numeroVoeux));
            pst.executeUpdate();
            con.commit();
        }catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during AjoutVoeux");
        }
    }

    public static void deleteEtudiant(Connection con, int id) throws SQLException{
        //méthode qui permet de supprimer un étudiant grâce a son id
        if(TrueEtudiantID(con,id)){
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
        } else {
            System.out.println("L'étudiant n'existait pas");
        }
    }

    public static void deleteEtudiant(Connection con, String adresse) throws SQLException{
        //méthode qui permet de supprimer un étudiant grâce a son adresse email qui est unique
        if(TrueEtudiantAdresse(con,adresse)){
            try ( PreparedStatement pst = con.prepareStatement(
                    """
                    delete from Etudiant where adresse = ?
                    """)) {
                con.setAutoCommit(false);
                pst.setString(1, adresse);
                pst.executeUpdate();
                con.commit();
            }catch (SQLException ex) {
                con.rollback();
                System.out.println("ERROR : problem during deleteEtudiant");
            }
        } else {
            System.out.println("L'étudiant n'existait pas");
        }
    }

    public static void deleteAdmin(Connection con, int id) throws SQLException{
        //méthode qui permet de supprimer un admin grâce a son id
        if(TrueAdminID(con,id)){
            try ( PreparedStatement pst = con.prepareStatement(
                    """
                    delete from Admin where id = ?
                    """)) {
                con.setAutoCommit(false);
                pst.setInt(1, id);
                pst.executeUpdate();
                con.commit();
            }catch (SQLException ex) {
                con.rollback();
                System.out.println("ERROR : problem during deleteAdmin");
            }
        } else {
            System.out.println("L'admin n'existait pas");
        }
    }

    public static void deleteAdmin(Connection con, String adresse) throws SQLException{
        //méthode qui permet de supprimer un étudiant grâce a son adresse email qui est unique
        if(TrueAdminAdresse(con,adresse)){
            try ( PreparedStatement pst = con.prepareStatement(
                    """
                    delete from Admin where adresse = ?
                    """)) {
                con.setAutoCommit(false);
                pst.setString(1, adresse);
                pst.executeUpdate();
                con.commit();
            }catch (SQLException ex) {
                con.rollback();
                System.out.println("ERROR : problem during deleteAdmin");
            }
        } else {
            System.out.println("L'admin n'existait pas");
        }
    }

    public static void removeModule(Connection con, int idModule,int idGrp, int idSemestre) throws SQLException{
        //méthode qui permet de supprimer le module d'un groupe
        try ( PreparedStatement pst = con.prepareStatement(
                """
                delete from GrpModule where idSemestre = ? and idGroupe = ? and idModule = ?
                """)) {
            con.setAutoCommit(false);
            pst.setInt(1, idSemestre);
            pst.setInt(2, idGrp);
            pst.setInt(3, idModule);
            pst.executeUpdate();
            con.commit();
        }catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during removeModule");
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

    

    public static void deleteGrpModule(Connection con, int idgrp, int idSemestre) throws SQLException{
        //méthode qui permet de supprimer un groupe de module
        try ( PreparedStatement pst = con.prepareStatement(
                """
                delete from GrpModule where idGroupe = ? and idSemestre = ?
                """)) {
            con.setAutoCommit(false);
            pst.setInt(1, idgrp);
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

    public static void ModificationVoeux(Connection con, int idModule, int idEtudiant, int idSemestre, int numeroVoeux) throws SQLException {
        //méthode qui permet à un étudiant de modifier ses voeux s'il change d'avis, ne fonctionne que pour le semestre actuel (on ne modifie pas l'historique)
        try (PreparedStatement pst = con.prepareStatement(
                """
                UPDATE Voeux SET idModule = ? WHERE idSemestre = ? AND idEtudiant = ? AND numeroVoeux = ?
                """)) {
            pst.setInt(1,idModule);
            pst.setInt(2,idSemestre);
            pst.setInt(3,idEtudiant);
            pst.setInt(4,numeroVoeux);
        }
    }

    //-----------------------------------------------------------------
    //          METHODES DE RECUPERATION D'ELEMENTS DE LA BDD
    //-----------------------------------------------------------------

    public static List<String> getColonne(Connection con, String table, String c) throws SQLException {
        //méthode permettant de recuperer une colonne c de la table "table"
        String r = "select ? from ?";
        try (PreparedStatement st = con.prepareStatement(r)){
            st.setString(1, table);
            st.setString(2, c);
                ResultSet rres = st.executeQuery(
                        ); {
            List<String> res = new ArrayList<>();
            while (rres.next()) {
                res.add(rres.getString(c));
            }
        
            return res;
        }
    }
    }

    public static boolean adresseExiste(Connection con, String adresse) throws SQLException{
        //méthode qui permet de verifier si une adresse mail existe deja dans la bdd
        boolean test=false;
        int count=0;
        String r = "SELECT COUNT(*) from etudiant WHERE adresse = ? ";
        try (PreparedStatement st = con.prepareStatement(r)){
            st.setString(1, adresse);
                ResultSet rres = st.executeQuery(
                        ); {
            while (rres.next()) {
                count = rres.getInt(1);
            }

            if (count >= 1){
                //si il y a une fois ou plus l'adresse 
                test = true;
            }else{
                test=false;
            }
        
        return test;
                        }
    }
    }

    public static void NouvSemestre(Connection con, boolean g1, boolean g2, boolean g3)throws SQLException{
        //méthode qui permet de savoir connaitre l'annee et le numero d'un nouveau semestre
        Semestre sem = new Semestre();
        ArrayList<Semestre> list =new ArrayList<Semestre>();
        try ( Statement st = con.createStatement()) {
            try ( ResultSet rres = st.executeQuery(
                    """
                    select * from semestre order by annee desc, numero desc
                     """)) {
                            //on classe les semestre par annee decroissante : seuls les deux derniers semestres nous interessent
                while (rres.next()) {
                    Semestre s = new Semestre();
                    s.setId(rres.getInt(1));
                    s.setAnnee(rres.getInt(2));
                    s.setNumero(rres.getInt(3));
                    list.add(s);    
                }
                Semestre s1 = list.get(0);
                Semestre s2 = list.get(1);
                if(s1.getAnnee()==s2.getAnnee()){
                    sem.setAnnee(s1.getAnnee()+1);
                    sem.setNumero(1);
                }else{
                    sem.setAnnee(s1.getAnnee());
                    sem.setNumero(2);
                }
                sem.setNg(3);
                sem.setNc(1);
                sem.setId(s1.getId()+1);
                
                AjoutSemestre(con, String.valueOf(sem.getAnnee()) , String.valueOf(sem.getNumero()), String.valueOf(sem.getNg()), String.valueOf(sem.getNc()));
                ArrayList<Integer> groupe = new ArrayList<Integer>();
            System.out.println(sem.toString());
                if(g1==true){
                    groupe=getGrp(con, 1, s1.getId());
                    System.out.println("groupe1");
                    System.out.println(groupe.get(0)+";"+groupe.get(1)+";"+groupe.get(2));

                    for(int i=0; i<3;i++){
                        AjoutGrpModule(con, sem.getId(), 1, groupe.get(i));

                    }
                    groupe.clear();
                        
                }

                if(g2==true){
                    groupe=getGrp(con, 2, s1.getId());
                    System.out.println("groupe2");
                    System.out.println(groupe.get(0)+";"+groupe.get(1)+";"+groupe.get(2));
                    for(int i=0; i<3;i++){
                        AjoutGrpModule(con, sem.getId(), 2, groupe.get(i));

                    }
                    groupe.clear();   
                }

                if(g3==true){
                    groupe=getGrp(con, 3, s1.getId());
                    System.out.println("groupe3");
                    System.out.println(groupe.get(0)+";"+groupe.get(1)+";"+groupe.get(2));
                    for(int i=0; i<3;i++){
                        AjoutGrpModule(con, sem.getId(), 3, groupe.get(i));

                    }
                    groupe.clear();    
                }
            }
        }
    }

    public static ArrayList<Integer> getGrp(Connection con, int idGrp, int idSemestre ) throws SQLException{
        ArrayList<Integer> groupe = new ArrayList<Integer>();
        try (PreparedStatement st = con.prepareStatement(
            """
            select * from GrpModule 
            where idSemestre=? and idGroupe=?
             """    
        )){
            st.setInt(1, idSemestre);
            st.setInt(2, idGrp);
                ResultSet rres = st.executeQuery(
                        ); {
            while (rres.next()) {
                groupe.add(rres.getInt(3));    
            }

        return groupe;
    }
}
}

    
    public static List<Etudiant> getEtudiant(Connection con) throws SQLException {
        //méthode permettant de recuperer une colonne c de la table "table"
        ArrayList<Etudiant> res = new ArrayList<Etudiant>();
        try ( Statement st = con.createStatement()) {
            try ( ResultSet rres = st.executeQuery(
                    """
                    select * from Etudiant
                     """)) {
            while (rres.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setid(rres.getInt(1));
                etudiant.setNom(rres.getString(2));
                etudiant.setPrenom(rres.getString(3));
                etudiant.setAdresse(rres.getString(4));
                etudiant.setMdp(rres.getString(5));
                etudiant.setDateNaiss(rres.getDate(6));
                etudiant.setDisponibilite(rres.getString(7));
                etudiant.setClasse(rres.getString(8));
                res.add(etudiant);
                
            }
        
            return res;
        }
     }
    }

    public static List<Module> getModule(Connection con, int idSemestre, int idGroupe) throws SQLException {
        //méthode permettant de recuperer les modules d'un groupe
        ArrayList<Module> res = new ArrayList<Module>();
        try (PreparedStatement st = con.prepareStatement(
            """
            select * from Module join grpModule on grpModule.idmodule = module.id
            join Semestre on grpModule.idsemestre = Semestre.id
            where Semestre.id=? and grpmodule.idgroupe=?
             """    
        )){
            st.setInt(1, idSemestre);
            st.setInt(2, idGroupe);
                ResultSet rres = st.executeQuery(
                        ); {
            while (rres.next()) {
                Module mod = new Module();
                mod.setId(rres.getInt(1));
                mod.setIntitule(rres.getString(2));
                mod.setDescription(rres.getString(3));
                mod.setNbPlaceMax(rres.getInt(4));
                mod.setNbPlaceMin(rres.getInt(5));
                mod.setClasseacceptee(rres.getString(6));
                res.add(mod);
            }
        
            return res;
        }
     }
    }


    public static int findPersonne(Connection con, String table, String nom, String prenom) throws SQLException {
        //Trouve la PREMIERE personne qui a ce nom et prénom et renvoie son identifiant
        int id = -1;
        try (PreparedStatement pst = con.prepareStatement(
                """
                SELECT id FROM ? WHERE nom = ? and prenom = ?
                """)) {
            pst.setString(1,table);
            pst.setString(2,nom);
            pst.setString(3,prenom);
            id = Integer.parseInt(String.valueOf(pst.executeQuery()));
        }
        return id;
    }
    



    public static Personne login(Connection con, String adresse, String mdp) throws SQLException {
        //permet de verifier si une adresse mail et un mdp appartiennent a la bdd
        //mdp = security.CreateHash(mdp);
        String r = "SELECT * from etudiant WHERE adresse = ? and mdp = ?";
        int test= 3; 
        Etudiant etudiant = new Etudiant();
        Admin admin = new Admin();
        try (PreparedStatement p = con.prepareStatement(r)) {
            p.setString(1, adresse);
            p.setString(2, mdp);
            try ( ResultSet tla = p.executeQuery(
                )) {
                while (tla.next()) {
                    etudiant.setid(tla.getInt(1));
                    etudiant.setNom(tla.getString(2));
                    etudiant.setPrenom(tla.getString(3));
                    etudiant.setAdresse(tla.getString(4));
                    etudiant.setMdp(tla.getString(5));
                    etudiant.setDateNaiss(tla.getDate(6));
                    etudiant.setDisponibilite(tla.getString(7));
                    etudiant.setClasse(tla.getString(8));
                    
                    test=1;
                    System.out.println("test etudiant"+test);
                }
                if(test==3){
                    //si aucune adresse et mdp ne correspond a ceux d'un etudiant on regarde alors chez les admin
                    String r2 = "SELECT * from admin WHERE adresse = ? and mdp = ?"; 
                    try (PreparedStatement p2 = con.prepareStatement(r2)) {
                        p2.setString(1, adresse);
                        p2.setString(2, mdp);
                        try ( ResultSet ta = p2.executeQuery(
                            )) {
                            while (ta.next()) {
                                admin.setid(ta.getInt(1));
                                admin.setNom(ta.getString(2));
                                System.out.println(admin.getNom());
                                admin.setPrenom(ta.getString(3));
                                admin.setAdresse(ta.getString(4));
                                admin.setMdp(ta.getString(5));
                                
                                test=2;
                                System.out.println("test admin"+test);
                            }
                        }
                    }
                }   
            }
            switch(test){
            case 1:
                System.out.println("Un étudiant se connecte");
                System.out.println(test);
            return etudiant;
             
            case 2:
                System.out.println("Un admin se connecte");
                System.out.println(test);
            return admin;
        
            case 3:
            break;
            }
        return admin;  
        }  
    }


    public static boolean TrueEtudiantID(Connection con, int id){
        //Vérifier qu'un étudiant existe
        //Vérifier qu'un admin existe
        int count = 0;
        boolean test=false;
        String r="SELECT COUNT(*) FROM etudiant WHERE id = ?";
        try (PreparedStatement st = con.prepareStatement(r)){
            st.setInt(1, id);
                ResultSet rres = st.executeQuery(
                        ); {
            while (rres.next()) {
                count = rres.getInt(1);
            }

            if (count >= 1){
                //si il y a une fois ou plus l'adresse 
                test = true;
            }else{
                test=false;
            }
        
        
                        }
    }catch (SQLException e) {

        System.out.println("Error : Commandes.java Trueetudiant(con,id) "+e);
    }
    return test;
    }

    public static boolean TrueEtudiantAdresse(Connection con, String adresse){
        //Vérifier qu'un étudiant existe
        int count = 0;
        boolean test=false;
        String r="SELECT COUNT(*) FROM etudiant WHERE adresse = ?";
        try (PreparedStatement st = con.prepareStatement(r)){
            st.setString(1, adresse);
                ResultSet rres = st.executeQuery(
                        ); {
            while (rres.next()) {
                count = rres.getInt(1);
            }

            if (count >= 1){
                //si il y a une fois ou plus l'adresse 
                test = true;
            }else{
                test=false;
            }
        
        
                        }
    }catch (SQLException e) {

        System.out.println("Error : Commandes.java Trueetudiant(con,id) "+e);
    }
    return test;
    }

    public static void ModulesDuSemestre(Connection con, int annee, int numero) throws SQLException {
        //méthode qui permet à un étudiant ou un admin de voir la liste des modules et leur groupe
        final String requete ="SELECT Modules.id FROM Semestres JOIN GrpModule ON GrpModule.idSemestre = Semestres.id Join Modules ON Modules.id = GrpModule.idGroupe WHERE Semestres.annee = '"+annee+"' and Semestres.numero = '"+numero+"'";
        
        try ( Statement st = con.createStatement()) {
            try ( ResultSet res = st.executeQuery(requete)) {
                while (res.next()) {
                    System.out.println(res.getString(1));
                }
            }
        }
    }
    

    public static boolean TrueAdminID(Connection con, int id) throws SQLException{
        //Vérifier qu'un admin existe
        int count = 0;
        boolean test=false;
        String r="SELECT COUNT(*) FROM Admin WHERE id = ?";
        try (PreparedStatement st = con.prepareStatement(r)){
            st.setInt(1, id);
                ResultSet rres = st.executeQuery(
                        ); {
            while (rres.next()) {
                count = rres.getInt(1);
            }

            if (count >= 1){
                //si il y a une fois ou plus l'adresse 
                test = true;
            }else{
                test=false;
            }
        
        
                        }
    }catch (SQLException e) {

        System.out.println("Error : Commandes.java TrueAdmin(con,id) "+e);
    }
    return test;
    }
    public static boolean TrueAdminAdresse(Connection con, String adresse) throws SQLException{
        //Vérifier qu'un admin existe
        int count = 0;
        boolean test=false;
        String r="SELECT COUNT(*) FROM Admin WHERE adresse = ?";
        try (PreparedStatement st = con.prepareStatement(r)){
            st.setString(1, adresse);
                ResultSet rres = st.executeQuery(
                        ); {
            while (rres.next()) {
                count = rres.getInt(1);
            }

            if (count >= 1){
                //si il y a une fois ou plus l'adresse 
                test = true;
            }else{
                test=false;
            }
        
        
                        }
    }catch (SQLException e) {

        System.out.println("Error : Commandes.java TrueAdmin(con,id) "+e);
    }
    return test;
    }

    public static boolean TrueGrpModule(Connection con, int idSemestre, int idGrpModule, int idModule){
        //Vérifier qu'un grpmod existe
        int res = 0;
        boolean test=false;
        String r = "SELECT COUNT(*) FROM GrpModule WHERE GrpModule.idSemestre = ? and GrpModule.idGroupe= ? and GrpModule.idModule = ?";
        System.out.println("ici");
        try (PreparedStatement pst = con.prepareStatement(r)){
            pst.setInt(1,idSemestre);
            pst.setInt(2,idGrpModule);
            pst.setInt(3,idModule);
                ResultSet rres = pst.executeQuery(
                        ); {
            while (rres.next()) {
                res = rres.getInt(1);
            }
            if (res >= 1){
                test=true;
            } else {
                test= false;
            }
        } 
        }catch (SQLException e) {
            
            System.out.println("Error : Commandes.java TrueGrpModule(con,id) "+e);
        
    }
        return test;
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

    public static List<Integer> EtudiantDispo(Connection con, String semestre) throws SQLException {
        //méthode qui permet d'avoir la liste des étudiants pouvant s'inscrire à un semestre. Pour ça, finir la disponibilité
        final String requete ="SELECT Etudiant.id,Etudiant.disponibile FROM Etudiant";
        try (Statement st = con.createStatement()) {
            try (ResultSet rset = st.executeQuery(requete)) {
                List<Integer> res = new ArrayList<>();
                while (rset.next()) {
                    if (rset.getString(2).indexOf(semestre) != -1) {
                        res.add(rset.getInt(1));
                    }
                }
                return res;
            }
        }
    }

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
        }
    }

    public static ResultSet historique(Connection con, int idEtud) throws SQLException {
        //méthode pour récuperer l'historique des voeux de modules d'un étudiant
        try (PreparedStatement pst = con.prepareStatement(
                """
                SELECT Voeux.idSemestre,Voeux.idModule,Voeux.numeroVoeux FROM Voeux JOIN Etudiants ON Etudiants.id = Voeux.idEtudiant WHERE Etudiant.id = ?
                """)) {
            pst.setInt(1,idEtud);
            return pst.executeQuery();
        }
    }

    public static List<Integer> ModulesPossible(Connection con, int idEtudiant, int idSemestre) throws SQLException {
        //méthode qui permet à un étudiant de voir la liste des modules auxquels il peut s'inscrire
        String classeEtudiant = null;
        List<Integer> res = new ArrayList<>();
        final String requeteA ="SELECT classe FROM Etudiants WHERE id = "+idEtudiant; // pas besoin de passer par "?" car idEtudiant est de type int
        try (Statement st = con.createStatement()) {
            try (ResultSet rset = st.executeQuery(requeteA)) {
                classeEtudiant = rset.getString(1);
            }
        }
        final String requeteB ="SELECT Modules.idModule,Modules.classeacceptee FROM Modules JOIN GrpModule ON GrpModule.idGroupe = Modules.id WHERE GrpModule.idSemestre = "+idSemestre; // pas besoin de passer par "?" car idSemestre est de type int
        try (Statement st = con.createStatement()) {
            try (ResultSet rset = st.executeQuery(requeteB)) {
                while (rset.next()) {
                    if(rset.getString(2).indexOf(classeEtudiant) != -1){
                        res.add(rset.getInt(1));
                    }
                }
            }
        }
        return res;
    }

    public static int getidsem(Connection con) throws SQLException, ClassNotFoundException {
        try ( Statement st = con.createStatement()) {
            try ( ResultSet rres = st.executeQuery(
                """
                SELECT MAX(id) FROM semestre
                """)) {
                while (rres.next()) {
                    return rres.getInt(1);
                }
            }
        }
        return 0;
    }
    
    public static ArrayList<String> getVoeux(Connection con, int idEtudiant) throws SQLException{
        //méthode permettant de récupérer les voeux de l'étudiant pour tout les semestres
        ArrayList<String> voeux = new ArrayList<String>();
        try (PreparedStatement st = con.prepareStatement(
            """
            SELECT Module.intitule from Module 
            JOIN Voeux ON Module.id=Voeux.idModule
            JOIN Semestre ON Voeux.idSemestre=Semestre.id
            JOIN Etudiant ON Voeux.idEtudiant=Etudiant.id
            WHERE Etudiant.id= ?
            ORDER BY Semestre.annee desc, Semestre.numero desc
             """    
        )){
            st.setInt(1, idEtudiant);
            
            ResultSet rres = st.executeQuery(
                        ); {
            while (rres.next()) {
                voeux.add(rres.getString(1));
                
            }
        
            return voeux;
        }
     }
    }
}