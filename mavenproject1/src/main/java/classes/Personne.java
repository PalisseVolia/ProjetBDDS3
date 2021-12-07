package classes;

public abstract class  Personne {
    
    private String nom ;
    private String prenom ;
    private String adresse ;
    private String mdp ;

    public Personne(String nom, String prenom, String adresse, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mdp = mdp;
    }
    
    public Personne() {
      
    }
    
    

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
