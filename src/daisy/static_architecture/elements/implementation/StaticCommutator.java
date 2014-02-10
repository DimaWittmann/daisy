package daisy.static_architecture.elements.implementation;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.elements.Commutator;
import daisy.static_architecture.elements.Element;
import daisy.static_architecture.elements.connection.ElementVertex;
import daisy.static_architecture.elements.views.CommutatorView;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Wittman
 */
public class StaticCommutator extends Commutator{

    private List<StaticProcessorUnit> processors;
    private StaticMatchingUnit machingUnit;
    
    private CommutatorView view;

    public StaticCommutator() {
        processors = new ArrayList<>();
        

    }
    
    @Override
    public DataToken getReadyData() {
        for (StaticProcessorUnit processor : processors) {
            if(processor.getState() == StaticProcessorUnit.State.DONE){
                DataToken data = processor.getCulcData();
                return data;
            }
        }
        return null;
    }
    
    @Override
    @Deprecated
    public void clock(){
        
    }
    
    @Override
    public CommutatorView getView(){
        if(view == null){
            view = new CommutatorView(this);
            view.setSize(view.preferredSize());
            
            ElementVertex vertex = new ElementVertex(this);
            vertex.getView().setLocation(new Point(5, 100));
            view.add(vertex.getView());

            daisy.Daisy.design.addVertex(vertex);

            vertex = new ElementVertex(this);
            vertex.getView().setLocation(new Point(65, 100));
            view.add(vertex.getView());

            daisy.Daisy.design.addVertex(vertex);
        }
        return view;
    }

    
    @Override
    public void attachElement(Element element) {
        if(element == this){
            return;
        }
        
        if(element instanceof StaticProcessorUnit){
            if(!processors.contains(element)){
                processors.add((StaticProcessorUnit) element);
                element.detachElement(this);
            }    
        }
        //TODO зробити перевірку чи це є той самий об'єкт
        if(element instanceof StaticMatchingUnit){
            if(machingUnit != element){
                machingUnit = (StaticMatchingUnit) element;
                element.attachElement(this);
            }
        }
        
    }

    @Override
    public void detachElement(Element element) {
        if(element instanceof StaticProcessorUnit){
            if(processors.contains(element)){
                processors.remove((StaticProcessorUnit) element);
                element.detachElement(this);
            }
        }
        if(element instanceof StaticMatchingUnit){
            if (machingUnit != null) {
                machingUnit.detachElement(element);
                machingUnit = null;
            }
            
        }
    }

    @Override 
    @Deprecated
    public int getRowCount() {
        throw new NotImplementedException();
    }

    @Override 
    @Deprecated
    public int getColumnCount() {
        throw new NotImplementedException();
    }

    @Override 
    @Deprecated
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new NotImplementedException();
    }

    @Override
    public void detachAllElements() {
        processors.clear();
        machingUnit = null;
    }


}
