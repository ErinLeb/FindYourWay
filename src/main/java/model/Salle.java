package model;

public class Salle extends Noeud{
    // Attributs

    /**
     * Nom de la salle
     */
    public final String nom;


    // Constructeurs

    public Salle(String nom, Batiment bat){
        super(bat);
        this.nom = nom;
    }


    // Getters & setters

    public String getNom(){
        return nom;
    }


    // Méthodes

    @Override
    public String toString(){
        return "Salle " + nom;
    }
}