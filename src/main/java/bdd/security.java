/** Programme de hashage de mot de passe
 * écrit par Thibault Tostain
 *
 * Dernière modification le 5 décembre 2021
 * par Thibault Tostain
 */

package bdd;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class security {

    //Le main ne sert que d'exemple au fonctionnement de ce fichier, il n'a pas d'importance dans le code.
    public static void main(String[] args) {

        //Initialisation des variables
        String mdp; // Récupère le mdp proposé par l'utilisateur

        //Programme d'exemple, permet de vérifier que si le bon mdp est donné alors c'est bon et seul lui peut être bon
        System.out.println("Start");
        System.out.println("Le mdp haché est : " + CreateHash("Thibault"));
        System.out.print("Quel est le mdp non-haché d'après toi ? : ");
        mdp = Lire.S();
        mdp = CreateHash(mdp);
        while(!mdp.equals(CreateHash("Thibault"))){
            System.out.print("Non, recommence : ");
            mdp = Lire.S();
            mdp = CreateHash(mdp);
        }
        System.out.println("Bravo tu as trouvé !");

    }

    // CreateHash prend un mdp en String et renvoie un String équivalant au mdp une fois hashé
    public static String CreateHash (String mdpInit){

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

        //Return, si le programme n'a pas réussi, alors retourne "null" sinon retourne le mdp hashé
        return mdpHash;
    }
}
