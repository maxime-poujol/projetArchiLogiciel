package fr.iut.projetArchi.factory;

import fr.iut.projetArchi.dao.catalogue.CatalogueDAO;
import fr.iut.projetArchi.dao.produit.ProduitDAO;

public interface Factory {

    /**
     *
     */
    ProduitDAO createProduitDAO();

    /**
     *
     */
    CatalogueDAO createCatalogueDAO();


}
