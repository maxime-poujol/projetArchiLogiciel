package fr.iut.projetArchi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {

    private final JButton btSupprimer;
    private final JComboBox<String> combo;

    public FenetreSuppressionProduit(String[] lesProduits) {

        setTitle("Suppression produit");
        setBounds(500, 500, 200, 105);
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        btSupprimer = new JButton("Supprimer");

        combo = new JComboBox<String>(lesProduits);
        combo.setPreferredSize(new Dimension(100, 20));
        contentPane.add(new JLabel("Produit"));
        contentPane.add(combo);
        contentPane.add(btSupprimer);

        btSupprimer.addActionListener(this);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }

}
