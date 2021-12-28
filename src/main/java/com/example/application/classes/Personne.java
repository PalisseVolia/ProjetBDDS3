package com.example.application.classes;

public abstract class Personne {
//on crée cette classe mère afin de différencier les administrateurs des étudiants
//Ces deux classes ont des attributs en commun mais doivent être différenciés

    //définition des attributs
    private String nom;
    private String prenom;
    private String adresse;
    private String mdp;

    //Constructeur
    public Personne(String nom, String prenom, String adresse, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mdp = mdp;
    }

    public Personne() {

    }

    //méthodes get
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMdp() {
        return mdp;
    }

    //méthode set
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

}
