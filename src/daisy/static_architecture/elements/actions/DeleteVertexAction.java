package daisy.static_architecture.elements.actions;

import daisy.static_architecture.elements.connection.Vertex;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Wittman
 */
public class DeleteVertexAction implements ActionListener{

    protected Vertex source;

    public DeleteVertexAction(Vertex source) {
        this.source = source;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        daisy.Daisy.getDesign().removeVertex(source);
        daisy.Daisy.getDesign().repaintDesign();
    }
}
