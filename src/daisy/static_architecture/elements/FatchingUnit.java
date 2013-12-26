package daisy.static_architecture.elements;

import daisy.static_architecture.Instruction;

/**
 *
 * @author Wittman
 */
public abstract class FatchingUnit extends Element{
    
    public abstract void addInstruction(Instruction inst);
}
