package view;

import javax.swing.JFrame;

import controller.Controleur;
import model.Noeud;

/**
 * L'interface graphique de l'application
 */
public class Vue extends JFrame {

    private Home accueil;

    private MainApp app;

    private Controleur control;

    /**
     * la largeur de la fenêtre 
     */
    private final static int WIDTH = 1650;
    /**
     * la hauteur de la fenêtre 
     */
    private final static int HEIGHT = 1080;

    /**
     * initialise la vue de l'app
     * @param control le controleur de l'app
     */
    public Vue(Controleur control) {
        //Création du nouveau controller
        this.control = control;

        //Configuration de la fenêtre
        this.setSize(WIDTH, HEIGHT);
        this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        this.setTitle("FindYourWay");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        accueil = new Home(this, control);
        

        this.setContentPane(accueil);
        this.setVisible(true);

    }

    /**
     * renvoie la fenêtre d'accueil de l'application
     * @return renvoie la fenêtre d'accueil de l'application
     */
    public Fenetre getAccueil(){
        return this.accueil;
    }

    /**
     * renvoie la fenêtre actuelle
     * @return renvoie la fenêtre actuelle
     */
    public Fenetre getApp() {
        if(app == null){
            return this.accueil;
        }
        return this.app;
    }

    /**
     * renvoie le controleur de la vue
     * @return renvoie le controleur de la vue
     */
    public Controleur getControl(){
        return control;
    }

    /**
     * met à jour l'application et effectue une tache en fonction des entrées utilisateur
     * @param depart noeud de départ du chemin
     * @param arrivee noeud d'arrivée du chemin
     * @param ascenseur permission d'utiliser les ascenseurs 
     */
    public void majApp(Noeud depart, Noeud arrivee, boolean ascenseur){
        if(this.app==null){
            this.app = new MainApp(this, control,accueil.getStart().getText(),accueil.getFinish().getText(),accueil.getAscenseur().isSelected());
        }
        else{
            this.app = new MainApp(this, control,app.getStart().getText(),app.getFinish().getText(),app.getAscenseur().isSelected());
        }
        if(depart != null && arrivee == null){
            this.app.afficherPortes(depart);
        }
        else if(depart == null && arrivee != null){
            this.app.afficherPortes(arrivee);
        }
        else if(depart != null && arrivee != null){
            this.app.afficherChemin(depart, arrivee, ascenseur);
        }
    }
    
}
