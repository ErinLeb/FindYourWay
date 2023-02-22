import view.Home;
import java.awt.GraphicsEnvironment;

public class Main {

    public static void main(String[] args) {
        if (!GraphicsEnvironment.isHeadless()) {
            Home accueil = new Home();
            accueil.setVisible(true);
        }
    }
}