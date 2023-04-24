package model;

import java.util.List;
import java.util.ArrayList;

public class Etage{
    // Attributs
    
    /**
     * Numéro de l'étage
     */
    public final int nom;

    private final Batiment bat;

    /**
     * Liste des noeuds de l'étage
     */
    private List<Noeud> noeuds;


    // Constructeur
    
    public Etage(int nom, Batiment bat){
        noeuds = new ArrayList<>();

        this.nom = nom;
        this.bat = bat;
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
     * Retourne le noeud ayant un id correspondant à {@code id}
     * @return le noeud ayant un id correspondant à {@code id}
     */    
    public Noeud getNoeud(int id){
        for(Noeud n : noeuds){
            if(n.id == id){
                return n;
            }
        }
        return null;
    }
    
    /**
     * Retourne le noeud à l'indice i de la liste
     * @param i indice
     * @return le noeud à l'indice i de la liste
     */
    public Noeud getIndNoeud(int i){
        return noeuds.get(i);
    }


    // Méthodes

    @Override
    public boolean equals(Object o){
        if(o instanceof Etage){
            Etage comp = (Etage)o;

            if(nom != comp.nom){
                return false;
            }

            if(!(noeuds.equals(comp.noeuds))){
                return false;
            }

            return true;
        }
        return false;
    }

    /** 
     * Ajoute un noeud à l'étage.
    */
    public void addNoeud(Noeud n){ 
        noeuds.add(n);
    }
}
