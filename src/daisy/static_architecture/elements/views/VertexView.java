package daisy.static_architecture.elements.views;
import daisy.static_architecture.elements.connection.Vertex;
import daisy.static_architecture.elements.listeners.DesignMouseListener;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


/**
 *
 * @author Wittman
 */
public class VertexView extends JPanel{

    private boolean selected;
    
    private Color color;
    
    public Vertex model;
    
    public VertexView(Vertex model ) {   
        setSize(6, 6);
        DesignMouseListener listener = new DesignMouseListener();
        
        addMouseListener(listener);
        addMouseMotionListener(listener);
        setOpaque(false);   
        
        this.model = model;
        color = Color.BLACK;
    }
    
    public void seleсt(){
        selected = true;
        color = Color.RED;
        repaint();
    }
    
    public void haver(){
        if(!selected){
            color = Color.YELLOW;
            repaint();
        }
    }
    
    public void unhaver(){
        if(!selected){
            color = Color.BLACK;
            repaint();
        }
    }
    
    public void unselect(){
        selected = false;
        color = Color.BLACK;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.drawRect(0, 0, 5, 5);
        
    }

}
