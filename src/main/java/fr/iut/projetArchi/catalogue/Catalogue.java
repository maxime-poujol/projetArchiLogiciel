package fr.iut.projetArchi.catalogue;

import fr.iut.projetArchi.produits.I_Produit;
import fr.iut.projetArchi.produits.Produit;

import java.util.*;

public class Catalogue implements I_Catalogue{

    private Set<I_Produit> lesProduits;

    private static Catalogue instance;

    public Catalogue(){
        lesProduits = new HashSet<>();
    }

    public static Catalogue getInstance() {
        if (instance == null) {
            instance = new Catalogue();
        }
        return instance;
    }

    @Override
    public boolean addProduit(I_Produit produit) {
        return lesProduits.add(produit);
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        return addProduit(new Produit(qte, nom, prix));
    }

    @Override
    public int addProduits(List<I_Produit> l) {

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

        return false;
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        return false;
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

        int montant = 0;

        for (I_Produit p: lesProduits) {
            montant += p.getPrixStockTTC();
         }

        return montant;
    }

    @Override
    public void clear() {
        lesProduits.clear();
    }
}
