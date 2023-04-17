package view;

import java.awt.Color;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 * Une boîte de saisie de texte dans laquelle un texte de fond est inscrit. 
 * Ce dernier disparaît quand le focus est sur la boîte.
 */
public class TextFieldBox extends JTextField {

    /**
     * Vue associée à ce TextFieldBox
     */
    private Vue view;

    /**
     * Construit une zone de saisie de texte
     * @param text le texte de fond de la boîte
     * @param columns la longueur de la boîte
     * @param view la vue associée à ce TextFieldBox
     */
    public TextFieldBox(String text, int columns, Vue view) {
        super(text, columns);
        this.view = view;
        setForeground(Color.LIGHT_GRAY);

        //Si on clique sur une zone de texte, le texte par défaut s'efface, si on en sort sans rien écrire, il revient
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(text)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setForeground(Color.LIGHT_GRAY);
                    setText(text);
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

    /**
     * Détermine si le texte contenu dans la TextFieldBox correspond à une salle du batiment
     * @return vrai si la salle existe dans le batiment et faux sinon
     */
    public boolean estSalle(){
        return this.view.getControl().estSalle(this.getText());
    }
}