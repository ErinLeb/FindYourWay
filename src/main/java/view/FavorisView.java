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
 * Classe qui gère l'affichage des éléments de gestion des salles et chemins favoris.
 */
public class FavorisView extends JPanel {
    // Attributs 

    /**
     * Bouton qui permet d'ajouter des favoris
     */
    private JButton ajouter;

    /**
     * Bouton qui permet de supprimer des favoris
     */
    private JButton supprimer;

    /**
     * liste d'affichage des favoris
     */
    private JComboBox<Binome> list;

    private Controleur controleur;


    // Constructeur 

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
        ajouter = new JButton("+");
        ajouter.addActionListener(new ActionListener() {
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
        add(ajouter);
        add(supprimer);
    }


    // Setters 

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }


    // Méthodes

    /**
     * Ajoute et sélectionne {@code favoris} dans la liste des favoris
     * 
     * @param favoris binome que l'on ajoute
     */
    public void addFavoris(Binome favoris) {
        list.addItem(favoris);
        list.setSelectedItem(favoris);
    }

    /**
     * Supprime l'élément à l'index {@code index} des favoris 
     * 
     * @param index index de l'élément que l'on retire des favoris
     */
    public void removeFavoris(int index) {
        if (index != 0) {
            list.removeItemAt(index);
        }
    }
}