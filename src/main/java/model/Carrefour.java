package model;

public class Carrefour extends Noeud {
    // Attributs

    /**
     * attribut définissant si le carrefour est un ascenseur et donc s'il y a besoin
     * d'une autorisation pour l'emprunter
     */
    public final boolean ascenseur;

    // Constructeurs

    /**
     * Crée un Carrefour sans voisin
     * 
     * @param ascenseur définit si le carrefour est un ascenseur ou non
     */
    public Carrefour(boolean ascenseur, int etage, Batiment batiment) {
        super(etage, batiment);
        this.ascenseur = ascenseur;
    }

    // Méthodes
    @Override
    public String toString(){
        if(ascenseur){
            return "Ascenseur à l'étage " + etage;
        }
        return "Escalier à l'étage " + etage;
    }
}