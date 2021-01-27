/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author FARMING
 */
public class Login extends javax.swing.JFrame {
public String usuario = "sa";
public String Password ="12";
   
    public Login() {
        this.setUndecorated(true);
        initComponents();
        setSize(849, 510);
        this.setLocationRelativeTo(null);
        AWTUtilities.setWindowOpaque(this, false);
        
      
             
      
    }
    
   void logueoConBddAdm(){
       SQLServerDataSource ds = new SQLServerDataSource();
       ds.setServerName("localhost");
       ds.setDatabaseName("CCEPC");
       ds.setUser("sa");
       ds.setPassword("12");
       ds.setPortNumber(49162);
       
       try{
           Connection cn = ds.getConnection();
           PreparedStatement ps = cn.prepareCall("select USUARIO,CONTRASEÑA,TIPO_USU FROM USUARIO "
                   + "where USUARIO = ? AND CONTRASEÑA=? and TIPO_USU='administrativo'");
          ps.setObject(1, this.Usuario.getText());
           ps.setObject(2, this.Contraseña.getText());
           ResultSet rs = ps.executeQuery();
           
           
            if (rs.next()){
               menu m = new menu();
               m.setVisible(true);
               dispose();
            }
            else {
                System.out.println("No existe usuario");
            }
               
           
        
       } catch (SQLServerException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       
   }
   
   
   
   void logueoConBddSecre(){
       SQLServerDataSource ds = new SQLServerDataSource();
       ds.setServerName("localhost");
       ds.setDatabaseName("CCEPC");
       ds.setUser("sa");
       ds.setPassword("12");
       ds.setPortNumber(49162);
       
       try{
           Connection cn = ds.getConnection();
           PreparedStatement ps = cn.prepareCall("select USUARIO,CONTRASEÑA,TIPO_USU FROM USUARIO "
                   + "where USUARIO = ? AND CONTRASEÑA=? and TIPO_USU='Secretaria'");
           ps.setObject(1, this.Usuario.getText());
           ps.setObject(2, this.Contraseña.getText());
           ResultSet rs = ps.executeQuery();
           
           if (rs.next()){
               menu m = new menu();
               m.RCurso.setVisible(false);
               m.jMenu2.setVisible(false);
               m.jMenu4.setEnabled(false);
                m.setVisible(true);
               
           }
           
           
       } catch (SQLServerException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Olvido = new javax.swing.JButton();
        Usuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        Contraseña = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        Ingresar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 204, 102));
        jPanel2.setLayout(null);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOGOR.png"))); // NOI18N
        jPanel2.add(jLabel5);
        jLabel5.setBounds(-260, 60, 590, 540);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(130, 10, 330, 490);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Lao UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 255));
        jLabel1.setText("Iniciar Sesiòn");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(530, 40, 160, 32);

        Olvido.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Olvido.setForeground(new java.awt.Color(102, 102, 102));
        Olvido.setText("¿Ha Olvidado su Usuario o Contraseña?");
        Olvido.setBorder(null);
        Olvido.setContentAreaFilled(false);
        Olvido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OlvidoActionPerformed(evt);
            }
        });
        jPanel1.add(Olvido);
        Olvido.setBounds(500, 310, 223, 17);

        Usuario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 153, 0))); // NOI18N
        Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsuarioActionPerformed(evt);
            }
        });
        Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                UsuarioKeyTyped(evt);
            }
        });
        jPanel1.add(Usuario);
        Usuario.setBounds(530, 150, 140, 30);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 255));
        jLabel2.setText("Usuario");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(530, 120, 43, 17);

        jSeparator1.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator1.setForeground(new java.awt.Color(255, 153, 0));
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(530, 180, 140, 10);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 255));
        jLabel3.setText("Contraseña");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(530, 210, 63, 17);

        Contraseña.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 153, 0))); // NOI18N
        Contraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ContraseñaKeyTyped(evt);
            }
        });
        jPanel1.add(Contraseña);
        Contraseña.setBounds(530, 240, 140, 30);

        jSeparator2.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator2.setForeground(new java.awt.Color(255, 153, 0));
        jSeparator2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(530, 270, 140, 10);

        Ingresar.setBackground(new java.awt.Color(0, 102, 153));
        Ingresar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Ingresar.setForeground(new java.awt.Color(255, 255, 255));
        Ingresar.setText("Ingresar");
        Ingresar.setBorder(null);
        Ingresar.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        Ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IngresarActionPerformed(evt);
            }
        });
        jPanel1.add(Ingresar);
        Ingresar.setBounds(560, 360, 70, 30);

        jButton2.setBackground(new java.awt.Color(0, 0, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(102, 102, 102));
        jButton2.setText("x");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(740, 10, 30, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(40, 30, 780, 450);

        jPanel3.setBackground(new java.awt.Color(245, 245, 245));
        jPanel3.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(null);
        getContentPane().add(jPanel3);
        jPanel3.setBounds(30, 20, 800, 470);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IngresarActionPerformed

  Conexiones.Conexion.setcuenta(usuario,Password);
        Conexiones.Conexion.getConexion();

        if(Conexiones.Conexion.getstatus()){
        
            System.out.println("conectado");

        }
        else{
            System.out.println("no conectado error de conexion");
        }
        logueoConBddAdm();
        logueoConBddSecre();
    }//GEN-LAST:event_IngresarActionPerformed

    private void OlvidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OlvidoActionPerformed
         Recuperar obj = new Recuperar();
        obj.setVisible(true);
        dispose();

    }//GEN-LAST:event_OlvidoActionPerformed

    private void UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsuarioActionPerformed

    private void UsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsuarioKeyTyped
        Character s = evt.getKeyChar();
        char b = evt.getKeyChar();
        if (Usuario.getText().length() >= 18) {
            evt.consume();
        }
        if ((b < 'a' || b > 'z') && (b < '0' || b > '9') && (b < 'A' || b > 'Z') && (s != 'ñ') && (s != 'Ñ')&& (s != KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_UsuarioKeyTyped

    private void ContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ContraseñaKeyTyped
        Character s = evt.getKeyChar();
        char b = evt.getKeyChar();
        if (Contraseña.getText().length() >= 18) {
            evt.consume();
        }
        if ((b < 'a' || b > 'z') && (b < '0' || b > '9') && (b < 'A' || b > 'Z') && (s != 'ñ') && (s != 'Ñ')&& (s != KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_ContraseñaKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Contraseña;
    private javax.swing.JButton Ingresar;
    private javax.swing.JButton Olvido;
    private javax.swing.JTextField Usuario;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables



}
