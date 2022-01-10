package bdd;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// =======================================================================================
// Classe de hash des mots de passe
// =======================================================================================

public class security {

    public static String CreateHash (String mdpInit){
        // CreateHash prend un mdp en String et renvoie un String équivalant au mdp une fois hashé

        //Initialisation des variables
        String mdpHash = ""; //Prend le mdp une fois hashé
        byte [] byteMdp; //prend le mdp en forme de tableau de bytes, sert à appliquer la fonction de hash

        //Algorithme de hash
        byteMdp = mdpInit.getBytes(); //on transforme notre mdp en tableau de byte
        try{
            //Créer le tableau de byte de hach
            MessageDigest mesDig = MessageDigest.getInstance("SHA-256"); //On dit le style de hash que l'on souhaite, ici le sha-256
            mesDig.update(byteMdp); // remplie le mesDig avec une suite de bytes définie pour pouvoir appliquer le code de hash
            byte[] byteMdp2 = mesDig.digest(); // On applique le code de hash

            // Créer le mdpHash au format String
            mdpHash += byteMdp2[0];
            for (int i = 1 ; i < 32 ; i++){
                mdpHash += ";"+byteMdp2[i];
            }
            if(mdpHash.length() > 250) { //Pour éviter la création de mdp trop long dans la bdd on coupe les mdp > 250 caractères
                mdpHash = mdpHash.substring(0,249);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error : security.java / String.CreateHash(String) : "+e);// S'il y a une erreur alors l'afficher
            mdpHash = null; // retourne 'null' si le programme a eu une erreur
        }

        //Return, si le programme n'a pas réussi, alors retourne "null" sinon retourne le mdp hashé
        return mdpHash;
    }

    public static String CreateHashv2 (String mdpInit){

        //Initialisation des variables
        String mdpHash = null; //Prend le mdp une fois hashé
        byte [] byteMdp; //prend le mdp en forme de tableau de bytes, sert à appliquer la fonction de hash

        //Algorithme de hash
        byteMdp = mdpInit.getBytes(); //on transforme notre mdp en tableau de byte
        try{
            MessageDigest mesDig = MessageDigest.getInstance("SHA-256"); //On dit le style de hash que l'on souhaite, ici le sha-256
            mesDig.update(byteMdp); // remplie le mesDig avec une suite de bytes définie pour pouvoir appliquer le code de hash
            byte[] byteMdp2 = mesDig.digest(); // On applique le code de hash
            mdpHash = new String(byteMdp2, java.nio.charset.StandardCharsets.UTF_8); // On transforme le tableau hashé en un string au format UTF_8
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error : security.java / String.CreateHash(String) : "+e);// S'il y a une erreur alors l'afficher
        }

        //Pour éviter la création de mdp trop long dans la bdd
        if(mdpHash.length() > 250){
            mdpHash = mdpHash.substring(0,249);
        }

        //Return, si le programme n'a pas réussi, alors retourne "null" sinon retourne le mdp hashé
        return mdpHash;
    }

    public static String CreateHashv3 (String mdpInit){                                                                                  
                                                                                                                                   
        //Initialisation des variables                                                                                                 
        String mdpHash = null; //Prend le mdp une fois hashé                                                                           
        byte [] byteMdp; //prend le mdp en forme de tableau de bytes, sert à appliquer la fonction de hash                             
                                                                                                                                       
        //Algorithme de hash                                                                                                           
        byteMdp = mdpInit.getBytes(); //on transforme notre mdp en tableau de byte                                                     
        try{                                                                                                                           
            MessageDigest mesDig = MessageDigest.getInstance("SHA-256"); //On dit le style de hash que l'on souhaite, ici le sha-256   
            mesDig.update(byteMdp); // remplie le mesDig avec une suite de bytes définie pour pouvoir appliquer le code de hash        
            byte[] byteMdp2 = mesDig.digest(); // On applique le code de hash                                                          
            for (int i = 0 ; i < 32 ; i++){                                                                                            
                if(byteMdp2[i]<0){                                                                                                     
                    byteMdp2[i] = (byte) -byteMdp2[i];                                                                                 
                }                                                                                                                      
                mdpHash += ";"+byteMdp2[i];                                                                                            
            }                                                                                                                          
            //Pour éviter la création de mdp trop long dans la bdd                                                                     
            if(mdpHash.length() > 250) {                                                                                               
                mdpHash = mdpHash.substring(0,249);                                                                                    
            }                                                                                                                          
        } catch (NoSuchAlgorithmException e) {                                                                                         
            System.out.println("Error : security.java / String.CreateHash(String) : "+e);// S'il y a une erreur alors l'afficher       
        }                                                                                                                              
                                                                                                                                       
        //Return, si le programme n'a pas réussi, alors retourne "null" sinon retourne le mdp hashé                                    
        return mdpHash;                                                                                                                
    }
}
