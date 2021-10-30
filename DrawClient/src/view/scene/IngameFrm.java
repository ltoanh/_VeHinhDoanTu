package view.scene;

import client.Client;
import constant.Avatar;
import constant.Constant;
import controller.ClientCtr;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import model.Player;

/**
 *
 * @author whiwf
 */
public class IngameFrm extends javax.swing.JFrame {

    private PaintToolPanel paintToolPanel;
    private GuessPane guessPane;
    
    private PaintPane paintPane1;
    private PaintPane paintPane2;
    
    
    public IngameFrm() {
        initComponents();
        this.setLocationRelativeTo(null);
        setTitle("Scribble it! " + client.Client.account.getName());
        
        setLayout(null);
        
        //paint
        paintPane1 = new PaintPane(this);
        add(paintPane1);
        paintPane1.setBounds(190, 70, 290, 370);
        
        paintPane2 = new PaintPane(this);
        add(paintPane2);
        paintPane2.setBounds(500, 70, 290, 370);
        
        if (Client.room.getLsPainterUsername().get(0).equals(Client.account.getUsername()) ) {
            paintPane1.setBorder(new LineBorder(Color.red));
        } else if (Client.room.getLsPainterUsername().get(1).equals(Client.account.getUsername())){
            paintPane2.setBorder(new LineBorder(Color.red));
        }
        
        //paint tool
        paintToolPanel = new PaintToolPanel(this);
        this.add(paintToolPanel);
        paintToolPanel.setBounds(190, 450, 600, 150);
        
        paintToolPanel.setVisible(false);
        
        //guess pane
        guessPane = new GuessPane();
        this.add(guessPane);
        guessPane.setBounds(190, 450, 600, 150);
        
        guessPane.setVisible(false);
    }
    //================start game============================
    //show paint tool
    public void displayPaintTool(){
        paintToolPanel.setVisible(true);
        guessPane.setVisible(false);
    }
    // show guess pane
    public void displayGuessPane(){
        paintToolPanel.setVisible(false);
        guessPane.setVisible(true);
    }
    // show player list
    public void displayLsPlayer(ArrayList<Player> lsPlayer){
        taLsPlayer.setText("");
        
        for(Player player : lsPlayer){
            String msgPlayer = player.getAccount().getName()+ "(" + player.getAccount().getUsername() + ") : " + player.getScore() + " điểm\n";
            taLsPlayer.append(msgPlayer);
        }
    }
    // show current player inf
    public void displayCurrentPlayerInf(){
        jLabel_avatar.setText(client.Client.account.getUsername());
        
        // display room inf 
        //... code ...
    }
    // show countdown time
    public void displayCountdownTime(String msgTurn, String msgTime){
        lbTurn.setText(msgTurn + "/3");
        lbCountdown.setText(msgTime + " s");
    }
    //show word for player
    public void displayWord(String word){
       if (Client.room.getLsPainterUsername().get(0).equals(Client.account.getUsername()) || 
               Client.room.getLsPainterUsername().get(1).equals(Client.account.getUsername()) ) {
            jLabelWord.setText(word);
        }else{
           String numOfWord="";
           for(int i = 0; i < word.length(); i++)
           {
               numOfWord += "_ ";
           }
           jLabelWord.setText(numOfWord);
       }
      
       
    }
    // Show message in jTextArea
    public void displayMesg(String mes) {
        jTextArea2.append(mes+"\n");
    }
    
    public void displayInformation(String avatar, String name){
        jLabel_Ten_nguoi_choi.setText(name);
        ImageIcon img = new ImageIcon(Avatar.PATH.concat(avatar));
        jLabel_avatar.setIcon(img);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel_avatar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taLsPlayer = new javax.swing.JTextArea();
        jLabel_Ten_nguoi_choi = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnSendMsg = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbTurn = new javax.swing.JLabel();
        lbCountdown = new javax.swing.JLabel();
        jLabelWord = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel_avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/avatar/icons8_circled_user_female_skin_type_7_96px.png"))); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("100 điểm");

        taLsPlayer.setEditable(false);
        taLsPlayer.setColumns(20);
        taLsPlayer.setLineWrap(true);
        taLsPlayer.setRows(5);
        jScrollPane1.setViewportView(taLsPlayer);

        jLabel_Ten_nguoi_choi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Ten_nguoi_choi.setText("Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel_avatar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Ten_nguoi_choi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel_avatar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel_Ten_nguoi_choi)
                        .addGap(49, 49, 49)))
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Chat"));

        jLabel3.setText("Nhập tin nhắn");

        btnSendMsg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8_paper_plane_24px.png"))); // NOI18N
        btnSendMsg.setText("Gửi");
        btnSendMsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMsgActionPerformed(evt);
            }
        });

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jTextField1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(btnSendMsg)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSendMsg)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Vòng:");

        lbTurn.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbTurn.setText("2/3");

        lbCountdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/assets/icons8-timer-30.png"))); // NOI18N
        lbCountdown.setText("120 s");

        jLabelWord.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelWord.setText("Từ để đoán (7)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lbTurn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelWord, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCountdown, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTurn)
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(jLabelWord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbCountdown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 636, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Send message
    private void btnSendMsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMsgActionPerformed
        String msg = "";
        msg = jTextField1.getText();
        if(jTextField1.getText().trim().length() > 0){
            ClientCtr.senderClient.sendChatMessage(msg);
            jTextField1.setText("");
        }
    }//GEN-LAST:event_btnSendMsgActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IngameFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngameFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngameFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngameFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IngameFrm().setVisible(true);
            }
        });
    }

    public PaintPane getPaintPane1() {
        return paintPane1;
    }

    public PaintPane getPaintPane2() {
        return paintPane2;
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSendMsg;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelWord;
    private javax.swing.JLabel jLabel_Ten_nguoi_choi;
    private javax.swing.JLabel jLabel_avatar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbCountdown;
    private javax.swing.JLabel lbTurn;
    private javax.swing.JTextArea taLsPlayer;
    // End of variables declaration//GEN-END:variables
}
