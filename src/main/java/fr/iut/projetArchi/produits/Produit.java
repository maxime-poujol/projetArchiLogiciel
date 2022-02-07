package fr.iut.projetArchi.produits;

public class Produit implements I_Produit {

    private int quantiteStock;
    private String nom;
    private double prixUnitaireHT;
    private static float tauxTVA = 0.2f;

    public Produit(int quantiteStock, String nom, double prixUnitaireHT) {
        this.quantiteStock = quantiteStock;
        this.nom = nom;
        this.prixUnitaireHT = prixUnitaireHT;
    }

    @Override
    public boolean ajouter(int qteAchetee) {
        if(qteAchetee <= 0) return false;
        quantiteStock += qteAchetee;
        return true;
    }

    @Override
    public boolean enlever(int qteVendue) {
        if(qteVendue <= 0) return false;
        if(quantiteStock < qteVendue) return false;
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
        return prixUnitaireHT * tauxTVA;
    }


    @Override
    public double getPrixStockTTC() {
        return getPrixUnitaireTTC() * quantiteStock;
    }
}
