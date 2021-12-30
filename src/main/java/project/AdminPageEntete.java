package project;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class AdminPageEntete extends HorizontalLayout{
    private H1 welcome;
    
    public AdminPageEntete(String nom, String prenom) {
        this.welcome = new H1();
        this.welcome.setText("Bienvenue " + prenom + " " + nom +",");
        this.add(welcome);
    }
}
