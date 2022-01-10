package project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SingleSelect;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;

import classes.Etudiant;
import bdd.Commandes;

// =======================================================================================
// Contenu de la page d'accueil Administrateur
// =======================================================================================

public class AdminPageContent extends VerticalLayout {
    private Grid<Etudiant> grid;
    private Button delete;
    private Etudiant et;
    
    public AdminPageContent() throws SQLException,ClassNotFoundException {
        //tableau contenant les étudiants
        grid = new Grid<>(Etudiant.class, false);
        grid.setSelectionMode(SelectionMode.SINGLE);
        setthegride();
        add(grid);

        
        //bouton permettant la supression de l'étudiant sélectionné
        delete = new Button();
        delete.setText("Supprimer");
        delete.setEnabled(false);
        add(delete);
        
        //style settings
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        setAlignItems(Alignment.CENTER);
        
        //connexion à la bdd
        Connection con = Commandes.connect("localhost", 5432, "postgres", "postgres", "pass");
        
        //lorsqu'une ligne du tableau sélectionnée on crée un Etudiant
        SingleSelect<Grid<Etudiant>, Etudiant> etuselect = grid.asSingleSelect();
        etuselect.addValueChangeListener(selection -> {
            et = selection.getValue();
            delete.setEnabled(false);
            if (selection != null) {
                delete.setEnabled(true);
            }
        });

        //suppression de l'etudiant selectionné au clic du bouton
        delete.addClickListener(t -> {
            try {
                Commandes.deleteEtudiant(con, et.getAdresse());
                Notification notif = Notification.show("Etudiant "+et.getNom()+" "+ et.getPrenom()+" a été supprimé de la base de données");
                notif.setPosition(Position.BOTTOM_CENTER);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                setthegride();
                delete.setEnabled(false);
            } catch (Exception e) {
                System.out.println("Problème lors de l'actualisation du tableau d'accueil admin");
            }
        });
    }
    
    //méthode permettant de recuperer les étudiants présents dans la base de donnée
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

    //methode mettant à jour le tableau
    public void setthegride() throws SQLException, ClassNotFoundException {
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
