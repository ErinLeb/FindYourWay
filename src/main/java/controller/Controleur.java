package controller;

import java.util.List;

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
     * le batiment sur lequel la vue est centrée
     */
    private Batiment batActuel;

    // Constructeur

    /**
     * Construit le controleur de l'app
     */
    public Controleur() { 
        this.vue = new Vue(this);

        batActuel.getViewFavoris().setcontroleur(this);
    }

    // Getters & setters

    /**
     * Renvoie l'étage max du bâtiment sur lequel la vue est centrée
     * 
     * @return l'étage max du bâtiment sur lequel la vue est centrée
     */
    public int getEtageMaxActuel() {
        return batActuel.getMax();
    }

    /**
     * Renvoie l'étage min du bâtiment sur lequel la vue est centrée
     * 
     * @return l'étage min du bâtiment sur lequel la vue est centrée
     */
    public int getEtageMinActuel() {
        return batActuel.getMin();
    }

    /**
     * Renvoie le batiment actuel
     * 
     * @return le batiment actuel
     */
    public Batiment getBatiment() {
        return batActuel;
    }

    /**
     * Renvoie la salle qui a pour nom {@code nom}
     * 
     * @param salle nom de la salle à trouver
     * @return le noeud de la salle qui a pour nom {@code nom}
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
     * Renvoie la liste des noeuds à afficher de l'étage numéro {@code numEtage}
     * 
     * @param numEtage le numéro de l'étage
     * @return la liste de ses points
     */
    public List<Noeud> getNoeudsEtage(int numEtage) {
        Etage etage = batActuel.getEtage(numEtage);

        return etage.getNoeuds();
    }

    /**
     * Change le batiment actuel par {@code bat} et si la vue est déjà initialisée, change sa liste d'images de plans
     * @param bat nouveau batiment
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
     * salles, soit "Départ", soit "Arrivée"
     * 
     * @return vrai si les textes dans les TextFieldBox sont soit des noms de
     *         salles, soit "Départ", soit "Arrivée", faux sinon
     */
    public boolean verifGoButton() {
        String start = vue.getApp().getStart().getText();
        String finish = vue.getApp().getFinish().getText();
        if((start.equalsIgnoreCase("Départ") || estSalle(start) || isBlank(start)) 
        && (finish.equalsIgnoreCase("Arrivée") || estSalle(finish) || isBlank(finish))){
            return true;
        }
        return false;
    }

    /**
     * vérifie si une chaine de caractères est composée de caractères blancs
     * @param s chaine de caractère à tester
     * @return true si elle est composée de caractères blancs, faux sinon
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
     * Vérifie si le nom {@code salle} est celui d'une salle du batiment
     * 
     * @param salle nom de la salle
     * @return vrai si le nom correspond à celui d'une salle du batiment, faux sinon
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
     * Methode qui ajoute un element au favoris (Salle ou chemin)
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
     * Methode qui supprime un element des favoris (Salle ou chemin)
     * 
     * @param index  index ou est stocké l'élément dans la vue dans le JComboBox
     * @param binome binome que l'on souhaite supprimer
     */
    public void supprimerElementListFavori(int index, Binome binome) {
        batActuel.getViewFavoris().removeFavoris(index);
        batActuel.getModelFavoris().removeFavoris(binome);
    }

    /**
     * Methode qui permet de mettre à jour les valeurs lorsque l'on selectionne un
     * favoris
     * 
     * @param b chemin ou noeud qui viens d'être séléctionné.
     */
    public void selectionFavoris(Binome b) {
        if (b == null) {
            vue.getApp().getFinish().setText("");
            vue.getApp().getStart().setText("");
            return;
        }
        if (b.getDepart() != null) {
            vue.getApp().getStart().setText(((Salle) b.getDepart()).getNom());
        } else {
            vue.getApp().getStart().setText("");
        }
        if (b.getArrivee() != null) {
            vue.getApp().getFinish().setText(((Salle) b.getArrivee()).getNom());
        } else {
            vue.getApp().getFinish().setText("");
        }
    }
}