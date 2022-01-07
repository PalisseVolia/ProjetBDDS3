package project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.selection.SingleSelect;

import classes.Module;
import bdd.Commandes;

// =======================================================================================
// Contenu de la page de gestion de groupes Administrateur
// =======================================================================================

public class EtudiantPageContent extends VerticalLayout{
    private Grid<Module> grid;
    private Button valider;
    private RadioButtonGroup<String> grpselect;
    private TextArea choisi;
    private Module mod;

    public EtudiantPageContent(int id) throws SQLException, ClassNotFoundException {
        System.out.println(id);
        //creation des boutons de choix de groupe à afficher
        grpselect = new RadioButtonGroup<>();
        grpselect.setItems("Groupe 1", "Groupe 2", "Groupe 3");
        grpselect.setLabel("Selectionner le groupe Dans lequel faire un voeux");
        grpselect.setValue("Groupe 1");
        add(grpselect);

        //creation du tableau affichant les modules
        grid = new Grid<>(Module.class, false);
        grid.setVisible(false);
        setthegridmg();
        add(grid);

        choisi = new TextArea();
        choisi.setVisible(false);
        add(choisi);

        //creation du bouton permettant la suppression de modules
        valider = new Button();
        valider.setText("Valider mon choix");
        valider.setEnabled(false);
        add(valider);

        //style settings
        valider.setWidth("13em");
        choisi.setWidth("30em");
        setAlignItems(Alignment.CENTER);

        //connexion à la bdd
        Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass");
        
        ArrayList<String> listevoeux = Commandes.getVoeux(con, id);
        try {
            if (listevoeux.get(0).equals(" ")) {
                grid.setVisible(true);
                choisi.setVisible(false);
                valider.setVisible(true);
                setthegridmg();
            } else {
                grid.setVisible(false);
                choisi.setVisible(true);
                valider.setVisible(false);
                choisi.setValue("Votre voeu pour le premier groupe est :\n" + listevoeux.get(0));
            }
        } catch (Exception e) {
            System.out.println("erreur lors de l'actualisation du tableau de modules");
        }
        grpselect.addValueChangeListener(t -> {
            try {
                String value = grpselect.getValue().toString();
                if (value.contains("Groupe 1")) {
                    if (listevoeux.get(0).equals(" ")) {
                        grid.setVisible(true);
                        choisi.setVisible(false);
                        valider.setVisible(true);
                        setthegridmg();
                    } else {
                        grid.setVisible(false);
                        choisi.setVisible(true);
                        valider.setVisible(false);
                        choisi.setValue("Votre voeu pour le premier groupe est :\n" + listevoeux.get(0));
                    }
                }
                if (value.contains("Groupe 2")) {
                    if (listevoeux.get(1).equals(" ")) {
                        grid.setVisible(true);
                        choisi.setVisible(false);
                        valider.setVisible(true);
                        setthegridmg();
                    } else {
                        grid.setVisible(false);
                        choisi.setVisible(true);
                        valider.setVisible(false);
                        choisi.setValue("Votre voeu pour le second groupe est :\n" + listevoeux.get(1));
                    }
                }
                if (value.contains("Groupe 3")) {
                    if (listevoeux.get(2).equals(" ")) {
                        grid.setVisible(true);
                        choisi.setVisible(false);
                        valider.setVisible(true);
                        setthegridmg();
                    } else {
                        grid.setVisible(false);
                        choisi.setVisible(true);
                        valider.setVisible(false);
                        choisi.setValue("Votre voeu pour le troisième groupe est :\n" + listevoeux.get(2));
                    }
                }
            } catch (Exception e) {
                System.out.println("erreur lors de l'actualisation du tableau de modules");
            }
            //desactive le bouton de suppression au changement de tableau
            valider.setEnabled(false);
        });

        //TODO: maj desc (lorsqu'une ligne du tableau sélectionnée on crée un module)
        SingleSelect<Grid<Module>, Module> moduselec = grid.asSingleSelect();
        moduselec.addValueChangeListener(selection -> {
            mod = selection.getValue();
            valider.setEnabled(false);
            if (selection != null) {
                valider.setEnabled(true);
            }
            //TODO: en fonction de si l'étudiant à déjà un groupe affiche les modules/indicatif de la selection
        });
        
        //suppression du module selectionné au clic du bouton
        valider.addClickListener(t -> {
            //recuperation du groupe selectionne
            String value = grpselect.getValue().toString();
            int idgrp = 0;
            if (value.contains("Groupe 1")) {
                idgrp = 1;
            }
            if (value.contains("Groupe 2")) {
                idgrp = 2;
            }
            if (value.contains("Groupe 3")) {
                idgrp = 3;
            }
            try {
                //TODO: ajout du voeu à l'étudiant pour le groupe donné
                valider.setVisible(false);
                grid.setVisible(false);
                //TODO: ajout d'une zone de texte indiquant le voeux choisi
            } catch (Exception e) {
                //TODO: handle exception
            }
        });
    }

    //méthode permettant de recuperer les modules correspondants au groupe selectionné
    public List<Module> getModulegrp(Connection con) throws SQLException, ClassNotFoundException {
        int idsem = 0;
        idsem = Commandes.getidsem(con);
        //recuperation du groupe selectionne
        String value = grpselect.getValue().toString();
        int idgrp = 0;
        if (value.contains("Groupe 1")) {
            idgrp = 1;
        }
        if (value.contains("Groupe 2")) {
            idgrp = 2;
        }
        if (value.contains("Groupe 3")) {
            idgrp = 3;
        }
        //recuperation des modules correspondant au groupe et au semestre
        List<Module> res = Commandes.getModule(con, idsem, idgrp);
        return res;
    }

    //methode mettant a jour le tableau de modules
    public void setthegridmg() throws SQLException, ClassNotFoundException {
        grid.removeAllColumns();
        //tableau contenant tous les modules
        grid.addColumn(Module::getIntitule).setHeader("Intitule").setSortable(true).setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(Module::getDescription).setHeader("Description").setSortable(true);
        grid.addColumn(Module::getNbPlaceMin).setHeader("Places min").setSortable(true).setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(Module::getNbPlaceMax).setHeader("places max").setSortable(true).setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(Module::getClasseacceptee).setHeader("Classes acceptées").setSortable(true).setAutoWidth(true).setFlexGrow(0);
        //connexion à la base de donnée
        Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass");
        List<Module> module = getModulegrp(con);
        grid.setItems(module);
    }
}