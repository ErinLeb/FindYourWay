import java.awt.GraphicsEnvironment;

import controller.Controleur;
import model.Batiment;

public class Main {

    public static void main(String[] args) {
        if (!GraphicsEnvironment.isHeadless()) {
            Batiment haf = new Batiment(0, 5, "src/main/ressources/csv/haf/");
            new Controleur(haf); 
        }
    }
}
