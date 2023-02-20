package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Batiment{
    // Attributs

    /**
     * Numéro de l'étage le plus bas
     */
    protected final int min;

    /**
     * Numéro de l'étage le plus haut
     */
    protected final int max;

    /**
     * Liste des étages du Batiment
     */
    public final List<Etage> etages =  new ArrayList<>();

    /**
     * Liste des noeuds du graphe du Batiment   
     */
    public final List<Noeud> noeuds = new ArrayList<>();


    // Constructeur 
    /**
     * 
     * @param min l'étage le plus bas
     * @param max l'étage le plus haut
     * @param path le chemin absolu du répertoire dans le dossier contenant les csv du batiment
     */
    public Batiment(int min, int max, String path){ 
        this.max = max;
        this.min = min;

        for(int i = min; i<=max; i++){
            etages.add(new Etage(i)); 
        }  

        Parseur parseur = new Parseur(this, path);
        
        //on crée les noeuds
        parseur.createNoeuds();

        //on crée les liens entre les noeuds
        parseur.initVoisins();
    }


    // Getters & setters
    
    /**
     * Retourne la liste de noeuds du bâtiment
     * @return la liste de noeuds du bâtiment
     */
    public List<Noeud> getNoeuds() {
        return this.noeuds;
    }

    /**
     * Retourne le noeud situé à l'indice i de la liste de noeuds
     * @param i indice du noeud dans la liste de ceux du batiment
     * @return le noeud situé à l'indice i de la liste de noeuds
     */
    public Noeud getNoeud(int i) {
        return this.noeuds.get(i);
    }

    /**
     * Retourne l'étage {@code i}
     * @return l'étage {@code i}
     */
    public Etage getEtage(int i){
        return this.etages.get(i);
    }


    // Methodes

    /**
     * Ajoute {@code n} à la liste de noeuds de son étage et à la liste de noeuds du batiment
     */
    public void addNoeud(Noeud n){ 
        if(n instanceof Carrefour){
            //on ajoute le noeud à l'étage que si on a besoin de savoir qu'il y est (pour l'affichage)
            getEtage(((Carrefour)n).etage).addNoeud(n);
        }
        noeuds.add(n);
    }
}