package daisy.static_architecture.elements;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.Instruction;

/**
 *
 * @author Wittman
 */
public abstract class ProcessorUnit extends Element{

    
    public abstract void setInstruction(Instruction instruction);
    
    public abstract DataToken getCulcData();
    
}
