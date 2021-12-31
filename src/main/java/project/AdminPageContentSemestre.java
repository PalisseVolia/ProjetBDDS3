package project;

import java.sql.SQLException;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;

// =======================================================================================
// Contenu de la page permettant l'ajout de semestres de l'interface Administrateur
// =======================================================================================

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

        //ajout d'un semestre lors de la pression du bouton d'ajout
        add.addClickListener(t -> {
            // TODO: Ajouter le lien a la méthode d'ajout de semestre
        });
    }
}