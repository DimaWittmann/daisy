package daisy.static_architecture.elements.implementation;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.elements.Element;
import daisy.static_architecture.elements.views.InputView;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JPanel;

/**
 *
 * @author Wittman
 */
public class StaticInputUnit extends Element{
    
    public Queue<DataToken> input;
    private InputView view;

    public StaticInputUnit() {
        input = new LinkedList<>();
    }
    
    

    @Override
    public void clock() {
        //TODO implement
    }

    @Override
    public JPanel getView() {
        if(view == null){
            view = new InputView(this);
        }
        return view;
    }

    @Override
    public void attachElement(Element element) {
        //TODO implement
    }

    @Override
    public void detachElement(Element element) {
        //TODO implement
    }

    @Override
    public void detachAllElements() {
        //TODO implement
    }

    @Override
    public int getRowCount() {
        return input.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex >= getRowCount()){
            return null;
        }
        
        switch(columnIndex){
            case 0:
                return ((List<DataToken>)input).get(rowIndex).data;
            case 1:
                return ((List<DataToken>)input).get(rowIndex).destPosition;
            case 2:
                return ((List<DataToken>)input).get(rowIndex).destination;
            default:
                return null;
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return int.class;
    }
    
        @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "data";
            case 1:
                return "dest";
            case 2:
                return "npos";
        }
        return "";
    }

}
