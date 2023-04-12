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
     * Coordonées ou sera affiché le noeud dans la vue.
     */
    private double x;
    private double y;
    

    // Constructeur

    /**
     * Crée un Carrefour sans voisin
     * 
     * @param isAscenseur définit si le carrefour est un ascenseur ou non
     * @param etage       l'étage auquel se trouve le carrefour
     * @param batiment    le batiment dans lequel se trouve le carrefour
     * @param x           la coordonnée d'abscisse sur le plan du carefour
     * @param y           la coordonnée d'ordonnée sur le plan du carefour
     */
    public Carrefour(boolean isAscenseur, int etage, Batiment batiment, double x, double y) {
        super(batiment);
        this.isAscenseur = isAscenseur;
        this.etage = etage;
        this.x = x;
        this.y = y;
    }

    /**
     * Crée un Carrefour sans voisin
     * @param isAscenseur définit si le carrefour est un ascenseur ou non
     * @param etage étage du carrefour
     * @param batiment bâtiment du carrefour
     */
    public Carrefour(boolean isAscenseur, int etage, Batiment batiment) {
        this(isAscenseur, etage, batiment, 0, 0);
    }

    // Getters & setters

    public int getEtage() {
        return etage;
    }

    /**
     * renvoie la coordonnée en x du carrefour pour l'affichage
     * @return renvoie la coordonnée en x du carrefour pour l'affichage
     */
    public double getX(){
        return x;
    }

    /**
     * renvoie la coordonnée en y du carrefour pour l'affichage
     * @return renvoie la coordonnée en y du carrefour pour l'affichage
     */
    public double getY(){
        return y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }


    // Méthodes
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }

        if (o instanceof Carrefour) {
            Carrefour comp = (Carrefour) o;

            if (etage != comp.etage) {
                return false;
            }

            if (isAscenseur != comp.isAscenseur) {
                return false;
            }

            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (isAscenseur) {
            return id + " Ascenseur à l'étage " + etage + ", x : " + x + ", y = " + y;
        }
        return id + " Carrefour à l'étage " + etage + ", x : " + x + ", y = " + y;
    }

}
