/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import static classes.Etudiant.PERSONNES_ALEA;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author thiba
 */
public class Module {
    
    String intitule ; //nom du module
    String description ; //description du module
    int nbPlaceMax; // nombre de personne maximum pouvant s'inscire au module
    int nbPlaceMin; //nombre de personne nécessaire à l'ouverture du module
    String classeacceptee ; //exemple GE/GCE/MIQ

    public Module(String intitule, String description, int nbPlaceMax, int nbPlaceMin, String classeacceptee) {
        this.intitule = intitule;
        this.description = description;
        this.nbPlaceMax = nbPlaceMax;
        this.nbPlaceMin = nbPlaceMin;
        this.classeacceptee = classeacceptee;
    }
    
    public Module(){
        
    }

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

    @Override
    public String toString() {
        return "Module{" + "intitule=" + intitule + ", description=" + description + ", nbPlaceMax=" + nbPlaceMax + ", nbPlaceMin=" + nbPlaceMin + ", classeacceptee=" + classeacceptee + '}';
    }
    
     public static final String[][] MODULE = new String[][]{
        {"1", "ArduinoMaker", "Concevoir un objet piloté par une carte Arduino", "25", "16",  "TOUTE"},
         
        {"2", "Conception inventive et innovation", "L’innovation apparait depuis quelques années comme un mot clé qui fait le buzz dans les discours des managers des entreprises ou de nos dirigeants politiques. Or, aux origines des innovations, c’est souvent l’ingénieur qui invente dès lors qu’il s’agit d’un objet ou d’un système technique plus ou moins complexe. En conséquence, l’activité créative chez l’ingénieur est au coeur de toutes les attentions des industries tournées vers l’innovation. Pour autant, tous les ingénieurs ne sont pas des inventeurs ! Ceci est en partie dû au fait qu’aucune pratique de l’invention (tout au plus quelques techniques comme le brainstorming) ne leur ont été enseignées dans leur parcours de formation.\n" +
        "Comment naissent les inventions qui deviendront des innovations ? Peut-on, au-delà du brainstorming, systématiser leur émergence ? Existe-t-il des théories, des méthodes, des outils de nature à structurer les processus créatifs de façon à garantir son efficience ? Peut-on apprendre à inventer ou améliorer ses capacités inventives ?\n" +
        "L’INSA Strasbourg est depuis de nombreuses années en pointe sur le sujet de l’ingénierie de l’innovation. Ceci est en grande partie dû à l’existence de travaux de recherche sur le sujet de la Conception Inventive dans l’une de ses équipes de recherche. Ce module électif est une première sensibilisation aux techniques d’analyse des objets techniques permettant d’impulser l’innovation par l’invention. Etant conçue pour les ingénieurs, la démarche méthodologique utilisée sera dans un premier temps décrite de façon théorique et agrémentée d’exemples, puis fera l’objet d’un micro-projet en groupe poursuivant un double objectif :\n" +
        "• Placer l’étudiant en situation de projet d’invention dans une équipe etconduire le projet par une méthode cadrant la démarche inventive ;\n" +
        "• Guider chaque équipe vers la construction d’un concept de solution (invention potentielle) qui sera essentiellement virtuelle mais technologiquement crédible.", "25","16", "TOUTE"},
        
        {"3", "Entrepreneuriat 1 : De l'idée au marché", "Être capable d'aborder un projet de création d'activité en mobilisant et en analysant le lien entre une idée et son environnement (marché potentiel, concurrents, clients, contraintes règlementaires).", "25","16",  "TOUTE"},
        
        {"4", "Initiation à la Plasturgie", "A l’issue de cet électif, l’étudiant doit être capable :\n" +
        " Mettre en œuvre des tests dits « hors laboratoire » afin d’identifier rapidement une famille de matière et certaines caractéristiques/propriétés propre à cette matière ;\n" +
        " Identifier et de décrire les principaux procédés de transformation des Thermo-Plastiques (TP), Thermo-Durcissables (TD) et élastomères d’un point de vue : Process, machines, périphériques, outillages, matières transformées, pièces obtenues.\n" +
        " Identifier des signatures procédés ;\n" +
        " Identifier et de décrire les fonctions et les solutions constructives d’outillages d’injection et d’extrusion;\n" +
        " Réaliser l’assemblage par soudage à air chaud ou ultrasons d’une pièce ;\n" +
        "", "25","16",  "TOUTE"},
        
        {"5", "Apprendre à dessiner et communiquer graphiquement", "Apprendre à dessiner", "25", "16", "TOUTE"},
        
        {"6", "Formation diplômante PRAP", "La formation PRAP a pour objectif de permettre au salarié de participer à l'amélioration de ses conditions de travail de manière à réduire les risques d'accidents du travail ou de maladies professionnelles.", "12","8", "TOUTE"},
       
        {"7", "Image(s) of the engineer", "Développer sa culture générale personnelle et son regard critique pour mieux se positionner dans son futur métier d’ingénieur.", "25", "16",  "TOUTE"},
        
        {"8", "Introduction au Design", "Apprendre à travailler ensemble dans une démarche collaborative - étudiants designer et étudiants ingénieur – afin de concevoir et de réaliser des pièces qui seront exposées lors des Designer’s Days à Paris début juin.", "25", "16","TOUTE"},
        
        {"9", "LV2 - Espagnol (Intermédiaire et avancé - Cycle 1)", "L'objectif de ce cours, qui s'adresse aux non débutants, est d'amener les étudiants à reprendre leurs marques en espagnol. Des groupes de niveau seront organisés, de manière à ce que chacun puisse bénéficier d'une pédagogie adaptée et progresser à son rythme.", "25","16", "TOUTE"},
        
        {"10", "LV2 - Allemand (Intermédiaire & Avancé - Cycle 1) 2LF", "Developper ses compétences en allemand", "25", "16", "TOUTE"},
        
       
   };
     
      public static List<String> intitule() {
        return Arrays.stream(MODULE).map((t) -> {
            return t[2];
        }).toList();
    }
    
      public static List<String> description() {
        return Arrays.stream(MODULE).map((t) -> {
            return t[1];
        }).toList();
    }
      
      public static List<String> nbrplacemin() {
        return Arrays.stream(MODULE).map((t) -> {
            return t[3];
        }).toList();
    }
      
       public static List<String> nbrplacemax() {
        return Arrays.stream(MODULE).map((t) -> {
            return t[4];
        }).toList();
       }
        
        public static List<String> classeacceptee() {
        return Arrays.stream(MODULE).map((t) -> {
            return t[5];
        }).toList();
    }
        
    
    
}
