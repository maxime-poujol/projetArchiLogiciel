package fr.iut.projetArchi.controller;

import fr.iut.projetArchi.observateur.Observateur;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    protected static List<Observateur> observateurs = new ArrayList<>();

    public static void attacher(Observateur observateur) {
        observateurs.add(observateur);
    }

    public static void avertir() {
        for (Observateur o : observateurs) {
            o.maj();
        }
    }
}
