package fr.iut.projetArchi.metier.catalogue;

import fr.iut.projetArchi.dao.produit.ProduitDAO;
import fr.iut.projetArchi.factory.ProduitFactory;
import fr.iut.projetArchi.metier.produits.I_Produit;
import fr.iut.projetArchi.metier.produits.Produit;
import fr.iut.projetArchi.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Catalogue implements I_Catalogue {

    private List<I_Produit> lesProduits;
    private static ProduitDAO produitDAO;

    private static Catalogue instance;

    private Catalogue() {
        lesProduits = new ArrayList<>();
    }

    public static Catalogue getInstance() {
        if (instance == null) {
            instance = new Catalogue();
            produitDAO = ProduitFactory.getIntance().createProduitDAO();
            ResultSet rs;
            try {
                rs = produitDAO.findAll();
                while (rs.next()) {
                    I_Produit produit = new Produit(
                            rs.getString("nom"),
                            rs.getInt("prixunitaireht"),
                            rs.getInt("qtestock"));
                    instance.lesProduits.add(produit);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return instance;
    }

    private I_Produit getProductByName(String name) {
        for (I_Produit produit : lesProduits) {
            if (produit.getNom().equals(name)) {
                return produit;
            }
        }
        return null;
    }

    @Override
    public boolean addProduit(I_Produit produit) {
        if (produit == null) return false;
        if (produit.getNom() == null) return false;
        if (produit.getPrixUnitaireHT() <= 0) return false;
        if (produit.getQuantite() < 0) return false;
        String nom = Util.formatNom(produit.getNom());
        produit = new Produit(nom, produit.getPrixUnitaireHT(), produit.getQuantite());

        for (I_Produit p : lesProduits) {
            if (p.equals(produit)) {
                return false;
            }
        }
        produitDAO.create(produit);

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

        for (I_Produit p : l) {
            if (addProduit(p)) compteur++;
        }

        return compteur;
    }

    @Override
    public boolean removeProduit(String nom) {
        produitDAO.delete(getProductByName(nom));
        return lesProduits.removeIf(i_produit -> Objects.equals(i_produit.getNom(), nom));
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        I_Produit product = getProductByName(nomProduit);
        if (product == null) {
            return false;
        }
        boolean bool = product.ajouter(qteAchetee);
        if (bool) {
            produitDAO.update(product);
        }
        return bool;
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {

        I_Produit product = getProductByName(nomProduit);
        if (product == null) {
            return false;
        }
        boolean bool = product.enlever(qteVendue);

        if (bool) {
            produitDAO.update(product);
        }

        return bool;
    }

    @Override
    public String[] getNomProduits() {

        int index = 0;
        String[] nomProduit = new String[lesProduits.size()];
        for (I_Produit p : lesProduits) {

            nomProduit[index] = p.getNom();
            index++;
        }
        Arrays.sort(nomProduit);
        return nomProduit;

    }

    @Override
    public double getMontantTotalTTC() {

        double montant = 0;

        for (I_Produit p : lesProduits) {
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

        for (I_Produit produit : lesProduits) {
            // double prixHT = Util.doubleDeuxChiffreApresVirgule();
            //double prixTTC = Util.doubleDeuxChiffreApresVirgule(produit.getPrixUnitaireTTC());
            //s.append(produit.getNom()).append(" - prix HT : ").append(Util.formatDoubleNumber(prixHT)).append(" € - prix TTC : ").append(Util.formatDoubleNumber(prixTTC)).append(" € - quantité en stock : ").append(produit.getQuantite()).append("\n");
            s.append(produit.getNom())
                    .append(" - prix HT : ")
                    .append(Util.frStringDeuxChiffreApresVirgule(produit.getPrixUnitaireHT()))
                    .append(" € - prix TTC : ")
                    .append(Util.frStringDeuxChiffreApresVirgule(produit.getPrixUnitaireTTC()))
                    .append(" € - quantité en stock : ")
                    .append(produit.getQuantite())
                    .append("\n");
        }

        s.append("\n").append("Montant total TTC du stock : ")
                .append(Util.frStringDeuxChiffreApresVirgule(getMontantTotalTTC()))
                .append(" €");

        return s.toString();
    }
}
