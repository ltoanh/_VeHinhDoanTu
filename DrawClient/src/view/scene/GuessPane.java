package view.scene;

import client.Client;
import controller.ClientCtr;
import java.awt.event.KeyEvent;

/**
 *
 * @author whiwf
 */
public class GuessPane extends javax.swing.JPanel {

    public GuessPane() {
        initComponents();
        resetGuessPane();
    }

    public void descGuessTurn(){
        int guessTurn = Integer.parseInt(lbGuessTurn.getText());
        guessTurn--;
        
        lbGuessTurn.setText(guessTurn + "");
    }
    
    public int getGuessTurn(){
        return Integer.parseInt(lbGuessTurn.getText());
    }
    
    public void closeGuessPane(){
        txtGuess.setEditable(false);
    }
    
    public void resetGuessPane(){
        txtGuess.setEditable(true);
        lbGuessTurn.setText("3");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbGuessTurn = new javax.swing.JLabel();
        txtGuess = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Số lần đoán còn lại:");

        lbGuessTurn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbGuessTurn.setText("3");

        txtGuess.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGuessKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGuess)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbGuessTurn)
                        .addGap(0, 95, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbGuessTurn))
                .addGap(18, 18, 18)
                .addComponent(txtGuess, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtGuessKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGuessKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String guessWord = txtGuess.getText().trim();
            txtGuess.setText("");
            //send to server
            ClientCtr.senderClient.sendGuessWord(guessWord);
        }
    }//GEN-LAST:event_txtGuessKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbGuessTurn;
    private javax.swing.JTextField txtGuess;
    // End of variables declaration//GEN-END:variables
}
