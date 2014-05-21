package daisy;

import daisy.static_architecture.GUI.MainFrame;
import daisy.static_architecture.DataToken;
import daisy.static_architecture.Instruction;
import daisy.static_architecture.elements.implementation.StaticPUtoMCommutator;
import daisy.static_architecture.elements.implementation.StaticFatchingUnit;
import daisy.static_architecture.elements.implementation.StaticMatchingUnit;
import daisy.static_architecture.elements.implementation.StaticProcessorUnit;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Wittman
 */
public class Daisy {
    
    public static MainFrame mainFrame;
    
    
    public Daisy() {
        mainFrame = new MainFrame();

        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
   
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        
    }
    
    public static Design getDesign(){
        return mainFrame.getCurrentTab();
    }
    
    public static void main(String [] args) throws IOException{
        Daisy d = new Daisy();
        d.mainFrame.createNewTab();
        StaticMatchingUnit MU = new StaticMatchingUnit(10);
        MU.getView().setLocation(300, 50);
        
        StaticFatchingUnit FU = new StaticFatchingUnit();
        FU.getView().setLocation(10, 70);
        
        StaticProcessorUnit PU1 = new StaticProcessorUnit();
        StaticProcessorUnit PU2 = new StaticProcessorUnit();
        
        PU1.getView().setLocation(300, 260);
        PU2.getView().setLocation(300, 400);
        
        StaticPUtoMCommutator C = new StaticPUtoMCommutator();
        C.getView().setLocation(600, 50);
        
                
        
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
        
        //C.attachElement(MU);
        //C.attachElement(PU1);
        //C.attachElement(PU2);
        
        Daisy.getDesign().addElement(MU);
        Daisy.getDesign().addElement(PU1);
        Daisy.getDesign().addElement(PU2);
        Daisy.getDesign().addElement(FU);
        Daisy.getDesign().addElement(C);
        
        while (true) {  
            System.in.read();
            FU.clock();
            MU.clock();
            PU1.clock();
            PU2.clock();
            
        }
        
        
        
    }
 
}
