package com.example.application.classes;

import java.sql.Date;

public class Etudiant extends Personne {

    //attributs
    Date dateNaiss; // date de naissance
    boolean disponibilite; // permet de définir si un éttudiant peut s'inscire ou non à un electif. Exemple
                           // : si il part en mobilité

    public Etudiant(Date dateNaiss, boolean disponibilite, String nom, String prenom, String adresse, String mdp) {
        super(nom, prenom, adresse, mdp);
        this.dateNaiss = dateNaiss;
        this.disponibilite = disponibilite;
    }

    public Etudiant() {
        super();
    }

    // pour tester
    public Etudiant(String nom, String prenom, String adresse, String mdp) {
        super(nom, prenom, adresse, mdp);
    }

    @Override
    public String getMdp() {
        return super.getMdp(); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAdresse() {
        return super.getAdresse(); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPrenom() {
        return super.getPrenom(); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNom() {
        return super.getNom(); // To change body of generated methods, choose Tools | Templates.
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String toString() {
        return (this.getNom() + ";" + this.getPrenom() + ";" + this.getAdresse() + ";" + this.getMdp() + ";"
                + this.getDateNaiss());
    }

    @Override
    public void setMdp(String mdp) {
        super.setMdp(mdp); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAdresse(String adresse) {
        super.setAdresse(adresse); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPrenom(String prenom) {
        super.setPrenom(prenom); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNom(String nom) {
        super.setNom(nom); // To change body of generated methods, choose Tools | Templates.
    }

}
