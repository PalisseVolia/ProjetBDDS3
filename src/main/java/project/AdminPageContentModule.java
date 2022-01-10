package project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import bdd.Commandes;

// =======================================================================================
// Contenu de la page permettant l'ajout de Modules de l'interface Administrateur
// =======================================================================================

public class AdminPageContentModule extends VerticalLayout{
    private TextField intitule;
    private TextArea desc;
    private ComboBox<String> dispo;
    private IntegerField min;
    private IntegerField max;
    private Button add;
    
    public AdminPageContentModule() throws ClassNotFoundException, SQLException {
        //creation du field d'intitule
        intitule = new TextField();
        intitule.setLabel("Intitule");
        add(intitule);

        //creation du field de description
        desc = new TextArea();
        desc.setLabel("Description du module");
        add(desc);

        //creation de l'entrée du nombre de place min n'acceptant que les entiers
        min = new IntegerField();
        min.setLabel("Min places");
        min.setValue(0);
        add(min);

        //creation de l'entrée du nombre de place max n'acceptant que les entiers
        max = new IntegerField();
        max.setLabel("Max places");
        max.setValue(10);
        max.setErrorMessage("Inférieur au minimum");
        add(max);

        //creation du field de sélection de disponibilité parmis une liste prédéfinie
        List<String> dispos = List.of("TOUTE", "GE2", "GM2", "GE3", "GM3", "GE4", "GCE2");
        dispo = new ComboBox<>();
        dispo.setItems(dispos);
        dispo.setValue("TOUTE");
        dispo.setLabel("Disponibilité");
        add(dispo);

        //bouton de d'ajout
        add = new Button();
        add.setText("Ajouter");
        add.setEnabled(false);
        add(add);

        //style settings
        add.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        setAlignItems(Alignment.CENTER);
        intitule.setWidth("17em");
        desc.setWidth("40em");
        desc.setHeight("20em");
        dispo.setWidth("17em");
        min.setWidth("17em");
        max.setWidth("17em");
        add.setWidth("13em");
        
        //connexion à la bdd
        Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass");

        //verifie que les champs soient remplis avant d'activer le bouton de validation
        intitule.addValueChangeListener(t -> {
            if ((intitule.getValue() != "")&&(desc.getValue() != "")&&(desc.getValue() != null)) {
                add.setEnabled(true);
            } else {
                add.setEnabled(false);
            }
        });
        desc.addValueChangeListener(t -> {
            if ((intitule.getValue() != "")&&(desc.getValue() != "")&&(desc.getValue() != null)&&(min.getValue() != null)&&(max.getValue() != null)) {
                add.setEnabled(true);
            } else {
                add.setEnabled(false);
            }
        });
        dispo.addValueChangeListener(t -> {
            if ((intitule.getValue() != "")&&(desc.getValue() != "")&&(desc.getValue() != null)&&(min.getValue() != null)&&(max.getValue() != null)) {
                add.setEnabled(true);
            } else {
                add.setEnabled(false);
            }
        });
        min.addValueChangeListener(t -> {
            if ((intitule.getValue() != "")&&(desc.getValue() != "")&&(desc.getValue() != null)&&(min.getValue() != null)&&(max.getValue() != null)) {
                add.setEnabled(true);
            } else {
                add.setEnabled(false);
            }
        });
        max.addValueChangeListener(t -> {
            if ((intitule.getValue() != "")&&(desc.getValue() != "")&&(desc.getValue() != null)&&(min.getValue() != null)&&(max.getValue() != null)) {
                add.setEnabled(true);
            } else {
                add.setEnabled(false);
            }
        });
        
        //en fontction de la valeur du minimum augmente la valeur min du champ max
        //si le min est plus grand que le max, le max est modifié pour etre égal au min
        min.addValueChangeListener(t -> {
            max.setMin(min.getValue());
            if (max.getValue() < min.getValue()) {
                max.setValue(min.getValue());
            }
        });
        
        //ajout d'un module lors de la pression du bouton d'ajout
        add.addClickListener(t -> {
            int tmp1 = min.getValue();
            int tmp2 = max.getValue();
            String minstr = String.valueOf(tmp1);
            String maxstr = String.valueOf(tmp2);
            try {
                Commandes.AjoutModule(con, intitule.getValue(), desc.getValue(), maxstr, minstr, dispo.getValue());
                Notification notif = Notification.show("Le module " + intitule.getValue() + " a été ajouté.");
                notif.setPosition(Position.BOTTOM_CENTER);
            } catch (Exception e) {
                System.out.println("Erreur lors de l'ajout d'un module depuis l'interface graphique");
            }
            //vide les champs
            intitule.setValue("");
            desc.setValue("");
            min.setValue(0);
            max.setValue(10);
            dispo.setValue("TOUTE");
        });
    }
}
