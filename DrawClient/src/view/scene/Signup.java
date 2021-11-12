package view.scene;

import client.Client;
import constant.Avatar;
import controller.ClientCtr;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Signup extends javax.swing.JFrame {
    public Signup() {
        initComponents();
        showAvatar();
        this.setLocationRelativeTo(null);
        setTitle("Đăng ký");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbUsername = new javax.swing.JLabel();
        lbPassword = new javax.swing.JLabel();
        lbConfirm = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        btnRememberP = new javax.swing.JButton();
        btnSignup = new javax.swing.JButton();
        txtPass = new javax.swing.JPasswordField();
        txtPass2 = new javax.swing.JPasswordField();
        lbConfirm1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Đăng ký");

        lbName.setText("Họ tên:");

        lbUsername.setText("Username:");

        lbPassword.setText("Mật khẩu:");

        lbConfirm.setText("Xác nhận mật khẩu:");

        btnRememberP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRememberP.setText("Đăng nhập");
        btnRememberP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRememberPActionPerformed(evt);
            }
        });

        btnSignup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSignup.setText("Đăng ký");
        btnSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignupActionPerformed(evt);
            }
        });

        lbConfirm1.setText("Chọn ảnh đại diện:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUsername)
                    .addComponent(lbName)
                    .addComponent(lbPassword)
                    .addComponent(lbConfirm)
                    .addComponent(btnRememberP)
                    .addComponent(lbConfirm1))
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnSignup)
                            .addComponent(txtPass, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPass2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, 327, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUsername)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPassword)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbConfirm)
                    .addComponent(txtPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbConfirm1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSignup)
                    .addComponent(btnRememberP))
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRememberPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRememberPActionPerformed
        // TODO add your handling code here:
        
        Client.closeScene(Client.SceneName.SIGNUP);
        Client.openScene(Client.SceneName.LOGIN);
    }//GEN-LAST:event_btnRememberPActionPerformed

    private void btnSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignupActionPerformed
        // TODO add your handling code here:
        StringBuilder mes = new StringBuilder();
        String name = txtName.getText();
        String userName = txtUsername.getText();
        String password = new String(txtPass.getPassword());
        String repassword = new String(txtPass2.getPassword());
        int idex = jComboBox1.getSelectedIndex();
        String tmp = jComboBox1.getItemAt(idex).toString();
        String avatar = Avatar.getAvatarFilNameFromPath(tmp);
        System.out.println(avatar);
        if(name.equals("")){
            mes.append("Vui lòng điền tên\n");
        }
        if(userName.equals("")){
            mes.append("Vui lòng điền username\n");
        }
        if(password.equals("")){
            mes.append("Vui lòng điền mật khẩu\n");
        }
        if(repassword.equals("")){
            mes.append("Vui lòng xác nhận mật khẩu\n");
        }
        if(name.length() > 0 && name.matches("[^a-zA-Z]+")){
            mes.append("Tên chỉ gồm các chữ cái\n");
        }
        if(!password.equals(repassword)){
            mes.append("Mật khẩu nhập lại không khớp");
        }
        if(mes.length()>0){
            JOptionPane.showMessageDialog(this, mes.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
        }else{
            ClientCtr.senderClient.sendSignUpAccount(name, userName, password, avatar);
            Client.closeScene(Client.SceneName.SIGNUP);
            Client.openScene(Client.SceneName.LOGIN);
            JOptionPane.showMessageDialog(this, "Bạn đã đăng kí thành công, vui lòng đăng nhập để bắt đầu game!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSignupActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Signup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRememberP;
    private javax.swing.JButton btnSignup;
    private javax.swing.JComboBox<ImageIcon> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbConfirm;
    private javax.swing.JLabel lbConfirm1;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JPasswordField txtPass2;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
private void showAvatar(){
        String[] avatar = Avatar.LIST;
        for (String string : avatar) {
            ImageIcon img;
            img = new ImageIcon(Avatar.PATH.concat(string));
            jComboBox1.addItem(img);
        }
    }
}
