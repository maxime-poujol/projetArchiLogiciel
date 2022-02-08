package fr.iut.projetArchi.util;

import java.text.NumberFormat;

public class Util {


    public static double doubleDeuxChiffreApresVirgule(double valeur){
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(2);
        return Double.parseDouble(format.format(valeur));
    }

}
