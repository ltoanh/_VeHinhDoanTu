package view.scene;

import client.Client;
import constant.Constant;
import constant.StreamData;
import controller.ClientCtr;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.border.LineBorder;
import model.DrawPoint;

/**
 *
 * @author whiwf
 */
public class PaintPane extends javax.swing.JPanel {

    private int lastX, lastY;

    private int activeTool = 1;
    private Color currentColor = Color.BLACK;

    private IngameFrm ingame;

//    private boolean isPainer = true;
    public PaintPane(IngameFrm ingame) {
        initComponents();

        this.ingame = ingame;
    }

    public void setTool(int tool) {
        this.activeTool = tool;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
            .addGap(0, 398, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int x = evt.getX(), y = evt.getY();
        
        if (this.ingame.getPaintPane1().equals(this) && Client.room.getLsPainterUsername().get(0).equals(Client.account.getUsername()) ) {
            // player 1 draw
            draw(lastX, lastY, x, y, currentColor);
            DrawPoint point = new DrawPoint(activeTool, lastX, lastY, x, y, currentColor);
            ClientCtr.senderClient.sendDrawPoint(StreamData.Type.PAINT1.name(), point);
        } else if (this.ingame.getPaintPane2().equals(this) && Client.room.getLsPainterUsername().get(1).equals(Client.account.getUsername())){
            // player 2 draw
            draw(lastX, lastY, x, y, currentColor);
            DrawPoint point = new DrawPoint(activeTool, lastX, lastY, x, y, currentColor);
            ClientCtr.senderClient.sendDrawPoint(StreamData.Type.PAINT2.name(), point);
        }
        
        lastX = x;
        lastY = y;
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
    public void addPointDraw(DrawPoint drawPoint) {
        this.activeTool = drawPoint.getTool();
        draw(drawPoint.getX1(), drawPoint.getY1(), drawPoint.getX2(), drawPoint.getY2(), drawPoint.getColor());
    }

    //send point => server

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
