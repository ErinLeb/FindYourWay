package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestParseur {
    
    /**
     * Teste la fonction createnoeuds()
     */
    @Test
    public void testCreateNoeuds(){
        // on initialise les éléments dont on a besoin
        Batiment bat = new Batiment(0, 5, "src/main/ressources/csv/test/");
        Parseur p = new Parseur(bat,"src/main/ressources/csv/test/");

        p.createNoeuds();

        // On crée les éléments de comparaison
        List<Noeud> batiment = new ArrayList<>();

        List<Noeud> etage0 = new ArrayList<>();
        Carrefour c1 = new Carrefour(false, 0, bat);
        etage0.add(c1);
        batiment.add(c1);
        
        batiment.add(new Salle("01", bat));

        List<Noeud> etage1 = new ArrayList<>();
        Carrefour c2 = new Carrefour(true, 1, bat);
        etage1.add(c2);
        batiment.add(c2);

        List<Noeud> etage2 = new ArrayList<>();
        batiment.add(new Salle("205", bat));

        List<Noeud> etage3 = new ArrayList<>();
        batiment.add(new Salle("308", bat));

        List<Noeud> etage4 = new ArrayList<>();

        List<Noeud> etage5 = new ArrayList<>();
        Carrefour c3 = new Carrefour(true, 5, bat);
        etage5.add(c3);
        batiment.add(c3);
        Carrefour c4 = new Carrefour(false, 5, bat);
        etage5.add(c4);
        batiment.add(c4);

        //on compare les éléments créés et les évènements attendus

        //on compare la liste de noeuds du batiment 
        int i = 0;
        for(Noeud n : batiment){
            assertEquals(n, bat.getNoeud(i));
            ++i;
        }

        //on compare les étages un à un
        i = 0;
        for(Noeud n : etage0){
            assertEquals(n, bat.getEtage(0).getIndNoeud(i));
            ++i;
        }

        i = 0;
        for(Noeud n : etage1){
            assertEquals(n, bat.getEtage(1).getIndNoeud(i));
            ++i;
        }

        i = 0;
        for(Noeud n : etage2){
            assertEquals(n, bat.getEtage(2).getIndNoeud(i));
            ++i;
        }

        i = 0;
        for(Noeud n : etage3){
            assertEquals(n, bat.getEtage(3).getIndNoeud(i));
            ++i;
        }
        
        i = 0;
        for(Noeud n : etage4){
            assertEquals(n, bat.getEtage(4).getIndNoeud(i));
            ++i;
        }
        
        i = 0;
        for(Noeud n : etage5){
            assertEquals(n, bat.getEtage(5).getIndNoeud(i));
            ++i;
        }
    }
    
    /**
     * Teste la fonction initVoisins()
     */
    @Test
    public void testInitVoisins(){
        // on initialise les éléments dont on a besoin
        Batiment bat = new Batiment(0, 5, "src/main/ressources/csv/test/");
        Parseur p = new Parseur(bat,"src/main/ressources/csv/test/");

        p.createNoeuds(); //déjà testée

        p.initVoisins(); //fonction que l'on teste

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


        //on compare les éléments créés et les évènements attendus
        for(int i = 0; i<batiment.size(); i++){
            Noeud reel = bat.getNoeud(i);
            Noeud attendu = batiment.get(i);

            assertEquals(reel.voisins, attendu.voisins);
        }
    }
}
