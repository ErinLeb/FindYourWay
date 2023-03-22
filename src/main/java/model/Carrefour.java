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


    // Getters & setters

    public int getEtage(){
        return etage;
    }


    // Méthodes
    @Override
    public boolean equals(Object o){
        if(!super.equals(o)){
            return false;
        }

        if(o instanceof Carrefour){
            Carrefour comp = (Carrefour)o;

            if(etage != comp.etage){
                return false;
            }

            if(isAscenseur != comp.isAscenseur){
                return false;
            }

            return true;
        }
        return false;
    }



    @Override
    public String toString() {
        if (isAscenseur) {
            return id + " Ascenseur à l'étage " + etage;
        }
        return id + " Carrefour à l'étage " + etage;
    }

}
