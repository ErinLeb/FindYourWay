package model;

import java.util.HashMap;
import java.util.Map;


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
     * Numéro d'étage du noeud
     */
    protected int etage;

    protected final Batiment batiment;

    /**
     * dictionnaire des paires voisin/distance du Noeud courant
     */
    protected HashMap<Noeud, Double> voisins;


    // Getters

    /**
     * renvoie l'identifiant du noeud
     * 
     * @return renvoie l'id du noeud
     */
    public int getId() {
        return id;
    }

    /**
     * renvoie le dictionnaire des voisins du noeud avec leur distance
     * 
     * @return renvoie le dictionnaire des voisins du noeud avec leur distance
     */
    public Map<Noeud, Double> getVoisins() {
        return voisins;
    }

    protected Noeud(int etage, Batiment batiment){
        this.id = nbNoeud;
        nbNoeud++;

        this.etage = etage;
        this.batiment = batiment;

        voisins = new HashMap<>();
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

    public void setEtage(int etage){
        if(etage >= batiment.min && etage <= batiment.max){
            this.etage = etage; 
        }else{
            throw new IllegalArgumentException("L'étage doit être compris entre 0 et 5");
        }    
    }
}