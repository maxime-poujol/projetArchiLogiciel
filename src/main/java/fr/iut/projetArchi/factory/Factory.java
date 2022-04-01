package fr.iut.projetArchi.factory;

import fr.iut.projetArchi.dao.catalogue.CatalogueDAO;
import fr.iut.projetArchi.dao.produit.ProduitDAO;
import fr.iut.projetArchi.metier.catalogue.Catalogue;

import java.sql.Connection;

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
