/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tables;

import classes.Etudiant;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author thiba
 */
public class main {
    //classe qui permet de creer les tables dans la bdd
    
        
     public static final String[][] foreignKeys = new String[][]{
        {"Module", "responsable", "Personne", "id"},
        {"Ouvert", "module", "Module", "id"},
        {"Ouvert", "semestre", "Semestre", "id"},
        {"Inscription", "etudiant", "Personne", "id"},
        {"Inscription", "module", "Module", "id"},
        {"Inscription", "semestre", "Semestre", "id"}
    };
     public static Connection connect() throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres");
    }
public static void createEtudiant(Connection con) throws SQLException {
        List<String> noms = Etudiant.noms();
        List<String> prenoms = Etudiant.prenoms();
        List<String> adresse = Etudiant.adresse();
        List<String> mdp = Etudiant.mdp();
        List<String> dateNaiss = Etudiant.datenaiss();
        ArrayList<Date> Date = new ArrayList<Date>();
        for(int i=0; i<dateNaiss.size(); i++){
            
            final String SEPARATEUR = "/";
            String conte = dateNaiss.get(i);
 
            String mots[] = conte.split(SEPARATEUR);
            for(int k=0;k<mots.length;k++){
                System.out.print(mots[k]+"/");
            }
            System.out.println("");
            Date d= new Date(Integer.valueOf(mots[1]),Integer.valueOf(mots[0]),Integer.valueOf(mots[2]));
            Date.add(d);
                
            
        }
       List<String> disponibilite = Etudiant.dispo();
        
        
        try (PreparedStatement pst = con.prepareStatement(
                """
               INSERT INTO Etudiant (nom,prenom,adresse,mdp,dateNaissance)
               VALUES (?,?,?,?,?)
               """)) {
            con.setAutoCommit(false);

            for (int i = 0; i < noms.size(); i++) {
                Etudiant t = new Etudiant();
                    pst.setString(1, noms.get(i));;
                System.out.print(noms.get(i)+";");
                t.setNom(noms.get(i));
                    pst.setString(2, prenoms.get(i));
                System.out.print(prenoms.get(i)+";");
                t.setPrenom(prenoms.get(i));
                    pst.setString(3, adresse.get(i));
                System.out.print(adresse.get(i)+";");
                t.setAdresse(adresse.get(i));
                    pst.setString(4, mdp.get(i));
                System.out.print(mdp.get(i)+";");
                t.setMdp(mdp.get(i));
                t.setDateNaiss(Date.get(i));
                pst.setDate(5, Date.get(i));
                //boolean test = boolean.valueOf(disponibilite.get(i)) ;
                //pst.setBoolean(6, test );
                System.out.println("");
                pst.executeUpdate();
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during createPersonnesAlea");
            throw ex;
        }
        
        try (PreparedStatement pst = con.prepareStatement(
                """
               INSERT INTO Module (intitule,description,nbrplacemin,nbrplacemax,classeacceptee)
               VALUES (?,?,?,?,?)
               """)) {
            con.setAutoCommit(false);

            for (int i = 0; i < noms.size(); i++) {
                Etudiant t = new Etudiant();
                    pst.setString(1, noms.get(i));;
                System.out.print(noms.get(i)+";");
                t.setNom(noms.get(i));
                    pst.setString(2, prenoms.get(i));
                System.out.print(prenoms.get(i)+";");
                t.setPrenom(prenoms.get(i));
                    pst.setString(3, adresse.get(i));
                System.out.print(adresse.get(i)+";");
                t.setAdresse(adresse.get(i));
                    pst.setString(4, mdp.get(i));
                System.out.print(mdp.get(i)+";");
                t.setMdp(mdp.get(i));
                t.setDateNaiss(Date.get(i));
                pst.setDate(5, Date.get(i));
                //boolean test = boolean.valueOf(disponibilite.get(i)) ;
                //pst.setBoolean(6, test );
                System.out.println("");
                pst.executeUpdate();
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during createPersonnesAlea");
            throw ex;
        }
    }    
public static void creeTableEtudiant(Connection con) throws SQLException{
   try( Statement st = con.createStatement()){
       st.executeUpdate(
        """
       create table Etudiant(
       id integer not null primary key
       generated always as identity, 
       nom VARCHAR(30), prenom VARCHAR(30), adresse VARCHAR(100),
       mdp VARCHAR(30), dateNaissance DATE)
                               )
       """
               );

   }; 
}  

public static void creeTableModule(Connection con) throws SQLException{
   try( Statement st = con.createStatement()){
       st.executeUpdate(
         """
       create table Module(
       id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
       intitule VARCHAR(50) NOT NULL,
       description TEXT,
       nbrplacemin INTEGER,
       nbrplacemax INTEGER,
       classeacceptee VARCHAR(50),
                         )  
       """);
   
   }
   
}  


public static void deleteSchema(Connection con,  String table) throws SQLException {
        try (Statement st = con.createStatement()) {


          
                st.executeUpdate("DROP TABLE " + table);
                
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
            System.out.println("ERROR : problem during deleteSchema");
            throw ex;
        }

    }

 public static void testConnect() {
        Connection con = null;
        try {
            // teste la présence du driver postgresql
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "pass");

            con.setAutoCommit(false);
            Statement st = con.createStatement();
            st.executeUpdate(
       """
       create table Etudiant(
       id integer not null primary key
       generated always as identity, 
       nom VARCHAR(30), prenom VARCHAR(30), adresse VARCHAR(100),
       mdp VARCHAR(30),
       dateNaissance DATE
                               )
       """);
            st.executeUpdate(
                """
       create table Module(
       id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
       intitule VARCHAR(50) NOT NULL,
       description TEXT,
       nbrplacemin INTEGER,
       nbrplacemax INTEGER,
       classeacceptee VARCHAR(50),
                         )  
       """);
        
            
            con.commit();
        } catch (Exception ex) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {}
            }
            throw new Error(ex);
        }

    }


    public static void main(String[] args) {
        
        try (Connection con = SGBD.connectionLocalPostgresql()) {
           
          con.setAutoCommit(false);
          testConnect();
          //deleteSchemaEtudiant(con,true, "etudiant");
          createEtudiant(con);
         
        } catch (SQLException ex) {
            throw new Error(ex);
        }
    }
        
        
    
}
