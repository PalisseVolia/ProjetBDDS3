package bdd;

import java.util.Arrays;
import java.util.List;
import java.sql.*;

// =======================================================================================
// Classe permettant d'initialiser la base de donnée
// =======================================================================================

public class Initialisation {

    // méthode à appeler pour initialiser la base de donnée
    public static void init() {
        try (Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass")) {
            //suppression des tables
            Commandes.tabledrop(con, "etudiant");
            System.out.println("etudiant supprimee");
            Commandes.tabledrop(con, "admin");
            System.out.println("adm supprimee");
            Commandes.tabledrop(con, "module");
            System.out.println("mod supprimee");
            Commandes.tabledrop(con, "semestre");
            System.out.println("semsupprimee");
            Commandes.tabledrop(con, "grpmodule");
            System.out.println("grpmod supprimee");
            Commandes.tabledrop(con, "voeux");
            System.out.println("voeu supprimee");
            System.out.println("Suppression des tables terminée");

            //création des tables
            tableEtudiant(con);
            System.out.println("etudiant cree");
            tableAdmin(con);
            System.out.println("admin cree");
            tableModule(con);
            System.out.println("module cree");
            tableSemestre(con);
            System.out.println("semestre cree");
            tableGrpModule(con);
            System.out.println("grpmod cree");
            tableVoeux(con);
            System.out.println("voeux cree");
            System.out.println("Création des tables terminée");

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    //-----------------------------------------------------------------
    //              CREATION DE LA TABLE DES ADMINS
    //-----------------------------------------------------------------

    public static void tableAdmin(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.executeUpdate("""
                        create table Admin(
                        id integer primary key generated always as identity,
                        nom varchar(200) not null,
                        prenom varchar(200) not null,
                        adresse varchar(200) not null,
                        mdp varchar(300) not null
                        )
                        """);
        }
        List<String> noms = Initialisation.nomsAdmin();
        List<String> prenoms = Initialisation.prenomsAdmin();
        List<String> adresses = Initialisation.adresseAdmin();
        List<String> mdps = Initialisation.mdpAdmin();
        for (int i = 0; i < ADMIN.length; i++) {
            Commandes.AjoutAdmin(con, noms.get(i), prenoms.get(i), adresses.get(i), mdps.get(i));
        }
    }

    public static final String[][] ADMIN = new String[][] {
            { "Thibaut", "Waechter", "ThibautWaechter@insa-strasbourg.fr", "mdp1"},
            { "Thibault", "Tostain", "ThibaultTostain@insa-strasbourg.fr", "mdp2"},
            { "Volia", "Palisse", "VoliaPalisse@insa-strasbourg.fr", "mdp3"},
            { "Francois", "de Bertrand de Beuvron", "FrancoisDeBertrandDeBeuvron@insa-strasbourg.fr", "tititoto" },
            { "Amadou", "Coulibaly", "AmadouCoulibaly@insa-strasbourg.fr", "tititoto" },
    };

    public static List<String> prenomsAdmin() {
        //méthode renvoyant le prénom de tous les administrateurs contenus dans la liste initiale ADMIN
        return Arrays.stream(ADMIN).map((t) -> {
            return t[0];
        }).toList();
    }

    public static List<String> nomsAdmin() {
        //méthode renvoyant le nom des admins
        return Arrays.stream(ADMIN).map((t) -> {
            return t[1];
        }).toList();
    }

    public static List<String> adresseAdmin() {
        //méthode renvoyant l'adresse des admins
        return Arrays.stream(ADMIN).map((t) -> {
            return t[2];
        }).toList();
    }

    public static List<String> mdpAdmin() {
        //méthode renvoyant le mot de passe des admins
        return Arrays.stream(ADMIN).map((t) -> {
            return t[3];
        }).toList();
    }

    //-----------------------------------------------------------------
    //              CREATION DE LA TABLE DES ETUDIANTS
    //-----------------------------------------------------------------

    public static void tableEtudiant(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.executeUpdate("""
                        create table Etudiant(
                        id integer primary key generated always as identity,
                        nom varchar(200) not null,
                        prenom varchar(200) not null,
                        adresse varchar(200) not null,
                        mdp varchar(300) not null,
                        dateNaissance date not null ,
                        disponibilite varchar(200) not null,
                        classe varchar(200) not null
                        )
                        """);
        }
        List<String> noms = Initialisation.noms();
        List<String> prenoms = Initialisation.prenoms();
        List<String> adresses = Initialisation.adresse();
        List<String> mdps = Initialisation.mdp();
        //on hash le mot de passe
        List<String> dates = Initialisation.datenaiss();
        List<String> dispo= Initialisation.dispo();
        List<String> classe = Initialisation.classe();
        for (int i = 0; i < ETUDIANT.length; i++) {
            //on ajoute tout les étudiants à la bdd
            Commandes.AjoutEtudiant(con, noms.get(i), prenoms.get(i), adresses.get(i),mdps.get(i), dates.get(i), dispo.get(i), classe.get(i));
        }
    }

    public static final String[][] ETUDIANT = new String[][]{
        {"Pauline", "Giroux", "PaulineGiroux@insa-strasbourg.fr", "Milita!recreux55",  "2002-05-07", "dispo","GE2"},
        {"Peppin", "David", "PeppinDavid@insa-strasbourg.fr","AttaqueT!tanesque0", "2002-01-11","dispo","GE2"},
        {"Aurore", "Benjamin", "AuroreBenjamin@insa-strasbourg.fr","ViveJava47?",  "2002-10-18","dispo","GE2"},
        {"Dominic", "Rochefort", "DominicRochefort@insa-strasbourg.fr","?PublicStatic02",  "2002-08-07","dispo","GE2"},
        {"Gérard", "Parenteau", "GerardParenteau@insa-strasbourg.fr", "Pizza4Fromage!", "2002-06-29","dispo","GE2"},
        {"Melusina", "Simon", "MelusinaSimon@insa-strasbourg.fr","InsaStras67!", "2002-01-27","dispo","GE2"},
        {"Calandre", "Béland", "CalandreBeland@insa-strasbourg.fr", "?Jb007",  "2002-03-20","dispo","GE2"},
        {"Édith", "Caya", "EdithCaya@insa-strasbourg.fr", "XxMessixX05!","2002-03-25","dispo","GE2"},
        {"Eugène", "Chrétien", "EugeneChretien@insa-strasbourg.fr","LeBronJames7!8", "2002-03-16","dispo","GE2"},
        {"Searlas", "Guay", "SearlasGuay@insa-strasbourg.fr", "Xaeo14!5B", "2002-01-28","dispo","GE2"},
        {"Pierrette", "Gadbois", "PierretteGadbois@insa-strasbourg.fr","fnzhj65H", "2002-05-04","dispo","GE2"},
        {"Zurie", "Bonami", "ZurieBonami@insa-strasbourg.fr","nfjezG14",  "2002-05-23","dispo","GE2"},
        {"Alphonsine", "Cloutier", "AlphonsineCloutier@insa-strasbourg.fr","dhebdG4","2002-10-03","dispo","GE2"},
        {"Pierpont", "Dumont", "PierpontDumont@insa-strasbourg.fr",  "thibautlbp01", "2002-11-05","dispo","GE2"},
        {"Yvon", "Primeau", "YvonPrimeau@insa-strasbourg.fr", "thibautsecritsansl88","2002-09-21","dispo","GE2"},
        {"Albertine", "Rossignol", "AlbertineRossignol@insa-strasbourg.fr","VoliaRugueux0", "2002-10-28","dispo","GM2"},
        {"Christien", "Laderoute", "ChristienLaderoute@insa-strasbourg.fr","CouscousMerguez5", "2002-09-09","dispo","GM2"},
        {"Lance", "Gosselin", "LanceGosselin@insa-strasbourg.fr", "PinkFloyd5", "2002-10-19","dispo","GM2"},
        {"Somerville", "Brisebois", "SomervilleBrisebois@insa-strasbourg.fr","Juice999!",  "2002-08-15","dispo","GM2"},
        {"Auriville", "Beauchemin", "AurivilleBeauchemin@insa-strasbourg.fr","RocketLeague4", "2001-03-23","dispo","GM2"},
        {"Hugues", "Houle", "HuguesHoule@insa-strasbourg.fr",  "fhjs!fFz4", "2001-10-16","dispo","GM2"},
        {"Ogier", "Josseaume", "OgierJosseaume@insa-strasbourg.fr","F!Sgjr5", "2001-03-07","dispo","GM2"},
        {"Armina", "Massé", "ArminaMasse@insa-strasbourg.fr","fDSfdfFz4!","2001-04-18","dispo","GM2"},
        {"Océane", "Lamare", "OceaneLamare@insa-strasbourg.fr","R!EZzfz4", "2001-04-15","dispo","GM2"},
        {"Beltane", "Pariseau", "BeltanePariseau@insa-strasbourg.fr","Jgfh!FGsFz4", "2001-01-08","dispo","GM2"},
        {"Maryse", "Guibord", "MaryseGuibord@insa-strasbourg.fr",  "HJ!Lrdq!Fz4","2001-01-21","dispo","GM2"},
        {"Émilie", "Desnoyers", "EmilieDesnoyers@insa-strasbourg.fr","Frdgzjks!fFz4",  "2001-07-05","dispo","GM2"},
        {"Melville", "Duplessis", "MelvilleDuplessis@insa-strasbourg.fr","Mh!ytkere4",  "2001-06-06","dispo","GM2"},
        {"Michel", "Baril", "MichelBaril@insa-strasbourg.fr","FZkfn?c475", "2001-11-14","dispo","GM2"},
        {"Bertrand", "Turgeon", "BertrandTurgeon@insa-strasbourg.fr","NarutoHokage", "2001-12-03","dispo","GM2"},
        {"Landers", "Lemieux", "LandersLemieux@insa-strasbourg.fr","ViveSpiderman054!", "2001-03-05","dispo","GM2"},
        {"La Roux", "Cotuand", "LaRouxCotuand@insa-strasbourg.fr","ProutLol:!4", "2001-01-30","dispo","GE3"},
        {"Serge", "Givry", "SergeGivry@insa-strasbourg.fr","fdhjzffzh2", "2001-06-06","dispo","GE3"},
        {"Ignace", "Quiron", "IgnaceQuiron@insa-strasbourg.fr","pokemon4", "2001-09-20","dispo","GE3"},
        {"Aleron", "Fréchette", "AleronFrechette@insa-strasbourg.fr","voiturerouge", "2001-10-15","dispo","GE3"},
        {"Fortun", "Savoie", "FortunSavoie@insa-strasbourg.fr","travailleurfrontalier", "2001-02-18","dispo","GE3"},
        {"Fusberta", "Dufour", "FusbertaDufour@insa-strasbourg.fr"," thdbadvc54!", "2000-11-23","dispo","GE3"},
        {"Merle", "Dupont", "MerleDupont@insa-strasbourg.fr", "Ggezf44", "1999-01-23","dispo","GE3"},
        {"Ferrau", "Déziel", "FerrauDeziel@insa-strasbourg.fr","Gkzjdbacv15",  "2001-03-24","dispo","GE3"},
        {"Joseph", "Laforge", "JosephLaforge@insa-strasbourg.fr","GKbdb1qn",  "2000-10-28","dispo","GE3"},
        {"David", "Laderoute", "DavidLaderoute@insa-strasbourg.fr","MatthieuLutzlpb",  "2001-04-15","dispo","GE3"},
        {"Eugène", "Busson", "EugeneBusson@insa-strasbourg.fr","yaatiktok",  "2000-07-15","dispo","GE3"},
        {"Joséphine", "Chandonnet", "JosephineChandonnet@insa-strasbourg.fr","salutsalut",  "2001-12-10","dispo","GE3"},
        {"Ambra", "Bourget", "AmbraBourget@insa-strasbourg.fr","Fzjfknbv",  "2000-03-19","dispo","GE3"},
        {"Merle", "Lapointe", "MerleLapointe@insa-strasbourg.fr","InfoBaseDeDonnee",  "2000-08-29","dispo","GE3"},
        {"Arthur", "Sevier", "ArthurSevier@insa-strasbourg.fr", "HGejkd", "2000-05-24","dispo","GE3"},
        {"Quennel", "Verreau", "QuennelVerreau@insa-strasbourg.fr","ZFhdbnfz", "2000-08-23","dispo","GE3"},
        {"Aubrey", "Gauthier", "AubreyGauthier@insa-strasbourg.fr", "DFsnjkzf", "2000-02-09","dispo","GM3"},
        {"Gill", "Vaillancour", "GillVaillancour@insa-strasbourg.fr", "Sjgioemnd65", "2000-09-26","dispo","GM3"},
        {"Talon", "Rhéaume", "TalonRheaume@insa-strasbourg.fr","dzFaknfv1",  "2000-04-25","dispo","GM3"},
        {"Latimer", "Morneau", "LatimerMorneau@insa-strasbourg.fr","PommePoire7",  "2001-03-25","dispo","GM3"},
        {"Chapin", "Sansouci", "ChapinSansouci@insa-strasbourg.fr", "BananeFraise",  "2001-04-26","dispo","GM3"},
        {"Cosette", "Chesnay", "CosetteChesnay@insa-strasbourg.fr","VanilleChocolat",  "2001-08-18","dispo","GM3"},
        {"Nicole", "Gregoire", "NicoleGregoire@insa-strasbourg.fr","SauceEpicee",  "1999-02-06","dispo","GM3"},
        {"Curtis", "Larocque", "CurtisLarocque@insa-strasbourg.fr","DonutSucreAuSucre", "1999-06-29","dispo","GM3"},
        {"Philippe", "Audet", "PhilippeAudet@insa-strasbourg.fr", "Tftyako", "1999-07-10","dispo","GM3"},
        {"Arnaud", "Rouze", "ArnaudRouze@insa-strasbourg.fr", "scottdgb", "1999-06-04","dispo","GM3"},
        {"Sumner", "Bizier", "SumnerBizier@insa-strasbourg.fr","ApexLegend",  "1999-05-28","dispo","GM3"},
        {"Rosamonde", "Laisné", "RosamondeLaisne@insa-strasbourg.fr", "JusticeLeague", "2000-04-04","dispo","GM3"},
        {"Rive", "Beauchamps", "RiveBeauchamps@ginsa-strasbourg.fr","BruceWayneEstBatman",  "2000-05-18","dispo","GM3"},
        {"Lotye", "Angélil", "LotyeAngelil@insa-strasbourg.fr", "djiflzb", "2000-04-16","dispo","GM3"},
        {"Jesper", "Chartier", "JesperChartier@insa-strasbourg.fr","SDjzefjkbv",  "2000-06-22","dispo","GM3"},
        {"Armina", "Langlais", "ArminaLanglais@insa-strasbourg.fr", "DJFzbfbn","1998-06-17","dispo","GM3"},
        {"Brigitte", "Pelchat", "BrigittePelchat@insa-strasbourg.fr","Djfzakb",  "1997-11-06","dispo","GM3"},
        {"Henry", "Brisebois", "HenryBrisebois@insa-strasbourg.fr","KHrjfn",  "1999-11-07","dispo","GM3"},
        {"Matilda", "Sevier", "MatildaSevier@insa-strasbourg.fr","Tjekilbvhjl", "2001-07-03","dispo","GM3"},
        {"Minette", "Roy", "MinetteRoy@insa-strasbourg.fr","HJriomnbjfd",  "2001-12-11","dispo","GM3"},
        {"Burkett", "Guertin", "BurkettGuertin@insa-strasbourg.fr", "htopn,kfl", "2001-02-25","dispo","GM3"},
        {"Halette", "Barteaux", "HaletteBarteaux@insa-strasbourg.fr", "JLpynfkz", "2000-11-18","dispo","GE4"},
        {"Jewel", "Fournier", "JewelFournier@insa-strasbourg.fr","egfnjker", "2001-10-26","dispo","GE4"},
        {"Raina", "Garcia", "RainaGarcia@insa-strasbourg.fr", "EGjeiuvb", "2000-08-27","dispo","GE4"},
        {"Peppin", "Ménard", "PeppinMenard@insa-strasbourg.fr","GFEzgviogn", "2000-07-15","dispo","GE4"},
        {"Fealty", "Allard", "FealtyAllard@insa-strasbourg.fr", "DVkfoivn","2000-06-30","dispo","GE4"},
        {"Percy", "Bériault", "PercyBeriault@insa-strasbourg.fr","Rkothnv", "2000-12-14","dispo","GE4"},
        {"Merlin", "Massé", "MerlinMasse@insa-strasbourg.fr","syrielzfjiu", "2000-10-25","dispo","GE4"},
        {"Damiane", "Vernadeau", "DamianeVernadeau@insa-strasbourg.fr","bolognaise>carbo",  "2000-10-20","dispo","GE4"},
        {"Byron", "LaGrande", "ByronLaGrande@insa-strasbourg.fr","sushipasbon", "2000-01-25","dispo","GE4"},
        {"Sibyla", "Roux", "SibylaRoux@insa-strasbourg.fr", "tonystarkXx", "2000-03-22","dispo","GE4"},
        {"Joséphine", "Grivois", "JosephineGrivois@insa-strasbourg.fr","sncf=retard",  "2000-08-23","dispo","GE4"},
        {"Pascaline", "DuLin", "PascalineDuLin@insa-strasbourg.fr","marchedenoel", "2000-10-19","dispo","GE4"},
        {"Galatee", "Bordeaux", "GalateeBordeaux@insa-strasbourg.fr", "yeeeepnji", "2000-01-22","dispo","GE4"},
        {"Pomeroy", "Fournier", "PomeroyFournier@insa-strasbourg.fr", "donda15","2002-04-12","dispo","GE4"},
        {"Artus", "Thibodeau", "ArtusThibodeau@insa-strasbourg.fr", "Efgkoe", "2000-02-25","dispo","GE4"},
        {"Arianne", "CinqMars", "ArianneCinqMars@insa-strasbourg.fr","VFeiovne", "2001-06-06","dispo","GE4"},
        {"Curtis", "Mainville", "CurtisMainville@insa-strasbourg.fr","claviersouris", "2001-10-02","dispo","GE4"},
        {"Viollette", "Bélanger", "ViolletteBelanger@ginsa-strasbourg.fr","manettedejeu",  "2001-10-06","dispo","GE4"},
        {"Warrane", "Raymond", "WarraneRaymond@insa-strasbourg.fr", "lessiestescestbien", "2001-11-11","dispo","GE4"},
        {"Rosemarie", "Roux", "RosemarieRoux@insa-strasbourg.fr", "dormircestlavie", "2001-06-07","dispo","GE4"},
        {"Bradamate", "Rossignol", "BradamateRossignol@insa-strasbourg.fr","jaimepaslecovid", "2001-06-25","dispo","GE4"},
        {"Céline", "Bertrand", "CelineBertrand@insa-strasbourg.fr", "jfzomfhz", "2001-05-12","dispo","GE4"},
        {"Françoise", "Raymond", "FrancoiseRaymond@insa-strasbourg.fr","KVeibc",  "2000-04-17","dispo","GE4"},
        {"Granville", "Baron", "GranvilleBaron@insa-strasbourg.fr", "Kzefizbvvz", "2001-06-08","dispo","GE4"},
        {"Serge", "Caya", "SergeCaya@insa-strasbourg.fr", "Kbirbvzlo", "2001-01-03","dispo","GE4"},
        {"Dixie", "Couet", "DixieCouet@insa-strasbourg.fr", "VKeojvnzkz", "2001-06-21","dispo","GE4"},
        {"Melusina", "Barrientos", "MelusinaBarrientos@insa-strasbourg.fr", "Veiovhbe,","2001-08-03","dispo","GE4"},
        {"Isaac", "Auclair", "IsaacAuclair@insa-strasbourg.fr", "Vekovbzkjbb", "2001-06-22","dispo","GCE2"},
        {"Jeannine", "Poulin", "JeanninePoulin@insa-strasbourg.fr", "VEveubvb","2001-01-01","dispo","GCE2"},
        {"Maurice", "Pirouet", "MauricePirouet@insa-strasbourg.fr", "jTrbzo", "1999-10-28","dispo","GCE2"},
        {"Marc", "Meunier", "MarcMeunier@insa-strasbourg.fr","HBeiovndm",  "2000-09-03","dispo","GCE2"},
        {"Valérie", "Cressac", "ValerieCressac@insa-strasbourg.fr","Jyerg,n",  "1998-06-30","dispo","GCE2"},
        {"Daniel", "Craig", "DanielCraig@insa-strasbourg.fr", "Vreomnb", "1998-02-03","dispo","GCE2"},
        {"Brad", "Pitt", "BradPitt@insa-strasbourg.fr", "aefvnt", "2003-12-18","dispo","GCE2"},
        {"Angélina", "Jolie", "AngelinaJolie@insa-strasbourg.fr", "Broeibp^n", "2003-05-22","dispo","GCE2"},
        {"Scarlett", "Johansson", "ScarlettJohansson@insa-strasbourg.fr","Rkopbnek",  "2002-02-03","dispo","GCE2"},
        {"Lionel", "Messi", "LionelMessi@insa-strasbourg.fr","hkemVe",  "2000-08-15","dispo","GCE2"},
        {"Teddy", "Riner", "TeddyRiner@insa-strasbourg.fr","DNgir",  "2000-04-07","dispo","GCE2"},
        {"Lewis", "Hamilton", "LewisHamilton@insa-strasbourg.fr","EKRolv",  "2002-01-07","dispo","GCE2"},
        {"Charles", "Leclerc", "CharlesLeclerc@insa-strasbourg.fr","EDKoeq",  "2002-10-16","dispo","GCE2"},
        {"Max", "Verstappen", "MaxVerstappen@insa-strasbourg.fr","Dbvozezf",  "2002-09-30","dispo","GCE2"},
        {"Valtteri", "Bottas", "ValterriBottas@insa-strasbourg.fr","FDkoev",  "2001-08-28","dispo","GCE2"},
        {"Sebastian", "Vettel", "SebastianVettel@insa-strasbourg.fr","VKopac",  "2001-03-07","dispo","GCE2"},
        {"Sergio", "Pérez", "SergioPerez@insa-strasbourg.fr","VDSjkoizf",  "2001-10-26","dispo","GCE2"},
        {"Daniel", "Ricciardo", "DanielRicciardo@insa-strasbourg.fr","Fkozpc",  "2000-01-07","dispo","GCE2"},
        {"Elerson", "Echiéjilé", "EldersonEchiejile@insa-strasbourg.fr","sVikzf",  "2000-01-20","dispo","GCE2"},
        {"Erling", "Haaland", "ErlingHaaland@insa-strasbourg.fr","SD?jizv",  "2001-07-21","dispo","GCE2"},
        {"Karim", "Benzema", "KarimBenzema@insa-strasbourg.fr","VJbdlnv",  "2001-12-19","dispo","GCE2"},
        {"Cristiano", "Ronaldo", "CristianoRonaldo@insa-strasbourg.fr","VDKozevs",  "1999-08-21","dispo","GCE2"},
        {"Bruce", "Wayne", "BruceWayne@insa-strasbourg.fr","joker",  "2002-04-17","dispo","GCE2"},
        {"Peter", "Parker", "PeterParker@insa-strasbourg.fr","maryjane",  "2002-08-15","dispo","GCE2"},
        {"Thor", "Odinson", "ThorOdinson@insa-strasbourg.fr","foudre",  "2002-08-01","dispo","GE4"},
        {"Lounès", "Youbi", "Lounes@insa-strasbourg.fr","015705jgdfkjsbvh",  "1984-07-20","dispo","GE2"},
   };

    public static List<String> noms() {
        return Arrays.stream(ETUDIANT).map((t) -> {
            return t[1];
        }).toList();
    }

    public static List<String> prenoms() {
        return Arrays.stream(ETUDIANT).map((t) -> {
            return t[0];
        }).toList();
    }

    public static List<String> adresse() {
        return Arrays.stream(ETUDIANT).map((t) -> {
            return t[2];
        }).toList();
    }

    public static List<String> mdp() {
        return Arrays.stream(ETUDIANT).map((t) -> {
            return t[3];
        }).toList();
    }

    public static List<String> datenaiss() {
        return Arrays.stream(ETUDIANT).map((t) -> {
            return t[4];
        }).toList();
    }

    public static List<String> dispo() {
        return Arrays.stream(ETUDIANT).map((t) -> {
            return t[5];
        }).toList();
    }

    public static List<String> classe() {
        return Arrays.stream(ETUDIANT).map((t) -> {
            return t[6];
        }).toList();
    }

    //-----------------------------------------------------------------
    //              CREATION DE LA TABLE DES MODULES
    //-----------------------------------------------------------------

    public static void tableModule(Connection con) throws SQLException {
        //méthode permettant de créer la table qui va contenir les modules
        try (Statement st = con.createStatement()) {
            st.executeUpdate("""
                        create table Module(
                        id integer primary key generated always as identity,
                        intitule varchar(100) not null,
                        description text not null,
                        nbPlaceMax integer not null,
                        nbPlaceMin integer not null,
                        classeacceptee varchar(100) not null
                         
                        )
                        """);
        }
        //on récupère ensuite les différents éléments du tableau MODULE
        List<String> intitules = Initialisation.intitule();
        List<String> descriptions = Initialisation.description();
        List<String> nbrplacemaxs = Initialisation.nbrplacemax();
        List<String> nbrplacemins = Initialisation.nbrplacemin();
        List<String> classeacceptees = Initialisation.classeacceptee();
        for (int i = 0; i < MODULE.length; i++) {
            Commandes.AjoutModule(con, intitules.get(i), descriptions.get(i), nbrplacemaxs.get(i), nbrplacemins.get(i), classeacceptees.get(i));
        }
    }

    public static final String[][] MODULE = new String[][]{
        {"ArduinoMaker", "Concevoir un objet piloté par une carte Arduino", "25", "16",  "TOUTE"},
         
        {"Conception inventive et innovation", "L’innovation apparait depuis quelques années comme un mot clé qui fait le buzz dans les discours des managers des entreprises ou de nos dirigeants politiques. Or, aux origines des innovations, c’est souvent l’ingénieur qui invente dès lors qu’il s’agit d’un objet ou d’un système technique plus ou moins complexe. En conséquence, l’activité créative chez l’ingénieur est au coeur de toutes les attentions des industries tournées vers l’innovation. Pour autant, tous les ingénieurs ne sont pas des inventeurs ! Ceci est en partie dû au fait qu’aucune pratique de l’invention (tout au plus quelques techniques comme le brainstorming) ne leur ont été enseignées dans leur parcours de formation.\n" +
        "Comment naissent les inventions qui deviendront des innovations ? Peut-on, au-delà du brainstorming, systématiser leur émergence ? Existe-t-il des théories, des méthodes, des outils de nature à structurer les processus créatifs de façon à garantir son efficience ? Peut-on apprendre à inventer ou améliorer ses capacités inventives ?\n" +
        "L’INSA Strasbourg est depuis de nombreuses années en pointe sur le sujet de l’ingénierie de l’innovation. Ceci est en grande partie dû à l’existence de travaux de recherche sur le sujet de la Conception Inventive dans l’une de ses équipes de recherche. Ce module électif est une première sensibilisation aux techniques d’analyse des objets techniques permettant d’impulser l’innovation par l’invention. Etant conçue pour les ingénieurs, la démarche méthodologique utilisée sera dans un premier temps décrite de façon théorique et agrémentée d’exemples, puis fera l’objet d’un micro-projet en groupe poursuivant un double objectif :\n" +
        "• Placer l’étudiant en situation de projet d’invention dans une équipe etconduire le projet par une méthode cadrant la démarche inventive ;\n" +
        "• Guider chaque équipe vers la construction d’un concept de solution (invention potentielle) qui sera essentiellement virtuelle mais technologiquement crédible.", "25","16", "TOUTE"},
        
        {"Entrepreneuriat 1 : De l'idée au marché", "Être capable d'aborder un projet de création d'activité en mobilisant et en analysant le lien entre une idée et son environnement (marché potentiel, concurrents, clients, contraintes règlementaires).", "25","16",  "TOUTE"},
        
        {"Initiation à la Plasturgie", "A l’issue de cet électif, l’étudiant doit être capable :\n" +
        " Mettre en œuvre des tests dits « hors laboratoire » afin d’identifier rapidement une famille de matière et certaines caractéristiques/propriétés propre à cette matière ;\n" +
        " Identifier et de décrire les principaux procédés de transformation des Thermo-Plastiques (TP), Thermo-Durcissables (TD) et élastomères d’un point de vue : Process, machines, périphériques, outillages, matières transformées, pièces obtenues.\n" +
        " Identifier des signatures procédés ;\n" +
        " Identifier et de décrire les fonctions et les solutions constructives d’outillages d’injection et d’extrusion;\n" +
        " Réaliser l’assemblage par soudage à air chaud ou ultrasons d’une pièce ;\n" +
        "", "25","16",  "TOUTE"},
        
        {"Apprendre à dessiner et communiquer graphiquement", "Apprendre à dessiner", "25", "16", "TOUTE"},
        
        {"Formation diplômante PRAP", "La formation PRAP a pour objectif de permettre au salarié de participer à l'amélioration de ses conditions de travail de manière à réduire les risques d'accidents du travail ou de maladies professionnelles.", "12","8", "TOUTE"},
       
        {"Image(s) of the engineer", "Développer sa culture générale personnelle et son regard critique pour mieux se positionner dans son futur métier d’ingénieur.", "25", "16",  "TOUTE"},
        
        {"Introduction au Design", "Apprendre à travailler ensemble dans une démarche collaborative - étudiants designer et étudiants ingénieur – afin de concevoir et de réaliser des pièces qui seront exposées lors des Designer’s Days à Paris début juin.", "25", "16","TOUTE"},
        
        {"LV2 - Espagnol (Intermédiaire et avancé - Cycle 1)", "L'objectif de ce cours, qui s'adresse aux non débutants, est d'amener les étudiants à reprendre leurs marques en espagnol. Des groupes de niveau seront organisés, de manière à ce que chacun puisse bénéficier d'une pédagogie adaptée et progresser à son rythme.", "25","16", "TOUTE"},
        
        {"LV2 - Allemand (Intermédiaire & Avancé - Cycle 1) 2LF", "Developper ses compétences en allemand", "25", "16", "TOUTE"},
        
        {"Energie électrique renouvelable : photovoltaïque - 1", "En présence :\n" +
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
        "de développement avenir,", "25", "16", "TOUTE" },
        
        {"Filmer la science", "L'objectif premier sera de répondre à la question suivante : quelles formes peut prendre la science lorsqu'elle est filmée, lorsqu'elle est observée au travers du prisme du cinéma ?\n" +
        "Le cinéma est un art visuel et narratif. Il permet de donner corps à des fantasmes, qu'ils soient scientifiques ou non, de créer un univers fictionnel singulier, mais aussi de témoigner de l'existant.\n" +
        "Le cinéma peut être permissif, il peut se jouer des règles scientifiques. Il sait aussi être précis.\n" +
        "Nous dresserons un panorama aussi exhaustif que possible de ce qui existe dans le domaine.", "25", "16", "TOUTE"},
        
        {"Pathologie des ouvrages", "analyses de dossiers « rapports d’inspection détaillés » fournis par les enseignants (1 dossier par groupe de\n" +
        "trois). Etude de dossiers afin d’établir un rapport concernant les désordres observés, les moyens d’analyses\n" +
        "des pathologies et de leur évolutions.", "25", "16", "TOUTE"},
        
        {"Introduction à l'informatique quantique", "L'informatique quantique est le sous-domaine de l'informatique qui traite des calculateurs quantiques utilisant des phénomènes de la mécanique quantique, par opposition à ceux de l'électricité exclusivement, pour l'informatique dite « classique ».", "25", "16","TOUTE"},
        
        {"Statistiques", "Ce cours est une introduction aux statistiques. L'objectif est de présenter ce que permettent de faire les statistiques mais aussi ce qu'elles ne permettent pas de faire.", "25", "16","TOUTE"},
        
        {"Lean Construction : les fondamentaux", "Ce module électif permettra aux futur(e)s ingénieur(e)s de découvrir l’essentiel de la démarche LEAN CONSTRUCTION. Il permettra aux participants d’acquérir les bases de cette démarche innovante, connaitre les outils par la pratique et prendre conscience des gains possibles sur leurs futurs projets de construction en termes de Sécurit Qualit Délais et Coûts (SQDC).", "25", "16","TOUTE"},
        
        {"Big Data", "Le terme de Big Data désigne de vastes ensembles de données collectées par les entreprises, pouvant être explorées et analysées afin d’en dégager des informations exploitables ou utilisées pour des projets de Machine Learning.", "25", "16","TOUTE"},
   
        {"Thermographie infrarouge", "La thermographie infrarouge est une technique non-intrusive de mesure radiométrique basée sur la théorie de rayonnement du corps noir et relève de la cartographie bidimensionnelle de distribution du flux radiatif rayonné par une surface. ", "25", "16","TOUTE"},

        {"Inventive design and Innovation", "Large companies but also smaller ones (SME-SMIs) have all adopted the idea of placing innovation at the heart of their strategy. Thus messages stemming from top management are unambiguous: we must innovate or disappear. But beyond words, very few of them have implemented how to methodologically support the innovation processes.", "25", "16","TOUTE"},

        {"Initiation au BIM (Building Information Modeling)", " BIM désigne les outils de modélisation des informations de la construction implémentés par des applications qui permettent la modélisation des données du bâtiment, d'une structure, d'un édifice ou d'un ouvrage.", "25", "16","TOUTE"},

        
    };

    public static List<String> intitule() {
        return Arrays.stream(MODULE).map((t) -> {
            return t[0];
        }).toList();
    }

    public static List<String> description() {
        return Arrays.stream(MODULE).map((t) -> {
            return t[1];
        }).toList();
    }

    public static List<String> nbrplacemax() {
        return Arrays.stream(MODULE).map((t) -> {
            return t[2];
        }).toList();
    }

    public static List<String> nbrplacemin() {
        return Arrays.stream(MODULE).map((t) -> {
            return t[3];
        }).toList();
    }

    public static List<String> classeacceptee() {
        return Arrays.stream(MODULE).map((t) -> {
            return t[4];
        }).toList();
    }

    //-----------------------------------------------------------------
    //              CREATION DE LA TABLE Semestre
    //-----------------------------------------------------------------

    public static void tableSemestre(Connection con) throws SQLException {
        //méthode permettant de créer la table qui va contenir les semestres
        try (Statement st = con.createStatement()) {
            st.executeUpdate("""
                        create table Semestre(
                        id integer primary key generated always as identity,
                        annee integer not null,
                        numero integer not null,
                        ng integer not null,
                        nc integer not null                       
                         
                        )
                        """);
        }
        List<String> annee = Initialisation.annee();
        List<String> num = Initialisation.numero();
        List<String> ng = Initialisation.ng();
        List<String> nc = Initialisation.nc();
        for (int i = 0; i < SEMESTRE.length; i++) {
            Commandes.AjoutSemestre(con, annee.get(i), num.get(i), ng.get(i), nc.get(i));
        }
    }

    public static final String[][] SEMESTRE = new String[][]{
            {"2019", "1", "3", "1"},//semestre 1 de l'année 2019, il y a 3 NG (Nombre de Groupes) et 1 NC (Nombre de Choix)
            {"2019", "2", "3", "1"},
            {"2020", "1", "3", "1"},
            {"2020", "2", "3", "1"},
    };

    public static List<String> annee() {
        return Arrays.stream(SEMESTRE).map((t) -> {
            return t[0];
        }).toList();
    }

    public static List<String> numero() {
        return Arrays.stream(SEMESTRE).map((t) -> {
            return t[1];
        }).toList();
    }

    public static List<String> ng() {
        return Arrays.stream(SEMESTRE).map((t) -> {
            return t[2];
        }).toList();
    }

    public static List<String> nc() {
        return Arrays.stream(SEMESTRE).map((t) -> {
            return t[3];
        }).toList();
    }

    //-----------------------------------------------------------------
    //              CREATION DE LA TABLE GrpModule
    //-----------------------------------------------------------------

    public static void tableGrpModule(Connection con) throws SQLException {
        //méthode permettant de créer la table qui va contenir les grps de module
        try (Statement st = con.createStatement()) {
            st.executeUpdate("""
                        create table GrpModule(
                        idSemestre integer not null,
                        idGroupe integer not null,
                        idModule integer not null
                         
                        )
                        """);
            st.executeUpdate(
                    """
                alter table GrpModule
                add constraint moduleAppartient  
                foreign key (idModule) references module(id)
                ON DELETE CASCADE
                
                    """);
            st.executeUpdate(
                    """
            alter table GrpModule
            add constraint moduleSemestre
            foreign key (idSemestre) references semestre(id)
            ON DELETE CASCADE
           
              """);
        }


        List<String> semestre = Initialisation.idsemestre();
        List<String> grp = Initialisation.grpmodule();
        List<String> mod = Initialisation.module();

        for(int i=0;i<GRPMODULE.length;i++){
            //Commandes.AjoutGrpModule(con,Integer.parseInt(semestre.get(i)),Integer.parseInt(grp.get(i)),Integer.parseInt(mod.get(i)));
            Commandes.AjoutGrpModule(con,semestre.get(i),grp.get(i),mod.get(i));
        }
    }

    public static final String[][] GRPMODULE = new String[][]{

            //semestre 1 2019: 3NG
            {"1", "1", "1"},
            {"1", "1", "3"},
            {"1", "1", "5"},
            //idsemestre, idGrp,idModule
            {"1", "2", "2"},
            {"1", "2", "4"},
            {"1", "2", "6"},

            {"1", "3", "11"},
            {"1", "3", "12"},
            {"1", "3", "13"},

            //semestre 2 2019: 3NG
            {"2", "1", "1"},
            {"2", "1", "3"},
            {"2", "1", "5"},

            {"2", "2", "2"},
            {"2", "2", "4"},
            {"2", "2", "6"},

            {"2", "3", "11"},
            {"2", "3", "12"},
            {"2", "3", "13"},

            //semestre 1 2020 : 4NG
            {"3", "1", "3"},
            {"3", "1", "6"},
            {"3", "1", "9"},
            //grp2
            {"3", "2", "4"},
            {"3", "2", "8"},
            {"3", "2", "12"},
            //grp3
            {"3", "3", "1"},
            {"3", "3", "5"},
            {"3", "3", "10"},
            //grp4
            {"3", "4", "15"},
            {"3", "4", "7"},
            {"3", "4", "11"},

            //semestre 2 2020 : 3NG

            {"4", "1", "13"},
            {"4", "1", "14"},
            {"4", "1", "15"},
            //grp2
            {"4", "2", "1"},
            {"4", "2", "3"},
            {"4", "2", "9"},
            //grp3
            {"4", "3", "7"},
            {"4", "3", "8"},
            {"4", "3", "11"},
    };

    public static List<String> idsemestre() {
        return Arrays.stream(GRPMODULE).map((t) -> {
            return t[0];
        }).toList();
    }

    public static List<String> grpmodule() {
        return Arrays.stream(GRPMODULE).map((t) -> {
            return t[1];
        }).toList();
    }
    public static List<String> module() {
        return Arrays.stream(GRPMODULE).map((t) -> {
            return t[2];
        }).toList();
    }

    //-----------------------------------------------------------------
    //              CREATION DE LA TABLE VOEUX
    //-----------------------------------------------------------------

    public static void tableVoeux(Connection con) throws SQLException {
        //méthode permettant de créer la table qui va contenir les voeux
        try (Statement st = con.createStatement()) {
            st.executeUpdate("""
                        create table Voeux(
                        idSemestre integer not null,
                        idEtudiant integer not null,
                        idModule integer not null,
                        idGrpModule integer not null,
                        numeroVoeux integer
                        )
                        """);
            st.executeUpdate(
                    """
                    alter table Voeux
                    add constraint etudiantinscrit 
                    foreign key (idEtudiant) references etudiant(id)
                    ON DELETE CASCADE
                    
                        """);
            st.executeUpdate(
                    """
                    alter table Voeux
                    add constraint moduleOuvert
                    foreign key (idModule) references module(id) 
                    ON DELETE CASCADE
                      """);          
        }

        List<String> semestre = Initialisation.idsemestreVoeux();
        List<String> etu = Initialisation.etudiantVoeux();
        List<String> voeux = Initialisation.moduleVoeux();
        List<String> gvoeux = Initialisation.groupevoeux();

        for(int i=0;i<VOEUX.length;i++){
            Commandes.AjoutVoeux(con,semestre.get(i),etu.get(i),voeux.get(i),gvoeux.get(i));
        }

    }
    public static final String[][] VOEUX = new String[][]{
            //Etudiant 1, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "1","13","1"},
            {"4", "1", "3","2"},
            {"4", "1", "8","3"},

            //Etudiant 2, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "2","13","1"},
            {"4", "2", "3","2"},
            {"4", "2", "8","3"},

            //Etudiant 3, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "3","13","1"},
            {"4", "3", "3","2"},
            {"4", "3", "8","3"},

            //Etudiant 4, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "4","13","1"},
            {"4", "4", "3","2"},
            {"4", "4", "8","3"},

            //Etudiant 5, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "5","13","1"},
            {"4", "5", "3","2"},
            {"4", "5", "8","3"},

            //Etudiant 6, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "6","13","1"},
            {"4", "6", "3","2"},
            {"4", "6", "8","3"},

            //Etudiant 7, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "7","13","1"},
            {"4", "7", "3","2"},
            {"4", "1", "8","3"},
            //Etudiant 8, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "8","13","1"},
            {"4", "8", "3","2"},
            {"4", "8", "8","3"},

            //Etudiant 9, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "9","13","1"},
            {"4", "9", "3","2"},
            {"4", "9", "8","3"},

            //Etudiant 10, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "10","13","1"},
            {"4", "10", "3","2"},
            {"4", "10", "8","3"},

            //Etudiant 11, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "11","13","1"},
            {"4", "11", "3","2"},
            {"4", "11", "8","3"},

            //Etudiant 12, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "12","13","1"},
            {"4", "12", "3","2"},
            {"4", "12", "8","3"},

            //Etudiant 13, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "13","13","1"},
            {"4", "13", "3","2"},
            {"4", "13", "8","3"},

            //Etudiant 14, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "14","13","1"},
            {"4", "14", "3","2"},
            {"4", "14", "8","3"},

            //Etudiant 15, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "15","13","1"},
            {"4", "15", "3","2"},
            {"4", "15", "8","3"},

            //Etudiant 16, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "16","13","1"},
            {"4", "16", "3","2"},
            {"4", "16", "8","3"},

            //Etudiant 17, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "17","13","1"},
            {"4", "17", "3","2"},
            {"4", "17", "8","3"},

            //Etudiant 18, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "18","13","1"},
            {"4", "18", "3","2"},
            {"4", "18", "8","3"},

            //Etudiant 16, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "19","13","1"},
            {"4", "19", "3","2"},
            {"4", "19", "8","3"},

            //Etudiant 20, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "20","13","1"},
            {"4", "20", "3","2"},
            {"4", "20", "8","3"},

            //Etudiant 21, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "21","13","1"},
            {"4", "21", "3","2"},
            {"4", "21", "8","3"},

            //Etudiant 22, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "22","13","1"},
            {"4", "22", "3","2"},
            {"4", "22", "8","3"},

            //Etudiant 23, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "23","13","1"},
            {"4", "23", "3","2"},
            {"4", "23", "8","3"},

            //Etudiant 24, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "24","13","1"},
            {"4", "24", "3","2"},
            {"4", "24", "8","3"},

            //Etudiant 25, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "25","13","1"},
            {"4", "25", "3","2"},
            {"4", "25", "8","3"},

            //Etudiant 26, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "26","13","1"},
            {"4", "26", "3","2"},
            {"4", "26", "8","3"},

            //Etudiant 27, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "27","13","1"},
            {"4", "27", "3","2"},
            {"4", "27", "8","3"},

            //Etudiant 28, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "28","13","1"},
            {"4", "28", "3","2"},
            {"4", "28", "8","3"},

            //Etudiant 29, n'a fait des electifs qu'au S2 2020, il a donc choisi 3 modules de 3 Groupes différents
            //idSemestre,idEtudiant,idModule
            {"4", "29","13","1"},
            {"4", "29", "3","2"},
            {"4", "29", "8","3"},

            //Etudiant 120, GE4, a fait les modules au  s2 2019, et au s1 et s2 2020
            //s2 2019
            {"2", "120", "5","1"},
            {"2", "120", "6","2"},
            {"2", "120", "13","3"},
            //s1 2020
            {"3", "120", "9","1"},
            {"3", "120", "8","2"},
            {"3", "120", "10","3"},
            //s2 2020
            {"4", "120", "14","1"},
            {"4", "120", "3","2"},
            {"4", "120", "8","3"},
    };

    public static List<String> idsemestreVoeux() {
        return Arrays.stream(VOEUX).map((t) -> {
            return t[0];
        }).toList();
    }

    public static List<String> etudiantVoeux() {
        return Arrays.stream(VOEUX).map((t) -> {
            return t[1];
        }).toList();
    }
    public static List<String> moduleVoeux() {
        return Arrays.stream(VOEUX).map((t) -> {
            return t[2];
        }).toList();
    }
    public static List<String> groupevoeux() {
        return Arrays.stream(VOEUX).map((t) -> {
            return t[3];
        }).toList();
    }

}
