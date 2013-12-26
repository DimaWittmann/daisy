package daisy;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.Instruction;
import daisy.static_architecture.elements.implementation.StaticCommutator;
import daisy.static_architecture.elements.implementation.StaticFatchingUnit;
import daisy.static_architecture.elements.implementation.StaticMatchingUnit;
import daisy.static_architecture.elements.implementation.StaticProcessorUnit;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Wittman
 */
public class Daisy {

    public static void main(String [] args) throws IOException{
        
        
        StaticMatchingUnit MU = new StaticMatchingUnit(10);

        MU.getView().setLocation(300, 50);
        
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.add(MU.getView());
        
        StaticFatchingUnit FU = new StaticFatchingUnit();
        FU.getView().setLocation(10, 70);
        frame.add(FU.getView());
        
        StaticProcessorUnit PU1 = new StaticProcessorUnit();
        StaticProcessorUnit PU2 = new StaticProcessorUnit();
        
        PU1.getView().setLocation(300, 260);
        PU2.getView().setLocation(300, 400);
        
        frame.add(PU1.getView());
        frame.add(PU2.getView());
        
        StaticCommutator C = new StaticCommutator();
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
       
        
        FU.attachElement(MU);
        FU.attachElement(PU1);
        FU.attachElement(PU2);
        
        C.attachElement(MU);
        C.attachElement(PU1);
        C.attachElement(PU2);
        
        while (true) {  
            System.in.read();
            FU.clock();
            MU.clock();
            PU1.clock();
            PU2.clock();
            
        }
        
    }
}
