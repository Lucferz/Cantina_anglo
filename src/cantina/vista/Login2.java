/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cantina.vista;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author danie
 */
public class Login2 extends javax.swing.JFrame {

    /**
     * Creates new form Login2
     */
    public Login2() {
        initComponents();
        setLocationRelativeTo(null);
        getContentPane().requestFocusInWindow();
    }
    
    public void changebgColor(JPanel hover, Color hcolor){
    hover.setBackground(hcolor);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftCont = new javax.swing.JPanel();
        rellenoTop = new javax.swing.JPanel();
        contenedorLogo = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        panelSuperSu = new javax.swing.JPanel();
        labelEmpresa = new javax.swing.JLabel();
        rellenoBottom = new javax.swing.JPanel();
        rightCont = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        loginUsername = new javax.swing.JTextField();
        loginPassw = new javax.swing.JPasswordField();
        usrSeparator = new javax.swing.JSeparator();
        passSeparator = new javax.swing.JSeparator();
        userIcon = new javax.swing.JLabel();
        passIcon = new javax.swing.JLabel();
        btnLogin = new javax.swing.JPanel();
        labelLogBtn = new javax.swing.JLabel();
        btnNewUser = new javax.swing.JPanel();
        labelNewUserBtn = new javax.swing.JLabel();
        btnForgetPass = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicia Sesión en el Sistema");
        setMinimumSize(new java.awt.Dimension(700, 500));
        setPreferredSize(new java.awt.Dimension(800, 478));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        leftCont.setBackground(new java.awt.Color(19, 170, 95));
        leftCont.setPreferredSize(new java.awt.Dimension(350, 400));
        leftCont.setLayout(new java.awt.GridLayout(4, 1));

        rellenoTop.setBackground(new java.awt.Color(19, 170, 95));

        javax.swing.GroupLayout rellenoTopLayout = new javax.swing.GroupLayout(rellenoTop);
        rellenoTop.setLayout(rellenoTopLayout);
        rellenoTopLayout.setHorizontalGroup(
            rellenoTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        rellenoTopLayout.setVerticalGroup(
            rellenoTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        leftCont.add(rellenoTop);

        contenedorLogo.setBackground(new java.awt.Color(19, 170, 95));
        contenedorLogo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_online_shopping_100px_1.png"))); // NOI18N
        logo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        logo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                logoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                logoKeyTyped(evt);
            }
        });
        contenedorLogo.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 200, 119));

        panelSuperSu.setBackground(new java.awt.Color(19, 170, 95));
        panelSuperSu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelSuperSuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelSuperSuLayout = new javax.swing.GroupLayout(panelSuperSu);
        panelSuperSu.setLayout(panelSuperSuLayout);
        panelSuperSuLayout.setHorizontalGroup(
            panelSuperSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        panelSuperSuLayout.setVerticalGroup(
            panelSuperSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        contenedorLogo.add(panelSuperSu, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 20, 10));

        leftCont.add(contenedorLogo);

        labelEmpresa.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        labelEmpresa.setForeground(new java.awt.Color(255, 255, 255));
        labelEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEmpresa.setText("CANTINA ANGLO");
        leftCont.add(labelEmpresa);

        rellenoBottom.setBackground(new java.awt.Color(19, 170, 95));

        javax.swing.GroupLayout rellenoBottomLayout = new javax.swing.GroupLayout(rellenoBottom);
        rellenoBottom.setLayout(rellenoBottomLayout);
        rellenoBottomLayout.setHorizontalGroup(
            rellenoBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        rellenoBottomLayout.setVerticalGroup(
            rellenoBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        leftCont.add(rellenoBottom);

        getContentPane().add(leftCont);

        rightCont.setBackground(new java.awt.Color(103, 226, 109));
        rightCont.setAlignmentX(1.0F);
        rightCont.setPreferredSize(new java.awt.Dimension(350, 400));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_user_locked_50px.png"))); // NOI18N

        loginUsername.setBackground(new java.awt.Color(103, 226, 109));
        loginUsername.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        loginUsername.setForeground(new java.awt.Color(233, 233, 233));
        loginUsername.setText("usuario");
        loginUsername.setAlignmentX(1.0F);
        loginUsername.setBorder(null);
        loginUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                loginUsernameFocusLost(evt);
            }
        });
        loginUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginUsernameActionPerformed(evt);
            }
        });

        loginPassw.setBackground(new java.awt.Color(103, 226, 109));
        loginPassw.setForeground(new java.awt.Color(233, 233, 233));
        loginPassw.setText("poiuytrewq");
        loginPassw.setAlignmentX(1.0F);
        loginPassw.setBorder(null);
        loginPassw.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginPasswFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                loginPasswFocusLost(evt);
            }
        });
        loginPassw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginPasswActionPerformed(evt);
            }
        });

        usrSeparator.setForeground(new java.awt.Color(204, 204, 204));
        usrSeparator.setAlignmentX(1.0F);

        passSeparator.setForeground(new java.awt.Color(204, 204, 204));
        passSeparator.setAlignmentX(1.0F);

        userIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_user_40px.png"))); // NOI18N
        userIcon.setAlignmentX(1.0F);

        passIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cantina/vista/imgs/icons8_lock_40px.png"))); // NOI18N
        passIcon.setAlignmentX(1.0F);

        btnLogin.setBackground(new java.awt.Color(103, 226, 109));
        btnLogin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        btnLogin.setAlignmentX(1.0F);
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginMouseExited(evt);
            }
        });
        btnLogin.setLayout(new java.awt.GridLayout(1, 0));

        labelLogBtn.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        labelLogBtn.setForeground(new java.awt.Color(255, 255, 255));
        labelLogBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLogBtn.setText("Ingresar");
        labelLogBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelLogBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelLogBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelLogBtnMouseExited(evt);
            }
        });
        btnLogin.add(labelLogBtn);

        btnNewUser.setBackground(new java.awt.Color(103, 226, 109));
        btnNewUser.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        btnNewUser.setAlignmentX(1.0F);
        btnNewUser.setLayout(new java.awt.GridLayout(1, 0));

        labelNewUserBtn.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        labelNewUserBtn.setForeground(new java.awt.Color(255, 255, 255));
        labelNewUserBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNewUserBtn.setText("Nuevo Usuario");
        labelNewUserBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelNewUserBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelNewUserBtnMouseExited(evt);
            }
        });
        btnNewUser.add(labelNewUserBtn);

        btnForgetPass.setFont(new java.awt.Font("Roboto Medium", 1, 12)); // NOI18N
        btnForgetPass.setForeground(new java.awt.Color(255, 255, 255));
        btnForgetPass.setText("Contraseña olvidada?");
        btnForgetPass.setAlignmentX(1.0F);
        btnForgetPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout rightContLayout = new javax.swing.GroupLayout(rightCont);
        rightCont.setLayout(rightContLayout);
        rightContLayout.setHorizontalGroup(
            rightContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightContLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(165, 165, 165))
            .addGroup(rightContLayout.createSequentialGroup()
                .addGroup(rightContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnForgetPass)
                    .addGroup(rightContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(rightContLayout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(userIcon)
                            .addGap(10, 10, 10)
                            .addComponent(loginUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(rightContLayout.createSequentialGroup()
                            .addGap(100, 100, 100)
                            .addComponent(usrSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(rightContLayout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addGroup(rightContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(rightContLayout.createSequentialGroup()
                                    .addComponent(passIcon)
                                    .addGap(10, 10, 10)
                                    .addGroup(rightContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(loginPassw, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(rightContLayout.createSequentialGroup()
                                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnNewUser, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(60, 60, 60))
        );
        rightContLayout.setVerticalGroup(
            rightContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightContLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel4)
                .addGap(58, 58, 58)
                .addGroup(rightContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userIcon)
                    .addComponent(loginUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(usrSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(rightContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rightContLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(passIcon))
                    .addGroup(rightContLayout.createSequentialGroup()
                        .addComponent(loginPassw, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(passSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(rightContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rightContLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnNewUser, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightContLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(btnForgetPass)
                .addGap(45, 45, 45))
        );

        getContentPane().add(rightCont);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelLogBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLogBtnMouseEntered
        changebgColor(btnLogin, new Color(90,210,95));
    }//GEN-LAST:event_labelLogBtnMouseEntered

    private void labelLogBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLogBtnMouseExited
       changebgColor(btnLogin, new Color(103,226,109));
    }//GEN-LAST:event_labelLogBtnMouseExited

    private void labelLogBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelLogBtnMouseClicked
            // TODO add your handling code here:
            new MainPage().setVisible(true);
      
    }//GEN-LAST:event_labelLogBtnMouseClicked

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseEntered
        // TODO add your handling code here:
        //changebgColor(btnLogin, new Color(240,126,128));
    }//GEN-LAST:event_btnLoginMouseEntered

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseExited
        // TODO add your handling code here:
      //  changebgColor(btnLogin, new Color(186,79,84));
    }//GEN-LAST:event_btnLoginMouseExited

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoginMouseClicked

    private void loginUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginUsernameActionPerformed

    private void loginUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginUsernameFocusGained
        if (loginUsername.getText().equals("usuario")){
            loginUsername.setText("");
            loginUsername.setForeground(new Color(255, 255, 255));
        }
        
    }//GEN-LAST:event_loginUsernameFocusGained

    private void loginPasswFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginPasswFocusGained
//   if (loginPassw.getPassword().equals("poiuytrewq")){
//            loginUsername.setText("");
//            loginUsername.setForeground(new Color(255, 255, 255));
//        }
    String pass = String.valueOf(loginPassw.getPassword());
    
    if(pass.toLowerCase().equals("poiuytrewq") ){
        loginPassw.setText("");
        loginPassw.setForeground(new Color(255, 255, 255));
        
    }
    
    
    }//GEN-LAST:event_loginPasswFocusGained

    private void loginPasswActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginPasswActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginPasswActionPerformed

    private void labelNewUserBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNewUserBtnMouseExited
        changebgColor(btnNewUser, new Color(103,226,109));
    }//GEN-LAST:event_labelNewUserBtnMouseExited

    private void labelNewUserBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelNewUserBtnMouseEntered
       changebgColor(btnNewUser, new Color(90,210,95));
    }//GEN-LAST:event_labelNewUserBtnMouseEntered

    private void loginPasswFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginPasswFocusLost
        
        String pass = String.valueOf(loginPassw.getPassword());
    
    if(pass.toLowerCase().equals("") ){
        loginPassw.setText("poiuytrewq");
        loginPassw.setForeground(new Color(233, 233, 233));
        
    }
    }//GEN-LAST:event_loginPasswFocusLost

    private void loginUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginUsernameFocusLost
        // TODO add your handling code here:
         if (loginUsername.getText().equals("")){
            loginUsername.setText("usuario");
            loginUsername.setForeground(new Color(233, 233, 233));
        }
    }//GEN-LAST:event_loginUsernameFocusLost

    private void logoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_logoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_logoKeyTyped

    private void logoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_logoKeyPressed
           // TODO add your handling code here:
    }//GEN-LAST:event_logoKeyPressed

    private void panelSuperSuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSuperSuMouseClicked
        new SuperSu().setVisible(true);
    }//GEN-LAST:event_panelSuperSuMouseClicked

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
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnForgetPass;
    private javax.swing.JPanel btnLogin;
    private javax.swing.JPanel btnNewUser;
    private javax.swing.JPanel contenedorLogo;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel labelEmpresa;
    private javax.swing.JLabel labelLogBtn;
    private javax.swing.JLabel labelNewUserBtn;
    private javax.swing.JPanel leftCont;
    private javax.swing.JPasswordField loginPassw;
    private javax.swing.JTextField loginUsername;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel panelSuperSu;
    private javax.swing.JLabel passIcon;
    private javax.swing.JSeparator passSeparator;
    private javax.swing.JPanel rellenoBottom;
    private javax.swing.JPanel rellenoTop;
    private javax.swing.JPanel rightCont;
    private javax.swing.JLabel userIcon;
    private javax.swing.JSeparator usrSeparator;
    // End of variables declaration//GEN-END:variables
}
