package model;

import java.util.ArrayList;
import java.util.List;

public class Salle{
    // Attributs

    /**
     * identifiant de la salle
     */
    public final String id;

    /**
     * liste des portes qui mènent à la salle
     */
    private ArrayList<Porte> portes;

    // Constructeurs

    Salle(String id){
        this.id = id;
        portes  = new ArrayList<>();
    }

    // Méthodes

    /**
     * Ajoute une porte à la liste de portes qui mènent à la salle
     * @param porte
     */
    public void addPorte(Porte porte){
        portes.add(porte);
    }

    /**
     * Renvoie la liste des portes qui mènent à la salle
     * @return renvoie la liste des portes qui mènent à la salle
     */
    public List<Porte> getPortes(){
        return portes;
    }
}