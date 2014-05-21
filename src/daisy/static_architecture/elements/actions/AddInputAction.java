package daisy.static_architecture.elements.actions;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.elements.implementation.StaticInputUnit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Wittman
 */
public class AddInputAction extends AbstractAction{
    
    private StaticInputUnit inputUnit;

    public AddInputAction(StaticInputUnit inputUnit) {
        this.inputUnit = inputUnit;
    }
    
     

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField data = new JTextField();
        JTextField dest = new JTextField();
        JTextField nop = new JTextField();
        
        int dataI = 0;
        int destI = 0;
        int nopI = 0;
        
        nop.setText("0");
        
        while(data.getText().equals("") || dest.getText().equals("")){
            JComponent [] inputs = new JComponent[]{
                new JLabel("Data:"),
                data,
                new JLabel("Destination:"),
                dest,
                new JLabel("Operator number:"),
                nop
            };
            int i = JOptionPane.showConfirmDialog(daisy.Daisy.mainFrame, inputs, 
                    "Create New Token", JOptionPane.NO_OPTION);
            
            if( i == -1 || i == 1){
                return;
            }
            
            if( i == 2){
                data.setText("");
                dest.setText("");
            }
            
            try{
                dataI = Integer.parseInt(data.getText());
                destI = Integer.parseInt(dest.getText());
                nopI = Integer.parseInt(nop.getText());
            }catch(NumberFormatException ex){
                data.setText("");
                dest.setText("");
                nop.setText("0");
            }

        }
        DataToken token = new DataToken(destI, nopI, dataI);
        
        inputUnit.input.add(token);
        inputUnit.fireTableDataChanged();
    }

}
