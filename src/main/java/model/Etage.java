package model;

import java.util.List;
import java.util.ArrayList;

public class Etage{
    // Attributs
    public final int nom;

    /**
     * Liste des noeuds de l'étage
     */
    private List<Noeud> noeuds;
    
    /**
     * Liste des salles accesibles à cet étage
     */
    private List<Salle> salles;


    // Constructeurs
    public Etage(int nom){
        noeuds = new ArrayList<>();
        salles = new ArrayList<>();

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

    /**
     * Retourne la liste des salles de l'étage
     * @return la liste des salles de l'étage
     */
    public List<Salle> getSalles(){
        return salles;
    }

    /**
     * Retourne la salle avec le nom {@code nom}
     * @param nom
     * @return la salle avec le nom {@code nom}
     */
    public Salle getSalle(String nom){
        for(Salle s : salles){
            if(s.id.equals(nom)){
                return s;
            }
        }
        return null;
    }


    // Méthodes

    /**
     * Ajoute une salle à l'étage
     */
    public void addSalle(Salle s){
        salles.add(s);
    }

    /** 
     * Ajoute un noeud à l'étage. Si c'est une porte, il vérifie qu'elle mène à une salle existante.
    */
    public void addNoeud(Noeud n){
        if( !(n instanceof Porte) || n instanceof Porte && salleExistante((Porte)n)){ //S'il s'agit d'une porte, on vérifie que la salle à laquelle elle mène est à cet étage
            noeuds.add(n);
        }else{
            throw new IllegalArgumentException("La porte mène à une salle qui n'existe pas à cet étage");
        }
    }

    /**
     * Vérifie que {@code p} mène à une salle de l'étage. 
     */
    private boolean salleExistante(Porte p){
        Salle s = getSalle(p.nomSalle);
        if(s != null){
            p.setSalle(s);
            return true;
        }
        return false;
    }

}