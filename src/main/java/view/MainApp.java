package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.util.ArrayList;

import controller.Controleur;
import model.Chemin;
import model.Dijkstra;
import model.Noeud;

/**
 * L'écran principal de l'application, 
 * sur lequel on voit les plans et itinéraires
 */
public class MainApp extends Fenetre {
    //Attributs 

    /**
     * La liste contenant les images du bâtiment actuel
     */
    private ArrayList<BufferedImage> listImages;

    /*
     * Le chemin actuel si les entrées utilisateurs sont correctes, null sinon
     */
    private Chemin cheminActuel;

    /**
     * le panel permettant de changer d'étages
     */
    private JPanel etagesPanel;

    /**
     * le panel indiquant les instructions de direction selon le chemin à l'écran
     */
    private JScrollPane indicationsPanel;

    /**
     * le panel réunissant les indications et le changement d'étages
     */
    private JPanel droitPanel;

    /**
     * le panel du plan affiché actuellement
     */
    private PlanPanel planPanel;


    //Constructeur

    /**
     * Construit l'écran principal
     * @param view la vue associée au menu principal
     * @param control le controleur de l'app
     * @param startStr nom de l'éventuelle salle de départ
     * @param finishStr nom de l'éventuelle salle d'arrivée
     * @param ascenseurBool état de la case cochable (true si cochée, false sinon)
     * @param lImages la liste des BufferedImage des plans du bâtiment actuel
     */
    public MainApp(Vue view, Controleur control,String startStr, String finishStr, boolean ascenseurBool, ArrayList<BufferedImage> lImages) {
        this.control = control;
        this.view = view;

        //Création  et configuration d'un panel contenant les éléments de l'application
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        this.initControlPanel(startStr,finishStr,ascenseurBool);

        //Création de l'image du plan
        this.listImages = lImages;
        planPanel = new PlanPanel(listImages.get(0), this);

        this.initPanelDroit();

        //on ajoute les différents panels à la pane principale
        this.add(controlPanel, BorderLayout.NORTH);
        this.add(planPanel, BorderLayout.CENTER);
        this.add(droitPanel, BorderLayout.EAST);
    }


    //Getters 

    /**
     * Récupère l'étage affiché actuellement grâce au label d'étage
     * @param etage le label indiquant l'étage actuel
     * @return l'étage actuel
     */
    private static int getEtageActuel(JLabel etagelabel) {
        String texte = etagelabel.getText();
        String s = "";
        int i = 0;
        while (Character.getNumericValue(texte.charAt(i)) < 10 && (Character.getNumericValue(texte.charAt(i)) >= 0)) {
            s += texte.charAt(i);
            i++;
        }
        int etage = 0;
        if (!s.equals("")) {
            etage = Integer.valueOf(s);
        }
        return estEtagePositif(etagelabel)? etage:-etage;
    }

    public Chemin getChemin(){
        return cheminActuel;
    }

    public void setChemin(Noeud depart, Noeud arrivee, boolean ascenseur){
        if(depart == null || arrivee == null){
            cheminActuel = null;
        }else{
            cheminActuel = Dijkstra.trouverCheminPlusCourt(depart, arrivee, ascenseur);
        }
    }


    //Méthodes

    /**
     * Initialise le controlPanel
     * @param startStr nom de l'éventuelle salle de départ
     * @param finishStr nom de l'éventuelle salle d'arrivée 
     * @param ascenseurBool état de la case cochable (true si cochée, false sinon)
     */
    private void initControlPanel(String startStr,String finishStr, boolean ascenseurBool) {
        //Création du Panel de contrôle
        this.controlPanel = new JPanel(new GridBagLayout());
        this.controlPanel.setBackground(Color.LIGHT_GRAY);
        
        //Logo
        ImageIcon logo = new ImageIcon("src/main/ressources/graphics/logos/placeholdermini.png");
        JLabel logoLabel = new JLabel(logo);
        //Boîtes de saisie de texte
        start = new TextFieldBox(startStr, 10, view);
        finish = new TextFieldBox(finishStr, 10, view);
        //Case à cocher
        ascenseur = new JCheckBox("Ascenseurs");
        ascenseur.setSelected(ascenseurBool);
        ascenseur.setHorizontalAlignment(SwingConstants.CENTER);
        //Bouton de recherche de chemin
        GoButton mainButton = new GoButton(view);

        //Placement des éléments dans le panel
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        this.controlPanel.add(logoLabel, constraints);
        constraints.gridx = 1;
        constraints.weightx = 1.0;
        this.controlPanel.add(start, constraints);
        constraints.gridx = 2;
        constraints.weightx = 1.0;
        this.controlPanel.add(finish, constraints);
        constraints.gridx = 3;
        constraints.weightx = 1.0;
        this.controlPanel.add(ascenseur, constraints);
        constraints.gridx = 4;
        constraints.anchor = GridBagConstraints.EAST;
        this.controlPanel.add(mainButton, constraints);
    }

    /**
     * Initialise le panel de changement d'étages
     */
    private void initEtagesPanel() {
        //Boutons haut et bas + Label indiquant l'étage
        this.etagesPanel = new JPanel(new GridLayout(3, 1));
        this.etagesPanel.setBackground(Color.LIGHT_GRAY);

        JLabel nbEtage = new JLabel("Rez-de-chaussée");
        nbEtage.setHorizontalAlignment(SwingConstants.CENTER);
        JButton upButton = new JButton(new ImageIcon("src/main/ressources/graphics/pictos/arrow-up.png"));
        JButton downButton = new JButton(new ImageIcon("src/main/ressources/graphics/pictos/arrow-down.png"));

        buttonsActionListener(upButton, downButton, nbEtage);

        if (control.getEtageMinActuel() == 0) {
            downButton.setEnabled(false);
        }

        if (control.getEtageMaxActuel() == 0) {
            upButton.setEnabled(false);
        }
        

        this.etagesPanel.add(upButton);
        this.etagesPanel.add(nbEtage);
        this.etagesPanel.add(downButton);
    }

    /**
     * Initialise le panel de droite
     */
    private void initPanelDroit(){
        droitPanel = new JPanel(new GridLayout(2, 1));
        this.droitPanel.setPreferredSize(new Dimension(300, droitPanel.getHeight()));

        this.initEtagesPanel();
        this.initIndicationsPanel();

        droitPanel.add(etagesPanel);
        droitPanel.add(indicationsPanel);
    }

    /**
     * Initialise le panel des indications
     */
    private void initIndicationsPanel(){
        String indic;

        if(cheminActuel == null){
            indic = "Rentrez deux salles pour voir l'itinéraire le plus court.";
        }else{
            indic = cheminActuel.getIndications();
        }
        JTextArea indications = new JTextArea(indic);

        Font font = new Font("Century Schoolbook", Font.PLAIN, 17);
        indications.setFont(font);
        indications.setLineWrap(true);

        indicationsPanel = new JScrollPane(indications);

        indicationsPanel.revalidate();
    }

    /**
     * Gère les clics sur les JButton up et down et modifie le JLabel etageActuel en conséquence
     * @param up le bouton qui va à l'étage supérieur
     * @param down le bouton qui va à l'étage inférieur
     * @param etageActuel le label indiquant l'étage actuel
     */
    private void buttonsActionListener(JButton up, JButton down, JLabel etageActuel) {
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Change d'étage
                    int nouvelEtage = getEtageActuel(etageActuel) + 1;
                    planPanel.setImage(listImages.get(nouvelEtage));
                    //Dessine les points et les liens
                    planPanel.drawPointsLinks(nouvelEtage);
                    //Modifie le label
                    modifEtageLabel(etageActuel, nouvelEtage);
                    //Modifie les permissions de cliquer sur les boutons si besoin
                    if (nouvelEtage == control.getEtageMaxActuel()) {
                        up.setEnabled(false);
                    }
                    down.setEnabled(true);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }

            }
        });
        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Change d'étage
                    int nouvelEtage = getEtageActuel(etageActuel) - 1;
                    planPanel.setImage(listImages.get(nouvelEtage));
                    //Dessine les points et les liens
                    planPanel.drawPointsLinks(nouvelEtage);
                    //Modifie le label
                    modifEtageLabel(etageActuel, nouvelEtage);
                    //Modifie les permissions de cliquer sur les boutons si besoin
                    if (nouvelEtage == control.getEtageMinActuel()) {
                        down.setEnabled(false);
                    }
                    up.setEnabled(true);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }

            }
        });
    }

    /**
     * Modifie le label en fonction du nouvel étage
     * @param etageLabel le label à modifier
     * @param nouvelEtage le numéro du nouvel étage
     */
    private static void modifEtageLabel(JLabel etageLabel, int nouvelEtage) {
        String nouveauTexte = "";
        if (nouvelEtage == 0) {
            nouveauTexte = "Rez-de-chaussée";
        } else if (nouvelEtage == -1) {
            nouveauTexte = "1er sous-sol";
        } else if (nouvelEtage == 1) {
            nouveauTexte = "1er étage";
        } else if (nouvelEtage < -1) {
            nouveauTexte = (-nouvelEtage)+"ème sous-sol";
        } else {
            nouveauTexte = nouvelEtage+"ème étage";
        }

        etageLabel.setText(nouveauTexte);
    }

    /**
     * Vérifie si etageLabel représente un numéro d'étage positif
     * @param etageLabel le label dont on veut vérifier le signe de l'étage qu'il représente
     * @return true si le numéro d'étage est positif, false sinon
     */
    private static boolean estEtagePositif(JLabel etageLabel) {
        String texte = etageLabel.getText();
        return texte.charAt((texte.length() - 1)) == 'e'; //Si le texte finit par e, c'est qu'il finit par étage et pas par sous-sol
    }

    /**
     * Affiche le chemin entre la salle de départ et la salle d'arrivée
     * @param depart salle de départ
     * @param arrivée salle d'arrivée
     * @param ascenseur permission d'utiliser les ascenseurs
     */
    public void afficherChemin(Noeud depart, Noeud arrivee, boolean ascenseur){        
        //mise à jour des indications
        droitPanel.removeAll();
        initPanelDroit(); 
        droitPanel.repaint();
        droitPanel.revalidate(); 
        this.add(droitPanel, BorderLayout.EAST);

        // TODO : finir la fonction
    }

    /**
     * Affiche la ou les portes de la salle 
     * @param salle salle dont on veut afficher la ou les portes
     */
    public void afficherPortes(Noeud salle){
        droitPanel.removeAll();
        initPanelDroit(); 
        droitPanel.repaint();
        droitPanel.revalidate(); 
        this.add(droitPanel, BorderLayout.EAST);

        // TODO : finir la fonction
    }
}