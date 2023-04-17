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

    /**
     * Indications correspondant au chemin
     */
    private String indications;

    /*
     * Batiment des noeuds du chemin
     */
    private Batiment bat;


    // Constructeurs
    public Chemin() {
        noeuds = new ArrayList<>();
        distance = 0;
        bat = null;
    }

    public Chemin(Noeud depart) {
        this();
        noeuds.add(depart);
        bat = depart.batiment;
    }

    public Chemin(List<Noeud> noeuds, double distance) {
        this.noeuds = noeuds;
        this.distance = distance;
        bat = noeuds.get(0).batiment;

        updateIndications();
    }


    // Getters & setters

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

    public String getIndications(){
        return indications;
    }

    public void setBat(Batiment bat){
        this.bat = bat;
    }


    //Classe interne statique

    /*
     * Représente le segment d'un carrefour à un autre
     */
    static class Segment{
        //Attributs
        /*Noeuds du segment */
        protected Carrefour n1, n2;
        /*Indique la direction du segment */
        protected boolean horizontal;
        /*Indique le nombre d'étages qui séparent les noeuds */
        protected int diffEtage;
        /*Distance entre n1 et n2 selon sa direction */
        protected double distance;

        //Constructeur
        public Segment(Carrefour n1, Carrefour n2){
            this.n1 = n1;
            this.n2 = n2;
            diffEtage = n2.getEtage() - n1.getEtage();
            initDirection();
            initDistance();
        }

        //Méthodes

        /*Détermine si le segment est horizontal ou vertical */
        private void initDirection(){
            if(Math.abs(n2.getX()-n1.getX()) >= Math.abs(n2.getY()-n1.getY())){
                horizontal = true;
            }else{
                horizontal = false;
            }
        }

        /*Calcule la distance entre les deux noeuds selon la direction du segment */
        private void initDistance(){
            double abs = Math.pow(n2.getX()-n1.getX(), 2);
            double ord = Math.pow(n2.getY()-n1.getY(), 2);
            distance = Math.sqrt(abs+ord);
        }
    }


    // Méthodes

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Chemin)){
            return false;
        }
        Chemin c = (Chemin)o;
        return this.noeuds.equals(c.noeuds) && this.distance == c.distance;
    }

    /**
     * Met à jour les indications correspondant au chemin
     */
    public void updateIndications(){
        if(noeuds.size() < 5){
            indications = "";
            return;
        }

        indications = "C'est parti ! \n";
        
        //le seuil minimal des longueurs de ligne droite à afficher
        double minDist = 0;
        if(bat != null){
            minDist = 1/bat.getEchelle(); //1 mètre environ
        }

        //on part du principe que le premier et le dernier noeud sont des salles
        
        int comptEtage = 0; //le nombre d'étage qu'on est en train de monter ou descendre
        double comptDist = 0; //la distance de la ligne droite en train d'être parcourue

        Segment s1 = null;
        Segment s2 = null;
        for(int i = 3; i<noeuds.size()-1; i++){
            //Mise à jour des segments
            if(i==3){
                s1 = new Segment((Carrefour)noeuds.get(1), (Carrefour)noeuds.get(2));
                s2 = new Segment((Carrefour)noeuds.get(2), (Carrefour)noeuds.get(3));
            }else{
                s1 = s2;
                s2 = new Segment(s1.n2, (Carrefour)noeuds.get(i));    
            }

            //Liste des cas de figure :

            //on va changer d'étage
            if(s1.diffEtage == 0 && s2.diffEtage != 0){
                comptDist += s1.distance;
                if(comptDist >= minDist){
                    indications += "Avancez de ";
                    indications += conversionMetres(comptDist);
                    indications += " mètres.\n";
                }
                comptDist = 0;
            }

            //on est en train de changer d'étage (escalier ou ascenseur)
            else if(s1.diffEtage != 0 && s2.diffEtage != 0){
                comptEtage += s1.diffEtage;
            }

            //on sort d'un escalier ou d'un ascenseur
            else if(s1.diffEtage != 0 && s2.diffEtage == 0){
                comptEtage += s1.diffEtage;
                if(s2.n1.isAscenseur){
                    indications += "Prenez l'ascenseur et ";
                }else{
                    indications += "Prenez l'escalier et ";
                }
                if(comptEtage < 0){
                    indications += "descendez de " + Math.abs(comptEtage) + " étages.\n";
                }else{
                    indications += "montez de " + comptEtage + " étages.\n";
                }
                comptEtage = 0;
            }

            //si on reste sur le même étage

            //si on est sur une ligne droite
            else if(s1.horizontal == s2.horizontal){
                comptDist += Math.abs(s1.distance);
                if(i == noeuds.size()-2 && comptDist != 0){ 
                    if(comptDist >= minDist){
                        indications += "Avancez de ";
                        indications += conversionMetres(comptDist);
                        indications += " mètres.\n";
                    }
                }
            }

            //si on tourne
            else{
                comptDist += Math.abs(s1.distance);
                if(comptDist >= minDist){
                    indications += "Avancez de ";
                    indications += conversionMetres(comptDist);
                    indications += " mètres.\n";
                }
                comptDist = 0;
                
                //on gère les différents cas

                //on est à l'horizontal sur le plan
                if(s1.horizontal){
                    if(s1.n1.getX()<s1.n2.getX()){ // --->
                        if(s2.n1.getY() > s2.n2.getY()){
                            indications += "Tournez à gauche. \n";
                        }else{
                            indications += "Tournez à droite. \n";
                        }
                    }else{ // <---
                        if(s2.n1.getY() > s2.n2.getY()){
                            indications += "Tournez à droite. \n";
                        }else{
                            indications += "Tournez à gauche. \n";
                        }
                    }

                //on est à la verticale sur le plan
                }else{
                    if(s1.n1.getY() < s1.n2.getY()){
                        if(s2.n1.getX() < s2.n2.getX()){
                            indications += "Tournez à gauche. \n";
                        }else{
                            indications += "Tournez à droite. \n";
                        }
                    }else{
                        if(s2.n1.getX() < s2.n2.getX()){
                            indications += "Tournez à droite. \n";
                        }else{
                            indications += "Tournez à gauche. \n";
                        }
                    }
                }
            }
        }

        indications += "Vous êtes arrivé !\n";
    }

    /**
     * Ajoute le noeud {@code n} au chemin et met à jour les indications
     * @param n
     */
    public void addNoeud(Noeud n) {
        if (!noeuds.isEmpty()) {
            distance += n.getVoisins().get(noeuds.get(noeuds.size() - 1));
        }
        noeuds.add(n);
        if(bat == null){
            bat = n.batiment;
        }

        updateIndications();
    }

    /**
     * Renverse le chemin
     */
    public void reverse() {
        Collections.reverse(noeuds);
    }

    /**
     * Retourne la distance en mètres correspondant à la distance sur le plan, selon l'échelle du batiment
     * @param dist
     * @return la distance en mètres correspondant à la distance sur le plan, selon l'échelle du batiment
     */
    private String conversionMetres(double dist){
        return conversionMetres(dist, bat.getEchelle());
    }

    /**
     * Retourne la distance en mètres correspondant {@code echelle}
     * @param dist
     * @param echelle
     * @return la distance en mètres correspondant à l'échelle
     */
    protected static String conversionMetres(double dist, double echelle){
        String res = "";
        res += Math.round((dist)*echelle);
        return res;
    }
}