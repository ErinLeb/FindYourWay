import java.util.HashMap;

public class Carrefour extends Noeud{

    /**
     * attribut définissant si le carrefour est un ascenseur et donc s'il y a besoin d'une autorisation pour l'emprunter
     */
    public final boolean ascenseur;

    /**
    * Crée un Carrefour sans voisin
    @param ascenseur définit si le carrefour est un ascenseur ou non
    */
    Carrefour(boolean ascenseur){
        super.id = super.nbNoeud;
        super.nbNoeud++;
        this.ascenseur=ascenseur;
        super.voisins = new HashMap<Noeud, Double>();
    }



}