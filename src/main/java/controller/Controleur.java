package controller;

import java.util.List;
import java.awt.Color;

import model.Batiment;
import model.Binome;
import model.Etage;
import model.Noeud;
import model.Salle;
import view.Vue;


/**
 * Gère les interactions entre la vue et le modèle
 */
public class Controleur {
    // Attributs

    /**
     * Vue associée au controleur
     */
    private Vue vue;

    /**
     * le bâtiment sur lequel la vue est centrée
     */
    private Batiment batActuel;


    // Constructeur

    public Controleur() { 
        this.vue = new Vue(this);

        batActuel.getViewFavoris().setcontroleur(this);
    }


    // Getters & setters

    /**
     * Renvoie l'étage maximum du bâtiment sur lequel la vue est centrée
     * 
     * @return l'étage maximum du bâtiment sur lequel la vue est centrée
     */
    public int getEtageMaxActuel() {
        return batActuel.getMax();
    }

    /**
     * Renvoie l'étage minimum du bâtiment sur lequel la vue est centrée
     * 
     * @return l'étage minimum du bâtiment sur lequel la vue est centrée
     */
    public int getEtageMinActuel() {
        return batActuel.getMin();
    }

    /**
     * Renvoie le bâtiment actuel
     * 
     * @return le bâtiment actuel
     */
    public Batiment getBatiment() {
        return batActuel;
    }

    /**
     * Renvoie la salle qui a pour nom {@code salle}
     * 
     * @param salle nom de la salle à trouver
     * @return      le noeud de la salle qui a pour nom {@code salle}
     */
    private Noeud getSalle(String salle) {
        return batActuel.getSalle(salle);
    }

    /**
     * Renvoie la vue du controleur
     * 
     * @return la vue du controleur
     */
    public Vue getVue() {
        return vue;
    }

    /**
     * Renvoie la liste des noeuds de l'étage numéro {@code numEtage}
     * 
     * @param numEtage le numéro de l'étage
     * @return         la liste de ses noeuds
     */
    public List<Noeud> getNoeudsEtage(int numEtage) {
        Etage etage = batActuel.getEtage(numEtage);

        return etage.getNoeuds();
    }

    /**
     * Change le bâtiment actuel par {@code bat} et si la vue est déjà initialisée, change sa liste d'images de plans
     * @param bat nouveau bâtiment
     */
    public void setBatiment(Batiment bat){ 
        this.batActuel = bat;
        if(vue != null){
            vue.initListImages();
        }
    }


    //Méthodes

    /**
     * Vérifie que les textes contenus dans les TextFieldBox sont soit des noms de
     * salles, soit "Départ", soit "Arrivée", soit des caractères blancs (espaces, tabulations...)
     * 
     * @return true si les textes dans les TextFieldBox sont soit des noms de
     *         salles, soit "Départ", soit "Arrivée", soit des caractères blancs (espaces, tabulations...),
     *         false sinon
     */
    public boolean verifGoButton() {
        String start = vue.getApp().getStart().getText();
        String finish = vue.getApp().getFinish().getText();
        if((start.equalsIgnoreCase("toilettes") || start.equalsIgnoreCase("wc") || start.equalsIgnoreCase("café") || start.equalsIgnoreCase("cafe")) && 
        (!finish.equalsIgnoreCase(start) && !finish.equalsIgnoreCase("Arrivée") && !isBlank(finish))){
            return false;
        }
        if((start.equalsIgnoreCase("Départ") || estSalle(start) || isBlank(start)) 
        && (finish.equalsIgnoreCase("Arrivée") || estSalle(finish) || isBlank(finish))){
            return true;
        }
        return false;
    }

    /**
     * Change la couleur des textes invalides des TextFieldBox en rouge
     */
    public void erreurTexte() {
        String start = vue.getApp().getStart().getText();
        String finish = vue.getApp().getFinish().getText();
        if(!(start.equalsIgnoreCase("Départ") || estSalle(start) || isBlank(start))){
            vue.getApp().getStart().setForeground(Color.RED);
        }
        if(!(finish.equalsIgnoreCase("Arrivée") || estSalle(finish) || isBlank(finish))){
            vue.getApp().getFinish().setForeground(Color.RED);
        }
        if((start.equalsIgnoreCase("toilettes") || start.equalsIgnoreCase("wc") || start.equalsIgnoreCase("café") || start.equalsIgnoreCase("cafe")) && 
        (!finish.equalsIgnoreCase(start) && !finish.equalsIgnoreCase("Arrivée") && !isBlank(finish))){
            vue.getApp().getStart().setForeground(Color.RED);
        }
    }

    /**
     * Vérifie si une chaine de caractères est composée de caractères blancs
     * @param s chaine de caractères à tester
     * @return  true si elle est composée de caractères blancs, false sinon
     */
    public boolean isBlank(String s){
        for(int i = 0; i < s.length(); i++){
            if(!Character.isWhitespace(s.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si le nom {@code salle} est celui d'une salle du bâtiment
     * 
     * @param salle nom de la salle
     * @return      true si le nom correspond à celui d'une salle du bâtiment, false sinon
     */
    public boolean estSalle(String salle) {
        return batActuel.findSalle(salle);
    }

    /**
     * Met à jour l'application en fonction des entrées utilisateur
     */
    public void majApp(){
        if((this.vue.getApp().getStart().getText().equals("Départ") || isBlank(this.vue.getApp().getStart().getText())) 
        && (this.vue.getApp().getFinish().getText().equals("Arrivée") || isBlank(this.vue.getApp().getFinish().getText()))){
            //on affiche seulement les plans
            this.vue.majApp(null, null, vue.getApp().getAscenseur().isSelected());
        }
        else if(this.vue.getApp().getStart().estSalle() && this.vue.getApp().getFinish().estSalle()){
            //on affiche le chemin le plus court entre deux salles
            this.vue.majApp(getSalle(vue.getApp().getStart().getText()), getSalle(vue.getApp().getFinish().getText()), vue.getApp().getAscenseur().isSelected());
        }
        else if(((this.vue.getApp().getStart().getText().equals("Départ") || isBlank(this.vue.getApp().getStart().getText()))
        && this.vue.getApp().getFinish().estSalle())){
            //on affiche les portes de la salle d'arrivée
            this.vue.majApp(null, getSalle(this.vue.getApp().getFinish().getText()), vue.getApp().getAscenseur().isSelected());
        }
        else if((this.vue.getApp().getStart().estSalle() 
        && (this.vue.getApp().getFinish().getText().equals("Arrivée") || isBlank(this.vue.getApp().getFinish().getText())))){
            //on affiche les portes de la salle de départ
            this.vue.majApp(getSalle(this.vue.getApp().getStart().getText()), null, this.vue.getApp().getAscenseur().isSelected());
		}
	}

    /**
     * Méthode qui ajoute un élément aux favoris (Salle ou Chemin)
     */
    public void ajouterElementListFavori() {
        Noeud depart = batActuel.getSalle(vue.getApp().getStart().getText());
        Noeud arrivee = batActuel.getSalle(vue.getApp().getFinish().getText());

        if ((depart != null) || arrivee != null) {
            Binome b = new Binome(depart, arrivee);

            if (batActuel.getModelFavoris().addFavoris(b)) {
                batActuel.getViewFavoris().addFavoris(b);
            }
        }
    }

    /**
     * Méthode qui supprime un élément des favoris (Salle ou Chemin)
     * 
     * @param index  index où est stocké l'élément dans le JComboBox de la vue
     * @param binome binome que l'on souhaite supprimer
     */
    public void supprimerElementListFavori(int index, Binome binome) {
        batActuel.getViewFavoris().removeFavoris(index);
        batActuel.getModelFavoris().removeFavoris(binome);
    }

    /**
     * Méthode qui permet de mettre à jour les valeurs lorsque l'on sélectionne un
     * favori
     * 
     * @param b chemin ou noeud qui vient d'être sélectionné
     */
    public void selectionFavoris(Binome b) {
        if (b == null) {
            vue.getApp().getFinish().setText("");
            vue.getApp().getStart().setText("");
            return;
        }
        if (b.getDepart() != null) {
            vue.getApp().getStart().setText(((Salle) b.getDepart()).nom);
        } else {
            vue.getApp().getStart().setText("");
        }
        if (b.getArrivee() != null) {
            vue.getApp().getFinish().setText(((Salle) b.getArrivee()).nom);
        } else {
            vue.getApp().getFinish().setText("");
        }
    }
}