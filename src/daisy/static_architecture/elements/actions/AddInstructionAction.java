package daisy.static_architecture.elements.actions;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.Instruction;
import daisy.static_architecture.elements.implementation.StaticInputUnit;
import daisy.static_architecture.elements.implementation.StaticMatchingUnit;
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
public class AddInstructionAction extends AbstractAction{
    
    private StaticMatchingUnit matchingUnit;

    public AddInstructionAction(StaticMatchingUnit matchingUnit) {
        this.matchingUnit = matchingUnit;
    }
    
     

      

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField idField = new JTextField();
        JTextField KOPField = new JTextField();
        JTextField destFiled = new JTextField();
        JTextField nopField = new JTextField();
        JTextField data1Field = new JTextField();
        JTextField data2Field = new JTextField();
        
        Instruction new_instr = new Instruction();
        
        nopField.setText("0");
        
        while(idField.getText().equals("")     || 
                KOPField.getText().equals("")  ||
                destFiled.getText().equals("") ||
                nopField.getText().equals("")){
            
            JComponent [] inputs = new JComponent[]{
                new JLabel("ID:"),
                idField,
                new JLabel("KOP:"),
                KOPField,
                new JLabel("Destination:"),
                destFiled,
                new JLabel("operand №:"),
                nopField,
                new JLabel("Data 1:"),
                data1Field,
                new JLabel("Data 2:"),
                data2Field
            };
            
            int i = JOptionPane.showConfirmDialog(daisy.Daisy.mainFrame, inputs, 
                    "Create New Token", JOptionPane.NO_OPTION);
            
            if( i == -1 || i == 1){
                return;
            }
            
            if( i == 2){
                idField.setText("");
                KOPField.setText("");
                destFiled.setText("");
                nopField.setText("");
                data1Field.setText("");
                data2Field.setText("");
            }
            
            try{
                new_instr.id = Integer.parseInt(idField.getText());
                new_instr.KOP = Integer.parseInt(KOPField.getText());
                new_instr.destination = Integer.parseInt(destFiled.getText());
                new_instr.destPosition = Integer.parseInt(nopField.getText());
            }catch(NumberFormatException ex){
                idField.setText("");
                KOPField.setText("");
                destFiled.setText("");
                nopField.setText("");
                data1Field.setText("");
                data2Field.setText("");
            }
            
            if(!data1Field.getText().equals("")){
                try {
                    int data = Integer.parseInt(data1Field.getText());
                    new_instr.setData(new DataToken(new_instr.id, 0, data));
                } catch (NumberFormatException ex) {
                }
            }
            
            if(!data2Field.getText().equals("")){
                try {
                    int data = Integer.parseInt(data1Field.getText());
                    new_instr.setData(new DataToken(new_instr.id, 1, data));
                } catch (NumberFormatException ex) {
                }
            }

        }
        
        
        matchingUnit.addIntruction(new_instr);
        matchingUnit.fireTableDataChanged();
    }
}
