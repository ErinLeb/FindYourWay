package model;

import java.util.List;
import java.util.ArrayList;

public class Etage{
    // Attributs
    /**
     * Numéro de l'étage
     */
    public final int nom;

    /**
     * Liste des noeuds de l'étage
     */
    private List<Noeud> noeuds;

    // Constructeurs
    public Etage(int nom){
        noeuds = new ArrayList<>();

        this.nom = nom;
    }


    // Getters

    /**
     * Retourne la liste des noeuds de l'étage
     * @return la liste des noeuds de l'étage
     */
    public List<Noeud> getNoeuds(){
        return noeuds;
    }
    
    /**
     * Retourne le noeud correspondant à {@code id}
     * @return le noeud correspondant à {@code id}
     */    
    public Noeud getNoeud(int id){
        for(Noeud n : noeuds){
            if(n.id == id){
                return n;
            }
        }
        return null;
    }


    // Méthodes

    /** 
     * Ajoute un noeud à l'étage.
    */
    public void addNoeud(Noeud n){ 
        noeuds.add(n);
    }
}