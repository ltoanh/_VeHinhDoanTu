package view.scene;

import constant.Constant;
import java.awt.Color;
import javax.swing.JColorChooser;

/**
 *
 * @author whiwf
 */
public class DrawSettingPane extends javax.swing.JPanel {

    private Ingame ingame;
    
    
    public DrawSettingPane(Ingame ingame) {
        initComponents();
        
        this.ingame = ingame;
    }

    private void setColorChooser(Color color){
        ingame.getPaintPane().setCurrentColor(color);
        btnCurrentColor.setBackground(color);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnErase = new javax.swing.JButton();
        btnPencil = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnRed = new javax.swing.JButton();
        btnBlue = new javax.swing.JButton();
        btnYellow = new javax.swing.JButton();
        btnBlack = new javax.swing.JButton();
        btnGreen = new javax.swing.JButton();
        btnMagenta = new javax.swing.JButton();
        btnCyan = new javax.swing.JButton();
        btnColorChooser = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnCurrentColor = new javax.swing.JButton();

        btnErase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnErase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8-erase-30.png"))); // NOI18N
        btnErase.setText("Tẩy");
        btnErase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEraseActionPerformed(evt);
            }
        });

        btnPencil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8-pencil-30.png"))); // NOI18N
        btnPencil.setText("Bút chì");
        btnPencil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPencilActionPerformed(evt);
            }
        });

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8-delete-30.png"))); // NOI18N
        btnClear.setText("Xóa");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Chọn màu:");

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

        btnMagenta.setBackground(java.awt.Color.magenta);
        btnMagenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMagentaActionPerformed(evt);
            }
        });

        btnCyan.setBackground(java.awt.Color.cyan);
        btnCyan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCyanActionPerformed(evt);
            }
        });

        btnColorChooser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8-paint-palette-30.png"))); // NOI18N
        btnColorChooser.setText("Chọn màu");
        btnColorChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorChooserActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Màu đã chọn:");

        btnCurrentColor.setBackground(java.awt.Color.black);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(btnCurrentColor, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btnColorChooser))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnRed)
                            .addGap(18, 18, 18)
                            .addComponent(btnBlue)
                            .addGap(18, 18, 18)
                            .addComponent(btnYellow)
                            .addGap(18, 18, 18)
                            .addComponent(btnBlack)
                            .addGap(18, 18, 18)
                            .addComponent(btnGreen)
                            .addGap(18, 18, 18)
                            .addComponent(btnCyan)
                            .addGap(18, 18, 18)
                            .addComponent(btnMagenta))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRed, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBlue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnYellow, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBlack, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGreen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMagenta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCyan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCurrentColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnColorChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnPencil)
                .addGap(50, 50, 50)
                .addComponent(btnErase)
                .addGap(36, 36, 36)
                .addComponent(btnClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnErase, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPencil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPencilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPencilActionPerformed
        ingame.getPaintPane().setTool(Constant.PENCIL_TOOL);
    }//GEN-LAST:event_btnPencilActionPerformed

    private void btnEraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEraseActionPerformed
        ingame.getPaintPane().setTool(Constant.ERASE_TOOL);
    }//GEN-LAST:event_btnEraseActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        ingame.getPaintPane().clearPaint();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnColorChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorChooserActionPerformed
        Color color = JColorChooser.showDialog(null, "Chọn màu", Color.BLACK);
        System.out.println(color);
        setColorChooser(color);
    }//GEN-LAST:event_btnColorChooserActionPerformed

    private void btnRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedActionPerformed
        setColorChooser(btnRed.getBackground());
    }//GEN-LAST:event_btnRedActionPerformed

    private void btnBlueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlueActionPerformed
        setColorChooser(btnBlue.getBackground());
    }//GEN-LAST:event_btnBlueActionPerformed

    private void btnYellowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYellowActionPerformed
        setColorChooser(btnYellow.getBackground());
    }//GEN-LAST:event_btnYellowActionPerformed

    private void btnBlackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlackActionPerformed
        setColorChooser(btnBlack.getBackground());
    }//GEN-LAST:event_btnBlackActionPerformed

    private void btnGreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGreenActionPerformed
        setColorChooser(btnGreen.getBackground());
    }//GEN-LAST:event_btnGreenActionPerformed

    private void btnCyanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCyanActionPerformed
        setColorChooser(btnCyan.getBackground());
    }//GEN-LAST:event_btnCyanActionPerformed

    private void btnMagentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMagentaActionPerformed
        setColorChooser(btnMagenta.getBackground());
    }//GEN-LAST:event_btnMagentaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBlack;
    private javax.swing.JButton btnBlue;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnColorChooser;
    private javax.swing.JButton btnCurrentColor;
    private javax.swing.JButton btnCyan;
    private javax.swing.JButton btnErase;
    private javax.swing.JButton btnGreen;
    private javax.swing.JButton btnMagenta;
    private javax.swing.JButton btnPencil;
    private javax.swing.JButton btnRed;
    private javax.swing.JButton btnYellow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
