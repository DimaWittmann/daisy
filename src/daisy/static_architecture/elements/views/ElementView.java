package daisy.static_architecture.elements.views;

import daisy.static_architecture.elements.listeners.DesignMouseListener;
import javax.swing.JPanel;

/**
 *
 * @author Wittman
 */
public abstract class ElementView extends JPanel{
    public ElementView() {
        DesignMouseListener mouseListener = new DesignMouseListener();
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        setLayout(null);
    }

    
    protected abstract void initComponents();

}
