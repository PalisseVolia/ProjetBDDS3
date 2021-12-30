package classes;

public class Semestre {

    //définition des attributs
    int annee ;
    int numero; //correspond au numéro du semestre, à savoir 1 ou 2
    int Ng; //nombre de groupe de module du semestre
    int Nc; //nombre de choix de module
    
    //Constructeurs
    public Semestre(int annee, int numero, int ng, int nc) {
        this.annee = annee;
        this.numero = numero;
        this.Ng = ng;
        this.Nc = nc;
    }

    public Semestre() {
    }

    //méthodes get
    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNg() {
        return Ng;
    }

    //méthodes set
    public void setNg(int ng) {
        Ng = ng;
    }

    public int getNc() {
        return Nc;
    }

    public void setNc(int nc) {
        Nc = nc;
    }

    public String toString() {
        return "Semestre [Nc=" + Nc + ", Ng=" + Ng + ", annee=" + annee + ", numero=" + numero + "]";
    }

    

}
