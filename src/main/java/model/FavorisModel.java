package model;

import java.util.ArrayList;

/**
 * Class qui modélise les éléments noeuds et chemins favoris.
 */
public class FavorisModel {

    /**
     * Liste des element favoris
     */
    private ArrayList<Binome> favoris;

    /**
     * Constructeur
     */
    public FavorisModel() {
        favoris = new ArrayList<Binome>();
    }

    /**
     * methode qui ajout un favoris
     * 
     * @param b favoris à ajouter
     * @return si il a reussi à ajouter l'élément.
     */
    public boolean addFavoris(Binome b) {
        if (!contains(b)) {
            return favoris.add(b);
        }
        return false;
    }

    /**
     * methode qui enlève un element
     * 
     * @param b élément à enlever
     * @return si l'élément à été enlevé.
     */
    public boolean removeFavoris(Binome b) {
        return favoris.remove(b);
    }

    /**
     * Vérifie si la liste contient déjà un binome
     * 
     * @param b binome à vérifier
     * @return si la liste contient l'élément b.
     */
    public boolean contains(Binome b) {
        for (Binome e : favoris) {
            if (e.equals(b)) {
                return true;
            }
        }
        return false;
    }

    /**
     * getter
     * 
     * @return la liste des binomes favoris.
     */
    public ArrayList<Binome> getFavoris() {
        return favoris;
    }
}
