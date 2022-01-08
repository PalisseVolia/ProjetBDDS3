package project;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

// =======================================================================================
// Footer du panel d'administration, contient les boutons permettant l'accès aux différentes donctions
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
        baccueil.setVisible(false);

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

        //Listeners permettant d'afficher le contenu correspondant aux boutons cliqués
        baccueil.addClickListener(t -> {
            baccueil.setVisible(false);
            bEtudiant.setVisible(true);
            bModule.setVisible(true);
            bbibliotheque.setVisible(true);
            bgroupes.setVisible(true);
            bSemestre.setVisible(true);
            bimpression.setVisible(true);
            try {
                main.setMainContent(new AdminPageContent()); 
            } catch (Exception e) {
                System.out.println("Problème lors de la création de la page d'accueil admin");
            }
        });
        bEtudiant.addClickListener(t -> {
            baccueil.setVisible(true);
            bEtudiant.setVisible(false);
            bModule.setVisible(true);
            bbibliotheque.setVisible(true);
            bgroupes.setVisible(true);
            bSemestre.setVisible(true);
            bimpression.setVisible(true);
            try {
                main.setMainContent(new AdminPageContentEtudiant());
            } catch (Exception e) {
                System.out.println("problème durant la création de la page d'ajout d'étudiant Admin");
            }
        });
        bModule.addClickListener(t -> {
            baccueil.setVisible(true);
            bEtudiant.setVisible(true);
            bModule.setVisible(false);
            bbibliotheque.setVisible(true);
            bgroupes.setVisible(true);
            bSemestre.setVisible(true);
            bimpression.setVisible(true);
            try {
                main.setMainContent(new AdminPageContentModule());
            } catch (Exception e) {
                System.out.println("problème durant la création de la page d'ajout de Module Admin");
            }
        });
        bbibliotheque.addClickListener(t -> {
            baccueil.setVisible(true);
            bEtudiant.setVisible(true);
            bModule.setVisible(true);
            bbibliotheque.setVisible(false);
            bgroupes.setVisible(true);
            bSemestre.setVisible(true);
            bimpression.setVisible(true);
            try {
                main.setMainContent(new AdminPageContentBibliotheqe());
            } catch (Exception e) {
                System.out.println("problème durant la création de la page de bibliotheque");
            }
        });
        bgroupes.addClickListener(t -> {
            baccueil.setVisible(true);
            bEtudiant.setVisible(true);
            bModule.setVisible(true);
            bbibliotheque.setVisible(true);
            bgroupes.setVisible(false);
            bSemestre.setVisible(true);
            bimpression.setVisible(true);
            try {
                main.setMainContent(new AdminPageContentGroupes());
            } catch (Exception e) {
                System.out.println("problème durant la création de la page de groupes");
            }
        });
        bSemestre.addClickListener(t -> {
            baccueil.setVisible(true);
            bEtudiant.setVisible(true);
            bModule.setVisible(true);
            bbibliotheque.setVisible(true);
            bgroupes.setVisible(true);
            bSemestre.setVisible(false);
            bimpression.setVisible(true);
            try {
                main.setMainContent(new AdminPageContentSemestre());
            } catch (Exception e) {
                System.out.println("problème durant la création de la page d'ajout de Semestre");
            }
        });
        bimpression.addClickListener(t -> {
            baccueil.setVisible(true);
            bEtudiant.setVisible(true);
            bModule.setVisible(true);
            bbibliotheque.setVisible(true);
            bgroupes.setVisible(true);
            bSemestre.setVisible(true);
            bimpression.setVisible(false);
            try {
                main.setMainContent(new AdminPageContentImpression());
            } catch (Exception e) {
                System.out.println("problème durant la création de la page d'ajout de Semestre");
            }
        });
    }
}
