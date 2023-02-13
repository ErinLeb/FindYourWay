package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Chemin {
    // Attributs

    /**
     * Liste des Noeuds du trajet
     */
    private List<Noeud> noeuds;

    /**
     * Distance totale du chemin
     */
    private double distance;

    // Constructeurs
    public Chemin() {
        noeuds = new ArrayList<>();
        distance = 0;
    }

    public Chemin(Noeud depart) {
        this();
        noeuds.add(depart);
    }

    public void addNoeud(Noeud n) {
        if (!noeuds.isEmpty()) {
            distance += n.getVoisins().get(noeuds.get(noeuds.size() - 1));
        }
        noeuds.add(n);
    }

    public double getDistance() {
        return distance;
    }

    public List<Noeud> getNoeuds() {
        return noeuds;
    }

    public Noeud getDepart() {
        if (noeuds.isEmpty()) {
            return null;
        }
        return noeuds.get(0);
    }

    public Noeud getArrivee() {
        if (noeuds.isEmpty()) {
            return null;
        }
        return noeuds.get(noeuds.size() - 1);
    }

    // Méthodes

    public void reverse() {
        Collections.reverse(noeuds);
    }

}