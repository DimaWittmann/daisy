package daisy;

import daisy.static_architecture.GUI.ElementsPanel;
import daisy.static_architecture.elements.ElementsModel;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Wittman
 */
public class Daisy {
    
    public static Design design;
    public static JFrame frame;
    public static ElementsPanel buttons;

    public Daisy() {
        
        design = new Design();
        buttons = new ElementsPanel(new ElementsModel());
        
        JPanel mainPanel= new JPanel(new BorderLayout());

        mainPanel.add(design.getView(), BorderLayout.CENTER);
        mainPanel.add(buttons, BorderLayout.WEST);
   
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
        
    }
    
    
    
    public static void main(String [] args){
        Daisy d = new Daisy();
        
    }
    
    
//
//    public static void main(String [] args) throws IOException{
//        
//        
//        StaticMatchingUnit MU = new StaticMatchingUnit(10);
//
//        MU.getView().setLocation(300, 50);
//        
//        JPanel panel = new JPanel();
//        panel.setLayout(null);
//        panel.setPreferredSize(new Dimension(800, 800));
//        panel.add(MU.getView());
//        panel.setBorder(new WindowsBorders.DashedBorder(Color.yellow));
//        
//        StaticFatchingUnit FU = new StaticFatchingUnit();
//        FU.getView().setLocation(10, 70);
//        panel.add(FU.getView());
//        
//        StaticProcessorUnit PU1 = new StaticProcessorUnit();
//        StaticProcessorUnit PU2 = new StaticProcessorUnit();
//        
//        PU1.getView().setLocation(300, 260);
//        PU2.getView().setLocation(300, 400);
//        
//        panel.add(PU1.getView());
//        panel.add(PU2.getView());
//        
//        StaticCommutator C = new StaticCommutator();
//        C.getView().setLocation(600, 50);
//        panel.add(C.getView());
//        
//        DesignPanel designPanel = new DesignPanel();
//        designPanel.add(panel, BorderLayout.CENTER);
//        
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(designPanel);
//        frame.pack();
//        frame.setVisible(true);
//                
//        
//        
//        Instruction[] inst = new Instruction[4];
//        inst[0] = new Instruction(0, 1, 2, 0);
//        inst[0].setData(new DataToken(0, 0, 3));
//        inst[0].setData(new DataToken(0, 1, 4));
//        
//        inst[1] = new Instruction(1, 2, 3, 1);
//        inst[1].setData(new DataToken(1, 0, 25));
//        inst[1].setData(new DataToken(1, 1, 1));
//        
//        inst[2] = new Instruction(2, 2, 3, 0);
//        inst[2].setData(new DataToken(2, 1, 2));
//        
//        inst[3] = new Instruction(3, 1, 3, 0);
//        
//        MU.loadProgram(inst);
//       
//        
//        FU.attachElement(MU);
//        FU.attachElement(PU1);
//        FU.attachElement(PU2);
//        
//        C.attachElement(MU);
//        C.attachElement(PU1);
//        C.attachElement(PU2);
//        
//        while (true) {  
//            System.in.read();
//            FU.clock();
//            MU.clock();
//            PU1.clock();
//            PU2.clock();
//            
//        }
//        
//    }
}
