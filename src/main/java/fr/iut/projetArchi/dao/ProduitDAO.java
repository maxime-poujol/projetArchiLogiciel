package fr.iut.projetArchi.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProduitDAO extends DAO{


    @Override
    protected String nameTable() {
        return "Produits";
    }

    @Override
    protected String idTable() {
        return "idProduit";
    }

    @Override
    protected void startFixtures() {

    }

    private static void printInfos(ProduitDAO dao, ResultSet rs) throws SQLException {
        System.out.println("ID: " + rs.getInt(rs.findColumn(dao.idTable())));
        System.out.println("nom: " + rs.getString(rs.findColumn("nom")));
        System.out.println("prixHT: " + rs.getInt(rs.findColumn("prixUnitaireHT")));
        System.out.println("qte: " + rs.getInt(rs.findColumn("qteStock")));
    }

    public static void main(String[] args) {
        ProduitDAO dao = new ProduitDAO();
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
