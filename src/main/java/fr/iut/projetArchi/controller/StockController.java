package fr.iut.projetArchi.controller;

import fr.iut.projetArchi.FenetreAffichage;
import fr.iut.projetArchi.metier.catalogue.Catalogue;

public class StockController {


    public static void openWindowStock(){
        new FenetreAffichage(Catalogue.getInstance().toString());
    }











}
