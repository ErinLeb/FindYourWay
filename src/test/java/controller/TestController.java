package controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import view.Fenetre;
import model.Batiment;

import java.awt.GraphicsEnvironment;

import org.junit.Test;

public class TestController {

    @Test
    public void testAccueil1(){ // test de la fenêtre d'accueil avec deux noms de salles invalides
        if(!GraphicsEnvironment.isHeadless()){
            Batiment batTest = new Batiment(0, 5, "src/main/ressources/csv/test/");
            Controleur control = new Controleur(batTest);
            control.getVue().getApp().getStart().setText("Salle Invalide");
            control.getVue().getApp().getFinish().setText("Salle Invalide");
            control.majApp();
            assertTrue(control.getVue().getAccueil()==control.getVue().getApp());
        }
    }

    @Test
    public void testAccueil2(){ // test de la fenêtre d'accueil avec un nom de salle invalide
        if(!GraphicsEnvironment.isHeadless()){
            Batiment batTest = new Batiment(0, 5, "src/main/ressources/csv/test/");
            Controleur control = new Controleur(batTest);
            control.getVue().getApp().getStart().setText("308");
            control.getVue().getApp().getFinish().setText("Salle Invalide");
            control.majApp();
            assertTrue(control.getVue().getAccueil()==control.getVue().getApp());
        }
    }

    @Test
    public void testAccueil3(){ // test de la fenêtre d'accueil avec un nom de salle invalide
        if(!GraphicsEnvironment.isHeadless()){
            Batiment batTest = new Batiment(0, 5, "src/main/ressources/csv/test/");
            Controleur control = new Controleur(batTest);
            control.getVue().getApp().getStart().setText("Salle invalide");
            control.getVue().getApp().getFinish().setText("308");
            control.majApp();
            assertTrue(control.getVue().getAccueil()==control.getVue().getApp());
        }
    }

    @Test
    public void testAccueil4(){ // test de la fenêtre d'accueil deux noms de salles vides et pas de permission pour les ascenseurs
        if(!GraphicsEnvironment.isHeadless()){
            Batiment batTest = new Batiment(0, 5, "src/main/ressources/csv/test/");
            Controleur control = new Controleur(batTest);
            control.majApp();
            assertTrue(control.getVue().getAccueil()!=control.getVue().getApp());
            assertTrue(control.getVue().getApp().getStart().getText().equals("Départ"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("Arrivée"));
            assertFalse(control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    @Test
    public void testAccueil5(){ // test de la fenêtre d'accueil deux noms de salles vides et la permission pour les ascenseurs
        if(!GraphicsEnvironment.isHeadless()){
            Batiment batTest = new Batiment(0, 5, "src/main/ressources/csv/test/");
            Controleur control = new Controleur(batTest);
            control.getVue().getApp().getAscenseur().setSelected(true);
            control.majApp();
            assertTrue(control.getVue().getAccueil()!=control.getVue().getApp());
            assertTrue(control.getVue().getApp().getStart().getText().equals("Départ"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("Arrivée"));
            assertTrue(control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    @Test
    public void testAccueil6(){ // test de la fenêtre d'accueil un nom de salle valide et un nom de salle vide et la permission pour les ascenseurs
        if(!GraphicsEnvironment.isHeadless()){
            Batiment batTest = new Batiment(0, 5, "src/main/ressources/csv/test/");
            Controleur control = new Controleur(batTest);
            control.getVue().getApp().getAscenseur().setSelected(true);
            control.getVue().getApp().getStart().setText("308");
            control.majApp();
            assertTrue(control.getVue().getAccueil()!=control.getVue().getApp());
            assertTrue(control.getVue().getApp().getStart().getText().equals("308"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("Arrivée"));
            assertTrue(control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    @Test
    public void testAccueil7(){ // test de la fenêtre d'accueil un nom de salle valide et un nom de salle vide et sans permission pour les ascenseurs
        if(!GraphicsEnvironment.isHeadless()){
            Batiment batTest = new Batiment(0, 5, "src/main/ressources/csv/test/");
            Controleur control = new Controleur(batTest);
            control.getVue().getApp().getAscenseur().setSelected(false);
            control.getVue().getApp().getFinish().setText("308");
            control.majApp();
            assertTrue(control.getVue().getAccueil()!=control.getVue().getApp());
            assertTrue(control.getVue().getApp().getStart().getText().equals("Départ"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("308"));
            assertTrue(!control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    @Test
    public void testAccueil8(){ // test de la fenêtre d'accueil deux noms de salles valides et la permission pour les ascenseurs
        if(!GraphicsEnvironment.isHeadless()){
            Batiment batTest = new Batiment(0, 5, "src/main/ressources/csv/test/");
            Controleur control = new Controleur(batTest);
            control.getVue().getApp().getAscenseur().setSelected(true);
            control.getVue().getApp().getStart().setText("308");
            control.getVue().getApp().getFinish().setText("205");
            control.majApp();
            assertTrue(control.getVue().getAccueil()!=control.getVue().getApp());
            assertTrue(control.getVue().getApp().getStart().getText().equals("308"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("205"));
            assertTrue(control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    @Test
    public void testMainApp1(){ // test de l'application principale avec deux noms de salles invalides
        if(!GraphicsEnvironment.isHeadless()){
            Batiment batTest = new Batiment(0, 5, "src/main/ressources/csv/test/");
            Controleur control = new Controleur(batTest);
            control.majApp();
            Fenetre oldApp = control.getVue().getApp();
            control.getVue().getApp().getStart().setText("Salle invalide");
            control.getVue().getApp().getFinish().setText("Salle Invalide");
            control.majApp();
            assertTrue(control.getVue().getApp()!=control.getVue().getAccueil());
            assertTrue(control.getVue().getApp()==oldApp);
        }
    }

    @Test
    public void testMainApp2(){ // test de l'application principale avec un nom de salle invalide
        if(!GraphicsEnvironment.isHeadless()){
            Batiment batTest = new Batiment(0, 5, "src/main/ressources/csv/test/");
            Controleur control = new Controleur(batTest);
            control.majApp();
            Fenetre oldApp = control.getVue().getApp();
            control.getVue().getApp().getStart().setText("Salle invalide");
            control.majApp();
            assertTrue(control.getVue().getApp()!=control.getVue().getAccueil());
            assertTrue(control.getVue().getApp()==oldApp);
        }
    }

    @Test
    public void testMainApp3(){ // test de l'application principale avec un nom de salle invalide
        if(!GraphicsEnvironment.isHeadless()){
            Batiment batTest = new Batiment(0, 5, "src/main/ressources/csv/test/");
            Controleur control = new Controleur(batTest);
            control.majApp();
            Fenetre oldApp = control.getVue().getApp();
            control.getVue().getApp().getFinish().setText("Salle Invalide");
            control.majApp();
            assertTrue(control.getVue().getApp()!=control.getVue().getAccueil());
            assertTrue(control.getVue().getApp()==oldApp);
        }
    }

    @Test
    public void testMainApp4(){ // test de l'application principale avec deux noms de salles vides et sans permissions pour les ascenseurs
        if(!GraphicsEnvironment.isHeadless()){
            Batiment batTest = new Batiment(0, 5, "src/main/ressources/csv/test/");
            Controleur control = new Controleur(batTest);
            control.majApp();
            Fenetre oldApp = control.getVue().getApp();
            control.majApp();
            assertTrue(control.getVue().getApp()!=control.getVue().getAccueil());
            assertTrue(control.getVue().getApp()!=oldApp);
            assertTrue(control.getVue().getApp().getStart().getText().equals("Départ"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("Arrivée"));
            assertTrue(!control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    @Test
    public void testMainApp5(){ // test de l'application principale avec un nom de salle valide et un vide et avec permission pour les ascenseurs
        if(!GraphicsEnvironment.isHeadless()){
            Batiment batTest = new Batiment(0, 5, "src/main/ressources/csv/test/");
            Controleur control = new Controleur(batTest);
            control.majApp();
            Fenetre oldApp = control.getVue().getApp();
            control.getVue().getApp().getStart().setText("308");
            control.getVue().getApp().getAscenseur().setSelected(true);
            control.majApp();
            assertTrue(control.getVue().getApp()!=control.getVue().getAccueil());
            assertTrue(control.getVue().getApp()!=oldApp);
            assertTrue(control.getVue().getApp().getStart().getText().equals("308"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("Arrivée"));
            assertTrue(control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    @Test
    public void testMainApp6(){ // test de l'application principale avec un nom de salle valide et un vide et avec permission pour les ascenseurs
        if(!GraphicsEnvironment.isHeadless()){
            Batiment batTest = new Batiment(0, 5, "src/main/ressources/csv/test/");
            Controleur control = new Controleur(batTest);
            control.majApp();
            Fenetre oldApp = control.getVue().getApp();
            control.getVue().getApp().getFinish().setText("308");
            control.getVue().getApp().getAscenseur().setSelected(true);
            control.majApp();
            assertTrue(control.getVue().getApp()!=control.getVue().getAccueil());
            assertTrue(control.getVue().getApp()!=oldApp);
            assertTrue(control.getVue().getApp().getStart().getText().equals("Départ"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("308"));
            assertTrue(control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    @Test
    public void testMainApp7(){ // test de l'application principale avec deux noms de salles valides et sans permission pour les ascenseurs
        if(!GraphicsEnvironment.isHeadless()){
            Batiment batTest = new Batiment(0, 5, "src/main/ressources/csv/test/");
            Controleur control = new Controleur(batTest);
            control.majApp();
            Fenetre oldApp = control.getVue().getApp();
            control.getVue().getApp().getStart().setText("205");
            control.getVue().getApp().getFinish().setText("308");
            control.majApp();
            assertTrue(control.getVue().getApp()!=control.getVue().getAccueil());
            assertTrue(control.getVue().getApp()!=oldApp);
            assertTrue(control.getVue().getApp().getStart().getText().equals("205"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("308"));
            assertTrue(!control.getVue().getApp().getAscenseur().isSelected());
        }
    }
    
}
