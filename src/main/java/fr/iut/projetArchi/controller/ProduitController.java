package fr.iut.projetArchi.controller;

import fr.iut.projetArchi.FenetreAchat;
import fr.iut.projetArchi.FenetreVente;
import fr.iut.projetArchi.metier.catalogue.Catalogue;

import java.sql.SQLException;

public class ProduitController {

    public static void openWindowAchatProduit(){
        new FenetreAchat(Catalogue.getInstance().getNomProduits());
    }

    public static void openWindowVenteProduit(){
        new FenetreVente(Catalogue.getInstance().getNomProduits());
    }

    public static boolean acheterProduit(String nom, int qte){
        return Catalogue.getInstance().acheterStock(nom,qte);
    }

    public static boolean vendreProduit(String nom, int qte){
        return Catalogue.getInstance().vendreStock(nom,qte);
    }
}