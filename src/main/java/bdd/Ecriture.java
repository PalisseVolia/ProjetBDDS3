package bdd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// =======================================================================================
// Classe permettant l'écriture du fichier texte d'historique
// =======================================================================================

public class Ecriture {
    public static void main(String[] args) {
        //à run pour initialiser la bdd
        try (Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass")) {
            ecrireFichier(con, 4, "C:/Users/Volia/Desktop");
        } catch (Exception err) {
            System.out.println("Error : Commandes.java main() "+err);
        }

    }

    public static void ecrireFichier (Connection con, int idSemestre, String chemin) throws SQLException {
        try {
            BufferedWriter sauv = new BufferedWriter(new FileWriter(chemin + "/Choix_Voeux_idSemestre_"+idSemestre+".txt",false));
            /*Ecriture de NG et NC
             *
             * NG
             * NC
             *
             * */
            final String requeteA ="SELECT ng,nc from Semestre WHERE id = "+idSemestre;
            try ( Statement st = con.createStatement()) {
                try ( ResultSet res = st.executeQuery(requeteA)) {
                    while(res.next()){
                        sauv.write(res.getInt(1)+" ");
                        sauv.newLine();
                        sauv.write(res.getInt(2)+" ");
                        sauv.newLine();
                    }
                }
            }
            catch (IOException e) {
                System.out.println("ERROR : ecricreFichier : Ecriture de NG et NC : ecriture impossible : "+e);
            }
            /*Description des Modules :
             *
             * MODULES
             * Modules.id ; GrpModule.id
             * Modules.id ; GrpModule.id
             * Modules.id ; GrpModule.id
             * FINMODULES
             *
             * */
            sauv.write("MODULES");
            sauv.newLine();
            final String requeteB ="SELECT DISTINCT Module.id, GrpModule.idGroupe from Semestre Join GrpModule ON  GrpModule.idSemestre = "+ idSemestre +" Join Module ON GrpModule.idModule = Module.id order by GrpModule.idGroupe";
            try ( Statement st = con.createStatement()) {
                try ( ResultSet res = st.executeQuery(requeteB)) {
                    while(res.next()) {
                        sauv.write(res.getInt(1) + ";"+ res.getInt(2) );
                        sauv.newLine();
                    }
                }
            }
            catch (IOException e) {
                System.out.println("ERROR : ecricreFichier : Ecriture de NG et NC : ecriture impossible : "+e);
            }
            sauv.write("FINMODULES");
            sauv.newLine();
            /* Description des choix
             *
             * CHOIX
             * idEtudiant;choixA{idModule},choixB;{Pour chaque étudiants}
             * FINCHOIX
             *
             * */
            sauv.write("CHOIX");
            sauv.newLine();
            ArrayList<Integer> nb = Commandes.etudiantvoeux(con, idSemestre);
            for(int i=0;i<nb.size();i++){
                final String requeteC ="SELECT Voeux.idModule from Voeux where idSemestre= "+idSemestre+ "and idEtudiant = " +nb.get(i);
                try ( Statement st = con.createStatement()) {
                    try ( ResultSet res = st.executeQuery(requeteC)) {
                        ArrayList<Integer> v= new ArrayList<Integer>();
                        while(res.next()) {
                            v.add(res.getInt(1));
                        }
                        sauv.write(nb.get(i) + ";");
                        for(int j=0;j<v.size();j++){
                            sauv.write(v.get(j)+";");
                        }
                        sauv.newLine();
                    }
                }
                catch (IOException e) {
                    System.out.println("ERROR : ecricreFichier : Ecriture de NG et NC : ecriture impossible : "+e);
                }
            }
            sauv.write("FINCHOIX");
            sauv.newLine();
            sauv.close();
        }
        catch (IOException err) {System.out.println("ERROR : ecricreFichier : impossible de créer le fichier d'écriture");}
    }


    private static void MakeListeidGroupeDistinct(List<Integer> ListeidGroupeDistinct, List<Integer> ListeidGroupe){
        int p;
        ListeidGroupeDistinct.add(ListeidGroupe.get(0));
        for(int i = 0 ; i < ListeidGroupe.toArray().length ; i++){
            for(p = 0 ; p < ListeidGroupeDistinct.toArray().length ; p++){
                if(ListeidGroupeDistinct.get(p) == ListeidGroupe.get(i)){
                    p = ListeidGroupeDistinct.toArray().length + 1;
                }
            }
            if ( p == ListeidGroupeDistinct.toArray().length) {
                ListeidGroupeDistinct.add(ListeidGroupe.get(i));
            }
        }
    }

    private static void CreateLineEtudiant(String idEtudiant , List<Integer> listeIdGroupeDistinct, List<Integer> listeIdGrpModule , List<Integer> listePositions , List<Integer> listeNumeroVoeux , List<Integer> listeIdModule , BufferedWriter sauv ) throws IOException {// Si on change d'étudiant, on va créer une nouvelle ligne
        String line = idEtudiant + ";";
        MakeListeidGroupeDistinct(listeIdGroupeDistinct,listeIdGrpModule);

        // Ajouter les voeux de module dans l'ordre pour chaque groupe de module
        // Séparer le dernier groupe pour ne pas avoir de ";" final
        for (int i = 0 ; i < listeIdGroupeDistinct.toArray().length-1 ; i++){
            listePositions.clear();
            // On cherche les positions pour un groupe donnée dans la liste
            for (int p = 0 ; p < listeIdGrpModule.toArray().length ; p++){
                if(listeIdGroupeDistinct.get(i) == listeIdGrpModule.get(p)){
                    listePositions.add(p);
                }
            }
            // Remplir la ligne des voeux dans l'ordre croissant des voeux
            // Garder le dernier voeu à la fin pour ne pas mettre la virgule
            for (int nv = 0 ; nv < listePositions.toArray().length-1 ; nv++){ //nv  = Numero Voeux
                for (int p = 0 ; p < listePositions.toArray().length ; p++){ // p = position
                    if(listeNumeroVoeux.get(listePositions.get(p)) == nv+1){
                        line += listeIdModule.get(listePositions.get(p)) + ",";
                    }
                }
            }
            for (int p = 0 ; p < listeIdGrpModule.toArray().length ; p++){
                if(listeNumeroVoeux.get(listePositions.get(p)) == listePositions.toArray().length){
                    line += listeIdModule.get(listePositions.get(p)) + ";";
                }
            }
        }

        // Faire le dernier groupe
        listePositions.clear();
        for (int p = 0 ; p < listeIdGrpModule.toArray().length ; p++){
            if(listeIdGroupeDistinct.get(listeIdGroupeDistinct.toArray().length-1) == listeIdGrpModule.get(p)){
                listePositions.add(p);
            }
        }
        for (int nv = 0 ; nv < listePositions.toArray().length-1 ; nv++){
            for (int p = 0 ; p < listePositions.toArray().length ; p++){
                if(listeNumeroVoeux.get(listePositions.get(p)) == nv+1){
                    line += listeIdModule.get(listePositions.get(p)) + ",";
                }
            }
        }
        for (int p = 0 ; p < listeIdGrpModule.toArray().length ; p++){
            if(listeNumeroVoeux.get(listePositions.get(p)) == listePositions.toArray().length){
                line += listeIdModule.get(listePositions.get(p));
            }
        }

        // Ecrire la nouvelle ligne
        sauv.write(line);
        sauv.newLine();

        // Réinitialiser les variables
        listeIdModule.clear();
        listeNumeroVoeux.clear();
        listeIdGrpModule.clear();
        listeIdGroupeDistinct.clear();
    }

    public static void ecrireFichierComplet (Connection con, int idSemestre, String chemin) throws SQLException {
        //
        try {
            BufferedWriter sauv = new BufferedWriter(new FileWriter(chemin + "Choix_Voeux_idSemestre_"+idSemestre+".txt",false));

            //Initialisation des variables
            String spst; // String PreparedSTatement = spst

            /*Ecriture de NG et NC
             *
             * NG
             * NC
             *
             * */
            spst = "SELECT Semestre.ng,Semestre.nc FROM Semestre WHERE Semestre.id = ?";
            try (PreparedStatement pst = con.prepareStatement(spst)) {
                pst.setInt(1, idSemestre);
                ResultSet rset = pst.executeQuery(); {
                    while (rset.next()) {
                        sauv.write(rset.getString(1)+" ");
                        sauv.newLine();
                        sauv.write(rset.getString(1)+" ");
                        sauv.newLine();
                    }
                }
            }
            catch (IOException e) {
                System.out.println("ERROR : ecricreFichier : Ecriture de NG et NC : ecriture impossible : "+e);
            }

            /*Description des Modules :
             *
             * MODULES
             * Modules.id ; GrpModule.id
             * Modules.id ; GrpModule.id
             * Modules.id ; GrpModule.id
             * FINMODULES
             *
             * */
            sauv.write("MODULES");
            sauv.newLine();
            spst = "SELECT DISTINCT Module.id,GrpModule.idGroupe FROM Semestre JOIN GrpModule ON ? = GrpModule.idSemestre JOIN Module ON GrpModule.idModule = Module.id ORDER BY Module.id ASC";
            try (PreparedStatement pst = con.prepareStatement(spst)) {
                pst.setInt(1, idSemestre);
                ResultSet rset = pst.executeQuery(); {
                    while (rset.next()) {
                        sauv.write(rset.getString(1) + ";" + rset.getString(2));
                        sauv.newLine();
                    }
                }
            }
            catch (IOException e) {
                System.out.println("ERROR : ecricreFichier : Ecriture de NG et NC : ecriture impossible : "+e);
            }
            sauv.write("FINMODULES");
            sauv.newLine();

            /* Description des choix
             *
             * CHOIX
             * idEtudiant;choixA{idModule},choixB;{Pour chaque étudiants}
             * FINCHOIX
             *
             * */
            sauv.write("CHOIX");
            sauv.newLine();
            spst = "SELECT DISTINCT Voeux.idEtudiant,Voeux.idModule,Voeux.numeroVoeux,Voeux.idGroupe FROM Voeux  Voeux.idSemestre = ? ORDER BY Voeux.idEtudiant ASC";
            try (PreparedStatement pst = con.prepareStatement(spst)) {
                pst.setInt(1, idSemestre);
                ResultSet rset = pst.executeQuery(); {

                    //Initialiser les variables
                    List<Integer> listeIdModule = new ArrayList<>();
                    List<Integer> listeNumeroVoeux = new ArrayList<>();
                    List<Integer> listeIdGrpModule = new ArrayList<>();
                    List<Integer> listeIdGroupeDistinct = new ArrayList<>();
                    List<Integer> listePositions = new ArrayList<>();
                    String idEtudiant = null;

                    // Créer les lignes du fichier txt
                    while (rset.next()) {
                        if(idEtudiant == null){
                            // Si c'est la première fois que l'on a cet étudiant
                            idEtudiant = rset.getString(1);
                            listeIdModule.add(rset.getInt(2));
                            listeNumeroVoeux.add(rset.getInt(3));
                            listeIdGrpModule.add(rset.getInt(4));
                        }
                        else if(idEtudiant.equals(rset.getString(1))){
                            // Si on a eu cet étudiant avant
                            listeIdModule.add(rset.getInt(2));
                            listeNumeroVoeux.add(rset.getInt(3));
                            listeIdGrpModule.add(rset.getInt(4));
                        }
                        else{
                            // Si on change d'étudiant

                            // Créer une nouvelle ligne
                            CreateLineEtudiant( idEtudiant , listeIdGroupeDistinct, listeIdGrpModule , listePositions , listeNumeroVoeux , listeIdModule , sauv );

                            //Initialiser pour le nouvel étudiant
                            idEtudiant = rset.getString(1);
                            listeIdModule.add(rset.getInt(2));
                            listeNumeroVoeux.add(rset.getInt(3));
                            listeIdGrpModule.add(rset.getInt(4));
                        }
                    }
                    //Ajouter le dernier étudiant
                    CreateLineEtudiant( idEtudiant , listeIdGroupeDistinct, listeIdGrpModule , listePositions , listeNumeroVoeux , listeIdModule , sauv );
                }
            }
            catch (IOException e) {
                System.out.println("ERROR : ecricreFichier : Ecriture de NG et NC : ecriture impossible : "+e);
            }
            sauv.write("FINCHOIX");
            sauv.newLine();

            // Nous ne gérons pas les couts, il reste donc
            sauv.write("COUTS");
            sauv.newLine();
            sauv.write("FINCOUTS");

            // FIN
            sauv.close();
        }
        catch (IOException err) {System.out.println("ERROR : ecricreFichier : impossible de créer le fichier d'écriture");}
    }

}