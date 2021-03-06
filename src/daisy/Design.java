package daisy;

import daisy.static_architecture.GUI.DesignPanel;
import daisy.static_architecture.elements.Element;
import daisy.static_architecture.elements.connection.Vertex;
import daisy.static_architecture.elements.listeners.DesignMouseListener;
import daisy.static_architecture.elements.views.ElementView;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Wittman
 */
public class Design {
    
    private ArrayList<Element> elements;
    private ArrayList<Vertex> vertexes;
    public DesignMouseListener designListener ;
    
    private DesignPanel view;

    public Design() {
        elements = new ArrayList<>();
        vertexes = new ArrayList<>();
        
        designListener = new DesignMouseListener();
    }

    
    public void addElement(Element element){
        daisy.Daisy.mainFrame.setPreferredSize(daisy.Daisy.mainFrame.getSize());  //resize problem when added new element
        
        elements.add(element);
        view.add(element.getView());
        view.repaint();
        
        daisy.Daisy.mainFrame.pack(); //if do not call new pannel do not repaint childern

    }
    
    public void removeElement(ElementView element){
        elements.remove(element.getElement());
        view.remove(element);
        view.repaint();
    }
    
    public void addVertex(Vertex vertex){
        //TODO implementation
        vertexes.add(vertex);
        if(vertex.getClass().equals(Vertex.class)){
            view.add(vertex.getView());
        }
        view.repaint();
        //daisy.Daisy.mainFrame.pack();
    }
    
    public void removeVertex(Vertex vertex){
        vertexes.remove(vertex);
        view.remove(vertex.getView());
        view.repaint();
    }
    
    public JPanel getView(){
        if(view == null){
            initView();
        }
        return view;
        
    }
    
    public void repaintDesign(){
        view.connections.clear();
        
        for (Vertex vertex : vertexes) {
            if(vertex.next != null){
                Path2D path = new Path2D.Double();
                Point from = vertex.next.getPosition();
                Point to = vertex.getPosition();
                path.moveTo(from.getX()+3, from.getY()+3);
                path.lineTo(to.getX()+3, to.getY()+3);
                view.connections.add((new BasicStroke(2)).createStrokedShape(path));
            }
        }
        view.repaint();

    }
    
    public void commectAllElements(){
        
    }
    
    private void initView(){
        //TODO implementation
        view = new DesignPanel(this);
        view.setPreferredSize(new Dimension(1000, 800));
        
        view.addMouseListener(designListener);
        view.addMouseMotionListener(designListener);
        
    }
}
