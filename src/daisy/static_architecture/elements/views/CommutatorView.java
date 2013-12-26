/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daisy.static_architecture.elements.views;

import daisy.static_architecture.elements.views.listeners.DragElementListener;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Path2D;

/**
 *
 * @author Wittman
 */
public class CommutatorView extends javax.swing.JPanel {

    /**
     * Creates new form CommutatorView
     */
    public CommutatorView() {
        DragElementListener mouseListener = new DragElementListener();
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        
        initComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xPoints[] = {5, 70, 70, 5, 5};
        int yPoints[] = {25, 5, 195, 175, 25};
        Path2D path = new Path2D.Double(new Polygon(xPoints, yPoints, 4));
        path = (Path2D) new BasicStroke(2).createStrokedShape(path);
        
        ((Graphics2D) g).fill(path);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}