package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestParseur {
    
    /**
     * Teste la fonction createNoeuds()
     */
    @Test
    public void testCreateNoeuds(){
        Batiment bat = new Batiment(0, 5, "src/main/ressources/csv/test/");
        
        bat.noeuds.clear();
        for(int i = bat.getMin(); i<= bat.getMax(); i++){
            bat.getEtage(i).getNoeuds().clear();
        }
        bat.setNbNoeuds(0);
        Parseur p = new Parseur(bat, "src/main/ressources/csv/test/");
        p.createNoeuds();
        
        // On crée les éléments de comparaison
        List<Noeud> batiment = new ArrayList<>();

        List<Noeud> etage0 = new ArrayList<>();
        Carrefour c1 = new Carrefour(false, 0, bat);
        c1.setId(0);
        etage0.add(c1);
        batiment.add(c1);
        
        Salle s1 = new Salle("01", bat);
        s1.setId(1);
        batiment.add(s1);

        List<Noeud> etage1 = new ArrayList<>();
        Carrefour c2 = new Carrefour(true, 1, bat);
        c2.setId(2);
        etage1.add(c2);
        batiment.add(c2);

        List<Noeud> etage2 = new ArrayList<>();
        Salle s2 = new Salle("205", bat);
        s2.setId(3);
        batiment.add(s2);

        List<Noeud> etage3 = new ArrayList<>();
        Salle s3 = new Salle("308", bat);
        s3.setId(4);
        batiment.add(s3);

        List<Noeud> etage4 = new ArrayList<>();

        List<Noeud> etage5 = new ArrayList<>();
        Carrefour c3 = new Carrefour(true, 5, bat);
        c3.setId(5);
        etage5.add(c3);
        batiment.add(c3);
        Carrefour c4 = new Carrefour(false, 5, bat);
        c4.setId(6);
        etage5.add(c4);
        batiment.add(c4);

        
        //on compare les éléments créés aux évènements attendus

        //on compare les listes de noeuds des étages un à un
        checkListeNoeuds(etage0, bat.getEtage(0).getNoeuds());
        checkListeNoeuds(etage1, bat.getEtage(1).getNoeuds());
        checkListeNoeuds(etage2, bat.getEtage(2).getNoeuds());
        checkListeNoeuds(etage3, bat.getEtage(3).getNoeuds());
        checkListeNoeuds(etage4, bat.getEtage(4).getNoeuds());
        checkListeNoeuds(etage5, bat.getEtage(5).getNoeuds());

        //on compare la liste de noeuds du batiment
        checkListeNoeuds(batiment, bat.noeuds);
    }

    /**
     * Teste que {@code attendu} et {@code reel} contiennent les mêmes noeuds
     * @param attendu
     * @param reel
     */
    private void checkListeNoeuds(List<Noeud> attendu, List<Noeud> reel){
        assertTrue(attendu.size() == reel.size());
        for(int i = 0; i< attendu.size(); i++){
            assertEquals(attendu.get(i), reel.get(i));
        }
    }
    
    /**
     * Teste la fonction initVoisins()
     */
    @Test
    public void testInitVoisins(){
        Batiment bat = new Batiment(0, 5, "src/main/ressources/csv/test/");
        
        //on crée les éléments attendus

        //on ajoute d'abord les noeuds au batiment
        List<Noeud> batiment = new ArrayList<>();
        Carrefour c0 = new Carrefour(false, 0, bat);
        batiment.add(c0);
        Salle s1 = new Salle("01", bat);
        batiment.add(s1);
        Carrefour c2 = new Carrefour(true, 1, bat);
        batiment.add(c2);
        Salle s3 = new Salle("205", bat);
        batiment.add(s3);
        Salle s4 = new Salle("308", bat);
        batiment.add(s4);
        Carrefour c5 = new Carrefour(true, 5, bat);
        batiment.add(c5);
        Carrefour c6 = new Carrefour(false, 5, bat);
        batiment.add(c6);

        //on ajoute les liens qui devraient être créés
        c0.addVoisin(s1, 3.0);
        c0.addVoisin(s4, 5.8);
        s1.addVoisin(c0, 3.0);
        c2.addVoisin(c5, 8.0);
        s3.addVoisin(c6, 1.0);
        s4.addVoisin(c0,5.8);
        c5.addVoisin(c2, 8.0);
        c5.addVoisin(c6, 2.0);
        c6.addVoisin(s3, 1.0);
        c6.addVoisin(c5, 2.0);
        
        //On initialise les éléments que l'on va comparer
        Parseur p = new Parseur(bat,"src/main/ressources/csv/test/");
        p.createNoeuds(); //déjà testée
        p.initVoisins(); //fonction que l'on teste
        
        //on compare les éléments créés aux évènements attendus
        for(int i = 0; i<batiment.size(); i++){
            Noeud reel = bat.getNoeud(i);
            Noeud attendu = batiment.get(i);

            attendu.compareVoisins(reel);
        }
    }
}
