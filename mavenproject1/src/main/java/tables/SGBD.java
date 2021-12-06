/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import javax.sql.DataSource;

/**
 *
 * @author thiba
 */
public class SGBD {
     // pour acceder à une base de donnée, il me faut connaitre
    //   . le SGBD utilisé (java DB, MySQL, Oracle ...) et fournir le pilote correspondant
    //     (pilote client JDBC fourni avec le SGBD, ou que l'on peut retrouver sur le site correspondant)
    //   . la référence à la base de donnée. Cette référence est une URL contenant :
    //       . le protocole utilisé
    //       . l'adresse du serveur
    //       . le port utilisé pour la connection
    //       . le nom de la base de donnée
    //   . un utilisateur valide pour cette base de donnée, et son mot de passe
    //
    // Seule la connection change. Ensuite, les ordres SQL sont normalement standards.
    // Ce programme peut acceder à une base locale utilisant le sgbd javaDB (derby)
    // ou une base MySQL sur le serveur de l'INSA
    // nous définissons ci-dessous les constantes correspondantes .
    // vous devez modifier ces variables pour qu'elles correspondent à vos
    // bases de données
    // note : j'aurais pu utilisé une classe interne pour représenter les
    // données de connection, ou au moins un tableau de chaines. Mais comme
    // ses constructions n'ont pas été vues, je me contente d'un ensemble
    // de variables static
    public static String JAVADB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    public static String JAVADB_URL = "jdbc:derby://localhost:1527/moduleM3";
    public static String JAVADB_USER = "beuvronfr";
    public static String JAVADB_PASS = "toto";
    public static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static String MYSQL_URL = "jdbc:mysql://crpilamp.insa-strasbourg.fr:3306/fdebertranddeb01";
    public static String MYSQL_USER = "fdebertranddeb01";
    /**
     * une valeur null pour le mot de passe oblige le programme à demander un
     * mot de passe à l'utilisateur.
     */
    public static String MYSQL_PASS = null;

    public static String MARIADB_DRIVER = "org.mariadb.jdbc.Driver";
    public static String MARIADB_TESTFDB_URL = "jdbc:mariadb://localhost:3306/testfdb";
    public static String MARIADB_USER = "root";
    public static String MARIADB_PASS = "toto";

    public static String POSTGRESQL_DRIVER = "org.postgresql.Driver";
    public static String POSTGRESQL_URL = "jdbc:postgresql://localhost:5432/postgres";
    public static String POSTGRESQL_USER = "postgres";
    public static String POSTGRESQL_PASS = "pass";

    public static Connection getDefautCon() {
        return SGBD.connectionLocalPostgresql();
    }

    public static Connection etabliConnection(String sgbdDriver, String dataBaseURL, String userName, String pass) {
        try {
            Connection con;
            // pour définir la connexion, il nous faut d'abord un driver JDBC qui
            // est spécifique pour chaque système de gestion de bases de données (SGBD)
            // pour cela, il faut s'assurer que java "charge" le driver
            //
            // attention, le driver correspondant doit être accessible.
            // il faut donc ajouter le .jar correspondant en tant que library lors de l'execution
            //
            // En Netbeans, vous pouvez cliquer droit sur le projet, et selectionner "properties"
            // allez ensuite dans "library", cliquez sur "add jar/folder", et indiquez ou se trouve
            // le fichier .jar contenant le driver.
            //
            // Si vous utilisez le projet "BdDPratique" fourni pour le module M3 informatique INSA Strasbourg
            // les .jar necessaires ont déjà été inclus
            //
             
         
            Class.forName(sgbdDriver);
            
            // ensuite, la base de données est identifiée par une URL de la forme :
            // jdbc:<nom du gestionnaire>://<adresse internet>:<port>/<nom de la base>
            // il faut également connaitre un nom d'utilisateur ayant la permission d'accéder
            // à la base de données, et éventuellement son mot de passe
            // Si le mot de passe n'est pas défini (null) je le demande
            if (pass == null) {
                Scanner stdin = new Scanner(System.in);
                System.out.println("donnez le mot de passe de l'utilisateur " + userName
                        + " pour la base de donnée " + dataBaseURL);
                pass = stdin.next();
            }

            // maintenant que j'ai tous ces renseignements, je peux effectivement me
            // connecter à la base de donnée
            con = DriverManager.getConnection(dataBaseURL, userName, pass);

            // le type de transaction fixe le niveau de sécurité lorque plusieurs personnes/programmes
            // accèdent en même temps à la base de donnée. Nous utiliserons ici le niveau le
            // plus élevé : tout se passe comme si chaque utilisateur était seul sur la base de
            // donnée durant le temps d'une transaction. La contre-partie de ce niveau élevé de protection
            // est un niveau réduit de performance lorsque de nombreux programmes accèdent à
            // la base de donnée simultanément.
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // il est a noter que la base de donnée doit avoir été crée au préalable. Pour cela,
            // si vous utilisez derby, vous pouvez le faire sous NetBeans (6.9.1)
            // en allant dans l'onglet "Services" puis clic droit sur "Java DB" --> create Database
            //
            // Normalement, la base de donnée MySQL a déjà été crée. Voir les explications
            // dans le fichier "connection MySQL et serveur http du labo" dans le moodle
            // du module M3 informatique INSA

            return con;
        } catch (Exception ex) {
            throw new Error(ex);
        }

    }

    public static Connection connectionDerby() {
        return etabliConnection(JAVADB_DRIVER, JAVADB_URL, JAVADB_USER, JAVADB_PASS);
    }

    public static Connection connectionMySQL() {
        return etabliConnection(MYSQL_DRIVER, MYSQL_URL, MYSQL_USER, MYSQL_PASS);
    }

    public static Connection connectionLocalMariaDB() {
        return etabliConnection(MARIADB_DRIVER, MARIADB_TESTFDB_URL, MARIADB_USER, MARIADB_PASS);
    }

    public static Connection connectionLocalPostgresql() {
        return etabliConnection(POSTGRESQL_DRIVER, POSTGRESQL_URL, POSTGRESQL_USER, POSTGRESQL_PASS);
    }

    
}
