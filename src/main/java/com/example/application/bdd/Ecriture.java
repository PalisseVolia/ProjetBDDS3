package com.example.application.bdd;

import java.sql.Connection;

public class Ecriture {

    /*public static void ecrireFichier (Connection con) {
        try {
            BufferedWriter sauv = new BufferedWriter(new FileWriter(nomDocument+".txt",false));
            List<Noeud> noeudList = treilli.getNoeudList();
            List<Barre> barreList = treilli.getBarreList();
            List<TypeBarre> typeList = treilli.getTypeList();
            List<TriangleTerrain> sol = terrain.getSol();
            sauv.write("Terrain;"+terrain.getAbsMin()+";"+terrain.getAbsMax()+";"+terrain.getOrdMin()+";"+terrain.getOrdMax());
            sauv.newLine();
            for(int i =0 ; i < sol.size() ; i++) {
                sauv.write("Triangle;"+sol.get(i).getId()+";"+sol.get(i).getStart()+";"+sol.get(i).getMiddle()+";"+sol.get(i).getEnd()+";"+sol.get(i).getCounter());
                sauv.newLine();
                System.out.println(sol.get(i).getId());
            }
            sauv.write("Treilli;"+treilli.getId()+";"+treilli.getCount());
            sauv.newLine();
            for(int i = 0; i < typeList.size() ; i++) {
                sauv.write("TypeBarre;"+typeList.get(i).getLongMin()+";"+typeList.get(i).getLongMax()+";"+typeList.get(i).getResTension()+";"+typeList.get(i).getResCompression()+";"+typeList.get(i).getCout()+";"+typeList.get(i).getCount()+";"+typeList.get(i).getId());
                sauv.newLine();
            }
            for(int i = 0 ; i < noeudList.size(); i++) {
                sauv.write(""+noeudList.get(i));
                sauv.newLine();
            }
            for(int i = 0 ; i < barreList.size(); i++) {
                sauv.write(""+barreList.get(i));
                sauv.newLine();
            }
            sauv.close(); // permet de fermer la mémoire tampon BufferedWriter
        }
        catch (IOException err) {System.out.println("Main : ecricreFichier : impossible de créer le fichier");}
    }*/

}
