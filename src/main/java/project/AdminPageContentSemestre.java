package project;

import java.sql.Connection;
import java.sql.SQLException;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;

import bdd.Commandes;

// =======================================================================================
// Contenu de la page permettant l'ajout de semestres de l'interface Administrateur
// =======================================================================================
import bdd.Commandes;
import classes.Semestre;

public class AdminPageContentSemestre extends VerticalLayout{
    private TextArea infos;
    private Button add;

    public AdminPageContentSemestre() throws SQLException, ClassNotFoundException {
        //creation du field non modifiable donnant des informations à l'utilisateur
        infos = new TextArea();
        infos.setValue("Lors de l'ajout d'un semestre, le contenu du dernier semestre existant est copié puis archivé. Les Pages de groupes permettent alors la modification du semestre nouvellement ajouté.");
        infos.setReadOnly(true);
        add(infos);

        //bouton de d'ajout
        add = new Button();
        add.setText("Ajouter");
        add(add);

        //style settings
        setAlignItems(Alignment.CENTER);
        add.setWidth("13em");
        infos.setWidth("30em");

        Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass");

        //ajout d'un semestre lors de la pression du bouton d'ajout
        add.addClickListener(t -> {
            try {
                Commandes.NouvSemestre(con);
            } catch (Exception e) {
                System.out.println("erreur durant la connexion à la bdd : ajout de semestre IG");
            }
        });
    }
}