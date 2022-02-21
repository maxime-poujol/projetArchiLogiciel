package fr.iut.projetArchi.factory;

public abstract class AbstractFactory implements Factory{


    private static Factory instance;

    protected AbstractFactory(){}


    public static Factory getInstance() {
        if (instance == null){
            instance = new RelationnelFactory();
        }
        return instance;
    }



}
