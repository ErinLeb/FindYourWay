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
     * Permet d'ajouter des salles à {@code batiment} en lisant un fichier .csv contenant les informations des salles
     */
    public void initSalles(){
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path + "salles.csv"));
        } catch (IOException e) {
            System.out.print("Erreur lors de l'ouverture du fichier salles.csv");
            e.printStackTrace();
            System.exit(1);
        }

        //on parcourt le fichier par ligne
        Etage current = null;

        while(sc.hasNext()){
            String[] line = sc.nextLine().split(";");
            if (line[0].equals("")) { //si on a un nouvel étage
                int number = Integer.parseInt(line[1]); //le numéro d'étage du batiment
                current = bat.getEtage(number);
            } else { //si on parcourt un étage
                current.addSalle(new Salle(line[0]));
            }
        }
        sc.close();
    }

    /**
     * Permet d'ajouter à {@code batiment} des noeuds en lisant un fichier .csv contenant les informations des noeuds
     */
    public void createNoeuds(){
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path + "noeuds.csv"));
        } catch (IOException e) {
            System.out.print("Erreur lors de l'ouverture du fichier noeuds.csv");
            e.printStackTrace();
            System.exit(1);
        }

        Noeud current = null; //le noeud dont on lit les informations
        int etage = 0;
        while (sc.hasNext()) {
            String[] line = sc.nextLine().split(";");
            if (line[0].equals("")) { //si on a un nouvel étage
                etage = Integer.parseInt(line[1]); // etage du noeud
            }else{
                boolean porte = line[0].equals("true");
                if(porte){ //c'est une porte
                    current = new Porte(line[1], etage, bat);

                }else{ //c'est un carrefour
                    boolean ascenseur = !line[1].equals("false");
                    current = new Carrefour(ascenseur, etage, bat);
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
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path + "voisins.csv"));
        } catch (IOException e) {
            System.out.print("Erreur lors de l'ouverture du fichier voisins.csv");
            e.printStackTrace();
            System.exit(1);
        }

        //on parcourt le fichier par ligne
        Noeud current = null; //le noeud dont on lit les informations
        
        while (sc.hasNext()) {
            String[] line = sc.nextLine().split(";");

            if (line[0].equals("")) { //si on a un nouveau noeud
                int number = Integer.parseInt(line[1]); //la place du noeud dans la liste du batiment
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
