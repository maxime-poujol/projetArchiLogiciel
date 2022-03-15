package fr.iut.projetArchi.dao.catalogue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CatalogueDAORelationnel implements CatalogueDAO{

    @Override
    public void create(Map<String, Object> values) throws SQLException {

    }

    @Override
    public void update(Map<String, Object> values) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public ResultSet findAll() {
        return null;
    }

    @Override
    public ResultSet find(int id) throws SQLException{
        return null;
    }

    @Override
    public String nameTable() {
        return "Catalogue";
    }

    @Override
    public String idTable() {
        return "idCatalogue";
    }
}
