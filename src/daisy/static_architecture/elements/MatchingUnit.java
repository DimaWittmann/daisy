package daisy.static_architecture.elements;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.Instruction;
import daisy.static_architecture.elements.views.MatchingUnitView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author Wittman
 */
public class MatchingUnit extends AbstractTableModel{
    private Instruction instructions[];
    private MatchingUnitView view;
    
    public MatchingUnit(int size) {
        instructions = new Instruction[size];
    }
    
    public synchronized void addDataToken(DataToken data){
        instructions[data.destination].setData(data);
        this.fireTableDataChanged();
    }
    
    public synchronized List<Instruction> getReadyInstructions(){
        List<Instruction> readyInstructions = new ArrayList<>();
        for (Instruction instruction : instructions) {
            if (instruction != null && instruction.ready()) {
                instruction.reset();
                readyInstructions.add(new Instruction(instruction));
            }
        }
        this.fireTableDataChanged();
        return readyInstructions;
    }
    
    public void loadProgram(Instruction [] program){
        System.arraycopy(program, 0, instructions, 0, (instructions.length>program.length)?program.length:instructions.length);
        this.fireTableDataChanged();
    }

    public MatchingUnitView getView() {
        return view;
    }

    
    private void initView(){
        view = new MatchingUnitView(this);
        view.setSize(view.getPreferredSize());
    }
    @Override
    public int getRowCount() {
        return instructions.length;
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(instructions[rowIndex] != null)
        switch(columnIndex){
            
            case 0:
                return instructions[rowIndex].id;
            case 1:
                return instructions[rowIndex].KOP;
            case 2:
                return instructions[rowIndex].data[0];
            case 3:
                return instructions[rowIndex].dataPresent[0];
            case 4:
                return instructions[rowIndex].data[1];
            case 5:
                return instructions[rowIndex].dataPresent[1];
            case 6:
                return instructions[rowIndex].destination;
            case 7:
                return instructions[rowIndex].destPosition;
        }
        return null;
        
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch(columnIndex){
            
            case 0:
                return "id";
            case 1:
                return "KOP";
            case 2:
                return "data1";
            case 3:
                return "";
            case 4:
                return "data2";
            case 5:
                return "";
            case 6:
                return "dest";
            case 7:
                return "destPos";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            
            case 0:
            case 1:
            case 2:
            case 4:
            case 6:
            case 7:
                return int.class;
            case 3:
            case 5:
                return Boolean.class;
            
        }
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        
        //TODO реалізувати
        return false;
    }

    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
        //TODO реалізувати
    }

}
