package daisy;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.Instruction;
import daisy.static_architecture.elements.Commutator;
import daisy.static_architecture.elements.FatchingUnit;
import daisy.static_architecture.elements.MatchingUnit;
import daisy.static_architecture.elements.ProcessorUnit;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Wittman
 */
public class Daisy {

    public static void main(String [] args) throws IOException{
        
        
        MatchingUnit MU = new MatchingUnit(10);

        MU.getView().setLocation(300, 50);
        
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.add(MU.getView());
        
        FatchingUnit FU = new FatchingUnit(MU);
        FU.getView().setLocation(10, 70);
        frame.add(FU.getView());
        
        ProcessorUnit PU1 = new ProcessorUnit();
        ProcessorUnit PU2 = new ProcessorUnit();
        
        PU1.getView().setLocation(300, 260);
        PU2.getView().setLocation(300, 400);
        
        frame.add(PU1.getView());
        frame.add(PU2.getView());
        
        Commutator C = new Commutator(MU);
        C.getView().setLocation(600, 50);
        frame.add(C.getView());
        
        
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(900, 600);
        frame.setVisible(true);
                
        
        
        Instruction[] inst = new Instruction[4];
        inst[0] = new Instruction(0, 1, 2, 0);
        inst[0].setData(new DataToken(0, 0, 3));
        inst[0].setData(new DataToken(0, 1, 4));
        
        inst[1] = new Instruction(1, 2, 3, 1);
        inst[1].setData(new DataToken(1, 0, 25));
        inst[1].setData(new DataToken(1, 1, 1));
        
        inst[2] = new Instruction(2, 2, 3, 0);
        inst[2].setData(new DataToken(2, 1, 2));
        
        inst[3] = new Instruction(3, 1, 3, 0);
        
        MU.loadProgram(inst);
        
        FU.processors.add(PU1);
        FU.processors.add(PU2);
        
        C.processors.add(PU1);
        C.processors.add(PU2);
        
        while (true) {  
            System.in.read();
            FU.clock();
            C.clock();
            PU1.clock();
            PU2.clock();
            
        }
        
    }
}
