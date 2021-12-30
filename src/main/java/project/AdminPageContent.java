package project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import classes.Etudiant;
import bdd.Commandes;

public class AdminPageContent extends VerticalLayout {
    private Grid<Etudiant> grid;
    private Button delete;
    
    public AdminPageContent() throws SQLException,ClassNotFoundException {
        grid = new Grid<>(Etudiant.class, false);
        grid.addColumn(Etudiant::getNom).setHeader("nom").setSortable(true);
        grid.addColumn(Etudiant::getPrenom).setHeader("prenom").setSortable(true);
        grid.addColumn(Etudiant::getAdresse).setHeader("email");
        grid.addColumn(Etudiant::getDateNaiss).setHeader("Date").setSortable(true);
        grid.addColumn(Etudiant::getClasse).setHeader("Classe").setSortable(true);
        
        Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass");
        List<Etudiant> etudiant = getEtudiant(con);
        grid.setItems(etudiant);
        add(grid);

        delete = new Button();
        delete.setText("Supprimer");
        add(delete);

        setAlignItems(Alignment.CENTER);

        grid.addSelectionListener(selection -> {
            Optional<Etudiant> etuselec = selection.getFirstSelectedItem();
            if (etuselec.isPresent()) {
                delete.addClickListener(t -> {
                    // TODO: suppression de l'étudiant sélectionné + sans doute un système de reload
                });
            }
        });
    }
    
    public static List<Etudiant> getEtudiant(Connection con) throws SQLException {
        //méthode permettant de recuperer une colonne c de la table "table"
        ArrayList<Etudiant> res = new ArrayList<Etudiant>();
        try ( Statement st = con.createStatement()) {
            try ( ResultSet rres = st.executeQuery(
                    """
                    select * from Etudiant
                     """)) {
            while (rres.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setid(rres.getInt(1));
                etudiant.setNom(rres.getString(2));
                etudiant.setPrenom(rres.getString(3));
                etudiant.setAdresse(rres.getString(4));
                etudiant.setMdp(rres.getString(5));
                etudiant.setDateNaiss(rres.getDate(6));
                etudiant.setDisponibilite(rres.getString(7));
                etudiant.setClasse(rres.getString(8));
                res.add(etudiant);
            }
            return res;
            }
        }
    }
}
