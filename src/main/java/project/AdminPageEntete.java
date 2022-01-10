package project;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

// =======================================================================================
// Entete de la page d'accueil admin
// =======================================================================================

public class AdminPageEntete extends HorizontalLayout{
    private VuePrincipale main;
    
    private H1 welcome;
    private Button disconnect;
    
    public AdminPageEntete(String nom, String prenom, VuePrincipale mainvue) {
        main = mainvue;
        
        //titre d'accueil
        this.welcome = new H1();
        this.welcome.setText("Bienvenue " + nom + " " + prenom+",");
        this.add(welcome);

        //bouton de déconnexion
        disconnect = new Button();
        disconnect.setText("Déconnexion");
        add(disconnect);
        
        //style settings
        setVerticalComponentAlignment(Alignment.CENTER, welcome);
        disconnect.getElement().getStyle().set("margin-top", "2em");
        setVerticalComponentAlignment(Alignment.CENTER, disconnect);
        this.getElement().getStyle().set("margin-top", "5em");

        //lors de la déconnexion, remet à l'écran d'acceuil
        disconnect.addClickListener((t) -> {
            main.setEntete(new LoginPageEntete());
            main.setAlignment(1);
            main.setMainContent(new LoginPageContent(main));
            main.setFooter(new LoginPageFooter());
        });
    }
}
