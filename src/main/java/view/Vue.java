package view;

import javax.swing.JFrame;

import controller.Controleur;

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

        app = new MainApp(this, control);
        accueil = new Home(this, control);
        

        this.setContentPane(accueil);
        this.setVisible(true);

    }

    public MainApp getApp() {
        return this.app;
    }
    
}
