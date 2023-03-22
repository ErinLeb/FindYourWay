package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

/**
 * Un bouton étiqueté GO! permettant de changer d'écran/lancer la recherche d'itinéraire
 */
public class GoButton extends JButton {

    /**
     * Crée un bouton qui permet de changer d'écran/lancer la recherche d'itinéraire
     * @param view la vue associée au bouton
     */
    public GoButton(Vue view) {
        super("GO!");
        //On change d'écran quand on clique sur le bouton, ou bien quand on appuie sur la touche ENTRÉE quand la selection est sur le bouton
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(view.getControl().verifGoButton()){ // on vérifie que les textes des TextFieldBox sont corrects et si oui, on met à jour la vue
                    view.getControl().majApp();
                    view.setContentPane(view.getApp());
                    view.repaint();
                    view.setVisible(true);
                }
                
            }
            
        });

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                //Méthode non utilisée
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(view.getControl().verifGoButton()){ // on vérifie que les textes des TextFieldBox sont corrects et si oui, on met à jour la vue
                        view.getControl().majApp();
                        view.setContentPane(view.getApp());
                        view.repaint();
                        view.setVisible(true);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //Méthode non utilisée
            }
            
        });

    }
    
}
