package fr.iut.projetArchi.factory;

import fr.iut.projetArchi.dao.catalogue.CatalogueDAO;
import fr.iut.projetArchi.dao.catalogue.CatalogueDAORelationnel;
import fr.iut.projetArchi.dao.produit.ProduitDAO;
import fr.iut.projetArchi.dao.produit.ProduitDAORelationnel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RelationnelFactory extends AbstractFactory {

    private Connection connection;

    public RelationnelFactory() {
//        cn = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut", "poujolm", "071563154FB");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut", "naberte", "040010611EG");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProduitDAO createProduitDAO() {
        return new ProduitDAORelationnel(connection);
    }

    @Override
    public CatalogueDAO createCatalogueDAO() {
        return new CatalogueDAORelationnel(connection);
    }
}
