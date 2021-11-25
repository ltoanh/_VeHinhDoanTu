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

    public PaintToolPanel(IngameFrm ingame) {
        initComponents();

        this.ingame = ingame;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPencil = new javax.swing.JButton();
        btnErase = new javax.swing.JButton();
        btnSharing = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbCurrentColor = new javax.swing.JPanel();
        btnChooseColor = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnPencil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8-pencil-30.png"))); // NOI18N
        btnPencil.setText("Bút chì");

        btnErase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8-erase-30.png"))); // NOI18N
        btnErase.setText("Tẩy");
        btnErase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEraseActionPerformed(evt);
            }
        });

        btnSharing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8-share-32.png"))); // NOI18N
        btnSharing.setText("Chia sẻ màn hình");

        jLabel1.setText("Màu được chọn:");

        lbCurrentColor.setBackground(new java.awt.Color(0, 0, 0));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lbCurrentColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnChooseColor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnErase, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPencil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSharing, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSharing)
                .addGap(18, 18, 18)
                .addComponent(btnPencil)
                .addGap(18, 18, 18)
                .addComponent(btnErase)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbCurrentColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(btnChooseColor)
                .addContainerGap(92, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseColorActionPerformed
        Color color = JColorChooser.showDialog(null, "Chọn màu", Color.BLACK);
        System.out.println(color);
        
        ingame.getPaintPane1().setCurrentColor(color);
        ingame.getPaintPane2().setCurrentColor(color);
        
        lbCurrentColor.setBackground(color);
    }//GEN-LAST:event_btnChooseColorActionPerformed

    private void btnEraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEraseActionPerformed
        ingame.getPaintPane1().setCurrentColor(Color.WHITE);
        ingame.getPaintPane2().setCurrentColor(Color.WHITE);
    }//GEN-LAST:event_btnEraseActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChooseColor;
    private javax.swing.JButton btnErase;
    private javax.swing.JButton btnPencil;
    private javax.swing.JButton btnSharing;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel lbCurrentColor;
    // End of variables declaration//GEN-END:variables
}
