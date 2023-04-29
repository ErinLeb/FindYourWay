package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import java.util.ArrayList;

import controller.Controleur;
import model.Carrefour;
import model.Chemin;
import model.Dijkstra;
import model.Noeud;


/**
 * L'écran principal de l'application,
 * sur lequel on voit les plans et itinéraires
 */
public class MainApp extends Fenetre {
    // Attributs

    /**
     * La liste contenant les images du bâtiment actuel
     */
    private ArrayList<BufferedImage> listImages;

    /*
     * Le chemin actuel si les entrées utilisateurs sont correctes, null sinon
     */
    private Chemin cheminActuel;

    /**
     * La salle à afficher si elle existe, null sinon
     */
    private Noeud salle;

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

    /**
     * Le Label indiquant l'étage actuel
     */
    private JLabel etageLabel;

    /**
     * Bouton permettant de changer d'étage
     */
    private JButton upButton, downButton;

    /**
     * Indique si l'application est en mode debug
     */
    private boolean debug = false;

    // Constructeur

    /**
     * Construit l'écran principal
     * 
     * @param view          la vue associée au menu principal
     * @param control       le controleur de l'app
     * @param startStr      nom de l'éventuelle salle de départ
     * @param finishStr     nom de l'éventuelle salle d'arrivée
     * @param ascenseurBool état de la case cochable (true si cochée, false sinon)
     * @param lImages       la liste des BufferedImage des plans du bâtiment actuel
     */
    public MainApp(Vue view, Controleur control, String startStr, String finishStr, boolean ascenseurBool,
            ArrayList<BufferedImage> lImages) {
        this.control = control;
        this.view = view;

        // Création et configuration d'un panel contenant les éléments de l'application
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);

        this.initControlPanel(startStr, finishStr, ascenseurBool);

        // Création de l'image du plan
        this.listImages = lImages;
        planPanel = new PlanPanel(listImages.get(0), this);
        this.initPanelDroit();

        // on ajoute les différents panels à la pane principale
        this.add(controlPanel, BorderLayout.NORTH);
        this.add(planPanel, BorderLayout.CENTER);
        this.add(droitPanel, BorderLayout.EAST);

        // Ajout d'un Listener pour changer le mode debug
        this.addListenerChangeDebug();
    }

    // Getters

    /**
     * Récupère l'étage affiché actuellement grâce au label d'étage
     * 
     * @param etage le label indiquant l'étage actuel
     * @return l'étage actuel
     */
    public int getEtageActuel() {
        String texte = etageLabel.getText();
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
        return estEtagePositif() ? etage : -etage;
    }

    /**
     * Renvoie la liste des images des plans
     * 
     * @return la liste des images des plans
     */
    public ArrayList<BufferedImage> getListImages() {
        return listImages;
    }

    /**
     * Renvoie le chemin actuel déterminé par les noeuds de départ et d'arrivée
     * s'ils sont valides, null sinon
     * 
     * @return le chemin actuel déterminé par les noeuds de départ et d'arrivée
     *         s'ils sont valides, null sinon
     */
    public Chemin getChemin() {
        return cheminActuel;
    }

    /**
     * Change le chemin actuel par le chemin le plus court entre {@code depart} et
     * {@code arrivee}
     * 
     * @param depart
     * @param arrivee
     * @param ascenseur
     */
    public void setChemin(Noeud depart, Noeud arrivee, boolean ascenseur) {
        if (depart == null || arrivee == null) {
            cheminActuel = null;
        } else {
            cheminActuel = Dijkstra.trouverCheminPlusCourt(depart, arrivee, ascenseur);
        }
    }

    /**
     * Modifie la salle à afficher pour {@code salle}
     * 
     * @param salle
     */
    public void setSalle(Noeud salle) {
        this.salle = salle;
    }

    /**
     * Renvoie la salle actuelle à afficher
     * @return la salle actuelle à afficher
     */
    public Noeud getSalle() {
        return this.salle;
    }

    /**
     * Renvoie true si l'application est en mode debug, false sinon
     * @return true si l'application est en mode debug, false sinon
     */
    public boolean getDebug() {
        return this.debug;
    }

    /**
     * Passe du mode debug au mode normal et inversement
     */
    public void changeDebug() {
        this.debug = !this.debug;
    }

    // Méthodes

    /**
     * Initialise le controlPanel
     * 
     * @param startStr      nom de l'éventuelle salle de départ
     * @param finishStr     nom de l'éventuelle salle d'arrivée
     * @param ascenseurBool état de la case cochable (true si cochée, false sinon)
     */
    private void initControlPanel(String startStr, String finishStr, boolean ascenseurBool) {
        // Création du Panel de contrôle
        this.controlPanel = new JPanel(new GridBagLayout());
        this.controlPanel.setBackground(Color.LIGHT_GRAY);

        // Logo
        ImageIcon logo = new ImageIcon("src/main/ressources/graphics/logos/logomini.png");
        JLabel logoLabel = new JLabel(logo);

        // Boîtes de saisie de texte
        start = new TextFieldBox(startStr, 10, view);
        finish = new TextFieldBox(finishStr, 10, view);

        // Case à cocher
        ascenseur = new JCheckBox("Ascenseurs");
        ascenseur.setSelected(ascenseurBool);
        ascenseur.setHorizontalAlignment(SwingConstants.CENTER);

        // Bouton de recherche de chemin
        GoButton mainButton = new GoButton(view);

        // Placement des éléments dans le panel
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        this.controlPanel.add(logoLabel, constraints);
        constraints.gridx = 1;
        this.controlPanel.add(control.getBatiment().getViewFavoris(), constraints);
        constraints.gridx = 2;
        constraints.weightx = 1.0;
        this.controlPanel.add(start, constraints);
        constraints.gridx = 3;
        constraints.weightx = 1.0;
        this.controlPanel.add(finish, constraints);
        constraints.gridx = 4;
        constraints.weightx = 1.0;
        this.controlPanel.add(ascenseur, constraints);
        constraints.gridx = 5;
        this.controlPanel.add(mainButton, constraints);
    }

    /**
     * Initialise le panel de changement d'étages
     */
    private void initEtagesPanel() {
        // Boutons haut et bas + Label indiquant l'étage
        this.etagesPanel = new JPanel(new GridLayout(3, 1));
        this.etagesPanel.setBackground(Color.LIGHT_GRAY);
        this.etagesPanel.setPreferredSize(new Dimension(droitPanel.getWidth(), 300));

        etageLabel = new JLabel("Rez-de-chaussée");
        etageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        upButton = new JButton(new ImageIcon("src/main/ressources/graphics/pictos/arrow-up.png"));
        downButton = new JButton(new ImageIcon("src/main/ressources/graphics/pictos/arrow-down.png"));

        buttonsActionListener();

        if (control.getEtageMinActuel() == 0) {
            downButton.setEnabled(false);
        }
        if (control.getEtageMaxActuel() == 0) {
            upButton.setEnabled(false);
        }

        this.etagesPanel.add(upButton);
        this.etagesPanel.add(etageLabel);
        this.etagesPanel.add(downButton);
    }

    /**
     * Initialise le panel de droite
     */
    private void initPanelDroit(){
        droitPanel = new JPanel(new BorderLayout());
        this.droitPanel.setPreferredSize(new Dimension(300, droitPanel.getHeight()));

        this.initEtagesPanel();
        this.initIndicationsPanel();

        droitPanel.add(etagesPanel, BorderLayout.NORTH);
        droitPanel.add(indicationsPanel, BorderLayout.CENTER);

        ExitButton exit = new ExitButton(view);
        exit.setPreferredSize(new Dimension(droitPanel.getWidth(), 100));
        droitPanel.add(exit, BorderLayout.SOUTH);
    }

    /**
     * Initialise le panel des indications
     */
    private void initIndicationsPanel() {
        String indic;

        if (cheminActuel == null) {
            indic = "Rentrez deux salles pour voir l'itinéraire le plus court.";
        } else {
            indic = cheminActuel.getIndications();
        }
        JTextArea indications = new JTextArea(indic);

        Font font = new Font("Century Schoolbook", Font.PLAIN, 17);
        indications.setFont(font);
        indications.setLineWrap(true);
        indications.setEditable(false);

        indicationsPanel = new JScrollPane(indications);
        indicationsPanel.revalidate();
    }

    /**
     * Gère les clics sur les JButton up et down et modifie le JLabel etageActuel en
     * conséquence
     */
    private void buttonsActionListener() {
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Change d'étage
                changeEtage(getEtageActuel() + 1);
            }
        });
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Change d'étage
                changeEtage(getEtageActuel() - 1);
            }
        });
    }

    /**
     * Ajoute un KeyListener afin de passer en mode debug quand la combinaison de touche CTRL+D est saisie
     */
    private void addListenerChangeDebug() {
        // On s'assure que les combinaisons de touches sont dirigées vers le JPanel
        this.setFocusable(true);
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK), "ctrlD");
        
        this.getActionMap().put("ctrlD", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                changeDebug();
                if (debug) {
                    planPanel.drawDebug(getEtageActuel());
                } else {
                    planPanel.eraseDebug(getEtageActuel());
                }
            }
            
        });
    }

    /**
     * Modifie le label en fonction du nouvel étage
     * 
     * @param nouvelEtage le numéro du nouvel étage
     */
    private void modifEtageLabel(int nouvelEtage) {
        String nouveauTexte = "";
        if (nouvelEtage == 0) {
            nouveauTexte = "Rez-de-chaussée";
        } else if (nouvelEtage == -1) {
            nouveauTexte = "1er sous-sol";
        } else if (nouvelEtage == 1) {
            nouveauTexte = "1er étage";
        } else if (nouvelEtage < -1) {
            nouveauTexte = (-nouvelEtage) + "ème sous-sol";
        } else {
            nouveauTexte = nouvelEtage + "ème étage";
        }

        etageLabel.setText(nouveauTexte);
    }

    /**
     * Vérifie si etageLabel représente un numéro d'étage positif
     * 
     * @return true si le numéro d'étage est positif, false sinon
     */
    private boolean estEtagePositif() {
        String texte = etageLabel.getText();
        return texte.charAt((texte.length() - 1)) == 'e'; // Si le texte finit par e, c'est qu'il finit par étage et non
                                                          // par sous-sol
    }

    /**
     * Modifie l'étage sur la vue
     * 
     * @param nouvelEtage le numéro du nouvel étage
     */
    public void changeEtage(int nouvelEtage) {
        // Change l'image à afficher
        planPanel.setImage(listImages.get(nouvelEtage));
        // Dessine les points et les liens si l'app est en mode debug
        planPanel.drawDebug(nouvelEtage);
        // Modifie le label
        modifEtageLabel(nouvelEtage);
        // Dessine le chemin s'il existe
        if (cheminActuel != null) {
            planPanel.drawPath(nouvelEtage);
        }
        // Modifie les permissions de cliquer sur les boutons si besoin
        if (nouvelEtage <= control.getEtageMinActuel()) {
            downButton.setEnabled(false);
        } else {
            downButton.setEnabled(true);
        }
        if (nouvelEtage >= control.getEtageMaxActuel()) {
            upButton.setEnabled(false);
        } else {
            upButton.setEnabled(true);
        }
    }

    /**
     * Affiche le chemin entre la salle de départ et la salle d'arrivée
     * 
     * @param depart    salle de départ
     * @param arrivée   salle d'arrivée
     * @param ascenseur permission d'utiliser les ascenseurs
     */
    public void afficherChemin(Noeud depart, Noeud arrivee, boolean ascenseur) {
        // mise à jour des indications
        droitPanel.removeAll();
        initPanelDroit();
        droitPanel.repaint();
        droitPanel.revalidate();
        this.add(droitPanel, BorderLayout.EAST);

        if (cheminActuel != null) {
            changeEtage(cheminActuel.getEtageDepart());
            planPanel.drawPath(getEtageActuel());
        }
    }

    /**
     * Affiche la ou les portes de la salle
     * 
     * @param salle salle dont on veut afficher la ou les portes
     */
    public void afficherPortes(Noeud salle) {
        droitPanel.removeAll();
        initPanelDroit();
        droitPanel.repaint();
        droitPanel.revalidate();
        this.add(droitPanel, BorderLayout.EAST);

        Carrefour c = null;
        for (Noeud voisin : salle.getVoisins().keySet()) {
            c = (Carrefour) voisin;
        }
        if (c != null) {
            changeEtage(c.getEtage());
            planPanel.drawRoom(getEtageActuel(), salle);
        }
    }
}