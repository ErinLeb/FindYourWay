package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Batiment{
    // Attributs
    protected final int max;
    protected final int min;


    public final List<Etage> etages =  new ArrayList<>();
    public final List<Noeud> noeuds = new ArrayList<>();

    // Constructeur 
    /**
     * 
     * @param min l'étage le plus bas
     * @param max l'étage le plus haut
     * @param path le chemin dans le répertoire du dossier contenant les csv du batiment
     */
    public Batiment(int min, int max, String path){ 
        for(int i = min; i<=max; i++){
            etages.add(new Etage(i)); 
        }  
        

        this.max = max;
        this.min = min;

        Parseur parseur = new Parseur(this, path);
        
        //on initialise les étages et leurs salles
        parseur.initSalles();
        
        //on initialise les noeuds
        parseur.createNoeuds();

        //on initialise les liens entre les noeuds
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
     * @param i
     * @return le noeud situé à l'indice i de la liste de noeuds
     */
    public Noeud getNoeud(int i) {
        return this.noeuds.get(i);
    }

    /**
     * Retourne l'étage {@code i}
     * @return l'étage {@©ode i}
     */
    public Etage getEtage(int i){
        return this.etages.get(i);
    }


    // Methodes

    /**
     * Ajoute {@code n} à la liste de noeuds de son étage et à la liste de noeuds du batiment
     */
    public void addNoeud(Noeud n){
        if(n.etage > max || n.etage < min){
            throw new IllegalArgumentException("L'étage n'existe pas");
        }
        etages.get(n.etage).addNoeud(n);
        noeuds.add(n);
    }

    public void addEtage(int number){
        if(number<min || number>max){
            throw new IllegalArgumentException("Le numéro d'étage n'est pas correct");
        }
        etages.add(new Etage(number)); 
    }

    public static void main(String[] args) {
        Batiment haf = new Batiment(0, 5, "src/main/ressources/test/");

        Iterator<Noeud> it = haf.noeuds.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
            
        }
    }

}