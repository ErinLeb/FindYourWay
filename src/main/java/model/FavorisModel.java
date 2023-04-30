package model;

import java.util.ArrayList;

/**
 * Classe qui modélise les éléments noeuds et chemins favoris.
 */
public class FavorisModel {
    // Attributs 

    /**
     * Liste des éléments favoris
     */
    private ArrayList<Binome> favoris;

    
    // Constructeur

    public FavorisModel() {
        favoris = new ArrayList<Binome>();
    }


    // Getters

    /**
     * Retourne la liste des favoris
     * 
     * @return la liste des binomes favoris.
     */
    public ArrayList<Binome> getFavoris() {
        return favoris;
    }

    
    // Méthodes

    /**
     * Ajoute {@code b} aux favoris
     * 
     * @param b favori à ajouter
     * @return  true si l'élément a bien été ajouté, false sinon
     */
    public boolean addFavoris(Binome b) {
        if (!contains(b)) {
            return favoris.add(b);
        }
        return false;
    }

    /**
     * Enlève {@code b} des favoris
     * 
     * @param b élément à enlever
     * @return  true si l'élément a été enlevé, false sinon
     */
    public boolean removeFavoris(Binome b) {
        return favoris.remove(b);
    }

    /**
     * Vérifie si la liste des favoris contient {@code b}
     * 
     * @param b élément dont on veut vérifier sa présence
     * @return  true si la liste de favoris contient l'élément b, false sinon
     */
    public boolean contains(Binome b) {
        for (Binome e : favoris) {
            if (e.equals(b)) {
                return true;
            }
        }
        return false;
    }
}