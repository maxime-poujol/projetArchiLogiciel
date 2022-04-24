package fr.iut.projetArchi;

import fr.iut.projetArchi.controller.CatalogueController;
import fr.iut.projetArchi.controller.Observable;
import fr.iut.projetArchi.controller.StockController;
import fr.iut.projetArchi.metier.catalogue.Catalogue;
import fr.iut.projetArchi.observateur.Observateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreAffichage extends JFrame implements ActionListener, Observateur {

    private final JButton btOK;
    private final JTextArea jtaSortie;

    public FenetreAffichage(String texte) {

        setTitle("Affichage");
        setBounds(500, 500, 450, 250);
        JPanel panHaut = new JPanel();
        JPanel panBas = new JPanel();
        panHaut.setLayout(new BorderLayout());
        panBas.setLayout(new FlowLayout());

        jtaSortie = new JTextArea(texte, 10, 5);
        btOK = new JButton("Quitter");


        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        panHaut.add(jtaSortie);
        panBas.add(btOK);

        contentPane.add(panHaut, "North");
        contentPane.add(panBas, "South");
        btOK.addActionListener(this);

        this.setVisible(true);
        Observable.attacher(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }

    @Override
    public void maj() {
        jtaSortie.setText(StockController.getEtatStock());
    }
}
