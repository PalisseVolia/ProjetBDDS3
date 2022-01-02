package project;

import com.vaadin.flow.component.dependency.NpmPackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.vaadin.artur.helpers.LaunchUtil;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
        //TODO: lors des ajouts, vider les sélections RESTANT: bibliotheque + semestre
        //TODO: Obliger à remplir les champs avant de pourvoir cliquer sur ajouter RESTANT: bibliotheque + semestre
        //TODO: corriger pb de supression dans modules (mm methode que poru l'accueil)
    }

}
