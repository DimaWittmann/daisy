package daisy.static_architecture.operations;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.Instruction;

/**
 *
 * @author Wittman
 */
public interface IOperation {
    
    /**
     * Повертає код операції
     * @return 
     */
    public int getKOP();
    
    /**
     * Повертрає час виконання операції
     * @return 
     */
    public int executionTime();
    
    
    public DataToken execute(Instruction instruction);
    
    

}
