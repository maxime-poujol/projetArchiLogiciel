package fr.iut.projetArchi.factory;

import fr.iut.projetArchi.dao.catalogue.CatalogueDAO;
import fr.iut.projetArchi.dao.catalogue.CatalogueDAORelationnel;
import fr.iut.projetArchi.dao.catalogue.CatalogueDAOXML;
import fr.iut.projetArchi.dao.produit.ProduitDAO;
import fr.iut.projetArchi.dao.produit.ProduitDAORelationnel;
import fr.iut.projetArchi.metier.catalogue.Catalogue;

public class RelationnelFactory extends AbstractFactory{



    @Override
    public ProduitDAO createProduitDAO() {
        return new ProduitDAORelationnel();
    }

    @Override
    public CatalogueDAO createCatalogueDAO() {
        return new CatalogueDAORelationnel();
    }
}
