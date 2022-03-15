package fr.iut.projetArchi.factory;

import fr.iut.projetArchi.dao.produit.ProduitDAO;
import fr.iut.projetArchi.dao.produit.ProduitDAORelationnel;
import fr.iut.projetArchi.dao.produit.ProduitDAOXML;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProduitFactory {

    private ProduitFactory intance;

    private ProduitFactory() {

    }

    public ProduitDAO createProduitDAO() {

        try (InputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            System.out.println(prop.getProperty("db"));

            return switch (prop.getProperty("db")) {
                case "relationnel" -> new ProduitDAORelationnel();
                case "xml" -> new ProduitDAOXML();
                default -> null;
            };

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public ProduitFactory getIntance() {
        if (intance == null){
            return new ProduitFactory();
        }
        return intance;
    }

    public static void main(String[] args) {

        ProduitFactory produitFactory = new ProduitFactory();
        produitFactory.createProduitDAO();

    }

}
