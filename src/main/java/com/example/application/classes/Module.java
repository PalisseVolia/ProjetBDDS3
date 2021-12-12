package com.example.application.classes;

import java.util.Arrays;
import java.util.List;

public class Module {

    String intitule; // nom du module
    String description; // description du module
    int nbPlaceMax; // nombre de personne maximum pouvant s'inscire au module
    int nbPlaceMin; // nombre de personne nécessaire à l'ouverture du module
    String classeacceptee; // exemple GE/GCE/MIQ

    public Module(String intitule, String description, int nbPlaceMax, int nbPlaceMin, String classeacceptee) {
        this.intitule = intitule;
        this.description = description;
        this.nbPlaceMax = nbPlaceMax;
        this.nbPlaceMin = nbPlaceMin;
        this.classeacceptee = classeacceptee;
    }

    public Module() {

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
        return "Module{" + "intitule=" + intitule + ", description=" + description + ", nbPlaceMax=" + nbPlaceMax
                + ", nbPlaceMin=" + nbPlaceMin + ", classeacceptee=" + classeacceptee + '}';
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
        
        {"11", "Energie électrique renouvelable : photovoltaïque - 1", "En présence :\n" +
        "- du cahier des charges d’une installation photovoltaïque à réaliser,\n" +
        "- ou du dossier technique d’une installation photovoltaïque existante,\n" +
        "l’étudiant sera capable à l’issue de ce module :\n" +
        "- d’évaluer le gisement solaire d’un site (/r à sa situation géographique, l’orientation et l’inclinaison des\n" +
        "modules…),\n" +
        "- de dimensionner et choisir, ou de justifier les principaux équipements de l’installation,\n" +
        "- de connaître les technologies, les caractéristiques et les performances des équipements présents dans les\n" +
        "installations photovoltaïques (modules, régulateur-chargeur, batteries, onduleurs, protections…),\n" +
        "- d’interpréter les schémas électriques d’installation,\n" +
        "- de décoder des documentions techniques des équipements,\n" +
        "- d’exploiter les normes et les réglementations du domaine photovoltaïque,\n" +
        "- d’établir un bilan de puissance complet et d’évaluer le potentiel de production énergétique d’un site,\n" +
        "- de connaître les tarifs de rachat et les modalités de raccordement au réseau de distribution public,\n" +
        "- d’évaluer la rentabilité économique d’une installation,\n" +
        "- de connaître le marché du solaire photovoltaïque (mondial, européen et français) ainsi que son potentiel\n" +
        "de développement avenir,", "25", "16", "TOUTE"},
        
        {"12", "Filmer la science", "L'objectif premier sera de répondre à la question suivante : quelles formes peut prendre la science lorsqu'elle est filmée, lorsqu'elle est observée au travers du prisme du cinéma ?\n" +
        "Le cinéma est un art visuel et narratif. Il permet de donner corps à des fantasmes, qu'ils soient scientifiques ou non, de créer un univers fictionnel singulier, mais aussi de témoigner de l'existant.\n" +
        "Le cinéma peut être permissif, il peut se jouer des règles scientifiques. Il sait aussi être précis.\n" +
        "Nous dresserons un panorama aussi exhaustif que possible de ce qui existe dans le domaine.", "25", "16", "TOUTE"},
        
        {"13", "Pathologie des ouvrages", "analyses de dossiers « rapports d’inspection détaillés » fournis par les enseignants (1 dossier par groupe de\n" +
        "trois). Etude de dossiers afin d’établir un rapport concernant les désordres observés, les moyens d’analyses\n" +
        "des pathologies et de leur évolutions.", "25", "16", "TOUTE"},
        
        {"14", "Introduction à l'informatique quantique", "Introduction à l'informatique quantique", "25", "16","TOUTE"},
        
        {"15", "Statistiques", "Ce cours est une introduction aux statistiques. L'objectif est de présenter ce que permettent de faire les statistiques mais aussi ce qu'elles ne permettent pas de faire.", "25", "16","TOUTE"},
        
        
        
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
