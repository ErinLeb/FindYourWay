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

    /**
     * Abscisse du noeud
     */
    private Double x;

    /**
     * Ordonnée du noeud
     */
    private Double y;

    // Constructeur

    /**
     * Crée un Carrefour sans voisin
     * 
     * @param isAscenseur définit si le carrefour est un ascenseur ou non
     * @param etage l'étage auquel se trouve le carrefour
     * @param batiment le batiment dans lequel se trouve le carrefour
     * @param x la coordonnée d'abscisse sur le plan du carefour
     * @param y la coordonnée d'ordonnée sur le plan du carefour
     */
    public Carrefour(boolean isAscenseur, int etage, Batiment batiment, Double x, Double y) {
        super(batiment);
        this.x = x;
        this.y = y;
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

    /**
     * renvoie la coordonnée x du carrefour
     * @return la coordonnée x du carrefour
     */
    public Double getX() {
        return this.x;
    }

    /**
     * renvoie la coordonnée y du carrefour
     * @return la coordonnée y du carrefour
     */
    public Double getY() {
        return this.y;
    }

}
