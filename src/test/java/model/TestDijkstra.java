package model;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;


public class TestDijkstra {

    @Test
    public void testTrouverCheminPlusCourt0() {
        int n = 5;
        Noeud[] arbre = new Noeud[n];
        for (int i = 0; i < n; ++i) {
            arbre[i] = new Carrefour(false, 1, null);
        }
        arbre[0].addVoisin(arbre[1], 12.0);
        arbre[0].addVoisin(arbre[2], 3.0);
        arbre[1].addVoisin(arbre[0], 1.0);
        arbre[1].addVoisin(arbre[3], 1.0);
        arbre[1].addVoisin(arbre[4], 1.0);
        arbre[2].addVoisin(arbre[3], 1.0);
        arbre[3].addVoisin(arbre[1], 1.0);

        // cas 0-->4
        int start = 0;
        int end = 4;
        Chemin res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        ArrayList<Noeud> chemin = new ArrayList<Noeud>();
        chemin.add(arbre[0]);
        chemin.add(arbre[2]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[4]);
        Chemin valide = new Chemin(chemin, 6.0);

        assertTrue(res.equals(valide));

        // cas 0-->3 :

        start = 0;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[2]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 0-->2 :

        start = 0;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 0-->1 :

        start = 0;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[2]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);

        valide = new Chemin(chemin, 5.0);

        assertTrue(res.equals(valide));

        // cas 1-->4 :

        start = 1;
        end = 4;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[4]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 1-->3 :

        start = 1;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 1-->2 :

        start = 1;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[0]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 1-->0 :

        start = 1;
        end = 0;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[0]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 2-->4 :

        start = 2;
        end = 4;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[4]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 2-->3 :

        start = 2;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 2-->1 :

        start = 2;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);

        valide = new Chemin(chemin, 2.0);

        assertTrue(res.equals(valide));

        // cas 3-->4 :

        start = 3;
        end = 4;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[4]);

        valide = new Chemin(chemin, 2.0);

        assertTrue(res.equals(valide));

        // cas 3-->2 :

        start = 3;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[0]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 5.0);

        assertTrue(res.equals(valide));

        // cas 3-->1 :

        start = 3;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 4-->? :

        start = 4;
        for (int i = 0; i < 4; ++i) {
            end = i;
            res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

            assertTrue(res == null);
        }
    }

    @Test
    public void testTrouverCheminPlusCourt1() {
        int n = 4;
        Noeud[] arbre = new Noeud[n];
        for (int i = 0; i < n; ++i) {
            arbre[i] = new Carrefour(false, 1, null);
        }
        arbre[0].addVoisin(arbre[1], 1.0);
        arbre[0].addVoisin(arbre[3], 1.0);
        arbre[1].addVoisin(arbre[0], 1.0);
        arbre[1].addVoisin(arbre[2], 3.0);
        arbre[2].addVoisin(arbre[1], 3.0);
        arbre[2].addVoisin(arbre[3], 2.0);
        arbre[3].addVoisin(arbre[0], 1.0);
        arbre[3].addVoisin(arbre[2], 2.0);

        // cas 0-->1 :
        int start = 0;
        int end = 1;
        Chemin res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);
        ArrayList<Noeud> chemin = new ArrayList<Noeud>();

        chemin.add(arbre[0]);
        chemin.add(arbre[1]);

        Chemin valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 0-->2 :

        start = 0;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[3]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 0-->3 :

        start = 0;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[3]);
        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 1-->0 :
        start = 1;
        end = 0;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[0]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 1-->2 :

        start = 1;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 1-->3 :

        start = 1;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[0]);
        chemin.add(arbre[3]);
        valide = new Chemin(chemin, 2.0);

        assertTrue(res.equals(valide));

        // cas 2-->0 :
        start = 2;
        end = 0;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[3]);
        chemin.add(arbre[0]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 2-->1 :

        start = 2;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[1]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 2-->3 :

        start = 2;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[3]);
        valide = new Chemin(chemin, 2.0);

        assertTrue(res.equals(valide));

        // cas 3-->0 :
        start = 3;
        end = 0;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[0]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 3-->1 :

        start = 3;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[0]);
        chemin.add(arbre[1]);

        valide = new Chemin(chemin, 2.0);

        assertTrue(res.equals(valide));

        // cas 3-->2 :

        start = 3;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[2]);
        valide = new Chemin(chemin, 2.0);

        assertTrue(res.equals(valide));

        // cas 0-->0 :

        start = 0;
        end = 0;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        assertTrue(res == null);

        // cas 1-->1 :

        start = 1;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        assertTrue(res == null);

        // cas 2-->2 :

        start = 2;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        assertTrue(res == null);

        // cas 3-->3 :

        start = 3;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        assertTrue(res == null);
    }

    @Test
    public void testTrouverCheminPlusCourt2() {
        int n = 11;
        Noeud[] arbre = new Noeud[n];
        for (int i = 0; i < n; ++i) {
            arbre[i] = new Carrefour(false, 1, null);
        }
        arbre[0].addVoisin(arbre[1], 1.0);
        arbre[0].addVoisin(arbre[2], 3.0);
        arbre[1].addVoisin(arbre[2], 5.0);
        arbre[1].addVoisin(arbre[3], 2.0);
        arbre[1].addVoisin(arbre[4], 7.0);
        arbre[2].addVoisin(arbre[0], 3.0);
        arbre[2].addVoisin(arbre[1], 5.0);
        arbre[2].addVoisin(arbre[5], 2.0);
        arbre[2].addVoisin(arbre[6], 1.0);
        arbre[3].addVoisin(arbre[1], 2.0);
        arbre[3].addVoisin(arbre[4], 2.0);
        arbre[3].addVoisin(arbre[7], 1.0);
        arbre[3].addVoisin(arbre[8], 1.0);
        arbre[4].addVoisin(arbre[9], 3.0);
        arbre[4].addVoisin(arbre[10], 1.0);
        arbre[4].addVoisin(arbre[5], 1.0);
        arbre[5].addVoisin(arbre[4], 1.0);
        arbre[8].addVoisin(arbre[3], 1.0);
        arbre[9].addVoisin(arbre[8], 1.0);

        // cas 0-->1 :
        int start = 0;
        int end = 1;
        Chemin res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);
        ArrayList<Noeud> chemin = new ArrayList<Noeud>();

        chemin.add(arbre[0]);
        chemin.add(arbre[1]);

        Chemin valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 0-->2 :

        start = 0;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 0-->3 :

        start = 0;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 0-->4 :

        start = 0;
        end = 4;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);

        valide = new Chemin(chemin, 5.0);

        assertTrue(res.equals(valide));

        // cas 0-->5 :

        start = 0;
        end = 5;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[2]);
        chemin.add(arbre[5]);

        valide = new Chemin(chemin, 5.0);

        assertTrue(res.equals(valide));

        // cas 0-->6 :

        start = 0;
        end = 6;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[2]);
        chemin.add(arbre[6]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 0-->7 :

        start = 0;
        end = 7;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);
        chemin.add(arbre[7]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 0-->8 :

        start = 0;
        end = 8;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);
        chemin.add(arbre[8]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 0-->9 :

        start = 0;
        end = 9;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);

        valide = new Chemin(chemin, 8.0);

        assertTrue(res.equals(valide));

        // cas 0-->10 :

        start = 0;
        end = 10;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);
        chemin.add(arbre[10]);

        valide = new Chemin(chemin, 6.0);

        assertTrue(res.equals(valide));

        // cas 1-->0 :

        start = 1;
        end = 0;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);
        chemin.add(arbre[0]);

        valide = new Chemin(chemin, 8.0);

        assertTrue(res.equals(valide));

        // cas 1-->2 :

        start = 1;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 5.0);

        assertTrue(res.equals(valide));

        // cas 1-->3 :

        start = 1;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 2.0);

        assertTrue(res.equals(valide));

        // cas 1-->4 :

        start = 1;
        end = 4;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 1-->5 :

        start = 1;
        end = 5;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);
        chemin.add(arbre[5]);

        valide = new Chemin(chemin, 5.0);

        assertTrue(res.equals(valide));

        // cas 1-->6 :

        start = 1;
        end = 6;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);
        chemin.add(arbre[6]);

        valide = new Chemin(chemin, 6.0);

        assertTrue(res.equals(valide));

        // cas 1-->7 :

        start = 1;
        end = 7;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);
        chemin.add(arbre[7]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 1-->8 :

        start = 1;
        end = 8;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);
        chemin.add(arbre[8]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 1-->9 :

        start = 1;
        end = 9;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);

        valide = new Chemin(chemin, 7.0);

        assertTrue(res.equals(valide));

        // cas 1-->10 :

        start = 1;
        end = 10;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);
        chemin.add(arbre[10]);

        valide = new Chemin(chemin, 5.0);

        assertTrue(res.equals(valide));

        // cas 2-->0 :

        start = 2;
        end = 0;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[0]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 2-->1 :

        start = 2;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[0]);
        chemin.add(arbre[1]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 2-->3 :

        start = 2;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[0]);
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 6.0);

        assertTrue(res.equals(valide));

        // cas 2-->4 :

        start = 2;
        end = 4;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 2-->5 :

        start = 2;
        end = 5;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[5]);

        valide = new Chemin(chemin, 2.0);

        assertTrue(res.equals(valide));

        // cas 2-->6 :

        start = 2;
        end = 6;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[6]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 2-->7 :

        start = 2;
        end = 7;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[0]);
        chemin.add(arbre[1]);
        chemin.add(arbre[3]);
        chemin.add(arbre[7]);

        valide = new Chemin(chemin, 7.0);

        assertTrue(res.equals(valide));

        // cas 2-->9 :

        start = 2;
        end = 9;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);

        valide = new Chemin(chemin, 6.0);

        assertTrue(res.equals(valide));

        // cas 2-->10 :

        start = 2;
        end = 10;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);
        chemin.add(arbre[10]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 3-->0 :

        start = 3;
        end = 0;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);
        chemin.add(arbre[0]);

        valide = new Chemin(chemin, 10.0);

        assertTrue(res.equals(valide));

        // cas 3-->1 :

        start = 3;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);

        valide = new Chemin(chemin, 2.0);

        assertTrue(res.equals(valide));

        // cas 3-->2 :

        start = 3;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 7.0);

        assertTrue(res.equals(valide));

        // cas 3-->4 :

        start = 3;
        end = 4;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);

        valide = new Chemin(chemin, 2.0);

        assertTrue(res.equals(valide));

        // cas 3-->5 :

        start = 3;
        end = 5;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);
        chemin.add(arbre[5]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 3-->6 :

        start = 3;
        end = 6;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);
        chemin.add(arbre[6]);

        valide = new Chemin(chemin, 8.0);

        assertTrue(res.equals(valide));

        // cas 3-->7 :

        start = 3;
        end = 7;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[7]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 3-->8 :

        start = 3;
        end = 8;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[8]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 3-->9 :

        start = 3;
        end = 9;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);

        valide = new Chemin(chemin, 5.0);

        assertTrue(res.equals(valide));

        // cas 3-->10 :

        start = 3;
        end = 10;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);
        chemin.add(arbre[10]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 4-->0 :

        start = 4;
        end = 0;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);
        chemin.add(arbre[0]);

        valide = new Chemin(chemin, 15.0);

        assertTrue(res.equals(valide));

        // cas 4-->1 :

        start = 4;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);

        valide = new Chemin(chemin, 7.0);

        assertTrue(res.equals(valide));

        // cas 4-->2 :

        start = 4;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 12.0);

        assertTrue(res.equals(valide));

        // cas 4-->3 :

        start = 4;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 5.0);

        assertTrue(res.equals(valide));

        // cas 4-->5 :

        start = 4;
        end = 5;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[4]);
        chemin.add(arbre[5]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 4-->6 :

        start = 4;
        end = 6;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);
        chemin.add(arbre[6]);

        valide = new Chemin(chemin, 13.0);

        assertTrue(res.equals(valide));

        // cas 4-->7 :

        start = 4;
        end = 7;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[7]);

        valide = new Chemin(chemin, 6.0);

        assertTrue(res.equals(valide));

        // cas 4-->8 :

        start = 4;
        end = 8;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 4-->9 :

        start = 4;
        end = 9;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 4-->10 :

        start = 4;
        end = 10;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[4]);
        chemin.add(arbre[10]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 5-->0 :

        start = 5;
        end = 0;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);
        chemin.add(arbre[0]);

        valide = new Chemin(chemin, 16.0);

        assertTrue(res.equals(valide));

        // cas 5-->1 :

        start = 5;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);

        valide = new Chemin(chemin, 8.0);

        assertTrue(res.equals(valide));

        // cas 5-->2 :

        start = 5;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 13.0);

        assertTrue(res.equals(valide));

        // cas 5-->3 :

        start = 5;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 6.0);

        assertTrue(res.equals(valide));

        // cas 5-->4 :

        start = 5;
        end = 4;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 5-->6 :

        start = 5;
        end = 6;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);
        chemin.add(arbre[6]);

        valide = new Chemin(chemin, 14.0);

        assertTrue(res.equals(valide));

        // cas 5-->7 :

        start = 5;
        end = 7;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[7]);

        valide = new Chemin(chemin, 7.0);

        assertTrue(res.equals(valide));

        // cas 5-->8 :

        start = 5;
        end = 8;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);

        valide = new Chemin(chemin, 5.0);

        assertTrue(res.equals(valide));

        // cas 5-->9 :

        start = 5;
        end = 9;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 5-->10 :

        start = 5;
        end = 10;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);
        chemin.add(arbre[10]);

        valide = new Chemin(chemin, 2.0);

        assertTrue(res.equals(valide));

        // cas 6 -> ?
        start = 6;
        for (int i = 0; i <= 10; ++i) {
            end = i;
            res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);
            assertTrue(res == null);
        }

        // cas 7 -> ?
        start = 7;
        for (int i = 0; i <= 10; ++i) {
            end = i;
            res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);
            assertTrue(res == null);
        }

        // cas 8-->0 :

        start = 8;
        end = 0;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);
        chemin.add(arbre[0]);

        valide = new Chemin(chemin, 11.0);

        assertTrue(res.equals(valide));

        // cas 8-->1 :

        start = 8;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 8-->2 :

        start = 8;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 8.0);

        assertTrue(res.equals(valide));

        // cas 8-->3 :

        start = 8;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 8-->4 :

        start = 8;
        end = 4;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 8-->5 :

        start = 8;
        end = 5;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);
        chemin.add(arbre[5]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 8-->6 :

        start = 8;
        end = 6;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);
        chemin.add(arbre[6]);

        valide = new Chemin(chemin, 9.0);

        assertTrue(res.equals(valide));

        // cas 8-->7 :

        start = 8;
        end = 7;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[7]);

        valide = new Chemin(chemin, 2.0);

        assertTrue(res.equals(valide));

        // cas 8-->9 :

        start = 8;
        end = 9;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);
        chemin.add(arbre[9]);

        valide = new Chemin(chemin, 6.0);

        assertTrue(res.equals(valide));

        // cas 8-->10 :

        start = 8;
        end = 10;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);
        chemin.add(arbre[10]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 9-->0 :

        start = 9;
        end = 0;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);
        chemin.add(arbre[0]);

        valide = new Chemin(chemin, 12.0);

        assertTrue(res.equals(valide));

        // cas 9-->1 :

        start = 9;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 9-->2 :

        start = 9;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 9.0);

        assertTrue(res.equals(valide));

        // cas 9-->3 :

        start = 9;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 2.0);

        assertTrue(res.equals(valide));

        // cas 9-->4 :

        start = 9;
        end = 4;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 9-->5 :

        start = 9;
        end = 5;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);
        chemin.add(arbre[5]);

        valide = new Chemin(chemin, 5.0);

        assertTrue(res.equals(valide));

        // cas 9-->6 :

        start = 9;
        end = 6;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);
        chemin.add(arbre[6]);

        valide = new Chemin(chemin, 10.0);

        assertTrue(res.equals(valide));

        // cas 9-->7 :

        start = 9;
        end = 7;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[7]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 9-->8 :

        start = 9;
        end = 8;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 9-->10 :

        start = 9;
        end = 10;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[9]);
        chemin.add(arbre[8]);
        chemin.add(arbre[3]);
        chemin.add(arbre[4]);
        chemin.add(arbre[10]);

        valide = new Chemin(chemin, 5.0);

        assertTrue(res.equals(valide));

        // cas 10 -> ?
        start = 10;
        for (int i = 0; i <= 10; ++i) {
            end = i;
            res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);
            assertTrue(res == null);
        }
    }

    @Test
    public void testTrouverCheminPlusCourt3() {
        int n = 6;
        Noeud[] arbre = new Noeud[n];
        for (int i = 0; i < n; ++i) {
            arbre[i] = new Carrefour(false, 1, null);
        }
        arbre[0].addVoisin(arbre[1], 1.0);
        arbre[0].addVoisin(arbre[5], 3.0);
        arbre[1].addVoisin(arbre[2], 4.0);
        arbre[1].addVoisin(arbre[5], 3.0);
        arbre[2].addVoisin(arbre[3], 3.0);
        arbre[4].addVoisin(arbre[3], 1.0);
        arbre[4].addVoisin(arbre[2], 2.0);
        arbre[5].addVoisin(arbre[4], 1.0);

        // cas 0-->1 :

        int start = 0;
        int end = 1;
        Chemin res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        ArrayList<Noeud> chemin = new ArrayList<Noeud>();
        chemin.add(arbre[0]);
        chemin.add(arbre[1]);

        Chemin valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 0-->2 :

        start = 0;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 5.0);

        assertTrue(res.equals(valide));

        // cas 0-->3 :

        start = 0;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 5.0);

        assertTrue(res.equals(valide));

        // cas 0-->4 :

        start = 0;
        end = 4;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 0-->5 :

        start = 0;
        end = 5;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[5]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas ?-->0 :

        end = 0;
        for (int i = 0; i <= 5; ++i) {
            start = i;
            res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

            assertTrue(res == null);
        }

        // cas 1-->2 :

        start = 1;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 1-->3 :

        start = 1;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 5.0);

        assertTrue(res.equals(valide));

        // cas 1-->4 :

        start = 1;
        end = 4;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 1-->5 :

        start = 1;
        end = 5;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[5]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 2-->? :

        start = 2;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        start = 2;
        for (int i = 0; i <= 5; ++i) {
            if (i == 3) {
                ++i;
            }
            end = i;
            res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

            assertTrue(res == null);
        }

        // cas 3-->? :

        start = 3;
        for (int i = 0; i <= 5; ++i) {
            end = i;
            res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

            assertTrue(res == null);
        }

        // cas 4-->? :

        start = 4;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[4]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 2.0);

        assertTrue(res.equals(valide));

        start = 4;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[4]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        start = 2;
        for (int i = 0; i <= 5; ++i) {
            if (i == 2) {
                i += 2;
            }
            end = i;
            res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

            assertTrue(res == null);
        }

        // cas 5-->1 :

        start = 5;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        assertTrue(res == null);

        // cas 5-->2 :

        start = 5;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 5-->3 :

        start = 5;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 2.0);

        assertTrue(res.equals(valide));

        // cas 5-->4 :

        start = 5;
        end = 4;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[5]);
        chemin.add(arbre[4]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));
    }

    @Test
    /**
     * Tests ascenseurs
     */
    public void testTrouverCheminPlusCourt4() {
        int n = 4;
        Noeud[] arbre = new Noeud[n];
        for (int i = 0; i < 2; ++i) {
            arbre[i] = new Carrefour(true, 1, null);
        }
        for (int i = 2; i < n; ++i) {
            arbre[i] = new Carrefour(false, 1, null);
        }
        arbre[0].addVoisin(arbre[1], 1.0);
        arbre[0].addVoisin(arbre[3], 2.0);
        arbre[1].addVoisin(arbre[0], 1.0);
        arbre[1].addVoisin(arbre[2], 2.0);
        arbre[2].addVoisin(arbre[1], 2.0);
        arbre[2].addVoisin(arbre[3], 2.0);
        arbre[3].addVoisin(arbre[0], 2.0);
        arbre[3].addVoisin(arbre[2], 2.0);

        // cas 0-->1 ascenseur : true :
        int start = 0;
        int end = 1;
        Chemin res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], true);
        ArrayList<Noeud> chemin = new ArrayList<Noeud>();

        chemin.add(arbre[0]);
        chemin.add(arbre[1]);

        Chemin valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 0-->2 ascenseur : false :

        start = 0;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[3]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 0-->2 ascenseur : true :

        start = 0;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], true);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 3-->1 ascenseur : false :

        start = 3;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        assertTrue(res == null);

        // cas 3-->1 ascenseur : true :

        start = 3;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], true);

        chemin.clear();
        chemin.add(arbre[3]);
        chemin.add(arbre[0]);
        chemin.add(arbre[1]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 1-->3 ascenseur : false :

        start = 1;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 4.0);

        assertTrue(res.equals(valide));

        // cas 3-->1 ascenseur : true :

        start = 1;
        end = 3;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], true);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[0]);
        chemin.add(arbre[3]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));
    }

    @Test
    /**
     * Tests salles
     */
    public void testTrouverCheminPlusCourt5() {
        int n = 3;
        Noeud[] arbre = new Noeud[n];
        arbre[0] = new Salle("A", null);
        for (int i = 1; i < n; ++i) {
            arbre[i] = new Carrefour(false, 1, null);
        }
        arbre[0].addVoisin(arbre[1], 1.0);
        arbre[0].addVoisin(arbre[2], 1.0);
        arbre[1].addVoisin(arbre[0], 1.0);
        arbre[1].addVoisin(arbre[2], 3.0);
        arbre[2].addVoisin(arbre[1], 3.0);
        arbre[2].addVoisin(arbre[0], 1.0);

        // cas 0-->1 :
        int start = 0;
        int end = 1;
        Chemin res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);
        ArrayList<Noeud> chemin = new ArrayList<Noeud>();

        chemin.add(arbre[0]);
        chemin.add(arbre[1]);

        Chemin valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 0-->2 :

        start = 0;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[0]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 1-->0 :

        start = 1;
        end = 0;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[0]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 1-->2 :

        start = 1;
        end = 2;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[1]);
        chemin.add(arbre[2]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));

        // cas 2-->0 :

        start = 2;
        end = 0;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[0]);

        valide = new Chemin(chemin, 1.0);

        assertTrue(res.equals(valide));

        // cas 2-->1 :

        start = 2;
        end = 1;
        res = Dijkstra.trouverCheminPlusCourt(arbre[start], arbre[end], false);

        chemin.clear();
        chemin.add(arbre[2]);
        chemin.add(arbre[1]);

        valide = new Chemin(chemin, 3.0);

        assertTrue(res.equals(valide));
    }

}