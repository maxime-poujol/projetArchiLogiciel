package fr.iut.projetArchi.dao.produit;

import fr.iut.projetArchi.metier.produits.I_Produit;
import fr.iut.projetArchi.metier.produits.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAORelationnel implements ProduitDAO {

    private Connection cn;

    public ProduitDAORelationnel() {
//            cn = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut", "poujolm", "071563154FB");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut", "naberte", "040010611EG");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private PreparedStatement requetePrepare(String sql) throws SQLException {
        return cn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    @Override
    public void create(I_Produit produit) {
        PreparedStatement ps;
        try {
            ps = cn.prepareCall("call insert_produit(?,?,?)");
            ps.setString(1, produit.getNom());
            ps.setInt(2, produit.getQuantite());
            ps.setDouble(3, produit.getPrixUnitaireHT());
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
            ps = requetePrepare("DELETE FROM Produits WHERE nom = ?");
            ps.setString(1, produit.getNom());
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
                        rs.getInt("qtestock"));
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
}
