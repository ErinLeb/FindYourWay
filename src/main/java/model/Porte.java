package model;

import java.util.HashMap;

public class Porte extends Noeud{
    //Attributs 

    /**
     * Salle à laquelle la porte mène
     */
    public final Salle salle;

    //Constructeurs

    /**
     * Crée une porte sans voisin qui mène à une salle
     * @param salle salle à laquelle la porte mène
     */
    Porte(Salle salle){
        super.id = super.nbNoeud;
        super.nbNoeud++;
        this.salle = salle;
        salle.addPorte(this);
        super.voisins = new HashMap<Noeud, Double>();
    }

    //Méthodes

}