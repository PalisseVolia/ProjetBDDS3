package com.example.application.bdd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Ecriture {

    public static void ecrireFichier (Connection con, int idSemestre) throws SQLException {
        //
        try {
            BufferedWriter sauv = new BufferedWriter(new FileWriter("Choix_Voeux.txt",false));
            //Ecriture de NG et NC
            final String requete ="SELECT ng,nc from Semestres WHERE id = 'idSemestre'";
            try ( Statement st = con.createStatement()) {
                try ( ResultSet res = st.executeQuery(requete)) {
                    sauv.write(res.getString(1));
                    sauv.newLine();
                    sauv.write(res.getString(1));
                    sauv.newLine();
                }
            }
            catch (IOException e) {
                System.out.println("ERROR : ecricreFichier : Ecriture de NG et NC : ecriture impossible : "+e);
            }
        }
        catch (IOException err) {System.out.println("ERROR : ecricreFichier : impossible de créer le fichier d'écriture");}
    }

}
