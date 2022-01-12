package project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import bdd.Commandes;

public class EtudiantPageContentHistorique extends VerticalLayout{
    private Grid<Module> grid;
    
    public EtudiantPageContentHistorique() {
        grid = new Grid<>(Module.class, false);
        setthegridm();
        add(grid);
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
