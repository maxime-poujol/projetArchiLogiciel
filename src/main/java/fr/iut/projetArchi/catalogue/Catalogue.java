package fr.iut.projetArchi.catalogue;

import fr.iut.projetArchi.produits.I_Produit;
import fr.iut.projetArchi.produits.Produit;
import fr.iut.projetArchi.util.Util;
import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Catalogue implements I_Catalogue{

    private Set<I_Produit> lesProduits;

    private static Catalogue instance;

    private Catalogue(){
        lesProduits = new HashSet<>();
    }

    public static Catalogue getInstance() {
        if (instance == null) {
            instance = new Catalogue();
        }
        return instance;
    }

    private I_Produit getProductByName(String name) {
        for(I_Produit produit : lesProduits) {
            if (produit.getNom().equals(name)) {
                return produit;
            }
        }
        return null;
    }

    @Override
    public boolean addProduit(I_Produit produit) {
        if (produit == null) return false;
        if (produit.getPrixUnitaireHT() <= 0) return false;
        if (produit.getPrixStockTTC() <= 0) return false;
        return lesProduits.add(produit);

    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        return addProduit(new Produit(nom, prix, qte));
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        if (l == null) {
            return 0;
        }

        int compteur = 0;

        for (I_Produit p: l) {
            if (addProduit(p)) compteur++;
        }

        return compteur;
    }

    @Override
    public boolean removeProduit(String nom) {
        return lesProduits.removeIf(i_produit -> Objects.equals(i_produit.getNom(), nom));
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        I_Produit product = getProductByName(nomProduit);
        if (product == null) {
            return false;
        }
        return product.ajouter(qteAchetee);
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {

        I_Produit product = getProductByName(nomProduit);
        if (product == null) {
            return false;
        }
        return product.enlever(qteVendue);
    }

    @Override
    public String[] getNomProduits() {

        int index = 0;
        String[] nomProduit = new String[lesProduits.size()];

        for (I_Produit p: lesProduits) {
            nomProduit[index] += p.getNom();
            index++;
        }

        return nomProduit;

    }

    @Override
    public double getMontantTotalTTC() {

        double montant = 0;

        for (I_Produit p: lesProduits) {
            montant += p.getPrixStockTTC();
         }

        return Util.doubleDeuxChiffreApresVirgule(montant);
    }

    @Override
    public void clear() {
        lesProduits.clear();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (I_Produit produit: lesProduits) {
            s.append(produit.getNom()).append(" - prix HT : ").append(produit.getPrixUnitaireHT()).append("€ - prix TTC : ").append(produit.getPrixUnitaireTTC()).append("€ - quantité en stock : ").append(produit.getQuantite()).append("\n");
        }

        s.append("\n").append("Montant total TTC du stock : ").append(getMontantTotalTTC()).append(" €");

        return s.toString();
    }
}
