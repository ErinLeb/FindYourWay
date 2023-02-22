package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Home extends JFrame {
    /**
     * Le JLabel contenant le logo
     */
    private JLabel logoLabel;

    /**
     * le panel principal 
     */
    private JPanel pane;

    /**
     * la boîte de texte dans laquelle on rentre le point de départ 
     */
    private JTextField start;
    /**
     * la boîte de texte dans laquelle on rentre le point de départ
     */
    private JTextField finish;

    /**
     * le bouton qui lance la recherche d'itinéraire ou juste les plans 
     */
    private JButton mainButton;

    /**
     * la largeur de la fenêtre 
     */
    private final int WIDTH = 1650;
    /**
     * la hauteur de la fenêtre 
     */
    private final int HEIGHT = 1080;
    
    

    public Home() {
        //Configuration de la fenêtre
        this.setSize(WIDTH, HEIGHT);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("FindYourWay");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Création  et configurationd'un panel contenant les éléments du menu
        pane = new JPanel();
        pane.setLayout(new GridBagLayout());
        pane.setBackground(Color.LIGHT_GRAY);

        //Ajout d'un logo centré au milieu du menu
        ImageIcon logo = new ImageIcon("src/main/ressources/graphics/placeholder.png");
        logoLabel = new JLabel(logo);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        
        //Ajout des deux champs de saisie de texte
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        start = new JTextField(10);
        start.setForeground(Color.LIGHT_GRAY);
        start.setText("Départ");
        finish = new JTextField(10);
        finish.setForeground(Color.LIGHT_GRAY);
        finish.setText("Arrivée");
        inputPanel.add(start);
        inputPanel.add(finish);

        //on ajoute les FocusListener aux TextFields
        textFieldsFocusListener();

        //Ajout du bouton
        mainButton = new JButton("GO!");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(mainButton);

        JPanel controlPane = new JPanel(new BorderLayout());
        controlPane.add(inputPanel, BorderLayout.NORTH);
        controlPane.add(buttonPanel, BorderLayout.SOUTH);

        //Ajout de la case à cocher
        JCheckBox ascenseur = new JCheckBox("Ascenseurs");
        ascenseur.setHorizontalAlignment(SwingConstants.CENTER);
        controlPane.add(ascenseur, BorderLayout.CENTER);


        //Placement des éléments à l'écran
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        pane.add(logoLabel, constraints);
        constraints.gridy = 1;
        pane.add(controlPane,constraints);

        this.setContentPane(pane);

    }

    /**
     * Ajoute des FocusListener aux TextFields
     * Un FocusListener prend effet lorsque le clavier a le contrôle de l'élément
     */
    private void textFieldsFocusListener() {
        start.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (start.getText().equals("Départ")) {
                    start.setText("");
                    start.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (start.getText().isEmpty()) {
                    start.setForeground(Color.GRAY);
                    start.setText("Départ");
                }
            }
            
        });
        finish.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (finish.getText().equals("Arrivée")) {
                    finish.setText("");
                    finish.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (finish.getText().isEmpty()) {
                    finish.setForeground(Color.GRAY);
                    finish.setText("Arrivée");
                }
            }
            
        });
    }

}
