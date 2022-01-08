package classes;

import java.sql.Date;

// =======================================================================================
// Classe permettant de créer des objets "etudiant"
// =======================================================================================

public class Etudiant extends Personne {

    //définition des attributs
    Date dateNaiss;             // date de naissance
    String classe;              //définit la classe de l'étudiant
    String disponibilite;       // permet de définir si un éttudiant peut s'inscire ou non à un electif.
    
    //Constructeurs
    public Etudiant(int id, Date dateNaiss, String disponibilite, String nom, String prenom, String adresse, String mdp, String classe) {
        super(id, nom, prenom, adresse, mdp);
        this.classe=classe;
        this.dateNaiss = dateNaiss;
        this.disponibilite = disponibilite;
    }

    public Etudiant() {
        super();
    }

    //méthodes get
    @Override
    public String getMdp() {
        return super.getMdp();
    }

    @Override
    public int getid() {
        return super.getid();
    }

    @Override
    public String getAdresse() {
        return super.getAdresse();
    }

    @Override
    public String getPrenom() {
        return super.getPrenom();
    }

    @Override
    public String getNom() {
        return super.getNom();
    }

    public Date getDateNaiss() {
        return this.dateNaiss;
    }

    public String getClasse() {
        return this.classe;
    }

    //méthodes set
    @Override
    public String toString() {
        return (super.toString()+ ";"
        + this.getDateNaiss()+";"+this.getClasse() );
    }
    
    @Override
    public void setMdp(String mdp) {
        super.setMdp(mdp);
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
    public void setPrenom(String prenom) {
        super.setPrenom(prenom);
    }

    @Override
    public void setNom(String nom) {
        super.setNom(nom);
    }
    
    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }
    
    public void setClasse(String classe){
        this.classe=classe;
    }

}
