package fr.iut.projetArchi.metier.catalogue;

import fr.iut.projetArchi.metier.produits.I_Produit;

import java.sql.SQLException;
import java.util.List;

public interface I_Catalogue {

    boolean addProduit(I_Produit produit);

    boolean addProduit(String nom, double prix, int qte);

    int addProduits(List<I_Produit> l);

    boolean removeProduit(String nom);

    boolean acheterStock(String nomProduit, int qteAchetee);

    boolean vendreStock(String nomProduit, int qteVendue);

    String[] getNomProduits();

    double getMontantTotalTTC();

    String toString();

    void clear();

}