package model;

import java.util.HashMap;
import java.util.Map;

public abstract class Noeud {
    // Attributs

    /**
     * Nombre de Noeuds qui ont été créés
     */
    protected static int nbNoeud;

    /**
     * Numéro d'identification des noeuds qui permettra de savoir par quels noeuds
     * on est déjà passé
     */
    protected int id;

    /**
     * Batiment auquel le noeud appartient
     */
    protected final Batiment batiment;

    /**
     * Dictionnaire des paires voisin/distance du noeud
     */
    protected HashMap<Noeud, Double> voisins;


    // Constructeur

    protected Noeud(Batiment batiment){
        this.id = nbNoeud;
        nbNoeud++;

        this.batiment = batiment;

        voisins = new HashMap<>();
    }


    // Getters

    /**
     * Renvoie l'identifiant du noeud
     * 
     * @return renvoie l'identifiant du noeud
     */
    public int getId() {
        return id;
    }

    /**
     * Renvoie le dictionnaire des voisins du noeud avec leur distance
     * 
     * @return renvoie le dictionnaire des voisins du noeud avec leur distance
     */
    public Map<Noeud, Double> getVoisins() {
        return voisins;
    }

    
    // Méthodes

    /**
     * Ajoute un voisin au noeud
     * 
     * @param voisin   voisin à ajouter
     * @param distance distance entre les deux noeuds
     */
    public void addVoisin(Noeud voisin, Double distance) {
        voisins.put(voisin, distance);
    }

}