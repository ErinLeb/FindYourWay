import java.util.HashMap;

public abstract class Noeud{

    /**
     * nombre de Noeuds qui ont été créés
     */
    protected static int nbNoeud;

    /**
     * numéro d'identification des noeuds qui permettra de savoir par quels noeuds on est déjà passé
     */
    protected int id;
    
    /**
    * dictionnaire des paires voisin,distance du Noeud courant
    */
    protected HashMap<Noeud, Double> voisins;

    /**
     * renvoie la liste des voisins du noeud avec leur distance
     * @return renvoie la liste des voisins du noeud avec leur distance
     */
    public HashMap<Noeud, Double> getVoisins(){
        return voisins;
    }

    /**
     * ajoute un voisin au noeud
     * @param voisin voisin à ajouter
     * @param distance distance entre les deux noeuds
     */
    public void addVoisin(Noeud voisin,Double distance){
        voisins.put(voisin,distance);
        voisin.voisins.put(this,distance);
    }

}