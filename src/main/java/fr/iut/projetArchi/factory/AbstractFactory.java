package fr.iut.projetArchi.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractFactory implements Factory{

    private static Factory instance;

    protected AbstractFactory(){}

    public static Factory getInstance() {
        if (instance == null){

            try (InputStream input = new FileInputStream("config.properties")) {
                Properties prop = new Properties();
                prop.load(input);

                // get the property value and print it out
                //System.out.println(prop.getProperty("db.url"));
                //System.out.println(prop.getProperty("db.user"));
                //System.out.println(prop.getProperty("db.password"));

                switch (prop.getProperty("db")){
                    case "relationnel" -> instance = new RelationnelFactory();
                    case "xml" -> instance = new XMLFactory();
                    default -> instance = null;
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        return instance;
    }


}
