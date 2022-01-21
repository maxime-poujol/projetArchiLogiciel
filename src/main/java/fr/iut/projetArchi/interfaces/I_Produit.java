package fr.iut.projetArchi.interfaces;

public interface I_Produit {

    boolean ajouter(int qteAchetee);

    boolean enlever(int qteVendue);

    String getNom();

    int getQuantite();

    double getPrixUnitaireHT();

    double getPrixUnitaireTTC();

    double getPrixStockTTC();

    String toString();

}