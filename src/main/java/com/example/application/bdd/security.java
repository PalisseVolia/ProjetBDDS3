package com.example.application.bdd;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class security {

    public static void main(String[] args) {

        //Initialisation des variables
        String mdp = null;

        //Programme
        System.out.println("Start");
        System.out.print("mdp : ");
        mdp = Lire.S();
        mdp = CreateHash(mdp);
        System.out.println("hash: "+mdp);

        //Boucle
        for(int i = 0; i<10;i++){
            System.out.println("hash: "+CreateHash("Thibault"));
        }
    }

    // CreateHash prend un mdp en String et renvoie un String équivalant au mdp une fois hashé
    public static String CreateHash (String mdpInit){

        //Initialisation des variables
        String mdpHash = null;
        byte [] byteMdp = null;

        //Algorithme de hash
        byteMdp = mdpInit.getBytes(); //on transforme notre mdp en tableau de byte
        try{
            MessageDigest mesDig = MessageDigest.getInstance("SHA-256"); //On dit le style de hash que l'on soufaite, ici le sha-256
            mesDig.update(byteMdp);
            byte[] byteMdp2 = mesDig.digest();
            mdpHash = new String(byteMdp2, java.nio.charset.StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //Return
        return mdpHash;
    }

    // CreateHashSimple prend un mdp, lui applique une modification simple et renvoie un string du mdp hashé
    public static String CreateHashSimple (String mdpInit){

        //Initialisation des variables
        String mdpHash = null;

        //Algorithme de hash
        mdpHash = mdpInit +"_confidential";

        //Retrun
        return mdpHash;
    }

}
