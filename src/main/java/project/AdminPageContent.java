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

// =======================================================================================
// Contenu de la page d'accueil Administrateur
// =======================================================================================

public class AdminPageContent extends VerticalLayout {
    private Grid<Etudiant> grid;
    private Button delete;
    
    public AdminPageContent() throws SQLException,ClassNotFoundException {
        grid = new Grid<>(Etudiant.class, false);
        setthegrid();
        add(grid);

        Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass");

        //bouton permettant la supression de l'étudiant sélectionné
        delete = new Button();
        delete.setText("Supprimer");
        add(delete);

        //style settings
        setAlignItems(Alignment.CENTER);

        //récupération de la ligne du tableau sélectionnée et suppression au clic
        grid.addSelectionListener(selection -> {
            Optional<Etudiant> etuselec = selection.getFirstSelectedItem();
            if (etuselec.isPresent()) {
                delete.addClickListener(t -> {
                    Etudiant et = etuselec.get();
                    try {
                        //TODO: problème de double supression a regler si ya le temps
                        //TODO: System.out.println a enlever si joublie
                        System.out.println(et.getAdresse());
                        Commandes.deleteEtudiant(con, et.getAdresse());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        setthegrid();
                    } catch (Exception e) {
                        System.out.println("Problème lors de l'actualsiation du tableau d'accueil admin");
                    }
                });
            }
        });
    }
    
    //méthode permettant de recuperer le étudiants présents dans la base de donnée
    public static List<Etudiant> getEtudiant(Connection con) throws SQLException {
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

    public void setthegrid() throws SQLException, ClassNotFoundException {
        grid.removeAllColumns();
        //tableau contenant tous les étudiants
        grid.addColumn(Etudiant::getNom).setHeader("nom").setSortable(true);
        grid.addColumn(Etudiant::getPrenom).setHeader("prenom").setSortable(true);
        grid.addColumn(Etudiant::getAdresse).setHeader("email");
        grid.addColumn(Etudiant::getDateNaiss).setHeader("Date").setSortable(true);
        grid.addColumn(Etudiant::getClasse).setHeader("Classe").setSortable(true);
        //connexion à la base de donnée
        Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass");
        List<Etudiant> etudiant = getEtudiant(con);
        grid.setItems(etudiant);
    }
}
