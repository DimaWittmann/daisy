package daisy.static_architecture.elements;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.elements.views.CommutatorView;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wittman
 */
public class Commutator{
    //TODO зробити загальний клас з методами додавання вхідних і вихідни конекшенів
    public List<ProcessorUnit> processors;
    public MatchingUnit machingUnit;
    
    private CommutatorView view;

    public Commutator(MatchingUnit machingUnit) {
        processors = new ArrayList<>();
        this.machingUnit = machingUnit;
    }
    
    public void clock(){
        
        for (ProcessorUnit processor : processors) {
            if(processor.getState() == ProcessorUnit.State.DONE){
                DataToken data = processor.getCulcData();
                machingUnit.addDataToken(data);
                return;
            }
        }
    }
    
    public CommutatorView getView(){
        if(view == null){
            view = new CommutatorView();
            view.setSize(view.getPreferredSize());
        }
        return view;
    }
}
