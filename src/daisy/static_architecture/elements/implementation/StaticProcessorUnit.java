package daisy.static_architecture.elements.implementation;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.Instruction;
import daisy.static_architecture.elements.Element;
import daisy.static_architecture.elements.ProcessorUnit;
import daisy.static_architecture.elements.connection.ElementVertex;
import daisy.static_architecture.elements.views.ProcessorUnitView;
import daisy.static_architecture.operations.Addition;
import daisy.static_architecture.operations.IOperation;
import daisy.static_architecture.operations.Substruction;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Wittman
 */
public class StaticProcessorUnit extends ProcessorUnit {

    /**
     * Поточна інструкція
     */
    private Instruction instruction;

    /**
     * Кількість пройдених тактів від початку обробки інструкції
     */
    private int progress;
    /**
     * Час роботи
     */
    public int workingTime;
    /**
     * Повний час
     */
    private int lifeTime;

    private State state;

    private ProcessorUnitView view;
    /**
     * Результат виконання операції
     */
    private DataToken culcData;

    //TODO реалізувати динамічне підвантаження операцій
    //TODO реалізувати отримання підтримуваних операцій
    private Map<Integer, IOperation> operations;
    private StaticFatchingUnit fatchingUnit;
    private StaticCommutator commutator;

    public StaticProcessorUnit() {
        state = State.FREE;
        initOperations();
        
        ElementVertex vertex = new ElementVertex(this);
        vertex.getView().setLocation(new Point(5, 60));
        getView().add(vertex.getView());
        daisy.Daisy.design.addVertex(vertex);
        
        vertex = new ElementVertex(this);
        vertex.getView().setLocation(new Point(240, 60));
        getView().add(vertex.getView());
        daisy.Daisy.design.addVertex(vertex);
    }

    @Override
    public void setInstruction(Instruction instruction) {
        if (getState() == State.FREE) {
            this.instruction = instruction;
            setState(State.DATA_ARRAIVED);
            this.fireTableDataChanged();
        }
    }
    
    
    @Override
    public DataToken getCulcData() {
        if (getState() == State.DONE) {
            setState(State.FREE);
            progress = 0;
            instruction = null;
            view.resetProgress();
            return culcData;
        } else {
            return null;
        }
    }
    @Override
    public ProcessorUnitView getView() {
        if (view == null) {
            view = new ProcessorUnitView(this);
            view.setSize(view.preferredSize());
        }
        return view;
    }

    private void initOperations() {
        operations = new HashMap<>();
        IOperation add = new Addition();
        IOperation sub = new Substruction();

        operations.put(add.getKOP(), add);
        operations.put(sub.getKOP(), sub);
    }

    private void setCulcData(DataToken culcData) {
        this.culcData = culcData;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        view.setStatus(state.toString());
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
    public void clock() {
        switch (state) {
            case DATA_ARRAIVED:
                setState(State.WORKING);
                view.resetProgress();
                view.setMaximumProgress(operations.get((int) instruction.KOP).executionTime());
                break;

            case WORKING:
                progress++;
                workingTime++;
                view.setProgress(progress);
                //TODO забезпечити безпеку і обробку невідомої інтсрукції
                if (operations.get((int) instruction.KOP).executionTime() == progress) {
                    setCulcData(operations.get((int) instruction.KOP).execute(instruction));
                    setState(State.DONE);
                }
                break;

            case FREE:
            case DONE:
            default:
                break;

        }

        lifeTime++;
    }

    /**
     * Перевірка, чи дана операція підтримується на процесорі
     *
     * @param KOP
     * @return
     */
    public boolean isSupported(int KOP) {
        return operations.containsKey(KOP);
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (instruction != null) {
            switch (columnIndex) {

                case 0:
                    return instruction.id;
                case 1:
                    return instruction.KOP;
                case 2:
                    return instruction.data[0];
                case 3:
                    return instruction.dataPresent[0];
                case 4:
                    return instruction.data[1];
                case 5:
                    return instruction.dataPresent[1];
                case 6:
                    return instruction.destination;
                case 7:
                    return instruction.destPosition;
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

    public enum State {

        FREE, DATA_ARRAIVED, WORKING, DONE;

        @Override
        public String toString() {
            switch (this) {
                case DATA_ARRAIVED:
                    return "DATA_ARRAIVED";
                case DONE:
                    return "DONE";
                case FREE:
                    return "FREE";
                case WORKING:
                    return "WORKING";
            }
            return "";
        }
    };
}