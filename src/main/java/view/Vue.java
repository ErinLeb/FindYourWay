package view;

import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;

import controller.Controleur;
import model.Noeud;

/**
 * L'interface graphique de l'application
 */
public class Vue extends JFrame {
    // Attributs

    /**
     * La page d'accueil
     */
    private Home accueil;

    /**
     * La page principale
     */
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
     * la liste contenant les images représentant les plans
     */
    private ArrayList<BufferedImage> listImages;


    // Constructeur

    /**
     * Initialise la vue de l'app
     * 
     * @param control le controleur de l'app
     */
    public Vue(Controleur control) {
        this.control = control;

        //Configuration de la fenêtre
        this.setSize(WIDTH, HEIGHT);
        this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        this.setTitle("FindYourWay");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        accueil = new Home(this, control);

        this.initListImages();

        this.setContentPane(accueil);
        this.setVisible(true);
    }


    // Getters

    /**
     * Renvoie la fenêtre d'accueil de l'application
     * @return la fenêtre d'accueil de l'application
     */
    public Fenetre getAccueil(){
        return this.accueil;
    }

    /**
     * Renvoie la fenêtre actuelle
     * @return la fenêtre actuelle
     */
    public Fenetre getApp() {
        if(app == null){
            return this.accueil;
        }
        return this.app;
    }

    /**
     * Renvoie le controleur de la vue
     * @return le controleur de la vue
     */
    public Controleur getControl(){
        return control;
    }


    //Méthodes

    /**
     * Met app à null et retourne sur la fenêtre d'accueil
     */
    public void resetApp(){
        this.app = null;
        this.setContentPane(accueil);
        repaint();
        setVisible(true);
    }
    
    /**
     * Initialise la liste des images des plans
     */
    public void initListImages() {
        try {
            listImages = new ArrayList<BufferedImage>();
            for (int i = control.getEtageMinActuel(); i <= control.getEtageMaxActuel(); i++) {
                String pathBatplan = control.getBatiment().getPathPlans();
                listImages.add(ImageIO.read(new File(pathBatplan + "/etage"+i+".jpg"))); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour l'application et effectue une tache en fonction des entrées utilisateur
     * 
     * @param depart    noeud de départ du chemin
     * @param arrivee   noeud d'arrivée du chemin
     * @param ascenseur permission d'utiliser les ascenseurs 
     */
    public void majApp(Noeud depart, Noeud arrivee, boolean ascenseur){
        if(depart != null && arrivee != null){
            this.app = new MainApp(this, control,getApp().getStart().getText(),getApp().getFinish().getText(),getApp().getAscenseur().isSelected(), listImages);
        }
        else if(depart != null){
            this.app = new MainApp(this, control,getApp().getStart().getText(),"Arrivée",getApp().getAscenseur().isSelected(), listImages);
        }
        else if(arrivee != null){
            this.app = new MainApp(this, control,"Départ",getApp().getFinish().getText(),getApp().getAscenseur().isSelected(), listImages);
        }
        else{
            this.app = new MainApp(this, control,"Départ","Arrivée",getApp().getAscenseur().isSelected(), listImages);
        }

        app.setChemin(depart, arrivee, ascenseur);

        if(depart != null && arrivee == null){
            this.app.setSalle(depart);
            this.app.afficherPortes(depart);
        }
        else if(depart == null && arrivee != null){
            this.app.setSalle(arrivee);
            this.app.afficherPortes(arrivee);
        }
        else if(depart != null && arrivee != null && depart == arrivee){
            this.app.setSalle(depart);
            this.app.afficherPortes(depart);
        }
        else if(depart != null && arrivee != null){
            this.app.setSalle(null);
            this.app.afficherChemin(depart, arrivee, ascenseur);
        }
    }
    
}