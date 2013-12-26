package daisy.static_architecture.elements;

import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author Wittman
 */
public abstract class Element extends AbstractTableModel{
    
    
    public abstract void clock();
    
    
    public abstract JPanel getView();
    
    
    public abstract void attachElement(Element element);
    
   
    public abstract void detachElement(Element element);
}
