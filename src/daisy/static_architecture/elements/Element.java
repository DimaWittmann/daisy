package daisy.static_architecture.elements;

import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author Wittman
 */
public abstract class Element extends AbstractTableModel{   
    /**
     * Цикл роботи елемента
     */
    public abstract void clock();
    
    /**
     * Відображення елемента
     * @return панель вигляду елемента
     */
    public abstract JPanel getView();
    
    /**
     * Під'єднати цей елемент до входу іншого
     * @param element інший елемент
     */
    public abstract void attachElement(Element element);
    
    /**
     * Від'єднати елемент
     * @param element під'єднаний елемент
     */
    public abstract void detachElement(Element element);
}
