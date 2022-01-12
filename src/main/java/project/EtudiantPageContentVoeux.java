package project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.selection.SingleSelect;

import classes.Etudiant;
import classes.Module;
import bdd.Commandes;

// =======================================================================================
// Contenu de la page de choix de voeux etudiant
// =======================================================================================

public class EtudiantPageContentVoeux extends VerticalLayout{
    private Grid<Module> grid;
    private Button valider;
    private RadioButtonGroup<String> grpselect;
    private TextArea choisi;
    private Module mod;
    private ArrayList<String> listevoeux;

    public EtudiantPageContentVoeux(int id) throws SQLException, ClassNotFoundException {
        //creation des boutons de choix de groupe à afficher
        grpselect = new RadioButtonGroup<>();
        grpselect.setItems("Groupe 1", "Groupe 2", "Groupe 3");
        grpselect.setLabel("Selectionner le groupe Dans lequel faire un voeux");
        grpselect.setValue("Groupe 1");
        add(grpselect);

        //creation du tableau affichant les modules
        grid = new Grid<>(Module.class, false);
        grid.setVisible(false);
        setthegridmg(id);
        add(grid);

        //creation de la zone de texte indiquant le module voulu
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
        valider.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        setAlignItems(Alignment.CENTER);

        //connexion à la bdd
        Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass");
        
        //Si l'étudiant a déjà fait des voeux dans certain groupes l'interface de selection est remplacée par une indication du voeu effectué
        listevoeux = Commandes.getVoeux(con, id);
        try {
            if (listevoeux.get(0).equals(" ")) {
                grid.setVisible(true);
                choisi.setVisible(false);
                valider.setVisible(true);
                setthegridmg(id);
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
                listevoeux = Commandes.getVoeux(con, id);
                String value = grpselect.getValue().toString();
                if (value.contains("Groupe 1")) {
                    if (listevoeux.get(0).equals(" ")) {
                        grid.setVisible(true);
                        choisi.setVisible(false);
                        valider.setVisible(true);
                        setthegridmg(id);
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
                        setthegridmg(id);
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
                        setthegridmg(id);
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

        //lorsqu'une ligne du tableau est selectionnée le bouton de validation est activé
        SingleSelect<Grid<Module>, Module> moduselec = grid.asSingleSelect();
        moduselec.addValueChangeListener(selection -> {
            mod = selection.getValue();
            valider.setEnabled(false);
            if (selection != null) {
                valider.setEnabled(true);
            }
        });
        
        //lorsque le bouton de validation est cliqué on ajoute le module sélectionné comme voeux à l'étudiant
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
                Commandes.AjoutVoeux(con, Commandes.getidsem(con), id, mod.getId(), idgrp, 1);
                listevoeux = Commandes.getVoeux(con, id);
                valider.setVisible(false);
                grid.setVisible(false);
                choisi.setVisible(true);
                choisi.setValue("Votre voeu pour le groupe " + idgrp + " est :\n" + listevoeux.get(idgrp-1));
            } catch (Exception e) {
                System.out.println("Erreur lors de l'ajout de voeu à l'étudiant");
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
    public void setthegridmg(int id) throws SQLException, ClassNotFoundException {
        grid.removeAllColumns();
        //tableau contenant tous les modules
        grid.addColumn(Module::getIntitule).setHeader("Intitule").setSortable(true).setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(Module::getDescription).setHeader("Description").setSortable(true);
        grid.addColumn(Module::getNbPlaceMin).setHeader("Places min").setSortable(true).setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(Module::getNbPlaceMax).setHeader("places max").setSortable(true).setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(Module::getClasseacceptee).setHeader("Classes acceptées").setSortable(true).setAutoWidth(true).setFlexGrow(0);
        //connexion à la base de donnée
        Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass");
        //liste des modules du groupe
        List<Module> module = getModulegrp(con);
        //liste des modules qui seront ajoutés au tableau
        List<Module> finalmodule = new ArrayList<Module>();
        //liste de tous les modules choisis par l'étudiant durant les semestres précédents
        List<Integer> precedents = Commandes.getAllVoeux(con, id);
        //etudiant actuel
        Etudiant etu = Commandes.getEtudiant(con, id);
        //Pour chaque module du groupe
        for (int i = 0; i < module.size(); i++) {
            //Si toutes les classes sont acceptées ou la classe de l'étudiant est acceptée
            if (module.get(i).getClasseacceptee().equals("TOUTE")) {
                boolean dejapris = false;
                for (int j = 0; j < precedents.size(); j++) {
                    //on vérifie si les modules du groupe on été séléctionnés auparavant ou non
                    if (module.get(i).getId() == precedents.get(j)) {
                        dejapris = true;
                    }
                }
                if (dejapris == false) {
                    finalmodule.add(module.get(i));
                }
            }
            if (module.get(i).getClasseacceptee().equals(etu.getClasse())) {
                boolean dejapris = false;
                for (int j = 0; j < precedents.size(); j++) {
                    if (module.get(i).getId() == precedents.get(j)) {
                        dejapris = true;
                    }
                }
                if (dejapris == false) {
                    finalmodule.add(module.get(i));
                }
            }
        }
        grid.setItems(finalmodule);
    }
}