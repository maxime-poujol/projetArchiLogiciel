package fr.iut.projetArchi.dao.catalogue;

import fr.iut.projetArchi.metier.catalogue.I_Catalogue;

import java.sql.Connection;
import java.util.List;

public class CatalogueDAORelationnel implements CatalogueDAO {

    private final Connection connection;

    public CatalogueDAORelationnel(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(I_Catalogue catalogue) {

    }

    @Override
    public void update(I_Catalogue catalogue) {

    }

    @Override
    public void delete(I_Catalogue catalogue) {

    }

    @Override
    public List<I_Catalogue> findAll() {
        return null;
    }

    @Override
    public I_Catalogue find(String nom) {
        return null;
    }
}
