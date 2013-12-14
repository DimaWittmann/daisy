package daisy.static_architecture.operations;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.Instruction;

/**
 *
 * @author Wittman
 */
public class Substruction implements IOperation{

    @Override
    public int getKOP() {
        return 2;
    }

    @Override
    public int executionTime() {
        return 5;
    }

    @Override
    public DataToken execute(Instruction instruction) {
        int dataRusult = instruction.data[0]-instruction.data[1];
        DataToken result = new DataToken(instruction.destination, instruction.destPosition, dataRusult);
        return result;
    }

}
