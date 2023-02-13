package model;

public class Porte extends Noeud{
    //Attributs 

    /**
     * Salle à laquelle la porte mène
     */
    protected Salle salle;

    /**
     * Nom de la salle à laquelle la porte mène
     */
    public final String nomSalle;

    //Constructeurs

    /**
     * Crée une porte sans voisin qui mène à une salle
     * @param salle nom de la salle à laquelle la porte mène
     * @param etage numero de l'etage de la porte
     */
    Porte(String nomSalle, int etage, Batiment batiment){
        super(etage, batiment);

        salle = null;
        this.nomSalle = nomSalle;
    }

    // Getters & Setters

    public void setSalle(Salle s){
        this.salle = s;
    }


    //Méthodes
    
    @Override
    public String toString(){
        return ("Porte à l'étage " + etage);
    }
}