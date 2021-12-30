package com.example.application.classes;

import java.sql.Date;

public class Etudiant extends Personne {

    //attributs
    Date dateNaiss; // date de naissance
    String classe; //définit la classe de l'étudiant
    String disponibilite; // permet de définir si un éttudiant peut s'inscire ou non à un electif. Exemple
                           // : si il part en mobilité
    

    public Etudiant(int id, Date dateNaiss, String disponibilite, String nom, String prenom, String adresse, String mdp, String classe) {
        super(id, nom, prenom, adresse, mdp);
        this.classe=classe;
        this.dateNaiss = dateNaiss;
        this.disponibilite = disponibilite;
    }

    public Etudiant() {
        super();
    }

    // pour tester
    public Etudiant(int id,String nom, String prenom, String adresse, String mdp) {
        super(id, nom, prenom, adresse, mdp);
    }

    @Override
    public String getMdp() {
        return super.getMdp(); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getid() {
        return super.getid();
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
        return this.dateNaiss;
    }

    public String getClasse() {
        return this.classe;
    }

    public String isDisponibilite() {
        return disponibilite;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }
    @Override
    public String toString() {
        return (super.toString()+ ";"
                + this.getDateNaiss()+";"+this.getClasse() );
    }

    @Override
    public void setMdp(String mdp) {
        super.setMdp(mdp); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setid(int id) {
        super.setid(id);
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

    public void setClasse(String classe){
        this.classe=classe;
    }

}
