package fr.iut.projetArchi.dao.produit;

import fr.iut.projetArchi.metier.produits.I_Produit;

import java.sql.ResultSet;


public interface ProduitDAO {

    /**
     *
     * @param produit
     */
    void create(I_Produit produit);

    /**
     * @param produit
     */
    void update(I_Produit produit);

    /**
     * @param nom
     */
    void delete(String nom);

    /**
     * @return
     */
    ResultSet findAll();

    ResultSet find(String nom);

}
