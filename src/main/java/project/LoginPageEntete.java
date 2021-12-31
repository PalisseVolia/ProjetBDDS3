package project;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

// =======================================================================================
// Entete de la page de Login
// =======================================================================================

public class LoginPageEntete extends HorizontalLayout {
    private H1 connexion;

    public LoginPageEntete() {
        //ajout du titre de la page de connexion
        this.connexion = new H1();
        this.connexion.setText("Connexion");
        this.add(connexion);
    }
}
