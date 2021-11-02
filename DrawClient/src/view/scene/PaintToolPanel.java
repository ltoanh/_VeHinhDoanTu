package view.scene;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JColorChooser;

/**
 *
 * @author whiwf
 */
public class PaintToolPanel extends javax.swing.JPanel {

    private IngameFrm ingame;
//    private Graphics2D g2;

    public PaintToolPanel(IngameFrm ingame) {
        initComponents();

        this.ingame = ingame;
    }

    private void setColorChooser(PaintPane panel, Color color) {
        panel.setCurrentColor(color);
        lbCurrentColor.setBackground(color);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPencil = new javax.swing.JButton();
        btnErase = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnRed = new javax.swing.JButton();
        btnBlue = new javax.swing.JButton();
        btnYellow = new javax.swing.JButton();
        btnBlack = new javax.swing.JButton();
        btnGreen = new javax.swing.JButton();
        btnCyan = new javax.swing.JButton();
        btnMagenta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbCurrentColor = new javax.swing.JPanel();
        btnChooseColor = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnPencil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8-pencil-30.png"))); // NOI18N
        btnPencil.setText("Bút chì");

        btnErase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8-erase-30.png"))); // NOI18N
        btnErase.setText("Tẩy");

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8-delete-30.png"))); // NOI18N
        btnClear.setText("Xóa");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chọn màu"));

        btnRed.setBackground(new java.awt.Color(255, 0, 0));
        btnRed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedActionPerformed(evt);
            }
        });

        btnBlue.setBackground(java.awt.Color.blue);
        btnBlue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBlueActionPerformed(evt);
            }
        });

        btnYellow.setBackground(java.awt.Color.yellow);
        btnYellow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYellowActionPerformed(evt);
            }
        });

        btnBlack.setBackground(java.awt.Color.black);
        btnBlack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBlackActionPerformed(evt);
            }
        });

        btnGreen.setBackground(java.awt.Color.green);
        btnGreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGreenActionPerformed(evt);
            }
        });

        btnCyan.setBackground(java.awt.Color.cyan);
        btnCyan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCyanActionPerformed(evt);
            }
        });

        btnMagenta.setBackground(java.awt.Color.magenta);
        btnMagenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMagentaActionPerformed(evt);
            }
        });

        jLabel1.setText("Màu được chọn:");

        lbCurrentColor.setBackground(new java.awt.Color(255, 102, 51));

        javax.swing.GroupLayout lbCurrentColorLayout = new javax.swing.GroupLayout(lbCurrentColor);
        lbCurrentColor.setLayout(lbCurrentColorLayout);
        lbCurrentColorLayout.setHorizontalGroup(
            lbCurrentColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        lbCurrentColorLayout.setVerticalGroup(
            lbCurrentColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        btnChooseColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8-paint-palette-30.png"))); // NOI18N
        btnChooseColor.setText("Bảng màu");
        btnChooseColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseColorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnRed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBlue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnYellow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBlack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGreen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCyan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMagenta))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lbCurrentColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChooseColor)))
                .addGap(166, 166, 166))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRed, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBlue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnYellow, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBlack, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMagenta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCyan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnChooseColor)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbCurrentColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(15, 15, 15))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPencil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnErase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnClear)
                .addGap(41, 41, 41)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnPencil)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnErase)
                    .addComponent(btnClear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedActionPerformed
        ingame.getPaintPane1().setCurrentColor(Color.RED);
        ingame.getPaintPane2().setCurrentColor(Color.RED);
    }//GEN-LAST:event_btnRedActionPerformed

    private void btnBlueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlueActionPerformed
        ingame.getPaintPane1().setCurrentColor(Color.BLUE);
        ingame.getPaintPane2().setCurrentColor(Color.BLUE);
    }//GEN-LAST:event_btnBlueActionPerformed

    private void btnYellowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYellowActionPerformed
        ingame.getPaintPane1().setCurrentColor(Color.YELLOW);
        ingame.getPaintPane2().setCurrentColor(Color.YELLOW);
    }//GEN-LAST:event_btnYellowActionPerformed

    private void btnBlackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlackActionPerformed
        ingame.getPaintPane1().setCurrentColor(Color.BLACK);
        ingame.getPaintPane2().setCurrentColor(Color.BLACK);
    }//GEN-LAST:event_btnBlackActionPerformed

    private void btnGreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGreenActionPerformed
        ingame.getPaintPane1().setCurrentColor(Color.GREEN);
        ingame.getPaintPane2().setCurrentColor(Color.GREEN);
    }//GEN-LAST:event_btnGreenActionPerformed

    private void btnCyanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCyanActionPerformed
        ingame.getPaintPane1().setCurrentColor(Color.CYAN);
        ingame.getPaintPane2().setCurrentColor(Color.CYAN);
    }//GEN-LAST:event_btnCyanActionPerformed

    private void btnMagentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMagentaActionPerformed
        ingame.getPaintPane1().setCurrentColor(Color.MAGENTA);
        ingame.getPaintPane2().setCurrentColor(Color.MAGENTA);
    }//GEN-LAST:event_btnMagentaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        ingame.getPaintPane1().clearPaint();
        ingame.getPaintPane2().clearPaint();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnChooseColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseColorActionPerformed
        Color color = JColorChooser.showDialog(null, "Chọn màu", Color.BLACK);
        System.out.println(color);
//        setColorChooser(color);
    }//GEN-LAST:event_btnChooseColorActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBlack;
    private javax.swing.JButton btnBlue;
    private javax.swing.JButton btnChooseColor;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCyan;
    private javax.swing.JButton btnErase;
    private javax.swing.JButton btnGreen;
    private javax.swing.JButton btnMagenta;
    private javax.swing.JButton btnPencil;
    private javax.swing.JButton btnRed;
    private javax.swing.JButton btnYellow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel lbCurrentColor;
    // End of variables declaration//GEN-END:variables
}
