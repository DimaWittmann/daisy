package daisy.static_architecture.elements.implementation;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.Instruction;
import daisy.static_architecture.elements.Element;
import daisy.static_architecture.elements.FatchingUnit;
import daisy.static_architecture.elements.views.FatchingUnitView;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Wittman
 */
public class StaticFatchingUnit extends FatchingUnit {

    private Queue<Instruction> queue;

    private FatchingUnitView view;

    private StaticMatchingUnit machingUnit;
    private List<StaticProcessorUnit> processors;

    public StaticFatchingUnit() {
        queue = new LinkedList<>();
        processors = new ArrayList<>();
    }

    //TODO вирішити проблему послідовних клоків у елементах
    @Override
    public void clock() {

        Instruction inst = queue.peek();
        if (inst != null) {
            for (StaticProcessorUnit proc : processors) {
                if (proc.getState() == StaticProcessorUnit.State.FREE && proc.isSupported(inst.KOP)) {
                    proc.setInstruction(inst);
                    queue.poll();
                    this.fireTableDataChanged();
                    getView().setCounter(queue.size());
                    return;
                }
            }
        }
        
    }

    @Override
    public void addInstruction(Instruction inst){
        queue.add(inst);
        this.fireTableDataChanged();
        getView().setCounter(queue.size());
    }
    
    @Override
    public void attachElement(Element element) {
        if(element == this){
            return;
        }
        
        if (element instanceof StaticMatchingUnit) {
            if(machingUnit != element){
                machingUnit = (StaticMatchingUnit) element;
                (((StaticMatchingUnit) element)).attachElement(this);
            }
        }

        if (element instanceof StaticProcessorUnit) {
            if(!processors.contains((StaticProcessorUnit)element)){
                processors.add((StaticProcessorUnit) element);
                element.attachElement(this);
            }
        }
    }

    @Override
    public void detachElement(Element element) {
        if (element instanceof StaticMatchingUnit) {
            if (machingUnit != null) {
                machingUnit.detachElement(this);
                machingUnit = null;
            }
            
        }

        if (element instanceof StaticProcessorUnit) {
            if(processors.contains(element)){
                processors.remove((StaticProcessorUnit) element);
                element.detachElement(this);
            }
            
        }
    }

    @Override
    public FatchingUnitView getView() {
        if (view == null) {
            view = new FatchingUnitView(this);
        }
        return view;
    }

    @Override
    public int getRowCount() {
        return queue.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<Instruction> l = new ArrayList<>(queue);
        switch (columnIndex) {

            case 0:
                return l.get(rowIndex).id;
            case 1:
                return l.get(rowIndex).KOP;
            case 2:
                return l.get(rowIndex).data[0];
            case 3:
                return l.get(rowIndex).dataPresent[0];
            case 4:
                return l.get(rowIndex).data[1];
            case 5:
                return l.get(rowIndex).dataPresent[1];
            case 6:
                return l.get(rowIndex).destination;
            case 7:
                return l.get(rowIndex).destPosition;
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

    @Override
    public void detachAllElements() {
        processors.clear();
        machingUnit = null;
    }

}
