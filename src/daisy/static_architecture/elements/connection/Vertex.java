package daisy.static_architecture.elements.connection;


import daisy.static_architecture.elements.Element;
import daisy.static_architecture.elements.views.VertexView;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Представляє точку сполучення ліній 
 * в графічному режимі
 * @author Wittman
 */
public class Vertex {
    

    public Vertex precending;
    protected ArrayList<Vertex> next;
    
    protected VertexView view;

    public Vertex(Vertex precending) {
        next = new ArrayList();
        this.precending = precending;
    }

    public Vertex() {
        this(null);
    }
    
    public void addNextVertex(Vertex vertex){
        next.add(vertex);
    }
    
    public void setPredVertex(Vertex vertex){
        precending = vertex;
    }
    
    /**
     * Попередня вершина з'єднання
     * Відповідно лінія може мати розгалудження попереду, але не по заду
     * @return вершина
     */
    public Vertex predVertex(){
        return precending;
    }
    
    public JPanel getView(){
        if(view == null){
            view = new VertexView(this);
        }
        return view;
    }
    
    public Point getPosition(){
        return view.getLocation();
    }
    
    /**
     * Елемент, до якого прикріплена вершина
     * @return 
     */
    public Element getElement(){
        return null;
    }
    
}
