package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Controleur;
import model.Binome;

/**
 * Class qui s'occupe d'afficher les éléments de gestion des chemins et ou
 * salles favoris.
 */
public class FavorisView extends JPanel {
    /**
     * Bouton qui permet d'ajouter des favoris
     */
    private JButton ajout;
    /**
     * Bouton qui permet de supprimer des favoris
     */
    private JButton supprimer;
    /**
     * liste d'affichage des favoris
     */
    private JComboBox<Binome> list;
    private Controleur controleur;

    /**
     * constructeur
     */
    public FavorisView() {
        super();
        setBackground(Color.LIGHT_GRAY);
        list = new JComboBox<Binome>(new Binome[0]);
        list.addItem(null);
        list.setSelectedItem(null);
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.selectionFavoris((Binome) list.getSelectedItem());
            }
        });
        ajout = new JButton("+");
        ajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.ajouterElementListFavori();
            }
        });
        supprimer = new JButton("-");
        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if (index >= 0) {
                    controleur.supprimerElementListFavori(index, (Binome) list.getSelectedItem());
                }
            }
        });
        add(list);
        add(ajout);
        add(supprimer);
    }

    public void setcontroleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void addFavoris(Binome favoris) {
        list.addItem(favoris);
        list.setSelectedItem(favoris);
    }

    public void removeFavoris(int index) {
        if (index != 0) {
            list.removeItemAt(index);
        }
    }
}
