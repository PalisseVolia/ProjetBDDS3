package com.example.application.bdd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Ecriture {

    public static void ecrireFichier (Connection con, int idSemestre) throws SQLException {
        //
        try {
            BufferedWriter sauv = new BufferedWriter(new FileWriter("Choix_Voeux_idSemestre_"+idSemestre+".txt",false));



            /*Ecriture de NG et NC
            *
            * NG
            * NC
            *
            * */
            final String requeteA ="SELECT ng,nc from Semestres WHERE id = 'idSemestre'";
            try ( Statement st = con.createStatement()) {
                try ( ResultSet res = st.executeQuery(requeteA)) {
                    sauv.write(res.getString(1));
                    sauv.newLine();
                    sauv.write(res.getString(1));
                    sauv.newLine();
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
            final String requeteB ="SELECT DISTINCT Modules.id,GrpModule.id from Semestres Join GrpModule ON "+idSemestre+" = GrpModule.id Join Modules ON GrpModule.id = Modules.id";
            try ( Statement st = con.createStatement()) {
                try ( ResultSet res = st.executeQuery(requeteB)) {
                    while(res.next()) {
                        sauv.write(res.getString(1) + ";"+ res.getString(2) );
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
        }
        catch (IOException err) {System.out.println("ERROR : ecricreFichier : impossible de créer le fichier d'écriture");}
    }

}
