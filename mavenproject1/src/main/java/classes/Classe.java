/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author thiba
 */
public class Classe {
    
    private String specialite ;
    private int annee ;

    public Classe(String specialite, int annee) {
        this.specialite = specialite;
        this.annee = annee;
    }

    public String getSpecialite() {
        return specialite;
    }

    public int getAnnee() {
        return annee;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    @Override
    public String toString() {
        return "Classe{" + "specialite=" + specialite + ", annee=" + annee + '}';
    }
    
    
    
    
}
