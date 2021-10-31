package view.scene;

import java.util.ArrayList;
import javax.swing.JDialog;
import model.Player;

/**
 *
 * @author whiwf
 */
public class ResultTurnDialog extends javax.swing.JDialog {

    public ResultTurnDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        //set loai modeless de nhan duoc msg close
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModalityType(JDialog.ModalityType.MODELESS);

        setTitle("Kết quả");
    }

    public void displayTurnResult(ArrayList<Player> lsPlayers) {
        lbPlayer1.setText(lsPlayers.get(0).getAccount().getName() + " : " + lsPlayers.get(0).getScore());
        lbPlayer2.setText(lsPlayers.get(1).getAccount().getName() + " : " + lsPlayers.get(1).getScore());
        lbPlayer3.setText(lsPlayers.get(2).getAccount().getName() + " : " + lsPlayers.get(2).getScore());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbPlayer1 = new javax.swing.JLabel();
        lbPlayer2 = new javax.swing.JLabel();
        lbPlayer3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Kết quả");

        lbPlayer1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPlayer1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8-medal-first-place-48.png"))); // NOI18N
        lbPlayer1.setText("user 1");

        lbPlayer2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPlayer2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8-medal-second-place-48.png"))); // NOI18N
        lbPlayer2.setText("user 2");

        lbPlayer3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPlayer3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8-medal-third-place-48.png"))); // NOI18N
        lbPlayer3.setText("user 3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbPlayer1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addComponent(lbPlayer1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbPlayer2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lbPlayer3)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ResultTurnDialog dialog = new ResultTurnDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbPlayer1;
    private javax.swing.JLabel lbPlayer2;
    private javax.swing.JLabel lbPlayer3;
    // End of variables declaration//GEN-END:variables
}
