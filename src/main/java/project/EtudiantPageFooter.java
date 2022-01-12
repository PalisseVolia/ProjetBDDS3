package project;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class EtudiantPageFooter extends HorizontalLayout{
    private VuePrincipale main;
    private Button bvoeux;
    private Button bhistorique;
    
    public EtudiantPageFooter(VuePrincipale mainvue, int id) {
        main = mainvue;

        bvoeux = new Button();
        bvoeux.setText("Voeux");
        add(bvoeux);
        bvoeux.setEnabled(false);
        
        bhistorique = new Button();
        bhistorique.setText("historique");
        add(bhistorique);
        bhistorique.setEnabled(true);

        bvoeux.setWidth("10em");
        bhistorique.setWidth("10em");

        bvoeux.addClickListener(t -> {
            bvoeux.setEnabled(false);
            bhistorique.setEnabled(true);
            try {
                main.setMainContent(new EtudiantPageContentVoeux(id));
            } catch (Exception e) {
                System.out.println("erreur lors de la création de la page de choix de voeux etudiant");
            }
        });
        
        bhistorique.addClickListener(t -> {
            bvoeux.setEnabled(true);
            bhistorique.setEnabled(false);
            try {
                main.setMainContent(new EtudiantPageContentHistorique());
            } catch (Exception e) {
                System.out.println("erreur lors de la création de la page d'historique de voeux etudiant");
            }
        });
    }
}
