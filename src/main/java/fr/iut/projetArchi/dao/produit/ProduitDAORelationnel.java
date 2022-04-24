package fr.iut.projetArchi.dao.produit;

import fr.iut.projetArchi.metier.produits.I_Produit;
import fr.iut.projetArchi.metier.produits.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAORelationnel implements ProduitDAO {

    private final Connection connection;

    public ProduitDAORelationnel(Connection connection) {
        this.connection = connection;
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            cn = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut", "naberte", "040010611EG");
//
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    private PreparedStatement requetePrepare(String sql) throws SQLException {
        return connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    @Override
    public void create(I_Produit produit) {
        PreparedStatement ps;
        try {
            ps = connection.prepareCall("call insert_produit(?,?,?,?)");
            System.out.println(produit.getNomCatalogue());
            ps.setString(1, produit.getNom());
            ps.setInt(2, produit.getQuantite());
            ps.setDouble(3, produit.getPrixUnitaireHT());
            ps.setString(4, produit.getNomCatalogue());
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(I_Produit produit) {
        String sql = "UPDATE Produits SET nom = ?, prixUnitaireHT = ?, qteStock = ? WHERE nom = ?";
        PreparedStatement ps;
        try {
            ps = requetePrepare(sql);
            ps.setString(1, produit.getNom());
            ps.setDouble(2, produit.getPrixUnitaireHT());
            ps.setInt(3, produit.getQuantite());
            ps.setString(4, produit.getNom());
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(I_Produit produit) {
        PreparedStatement ps;
        try {
            ps = requetePrepare("DELETE FROM Produits WHERE nom = ? AND nomCatalogue = ?");
            ps.setString(1, produit.getNom());
            ps.setString(2, produit.getNomCatalogue());
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<I_Produit> findAll() {
        List<I_Produit> produits = new ArrayList<>();
        try {
            PreparedStatement ps = requetePrepare("SELECT * FROM Produits");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                I_Produit produit = new Produit(
                        rs.getString("nom"),
                        rs.getInt("prixunitaireht"),
                        rs.getInt("qtestock"),
                        rs.getString("nomcatalogue"));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    @Override
    public I_Produit find(String nom) {
        return null;
    }

    @Override
    public List<I_Produit> findByCatalogue(String catalogueName) {

        List<I_Produit> produits = new ArrayList<>();
        try {
            PreparedStatement ps = requetePrepare("SELECT p.nom,prixunitaireht,qtestock,nomCatalogue FROM Produits p JOIN Catalogues c ON c.nom = p.nomCatalogue WHERE c.nom = ?");
            ps.setString(1,catalogueName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                I_Produit produit = new Produit(
                        rs.getString("nom"),
                        rs.getInt("prixunitaireht"),
                        rs.getInt("qtestock"),
                        rs.getString("nomcatalogue"));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produits;
    }
}
