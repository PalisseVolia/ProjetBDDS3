package project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import classes.Module;
import bdd.Commandes;

public class AdminPageContentBibliotheqe extends VerticalLayout{
    private Grid<Module> grid;
    private Button add;
    private CheckboxGroup<String> groupes;

    public AdminPageContentBibliotheqe() throws SQLException, ClassNotFoundException{
        grid = new Grid<>(Module.class, false);
        setthegridm();
        add(grid);

        groupes = new CheckboxGroup<>();
        groupes.setItems("Groupe 1", "Groupe 2", "Groupe 3");
        groupes.setLabel("Groupes auxquels ajouter le module :");
        add(groupes);

        //bouton de d'ajout
        add = new Button();
        add.setText("Ajouter");
        add(add);

        setAlignItems(Alignment.CENTER);

        grid.addSelectionListener(selection -> {
            Optional<Module> moduselec = selection.getFirstSelectedItem();
            if (moduselec.isPresent()) { 
                add.addClickListener(t -> {
                    Module mod = moduselec.get();
                    //TODO: ajouter le module selectionné au groupe correspondant
                    String value = groupes.getValue().toString();
                    boolean ajoutgrp1 = false;
                    boolean ajoutgrp2 = false;
                    boolean ajoutgrp3 = false;
                    if (value.contains("Groupe 1")) {
                        ajoutgrp1 = true;
                    }
                    if (value.contains("Groupe 2")) {
                        ajoutgrp2 = true;
                    }
                    if (value.contains("Groupe 3")) {
                        ajoutgrp3 = true;
                    } else {
                        Notification notif = Notification.show("Choisissez un group auquel ajouter le module.");
                        notif.setPosition(Position.BOTTOM_CENTER);
                    }
                });
            }
        });
    }

    //méthode permettant de recuperer les modules présents dans la base de donnée
    public static List<Module> getModule(Connection con) throws SQLException {
        ArrayList<Module> res = new ArrayList<Module>();
        try ( Statement st = con.createStatement()) {
            try ( ResultSet rres = st.executeQuery(
                    """
                    select * from module
                     """)) {
            while (rres.next()) {
                Module module = new Module();
                module.setIntitule(rres.getString(2));
                module.setDescription(rres.getString(3));
                module.setNbPlaceMax(rres.getInt(4));
                module.setNbPlaceMin(rres.getInt(5));
                module.setClasseacceptee(rres.getString(6));
                res.add(module);
            }
            return res;
            }
        }
    }
    
    public void setthegridm() throws SQLException, ClassNotFoundException {
        grid.removeAllColumns();
        //tableau contenant tous les modules
        grid.addColumn(Module::getIntitule).setHeader("Intitule").setSortable(true).setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(Module::getDescription).setHeader("Description").setSortable(true);
        grid.addColumn(Module::getNbPlaceMin).setHeader("Places min").setSortable(true).setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(Module::getNbPlaceMax).setHeader("places max").setSortable(true).setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(Module::getClasseacceptee).setHeader("Classes acceptées").setSortable(true).setAutoWidth(true).setFlexGrow(0);
        //connexion à la base de donnée
        Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass");
        List<Module> module = getModule(con);
        grid.setItems(module);
    }
}
