package daisy.static_architecture.elements.views;

import daisy.static_architecture.elements.Element;
import daisy.static_architecture.elements.actions.DeleteElementAction;
import daisy.static_architecture.elements.connection.ElementVertex;
import daisy.static_architecture.elements.listeners.DesignMouseListener;
import java.awt.Point;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author Wittman
 */
public abstract class ElementView extends JPanel implements ActionElement{
    
    protected Element element;
    protected JPopupMenu popupMenu;
    
    public ElementView(Element element) {
        this.element = element;
        DesignMouseListener mouseListener = new DesignMouseListener();
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        setLayout(null);
    }

    
    public void addPin(Point point){
        ElementVertex vertex = new ElementVertex(this.element);
        vertex.getView().setLocation(point);
        this.add(vertex.getView());

        daisy.Daisy.getDesign().addVertex(vertex);
    }
    
    protected abstract void initComponents();
    
    public Element getElement(){
        return element;
    }
    
    @Override
    public JPopupMenu getPopupMenu() {
        if(popupMenu == null){
            popupMenu = new JPopupMenu();
            JMenuItem menuItem = new JMenuItem("Delete");
            menuItem.addActionListener(new DeleteElementAction(this));
            popupMenu.add(menuItem);
        }
        
        return popupMenu;
    }
    
    protected abstract void initElementVertexes();

}
