package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.util.ArrayList;
import java.util.List;

import controller.Controleur;
import model.Batiment;


/**
 * Le menu d'accueil de l'application
 */
public class Home extends Fenetre {
    //Attributs

    /**
     * Le JLabel contenant le logo
     */
    private JLabel logoLabel;
    
    /*
     * Panel du choix des batiments
     */
    private JPanel choixBatiment;

    /*
     * ComboBox des batiments disponibles
     */
    private JComboBox<Batiment> boxBatiment;

    /*
     * Liste des batiments disponibles
     */
    private List<Batiment> batiments;


    //Constructeur 

    /**
     * Construit le menu d'accueil
     * @param view la vue associée au menu Home
     * @param control le controleur de l'app
     */
    public Home(Vue view, Controleur control) {
        this.control = control;
        this.view = view;
                
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
    

    //Méthodes
    
    /**
     * Initialise tous les éléments du panel de controle (batiments, départ, arrivée, ascenseurs)
     */
    private void initControlPanel() {
        //Création des deux champs de saisie de texte
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        start = new TextFieldBox("Départ", 10, view);
        finish = new TextFieldBox("Arrivée", 10, view);
        inputPanel.add(start);
        inputPanel.add(finish);

        //Création du bouton
        GoButton mainButton = new GoButton(view);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(mainButton);
        
        //Création de la case à cocher
        ascenseur = new JCheckBox("Ascenseurs");
        ascenseur.setHorizontalAlignment(SwingConstants.CENTER);

        //Ajout des éléments au panel
        controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);

        initBatimentPanel();
        controlPanel.add(choixBatiment, c);

        c.gridy = 1;
        controlPanel.add(inputPanel, c);

        c.gridy = 2;
        controlPanel.add(buttonPanel, c);

        c.gridy = 3;
        controlPanel.add(ascenseur, c);
    }

    /**
     * Initialise la liste des batiments disponibles et le JComboBox y correspondant
     */
    public void initBatiments(){
        batiments = new ArrayList<>();
        batiments.add(new Batiment("Halle aux Farines", 0, 5, "src/main/ressources/csv/haf/", "src/main/ressources/graphics/plans/haf/"));

        boxBatiment = new JComboBox<>();
        for(Batiment b : batiments){
            boxBatiment.addItem(b);
        }

        boxBatiment.setSelectedIndex(0);
        control.setBatiment(boxBatiment.getItemAt(0));
        boxBatiment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setBatiment((Batiment)boxBatiment.getSelectedItem());
            }
        });
        boxBatiment.setEditable(false);
    }

    /**
     * Initialise le panel correspondant au choix du batiment 
     */
    private void initBatimentPanel() {
        choixBatiment = new JPanel(new GridBagLayout());
        JLabel consigne = new JLabel("Dans quel batiment se trouvent vos salles ?");
        initBatiments();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        choixBatiment.add(consigne, constraints);

        constraints.gridy = 1;
        choixBatiment.add(boxBatiment, constraints);
    }
}