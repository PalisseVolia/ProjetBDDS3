package project;

import java.sql.Connection;
import java.sql.SQLException;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;

import bdd.Commandes;

// =======================================================================================
// Contenu de la page permettant l'ajout de semestres de l'interface Administrateur
// =======================================================================================

public class AdminPageContentSemestre extends VerticalLayout{
    private TextArea infos;
    private Button add;
    private CheckboxGroup<String> groupes;

    public AdminPageContentSemestre() throws SQLException, ClassNotFoundException {
        //creation du field non modifiable donnant des informations à l'utilisateur
        infos = new TextArea();
        infos.setValue("Lors de l'ajout d'un semestre, le contenu du dernier semestre existant est copié puis archivé. Les Pages de groupes permettent alors la modification du semestre nouvellement ajouté.");
        infos.setReadOnly(true);
        add(infos);

        groupes = new CheckboxGroup<>();
        groupes.setItems("Groupe 1", "Groupe 2", "Groupe 3");
        groupes.setLabel("Groupes à copier du semestre précédent.");
        add(groupes);

        //bouton de d'ajout
        add = new Button();
        add.setText("Ajouter");
        add(add);

        //style settings
        add.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        setAlignItems(Alignment.CENTER);
        add.setWidth("13em");
        infos.setWidth("30em");

        Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass");

        //ajout d'un semestre lors de la pression du bouton d'ajout
        add.addClickListener(t -> {
            try {
                String value = groupes.getValue().toString();               
                Boolean grp1 = false;
                Boolean grp2 = false;
                Boolean grp3 = false;
                    if (value.contains("Groupe 1")) {
                    grp1 = true;
                    }
                    if (value.contains("Groupe 2")) {
                        grp2 = true;
                    }
                    if (value.contains("Groupe 3")) {
                        grp3 = true;
                    }
                Commandes.NouvSemestre(con, grp1, grp2, grp3);
                Notification notif = Notification.show("Nouveau semestre créé");
                notif.setPosition(Position.BOTTOM_CENTER);
            } catch (Exception e) {
                System.out.println("erreur durant la connexion à la bdd : ajout de semestre IG");
            }
        });
    }
}