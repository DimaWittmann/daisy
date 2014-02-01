package daisy.static_architecture;

import java.util.Arrays;

/**
 *
 * @author Wittman
 */
public class Instruction {
    
    
    //TODO подумати про розробку операцій з різним числом операндів
    public int id;
    
    public int KOP;
    
    public int data[];
    public boolean dataPresent[];

    
    public int destination;
    public int destPosition;

    public Instruction(int id,int KOP, int destination, int destPosition) {
        this();
        
        this.KOP = KOP;
        this.destination = destination;
        this.destPosition = destPosition;
    }

    public Instruction() {
        dataPresent = new boolean[2];
        data = new int[2];
    }
    public void setData(DataToken data){
        this.dataPresent[data.destPosition] = true;
        this.data[data.destPosition] = data.data;
    }
    
    public boolean ready(){
        return dataPresent[0] && dataPresent[1];
    }

    public Instruction(Instruction ins) {
        this.id = ins.id;
        this.KOP = ins.KOP;
        this.data = Arrays.copyOf(ins.data, ins.data.length);
        this.destination = ins.destination;
        this.destPosition = ins.destPosition;
        this.dataPresent = new boolean[2];
    }
    
    
    public void reset(){
        for (int i = 0; i < dataPresent.length; i++) {
            dataPresent[i] = false;
        }
    }
}
