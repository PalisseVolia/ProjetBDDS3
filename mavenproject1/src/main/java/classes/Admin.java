/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author thiba
 */
public class Admin extends Personne {

    public Admin(String nom, String prenom, String adresse, String mdp) {
        super(nom, prenom, adresse, mdp);
    }

    public Admin() {
    }
    
     public static final String[][] ADMIN = new String[][]{
        {"1", "Thibaut", "Waechter", "ThibautWaechter@insa-strasbourg.fr", "TW" },
        {"2", "Thibault", "Tostain", "ThibaultTostain@insa-strasbourg.fr","TT"},
        {"3", "Volia", "Palisse", "VoliaPalisse@insa-strasbourg.fr","VP"},
        
        
   };
    public static List<String> noms() {
        return Arrays.stream(ADMIN).map((t) -> {
            return t[2];
        }).toList();
    }
    
      public static List<String> prenoms() {
        return Arrays.stream(ADMIN).map((t) -> {
            return t[1];
        }).toList();
    }
      
      public static List<String> adresse() {
        return Arrays.stream(ADMIN).map((t) -> {
            return t[3];
        }).toList();
    }
      
       public static List<String> mdp() {
        return Arrays.stream(ADMIN).map((t) -> {
            return t[4];
        }).toList();
       }
    
}
