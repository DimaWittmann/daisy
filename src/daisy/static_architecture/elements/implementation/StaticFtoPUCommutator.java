package daisy.static_architecture.elements.implementation;

import daisy.static_architecture.DataToken;
import daisy.static_architecture.elements.Element;
import daisy.static_architecture.elements.ITokenOutput;
import daisy.static_architecture.elements.views.CommutatorView;
import javax.swing.JPanel;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Wittman
 */
public class StaticFtoPUCommutator extends Element implements ITokenOutput{

    private JPanel view;
    @Override
    public void clock() {
    }

    @Override
    public JPanel getView() {
        if(view == null){
            view = new CommutatorView(this);
        }
        return view;
    }

    @Override
    public void attachElement(Element element) {
    }

    @Override
    public void detachElement(Element element) {
    }

    @Override
    public void detachAllElements() {
    }
    
    @Override
    public DataToken getReadyData() {
        //TODO implement
        return null;
    }

    @Override
    @Deprecated
    public int getRowCount() {
        throw new NotImplementedException();
    }

    @Override
    @Deprecated
    public int getColumnCount() {
        throw new NotImplementedException();
    }

    @Override
    @Deprecated
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new NotImplementedException();
    }



}
