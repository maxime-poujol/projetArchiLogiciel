package fr.iut.projetArchi.controller;

import fr.iut.projetArchi.FenetreAffichage;
import fr.iut.projetArchi.metier.catalogue.Catalogue;

import java.sql.SQLException;

public class StockController extends Observable{


    public static void openWindowStock() {
        new FenetreAffichage(getEtatStock());
    }

    public static String getEtatStock() {
        System.out.println(CatalogueController.getCurrentCatalogue());
        return CatalogueController.getCurrentCatalogue().toString();
    }




}
