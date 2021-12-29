package project;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.data.value.ValueChangeMode;

// =======================================================================================
// Contenu de la page de login
// =======================================================================================

public class LoginPageContent extends VerticalLayout{
    private EmailField email;
    private PasswordField mdp;
    private Span passwordStrengthText;
    private Icon checkIcon;

    public LoginPageContent() {
        //creation du field d'email
        this.email = new EmailField();
        this.email.setLabel("Adresse mail");
        this.email.setPlaceholder("nom@insa-strasbourg.com");
        this.email.setErrorMessage("adresse invalide");
        this.email.setClearButtonVisible(true);
        this.add(email);

        //creation du field de mdp
        this.mdp = new PasswordField();
        this.mdp.setLabel("Mot de passe");
        this.mdp.setClearButtonVisible(true);
        this.add(mdp);

        //style settings
        email.setWidth("20em");
        mdp.setWidth("20em");
        setAlignItems(Alignment.CENTER);

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