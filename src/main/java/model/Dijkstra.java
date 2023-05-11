package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe qui permet de trouver le chemin le plus court dans un arbre pour aller
 * d'un noeud de départ à un noeud d'arrivée
 */
public class Dijkstra {

    // Constructeur

    private Dijkstra() {
        // Jamais instanciée car classe utilitaire
    }


    // Getters & setters

    /**
     * Methode qui permet d'obtenir le noeud avec la distance la plus courte du
     * noeud de départ
     * 
     * La liste est déjà triée, donc on récupère le premier élément de liste
     * 
     * @param noeuds Liste des noeuds à regarder (déjà triés)
     * @return       le noeud avec la distance la plus courte avec le noeud de départ
     */

    private static Noeud getNoeudMinimal(List<Noeud> noeuds) {
        return noeuds.get(0);
    }


    // Méthodes

    /**
     * Methode qui trouve le chemin le plus court pour aller d'un noeud de début à
     * un noeud de fin
     * 
     * @param debut noeud de départ du parcours
     * @param fin   noeud de fin du parcours
     * @return      une liste avec les noeuds à parcourir pour obtenir le chemin le plus
     *              court
     */
    public static Chemin trouverCheminPlusCourt(Noeud debut, Noeud fin, boolean permsAscenseur) {
        // Map qui contiendra l'information de la distance entre chaque
        // noeud et le noeud de départ
        Map<Noeud, Double> distances = new HashMap<>();

        // Map qui associe le noeud précédent le noeud actuel avec le chemin le plus
        // court pour accéder au noeud actuel(commence à répertorier le chemin le plus
        // court)
        Map<Noeud, Noeud> predecesseurs = new HashMap<>();

        // contiendra la liste des noeuds
        List<Noeud> noeuds = new ArrayList<>();

        initNoeudList(distances, noeuds, debut);
        distances.put(debut, 0.0);

        while (!noeuds.isEmpty()) {
            // on récupère le noeud avec la distance la plus courte
            Noeud noeudActuel = getNoeudMinimal(noeuds);
            // on le supprime de la liste des noeud à traiter
            noeuds.remove(noeudActuel);

            // pour chacun des voisins
            for (Noeud voisin : noeudActuel.voisins.keySet()) {
                // on initialise la distance du chemin à parcourir pour aller au noeud voisin
                // qui est en train d'être traité
                double poids = noeudActuel.voisins.get(voisin);
                // on initialise les distances totales (soit la distance qu'il a fallu pour se
                // déplacer du noeud actuel jusqu'au noeud de départ, plus la distance qu'il
                // faut pour se déplacer au voisin que l'on est en train de traiter)
                double distanceTotale = distances.get(noeudActuel) + poids;
                // Si la distance à parcourir par le chemin actuel est plus petite pour accéder
                // au voisin traité que tous ceux trouvés avant
                if (distanceTotale <= distances.get(voisin)
                        && (!(voisin instanceof Salle) || voisin == debut || voisin == fin)
                        // si le noeud est une salle, pour qu'il puisse être inséré dans le
                        // chemin, il faut que ce soit le noeud de fin ou celui de départ
                        // Les seules salles sont le noeud de début et celui de fin. Personne ne
                        // peut traverser une salle
                        && ((voisin instanceof Carrefour && (!((Carrefour) voisin).isAscenseur || permsAscenseur))
                                || !(voisin instanceof Carrefour))
                // si le noeud est un carrefour, pour qu'il puisse être inséré dans le chemin,
                // il faut que ne soit pas un ascenseur, ou si c'est un ascenseur, il faut que
                // la personne ait la permission de l'utiliser
                ) {
                    // on associe à chaque voisin la distance la plus courte pour arriver à ce
                    // noeud
                    distances.put(voisin, distanceTotale);
                    // trie la liste de noeuds avec la distance actualisée à la ligne précédente
                    actualiserNoeudOrdreCroissant(noeuds, distances, voisin);
                    // on associe à chaque voisin un prédécesseur, le prédécesseur avec le chemin le
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
     * Initialise tous les noeuds à une distance théorique infinie (MAX_VALUE) du
     * noeud de départ
     * 
     * @param distances Map qui contient l'information de la distance entre le
     *                  noeud actuel et le noeud de départ
     * @param noeuds    noeuds déjà traités
     * @param noeud     noeud que l'on est en train de traiter
     */
    public static void initNoeudList(Map<Noeud, Double> distances, List<Noeud> noeuds, Noeud noeud) {
        // initialise la distance du noeud actuel par rapport au noeud de départ à une
        // distance infinie
        distances.put(noeud, Double.MAX_VALUE);

        // ajoute le noeud à la liste des noeuds traités
        noeuds.add(noeud);

        for (Noeud n : noeud.voisins.keySet()) {
            // si le noeud n'a pas encore été traité
            if (!noeuds.contains(n)) {
                initNoeudList(distances, noeuds, n);
            }
        }
    }

    /**
     * Complexité log2(n) :
     * 
     * Trie la liste de noeuds dans l'ordre du noeud le plus proche au noeud le plus
     * loin du noeud de départ
     * 
     * @param noeuds    liste à trier
     * @param distances information de la distance des noeuds par rapport au noeud
     *                  de départ
     * @param voisin    noeud que l'on doit ranger correctement
     * @param debut     début de l'intervalle dans lequel peut être rangé le noeud
     *                  voisin
     * @param fin       fin de l'intervalle dans lequel peut être rangé le noeud
     *                  voisin
     */
    private static void rangerNoeudOrdreCroissant(List<Noeud> noeuds, Map<Noeud, Double> distances, Noeud voisin,
            int debut, int fin) {
        if (debut == fin) {
            if (distances.get(noeuds.get(debut)) > distances.get(voisin)) {
                noeuds.add(debut, voisin);
            } else {
                noeuds.add(fin + 1, voisin);
            }
        } else {
            if (fin - debut == 1) {
                if (distances.get(noeuds.get(debut)) > distances.get(voisin)) {
                    noeuds.add(debut, voisin);
                } else {
                    if (distances.get(noeuds.get(debut)) < distances.get(voisin)) {
                        noeuds.add(fin + 1, voisin);
                    }
                    noeuds.add(fin, voisin);
                }
            } else {
                if (distances.get(noeuds.get(debut + ((fin - debut) / 2))) > distances.get(voisin)) {
                    rangerNoeudOrdreCroissant(noeuds, distances, voisin, debut, debut + ((fin - debut) / 2));
                } else {
                    rangerNoeudOrdreCroissant(noeuds, distances, voisin, debut + ((fin - debut) / 2), fin);
                }
            }
        }
    }

    /**
     * Trie la liste de noeuds dans l'ordre du noeud le plus proche au noeud le plus
     * loin du noeud de départ
     * 
     * @param noeuds    liste à trier
     * @param distances information de la distance des noeuds par rapport au noeud de
     *                  départ
     * @param voisin    noeud que l'on doit ranger correctement
     */
    private static void actualiserNoeudOrdreCroissant(List<Noeud> noeuds, Map<Noeud, Double> distances, Noeud voisin) {
        noeuds.remove(voisin);
        if (!(noeuds.isEmpty())) {
            rangerNoeudOrdreCroissant(noeuds, distances, voisin, 0, noeuds.size() - 1);
        } else {
            noeuds.add(voisin);
        }
    }

    /**
     * Méthode qui reconstruit le chemin le plus court
     * 
     * @param predecesseurs Map qui associe le noeud précédant le noeud actuel avec
     *                      le chemin le plus court pour accéder au noeud actuel
     * @param debut         noeud de départ
     * @param fin           noeud de fin
     * @return              la liste qui contient le chemin le plus court
     */
    private static Chemin reconstruireChemin(Map<Noeud, Noeud> predecesseurs, Noeud debut, Noeud fin) {
        Chemin chemin = new Chemin();

        // on démarre le chemin par la fin
        Noeud noeudActuel = fin;

        while (noeudActuel != debut) {
            chemin.addNoeud(noeudActuel);
            // on ajoute le prédécesseur le moins loin du noeud de départ
            noeudActuel = predecesseurs.get(noeudActuel);
        }
        // on ajoute le noeud de départ à la fin
        chemin.addNoeud(noeudActuel);
        
        // on retourne la liste pour l'avoir dans le bon ordre
        chemin.reverse();
        chemin.updateIndications();
        chemin.setBat(chemin.getNoeuds().get(0).batiment);
        return chemin;
    }
}