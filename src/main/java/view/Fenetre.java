package view;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import controller.Controleur;


/**
 * Classe abstraite correspondant à une fenêtre de l'interface graphique (menu d'accueil ou page principale)
 */
public abstract class Fenetre extends JPanel{
    // Attributs

    /**
     * Le panel avec lequel on peut interagir
     */
    protected JPanel controlPanel;
    
    /**
     * La fenêtre de l'app
     */
    protected Vue view;

    /**
     * Le controller de l'app
     */
    protected Controleur control;

    /**
     * Champ de saisie de la salle de départ
     */
    protected TextFieldBox start;

    /**
     * Champ de saisie de la salle d'arrivée
     */
    protected TextFieldBox finish;

    /**
     * Case cochable indiquant si on souhaite utiliser les ascenseurs ou non   
     */
    protected JCheckBox ascenseur;


    // Getters 

    /**
     * Renvoie le controleur
     * @return le controleur
     */
    public Controleur getControl(){
        return control;
    }

    /**
     * Renvoie le champ de saisie de la salle de départ
     * @return le champ de saisie de la salle de départ
     */
    public TextFieldBox getStart(){
        return start;
    }

    /**
     * Renvoie le champ de saisie de la salle d'arrivée
     * @return le champ de saisie de la salle d'arrivée
     */
    public TextFieldBox getFinish(){
        return finish;
    }

    /**
     * Renvoie la case cochable pour les ascenseurs
     * @return la case cochable pour les ascenseurs
     */
    public JCheckBox getAscenseur(){
        return ascenseur;
    }
}