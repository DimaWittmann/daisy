package daisy.static_architecture.GUI;

import daisy.Design;
import daisy.static_architecture.elements.views.ActionElement;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author Wittman
 */

public class DesignPanel extends JPanel implements ActionElement{

    private JPopupMenu popupMenu;
    public ArrayList<Shape> connections;
    public Design design;

    public DesignPanel(Design design) {
        this.connections = new ArrayList();
        this.design = design;
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

    @Override
    public JPopupMenu getPopupMenu() {
        if(popupMenu == null){
            popupMenu = new JPopupMenu();
            popupMenu.add(new JMenuItem("Hello"));
        }
        return popupMenu;
    }
    
    


   
}
