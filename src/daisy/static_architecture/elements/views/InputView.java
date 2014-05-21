 package daisy.static_architecture.elements.views;

import daisy.static_architecture.elements.actions.AddInputAction;
import daisy.static_architecture.elements.actions.DeleteElementAction;
import daisy.static_architecture.elements.implementation.StaticInputUnit;
import java.awt.Point;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Wittman
 */
public class InputView extends ElementView {

    private StaticInputUnit inputUnit;

    /**
     * Creates new form InputView
     *
     * @param unit
     */
    public InputView(StaticInputUnit unit) {
        super(unit);
        inputUnit = unit;
        initComponents();
        initElementVertexes();
        this.setSize(this.preferredSize());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    protected void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setPreferredSize(new java.awt.Dimension(250, 150));

        jLabel1.setText("InputUnit");

        jTable1.setModel(inputUnit);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void initElementVertexes() {
        addPin(new Point(5, 60));
        addPin(new Point(240, 60));
    }

    @Override
    public JPopupMenu getPopupMenu() {
        if(popupMenu == null){
            popupMenu = super.getPopupMenu();
            
            JMenuItem menuItem = new JMenuItem("Add token");
            menuItem.addActionListener(new AddInputAction(inputUnit));
            popupMenu.add(menuItem);
        }
        return popupMenu;
    }
    
    

}