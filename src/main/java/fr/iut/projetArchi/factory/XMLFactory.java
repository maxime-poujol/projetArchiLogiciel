package fr.iut.projetArchi.factory;

import fr.iut.projetArchi.dao.catalogue.CatalogueDAO;
import fr.iut.projetArchi.dao.produit.ProduitDAO;

public class XMLFactory extends AbstractFactory{
    @Override
    public ProduitDAO createProduitDAO() {
        return null;
    }

    @Override
    public CatalogueDAO createCatalogueDAO() {
        return null;
    }
}
