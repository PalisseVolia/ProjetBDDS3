package classes;

public class Module {

    //Attributs
    String intitule; // nom du module
    String description; // description du module
    int nbPlaceMax; // nombre de personne maximum pouvant s'inscire au module
    int nbPlaceMin; // nombre de personne nécessaire à l'ouverture du module
    String classeacceptee; // exemple GE/GCE/MIQ

    //Constructeur
    public Module(String intitule, String description, int nbPlaceMax, int nbPlaceMin, String classeacceptee) {
        this.intitule = intitule;
        this.description = description;
        this.nbPlaceMax = nbPlaceMax;
        this.nbPlaceMin = nbPlaceMin;
        this.classeacceptee = classeacceptee;
    }

    public Module() {

    }

    //méthodes get
    public String getIntitule() {
        return intitule;
    }

    public String getDescription() {
        return description;
    }

    public int getNbPlaceMax() {
        return nbPlaceMax;
    }

    public int getNbPlaceMin() {
        return nbPlaceMin;
    }

    public String getClasseacceptee() {
        return classeacceptee;
    }

    //méthodes set
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNbPlaceMax(int nbPlaceMax) {
        this.nbPlaceMax = nbPlaceMax;
    }

    public void setNbPlaceMin(int nbPlaceMin) {
        this.nbPlaceMin = nbPlaceMin;
    }

    public void setClasseacceptee(String classeacceptee) {
        this.classeacceptee = classeacceptee;
    }

    public String toString() {
        return "Module{" + "intitule=" + intitule + ", description=" + description + ", nbPlaceMax=" + nbPlaceMax
                + ", nbPlaceMin=" + nbPlaceMin + ", classeacceptee=" + classeacceptee + '}';
    }

}
