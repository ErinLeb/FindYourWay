package model;

import java.util.HashMap;

public abstract class Noeud {
    // Attributs 

    /**
     * nombre de Noeuds qui ont été créés
     */
    protected static int nbNoeud;

    /**
     * numéro d'identification des noeuds qui permettra de savoir par quels noeuds
     * on est déjà passé
     */
    protected int id;

    /**
     * dictionnaire des paires voisin,distance du Noeud courant
     */
    protected HashMap<Noeud, Double> voisins;

    // Getters

    /**
     * renvoie le dictionnaire des voisins du noeud avec leur distance
     * 
     * @return renvoie le dictionnaire des voisins du noeud avec leur distance
     */
    public HashMap<Noeud, Double> getVoisins() {
        return voisins;
    }

    /**
     * renvoie l'identifiant du noeud
     * 
     * @return renvoie l'id du noeud
     */
    public int getId() {
        return id;
    }

    // Méthodes

    /**
     * ajoute un voisin au noeud
     * 
     * @param voisin   voisin à ajouter
     * @param distance distance entre les deux noeuds
     */
    public void addVoisin(Noeud voisin, Double distance) {
        voisins.put(voisin, distance);
        voisin.voisins.put(this, distance);
    }
}