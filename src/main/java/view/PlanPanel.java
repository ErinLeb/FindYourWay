package view;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

/**
 * Un JPanel contenant l'image du plan actuel
 */
public class PlanPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
    /**
     * l'image de l'étage à afficher
     */
    private Image planActuel;

    /**
     * coordonnée de notre vision de l'image
     */
    private int viewX, viewY;

    /**
     * échelle du zoom de l'image
     */
    private double scale;

    /**
     * le niveau maximal du dézoom
     */
    private double maxScale;

    /**
     * Construit le panel avec la première image
     * @param plan le premier plan par défaut
     */
    public PlanPanel(Image plan) {
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
            public void componentResized(ComponentEvent e) {
                maxScale = Math.min(getWidth() / (double) planActuel.getWidth(null), getHeight() / (double) planActuel.getHeight(null));
                scale = maxScale;
                repaint();
            }
        });
    }

    /**
     * appelée à chaque <code>repaint()</code>, permet de redessiner le plan sur le Panel
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
     * change l'image affichée
     * @param newImage la nouvelle image
     */
    public void setImage(Image newImage) {
        planActuel = newImage;
        
        repaint();
    }
    
}
