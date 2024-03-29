package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ExitButton extends JButton{
    // Attributs

    protected Vue view;


    // Constructeur 

    public ExitButton(Vue view){
        super("Exit");
        setBackground(new Color(140,20,46));
        setForeground(Color.WHITE);
        setOpaque(true);
        setBorderPainted(false);
        this.view = view;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.resetApp();
            }
        });
    }
}