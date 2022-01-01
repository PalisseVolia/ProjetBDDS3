package project;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import bdd.Commandes;

// =======================================================================================
// Contenu de la page permettant l'ajout d'étudiants de l'interface Administrateur
// =======================================================================================

public class AdminPageContentEtudiant extends VerticalLayout{
    private TextField nom;
    private TextField prenom;
    private EmailField email;
    private PasswordField mdp;
    private DatePicker date;
    private ComboBox<String> classe;
    private Button add;
    private Icon checkIcon;
    private Span passwordStrengthText;

    public AdminPageContentEtudiant() throws SQLException, ClassNotFoundException{
        //creation du field de nom
        nom = new TextField();
        nom.setPlaceholder("Nom");
        add(nom);

        //creation du field de prenom
        prenom = new TextField();
        prenom.setPlaceholder("Prenom");
        add(prenom);

        //creation du field d'email
        email = new EmailField();
        email.setPlaceholder("Email");
        email.setErrorMessage("adresse invalide");
        email.setClearButtonVisible(true);
        add(email);

        //creation du field de mot de passe
        mdp = new PasswordField();
        mdp.setPlaceholder("Mot de passe");
        mdp.setClearButtonVisible(true);
        add(mdp);

        //creation du field de sélection de date
        date = new DatePicker();
        date.setLabel("Date de naissance");
        add(date);

        //creation du field de sélection de classe parmis une liste prédéfinie
        List<String> classes = List.of("GE2", "GM2", "GE3", "GM3", "GE4", "GCE2");
        classe = new ComboBox<>();
        classe.setItems(classes);
        classe.setLabel("Classe");
        add(classe);

        //bouton de d'ajout
        add = new Button();
        add.setText("Ajouter");
        add(add);

        //style settings
        setAlignItems(Alignment.CENTER);
        nom.setWidth("17em");
        prenom.setWidth("17em");
        email.setWidth("17em");
        mdp.setWidth("17em");
        date.setWidth("17em");
        classe.setWidth("17em");
        add.setWidth("13em");

        //connexion à la bdd
        Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass");
        
        //ajout d'un étudiant lors de la pression du bouton d'ajout
        add.addClickListener(t -> {
            LocalDate tmp = date.getValue();
            String dispo = "true";
            try {
            
                if(Commandes.adresseValide(email.getValue()) ==true) {
                    //si l'adresse contient "@insa-strasbourg.fr"
                    
                    
                    if(Commandes.adresseExiste(con, email.getValue())==false){
                        //si l'adresse n'existe pas encore dans la bdd
                        Commandes.AjoutEtudiant(con, nom.getValue(), prenom.getValue(), email.getValue(), mdp.getValue(), tmp.toString(), dispo, classe.getValue());
                }       
                    else{
                        System.out.println("Erreur lors de l'ajout d'étudiant depuis l'interface graphique : l'adresse email existe deja");
                    }

                }else{
                    System.out.println("Erreur lors de l'ajout d'étudiant depuis l'interface graphique : adresse email invalide");
                    
                }
               
            
            } catch (Exception e) {
                System.out.println("Erreur lors de l'ajout d'étudiant depuis l'interface graphique");
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
