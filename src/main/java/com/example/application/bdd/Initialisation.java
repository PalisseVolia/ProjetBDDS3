package com.example.application.bdd;

import java.util.Arrays;
import java.util.List;
import java.sql.*;

public class Initialisation {
    //classe contenant tout les éléments permettant d'initialiser la base de donnée
    public static void init() {
        try (Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass")) {
            Commandes.tabledrop(con, "Etudiant");
            Commandes.tabledrop(con, "Module");
            tableEtudiant(con);
            tableModule(con);
            
        } catch (Exception err) {
            System.out.println(err);
        }
    }

   
    
    public static final String[][] ADMIN = new String[][] {
            { "1", "Thibaut", "Waechter", "ThibautWaechter@insa-strasbourg.fr", "TW" },
            { "2", "Thibault", "Tostain", "ThibaultTostain@insa-strasbourg.fr", "TT" },
            { "3", "Volia", "Palisse", "VoliaPalisse@insa-strasbourg.fr", "VP" },
    };

    public static List<String> prenomsAdmin() {
        //méthode renvoyant le prénom de tous les administrateurs contenus dans la liste initiale ADMIN
        return Arrays.stream(ADMIN).map((t) -> {
            return t[1];
        }).toList();
    }

    public static List<String> nomsAdmin() {
        //méthode renvoyant le nom des admins
        return Arrays.stream(ADMIN).map((t) -> {
            return t[2];
        }).toList();
    }

    public static List<String> adresseAdmin() {
        //méthode renvoyant l'adresse des admins
        return Arrays.stream(ADMIN).map((t) -> {
            return t[3];
        }).toList();
    }

    public static List<String> mdpAdmin() {
        //méthode renvoyant le mot de passe des admins
        return Arrays.stream(ADMIN).map((t) -> {
            return t[4];
        }).toList();
    }

    //----------CREATION DE LA TABLE DES ETUDIANTS ----------
    
    public static final String[][] ETUDIANT = new String[][]{
        //tableau contenant les étudiants de base
        {"1", "Pauline", "Giroux", "PaulineGiroux@insa-strasbourg.fr", "Milita!recreux55",  "2002-05-07", "true"},
        {"2", "Peppin", "David", "PeppinDavid@insa-strasbourg.fr","AttaqueT!tanesque0", "2002-01-11","true"},
        {"3", "Aurore", "Benjamin", "AuroreBenjamin@insa-strasbourg.fr","ViveJava47?",  "2002-10-18","true"},
        {"4", "Dominic", "Rochefort", "DominicRochefort@insa-strasbourg.fr","?PublicStatic02",  "2002-08-07","true"},
        {"5", "Gérard", "Parenteau", "GerardParenteau@insa-strasbourg.fr", "Pizza4Fromage!", "2002-06-29","true"},
        {"6", "Melusina", "Simon", "MelusinaSimon@insa-strasbourg.fr","InsaStras67!", "2002-01-27","true"},
        {"7", "Calandre", "Béland", "CalandreBeland@insa-strasbourg.fr", "?Jb007",  "2002-03-20","true"},
        {"8", "Édith", "Caya", "EdithCaya@insa-strasbourg.fr", "XxMessixX05!","2002-03-25","true"},
        {"9", "Eugène", "Chrétien", "EugeneChretien@insa-strasbourg.fr","LeBronJames7!8", "2002-03-16","true"},
        {"10", "Searlas", "Guay", "SearlasGuay@insa-strasbourg.fr", "Xaeo14!5B", "2002-01-28","true"},
        {"11", "Pierrette", "Gadbois", "PierretteGadbois@insa-strasbourg.fr","fnzhj65H", "2002-05-04","true"},
        {"12", "Zurie", "Bonami", "ZurieBonami@insa-strasbourg.fr","nfjezG;14",  "2002-05-23","true"},
        {"13", "Alphonsine", "Cloutier", "AlphonsineCloutier@insa-strasbourg.fr","dhebdG4","2002-10-03","true"},
        {"14", "Pierpont", "Dumont", "PierpontDumont@insa-strasbourg.fr",  "thibautlbp01", "2002-11-05","true"},
        {"15", "Yvon", "Primeau", "YvonPrimeau@insa-strasbourg.fr", "thibautsecritsansl88","2002-09-21","true"},
        {"16", "Albertine", "Rossignol", "AlbertineRossignol@insa-strasbourg.fr","VoliaRugueux0", "2002-10-28","true"},
        {"17", "Christien", "Laderoute", "ChristienLaderoute@insa-strasbourg.fr","CouscousMerguez5", "2002-09-09","true"},
        {"18", "Lance", "Gosselin", "LanceGosselin@insa-strasbourg.fr", "PinkFloyd5", "2002-10-19","true"},
        {"19", "Somerville", "Brisebois", "SomervilleBrisebois@insa-strasbourg.fr","Juice999!",  "2002-08-15","true"},
        {"20", "Auriville", "Beauchemin", "AurivilleBeauchemin@insa-strasbourg.fr","RocketLeague4", "2001-03-23","true"},
        {"21", "Hugues", "Houle", "HuguesHoule@insa-strasbourg.fr",  "fhjs!fFz4", "2001-10-16","true"},
        {"22", "Ogier", "Josseaume", "OgierJosseaume@insa-strasbourg.fr","F!Sgjr5", "2001-03-07","true"},
        {"23", "Armina", "Massé", "ArminaMasse@insa-strasbourg.fr","fDSfdfFz4!","2001-04-18","true"},
        {"24", "Océane", "Lamare", "OceaneLamare@insa-strasbourg.fr","R!EZzfz4", "2001-04-15","true"},
        {"25", "Beltane", "Pariseau", "BeltanePariseau@insa-strasbourg.fr","Jgfh!FGsFz4", "2001-01-08","true"},
        {"26", "Maryse", "Guibord", "MaryseGuibord@insa-strasbourg.fr",  "HJ!Lrdq!Fz4","2001-01-21","true"},
        {"27", "Émilie", "Desnoyers", "EmilieDesnoyers@insa-strasbourg.fr","Frdgzjks!fFz4",  "2001-07-05","true"},
        {"28", "Melville", "Duplessis", "MelvilleDuplessis@insa-strasbourg.fr","Mh!ytkere4",  "2001-06-06","true"},
        {"29", "Michel", "Baril", "MichelBaril@insa-strasbourg.fr","FZkfn?c475", "2001-11-14","true"},
        {"30", "Bertrand", "Turgeon", "BertrandTurgeon@insa-strasbourg.fr","NarutoHokage", "2001-12-03","true"},
        {"31", "Landers", "Lemieux", "LandersLemieux@insa-strasbourg.fr","ViveSpiderman054!", "2001-03-05","true"},
        {"32", "La Roux", "Cotuand", "LaRouxCotuand@insa-strasbourg.fr","ProutLol:!4", "2001-01-30","true"},
        {"33", "Serge", "Givry", "SergeGivry@insa-strasbourg.fr","fdhjzffzh2", "2001-06-06","true"},
        {"34", "Ignace", "Quiron", "IgnaceQuiron@insa-strasbourg.fr","pokemon4", "2001-09-20","true"},
        {"35", "Aleron", "Fréchette", "AleronFrechette@insa-strasbourg.fr","voiturerouge", "2001-10-15","true"},
        {"36", "Fortun", "Savoie", "FortunSavoie@insa-strasbourg.fr","travailleurfrontalier", "2001-02-18","true"},
        {"37", "Fusberta", "Dufour", "FusbertaDufour@insa-strasbourg.fr"," thdbadvc54!", "2000-11-23","true"},
        {"38", "Merle", "Dupont", "MerleDupont@insa-strasbourg.fr", "Ggezf44", "1999-01-23","true"},
        {"39", "Ferrau", "Déziel", "FerrauDeziel@insa-strasbourg.fr","Gkzjdbacv15",  "2001-03-24","true"},
        {"40", "Joseph", "Laforge", "JosephLaforge@insa-strasbourg.fr","GKbdb1qn",  "2000-10-28","true"},
        {"41", "David", "Laderoute", "DavidLaderoute@insa-strasbourg.fr","MatthieuLutzlpb",  "2001-04-15","true"},
        {"42", "Eugène", "Busson", "EugeneBusson@insa-strasbourg.fr","yaatiktok",  "2000-07-15","true"},
        {"43", "Joséphine", "Chandonnet", "JosephineChandonnet@insa-strasbourg.fr","salutsalut",  "2001-12-10","true"},
        {"44", "Ambra", "Bourget", "AmbraBourget@insa-strasbourg.fr","Fzjfknbv",  "2000-03-19","true"},
        {"45", "Merle", "Lapointe", "MerleLapointe@insa-strasbourg.fr","InfoBaseDeDonnee",  "2000-08-29","true"},
        {"46", "Arthur", "Sevier", "ArthurSevier@insa-strasbourg.fr", "HGejkd", "2000-05-24","true"},
        {"47", "Quennel", "Verreau", "QuennelVerreau@insa-strasbourg.fr","ZFhdbnfz", "2000-08-23","true"},
        {"48", "Aubrey", "Gauthier", "AubreyGauthier@insa-strasbourg.fr", "DFsnjkzf", "2000-02-09","true"},
        {"49", "Gill", "Vaillancour", "GillVaillancour@insa-strasbourg.fr", "Sjgioemnd65", "2000-09-26","true"},
        {"50", "Talon", "Rhéaume", "TalonRheaume@insa-strasbourg.fr","dzFaknfv1",  "2000-04-25","true"},
        {"51", "Latimer", "Morneau", "LatimerMorneau@insa-strasbourg.fr","PommePoire7",  "2001-03-25","true"},
        {"52", "Chapin", "Sansouci", "ChapinSansouci@insa-strasbourg.fr", "BananeFraise",  "2001-04-26","true"},
        {"53", "Cosette", "Chesnay", "CosetteChesnay@insa-strasbourg.fr","VanilleChocolat",  "2001-08-18","true"},
        {"54", "Nicole", "Gregoire", "NicoleGregoire@insa-strasbourg.fr","SauceEpicee",  "1999-02-06","true"},
        {"55", "Curtis", "Larocque", "CurtisLarocque@insa-strasbourg.fr","DonutSucreAuSucre", "1999-06-29","true"},
        {"56", "Philippe", "Audet", "PhilippeAudet@insa-strasbourg.fr", "Tftyako", "1999-07-10","true"},
        {"57", "Arnaud", "Rouze", "ArnaudRouze@insa-strasbourg.fr", "scottdgb", "1999-06-04","true"},
        {"58", "Sumner", "Bizier", "SumnerBizier@insa-strasbourg.fr","ApexLegend",  "1999-05-28","true"},
        {"59", "Rosamonde", "Laisné", "RosamondeLaisne@insa-strasbourg.fr", "JusticeLeague", "2000-04-04","true"},
        {"60", "Rive", "Beauchamps", "RiveBeauchamps@ginsa-strasbourg.fr","BruceWayneEstBatman",  "2000-05-18","true"},
        {"61", "Lotye", "Angélil", "LotyeAngelil@insa-strasbourg.fr", "djiflzb", "2000-04-16","true"},
        {"62", "Jesper", "Chartier", "JesperChartier@insa-strasbourg.fr","SDjzefjkbv",  "2000-06-22","true"},
        {"63", "Armina", "Langlais", "ArminaLanglais@insa-strasbourg.fr", "DJFzbfbn","1998-06-17","true"},
        {"64", "Brigitte", "Pelchat", "BrigittePelchat@insa-strasbourg.fr","Djfzakb",  "1997-11-06","true"},
        {"65", "Henry", "Brisebois", "HenryBrisebois@insa-strasbourg.fr","KHrjfn",  "1999-11-07","true"},
        {"66", "Matilda", "Sevier", "MatildaSevier@insa-strasbourg.fr","Tjekilbvhjl", "2001-07-03","true"},
        {"67", "Minette", "Roy", "MinetteRoy@insa-strasbourg.fr","HJriomnbjfd",  "2001-12-11","true"},
        {"68", "Burkett", "Guertin", "BurkettGuertin@insa-strasbourg.fr", "htopn,kfl", "2001-02-25","true"},
        {"69", "Halette", "Barteaux", "HaletteBarteaux@insa-strasbourg.fr", "JLpynfkz", "2000-11-18","true"},
        {"70", "Jewel", "Fournier", "JewelFournier@insa-strasbourg.fr","egfnjker", "2001-10-26","true"},
        {"71", "Raina", "Garcia", "RainaGarcia@insa-strasbourg.fr", "EGjeiuvb", "2000-08-27","true"},
        {"72", "Peppin", "Ménard", "PeppinMenard@insa-strasbourg.fr","GFEzgviogn", "2000-07-15","true"},
        {"73", "Fealty", "Allard", "FealtyAllard@insa-strasbourg.fr", "DVkfoivn","2000-06-30","true"},
        {"74", "Percy", "Bériault", "PercyBeriault@insa-strasbourg.fr","Rkothnv", "2000-12-14","true"},
        {"75", "Merlin", "Massé", "MerlinMasse@insa-strasbourg.fr","syrielzfjiu", "2000-10-25","true"},
        {"76", "Damiane", "Vernadeau", "DamianeVernadeau@insa-strasbourg.fr","bolognaise>carbo",  "2000-10-20","true"},
        {"77", "Byron", "LaGrande", "ByronLaGrande@insa-strasbourg.fr","sushipasbon", "2000-01-25","true"},
        {"78", "Sibyla", "Roux", "SibylaRoux@insa-strasbourg.fr", "tonystarkXx", "2000-03-22","true"},
        {"79", "Joséphine", "Grivois", "JosephineGrivois@insa-strasbourg.fr","sncf=retard",  "2000-08-23","true"},
        {"80", "Pascaline", "DuLin", "PascalineDuLin@insa-strasbourg.fr","marchedenoel", "2000-10-19","true"},
        {"81", "Galatee", "Bordeaux", "GalateeBordeaux@insa-strasbourg.fr", "yeeeepnji", "2000-01-22","true"},
        {"82", "Pomeroy", "Fournier", "PomeroyFournier@insa-strasbourg.fr", "donda15","2002-04-12","true"},
        {"83", "Artus", "Thibodeau", "ArtusThibodeau@insa-strasbourg.fre", "Efgkoe", "2000-02-25","true"},
        {"84", "Arianne", "CinqMars", "ArianneCinqMars@insa-strasbourg.fr","VFeiovne", "2001-06-06","true"},
        {"85", "Curtis", "Mainville", "CurtisMainville@insa-strasbourg.fr","claviersouris", "2001-10-02","true"},
        {"86", "Viollette", "Bélanger", "ViolletteBelanger@ginsa-strasbourg.fr","manettedejeu",  "2001-10-06","true"},
        {"87", "Warrane", "Raymond", "WarraneRaymond@insa-strasbourg.fr", "lessiestescestbien", "2001-11-11","true"},
        {"88", "Rosemarie", "Roux", "RosemarieRoux@insa-strasbourg.fr", "dormircestlavie", "2001-06-07","true"},
        {"89", "Bradamate", "Rossignol", "BradamateRossignol@insa-strasbourg.fr","jaimepaslecovid", "2001-06-25","true"},
        {"90", "Céline", "Bertrand", "CelineBertrand@insa-strasbourg.fr", "jfzomfhz", "2001-05-12","true"},
        {"91", "Françoise", "Raymond", "FrancoiseRaymond@insa-strasbourg.fr","KVeibc",  "2000-04-17","true"},
        {"92", "Granville", "Baron", "GranvilleBaron@insa-strasbourg.fr", "Kzefizbvvz", "2001-06-08","true"},
        {"93", "Serge", "Caya", "SergeCaya@insa-strasbourg.fr", "Kbirbvzlo", "2001-01-03","true"},
        {"94", "Dixie", "Couet", "DixieCouet@insa-strasbourg.fr", "VKeojvnzkz", "2001-06-21","true"},
        {"95", "Melusina", "Barrientos", "MelusinaBarrientos@insa-strasbourg.fr", "Veiovhbe,","2001-08-03","true"},
        {"96", "Isaac", "Auclair", "IsaacAuclair@insa-strasbourg.fr", "Vekovbzkjbb", "2001-06-22","true"},
        {"97", "Jeannine", "Poulin", "JeanninePoulin@insa-strasbourg.fr", "VEveubvb","2001-01-01","true"},
        {"98", "Maurice", "Pirouet", "MauricePirouet@insa-strasbourg.fr", "jTrbzo", "1999-10-28","true"},
        {"99", "Marc", "Meunier", "MarcMeunier@insa-strasbourg.fr","HBeiovndm",  "2000-09-03","true"},
        {"100", "Valérie", "Cressac", "ValerieCressac@insa-strasbourg.fr","Jyerg,n",  "1998-06-30","true"},
        {"101", "Daniel", "Craig", "DanielCraig@insa-strasbourg.fr", "Vreomnb", "1998-02-03","true"},
        {"102", "Brad", "Pitt", "BradPitt@insa-strasbourg.fr", "aefvnt", "2003-12-18","true"},
        {"103", "Angélina", "Jolie", "AngelinaJolie@insa-strasbourg.fr", "Broeibp^n", "2003-05-22","true"},
        {"104", "Scarlett", "Johansson", "ScarlettJohansson@insa-strasbourg.fr","Rkopbnek",  "2002-02-03","true"},
        {"105", "Lionel", "Messi", "LionelMessi@insa-strasbourg.fr","hkemVe",  "2000-08-15","true"},
        {"106", "Teddy", "Riner", "TeddyRiner@insa-strasbourg.fr","DNgir",  "2000-04-07","true"},
        {"107", "Lewis", "Hamilton", "LewisHamilton@insa-strasbourg.fr","EKRolv",  "2002-01-07","true"},
        {"108", "Charles", "Leclerc", "CharlesLeclerc@insa-strasbourg.fr","EDKoeq",  "2002-10-16","true"},
        {"109", "Max", "Verstappen", "MaxVerstappen@insa-strasbourg.fr","Dbvozezf",  "2002-09-30","true"},
        {"110", "Valtteri", "Bottas", "ValterriBottas@insa-strasbourg.fr","FDkoev",  "2001-08-28","true"},
        {"111", "Sebastian", "Vettel", "SebastianVettel@insa-strasbourg.fr","VKopac",  "2001-03-07","true"},
        {"112", "Sergio", "Pérez", "SergioPerez@insa-strasbourg.fr","VDSjkoizf",  "2001-10-26","true"},
        {"113", "Daniel", "Ricciardo", "DanielRicciardo@insa-strasbourg.fr","Fkozpc",  "2000-01-07","true"},
        {"114", "Elerson", "Echiéjilé", "EldersonEchiejile@insa-strasbourg.fr","sVikzf",  "2000-01-20","true"},
        {"115", "Erling", "Haaland", "ErlingHaaland@insa-strasbourg.fr","SD?jizv",  "2001-07-21","true"},
        {"116", "Karim", "Benzema", "KarimBenzema@insa-strasbourg.fr","VJbdlnv",  "2001-12-19","true"},
        {"117", "Cristiano", "Ronaldo", "CristianoRonaldo@insa-strasbourg.fr","VDKozevs",  "1999-08-21","true"},
        {"118", "Bruce", "Wayne", "BruceWayne@insa-strasbourg.fr","joker",  "2002-04-17","true"},
        {"119", "Peter", "Parker", "PeterParker@insa-strasbourg.fr","maryjane",  "2002-08-15","true"},
        {"120", "Thor", "Odinson", "ThorOdinson@insa-strasbourg.fr","foudre",  "2002-08-01","true"},
        {"121", "Lounès", "Youxes", "Lounes@insa-strasbourg.fr","015705jgdfkjsbvh",  "1984-07-20","true"},
   };

    public static List<String> noms() {
        return Arrays.stream(ETUDIANT).map((t) -> {
            return t[2];
        }).toList();
    }

    public static List<String> prenoms() {
        return Arrays.stream(ETUDIANT).map((t) -> {
            return t[1];
        }).toList();
    }

    public static List<String> adresse() {
        return Arrays.stream(ETUDIANT).map((t) -> {
            return t[3];
        }).toList();
    }

    public static List<String> mdp() {
        return Arrays.stream(ETUDIANT).map((t) -> {
            return t[4];
        }).toList();
    }

    public static List<String> datenaiss() {
        return Arrays.stream(ETUDIANT).map((t) -> {
            return t[5];
        }).toList();
    }

    public static List<String> dispo() {
        return Arrays.stream(ETUDIANT).map((t) -> {
            return t[6];
        }).toList();
    }
     public static void tableEtudiant(Connection con) throws SQLException {
       //méthode permettant de créer la table qui va contenir les étudiants
        try (Statement st = con.createStatement()) {
            st.executeUpdate("""
                         create table Etudiant(
                         id integer primary key generated always as identity,
                         nom varchar(50) not null,
                         prenom varchar(50) not null,
                         adresse varchar(50) not null,
                         mdp varchar(50) not null,
                         dateNaissance date not null,
                         disponibilite varchar(50) not null,
                         classe varchar(50) not null
                         )
                         """);
        }
        //on récupère ensuite les différents éléments du tableau ETUDIANT
        List<String> noms = Initialisation.noms();
        List<String> prenoms = Initialisation.prenoms();
        List<String> adresses = Initialisation.adresse();
        List<String> mdps = Initialisation.mdp();
        List<String> dates = Initialisation.datenaiss();
        for (int i = 0; i < ETUDIANT.length; i++) {
            //on ajoute chaque étudiant du tableau dans la table etudiant de la base de donnée
            Commandes.AjoutEtudiant(con, noms.get(i), prenoms.get(i), adresses.get(i), mdps.get(i), dates.get(i), "dispo", "classe");
        }
    }
    
    //----------CREATION DE LA TABLE DES MODULES----------
    
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
     public static void tableModule(Connection con) throws SQLException {
       //méthode permettant de créer la table qui va contenir les étudiants
        try (Statement st = con.createStatement()) {
            st.executeUpdate("""
                         create table Module(
                         id integer primary key generated always as identity,
                         intitulé varchar(50) not null,
                         description text not null,
                         nbPlaceMax integer not null,
                         nbPlaceMin integer not null,
                         classeacceptee varchar(100) not null,
                         
                         )
                         """);
        }
        List<String> intitules = Initialisation.intitule();
        List<String> descriptions = Initialisation.description();
        List<String> nbrplacemaxs = Initialisation.nbrplacemax();
        List<String> nbrplacemins = Initialisation.nbrplacemin();
        List<String> classeacceptees = Initialisation.classeacceptee();
        for (int i = 0; i < ETUDIANT.length; i++) {
            Commandes.AjoutModule(con, intitules.get(i), descriptions.get(i), nbrplacemaxs.get(i), nbrplacemins.get(i), classeacceptees.get(i));
        }
        
        
    }
    
}
