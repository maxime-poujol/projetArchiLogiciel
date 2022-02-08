package fr.iut.projetArchi.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Util {


    public static double doubleDeuxChiffreApresVirgule(double valeur){
        BigDecimal format = new BigDecimal(Double.toString(valeur))
                .setScale(2, RoundingMode.HALF_UP);
        return format.doubleValue();
    }

    public static String formatDoubleNumber(double valeur) {
        String stringDouble = Double.toString(valeur);
        String[] parsed = stringDouble.split("\\.");
        switch (parsed[1].length()) {
            case 0 -> parsed[1] += "00";
            case 1 -> parsed[1] += "0";
        }
        return String.join(",",parsed);
    }

    public static String formatNom(String nom) {
        int first = 0;
        int last = nom.length();

        boolean found = false;
        for (int i = 0; i < nom.length() && !found; i++) {
            if (nom.charAt(i) != ' ' && nom.charAt(i) != '\t') {
                first = i;
                found = true;
            }
        }

        found = false;
        for (int i = nom.length() - 1; i >= 0 && !found; i--) {
            if (nom.charAt(i) != ' ' && nom.charAt(i) != '\t') {
                last = i;
                found = true;
            }
        }

        return nom.substring(first,last + 1).replace('\t',' ');
    }

}
