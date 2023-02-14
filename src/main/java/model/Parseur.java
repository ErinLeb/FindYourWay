package model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Parseur {
    /**
     * le scanner utilisé pour parcourir le fichier
     */
    private Scanner sc;
    /**
     * le batîment que l'on veut remplir
     */
    private Batiment bat;

    public Parseur(Batiment bat, String path) {
        //on initialise le scanner
        this.bat = bat;
        this.sc = null;
        try {
            sc = new Scanner(new File(path));
        } catch (IOException e) {
            System.out.print("Erreur lors de l'ouverture du fichier");
            e.printStackTrace();
            System.exit(1);
        }
        
    }

    /**
     * Permet de lire un fichier .csv contenant les informations des noeuds
     */
    public void initNoeuds() {
        
        //on parcourt le fichier par ligne
        Noeud current = null; //le noeud dont on lit les informations
        while (sc.hasNext()) {
            String[] line = sc.nextLine().split(";");
            if (line[0].equals("")) { //si on a un nouveau noeud
                int number = Integer.parseInt(line[1]); //la place du noeud dans le tableau du bâtiment
                current = bat.getNoeud(number);
            } else { //si on parcourt les voisins d'un noeud
                Noeud voisin = bat.getNoeud(Integer.parseInt(line[0])); //l'un des voisins du noeud current
                Double distance = Double.parseDouble(line[1]); // la distance du voisin avec current
                current.addVoisin(voisin, distance);
            }
        }
    }
}
