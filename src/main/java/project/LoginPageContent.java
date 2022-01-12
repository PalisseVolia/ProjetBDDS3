package project;

import java.sql.Connection;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.data.value.ValueChangeMode;

import bdd.Commandes;
import classes.Personne;

// =======================================================================================
// Contenu de la page de login
// =======================================================================================

public class LoginPageContent extends VerticalLayout{
    private VuePrincipale main;

    private EmailField email;
    private PasswordField mdp;
    private Button valider;
    private Span passwordStrengthText;
    private Icon checkIcon;

    public LoginPageContent(VuePrincipale mainvue) {
        main = mainvue;

        //creation du field d'email
        email = new EmailField();
        email.setLabel("Adresse mail");
        email.setPlaceholder("nom@insa-strasbourg.fr");
        email.setErrorMessage("adresse invalide");
        email.setClearButtonVisible(true);
        add(email);

        //creation du field de mdp
        mdp = new PasswordField();
        mdp.setLabel("Mot de passe");
        mdp.setPlaceholder("••••••••");
        mdp.setClearButtonVisible(true);
        add(mdp);
        
        //bouton de validation
        valider = new Button();
        valider.setText("Valider");
        valider.setEnabled(false);
        add(valider);

        //style settings
        email.setWidth("20em");
        mdp.setWidth("20em");
        valider.setWidth("10em");
        setAlignItems(Alignment.CENTER);

        //verifie que les champs soient remplsi avant d'activer le bouton de validation
        email.addValueChangeListener(t -> {
            if ((email.getValue() != "")&&(mdp.getValue() != "")) {
                valider.setEnabled(true);
            } else {
                valider.setEnabled(false);
            }
        });
        mdp.addValueChangeListener(t -> {
            if ((email.getValue() != "")&&(mdp.getValue() != "")) {
                valider.setEnabled(true);
            } else {
                valider.setEnabled(false);
            }
        });
        
        //listener du bouton de validation, détermine si l'accès est donné à la partie admin ou étudiant
        valider.addClickListener((t) -> {
            String txtemail = email.getValue();
            String txtmdp = mdp.getValue();
            
            try (Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass")) {
                Personne p = Commandes.login(con, txtemail, txtmdp);
                if (p.getNom()==null && p.getPrenom()==null){
                    Notification notif = Notification.show("Identifiant ou mot de passe invalide.");
                    notif.setPosition(Position.BOTTOM_CENTER);
                }else{
                    String s = p.testClasse();
                    if(s.equals("etudiant")){
                        main.setEntete(new EtudiantPageEntete(p.getPrenom(), p.getNom(), mainvue));
                        main.setMainContent(new EtudiantPageContentVoeux(p.getid()));
                        main.setFooter(new EtudiantPageFooter(main, p.getid()));
                        main.setAlignment(1);
                    }else if(s.equals("admin")){
                        main.setEntete(new AdminPageEntete(p.getPrenom(), p.getNom(), main));
                        main.setMainContent(new AdminPageContent());
                        main.setFooter(new AdminPageFooter(main));
                        main.setAlignment(1);
                    }
                }
            } catch (Exception err) {
                System.out.println("problème lors de la connexion");
            }
        });
        
        //indication force du mdp
        checkIcon = VaadinIcon.CHECK.create();
        checkIcon.setVisible(false);
        checkIcon.getStyle().set("color", "var(--lumo-success-color)");
        mdp.setSuffixComponent(checkIcon);
        Div passwordStrength = new Div();
        passwordStrengthText = new Span();
        passwordStrength.add(new Text("Password strength: "), passwordStrengthText);
        mdp.setHelperComponent(passwordStrength);
        mdp.setValueChangeMode(ValueChangeMode.EAGER);
        mdp.addValueChangeListener(e -> {
            String password = e.getValue();
            updateHelper(password);
        });
        updateHelper("");
    }

    //check de la force du mdp
    private void updateHelper(String password) {
        if (password.length() > 9) {
            passwordStrengthText.setText("strong");
            passwordStrengthText.getStyle().set("color", "var(--lumo-success-color)");
            checkIcon.setVisible(true);
        } else if (password.length() > 5) {
            passwordStrengthText.setText("moderate");
            passwordStrengthText.getStyle().set("color", "#e7c200");
            checkIcon.setVisible(false);
        } else {
            passwordStrengthText.setText("weak");
            passwordStrengthText.getStyle().set("color", "var(--lumo-error-color)");
            checkIcon.setVisible(false);
        }
    }
}