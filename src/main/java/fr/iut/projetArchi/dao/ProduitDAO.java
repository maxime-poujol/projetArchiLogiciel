package fr.iut.projetArchi.dao;


public class ProduitDAO extends DAO{


    @Override
    protected String nameTable() {
        return "Produit";
    }

    @Override
    protected String idTable() {
        return "idProduit";
    }

    @Override
    protected void startFixtures() {

    }


}
