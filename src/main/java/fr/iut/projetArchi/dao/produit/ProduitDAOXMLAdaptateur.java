package fr.iut.projetArchi.dao.produit;

import fr.iut.projetArchi.metier.produits.I_Produit;

import java.util.List;

public class ProduitDAOXMLAdaptateur implements ProduitDAO {

    private final ProduitDAO_XML produitDAO_xml;

    public ProduitDAOXMLAdaptateur() {
        produitDAO_xml = new ProduitDAO_XML();
    }

    @Override
    public void create(I_Produit produit) {
        produitDAO_xml.creer(produit);
    }

    @Override
    public void update(I_Produit produit) {
        produitDAO_xml.maj(produit);
    }

    @Override
    public void delete(I_Produit produit) {
        produitDAO_xml.supprimer(produit);
    }

    @Override
    public List<I_Produit> findAll() {
        return produitDAO_xml.lireTous();
    }

    @Override
    public I_Produit find(String nom) {
        return produitDAO_xml.lire(nom);
    }

    @Override
    public List<I_Produit> findByCatalogue(String catalogueName) {
        return null;
    }
}
