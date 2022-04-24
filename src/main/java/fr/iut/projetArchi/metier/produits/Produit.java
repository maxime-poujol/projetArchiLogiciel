package fr.iut.projetArchi.metier.produits;

import java.util.Objects;

public class Produit implements I_Produit {

    private static final float tauxTVA = 0.2f;
    private int quantiteStock;
    private final String nom;
    private final double prixUnitaireHT;

    private String nomCatalogue;

    @Override
    public String getNomCatalogue() {
        return nomCatalogue;
    }

    public Produit(String nom, double prixUnitaireHT, int quantiteStock, String nomCatalogue) {
        this.quantiteStock = quantiteStock;
        this.nom = nom;
        this.prixUnitaireHT = prixUnitaireHT;
        this.nomCatalogue = nomCatalogue;
    }

    @Override
    public boolean ajouter(int qteAchetee) {
        if (qteAchetee <= 0) return false;
        quantiteStock += qteAchetee;
        return true;
    }

    @Override
    public boolean enlever(int qteVendue) {
        if (qteVendue <= 0) return false;
        if (quantiteStock < qteVendue) return false;
        quantiteStock -= qteVendue;
        return true;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public int getQuantite() {
        return quantiteStock;
    }

    @Override
    public double getPrixUnitaireHT() {
        return prixUnitaireHT;
    }

    @Override
    public double getPrixUnitaireTTC() {
        return prixUnitaireHT * (1 + tauxTVA);
    }


    @Override
    public double getPrixStockTTC() {
        return getPrixUnitaireTTC() * quantiteStock;
    }

    @Override
    public int getIdCatalogue() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return nom.equals(produit.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
