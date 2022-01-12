package bdd;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

// =======================================================================================
// Classe permettant l'écriture du fichier texte d'historique
// =======================================================================================

public class Ecriture {
    
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

}