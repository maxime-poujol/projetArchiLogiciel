package fr.iut.projetArchi.controller;

import fr.iut.projetArchi.FenetreAffichage;
import fr.iut.projetArchi.metier.catalogue.Catalogue;

import java.sql.SQLException;

public class StockController {


    public static void openWindowStock() throws SQLException {
        new FenetreAffichage(Catalogue.getInstance().toString());
    }











}
