package daisy.static_architecture.elements.listeners;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 *
 * @author Wittman
 */
@Deprecated
public class DragElementListener implements MouseListener, MouseMotionListener{
    private Point oldP;
    
    public void mousePressed(java.awt.event.MouseEvent evt) {
        oldP = new Point(evt.getX(), evt.getY());
    }
    public void mouseReleased(java.awt.event.MouseEvent evt) {
        JPanel panel = ((JPanel) evt.getSource());
        panel.setLocation(panel.getX()-(oldP.x-evt.getX()), panel.getY() - (oldP.y - evt.getY()));
        oldP = null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(oldP != null){
            JPanel panel = ((JPanel) e.getSource());
            panel.setLocation(panel.getX()-(oldP.x-e.getX()), panel.getY() - (oldP.y - e.getY()));
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
};
