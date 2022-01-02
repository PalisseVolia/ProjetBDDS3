package project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;

import org.atmosphere.container.TomcatWebSocketUtil.Delegate;

import classes.Module;
import bdd.Commandes;

public class AdminPageContentGroupes extends VerticalLayout{
    private Grid<Module> grid;
    private Button del;
    private RadioButtonGroup<String> grpselect;

    public AdminPageContentGroupes() throws SQLException, ClassNotFoundException {
        grid = new Grid<>(Module.class, false);
        setthegridmg();
        add(grid);

        grpselect = new RadioButtonGroup<>();
        grpselect.setItems("Groupe 1", "Groupe 2", "Groupe 3");
        grpselect.setLabel("Selectionner le groupe à afficher");
        add(grpselect);

        del = new Button();
        del.setText("Supprimer");
        add(del);

        setAlignItems(Alignment.CENTER);

        grid.addSelectionListener(selection -> {
            Optional<Module> moduselec = selection.getFirstSelectedItem();
            if (moduselec.isPresent()) { 
                del.addClickListener(t -> {
                    Module mod = moduselec.get();
                    String value = grpselect.getValue().toString();
                    System.out.println(value);
                    boolean ajoutgrp1 = false;
                    boolean ajoutgrp2 = false;
                    boolean ajoutgrp3 = false;
                });
            }
        });
    }

    //méthode permettant de recuperer les modules correspondants au groupe selectionné
    public static List<Module> getModule(Connection con) throws SQLException {
        ArrayList<Module> res = new ArrayList<Module>();
        
        //TODO: faire la selection de modules du groupe selectionné, doit retourner une liste de modules
        // try ( Statement st = con.createStatement()) {
        //     try ( ResultSet rres = st.executeQuery(
        //             """
        //             select * from module
        //              """)) {
        //     while (rres.next()) {
        //         Module module = new Module();
        //         module.setIntitule(rres.getString(2));
        //         module.setDescription(rres.getString(3));
        //         module.setNbPlaceMax(rres.getInt(4));
        //         module.setNbPlaceMin(rres.getInt(5));
        //         module.setClasseacceptee(rres.getString(6));
        //         res.add(module);
        //     }
        //     return res;
        //     }
        // }

        return res;
    }

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
        List<Module> module = getModule(con);
        grid.setItems(module);
    }
}