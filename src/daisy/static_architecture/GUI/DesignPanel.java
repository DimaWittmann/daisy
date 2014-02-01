package daisy.static_architecture.GUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Wittman
 */

public class DesignPanel extends JPanel{

    public ArrayList<Shape> connections;

    public DesignPanel() {
        this.connections = new ArrayList();
        setLayout(null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Shape shape : connections) {
            g2d.fill(shape);
        }
        
    }
    
    


   
}
