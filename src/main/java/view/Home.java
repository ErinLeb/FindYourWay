package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Controleur;

/**
 * Le menu d'accueil de l'application
 */
public class Home extends JPanel {
    

    /**
     * Le JLabel contenant le logo
     */
    private JLabel logoLabel;

    /**
     * Le panel avec lequel on peut interagir
     */
    private JPanel controlPanel;
    
    /**
     * la fenêtre de l'app
     */
    private Vue view;

    /**
     * Le controller de l'app
     */
    private Controleur control;
    
    /**
     * Construit le menu d'accueil
     * @param view la vue associée au menu Home
     * @param control le controleur de l'app
     */
    public Home(Vue view, Controleur control) {
        this.control = control;
        this.view = view;
        
        //Création  et configuration d'un panel contenant les éléments du menu
        
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        
        //Ajout d'un logo centré au milieu du menu
        ImageIcon logo = new ImageIcon("src/main/ressources/graphics/logos/placeholder.png");
        logoLabel = new JLabel(logo);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        
        initControlPanel();

        //Placement des éléments à l'écran
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(logoLabel, constraints);
        constraints.gridy = 1;
        add(controlPanel,constraints);
    }

    /**
     * initialise le controlPanel
     */
    private void initControlPanel() {
        //Création des deux champs de saisie de texte
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        TextFieldBox start = new TextFieldBox("Départ", 10, view);
        TextFieldBox finish = new TextFieldBox("Arrivée", 10, view);
        inputPanel.add(start);
        inputPanel.add(finish);

        //Création du bouton
        GoButton mainButton = new GoButton(view);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(mainButton);
        
        //Création de la case à cocher
        JCheckBox ascenseur = new JCheckBox("Ascenseurs");
        ascenseur.setHorizontalAlignment(SwingConstants.CENTER);

        //Ajout des éléments au panel
        controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(inputPanel, BorderLayout.NORTH);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);
        controlPanel.add(ascenseur, BorderLayout.CENTER);

    }
}