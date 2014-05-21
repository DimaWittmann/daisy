/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package daisy.static_architecture.elements.views;


import daisy.static_architecture.elements.Element;
import daisy.static_architecture.elements.implementation.StaticPUtoMCommutator;
import daisy.static_architecture.elements.implementation.StaticFtoPUCommutator;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Path2D;

/**
 *
 * @author Wittman
 */
public class CommutatorView extends ElementView implements ActionElement{
    /**
     * Creates new form CommutatorView
     * @param commutator
     */
    public CommutatorView(Element commutator) {
        super(commutator);
        initElementVertexes();
        initComponents();
        this.setSize(this.preferredSize());
    }
    
    
    @Override
    protected void initElementVertexes(){   
        
        addPin(new Point(5, 40));
        addPin(new Point(5, 80));
        addPin(new Point(5, 120));
        addPin(new Point(5, 160));

        if(element instanceof StaticPUtoMCommutator){
            addPin(new Point(65, 100));
        }
        
        if(element instanceof StaticFtoPUCommutator){
            addPin(new Point(65, 40));
            addPin(new Point(65, 80));
            addPin(new Point(65, 120));
            addPin(new Point(65, 160));
        }

        
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        if(element instanceof StaticPUtoMCommutator){
            int xPoints[] = {5, 70, 70, 5, 5};
            int yPoints[] = {5, 25, 175, 195, 5};
            Path2D path = new Path2D.Double(new Polygon(xPoints, yPoints, 4));
            path = (Path2D) new BasicStroke(2).createStrokedShape(path);

            ((Graphics2D) g).fill(path);
        }
        if(element instanceof StaticFtoPUCommutator){
            int xPoints[] = {5, 70, 70, 5, 5};
            int yPoints[] = {5, 5, 195, 195, 5};
            Path2D path = new Path2D.Double(new Polygon(xPoints, yPoints, 4));
            path = (Path2D) new BasicStroke(2).createStrokedShape(path);

            ((Graphics2D) g).fill(path);
        }
        
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    @Override
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    protected void initComponents() {

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
