package fr.iut.projetArchi.factory;

import fr.iut.projetArchi.dao.produit.ProduitDAO;
import fr.iut.projetArchi.dao.produit.ProduitDAORelationnel;
import fr.iut.projetArchi.dao.produit.ProduitDAOXMLAdaptateur;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProduitFactory extends AbstractFactory{

    private static ProduitFactory intance;

    private ProduitFactory() {

    }

    public ProduitDAO createProduitDAO() {

        try (InputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            System.out.println(prop.getProperty("db"));

            return switch (prop.getProperty("db")) {
                case "relationnel" -> new ProduitDAORelationnel();
                case "xml" -> new ProduitDAOXMLAdaptateur();
                default -> null;
            };

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public static ProduitFactory getIntance() {
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
