package fr.iut.projetArchi.controller;

import fr.iut.projetArchi.FenetreAccueil;
import fr.iut.projetArchi.FenetreNouveauProduit;
import fr.iut.projetArchi.FenetrePrincipale;
import fr.iut.projetArchi.FenetreSuppressionProduit;
import fr.iut.projetArchi.dao.catalogue.CatalogueDAO;
import fr.iut.projetArchi.factory.AbstractFactory;
import fr.iut.projetArchi.metier.catalogue.Catalogue;
import fr.iut.projetArchi.metier.catalogue.I_Catalogue;
import fr.iut.projetArchi.observateur.Observateur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CatalogueController extends Observable{

    private static List<I_Catalogue> lesCatalogues;
    private static I_Catalogue currentCatalogue;
    private static CatalogueDAO catalogueDAO;

    private static boolean firstSelected = false;

    public static void initController() {
        lesCatalogues = new ArrayList<>();
        catalogueDAO = AbstractFactory.getInstance().createCatalogueDAO();

    }

    public static void openWindowAjoutProduit() {
        new FenetreNouveauProduit();
    }

    public static void openWindowSupprimerProduit() {
        new FenetreSuppressionProduit(currentCatalogue.getNomProduits());
    }

    public static void openMainWindow() {
        if (!firstSelected) {
            new FenetrePrincipale();
            firstSelected = true;
        }

    }

    public static boolean ajouterProduit(String nom, double prixHT, int quantite) {
        boolean added = currentCatalogue.addProduit(nom, prixHT, quantite);
        avertir();
        return added;
    }

    public static boolean supprimerProduit(String nom) {
        boolean removed = currentCatalogue.removeProduit(nom);
        avertir();
        return removed;
    }

    public static void recupererCataloguesEnBD() {
        List<I_Catalogue> catalogues = catalogueDAO.findAll();
        if (!catalogues.isEmpty()) {
            lesCatalogues.addAll(catalogues);
            currentCatalogue = lesCatalogues.get(0);
        }

    }

    public static String[] getNomsCatalogues() {
        if (!lesCatalogues.isEmpty()) {
            String[] noms = new String[lesCatalogues.size()];

            int index = 0;
            for (I_Catalogue catalogue : lesCatalogues) {
                noms[index] = catalogue.getNom();
                index++;
            }
            Arrays.sort(noms);
            return noms;
        }

        return new String[1];
    }

    public static void selectionnerCatalogue(String name) {
        System.out.println(name);
        openMainWindow();
        currentCatalogue = getCatalogueByName(name);
        currentCatalogue.updateListeProduits();
        avertir();
    }

    public static I_Catalogue getCurrentCatalogue() {
        return currentCatalogue;
    }

    public static String[] getEtatCatalogues() {
        if (!lesCatalogues.isEmpty()) {
            String[] etat = new String[lesCatalogues.size()];

            int index = 0;
            for (I_Catalogue catalogue : lesCatalogues) {
                etat[index] = catalogue.getNom() + " : " + catalogueDAO.getNbProduits(catalogue) + " produits";
                index++;
            }
            return etat;
        }

        return new String[]{""};
    }

    public static int getNbCatalogues() {
        return lesCatalogues.size();
    }

    public static void ajouterCatalogue(String name) {
        if (getCatalogueByName(name) == null) {
            I_Catalogue catalogue = new Catalogue(name);
            lesCatalogues.add(catalogue);
            catalogueDAO.create(catalogue);
            avertir();
        }
    }

    public static void supprimerCatalogue(String name) {
        I_Catalogue catalogue = getCatalogueByName(name);
        if (catalogue != null) {
            lesCatalogues.remove(catalogue);
            catalogueDAO.delete(catalogue);
            avertir();
        }

    }

    private static I_Catalogue getCatalogueByName(String name) {
        return lesCatalogues.stream()
                .filter(catalogue -> catalogue.getNom().equals(name))
                .findFirst()
                .orElse(null);
    }
}
