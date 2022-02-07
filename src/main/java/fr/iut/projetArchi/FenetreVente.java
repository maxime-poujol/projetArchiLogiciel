package fr.iut.projetArchi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreVente extends JFrame implements ActionListener {

    private final JButton btVente;
    private final JTextField txtQuantite;
    private final JComboBox<String> combo;

    public FenetreVente(String[] lesProduits) {
        setTitle("Vente");
        setBounds(500, 500, 200, 125);
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        btVente = new JButton("Vente");
        txtQuantite = new JTextField(5);
        txtQuantite.setText("0");

        combo = new JComboBox<String>(lesProduits);
        combo.setPreferredSize(new Dimension(100, 20));
        contentPane.add(new JLabel("Produit"));
        contentPane.add(combo);
        contentPane.add(new JLabel("Quantité vendue"));
        contentPane.add(txtQuantite);
        contentPane.add(btVente);

        btVente.addActionListener(this);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btVente){
            System.out.println("vente");
        }

        //this.dispose();
    }

}
