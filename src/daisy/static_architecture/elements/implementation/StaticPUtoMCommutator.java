package daisy.static_architecture.elements.implementation;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.elements.Element;
import daisy.static_architecture.elements.ITokenOutput;
import daisy.static_architecture.elements.views.CommutatorView;
import java.util.ArrayList;
import java.util.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Wittman
 */
public class StaticPUtoMCommutator extends Element implements ITokenOutput{

    private List<StaticProcessorUnit> processors;
    private StaticMatchingUnit machingUnit;
    
    private CommutatorView view;

    public StaticPUtoMCommutator() {
        processors = new ArrayList<>();
    }
    

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
