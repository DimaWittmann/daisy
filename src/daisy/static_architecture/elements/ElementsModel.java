package daisy.static_architecture.elements;

import daisy.static_architecture.elements.implementation.StaticPUtoMCommutator;
import daisy.static_architecture.elements.implementation.StaticFatchingUnit;
import daisy.static_architecture.elements.implementation.StaticMatchingUnit;
import daisy.static_architecture.elements.implementation.StaticProcessorUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wittman
 */
@Deprecated
public class ElementsModel {

    private List<Class<?extends Element>> elements;
    public List<String> elementsName;

    public ElementsModel() {
        elements = new ArrayList<>();
        elementsName = new ArrayList<>();
        initStaticElments();
    }
    
    
    public void loadNewElment(Class<?extends Element> elementClass){
        elements.add(elementClass);
    }
    
    
    public void removeNewElment(Class<?extends Element> elementClass){
        elements.remove(elementClass);
    }
    
    public Element getNewElementInstance(String name) throws InstantiationException, IllegalAccessException{
        if(elementsName.contains(name)){
            return elements.get(elementsName.indexOf(name)).newInstance();
        }else{
            return null;
        }
    }
    
    
            
    private void initStaticElments(){
        elementsName.add("Commutator");
        elementsName.add("Fatching Unit");
        elementsName.add("Matching Unit");
        elementsName.add("Processor Unit");
        
        elements.add(StaticPUtoMCommutator.class);
        elements.add(StaticFatchingUnit.class);
        elements.add(StaticMatchingUnit.class);
        elements.add(StaticProcessorUnit.class);
    }

}
