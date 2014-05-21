package daisy.static_architecture.elements;

import daisy.static_architecture.Instruction;

/**
 *
 * @author Wittman
 */
public abstract class MatchingUnit extends Element{

    public abstract void loadProgram(Instruction[] program);
    
    public abstract void addIntruction(Instruction instruction);
}
