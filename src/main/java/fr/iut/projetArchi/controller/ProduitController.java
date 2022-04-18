package fr.iut.projetArchi.controller;

import fr.iut.projetArchi.FenetreAchat;
import fr.iut.projetArchi.FenetreVente;

public class ProduitController {

    public static void openWindowAchatProduit() {
        new FenetreAchat(CatalogueController.getCurrentCatalogue().getNomProduits());
    }

    public static void openWindowVenteProduit() {
        new FenetreVente(CatalogueController.getCurrentCatalogue().getNomProduits());
    }

    public static boolean acheterProduit(String nom, int qte) {
        return CatalogueController.getCurrentCatalogue().acheterStock(nom, qte);
    }

    public static boolean vendreProduit(String nom, int qte) {
        return CatalogueController.getCurrentCatalogue().vendreStock(nom, qte);
    }
}