package fr.iut.projetArchi.dao.catalogue;


import fr.iut.projetArchi.metier.catalogue.I_Catalogue;

import java.util.List;

public interface CatalogueDAO {

    /**
     * @param produit
     */
    void create(I_Catalogue catalogue);

    /**
     * @param produit
     */
    void update(I_Catalogue catalogue);

    /**
     * @param produit
     */
    void delete(I_Catalogue catalogue);

    /**
     * @return
     */
    List<I_Catalogue> findAll();

    /**
     * @param nom
     * @return
     */
    I_Catalogue find(String nom);

}
