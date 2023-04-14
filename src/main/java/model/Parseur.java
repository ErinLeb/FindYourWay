package model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Parseur {
    // Attributs

    /**
     * le chemin vers le dossier de csv
     */
    private String path; 

    /**
     * le batîment que l'on veut remplir
     */
    private Batiment bat;


    // Constructeur

    public Parseur(Batiment bat, String path) { 
        this.bat = bat;
        this.path = path;
    }


    // Méthodes

    /**
     * Permet d'ajouter à {@code batiment} des noeuds en lisant un fichier .csv contenant les informations des noeuds
     */
    public void createNoeuds(){

        // on initialise le scanner
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path + "noeuds.csv"));
        } catch (IOException e) {
            System.out.print("Erreur lors de l'ouverture du fichier noeuds.csv");
            e.printStackTrace();
            System.exit(1);
        }

        Noeud current = null; //le noeud dont on lit les informations
        int etage = bat.min;

        //on parcourt le fichier par ligne
        while (sc.hasNext()) {
            String[] line = sc.nextLine().split(";");
            if (line[0].equals("")) { //si on a un nouvel étage
                etage = Integer.parseInt(line[1]); // etage du noeud

            }else{
                if(line[0].equals("salle")){ //si c'est une salle
                    current = new Salle(line[1], bat);

                }else if (line[0].equals("carrefour")) { // si c'est un carrefour
                    Double x = 0.0;
                    Double y = 0.0;
                    if (line.length > 2 && line[2].length() != 0) {
                        x = Double.valueOf(line[2]);
                    }
                    if (line.length > 3 && line[3].length() != 0) {
                        y = Double.valueOf(line[3]);
                    }
                    if (line.length > 1 && line[1].equals("ascenseur")) {
                        current = new Carrefour(true, etage, bat, x, y);
                    } else {
                        current = new Carrefour(false, etage, bat, x, y);
                    }

                }else{ //sinon ce n'est pas valide, on passe au noeud suivant
                    continue;
                }

                //on ajoute le noeud
                bat.addNoeud(current);            
            }
        }
        sc.close();
    }

    /**
     * Permet de lier les noeuds de {@code batiment} entre eux en lisant un fichier .csv contenant les informations des voisins
     */
    public void initVoisins() {

        // on initialise le scanner
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path + "voisins.csv"));
        } catch (IOException e) {
            System.out.print("Erreur lors de l'ouverture du fichier voisins.csv");
            e.printStackTrace();
            System.exit(1);
        }

        Noeud current = null; //le noeud dont on lit les informations
        
        //on parcourt le fichier par ligne
        while (sc.hasNext()) {
            String[] line = sc.nextLine().split(";");

            if (line[0].equals("")) { //si on a un nouveau noeud
                int number = Integer.parseInt(line[1]); //l'identifiant du noeud dans la liste de noeuds du batiment
                current = bat.getNoeud(number);

            } else { //si on parcourt les voisins d'un noeud
                Noeud voisin = bat.getNoeud(Integer.parseInt(line[0])); //l'un des voisins du noeud current
                Double distance = Double.parseDouble(line[1]); // la distance du voisin avec current
                current.addVoisin(voisin, distance);
            }
        }

        sc.close();
    }

}