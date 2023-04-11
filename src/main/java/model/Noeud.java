package model;

import java.util.HashMap;
import java.util.Map;

public abstract class Noeud {
    // Attributs

    /**
     * Nombre de Noeuds qui ont été créés
     */
    protected static int nbNoeud;

    /**
     * Numéro d'identification des noeuds qui permettra de savoir par quels noeuds
     * on est déjà passé, initialisé par le numéro de création du noeud
     */
    protected int id;

    /**
     * Batiment auquel le noeud appartient
     */
    protected final Batiment batiment;

    /**
     * Dictionnaire des paires voisin/distance du noeud
     */
    protected HashMap<Noeud, Double> voisins;


    // Constructeur

    protected Noeud(Batiment batiment){
        this.id = nbNoeud;
        nbNoeud++;

        this.batiment = batiment;

        voisins = new HashMap<>();
    }


    // Getters & setters

    /**
     * Renvoie l'identifiant du noeud
     * 
     * @return renvoie l'identifiant du noeud
     */
    public int getId() {
        return id;
    }

    /**
     * Change l'id du noeud par {@code id}
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Renvoie le dictionnaire des voisins du noeud avec leur distance
     * 
     * @return renvoie le dictionnaire des voisins du noeud avec leur distance
     */
    public Map<Noeud, Double> getVoisins() {
        return voisins;
    }

    /**
     * Renvoie le noeud de la map correspondant à l'id {@code id}, null s'il n'y en a pas
     * @param id
     * @return
     */
    public Noeud getVoisin(int id){
        //on parcourt la map
        for(Map.Entry mapentry : voisins.entrySet()){

            Noeud current = (Noeud)(mapentry.getKey());

            if(current.id == id){
                return current;
            }
        }
        return null;
    }

    
    // Méthodes

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Noeud)){
            return false;
        }

        Noeud comp = (Noeud)o;

        //on compare le numéro du noeud dans le batiment
        if(id != comp.id){
            return false;
        }

        //on compare les voisins
        return compareVoisins(comp);
    }

    /**
     * Compare si les voisins de n sont les mêmes que ceux du noeud courant 
     * @param n
     * @return
     */
    public boolean compareVoisins(Noeud n){
        //si les noeuds n'ont pas le même nombre de voisins
        if(voisins.size() != n.voisins.size()){
            return false;
        }

        //on parcourt la map de voisins 
        for(Map.Entry mapentry : voisins.entrySet()){

            Noeud noeudCourant = (Noeud)mapentry.getKey();
            Double distCourante = (Double)mapentry.getValue(); 
            //pour chaque voisin, on regarde s'il y est dans la map de n 

            boolean there = false;
            for(Map.Entry mapentryBis : n.voisins.entrySet()){
                Noeud noeudComp = (Noeud)mapentryBis.getKey();
                Double distComp = (Double)mapentryBis.getValue();

                if(noeudCourant.id == noeudComp.id && distCourante == distComp){
                    there = true;
                }
            }
            if(!there){ //on a pas trouvé d'équivalent
                return false; 
            }
        }

        return true;
    }


    /**
     * Ajoute un voisin au noeud
     * 
     * @param voisin   voisin à ajouter
     * @param distance distance entre les deux noeuds
     */
    public void addVoisin(Noeud voisin, Double distance) {
        voisins.put(voisin, distance);
    }

}
