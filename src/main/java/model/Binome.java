package model;

/**
 * Classe qui représente une paire de Noeuds
 */
public class Binome {
    // Attributs 

    public Noeud depart;

    public Noeud arrivee;


    // Constructeur

    public Binome(Noeud depart, Noeud arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
    }


    // Getters

    public Noeud getDepart() {
        return depart;
    }

    public Noeud getArrivee() {
        return arrivee;
    }


    // Méthodes 

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Binome)) {
            return false;
        }
        Binome b = (Binome) obj;
        if (this.depart == null && b.depart == null) {
            if (this.arrivee != null) {
                return this.arrivee.equals(b.arrivee);
            }
            return b.arrivee == null;
        }
        if (this.arrivee == null && b.arrivee == null) {
            if (this.depart != null) {
                return this.depart.equals(b.depart);
            }
            return b.depart == null;
        }
        if (this.depart == null || this.arrivee == null) {
            return false;
        }
        return this.depart.equals(b.depart) && this.arrivee.equals(b.arrivee);
    }

    @Override
    public String toString() {
        if (depart == null) {
            if (arrivee == null) {
                return "null";
            }
            return ((Salle) arrivee).nom;
        }
        if (arrivee == null) {
            return ((Salle) depart).nom;
        }
        return ((Salle) depart).nom + " ==> " + ((Salle) arrivee).nom;
    }
}