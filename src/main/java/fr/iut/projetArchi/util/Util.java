package fr.iut.projetArchi.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class Util {

    private static String stringDeuxChiffreApresVirgule(double valeur){
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        return format.format(valeur);
    }

    public static String frStringDeuxChiffreApresVirgule(double valeur){
        return stringDeuxChiffreApresVirgule(valeur).replace('.', ',');
    }

    public static double doubleDeuxChiffreApresVirgule(double valeur){
        return Double.parseDouble(stringDeuxChiffreApresVirgule(valeur));
    }


    public static String formatNom(String nom) {

        nom = nom.replace('\t',' ');

        while (nom.charAt(nom.length() - 1) == ' '){
            nom = nom.substring(0, nom.length() - 1);
        }

        while (nom.charAt(0) == ' '){
            nom = nom.substring(1);
        }

        return nom;
    }

}
