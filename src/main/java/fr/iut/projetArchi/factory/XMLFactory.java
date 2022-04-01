package fr.iut.projetArchi.factory;

import fr.iut.projetArchi.dao.catalogue.CatalogueDAO;
import fr.iut.projetArchi.dao.catalogue.CatalogueDAOXML;
import fr.iut.projetArchi.dao.produit.ProduitDAO;
import fr.iut.projetArchi.dao.produit.ProduitDAOXMLAdaptateur;

public class XMLFactory extends AbstractFactory {

    @Override
    public ProduitDAO createProduitDAO() {
        return new ProduitDAOXMLAdaptateur();
    }

    @Override
    public CatalogueDAO createCatalogueDAO() {
        return new CatalogueDAOXML();
    }
}
