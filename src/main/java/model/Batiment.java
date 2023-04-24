package model;

import java.util.ArrayList;
import java.util.List;

import view.FavorisView;

public class Batiment {
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
    public final List<Etage> etages = new ArrayList<>();

    /**
     * Liste des noeuds du graphe du Batiment
     */
    public final List<Noeud> noeuds = new ArrayList<>();

    /**
     * Nombre de noeuds dans le batiment, incrémenté à chaque ajout de noeud au
     * batiment
     */
    private int nbNoeuds = 0;

    /*
     * Facteur multiplicatif entre le plan et la réalité
     */
    private double echelle = 0.3333;

    /**
     * Element favoris
     */
    private FavorisModel modelFavoris;
    private FavorisView viewFavoris;

    // Constructeur
    /**
     * 
     * @param min  l'étage le plus bas
     * @param max  l'étage le plus haut
     * @param path le chemin absolu du répertoire dans le dossier contenant les csv
     *             du batiment
     */
    public Batiment(int min, int max, String path) {
        this.max = max;
        this.min = min;

        for (int i = min; i <= max; i++) {
            etages.add(new Etage(i));
        }

        Parseur parseur = new Parseur(this, path);

        // on crée les noeuds
        parseur.createNoeuds();

        // on crée les liens entre les noeuds
        parseur.initVoisins();

        this.modelFavoris = new FavorisModel();
        this.viewFavoris = new FavorisView();
    }

    // Getters & setters

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
     * Retourne le noeud situé à l'indice i de la liste de noeuds
     * 
     * @param i indice du noeud dans la liste de ceux du batiment
     * @return le noeud situé à l'indice i de la liste de noeuds
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
     * Renvoie la salle dont on a donné le nom en paramètre
     * 
     * @param salle nom de la salle à trouver
     * @return la salle de nom donné en paramètre
     */
    public Noeud getSalle(String salle) {
        for (int i = 0; i < noeuds.size(); i++) {
            if ((noeuds.get(i) instanceof Salle) && ((((Salle) noeuds.get(i)).getNom().equalsIgnoreCase(salle))
                    || (salle.equalsIgnoreCase("cafe") && ((Salle) noeuds.get(i)).getNom().equalsIgnoreCase("café")))) {
                return noeuds.get(i);
            }
        }
        return null;
    }

    /**
     * Retourne le facteur multiplicatif entre le plan du batiment et la réalité
     * 
     * @return le facteur multiplicatif entre le plan du batiment et la réalité
     */
    public double getEchelle() {
        return echelle;
    }

    /**
     * Renvoie la view des éléments favoris.
     * 
     * @return la view des éléments favoris.
     */
    public FavorisView getViewFavoris() {
        return viewFavoris;
    }

    /**
     * Renvoie le modèle des éléments favoris.
     * 
     * @return le modèle des éléments favoris.
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

    /**
     * Change l'échelle du plan
     * 
     * @param echelle
     */
    public void setEchelle(double echelle) {
        this.echelle = echelle;
    }

    // Methodes

    @Override
    public boolean equals(Object o) {
        if (o instanceof Batiment) {
            Batiment comp = (Batiment) o;

            if (min != comp.min || max != comp.max) {
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
     * batiment
     */
    public void addNoeud(Noeud n) {
        if (n instanceof Carrefour) {
            // on ajoute le noeud à l'étage que si on a besoin de savoir qu'il y est (pour
            // l'affichage)
            getEtage(((Carrefour) n).etage).addNoeud(n);
        }
        noeuds.add(n);

        n.setId(nbNoeuds++);
    }

    /**
     * Renvoie si une salle de nom {@code nom} se trouve dans le batiment
     * 
     * @param nom nom de la salle recherchée
     * @return true si la salle se trouve dans le batiment et faux sinon
     */
    public boolean findSalle(String nom) {
        for (int i = 0; i < noeuds.size(); i++) {
            // on permet à l'utilisateur d'écrire "café" sans accents
            if ((noeuds.get(i) instanceof Salle) && ((((Salle) noeuds.get(i)).getNom().equalsIgnoreCase(nom))
                    || (nom.equalsIgnoreCase("cafe") && ((Salle) noeuds.get(i)).getNom().equalsIgnoreCase("café")))) {
                return true;
            }
        }
        return false;
    }

}