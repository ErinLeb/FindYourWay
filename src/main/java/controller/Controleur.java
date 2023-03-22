package controller;

import model.Batiment;
import model.Noeud;
import view.Vue;

/**
 * Gère les interactions entre la vue et le modèle
 */
public class Controleur {

    /**
     * Vue associée au controleur
     */
    private Vue vue;

    /**
     * le batiment sur lequel la vue est centrée
     */
    private Batiment batActuel;

    /**
     * construit le controleur de l'app
     * @param bat le bâtiment de départ
     */
    public Controleur(Batiment bat) {
        this.batActuel = bat;
        this.vue = new Vue(this);
    }

    /**
     * renvoie l'étage max du bâtiment sur lequel la vue est centrée
     * @return l'étage max du bâtiment sur lequel la vue est centrée
     */
    public int getEtageMaxActuel() {
        return batActuel.getMax();
    }

    /**
     * renvoie l'étage min du bâtiment sur lequel la vue est centrée
     * @return l'étage min du bâtiment sur lequel la vue est centrée
     */
    public int getEtageMinActuel() {
        return batActuel.getMin();
    }

    /**
     * renvoie le batiment actuel
     * @return renvoie le batiment actuel
     */
    public Batiment getBatiment(){
        return batActuel;
    }

    /**
     * Vérifie que les textes contenus dans les TextFieldBox sont soit des noms de salles, soit "Départ" et "Arrivée"
     * @return renvoie vrai si les textes dans les TextFieldBox sont soit des noms de salles, soit "Départ" et "Arrivée" et renvoie faux sinon
     */
    public boolean verifGoButton(){
        String start = vue.getApp().getStart().getText();
        String finish = vue.getApp().getFinish().getText();
        if((start.equalsIgnoreCase("Départ")||estSalle(start))&&(finish.equalsIgnoreCase("Arrivée")||estSalle(finish))){
            return true;
        }
        return false;
    }

    /**
     * vérifie si le nom donné en paramètre est celui d'une salle du batiment
     * @param salle nom de la salle
     * @return renvoie vrai si le nom correspond à celui d'une salle du batiment et faux sinon
     */
    public boolean estSalle(String salle){
        return batActuel.findSalle(salle);
    }

    /**
     * met à jour l'application en fonction des entrées utilisateur
     */
    public void majApp(){
        if(this.vue.getApp().getStart().getText().equals("Départ") && this.vue.getApp().getFinish().getText().equals("Arrivée")){
            // cas où on veut juste afficher les plans
            this.vue.majApp(null, null, vue.getApp().getAscenseur().isSelected());
        }
        else if(this.vue.getApp().getStart().estSalle() && this.vue.getApp().getFinish().estSalle()){
            // cas où on veut afficher le chemin le plus court entre deux salles
            this.vue.majApp(getSalle(vue.getApp().getStart().getText()), getSalle(vue.getApp().getStart().getText()), vue.getApp().getAscenseur().isSelected());
        }
        else if((this.vue.getApp().getStart().getText().equals("Départ") && this.vue.getApp().getFinish().estSalle())){
            // cas où on souhaite afficher les portes de la salle d'arrivée
            this.vue.majApp(null, getSalle(this.vue.getApp().getFinish().getText()), vue.getApp().getAscenseur().isSelected());
        }
        else if((this.vue.getApp().getStart().estSalle() && this.vue.getApp().getFinish().getText().equals("Arrivée"))){
            // cas où on veut afficher les portes de la salle de départ
            this.vue.majApp(getSalle(this.vue.getApp().getStart().getText()), null, this.vue.getApp().getAscenseur().isSelected());
        }
    }

    /**
     * renvoie la salle dont on a donné le nom en paramètre
     * @param salle nom de la salle à trouver
     * @return renvoie le noeud de la salle dont on a donné le nom en paramètre
     */
    private Noeud getSalle(String salle){
        return batActuel.getSalle(salle);
    }

    /**
     * renvoie la vue du controleur
     * @return renvoie la vue du controleur
     */
    public Vue getVue(){
        return vue;
    }
    
}
