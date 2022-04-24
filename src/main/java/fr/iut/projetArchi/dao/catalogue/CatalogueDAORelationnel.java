package fr.iut.projetArchi.dao.catalogue;

import fr.iut.projetArchi.metier.catalogue.Catalogue;
import fr.iut.projetArchi.metier.catalogue.I_Catalogue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogueDAORelationnel implements CatalogueDAO {

    private final Connection connection;

    public CatalogueDAORelationnel(Connection connection) {
        this.connection = connection;
    }

    private PreparedStatement requetePrepare(String sql) throws SQLException {
        return connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }
    @Override
    public void create(I_Catalogue catalogue) {
        PreparedStatement ps;

        System.out.println("CatalogueDAO relationnel");
        try {
            ps = connection.prepareCall("call insert_catalogue(?)");
            ps.setString(1, catalogue.getNom());
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(I_Catalogue catalogue) {
        String sql = "UPDATE catalogues SET nom = ? WHERE nom = ?";
        PreparedStatement ps;
        try {
            ps = requetePrepare(sql);
            ps.setString(1, catalogue.getNom());
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(I_Catalogue catalogue) {
        PreparedStatement ps;
        try {
            ps = requetePrepare("DELETE FROM catalogues WHERE nom = ?");
            ps.setString(1, catalogue.getNom());
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<I_Catalogue> findAll() {
        List<I_Catalogue> catalogues = new ArrayList<>();
        try {
            PreparedStatement ps = requetePrepare("SELECT * FROM Catalogues");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                I_Catalogue catalogue = new Catalogue(
                        rs.getString("nom")
                );
                catalogues.add(catalogue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catalogues;
    }

    @Override
    public I_Catalogue find(String nom) {
        return null;
    }

    @Override
    public int getNbProduits(I_Catalogue catalogue) {
        try {
            PreparedStatement ps = requetePrepare("SELECT COUNT(*) FROM produits p JOIN catalogues c ON c.nom = p.nomCatalogue WHERE c.nom = ?");
            ps.setString(1,catalogue.getNom());
            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
