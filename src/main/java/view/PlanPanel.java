package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.AlphaComposite;
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

import java.util.ArrayList;
import java.util.List;
import model.Carrefour;
import model.Noeud;

/**
 * Un JPanel contenant l'image du plan actuel
 */
public class PlanPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, Runnable {
    //Attributs

    /**
     * L'écran de l'appli
     */
    private MainApp app;

    /**
     * L'image de l'étage à afficher
     */
    private BufferedImage image;

    /**
     * Les plans vierges de tout dessin
     */
    private ArrayList<BufferedImage> blankPlans;

    /**
     * Les plans avec les liens et les noeuds dessinés
     */
    private ArrayList<BufferedImage> debugPlans;

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

    /**
     * Le degré de transparence de la ligne
     */
    private float alpha = 0.0f;

    /**
     * Indique si la ligne apparaît ou s'estompe
     */
    private boolean increasing = true;


    //Constructeur

    /**
     * Construit le panel avec la première image
     * @param plan le premier plan par défaut
     * @param app le MainApp auquel ce panel est relié
     */
    public PlanPanel(BufferedImage plan, MainApp app) {
        this.app = app;

        setBackground(Color.WHITE);
        image = plan;

        blankPlans = new ArrayList<BufferedImage>();
        for (BufferedImage image : app.getListImages()) {
            blankPlans.add(image);
        }

        initDebugPlans();

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
                maxScale = Math.min(getWidth() / (double) image.getWidth(null), getHeight() / (double) image.getHeight(null));
                scale = maxScale;
                repaint();
            }
        });

        //Dessine les points et liens de l'étage 0 si l'app est en mode debug
        drawDebug(0);

        //Démarre l'animation 
        Thread thread = new Thread(this);
        thread.start();
    }

    private void initDebugPlans() {
        debugPlans = new ArrayList<BufferedImage>();
        for (int i = 0; i < app.getListImages().size(); i++) {
            BufferedImage image = app.getListImages().get(i);
            // On crée une copie de l'image sur laquelle on va dessiner
            BufferedImage modifImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
            Graphics2D g2dmodif = modifImage.createGraphics();
            g2dmodif.drawImage(image, 0, 0, null);
            
            // Le facteur multiplicateur de l'emplacement des points
            double scale = 8.23;

            //On définit la couleur des points
            g2dmodif.setColor(Color.RED);

            //On définit le style des liens
            g2dmodif.setStroke(new BasicStroke(3.0f));

            //On récupère la liste des points de l'étage actuel
            List<Noeud> noeudsEtageActuel = app.getControl().getNoeudsEtage(i);
            //On parcourt les noeuds
            for (Noeud n1 : noeudsEtageActuel) {
                if (n1 instanceof Carrefour) {
                    g2dmodif.setColor(Color.RED);
                    Carrefour carr1 = (Carrefour) n1;
                    //On dessine le point correspondant
                    g2dmodif.fillOval((int) Math.round(carr1.getX()*scale), (int) Math.round(carr1.getY()*scale), 15, 15);
                    g2dmodif.setColor(Color.BLUE);
                    //On parcourt les voisins
                    for (Noeud n2 : carr1.getVoisins().keySet()) {
                        if (n2 instanceof Carrefour) {
                            Carrefour carr2 = (Carrefour) n2;
                            if (noeudsEtageActuel.contains(carr2)) {
                                //S'il fait partie du bon étage on relie les deux points
                                g2dmodif.drawLine((int) Math.round(carr1.getX()*scale), (int) Math.round(carr1.getY()*scale), (int) Math.round(carr2.getX()*scale), (int) Math.round(carr2.getY()*scale));
                            }
                        }
                    }
                }
            }
            debugPlans.add(modifImage);
            g2dmodif.dispose();
        }
    }


    //Setters
    
    /**
     * Change l'image affichée
     * @param newImage la nouvelle image
     */
    public void setImage(BufferedImage newImage) {
        image = newImage;
        
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
        int x = Math.min(viewX, getWidth() - (int) (image.getWidth(null) * scale));
        int y = Math.min(viewY, getHeight() - (int) (image.getHeight(null) * scale));
        g2d.drawImage(image, x, y, null);

        if (app.getChemin() != null) {
            drawPath(app.getEtageActuel());
        } else if (app.getSalle() != null) {
            drawRoom(app.getEtageActuel(), app.getSalle());
        }
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
     * Dessine les points représentant les noeuds et les liens qui les relient, si l'app est en mode debug
     * @param etage le numéro de l'étage dont on dessine les noeuds et les liens
     */
    public void drawDebug(int etage) {
        if (app.getDebug()) {
            setImage(debugPlans.get(etage));
            if (app.getChemin() != null) {
                drawPath(etage);
            } else if (app.getSalle() != null) {
                drawRoom(etage, app.getSalle());
            }
        }
    }

    /**
     * Efface les liens et les points de l'étage {@code etage}
     * @param etage
     */
    public void eraseDebug(int etage) {
        setImage(blankPlans.get(etage));
        if (app.getChemin() != null) {
            drawPath(etage);
        } else if (app.getSalle() != null) {
            drawRoom(etage, app.getSalle());
        }
    }

    /**
     * Dessine le chemin sur l'étage etage donné par l'algorithme de Dijkstra
     * @param etage L'étage sur lequel on veut afficher le trajet
     */
    public void drawPath(int etage) {
        //On efface les éventuelles lignes ou points déjà présents sur le plan en réinitialisant le plan
        if (app.getDebug()) {
            setImage(debugPlans.get(etage));
        } else {
            setImage(blankPlans.get(etage));
        }
        
        //on crée une copie de l'image sur laquelle on va dessiner
        BufferedImage modifImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g2dmodif = modifImage.createGraphics();
        g2dmodif.drawImage(image, 0, 0, null);
        
        //Le facteur multiplicateur de l'emplacement des points
        double scale = 8.23;

        //On définit la couleur du chemin
        g2dmodif.setColor(new Color(232, 70, 114));

        //On définit le style du chemin
        g2dmodif.setStroke(new BasicStroke(10.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2dmodif.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        List<Noeud> noeuds = app.getChemin().getNoeuds();
        //on parcourt le chemin en dessinant les liens entre les points de l'étage actuel
        for (int i = 0; i < noeuds.size() - 1; i++) {
            if (noeuds.get(i) instanceof Carrefour && noeuds.get(i+1) instanceof Carrefour) {
                Carrefour depart = (Carrefour) noeuds.get(i);
                Carrefour arrivee = (Carrefour) noeuds.get(i+1);
                if (depart.getEtage() == etage && arrivee.getEtage() == etage) {
                    int xA = (int) Math.round(depart.getX()*scale);
                    int yA = (int) Math.round(depart.getY()*scale);
                    int xB = (int) Math.round(arrivee.getX()*scale);
                    int yB = (int) Math.round(arrivee.getY()*scale);

                    g2dmodif.drawLine(xA, yA, xB, yB);
                }
            }
        }
        //on met l'image modifiée à l'écran
        setImage(modifImage);
        g2dmodif.dispose();
    }

    /**
     * Dessine les portes d'une salle {@code room} se trouvant à {@code etage}
     * @param etage
     * @param room
     */
    public void drawRoom(int etage, Noeud room) {
        //On efface les éventuelles lignes ou points déjà présents sur le plan en réinitialisant le plan
        if (app.getDebug()) {
            setImage(debugPlans.get(etage));
        } else {
            setImage(blankPlans.get(etage));
        }
        
        //On crée une copie de l'image sur laquelle on va dessiner
        BufferedImage modifImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g2dmodif = modifImage.createGraphics();
        g2dmodif.drawImage(image, 0, 0, null);
        
        //Le facteur multiplicateur de l'emplacement des points
        double scale = 8.23;

        //On définit le style des points
        g2dmodif.setColor(new Color(232, 70, 114));
        g2dmodif.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        //Pour chaque voisin de la salle, on affiche un point à son emplacement
        for (Noeud n : room.getVoisins().keySet()) {
            if (n instanceof Carrefour) {
                Carrefour carr = (Carrefour) n;
                if (carr.getEtage() == etage) {
                    g2dmodif.fillOval((int) Math.round(carr.getX()*scale), (int) Math.round(carr.getY()*scale), 25, 25);
                }
            }
        }
        //on met l'image modifiée à l'écran
        setImage(modifImage);
        g2dmodif.dispose();
    }

    @Override
    public void run() {
        while (true) {
            if (increasing) { //Quand le dessin s'assombrit
                alpha += 0.05f; //On incrémente l'opacité
                if (alpha >= 1.0f) { //Si l'opacité dépasse 1 on le ramène à 1 et le dessin s'éclaircit
                    alpha = 1.0f;
                    increasing = false;
                }
            } else { //Quand le dessin s'éclaircit
                alpha -= 0.05f; //On décrémente l'opacité
                if (alpha <= 0.0f) { //Si l'opacité tombe en dessous de 0 on la ramène à 0 et le dessin s'assombrit
                    alpha = 0.0f;
                    increasing = true;
                }
            }
            repaint();
            try {
                Thread.sleep(50); //5 millisecondes entre chaque maj du dessin
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}