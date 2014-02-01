package daisy.static_architecture.elements.implementation;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.Instruction;
import daisy.static_architecture.elements.Element;
import daisy.static_architecture.elements.MatchingUnit;
import daisy.static_architecture.elements.connection.ElementVertex;
import daisy.static_architecture.elements.views.MatchingUnitView;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wittman
 */
public class StaticMatchingUnit extends MatchingUnit {

    private Instruction instructions[];
    private MatchingUnitView view;

    private StaticFatchingUnit fatchingUnit;
    private StaticCommutator commutator;

    public StaticMatchingUnit() {
        this(32);
    }

    public StaticMatchingUnit(int size) {
        instructions = new Instruction[size];
        
        ElementVertex vertex = new ElementVertex(this);
        vertex.getView().setLocation(new Point(5, 100));
        getView().add(vertex.getView());
        daisy.Daisy.design.addVertex(vertex);
        
        vertex = new ElementVertex(this);
        vertex.getView().setLocation(new Point(240, 100));
        getView().add(vertex.getView());
        daisy.Daisy.design.addVertex(vertex);
    }

    @Override
    public void loadProgram(Instruction[] program) {
        System.arraycopy(program, 0, instructions, 0, (instructions.length > program.length) ? program.length : instructions.length);
        this.fireTableDataChanged();
    }

    @Override
    public void attachElement(Element element) {
        if(element == this){
            return;
        }
        
        if (element instanceof StaticFatchingUnit) {
            if(element != fatchingUnit){
                fatchingUnit = (StaticFatchingUnit) element;
                element.attachElement(this);
            }
        }

        if (element instanceof StaticCommutator) {
            if(element != commutator){
                commutator = (StaticCommutator) element;
                element.attachElement(this);
            }
        }
    }

    @Override
    public void detachElement(Element element) {
        if (element instanceof StaticFatchingUnit) {
            if(fatchingUnit != null){
                fatchingUnit.detachElement(this);
                fatchingUnit = null;
            }
            
        }

        if (element instanceof StaticProcessorUnit) {
            if( commutator != null){
                commutator.detachElement(this);
                commutator = null;
            }
            
        }
    }

    @Override
    public MatchingUnitView getView() {
        if (view == null) {
            view = new MatchingUnitView(this);
            view.setSize(view.preferredSize());
        }
        return view;
    }

    @Override
    public void clock() {
        List<Instruction> inst = getReadyInstructions();
        if (!inst.isEmpty()) {
            inst.get(0).reset();
            fatchingUnit.addInstruction(new Instruction(inst.get(0)));
        }

        DataToken data = commutator.getReadyData();
        if (data != null) {
            addDataToken(data);
        }

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
        if (instructions[rowIndex] != null) {
            switch (columnIndex) {

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
        }
        return null;

    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {

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
        switch (columnIndex) {

            case 0:
            case 1:
            case 2:
            case 4:
            case 6:
            case 7:
                return Integer.class;
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

    private List<Instruction> getReadyInstructions() {
        List<Instruction> readyInstructions = new ArrayList<>();
        for (Instruction instruction : instructions) {
            if (instruction != null && instruction.ready()) {
                readyInstructions.add(instruction);
            }
        }
        this.fireTableDataChanged();
        return readyInstructions;
    }

    private void addDataToken(DataToken data) {
        instructions[data.destination].setData(data);
        this.fireTableDataChanged();
    }
}
