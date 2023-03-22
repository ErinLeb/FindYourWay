package view;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import controller.Controleur;

/**
 * classe abstraite correspondant à une fenêtre de l'interface graphique (menu d'accueil ou application)
 */
public abstract class Fenetre extends JPanel{
    /**
     * Le panel avec lequel on peut interagir
     */
    protected JPanel controlPanel;
    
    /**
     * la fenêtre de l'app
     */
    protected Vue view;

    /**
     * Le controller de l'app
     */
    protected Controleur control;

    /**
     * champ de saisie de la salle de départ
     */
    protected TextFieldBox start;

    /**
     * champ de saisie de la salle d'arrivée
     */
    protected TextFieldBox finish;

    /**
     * case cochable indiquant si on souhaite utiliser les ascenseurs ou      
     */
    protected JCheckBox ascenseur;

    /**
     * renvoie le controleur
     * @return renvoie le controleur
     */
    public Controleur getControl(){
        return control;
    }

    /**
     * renvoie le champ de saisie de la salle de départ
     * @return renvoie le champ de saisie de la salle de départ
     */
    public TextFieldBox getStart(){
        return start;
    }

    /**
     * renvoie le champ de saisie de la salle d'arrivée
     * @return renvoie le champ de saisie de la salle d'arrivée
     */
    public TextFieldBox getFinish(){
        return finish;
    }

    /**
     * renvoie la case cochable pour les ascenseurs
     * @return renvoie la case cochable pour les ascenseurs
     */
    public JCheckBox getAscenseur(){
        return ascenseur;
    }
}
