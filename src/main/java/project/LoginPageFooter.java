package project;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import bdd.Initialisation;

// =======================================================================================
//   /  \       Ajoute un bouton permettant l'initialisation de la bdd
//  / !! \      Uniquement avec un objectif de démonstration
// /      \     N'a pas vocation a exister dans un produit fini
// =======================================================================================

public class LoginPageFooter extends HorizontalLayout{
    private Button devinit;

    public LoginPageFooter() {
        //bouton permettant l'initialisation de la bdd
        devinit = new Button();
        devinit.setText("Dev.init");
        add(devinit);

        //style settings
        devinit.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

        //au clic exéctue l'initialisation de la bdd
        devinit.addClickListener(t -> {
            Initialisation.init();
            Notification notif = Notification.show("Base de donnée initialisée avec succès.");
            notif.setPosition(Position.BOTTOM_CENTER);
        });
    }
}
