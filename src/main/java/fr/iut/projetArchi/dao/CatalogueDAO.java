package fr.iut.projetArchi.dao;

public class CatalogueDAO extends DAO{

    @Override
    protected String nameTable() {
        return "Catalogue";
    }

    @Override
    protected String idTable() {
        return "idCatalogue";
    }

    @Override
    protected void startFixtures() {

    }
}
