package view.scene;

import client.Client;
import constant.Constant;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author whiwf
 */
public class PaintPane extends javax.swing.JPanel {

    private int lastX, lastY;
    private int inkWidth = 625, inkHeight = 390;

    private int activeTool = 1;
    private Color currentColor = Color.BLACK;

//    private boolean isPainer = true;

    public PaintPane() {
        initComponents();

        setSize(inkWidth, inkHeight);
    }

    public void setTool(int tool) {
        this.activeTool = tool;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public void clearPaint() {
        this.removeAll();
        this.updateUI();
        this.activeTool = Constant.PENCIL_TOOL;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
//        if (isPainer) {
            int x = evt.getX(), y = evt.getY();

            draw(lastX, lastY, x, y, currentColor);
            Client.clientCtr.senderClient.drawPoint(activeTool, lastX, lastY, x, y, currentColor);
            
            lastX = x;
            lastY = y;
//        }
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        lastX = evt.getX();
        lastY = evt.getY();
    }//GEN-LAST:event_formMousePressed

    // draw 
    private void draw(int x1, int y1, int x2, int y2, Color color) {
        Graphics g = getGraphics();
        if (activeTool == Constant.PENCIL_TOOL) {
            g.setColor(color);
            g.drawLine(x1, y1, x2, y2);
        } else if (activeTool == Constant.ERASE_TOOL) {
            g.setColor(Color.white);
            g.fillOval(x1, y1, 15, 15);
        }
    }

    // diem nhan dc tu server => draw
    public void addPointDraw(int tool, int x1, int y1, int x2, int y2, Color color) {
//        if(!isPainer){
            draw(x1, y1, x2, y2, color);
//        }
    }
    
    //send point => server
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
