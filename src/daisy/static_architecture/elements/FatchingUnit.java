package daisy.static_architecture.elements;

import daisy.static_architecture.Instruction;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Wittman
 */
public class FatchingUnit {
    private Queue<Instruction> queue;
    private MatchingUnit machingUnit;

    //TODO реалізувати додавання процесорів адекватне
    public List<ProcessorUnit> processors;
    
    public FatchingUnit(MatchingUnit machingUnit) {
        this.machingUnit = machingUnit;
        queue = new LinkedBlockingQueue<>();
        processors = new ArrayList<>();
    }
    
    //TODO вирішити проблему послідовних клоків у елементах
    public void clock(){
        List<Instruction> new_inst = machingUnit.getReadyInstructions();
        queue.addAll(new_inst);
        
        Instruction inst = queue.peek();
        if(inst != null)
        if(inst.KOP < 0x80){
            for (ProcessorUnit proc : processors) {
                if(proc.getState() == ProcessorUnit.State.FREE){
                    proc.setInstruction(inst);
                    queue.poll();
                    return;
                }
            }
        }else{
            //TODO доробити різні оброблюючі елементи
        }
    }
    
    
}
