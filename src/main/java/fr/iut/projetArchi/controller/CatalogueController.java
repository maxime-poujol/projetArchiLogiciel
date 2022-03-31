package fr.iut.projetArchi.controller;

import fr.iut.projetArchi.FenetreNouveauProduit;
import fr.iut.projetArchi.FenetreSuppressionProduit;
import fr.iut.projetArchi.metier.catalogue.Catalogue;

import java.sql.SQLException;

public class CatalogueController {


    public static void openWindowAjoutProduit() {
        new FenetreNouveauProduit();
    }

    public static void openWindowSupprimerProduit() throws SQLException {
        new FenetreSuppressionProduit(Catalogue.getInstance().getNomProduits());
    }

    public static boolean ajouterProduit(String nom, double prixHT, int quantite){
        return Catalogue.getInstance().addProduit(nom, prixHT, quantite);
    }

    public static boolean supprimerProduit(String nom){
        return Catalogue.getInstance().removeProduit(nom);
    }


}
