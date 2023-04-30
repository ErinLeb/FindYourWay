package model;

import java.util.ArrayList;
import java.util.List;

import view.FavorisView;


public class Batiment {
    // Attributs

    /**
     * Nom du bâtiment
     */
    protected String nom;

    /** 
     * Numéro de l'étage le plus bas
     */
    protected final int min;

    /**
     * Numéro de l'étage le plus haut
     */
    protected final int max;

    /**
     * Path des csv des noeuds du bâtiment
     */
    protected final String pathNoeuds;

     /**
     * Path des plans du bâtiment
     */
    protected final String pathPlans;

    /**
     * Liste des étages du bâtiment
     */
    public final List<Etage> etages = new ArrayList<>();

    /**
     * Liste des noeuds du bâtiment
     */
    public final List<Noeud> noeuds = new ArrayList<>();

    /**
     * Nombre de noeuds dans le bâtiment, incrémenté à chaque ajout de noeud au
     * bâtiment
     */
    private int nbNoeuds = 0;

    /*
     * Facteur multiplicatif entre le plan et la réalité
     */
    private double echelle = 0.3333;

    /**
     * Modèle associé aux éléments favoris
     */
    private FavorisModel modelFavoris;

    /**
     * Vue associée aux éléments favoris
     */
    private FavorisView viewFavoris;

    
    // Constructeur

    /**
     * @param nom          nom du bâtiment 
     * @param min          l'étage le plus bas
     * @param max          l'étage le plus haut
     * @param pathNoeuds   le chemin absolu du dossier contenant les csv des noeuds du bâtiment
     * @param pathPlans    le chemin absolu du dossier contenant les plans du bâtiment
     */
    public Batiment(String nom, int min, int max, String pathNoeuds, String pathPlans) {
        this.nom = nom;
        this.max = max;
        this.min = min;
        this.pathNoeuds = pathNoeuds;
        this.pathPlans = pathPlans;


        for (int i = min; i <= max; i++) {
            etages.add(new Etage(i));
        }

        Parseur parseur = new Parseur(this, pathNoeuds);

        // on crée les noeuds
        parseur.createNoeuds();

        // on crée les liens entre les noeuds
        parseur.initVoisins();

        this.modelFavoris = new FavorisModel();
        this.viewFavoris = new FavorisView();
    }

    /**
     * @param min  l'étage le plus bas
     * @param max  l'étage le plus haut
     * @param path le chemin absolu du dossier contenant les csv des noeuds du bâtiment
     */
    public Batiment(int min, int max, String pathNoeuds){
        this("", min, max, pathNoeuds, ""); 
    }


    // Getters & setters

    /**
     * Renvoie le nom du bâtiment
     * @return le nom du bâtiment
     */
    public String getNom(){
        return nom;
    }

    /**
     * Renvoie l'étage max du bâtiment
     * 
     * @return l'étage max du bâtiment
     */
    public int getMax() {
        return this.max;
    }

    /**
     * Renvoie l'étage min du bâtiment
     * 
     * @return l'étage min du bâtiment
     */
    public int getMin() {
        return this.min;
    }

    /**
     * Renvoie le path des csv des noeuds du bâtiment
     * @return le path des csv des noeuds du bâtiment
     */
    public String getPathNoeuds(){
        return this.pathNoeuds;
    }

    /**
     * Renvoie le path des plans du bâtiment
     * @return le path des plans du bâtiment
     */
    public String getPathPlans(){
        return this.pathPlans;
    }

    /**
     * Retourne le noeud situé à l'indice i de la liste de noeuds
     * 
     * @param i indice du noeud dans la liste de ceux du bâtiment
     * @return  le noeud situé à l'indice i de la liste de noeuds
     */
    public Noeud getNoeud(int i) {
        return this.noeuds.get(i);
    }

    /**
     * Retourne l'étage {@code i}
     * 
     * @return l'étage {@code i}
     */
    public Etage getEtage(int i) {
        return this.etages.get(i);
    }

    /**
     * Renvoie la salle  portant le nom {@code salle}
     * 
     * @param salle nom de la salle à trouver
     * @return      la salle de nom {@code salle}
     */
    public Noeud getSalle(String salle) {
        for (int i = 0; i < noeuds.size(); i++) {
            if ((noeuds.get(i) instanceof Salle) && ((((Salle) noeuds.get(i)).nom).equalsIgnoreCase(salle))
                    || (salle.equalsIgnoreCase("cafe") && ((Salle) noeuds.get(i)).nom.equalsIgnoreCase("café"))) {
                return noeuds.get(i);
            }
        }
        return null;
    }

    /**
     * Retourne le facteur multiplicatif entre le plan du bâtiment et la réalité
     * 
     * @return le facteur multiplicatif entre le plan du bâtiment et la réalité
     */
    public double getEchelle() {
        return echelle;
    }

    /**
     * Renvoie la view des éléments favoris
     * 
     * @return la view des éléments favoris
     */
    public FavorisView getViewFavoris() {
        return viewFavoris;
    }

    /**
     * Renvoie le modèle des éléments favoris
     * 
     * @return le modèle des éléments favoris
     */
    public FavorisModel getModelFavoris() {
        return modelFavoris;
    }

    /**
     * Change le nombre de Noeuds de la classe par {@code n}
     * 
     * @param n
     */
    public void setNbNoeuds(int n) {
        nbNoeuds = n;
    }


    // Methodes

    @Override
    public boolean equals(Object o) {
        if (o instanceof Batiment) {
            Batiment comp = (Batiment) o;

            if(!nom.equals(comp.nom)){
                return false;
            }
            if(min != comp.min || max != comp.max){
                return false;
            }
            if (!(noeuds.equals(comp.noeuds))) {
                return false;
            }
            if (!(etages.equals(comp.etages))) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Ajoute {@code n} à la liste de noeuds de son étage et à la liste de noeuds du
     * bâtiment
     * 
     * @param n noeud à ajouter
     */
    public void addNoeud(Noeud n) {
        if (n instanceof Carrefour) {
            getEtage(((Carrefour) n).etage).addNoeud(n);
        }
        noeuds.add(n);

        n.setId(nbNoeuds++);
    }

    /**
     * Renvoie si une salle de nom {@code nom} se trouve dans le bâtiment
     * 
     * @param nom nom de la salle recherchée
     * @return    true si la salle se trouve dans le bâtiment, faux sinon
     */
    public boolean findSalle(String nom) {
        for (int i = 0; i < noeuds.size(); i++) {
            if ((noeuds.get(i) instanceof Salle) && ((((Salle) noeuds.get(i)).nom.equalsIgnoreCase(nom))
                    || (nom.equalsIgnoreCase("cafe") && ((Salle) noeuds.get(i)).nom.equalsIgnoreCase("café")))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return nom;
    }
}