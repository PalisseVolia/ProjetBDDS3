package classes;

// =======================================================================================
// Classe permettant de créer des objets "personne"
// =======================================================================================

public abstract class Personne {
//on crée cette classe mère qui sert de base aux admins et aux étudiants
//Ces deux classes ont des attributs en commun mais doivent être différenciés

    //définition des attributs
    private int id;             //identifiant de la personne
    private String nom;         //nom
    private String prenom;      //prenom
    private String adresse;     //email
    private String mdp;         //mot de passe

    //Constructeur
    public Personne(int id,String nom, String prenom, String adresse, String mdp) {
        this.id=id;
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

    public int getid() {
        return id;
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

    public void setid(int id) {
        this.id = id;
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

    //toString
    public String toString(){
        String s = this.getNom() + ";" + this.getPrenom() + ";" + this.getAdresse() + ";" + this.getMdp();
        return s;

    }

    public String testClasse(){
        String s="";
        if (this instanceof Etudiant ){
            s ="etudiant";

        }else if (this instanceof Admin ){
            s="admin";
        }

        return s;
    }

}
