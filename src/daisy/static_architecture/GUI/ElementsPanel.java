/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daisy.static_architecture.GUI;

import daisy.static_architecture.elements.ElementsModel;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Wittman
 */
@Deprecated
public class ElementsPanel extends JPanel {

    private ElementsModel model;

    /**
     * Creates new form ElementsPanel
     * @param model
     */
    public ElementsPanel(ElementsModel model) {
        this.model = model;
        
        initComponents();
    }

                        
    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        for (int i = 0; i < model.elementsName.size(); i++) {
            JButton button = new JButton(model.elementsName.get(i));
            button.setName(model.elementsName.get(i));
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        daisy.Daisy.getDesign().addElement(model.getNewElementInstance(((JButton)e.getSource()).getName()));
                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(ElementsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            this.add(button);
        }
        
    }
    
    

}
