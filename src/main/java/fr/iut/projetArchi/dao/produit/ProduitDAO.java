package fr.iut.projetArchi.dao.produit;

import fr.iut.projetArchi.metier.produits.I_Produit;

import java.util.List;


public interface ProduitDAO {


    /**
     * @param produit
     */
    void create(I_Produit produit);

    /**
     * @param produit
     */
    void update(I_Produit produit);

    /**
     * @param produit
     */
    void delete(I_Produit produit);

    /**
     * @return
     */
    List<I_Produit> findAll();

    /**
     * @param nom
     * @return
     */
    I_Produit find(String nom);

    List<I_Produit> findByCatalogue(String catalogueName);
}
