package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.util.List;

import model.Carrefour;
import model.Noeud;

/**
 * Un JPanel contenant l'image du plan actuel
 */
public class PlanPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
    //Attributs

    /**
     * L'écran de l'appli
     */
    private MainApp app;

    /**
     * l'image de l'étage à afficher
     */
    private BufferedImage planActuel;

    /**
     * Coordonnées de notre vision de l'image
     */
    private int viewX, viewY;

    /**
     * Echelle du zoom de l'image
     */
    private double scale;

    /**
     * Niveau maximal du dézoom
     */
    private double maxScale;


    //Constructeur

    /**
     * Construit le panel avec la première image
     * @param plan le premier plan par défaut
     * @param app le MainApp auquel ce panel est relié
     */
    public PlanPanel(BufferedImage plan, MainApp app) {
        this.app = app;

        setBackground(Color.WHITE);
        planActuel = plan;
        viewX = 0;
        viewY = 0;
        scale = 0;

        //Pour le zoom et le déplacement, on ajoute des MouseListener
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        
        //Quand la taille du panel change, l'échelle de l'image change aussi
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                maxScale = Math.min(getWidth() / (double) planActuel.getWidth(null), getHeight() / (double) planActuel.getHeight(null));
                scale = maxScale;
                repaint();
            }
        });

         //Dessine les points et liens de l'étage 0
         drawPointsLinks(0);
    }


    //Setters
    
    /**
     * Change l'image affichée
     * @param newImage la nouvelle image
     */
    public void setImage(BufferedImage newImage) {
        planActuel = newImage;
        
        repaint();
    }

    
    //Méthodes

    /**
     * Appelée à chaque <code>repaint()</code>, permet de redessiner le plan sur le Panel
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.scale(scale, scale);
        int x = Math.min(viewX, getWidth() - (int) (planActuel.getWidth(null) * scale));
        int y = Math.min(viewY, getHeight() - (int) (planActuel.getHeight(null) * scale));
        g2d.drawImage(planActuel, x, y, null);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        /*int notches = e.getWheelRotation();
        if (notches < 0) {
            scale *= 1.07;
        } else {
            scale /= 1.07;
        }

        
        scale = Math.max(scale, maxScale);

        repaint();*/
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        /*int dx = e.getX() - imageX;
        int dy = e.getY() - imageY;
        imageX = e.getX();
        imageY = e.getY();

        // Limit view to be within image bounds
        int maxX = (int) ((planActuel.getWidth(null) * scale) - getWidth());
        int maxY = (int) ((planActuel.getHeight(null) * scale) - getHeight());
        viewX = Math.min(viewX - dx, maxX);
        viewX = Math.max(viewX, 0);
        viewY = Math.min(viewY - dy, maxY);
        viewY = Math.max(viewY, 0);

        repaint();*/
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //Méthode non utilisée
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Méthode non utilisée
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*int x = e.getX();
        int y = e.getY();

        imageX = (int) ((x - viewX) / scale);
        imageY = (int) ((y - viewY) / scale);

        repaint();*/
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Méthode non utilisée
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //Méthode non utilisée
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //Méthode non utilisée
    }
    
    /**
     * Dessine les points représentant les noeuds et les liens qui les relient
     * @param etage le numéro de l'étage dont on dessine les noeuds et les liens
     */
    public void drawPointsLinks(int etage) {
        //Le facteur multiplicateur de l'emplacement des points
        double scale = 8.23;
        //On récupère le Graphics du plan actuel pour pouvoir dessiner dessus
        Graphics g = planActuel.getGraphics();
        Graphics2D g2d = (Graphics2D) g;

        //On définit la couleur des points
        g2d.setColor(Color.RED);
        //On définit le style des liens
        g2d.setStroke(new BasicStroke(3.0f));

        //On récupère la liste des points de l'étage actuel
        List<Noeud> noeuds = app.getControl().getNoeudsEtage(etage);
        //On parcourt les noeuds
        for (Noeud n1 : noeuds) {
            if (n1 instanceof Carrefour) {
                g2d.setColor(Color.RED);
                Carrefour carr1 = (Carrefour) n1;
                //On dessine le point correspondant
                g2d.fillOval((int) Math.round(carr1.getX()*scale), (int) Math.round(carr1.getY()*scale), 15, 15);
                g2d.setColor(Color.BLUE);
                //On parcourt les voisins
                for (Noeud n2 : carr1.getVoisins().keySet()) {
                    if (n2 instanceof Carrefour) {
                        Carrefour carr2 = (Carrefour) n2;
                        if (noeuds.contains(carr2)) {
                            //S'il fait partie du bon étage on relie les deux points
                            g2d.drawLine((int) Math.round(carr1.getX()*scale), (int) Math.round(carr1.getY()*scale), (int) Math.round(carr2.getX()*scale), (int) Math.round(carr2.getY()*scale));
                        }
                    }
                }
            }
        }
    }

}