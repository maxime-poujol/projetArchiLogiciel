package fr.iut.projetArchi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreAffichage extends JFrame implements ActionListener {

    private final JButton btOK;

    public FenetreAffichage(String texte) {

        setTitle("Affichage");
        setBounds(500, 500, 450, 250);
        JPanel panHaut = new JPanel();
        JPanel panBas = new JPanel();
        panHaut.setLayout(new BorderLayout());
        panBas.setLayout(new FlowLayout());

        JTextArea jtaSortie = new JTextArea(texte, 10, 5);
        btOK = new JButton("Quitter");


        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        panHaut.add(jtaSortie);
        panBas.add(btOK);

        contentPane.add(panHaut, "North");
        contentPane.add(panBas, "South");
        btOK.addActionListener(this);

        this.setVisible(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }

}
