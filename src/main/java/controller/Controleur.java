package controller;

import javax.swing.JFrame;

import model.Batiment;
import view.Vue;

/**
 * Gère les interactions entre la vue et le modèle
 */
public class Controleur {

    private JFrame vue;

    /**
     * le batiment sur lequel la vue est centrée
     */
    private Batiment batActuel;

    /**
     * construit le controleur de l'app
     * @param bat le bâtiment de départ
     */
    public Controleur(Batiment bat) {
        this.batActuel = bat;
        this.vue = new Vue(this);
    }

    /**
     * renvoie l'étage max du bâtiment sur lequel la vue est centrée
     * @return l'étage max du bâtiment sur lequel la vue est centrée
     */
    public int getEtageMaxActuel() {
        return batActuel.getMax();
    }

    /**
     * renvoie l'étage min du bâtiment sur lequel la vue est centrée
     * @return l'étage min du bâtiment sur lequel la vue est centrée
     */
    public int getEtageMinActuel() {
        return batActuel.getMin();
    }
    
}
