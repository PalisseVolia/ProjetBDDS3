/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.sql.Connection;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author thiba
 */
public class Etudiant extends Personne{
    
    Date dateNaiss ; //date de naissance
    boolean disponibilite; //permet de définir si un éttudiant peut s'inscire ou non à un electif. Exemple : si il part en mobilité

    public Etudiant(Date dateNaiss, boolean disponibilite, String nom, String prenom, String adresse, String mdp) {
        super(nom, prenom, adresse, mdp);
        this.dateNaiss = dateNaiss;
        this.disponibilite = disponibilite;
    }
     public Etudiant() {
        super();
    }
     
      public Etudiant(String nom, String prenom, String adresse, String mdp) {
        super(nom, prenom, adresse, mdp);
    }

    @Override
    public String getMdp() {
        return super.getMdp(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAdresse() {
        return super.getAdresse(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPrenom() {
        return super.getPrenom(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNom() {
        return super.getNom(); //To change body of generated methods, choose Tools | Templates.
    }
    

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

  
    public String toString() {
        return(this.getNom()+";"+this.getPrenom()+";"+this.getAdresse()+";"+this.getMdp()+";"+this.getDateNaiss());
    }

   

    @Override
    public void setMdp(String mdp) {
        super.setMdp(mdp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAdresse(String adresse) {
        super.setAdresse(adresse); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPrenom(String prenom) {
        super.setPrenom(prenom); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNom(String nom) {
        super.setNom(nom); //To change body of generated methods, choose Tools | Templates.
    }
    
   public static final String[][] PERSONNES_ALEA = new String[][]{
        {"1", "Pauline", "Giroux", "PaulineGiroux@insa-strasbourg.fr", "Milita!recreux55",  "5/7/2002", "true"},
        {"2", "Peppin", "David", "PeppinDavid@insa-strasbourg.fr","AttaqueT!tanesque0", "11/1/2002","true"},
        {"3", "Aurore", "Benjamin", "AuroreBenjamin@insa-strasbourg.fr","ViveJava47?",  "10/18/2002","true"},
        {"4", "Dominic", "Rochefort", "DominicRochefort@insa-strasbourg.fr","?PublicStatic02",  "7/8/2002","true"},
        {"5", "Gérard", "Parenteau", "GerardParenteau@insa-strasbourg.fr", "Pizza4Fromage!", "6/29/2002","true"},
        {"6", "Melusina", "Simon", "MelusinaSimon@insa-strasbourg.fr","InsaStras67!", "1/27/2002","true"},
        {"7", "Calandre", "Béland", "CalandreBeland@insa-strasbourg.fr", "?Jb007",  "3/20/2002","true"},
        {"8", "Édith", "Caya", "EdithCaya@insa-strasbourg.fr", "XxMessixX05!","3/25/2002","true"},
        {"9", "Eugène", "Chrétien", "EugeneChretien@insa-strasbourg.fr","LeBronJames7!8", "3/16/2002","true"},
        {"10", "Searlas", "Guay", "SearlasGuay@insa-strasbourg.fr", "Xaeo14!5B", "1/28/2002","true"},
        {"11", "Pierrette", "Gadbois", "PierretteGadbois@insa-strasbourg.fr","fnzhj65H", "5/4/2002","true"},
        {"12", "Zurie", "Bonami", "ZurieBonami@insa-strasbourg.fr","nfjezG;14",  "5/23/2002","true"},
        {"13", "Alphonsine", "Cloutier", "AlphonsineCloutier@insa-strasbourg.fr","dhebdG4","10/3/2002","true"},
        {"14", "Pierpont", "Dumont", "PierpontDumont@insa-strasbourg.fr",  "thibautlbp01", "11/5/2002","true"},
        {"15", "Yvon", "Primeau", "YvonPrimeau@insa-strasbourg.fr", "thibautsecritsansl88","9/21/2002","true"},
        {"16", "Albertine", "Rossignol", "AlbertineRossignol@insa-strasbourg.fr","VoliaRugueux0", "10/28/2002","true"},
        {"17", "Christien", "Laderoute", "ChristienLaderoute@insa-strasbourg.fr","CouscousMerguez5", "5/29/2002","true"},
        {"18", "Lance", "Gosselin", "LanceGosselin@insa-strasbourg.fr", "PinkFloyd5", "10/19/2002","true"},
        {"19", "Somerville", "Brisebois", "SomervilleBrisebois@insa-strasbourg.fr","Juice999!",  "8/15/2002","true"},
        {"20", "Auriville", "Beauchemin", "AurivilleBeauchemin@insa-strasbourg.fr","RocketLeague4", "3/23/2001","true"},
        {"21", "Hugues", "Houle", "HuguesHoule@insa-strasbourg.fr",  "fhjs!fFz4", "10/16/2001","true"},
        {"22", "Ogier", "Josseaume", "OgierJosseaume@insa-strasbourg.fr","F!Sgjr5", "3/7/2001","true"},
        {"23", "Armina", "Massé", "ArminaMasse@insa-strasbourg.fr","fDSfdfFz4!","4/18/2001","true"},
        {"24", "Océane", "Lamare", "OceaneLamare@insa-strasbourg.fr","R!EZzfz4", "4/15/2001","true"},
        {"25", "Beltane", "Pariseau", "BeltanePariseau@insa-strasbourg.fr","Jgfh!FGsFz4", "1/8/2001","true"},
        {"26", "Maryse", "Guibord", "MaryseGuibord@insa-strasbourg.fr",  "HJ!Lrdq!Fz4","1/21/2001","true"},
        {"27", "Émilie", "Desnoyers", "EmilieDesnoyers@insa-strasbourg.fr","Frdgzjks!fFz4",  "7/5/2001","true"},
        {"28", "Melville", "Duplessis", "MelvilleDuplessis@insa-strasbourg.fr","Mh!ytkere4",  "6/6/2001","true"},
        {"29", "Michel", "Baril", "MichelBaril@insa-strasbourg.fr","FZkfn?c475", "11/14/2001","true"},
        {"30", "Bertrand", "Turgeon", "BertrandTurgeon@insa-strasbourg.fr","NarutoHokage", "3/12/2001","true"},
        {"31", "Landers", "Lemieux", "LandersLemieux@insa-strasbourg.fr","ViveSpiderman054!", "3/5/2001","true"},
        {"32", "La Roux", "Cotuand", "LaRouxCotuand@insa-strasbourg.fr","ProutLol:!4", "1/30/2001","true"},
        {"33", "Serge", "Givry", "SergeGivry@insa-strasbourg.fr","fdhjzffzh2", "6/6/2001","true"},
        {"34", "Ignace", "Quiron", "IgnaceQuiron@insa-strasbourg.fr","pokemon4", "9/20/2001","true"},
        {"35", "Aleron", "Fréchette", "AleronFrechette@insa-strasbourg.fr","voiturerouge", "10/15/2001","true"},
        {"36", "Fortun", "Savoie", "FortunSavoie@insa-strasbourg.fr","travailleurfrontalier", "2/18/2001","true"},
        {"37", "Fusberta", "Dufour", "FusbertaDufour@insa-strasbourg.fr"," thdbadvc54!", "11/23/2000","true"},
        {"38", "Merle", "Dupont", "MerleDupont@insa-strasbourg.fr", "Ggezf44", "1/9/1999","true"},
        {"39", "Ferrau", "Déziel", "FerrauDeziel@insa-strasbourg.fr","Gkzjdbacv15",  "3/24/2001","true"},
        {"40", "Joseph", "Laforge", "JosephLaforge@insa-strasbourg.fr","GKbdb1qn",  "10/28/2000","true"},
        {"41", "David", "Laderoute", "DavidLaderoute@insa-strasbourg.fr","MatthieuLutzlpb",  "5/14/2001","true"},
        {"42", "Eugène", "Busson", "EugeneBusson@insa-strasbourg.fr","yaatiktok",  "7/15/2000","true"},
        {"43", "Joséphine", "Chandonnet", "JosephineChandonnet@insa-strasbourg.fr","salutsalut",  "12/10/2001","true"},
        {"44", "Ambra", "Bourget", "AmbraBourget@insa-strasbourg.fr","Fzjfknbv",  "3/19/2000","true"},
        {"45", "Merle", "Lapointe", "MerleLapointe@insa-strasbourg.fr","InfoBaseDeDonnee",  "8/29/2000","true"},
        {"46", "Arthur", "Sevier", "ArthurSevier@insa-strasbourg.fr", "HGejkd", "5/24/2000","true"},
        {"47", "Quennel", "Verreau", "QuennelVerreau@insa-strasbourg.fr","ZFhdbnfz", "8/18/2000","true"},
        {"48", "Aubrey", "Gauthier", "AubreyGauthier@insa-strasbourg.fr", "DFsnjkzf", "2/9/2000","true"},
        {"49", "Gill", "Vaillancour", "GillVaillancour@insa-strasbourg.fr", "Sjgioemnd65", "9/26/2000","true"},
        {"50", "Talon", "Rhéaume", "TalonRheaume@insa-strasbourg.fr","dzFaknfv1",  "4/3/2000","true"},
        {"51", "Latimer", "Morneau", "LatimerMorneau@insa-strasbourg.fr","PommePoire7",  "3/26/2001","true"},
        {"52", "Chapin", "Sansouci", "ChapinSansouci@insa-strasbourg.fr", "BananeFraise",  "5/25/2001","true"},
        {"53", "Cosette", "Chesnay", "CosetteChesnay@insa-strasbourg.fr","VanilleChocolat",  "8/18/2001","true"},
        {"54", "Nicole", "Gregoire", "NicoleGregoire@insa-strasbourg.fr","SauceEpicee",  "6/2/1999","true"},
        {"55", "Curtis", "Larocque", "CurtisLarocque@insa-strasbourg.fr","DonutSucreAuSucre", "6/29/1999","true"},
        {"56", "Philippe", "Audet", "PhilippeAudet@insa-strasbourg.fr", "Tftyako", "10/7/1999","true"},
        {"57", "Arnaud", "Rouze", "ArnaudRouze@insa-strasbourg.fr", "scottdgb", "6/4/1999","true"},
        {"58", "Sumner", "Bizier", "SumnerBizier@insa-strasbourg.fr","ApexLegend",  "5/28/1999","true"},
        {"59", "Rosamonde", "Laisné", "RosamondeLaisne@insa-strasbourg.fr", "JusticeLeague", "4/4/2000","true"},
        {"60", "Rive", "Beauchamps", "RiveBeauchamps@ginsa-strasbourg.fr","BruceWayneEstBatman",  "5/18/2000","true"},
        {"61", "Lotye", "Angélil", "LotyeAngelil@insa-strasbourg.fr", "djiflzb", "4/16/2000","true"},
        {"62", "Jesper", "Chartier", "JesperChartier@insa-strasbourg.fr","SDjzefjkbv",  "6/22/2000","true"},
        {"63", "Armina", "Langlais", "ArminaLanglais@insa-strasbourg.fr", "DJFzbfbn","6/17/1998","true"},
        {"64", "Brigitte", "Pelchat", "BrigittePelchat@insa-strasbourg.fr","Djfzakb",  "11/6/1997","true"},
        {"65", "Henry", "Brisebois", "HenryBrisebois@insa-strasbourg.fr","KHrjfn",  "7/11/1999","true"},
        {"66", "Matilda", "Sevier", "MatildaSevier@insa-strasbourg.fr","Tjekilbvhjl", "3/7/2001","true"},
        {"67", "Minette", "Roy", "MinetteRoy@insa-strasbourg.fr","HJriomnbjfd",  "12/11/2001","true"},
        {"68", "Burkett", "Guertin", "BurkettGuertin@insa-strasbourg.fr", "htopn,kfl", "2/25/2001","true"},
        {"69", "Halette", "Barteaux", "HaletteBarteaux@insa-strasbourg.fr", "JLpynfkz", "11/18/2001","true"},
        {"70", "Jewel", "Fournier", "JewelFournier@insa-strasbourg.fr","egfnjker", "10/26/2001","true"},
        {"71", "Raina", "Garcia", "RainaGarcia@insa-strasbourg.fr", "EGjeiuvb", "8/27/2000","true"},
        {"72", "Peppin", "Ménard", "PeppinMenard@insa-strasbourg.fr","GFEzgviogn", "7/15/2000","true"},
        {"73", "Fealty", "Allard", "FealtyAllard@insa-strasbourg.fr", "DVkfoivn","6/30/2000","true"},
        {"74", "Percy", "Bériault", "PercyBeriault@insa-strasbourg.fr","Rkothnv", "12/14/2000","true"},
        {"75", "Merlin", "Massé", "MerlinMasse@insa-strasbourg.fr","syrielzfjiu", "10/25/2000","true"},
        {"76", "Damiane", "Vernadeau", "DamianeVernadeau@insa-strasbourg.fr","bolognaise>carbo",  "10/20/2000","true"},
        {"77", "Byron", "LaGrande", "ByronLaGrande@insa-strasbourg.fr","sushipasbon", "1/25/2000","true"},
        {"78", "Sibyla", "Roux", "SibylaRoux@insa-strasbourg.fr", "tonystarkXx", "3/22/2000","true"},
        {"79", "Joséphine", "Grivois", "JosephineGrivois@insa-strasbourg.fr","sncf=retard",  "8/23/2000","true"},
        {"80", "Pascaline", "DuLin", "PascalineDuLin@insa-strasbourg.fr","marchedenoel", "10/19/2000","true"},
        {"81", "Galatee", "Bordeaux", "GalateeBordeaux@insa-strasbourg.fr", "yeeeepnji", "1/22/2000","true"},
        {"82", "Pomeroy", "Fournier", "PomeroyFournier@insa-strasbourg.fr", "donda15","4/12/2002","true"},
        {"83", "Artus", "Thibodeau", "ArtusThibodeau@insa-strasbourg.fre", "Efgkoe", "2/25/2000","true"},
        {"84", "Arianne", "CinqMars", "ArianneCinqMars@insa-strasbourg.fr","VFeiovne", "6/6/2001","true"},
        {"85", "Curtis", "Mainville", "CurtisMainville@insa-strasbourg.fr","claviersouris", "2/10/2001","true"},
        {"86", "Viollette", "Bélanger", "ViolletteBelanger@ginsa-strasbourg.fr","manettedejeu",  "6/10/2001","true"},
        {"87", "Warrane", "Raymond", "WarraneRaymond@insa-strasbourg.fr", "lessiestescestbien", "11/11/2001","true"},
        {"88", "Rosemarie", "Roux", "RosemarieRoux@insa-strasbourg.fr", "dormircestlavie", "7/6/2001","true"},
        {"89", "Bradamate", "Rossignol", "BradamateRossignol@insa-strasbourg.fr","jaimepaslecovid", "6/25/2001","true"},
        {"90", "Céline", "Bertrand", "CelineBertrand@insa-strasbourg.fr", "jfzomfhz", "12/5/2001","true"},
        {"91", "Françoise", "Raymond", "FrancoiseRaymond@insa-strasbourg.fr","KVeibc",  "4/17/2000","true"},
        {"92", "Granville", "Baron", "GranvilleBaron@insa-strasbourg.fr", "Kzefizbvvz", "8/6/2001","true"},
        {"93", "Serge", "Caya", "SergeCaya@insa-strasbourg.fr", "Kbirbvzlo", "3/1/2001","true"},
        {"94", "Dixie", "Couet", "DixieCouet@insa-strasbourg.fr", "VKeojvnzkz", "21/6/2001","true"},
        {"95", "Melusina", "Barrientos", "MelusinaBarrientos@insa-strasbourg.fr", "Veiovhbe,","3/8/2001","true"},
        {"96", "Isaac", "Auclair", "IsaacAuclair@insa-strasbourg.fr", "Vekovbzkjbb", "22/6/2001","true"},
        {"97", "Jeannine", "Poulin", "JeanninePoulin@insa-strasbourg.fr", "VEveubvb","1/1/2001","true"},
        {"98", "Maurice", "Pirouet", "MauricePirouet@insa-strasbourg.fr", "jTrbzo", "28/10/1999","true"},
        {"99", "Marc", "Meunier", "MarcMeunier@insa-strasbourg.fr","HBeiovndm",  "9/3/2000","true"},
        {"100", "Valérie", "Cressac", "ValerieCressac@insa-strasbourg.fr","Jyerg,n",  "30/6/1998","true"},
        {"101", "Daniel", "Craig", "DanielCraig@insa-strasbourg.fr", "Vreomnb", "2/03/1998","true"},
        {"102", "Brad", "Pitt", "BradPitt@insa-strasbourg.fr", "aefvnt", "12/18/2003","true"},
        {"103", "Angélina", "Jolie", "AngelinaJolie@insa-strasbourg.fr", "Broeibp^n", "05/22/2003","true"},
        {"104", "Scarlett", "Johansson", "ScarlettJohansson@insa-strasbourg.fr","Rkopbnek",  "2/03/2002","true"},
        {"105", "Lionel", "Messi", "LionelMessi@insa-strasbourg.fr","hkemVe",  "08/15/2000","true"},
        {"106", "Teddy", "Riner", "TeddyRiner@insa-strasbourg.fr","DNgir",  "7/04/2000","true"},
        {"107", "Lewis", "Hamilton", "LewisHamilton@insa-strasbourg.fr","EKRolv",  "1/7/2002","true"},
        {"108", "Charles", "Leclerc", "CharlesLeclerc@insa-strasbourg.fr","EDKoeq",  "10/16/2002","true"},
        {"109", "Max", "Verstappen", "MaxVerstappen@insa-strasbourg.fr","Dbvozezf",  "09/30/2002","true"},
        {"110", "Valtteri", "Bottas", "ValterriBottas@insa-strasbourg.fr","FDkoev",  "08/28/2001","true"},
        {"111", "Sebastian", "Vettel", "SebastianVettel@insa-strasbourg.fr","VKopac",  "3/07/2001","true"},
        {"112", "Sergio", "Pérez", "SergioPerez@insa-strasbourg.fr","VDSjkoizf",  "10/26/2001","true"},
        {"113", "Daniel", "Ricciardo", "DanielRicciardo@insa-strasbourg.fr","Fkozpc",  "1/07/2000","true"},
        {"114", "Elerson", "Echiéjilé", "EldersonEchiejile@insa-strasbourg.fr","sVikzf",  "1/20/2000","true"},
        {"115", "Erling", "Haaland", "ErlingHaaland@insa-strasbourg.fr","SD?jizv",  "07/21/2001","true"},
        {"116", "Karim", "Benzema", "KarimBenzema@insa-strasbourg.fr","VJbdlnv",  "12/19/2001","true"},
        {"117", "Cristiano", "Ronaldo", "CristianoRonaldo@insa-strasbourg.fr","VDKozevs",  "08/21/1999","true"},
        {"118", "Bruce", "Wayne", "BruceWayne@insa-strasbourg.fr","joker",  "4/17/2002","true"},
        {"119", "Peter", "Parker", "PeterParker@insa-strasbourg.fr","maryjane",  "8/15/2002","true"},
        {"120", "Thor", "Odinson", "ThorOdinson@insa-strasbourg.fr","foudre",  "8/1/2002","true"},
   };
    public static List<String> noms() {
        return Arrays.stream(PERSONNES_ALEA).map((t) -> {
            return t[2];
        }).toList();
    }
    
      public static List<String> prenoms() {
        return Arrays.stream(PERSONNES_ALEA).map((t) -> {
            return t[1];
        }).toList();
    }
      
      public static List<String> adresse() {
        return Arrays.stream(PERSONNES_ALEA).map((t) -> {
            return t[3];
        }).toList();
    }
      
       public static List<String> mdp() {
        return Arrays.stream(PERSONNES_ALEA).map((t) -> {
            return t[4];
        }).toList();
       }
        
        public static List<String> datenaiss() {
        return Arrays.stream(PERSONNES_ALEA).map((t) -> {
            return t[5];
        }).toList();
    }
         public static List<String> dispo() {
        return Arrays.stream(PERSONNES_ALEA).map((t) -> {
            return t[6];
        }).toList();
    }


    }




