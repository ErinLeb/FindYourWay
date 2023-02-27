package model;

public class Carrefour extends Noeud {
    // Attributs

    /**
     * Numéro d'étage qui servira à l'affichage
     */
    protected int etage;

    /**
     * Attribut définissant si le carrefour est un ascenseur
     */
    public final boolean isAscenseur;

    // Constructeur

    /**
     * Crée un Carrefour sans voisin
     * 
     * @param isAscenseur définit si le carrefour est un ascenseur ou non
     */
    public Carrefour(boolean isAscenseur, int etage, Batiment batiment) {
        super(batiment);
        this.isAscenseur = isAscenseur;
        this.etage = etage;
    }

    // Méthodes
    public int getEtage() {
        return etage;
    }

    @Override
    public String toString() {
        if (isAscenseur) {
            return "Ascenseur à l'étage " + etage;
        }
        return "Carrefour à l'étage " + etage;
    }
}