package project;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

// =======================================================================================
// Footer du panel d'administration, contient les boutons permettant l'accès aux différentes fonctions
// =======================================================================================

public class AdminPageFooter extends HorizontalLayout{
    private VuePrincipale main;
    private Button baccueil;
    private Button bEtudiant;
    private Button bModule;
    private Button bSemestre;
    private Button bbibliotheque;
    private Button bgroupes;
    private Button bimpression;
    
    public AdminPageFooter(VuePrincipale mainvue) {
        main = mainvue;
        
        //bouton de retour à l'accueil
        baccueil = new Button();
        baccueil.setText("Accueil");
        add(baccueil);
        baccueil.setEnabled(false);

        //bouton de d'accès à la page d'ajout d'étudiant
        bEtudiant = new Button();
        bEtudiant.setText("Etudiant");
        add(bEtudiant);

        //bouton de d'accès à la page d'ajout de module
        bModule = new Button();
        bModule.setText("Module");
        add(bModule);

        //bouton de d'accès à la page d'ajout de moduels au groupes, la "bibliothèque" de modules
        bbibliotheque = new Button();
        bbibliotheque.setText("Bibliothèque");
        add(bbibliotheque);

        //bouton de d'accès à la page de suppression de modules/consultation du contenu des groupes
        bgroupes = new Button();
        bgroupes.setText("Groupes");
        add(bgroupes);

        //bouton de d'accès à la page d'ajout de semestre
        bSemestre = new Button();
        bSemestre.setText("Semestre");
        add(bSemestre);

        //bouton d'impression permettant la création d'un fichier texte
        bimpression = new Button();
        bimpression.setText("Impression");
        add(bimpression);

        //style settings
        baccueil.setWidth("8em");
        bEtudiant.setWidth("8em");
        bModule.setWidth("8em");
        bbibliotheque.setWidth("8em");
        bgroupes.setWidth("8em");
        bSemestre.setWidth("8em");
        bimpression.setWidth("8em");

        //Listeners permettant d'afficher le contenu correspondant aux boutons cliqués
        baccueil.addClickListener(t -> {
            baccueil.setEnabled(false);
            bEtudiant.setEnabled(true);
            bModule.setEnabled(true);
            bbibliotheque.setEnabled(true);
            bgroupes.setEnabled(true);
            bSemestre.setEnabled(true);
            bimpression.setEnabled(true);
            try {
                main.setMainContent(new AdminPageContent()); 
            } catch (Exception e) {
                System.out.println("Problème lors de la création de la page d'accueil admin");
            }
        });
        bEtudiant.addClickListener(t -> {
            baccueil.setEnabled(true);
            bEtudiant.setEnabled(false);
            bModule.setEnabled(true);
            bbibliotheque.setEnabled(true);
            bgroupes.setEnabled(true);
            bSemestre.setEnabled(true);
            bimpression.setEnabled(true);
            try {
                main.setMainContent(new AdminPageContentEtudiant());
            } catch (Exception e) {
                System.out.println("problème durant la création de la page d'ajout d'étudiant Admin");
            }
        });
        bModule.addClickListener(t -> {
            baccueil.setEnabled(true);
            bEtudiant.setEnabled(true);
            bModule.setEnabled(false);
            bbibliotheque.setEnabled(true);
            bgroupes.setEnabled(true);
            bSemestre.setEnabled(true);
            bimpression.setEnabled(true);
            try {
                main.setMainContent(new AdminPageContentModule());
            } catch (Exception e) {
                System.out.println("problème durant la création de la page d'ajout de Module Admin");
            }
        });
        bbibliotheque.addClickListener(t -> {
            baccueil.setEnabled(true);
            bEtudiant.setEnabled(true);
            bModule.setEnabled(true);
            bbibliotheque.setEnabled(false);
            bgroupes.setEnabled(true);
            bSemestre.setEnabled(true);
            bimpression.setEnabled(true);
            try {
                main.setMainContent(new AdminPageContentBibliotheque());
            } catch (Exception e) {
                System.out.println("problème durant la création de la page de bibliotheque");
            }
        });
        bgroupes.addClickListener(t -> {
            baccueil.setEnabled(true);
            bEtudiant.setEnabled(true);
            bModule.setEnabled(true);
            bbibliotheque.setEnabled(true);
            bgroupes.setEnabled(false);
            bSemestre.setEnabled(true);
            bimpression.setEnabled(true);
            try {
                main.setMainContent(new AdminPageContentGroupes());
            } catch (Exception e) {
                System.out.println("problème durant la création de la page de groupes");
            }
        });
        bSemestre.addClickListener(t -> {
            baccueil.setEnabled(true);
            bEtudiant.setEnabled(true);
            bModule.setEnabled(true);
            bbibliotheque.setEnabled(true);
            bgroupes.setEnabled(true);
            bSemestre.setEnabled(false);
            bimpression.setEnabled(true);
            try {
                main.setMainContent(new AdminPageContentSemestre());
            } catch (Exception e) {
                System.out.println("problème durant la création de la page d'ajout de Semestre");
            }
        });
        bimpression.addClickListener(t -> {
            baccueil.setEnabled(true);
            bEtudiant.setEnabled(true);
            bModule.setEnabled(true);
            bbibliotheque.setEnabled(true);
            bgroupes.setEnabled(true);
            bSemestre.setEnabled(true);
            bimpression.setEnabled(false);
            try {
                main.setMainContent(new AdminPageContentImpression());
            } catch (Exception e) {
                System.out.println("problème durant la création de la page d'ajout de Semestre");
            }
        });
    }
}
