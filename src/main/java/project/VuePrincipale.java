package project;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@PWA(name = "My App", shortName = "My App", enableInstallPrompt = false)
@Theme(themeFolder = "myapp", variant = Lumo.DARK)
@Route("")
@PageTitle("Main")

// =======================================================================================
// Page principale de l'interface graphique
// =======================================================================================

public class VuePrincipale extends VerticalLayout{
    
    private MyHorizontalLayout entete;
    private MyVerticalLayout mainContent;
    private MyHorizontalLayout footer;

    public VuePrincipale() {

        //création d'un Layout Horizontal/Vertical
        this.entete = new MyHorizontalLayout();
        this.add(this.entete);
        this.mainContent = new MyVerticalLayout();
        this.add(this.mainContent);
        this.footer = new MyHorizontalLayout();
        this.add(this.footer);

        //remplacement par les layouts de la page de Login
        this.setEntete(new LoginPageEntete());
        this.setMainContent(new LoginPageContent(this));
        this.setFooter(new LoginPageFooter());
        //style de la page de login
        setHeight("75%");
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    //méthodes de remplacement de Layouts
    public void setEntete(Component c) {
        this.entete.removeAll();
        this.entete.add(c);
    }
    public void setMainContent(Component c) {
        this.mainContent.removeAll();
        this.mainContent.add(c);
    }
    public void setFooter(Component c) {
        this.footer.removeAll();
        this.footer.add(c);
    }

    //méthodes permettant de changer l'alignement des layouts sur la vue principale
    public void setAlignment(int value) {
        switch (value) {
            case 0:
            setAlignItems(Alignment.START);
                break;
            case 1:
            setAlignItems(Alignment.CENTER);
                break;
            case 2:
            setAlignItems(Alignment.END);
                break;
        
            default:
                break;
        }
    }
        
}