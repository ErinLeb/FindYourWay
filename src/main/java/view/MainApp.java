package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Controleur;
import model.Chemin;
import model.Dijkstra;
import model.Noeud;

/**
 * L'écran principal de l'application, 
 * sur lequel on voit les plans et itinéraires
 */
public class MainApp extends Fenetre {

    /**
     * le panel permettant de changer d'étages
     */
    private JPanel etagesPanel;

    /**
     * le panel du plan affiché actuellement
     */
    private PlanPanel planPanel;


    /**
     * Construit l'écran principal
     * @param view la vue associée au menu principal
     * @param control le controleur de l'app
     * @param startStr nom de l'éventuelle salle de départ
     * @param finishStr nom de l'éventuelle salle d'arrivée
     * @param ascenseurBool état de la case cochable (true si cochée, false sinon)
     */
    public MainApp(Vue view, Controleur control,String startStr, String finishStr, boolean ascenseurBool) {
        this.control = control;
        this.view = view;

        //Création  et configuration d'un panel contenant les éléments de l'application
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        this.initControlPanel(startStr,finishStr,ascenseurBool);

        //Création de l'image du plan
        Image planImage = new ImageIcon("src/main/ressources/graphics/plans/etage0.jpg").getImage();
        planPanel = new PlanPanel(planImage);
        
        this.initEtagesPanel();

        //on ajoute les différents panels à la pane principale
        this.add(controlPanel, BorderLayout.NORTH);
        this.add(planPanel, BorderLayout.CENTER);
        this.add(etagesPanel, BorderLayout.EAST);
    }

    // getters

    /**
     * récupère l'étage affiché actuellement grâce au label d'étage
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

    // méthodes

    /**
     * initialise le controlPanel
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
     * initialise le panel de changement d'étages
     */
    private void initEtagesPanel() {
        //Boutons haut et bas + Label indiquant l'étage
        this.etagesPanel = new JPanel(new GridLayout(3, 1));
        this.etagesPanel.setBackground(Color.LIGHT_GRAY);
        this.etagesPanel.setPreferredSize(new Dimension(300, etagesPanel.getHeight()));

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
     * gère les clics sur les JButton up et down et modifie le JLabel etageActuel en conséquence
     * @param up le bouton qui va à l'étage supérieur
     * @param down le bouton qui va à l'étage inférieur
     * @param etageActuel le label indiquant l'étage actuel
     */
    private void buttonsActionListener(JButton up, JButton down, JLabel etageActuel) {
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Change d'étage
                int nouvelEtage = getEtageActuel(etageActuel) + 1;
                planPanel.setImage(new ImageIcon("src/main/ressources/graphics/plans/etage"+nouvelEtage+".jpg").getImage());
                //Modifie le label
                modifEtageLabel(etageActuel, nouvelEtage);
                //Modifie les permissions de cliquer sur les boutons si besoin
                if (nouvelEtage == control.getEtageMaxActuel()) {
                    up.setEnabled(false);
                }
                down.setEnabled(true);
            }
        });
        down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Change d'étage
                int nouvelEtage = getEtageActuel(etageActuel) - 1;
                planPanel.setImage(new ImageIcon("src/main/ressources/graphics/plans/etage"+nouvelEtage+".jpg").getImage());
                //Modifie le label
                modifEtageLabel(etageActuel, nouvelEtage);
                //Modifie les permissions de cliquer sur les boutons si besoin
                if (nouvelEtage == control.getEtageMinActuel()) {
                    down.setEnabled(false);
                }
                up.setEnabled(true);
            }
        });
    }

    /**
     * modifie le label en fonction du nouvel étage
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
     * vérifie si etageLabel représente un numéro d'étage positif
     * @param etageLabel le label dont on veut vérifier le signe de l'étage qu'il représente
     * @return true si le numéro d'étage est positif, false sinon
     */
    private static boolean estEtagePositif(JLabel etageLabel) {
        String texte = etageLabel.getText();
        return texte.charAt((texte.length() - 1)) == 'e'; //Si le texte finit par e, c'est qu'il finit par étage et pas par sous-sol
    }

    /**
     * affiche le chemin entre la salle de départ et la salle d'arrivée
     * @param depart salle de départ
     * @param arrivée salle d'arrivée
     * @param ascenseur permission d'utiliser les ascenseurs
     */
    public void afficherChemin(Noeud depart, Noeud arrivee, boolean ascenseur){
        Chemin chemin = Dijkstra.trouverCheminPlusCourt(depart, arrivee, ascenseur);
        // TODO : finir de définir la fonction
    }

    /**
     * affiche la ou les portes de la salle
     * @param salle salle dont on veut afficher la ou les portes
     */
    public void afficherPortes(Noeud salle){
        // TODO : définir la fonction
    }

}
