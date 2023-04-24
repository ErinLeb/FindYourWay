import java.awt.GraphicsEnvironment;

import controller.Controleur;

public class Main {

    public static void main(String[] args) {
        if (!GraphicsEnvironment.isHeadless()) {
            Controleur control = new Controleur(); 
        }
    }
}