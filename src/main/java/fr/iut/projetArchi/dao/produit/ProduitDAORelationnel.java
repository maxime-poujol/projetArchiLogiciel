package fr.iut.projetArchi.dao.produit;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ProduitDAORelationnel implements ProduitDAO{

    private ResultSet rs;
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
    public void create(Map<String, Object> values) throws SQLException {
        PreparedStatement ps = cn.prepareCall("call insert_produit(?,?,?)");
        ps.setString(1,(String) values.get("nom"));
        ps.setInt(1,(Integer) values.get("prixUnitaireHT"));
        ps.setInt(1,(Integer) values.get("qteStock"));
        ps.executeQuery();
    }

    @Override
    public void update(Map<String, Object> values) throws SQLException {
        String sql = "UPDATE Produit SET nom = ?, prixUnitaireHT = ?, qteStock = ? WHERE idProduit = ?";
        PreparedStatement ps = requetePrepare(sql);
        ps.setString(1,(String) values.get("nom"));
        ps.setInt(2,(Integer) values.get("prixUnitaireHT"));
        ps.setInt(1,(Integer) values.get("qteStock"));
        ps.setInt(1,(Integer) values.get("idProduit"));
        ps.executeQuery();

    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement ps = requetePrepare("DELETE FROM Produit WHERE idProduit = ?");
        ps.setInt(1,id);
        ps.executeQuery();
    }

    @Override
    public ResultSet findAll() throws SQLException{
        PreparedStatement ps = requetePrepare("SELECT * FROM Produit");
        return ps.executeQuery();
    }

    @Override
    public ResultSet find(int id) throws SQLException{
        PreparedStatement ps = requetePrepare("SELECT * FROM Produit WHERE id = ?");
        ps.setInt(1,id);
        return ps.executeQuery();
    }

    @Override
    public String nameTable() {
        return "Produits";
    }

    @Override
    public String idTable() {
        return "idProduit";
    }

    private static void printInfos(ProduitDAORelationnel dao, ResultSet rs) throws SQLException {
        System.out.println("ID: " + rs.getInt(rs.findColumn(dao.idTable())));
        System.out.println("nom: " + rs.getString(rs.findColumn("nom")));
        System.out.println("prixHT: " + rs.getInt(rs.findColumn("prixUnitaireHT")));
        System.out.println("qte: " + rs.getInt(rs.findColumn("qteStock")));
    }

    public static void main(String[] args) {
        ProduitDAORelationnel dao = new ProduitDAORelationnel();
        Map<String,Object> map = new HashMap<>();

        map.put("nom","twix");
        map.put("prixUnitaireHT",25);
        map.put("qteStock",2);

        try {
//            dao.create(map);  // cannot test before insert mode decision
//            dao.update(map, 0); // OK
            dao.delete(1); // OK
//            ResultSet rs = dao.findAll(); // OK
//            while (rs.next()) {
//                printInfos(dao, rs);
//                System.out.println();
//            }

//            ResultSet rs = dao.find(1); //OK
//            rs.next();
//            printInfos(dao, rs);



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
