package fr.iut.projetArchi;

import fr.iut.projetArchi.catalogue.Catalogue;
import fr.iut.projetArchi.controller.CatalogueController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreNouveauProduit extends JFrame implements ActionListener {

    private final JTextField txtPrixHT;
    private final JTextField txtNom;
    private final JTextField txtQte;
    //	private JComboBox<String> combo;
    private final JButton btValider;

    //	public fr.iut.projetArchi.FenetreNouveauProduit(String[] lesCategories) {
    public FenetreNouveauProduit() {

        setTitle("Creation Produit");
        setBounds(500, 500, 200, 250);
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        JLabel labNom = new JLabel("Nom produit");
        JLabel labPrixHT = new JLabel("Prix Hors Taxe");
        JLabel labQte = new JLabel("Quantité en stock");
//		JLabel labCategorie = new JLabel("Categorie");
        contentPane.add(labNom);
        txtNom = new JTextField(15);
        contentPane.add(txtNom);
        contentPane.add(labPrixHT);
        txtPrixHT = new JTextField(15);
        contentPane.add(txtPrixHT);
        contentPane.add(labQte);
        txtQte = new JTextField(15);
        contentPane.add(txtQte);

//		combo = new JComboBox<String>(lesCategories);
//		combo.setPreferredSize(new Dimension(100, 20));
//		contentPane.add(labCategorie);
//		contentPane.add(combo);


        btValider = new JButton("Valider");
        contentPane.add(btValider);

        btValider.addActionListener(this);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btValider){
            System.out.println("btn valider");

            try {
                String nom = txtNom.getText();
                double prix = Double.parseDouble(txtPrixHT.getText());
                int quantite = Integer.parseInt(txtQte.getText());
                CatalogueController.ajouterProduit(nom, prix, quantite);

            } catch (NumberFormatException exception) {
                exception.printStackTrace();
            }finally {
                System.out.println("Au revoir");
                this.dispose();
            }

        }

        //this.dispose();
    }

}