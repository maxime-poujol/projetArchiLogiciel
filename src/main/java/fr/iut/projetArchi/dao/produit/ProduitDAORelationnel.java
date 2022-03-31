package fr.iut.projetArchi.dao.produit;

import fr.iut.projetArchi.metier.produits.I_Produit;

import java.sql.*;

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
            ps.setDouble(2, produit.getPrixUnitaireHT());
            ps.setInt(3, produit.getQuantite());
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
            ps.setString(1, nom);
            ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet findAll() {
        PreparedStatement ps;
        ResultSet rs = null;
        try {
            ps = requetePrepare("SELECT * FROM Produits");
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public ResultSet find(String nom) {
        return null;
    }
}
