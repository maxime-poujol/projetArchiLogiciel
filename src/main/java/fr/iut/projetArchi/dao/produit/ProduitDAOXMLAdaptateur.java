package fr.iut.projetArchi.dao.produit;

import fr.iut.projetArchi.metier.produits.I_Produit;
import java.sql.ResultSet;


public class ProduitDAOXMLAdaptateur implements ProduitDAO {

    private ProduitDAO_XML produitDAO_xml;

    public ProduitDAOXMLAdaptateur(){
        produitDAO_xml = new ProduitDAO_XML();
    }

    @Override
    public void create(I_Produit produit){
        produitDAO_xml.creer(produit);
    }

    @Override
    public void update(I_Produit produit){
        produitDAO_xml.maj(produit);
    }

    @Override
    public void delete(I_Produit produit) {

        produitDAO_xml.supprimer(produit);
    }

    @Override
    public ResultSet findAll(){
        return null;
    }

    @Override
    public ResultSet find(String nom) {
        return null;
    }
}
