package controller;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.awt.GraphicsEnvironment;

import view.Fenetre;


public class TestController {
    /**
     * Test de la fenêtre d'accueil avec deux noms de salles invalides
     */
    @Test
    public void testAccueil1(){ 
        if(!GraphicsEnvironment.isHeadless()){
            Controleur control = new Controleur();
            control.getVue().setVisible(false);
            control.getVue().getApp().getStart().setText("Salle Invalide");
            control.getVue().getApp().getFinish().setText("Salle Invalide");
            control.majApp();
            control.getVue().setVisible(false);
            assertTrue(control.getVue().getAccueil()==control.getVue().getApp());
        }
    }

    /**
     * Test de la fenêtre d'accueil avec un nom de salle invalide
     */
    @Test
    public void testAccueil2(){ 
        if(!GraphicsEnvironment.isHeadless()){
            Controleur control = new Controleur();
            control.getVue().setVisible(false);
            control.getVue().getApp().getStart().setText("308");
            control.getVue().getApp().getFinish().setText("Salle Invalide");
            control.majApp();
            control.getVue().setVisible(false);
            assertTrue(control.getVue().getAccueil()==control.getVue().getApp());
        }
    }

    /**
     *  Test de la fenêtre d'accueil avec un nom de salle invalide 
     */
    @Test
    public void testAccueil3(){ 
        if(!GraphicsEnvironment.isHeadless()){
            Controleur control = new Controleur();
            control.getVue().setVisible(false);
            control.getVue().getApp().getStart().setText("Salle invalide");
            control.getVue().getApp().getFinish().setText("308");
            control.majApp();
            control.getVue().setVisible(false);
            assertTrue(control.getVue().getAccueil()==control.getVue().getApp());
        }
    }

    /**
     * Test de la fenêtre d'accueil avec deux noms de salles vides et pas de permission pour les ascenseurs
     */
    @Test
    public void testAccueil4(){ 
        if(!GraphicsEnvironment.isHeadless()){
            Controleur control = new Controleur();
            control.getVue().setVisible(false);
            control.majApp();
            control.getVue().setVisible(false);
            assertTrue(control.getVue().getAccueil()!=control.getVue().getApp());
            assertTrue(control.getVue().getApp().getStart().getText().equals("Départ"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("Arrivée"));
            assertFalse(control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    /**
     *  Test de la fenêtre d'accueil avec deux noms de salles vides et la permission pour les ascenseurs
     */
    @Test
    public void testAccueil5(){ 
        if(!GraphicsEnvironment.isHeadless()){
            Controleur control = new Controleur();
            control.getVue().setVisible(false);
            control.getVue().getApp().getAscenseur().setSelected(true);
            control.majApp();
            control.getVue().setVisible(false);
            assertTrue(control.getVue().getAccueil()!=control.getVue().getApp());
            assertTrue(control.getVue().getApp().getStart().getText().equals("Départ"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("Arrivée"));
            assertTrue(control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    /**
     * Test de la fenêtre d'accueil avec un nom de salle valide et un nom de salle vide et la permission pour les ascenseurs
     */
    @Test
    public void testAccueil6(){ 
        if(!GraphicsEnvironment.isHeadless()){
            Controleur control = new Controleur();
            control.getVue().setVisible(false);
            control.getVue().getApp().getAscenseur().setSelected(true);
            control.getVue().getApp().getStart().setText("2A");
            control.majApp();
            control.getVue().setVisible(false);
            assertTrue(control.getVue().getAccueil() != control.getVue().getApp());
            assertTrue(control.getVue().getApp().getStart().getText().equals("2A"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("Arrivée"));
            assertTrue(control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    /**
     * Test de la fenêtre d'accueil avec un nom de salle valide et un nom de salle vide et sans permission pour les ascenseurs
     */
    @Test
    public void testAccueil7(){ 
        if(!GraphicsEnvironment.isHeadless()){
            Controleur control = new Controleur();
            control.getVue().setVisible(false);
            control.getVue().getApp().getAscenseur().setSelected(false);
            control.getVue().getApp().getFinish().setText("2A");
            control.majApp();
            control.getVue().setVisible(false);
            assertTrue(control.getVue().getAccueil()!=control.getVue().getApp());
            assertTrue(control.getVue().getApp().getStart().getText().equals("Départ"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("2A"));
            assertTrue(!control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    /**
     * Test de la fenêtre d'accueil avec deux noms de salles valides et la permission pour les ascenseurs
     */
    @Test
    public void testAccueil8(){ 
        if(!GraphicsEnvironment.isHeadless()){
            Controleur control = new Controleur();
            control.getVue().setVisible(false);
            control.getVue().getApp().getAscenseur().setSelected(true);
            control.getVue().getApp().getStart().setText("2A");
            control.getVue().getApp().getFinish().setText("5C");
            control.majApp();
            control.getVue().setVisible(false);
            assertTrue(control.getVue().getAccueil()!=control.getVue().getApp());
            assertTrue(control.getVue().getApp().getStart().getText().equals("2A"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("5C"));
            assertTrue(control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    /**
     * Test de l'application principale avec deux noms de salles invalides
     */
    @Test
    public void testMainApp1(){ 
        if(!GraphicsEnvironment.isHeadless()){
            Controleur control = new Controleur();
            control.getVue().setVisible(false);
            control.majApp();
            control.getVue().setVisible(false);
            Fenetre oldApp = control.getVue().getApp();
            control.getVue().getApp().getStart().setText("Salle invalide");
            control.getVue().getApp().getFinish().setText("Salle Invalide");
            control.majApp();
            control.getVue().setVisible(false);
            assertTrue(control.getVue().getApp()!=control.getVue().getAccueil());
            assertTrue(control.getVue().getApp()==oldApp);
        }
    }

    /**
     *  Test de l'application principale avec un nom de salle invalide
     */
    @Test
    public void testMainApp2(){ 
        if(!GraphicsEnvironment.isHeadless()){
            Controleur control = new Controleur();
            control.getVue().setVisible(false);
            control.majApp();
            control.getVue().setVisible(false);
            Fenetre oldApp = control.getVue().getApp();
            control.getVue().getApp().getStart().setText("Salle invalide");
            control.majApp();
            control.getVue().setVisible(false);
            assertTrue(control.getVue().getApp()!=control.getVue().getAccueil());
            assertTrue(control.getVue().getApp()==oldApp);
        }
    }

    /**
     * Test de l'application principale avec un nom de salle invalide
     */
    @Test
    public void testMainApp3(){ 
        if(!GraphicsEnvironment.isHeadless()){
            Controleur control = new Controleur();
            control.getVue().setVisible(false);
            control.majApp();
            control.getVue().setVisible(false);
            Fenetre oldApp = control.getVue().getApp();
            control.getVue().getApp().getFinish().setText("Salle Invalide");
            control.majApp();
            control.getVue().setVisible(false);
            assertTrue(control.getVue().getApp()!=control.getVue().getAccueil());
            assertTrue(control.getVue().getApp()==oldApp);
        }
    }

    /**
     * Test de l'application principale avec deux noms de salles vides et sans permissions pour les ascenseurs
     */
    @Test
    public void testMainApp4(){ 
        if(!GraphicsEnvironment.isHeadless()){
            Controleur control = new Controleur();
            control.getVue().setVisible(false);
            control.majApp();
            control.getVue().setVisible(false);
            Fenetre oldApp = control.getVue().getApp();
            control.majApp();
            control.getVue().setVisible(false);
            assertTrue(control.getVue().getApp()!=control.getVue().getAccueil());
            assertTrue(control.getVue().getApp()!=oldApp);
            assertTrue(control.getVue().getApp().getStart().getText().equals("Départ"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("Arrivée"));
            assertTrue(!control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    /**
     * Test de l'application principale avec un nom de salle valide et un vide et avec permission pour les ascenseurs
     */
    @Test
    public void testMainApp5(){ 
        if(!GraphicsEnvironment.isHeadless()){
            Controleur control = new Controleur();
            control.getVue().setVisible(false);
            control.majApp();
            control.getVue().setVisible(false);
            Fenetre oldApp = control.getVue().getApp();
            control.getVue().getApp().getStart().setText("2A");
            control.getVue().getApp().getAscenseur().setSelected(true);
            control.majApp();
            control.getVue().setVisible(false);
            assertTrue(control.getVue().getApp()!=control.getVue().getAccueil());
            assertTrue(control.getVue().getApp()!=oldApp);
            assertTrue(control.getVue().getApp().getStart().getText().equals("2A"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("Arrivée"));
            assertTrue(control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    /**
     * Test de l'application principale avec un nom de salle valide et un vide et avec permission pour les ascenseurs
     */
    @Test
    public void testMainApp6(){ 
        if(!GraphicsEnvironment.isHeadless()){
            Controleur control = new Controleur();
            control.getVue().setVisible(false);
            control.majApp();
            control.getVue().setVisible(false);
            Fenetre oldApp = control.getVue().getApp();
            control.getVue().getApp().getFinish().setText("2A");
            control.getVue().getApp().getAscenseur().setSelected(true);
            control.majApp();
            control.getVue().setVisible(false);
            assertTrue(control.getVue().getApp()!=control.getVue().getAccueil());
            assertTrue(control.getVue().getApp()!=oldApp);
            assertTrue(control.getVue().getApp().getStart().getText().equals("Départ"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("2A"));
            assertTrue(control.getVue().getApp().getAscenseur().isSelected());
        }
    }

    /**
     * Test de l'application principale avec deux noms de salles valides et sans permission pour les ascenseurs
     */
    @Test
    public void testMainApp7(){ 
        if(!GraphicsEnvironment.isHeadless()){
            Controleur control = new Controleur();
            control.getVue().setVisible(false);
            control.majApp();
            control.getVue().setVisible(false);
            Fenetre oldApp = control.getVue().getApp();
            control.getVue().getApp().getStart().setText("2A");
            control.getVue().getApp().getFinish().setText("5C");
            control.majApp();
            control.getVue().setVisible(false);
            assertTrue(control.getVue().getApp()!=control.getVue().getAccueil());
            assertTrue(control.getVue().getApp()!=oldApp);
            assertTrue(control.getVue().getApp().getStart().getText().equals("2A"));
            assertTrue(control.getVue().getApp().getFinish().getText().equals("5C"));
            assertTrue(!control.getVue().getApp().getAscenseur().isSelected());
        }
    }
}