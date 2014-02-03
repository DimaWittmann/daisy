package daisy.static_architecture.elements.listeners;

import daisy.Design;
import daisy.static_architecture.GUI.DesignPanel;
import daisy.static_architecture.elements.connection.Vertex;
import daisy.static_architecture.elements.views.ActionElement;
import daisy.static_architecture.elements.views.ElementView;
import daisy.static_architecture.elements.views.VertexView;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;


/**
 *
 * @author Wittman
 */
public class DesignMouseListener implements MouseListener, MouseMotionListener{
    
    private static VertexView selected;
    private Point oldP;

    @Override
    public void mouseDragged(MouseEvent e) {

        if(e.getSource().getClass().equals( VertexView.class) || e.getSource() instanceof ElementView){
            switch(e.getModifiers()){

            case 0x10: //left mouse button
                JPanel panel = ((JPanel) e.getSource());
                panel.setLocation(panel.getX()-(oldP.x-e.getX()), panel.getY() - (oldP.y - e.getY()));
                daisy.Daisy.design.repaintDesign();
                break;
            }
        }
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == daisy.Daisy.design.getView() ){
            
            switch(e.getModifiers()){

            case 0x10: //left mouse button
                Vertex vertex = new Vertex();
                vertex.getView().setLocation(e.getPoint());
                
                daisy.Daisy.design.addVertex(vertex);
                break;
            case 0x4: //right mouse button
                if(selected != null){
                    selected.unselect();
                    selected = null;
                }
                break;
            case 0x12: //left mouse button + ctrl
                if(selected != null){

                }
                break;
            case 0x11:  //left mouse button + shift
                //TODO implement or not implement
                break;
            }
            
        }
        

        if(e.getSource() instanceof VertexView){
            switch(e.getModifiers()){

            case 0x10: //left mouse button
                VertexView vertex = (VertexView)e.getSource();
                if(selected != null){
                    selected.unselect();
                }
                selected = vertex;
                vertex.seleсt();
                break;
                
            case 0x4: //right mouse button
                if(selected != null){
                    vertex = (VertexView)e.getSource();
                    selected.model.addNextVertex(vertex.model);
                    vertex.model.setPredVertex(selected.model);
                    daisy.Daisy.design.repaintDesign();
                }
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() instanceof ActionElement){
            ActionElement element = (ActionElement) e.getSource();
            switch(e.getModifiers()){
                case 0x4: //right mouse button
                    if(e.isPopupTrigger()){
                        element.getPopupMenu().show(e.getComponent(), e.getX(), e.getY());
                    }
            }
        }
        
        
        if(e.getSource().getClass().equals( VertexView.class) || e.getSource() instanceof ElementView){
            oldP = new Point(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        if(e.getSource() instanceof ActionElement){
            ActionElement element = (ActionElement) e.getSource();
            switch(e.getModifiers()){
                case 0x4: //right mouse button
                    if(e.isPopupTrigger()){
                        element.getPopupMenu().show(e.getComponent(), e.getX(), e.getY());
                    }
            }
        }
        
        if(e.getSource().getClass().equals( VertexView.class) || e.getSource() instanceof ElementView){
            switch(e.getModifiers()){

            case 0x10: //left mouse button
                
                JPanel panel = ((JPanel) e.getSource());

                int x = panel.getX()-(oldP.x-e.getX());
                int y = panel.getY() - (oldP.y - e.getY());

                try {
                    int line = 2;
                    double d = (line*2) % x;
                    x = (int) ((d > line)? x+line-d:x-d);
                    d = (line*2) % y;
                    y = (int) ((d > line)? y+line-d:y-d);

                } catch (ArithmeticException ex) {
                }

                panel.setLocation(x , y);
                daisy.Daisy.design.repaintDesign();
                break;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() instanceof VertexView){
            VertexView vertex = (VertexView)e.getSource();
            vertex.haver();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() instanceof VertexView){
            VertexView vertex = (VertexView)e.getSource();
            vertex.unhaver();
        }
    }
}
