package fr.iut.projetArchi.dao.produit;

import fr.iut.projetArchi.dao.produit.ProduitDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ProduitDAOXML implements ProduitDAO {

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
    public ResultSet findAll() throws SQLException {
        return null;
    }

    @Override
    public ResultSet find(int id) throws SQLException {
        return null;
    }

    @Override
    public String nameTable() {
        return null;
    }

    @Override
    public String idTable() {
        return null;
    }
}
