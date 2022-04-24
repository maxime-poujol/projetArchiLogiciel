package fr.iut.projetArchi;

import fr.iut.projetArchi.controller.CatalogueController;
import fr.iut.projetArchi.controller.Observable;
import fr.iut.projetArchi.metier.catalogue.I_Catalogue;
import fr.iut.projetArchi.observateur.Observateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreAccueil extends JFrame implements ActionListener, Observateur {

    private final JButton btAjouter;
    private final JButton btSupprimer;
    private final JButton btSelectionner;
    private final JTextField txtAjouter;
    private final JLabel lbNbCatalogues;
    private final JComboBox cmbSupprimer;
    private final JComboBox cmbSelectionner;
    private final TextArea taDetailCatalogues;

    public FenetreAccueil() {
        setTitle("Catalogues");
        setBounds(500, 500, 200, 125);
        Container contentPane = getContentPane();
        JPanel panInfosCatalogues = new JPanel();
        JPanel panNbCatalogues = new JPanel();
        JPanel panDetailCatalogues = new JPanel();
        JPanel panGestionCatalogues = new JPanel();
        JPanel panAjouter = new JPanel();
        JPanel panSupprimer = new JPanel();
        JPanel panSelectionner = new JPanel();
        panNbCatalogues.setBackground(Color.white);
        panDetailCatalogues.setBackground(Color.white);
        panAjouter.setBackground(Color.gray);
        panSupprimer.setBackground(Color.lightGray);
        panSelectionner.setBackground(Color.gray);

        panNbCatalogues.add(new JLabel("Nous avons actuellement : "));
        lbNbCatalogues = new JLabel();
        panNbCatalogues.add(lbNbCatalogues);

        taDetailCatalogues = new TextArea();
        taDetailCatalogues.setEditable(false);
        new JScrollPane(taDetailCatalogues);
        taDetailCatalogues.setPreferredSize(new Dimension(300, 100));
        panDetailCatalogues.add(taDetailCatalogues);

        panAjouter.add(new JLabel("Ajouter un catalogue : "));
        txtAjouter = new JTextField(10);
        panAjouter.add(txtAjouter);
        btAjouter = new JButton("Ajouter");
        panAjouter.add(btAjouter);

        panSupprimer.add(new JLabel("Supprimer un catalogue : "));
        cmbSupprimer = new JComboBox();
        cmbSupprimer.setPreferredSize(new Dimension(100, 20));
        panSupprimer.add(cmbSupprimer);
        btSupprimer = new JButton("Supprimer");
        panSupprimer.add(btSupprimer);

        panSelectionner.add(new JLabel("Selectionner un catalogue : "));
        cmbSelectionner = new JComboBox();
        cmbSelectionner.setPreferredSize(new Dimension(100, 20));
        panSelectionner.add(cmbSelectionner);
        btSelectionner = new JButton("Selectionner");
        panSelectionner.add(btSelectionner);

        panGestionCatalogues.setLayout(new BorderLayout());
        panGestionCatalogues.add(panAjouter, "North");
        panGestionCatalogues.add(panSupprimer);
        panGestionCatalogues.add(panSelectionner, "South");

        panInfosCatalogues.setLayout(new BorderLayout());
        panInfosCatalogues.add(panNbCatalogues, "North");
        panInfosCatalogues.add(panDetailCatalogues, "South");

        contentPane.add(panInfosCatalogues, "North");
        contentPane.add(panGestionCatalogues, "South");
        pack();

        btAjouter.addActionListener(this);
        btSupprimer.addActionListener(this);
        btSelectionner.addActionListener(this);

        CatalogueController.initController();
        Observable.attacher(this);
        CatalogueController.recupererCataloguesEnBD();
        modifierDetailCatalogues(CatalogueController.getEtatCatalogues());
        modifierListesCatalogues(CatalogueController.getNomsCatalogues());
        modifierNbCatalogues(CatalogueController.getNbCatalogues());
        setVisible(true);
    }

    public static void main(String[] args) {
        new FenetreAccueil();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAjouter) {
            String texteAjout = txtAjouter.getText();
            if (!texteAjout.equals("")) {
                CatalogueController.ajouterCatalogue(texteAjout);
                txtAjouter.setText(null);
            }
        }
        if (e.getSource() == btSupprimer) {
            String texteSupprime = (String) cmbSupprimer.getSelectedItem();
            if (texteSupprime != null) CatalogueController.supprimerCatalogue(texteSupprime);
        }
        if (e.getSource() == btSelectionner) {
            String texteSelection = (String) cmbSelectionner.getSelectedItem();
            if (texteSelection != null) {
                CatalogueController.selectionnerCatalogue(texteSelection);
            }
        }
    }

    private void modifierListesCatalogues(String[] nomsCatalogues) {
        cmbSupprimer.removeAllItems();
        cmbSelectionner.removeAllItems();
        if (nomsCatalogues != null) {
            for (String nomsCatalogue : nomsCatalogues) {
                cmbSupprimer.addItem(nomsCatalogue);
                cmbSelectionner.addItem(nomsCatalogue);
            }
        }
    }

    private void modifierNbCatalogues(int nb) {
        lbNbCatalogues.setText(nb + " catalogues");
    }

    private void modifierDetailCatalogues(String[] detailCatalogues) {
        taDetailCatalogues.setText("");
        if (detailCatalogues != null) {
            for (String detailCatalogue : detailCatalogues) taDetailCatalogues.append(detailCatalogue + "\n");
        }
    }

    @Override
    public void maj() {
        modifierDetailCatalogues(CatalogueController.getEtatCatalogues());
        modifierListesCatalogues(CatalogueController.getNomsCatalogues());
        modifierNbCatalogues(CatalogueController.getNbCatalogues());
    }
}
