package com.example.application.classes;

public class Admin extends Personne {

    public Admin(int id,String nom, String prenom, String adresse, String mdp) {
        super(id, nom, prenom, adresse, mdp);
    }

    public Admin() {
    }

    @Override
    public String getAdresse() {
        return super.getAdresse();
    }

    @Override
    public String getMdp() {
        return super.getMdp();
    }

    @Override
    public String getNom() {
        return super.getNom();
    }

    @Override
    public String getPrenom() {
        return super.getPrenom();
    }

    @Override
    public int getid() {
        return super.getid();
    }
    
    @Override
    public void setid(int id) {
        super.setid(id);
    }

    @Override
    public void setAdresse(String adresse) {
        super.setAdresse(adresse);
    }

    @Override
    public void setMdp(String mdp) {
        super.setMdp(mdp);
    }

    @Override
    public void setNom(String nom) {
        super.setNom(nom);
    }

    @Override
    public void setPrenom(String prenom) {

        super.setPrenom(prenom);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {    
        return super.clone();
    }

    @Override
    public boolean equals(Object arg0) {    
        return super.equals(arg0);
    }

    @Override
    protected void finalize() throws Throwable { 
        super.finalize();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    
}
