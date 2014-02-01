package daisy.static_architecture.elements.connection;

import daisy.static_architecture.elements.Element;
import daisy.static_architecture.elements.views.ElementVertexView;
import daisy.static_architecture.elements.views.VertexView;
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author Wittman
 */
public class ElementVertex extends Vertex{

    protected Element element;

    public ElementVertex(Element element) {
        super();
        this.element = element;
    }
    
    
    
    @Override
    public Point getPosition() {
        Point global = view.getParent().getLocation();
        Point local = view.getLocation();
        return new Point(global.x+local.x, global.y+local.y);
    }
    
    @Override
    public JPanel getView(){
        if(view == null){
            view = new ElementVertexView(this);
        }
        return view;
    }

    @Override
    public Element getElement() {
        return element;
    }
    
    
    
}
