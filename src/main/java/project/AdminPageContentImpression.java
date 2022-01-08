package project;

import java.sql.Connection;
import java.sql.SQLException;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import bdd.Commandes;
import bdd.Ecriture;

public class AdminPageContentImpression extends VerticalLayout {
    private TextField path;
    private Button valider;
    
    public AdminPageContentImpression() throws SQLException, ClassNotFoundException{
        path = new TextField();
        path.setLabel("Chemin vers lequel écrire le fichier");
        path.setPlaceholder("C:/Users/Utilisateur/Desktop");
        add(path);

        valider = new Button();
        valider.setText("Imprimer");
        valider.setEnabled(false);
        add(valider);

        valider.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        setAlignItems(Alignment.CENTER);
        path.setWidth("17em");
        valider.setWidth("13em");

        //connexion à la bdd
        Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass");
        
        path.addValueChangeListener(t -> {
            if (path.getValue() != "") {
                valider.setEnabled(true);
            } else {
                valider.setEnabled(false);
            }
        });

        valider.addClickListener(cl -> {
            String chemin = path.getValue().replace("\\", "/");
            try {
                Ecriture.ecrireFichier(con, Commandes.getidsem(con), chemin);
                System.out.println("ecriture ok");
            } catch (Exception e) {
                //TODO: handle exception
            }
        });
    }
}
