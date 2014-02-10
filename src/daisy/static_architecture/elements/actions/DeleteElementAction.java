package daisy.static_architecture.elements.actions;

import daisy.static_architecture.elements.views.ElementView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Wittman
 */
public class DeleteElementAction implements ActionListener{
    private ElementView source;

    public DeleteElementAction(ElementView source) {
        this.source = source;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        daisy.Daisy.design.removeElement(source);
        daisy.Daisy.design.repaintDesign();
    }

}
