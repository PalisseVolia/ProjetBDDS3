package project;

import java.sql.Connection;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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
        email.setPlaceholder("nom@insa-strasbourg.com");
        email.setErrorMessage("adresse invalide");
        email.setClearButtonVisible(true);
        add(email);

        //creation du field de mdp
        mdp = new PasswordField();
        mdp.setLabel("Mot de passe");
        mdp.setPlaceholder("••••••••");
        mdp.setClearButtonVisible(true);
        add(mdp);

        // TODO: temporaire remplissage de test
        email.setValue("admin@test.com");
        mdp.setValue("admin");

        //bouton de validation
        valider = new Button();
        valider.setText("Valider");
        add(valider);

        //style settings
        email.setWidth("20em");
        mdp.setWidth("20em");
        valider.setWidth("10em");
        setAlignItems(Alignment.CENTER);

        //listener du bouton de validation, détermine si l'accès est donné à la partie admin ou étudiant
        valider.addClickListener((t) -> {
            String txtemail = email.getValue();
            String txtmdp = mdp.getValue();

            try (Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass")) {
                Personne p = Commandes.login(con, txtemail, txtmdp);
                if (p==null){
                    System.out.println("non reconnu");
                }else{
                    String s = p.testClasse();

                    if(s.equals("etudiant")){
                        System.out.println("etudiant");
                    }else if(s.equals("admin")){
                        System.out.println("admin");
                        main.setEntete(new AdminPageEntete(p.getPrenom(), p.getNom()));
                        main.setAlignment(0);
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