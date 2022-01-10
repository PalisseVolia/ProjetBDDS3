package classes;

// =======================================================================================
// Classe permettant de créer des objets "semestre"
// =======================================================================================

public class Semestre {

    //définition des attributs
    int id;         //identifiant unique du semestre
    int annee ;     //année du semestre
    int numero;     //correspond au numéro du semestre dans l'année, à savoir 1 ou 2
    int Ng;         //nombre de groupe de module du semestre
    int Nc;         //nombre de choix de module
    
    //Constructeurs
    public Semestre(int id, int annee, int numero, int ng, int nc) {
        this.id=id;
        this.annee = annee;
        this.numero = numero;
        this.Ng = ng;
        this.Nc = nc;
    }

    public Semestre() {
    }
    
    //méthodes get
    public int getId() {
        return id;
    }

    public int getAnnee() {
        return annee;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public int getNg() {
        return Ng;
    }
    
    public int getNc() {
        return Nc;
    }
    
    //méthodes set
    public void setId(int id) {
        this.id = id;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNg(int ng) {
        Ng = ng;
    }

    public void setNc(int nc) {
        Nc = nc;
    }

    //toString
    public String toString() {
        return "Semestre [Nc=" + Nc + ", Ng=" + Ng + ", annee=" + annee + ", id=" + id + ", numero=" + numero + "]";
    }

    public String toStringSimple() {
        return "Semestre [annee=" + annee + ", id=" + id + ", numero=" + numero + "]";
    }
    
}
