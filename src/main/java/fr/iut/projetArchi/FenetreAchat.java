package fr.iut.projetArchi;

import fr.iut.projetArchi.controller.ProduitController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreAchat extends JFrame implements ActionListener {

    private final JButton btAchat;
    private final JTextField txtQuantite;
    private final JComboBox<String> combo;

    public FenetreAchat(String[] lesProduits) {

        setTitle("Achat");
        setBounds(500, 500, 200, 125);
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        btAchat = new JButton("Achat");
        txtQuantite = new JTextField(5);
        txtQuantite.setText("0");

        combo = new JComboBox<String>(lesProduits);
        combo.setPreferredSize(new Dimension(100, 20));
        contentPane.add(new JLabel("Produit"));
        contentPane.add(combo);
        contentPane.add(new JLabel("Quantité achetée"));
        contentPane.add(txtQuantite);
        contentPane.add(btAchat);

        btAchat.addActionListener(this);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAchat){
            ProduitController.acheterProduit(combo.getSelectedItem().toString(), Integer.parseInt(txtQuantite.getText()));
        }

        this.dispose();
    }

}
