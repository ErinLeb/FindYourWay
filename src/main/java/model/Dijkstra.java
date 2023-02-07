package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe qui permet de touver le chemin le plus court dans un arbre pour aller
 * d'un noeud 1 à un noeud 2.
 */
public class Dijkstra {

    /**
     * Methode qui trouve le chemin le plus court pour aller d'un noeud de début à un
     * noeud de fin
     * 
     * @param debut noeud de départ du parcours
     * @param fin   noeud de fin du parcours
     * @return une liste avec les noeuds à parcourir pour obtenir le chemin le plus
     *         court.
     */
    public static List<Noeud> trouverCheminPlusCourt(Noeud debut, Noeud fin) {
        // Map qui contiendra l'information de la distance entre chaque
        // noeud et le noeud de départ.
        Map<Noeud, Double> distances = new HashMap<Noeud, Double>();
        // Map qui associe le noeud précédent le noeud actuel avec le chemin le plus
        // court pour accéder au noeud actuel(commence à répertorier le chemin le plus
        // court).
        Map<Noeud, Noeud> predecesseurs = new HashMap<Noeud, Noeud>();
        // contiendra la liste des noeuds.
        List<Noeud> noeuds = new ArrayList<Noeud>();

        initNoeudList(distances, noeuds, debut);
        distances.put(debut, 0.0);

        while (!noeuds.isEmpty()) {
            // on récupère le noeud avec la distance la plus courte.
            Noeud noeudActuel = getNoeudMinimal(noeuds, distances);
            // on le supprime de la liste des noeud à traiter.
            noeuds.remove(noeudActuel);

            // pour chacun des voisins
            for (Noeud voisin : noeudActuel.voisins.keySet()) {
                // on initialise la distance du chemin à parcourir pour aller au noeud voisin
                // qui est en train d'être traité.
                double poids = noeudActuel.voisins.get(voisin);
                // on initialise les distances totales (soit la distance qu'il a fallu pour se
                // déplacer du noeud actuel jusqu'au noeud de départ, plus la distance qu'il
                // faut pour se déplacer au voisin que l'on est en train de traiter).
                double distanceTotale = distances.get(noeudActuel) + poids;
                // Si la distance à parcourir par le chemin actuel est plus petit pour accèder
                // au voisin traité que tout ceux trouvés avant
                if (distanceTotale <= distances.get(voisin)) {
                    // on associe à chaque voisin la distance la plus courte pour arriver à ce noeud.
                    distances.put(voisin, distanceTotale);
                    // on associe à chaque voisin un prédécéceur, le prédécéceur avec le chemin le
                    // plus court pour accéder à ce noeud. (remplace l'ancien)
                    predecesseurs.put(voisin, noeudActuel);
                }
            }
        }

        // s'il existe bien un chemin qui contient le noeud de fin
        if (predecesseurs.containsKey(fin)) {
            // on reconstruit le chemin
            return reconstruireChemin(predecesseurs, debut, fin);
        }
        // sinon on retourne un chemin vide
        return null;
    }

    /**
     * initialise tous les noeuds à une distance théorique infinie (MAX_VALUE) du
     * noeud de départ.
     * 
     * @param distances Map qui contient l'information de la distance entre le
     *                  noeud actuel et le noeud de départ.
     * @param noeuds    noeuds déjà traités
     * @param noeud     noeud que l'on est en train de traiter
     */
    public static void initNoeudList(Map<Noeud, Double> distances, List<Noeud> noeuds, Noeud noeud) {
        // initialise la distance du noeud actuel par rapport au noeud de départ à une
        // distance infinie.
        distances.put(noeud, Double.MAX_VALUE);
        // ajoute le noeud à la liste des noeuds traités.
        noeuds.add(noeud);
        // pour chacun des voisins
        for (Noeud n : noeud.voisins.keySet()) {
            // si le noeud n'a pas encore été traité
            if (!noeuds.contains(n)) {
                // j'initialise le noeud.
                initNoeudList(distances, noeuds, n);
            }
        }
    }

    /**
     * Methode qui permet d'obtenir le noeud avec la distance la plus courte du noeud de départ
     * 
     * @param noeuds    Liste des noeuds à regarder
     * @param distances Map qui contient l'information de la distance entre le
     *                  noeud actuel et le noeud de départ.
     * @return le noeud avec la distance la plus courte avec le noeud de depart.
     */

    // TODO : utiliser une file de priorité

    private static Noeud getNoeudMinimal(List<Noeud> noeuds, Map<Noeud, Double> distances) {
        Noeud noeudMinimal = noeuds.get(0);
        double distanceMinimale = distances.get(noeudMinimal);
        for (Noeud noeud : noeuds) {
            double distance = distances.get(noeud);
            if (distance < distanceMinimale) {
                noeudMinimal = noeud;
                distanceMinimale = distance;
            }
        }
        return noeudMinimal;
    }

    /**
     * Méthode qui reconstruit le chemin le plus court
     * 
     * @param predecesseurs Map qui associe le noeud précédant le noeud actuel avec
     *                      le chemin le plus court pour accéder au noeud actuel
     * @param debut         noeud de départ
     * @param fin           noeud de fin
     * @return la liste qui contient le chemin le plus court.
     */
    private static List<Noeud> reconstruireChemin(Map<Noeud, Noeud> predecesseurs, Noeud debut, Noeud fin) {
        // initialise la liste qui contiendra les noeuds à parcourir.
        List<Noeud> chemin = new ArrayList<Noeud>();
        // on démarre le chemin par la fin.
        Noeud noeudActuel = fin;
        // tant qu'on est pas arrivé au noeud de départ.
        while (noeudActuel != debut) {
            // on ajoute le noeud actuel à la liste
            chemin.add(noeudActuel);
            // on ajoute le prédécésseur le moins loin du noeud de départ.
            noeudActuel = predecesseurs.get(noeudActuel);
        }
        // on ajoute le noeud de départ à la fin.
        chemin.add(noeudActuel);
        // on retourne la liste pour l'avoir dans le bonne ordre
        Collections.reverse(chemin);
        return chemin;
    }
}