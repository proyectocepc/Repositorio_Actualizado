/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Conexiones.BDConexion;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.sun.awt.AWTUtilities;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static vista.Inscripcion.res;

/**
 *
 * @author Familia Mijares
 */
public class Recuperar extends javax.swing.JFrame {

    static ResultSet res;

    /**
     * Creates new form Recuperar
     */
    public Recuperar() {
        this.setUndecorated(true);
        initComponents();
        setSize(849, 510);
        this.setLocationRelativeTo(null);
        AWTUtilities.setWindowOpaque(this, false);

        this.PanelDatosUsuario.setVisible(false);

        this.PanelSeguridad.setVisible(false);
    }
public String usuario = "sa";
public String Password ="12";
    public void validacionEF() {
        String mensaje = BDConexion.recuperarContra(PreguntaS.getText(), RespuestaS.getText());
        if (mensaje.equals("ENCONTRADA")) {
            JOptionPane.showMessageDialog(null, "datos cargados");
            this.PreguntaS.setVisible(false);
            this.RespuestaS.setVisible(false);
            this.labelseguridad.setVisible(false);
            this.labelcontra.setVisible(false);
            this.jLabel9.setVisible(false);
            this.labelrespuesta.setVisible(false);
            this.Buscar1.setVisible(false);
            this.PanelDatosUsuario.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "verifique la pregunta");

            this.PreguntaS.setVisible(true);
            this.RespuestaS.setVisible(true);
            this.labelseguridad.setVisible(true);
            this.labelcontra.setVisible(true);
            this.jLabel9.setVisible(true);
            this.labelrespuesta.setVisible(true);
            this.Buscar1.setVisible(true);
            this.PanelDatosUsuario.setVisible(false);
        }
    }
    void logueoConBddAdm(){
       SQLServerDataSource ds = new SQLServerDataSource();
       ds.setServerName("localhost");
       ds.setDatabaseName("CCEPC");
       ds.setUser("sa");
       ds.setPassword("12");
       ds.setPortNumber(49162);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        PanelSeguridad = new javax.swing.JPanel();
        RespuestaS = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        labelrespuesta = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        PreguntaS = new javax.swing.JTextField();
        labelseguridad = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Buscar1 = new javax.swing.JButton();
        PanelDatosUsuario = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        labelusu = new javax.swing.JLabel();
        uSU = new javax.swing.JTextField();
        labelcontra = new javax.swing.JLabel();
        cONTR = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        Buscar2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        Buscar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        CedulaU = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 204, 102));
        jPanel2.setLayout(null);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOGOR.png"))); // NOI18N
        jPanel2.add(jLabel5);
        jLabel5.setBounds(-300, 70, 590, 540);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(50, 0, 290, 500);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

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
        jButton2.setBounds(730, 10, 30, 20);

        RespuestaS.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        RespuestaS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RespuestaSActionPerformed(evt);
            }
        });

        jSeparator5.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator5.setForeground(new java.awt.Color(255, 153, 0));
        jSeparator5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        labelrespuesta.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        labelrespuesta.setForeground(new java.awt.Color(0, 153, 255));
        labelrespuesta.setText("Respuesta");

        jSeparator3.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator3.setForeground(new java.awt.Color(255, 153, 0));

        PreguntaS.setEditable(false);
        PreguntaS.setBackground(new java.awt.Color(255, 255, 255));
        PreguntaS.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        PreguntaS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreguntaSActionPerformed(evt);
            }
        });

        labelseguridad.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        labelseguridad.setForeground(new java.awt.Color(0, 153, 255));
        labelseguridad.setText("Pregunta de Seguridad");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_security_lock_32px.png"))); // NOI18N

        Buscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_circled_right_64px.png"))); // NOI18N
        Buscar1.setBorder(null);
        Buscar1.setContentAreaFilled(false);
        Buscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buscar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelSeguridadLayout = new javax.swing.GroupLayout(PanelSeguridad);
        PanelSeguridad.setLayout(PanelSeguridadLayout);
        PanelSeguridadLayout.setHorizontalGroup(
            PanelSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSeguridadLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(PanelSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RespuestaS, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelrespuesta)
                    .addComponent(labelseguridad, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PreguntaS, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelSeguridadLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(165, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSeguridadLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Buscar1)
                .addContainerGap())
        );
        PanelSeguridadLayout.setVerticalGroup(
            PanelSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSeguridadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelseguridad)
                .addGap(13, 13, 13)
                .addComponent(PreguntaS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(labelrespuesta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RespuestaS, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(Buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(PanelSeguridad);
        PanelSeguridad.setBounds(340, 0, 334, 424);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_user_groups_32px.png"))); // NOI18N
        jLabel8.setText("Usuario");

        labelusu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        labelusu.setText("Usuario");

        uSU.setEditable(false);
        uSU.setBackground(new java.awt.Color(255, 255, 255));
        uSU.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        uSU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uSUActionPerformed(evt);
            }
        });

        labelcontra.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        labelcontra.setText("Contraseña");

        cONTR.setEditable(false);
        cONTR.setBackground(new java.awt.Color(255, 255, 255));
        cONTR.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        cONTR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cONTRActionPerformed(evt);
            }
        });

        jSeparator6.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator6.setForeground(new java.awt.Color(255, 153, 0));

        jSeparator7.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator7.setForeground(new java.awt.Color(255, 153, 0));

        Buscar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_circled_right_64px.png"))); // NOI18N
        Buscar2.setBorder(null);
        Buscar2.setContentAreaFilled(false);
        Buscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Buscar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDatosUsuarioLayout = new javax.swing.GroupLayout(PanelDatosUsuario);
        PanelDatosUsuario.setLayout(PanelDatosUsuarioLayout);
        PanelDatosUsuarioLayout.setHorizontalGroup(
            PanelDatosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosUsuarioLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(PanelDatosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelDatosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelcontra, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelusu, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(uSU, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator7)
                        .addComponent(cONTR, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                    .addComponent(jLabel8))
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDatosUsuarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Buscar2)
                .addContainerGap())
            .addGroup(PanelDatosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelDatosUsuarioLayout.createSequentialGroup()
                    .addGap(48, 48, 48)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(58, Short.MAX_VALUE)))
        );
        PanelDatosUsuarioLayout.setVerticalGroup(
            PanelDatosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosUsuarioLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel8)
                .addGap(47, 47, 47)
                .addComponent(labelusu, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(uSU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(labelcontra, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cONTR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Buscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelDatosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelDatosUsuarioLayout.createSequentialGroup()
                    .addGap(173, 173, 173)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(182, Short.MAX_VALUE)))
        );

        jPanel1.add(PanelDatosUsuario);
        PanelDatosUsuario.setBounds(346, 0, 210, 365);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_user_groups_32px.png"))); // NOI18N
        jLabel11.setText("Recuperar");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(580, 80, 160, 40);

        Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_circled_right_64px.png"))); // NOI18N
        Buscar.setBorder(null);
        Buscar.setContentAreaFilled(false);
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        jPanel1.add(Buscar);
        Buscar.setBounds(670, 360, 80, 70);

        jPanel4.setBackground(new java.awt.Color(252, 252, 252));

        CedulaU.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        CedulaU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CedulaUActionPerformed(evt);
            }
        });

        jSeparator2.setBackground(new java.awt.Color(255, 153, 0));
        jSeparator2.setForeground(new java.awt.Color(255, 153, 0));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 255));
        jLabel4.setText("Cédula");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CedulaU, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CedulaU, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(204, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(550, 0, 220, 440);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(40, 30, 780, 450);

        jPanel3.setBackground(new java.awt.Color(245, 245, 245));
        jPanel3.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(30, 20, 800, 470);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
   
         Conexiones.Conexion.setcuenta(usuario,Password);
        Conexiones.Conexion.getConexion();

        if(Conexiones.Conexion.getstatus()){
        
            System.out.println("conectado");

        }
        else{
            System.out.println("no conectado error de conexion");
        }
        
        logueoConBddAdm();
       
        
        
        if (CedulaU.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Coloque Bien sus Datos", "Error", JOptionPane.ERROR_MESSAGE);
            CedulaU.setText("");
            CedulaU.requestFocus();
        } else {
            try {
                String b;
                BDConexion.BuscarUsuario(Integer.parseInt(CedulaU.getText()));
                b = CedulaU.getText();
                CedulaU.setText("");

                res = Conexiones.Conexion.Consulta("select PREGUNTA_SECRETA, CI from USUARIO");
                while (res.next()) {
                    if (res.getString(2).equals(b)) {
                        JOptionPane.showMessageDialog(null, "Datos Encontrados");
                        PreguntaS.setText(res.getString(1));
                        CedulaU.setText(res.getString(2));

                    }
                }

                this.PanelSeguridad.setVisible(true);
                this.Buscar1.setVisible(true);
                this.Buscar.setVisible(false);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }

    }//GEN-LAST:event_BuscarActionPerformed

    private void Buscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar1ActionPerformed
        String b, cd = "q", c;
        try {

            res = Conexiones.Conexion.Consulta("select USUARIO,CONTRASEÑA,RESPUESTA_sEC,CI from USUARIO where"
                    + " PREGUNTA_SECRETA='" + PreguntaS.getText() + "' AND RESPUESTA_sEC= '" + RespuestaS.getText() + "'");
            b = CedulaU.getText();
            c = RespuestaS.getText();
            validacionEF();
            while (res.next()) {
                {
                    if (res.getString(4).equals(b)) {
                        uSU.setText(res.getString(1));
                        cONTR.setText(res.getString(2));
                        cd = "encontro";
                    }
                }

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "Datos no Encontrados");
        }
    }//GEN-LAST:event_Buscar1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void CedulaUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaUActionPerformed

    private void cONTRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cONTRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cONTRActionPerformed

    private void RespuestaSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RespuestaSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RespuestaSActionPerformed

    private void PreguntaSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreguntaSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PreguntaSActionPerformed

    private void uSUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uSUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uSUActionPerformed

    private void Buscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Buscar2ActionPerformed
        Login obj = new Login();
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_Buscar2ActionPerformed

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
            java.util.logging.Logger.getLogger(Recuperar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recuperar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recuperar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recuperar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recuperar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton Buscar;
    public static javax.swing.JButton Buscar1;
    public static javax.swing.JButton Buscar2;
    public static javax.swing.JTextField CedulaU;
    private javax.swing.JPanel PanelDatosUsuario;
    private javax.swing.JPanel PanelSeguridad;
    public static javax.swing.JTextField PreguntaS;
    public static javax.swing.JTextField RespuestaS;
    private javax.swing.JTextField cONTR;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel labelcontra;
    private javax.swing.JLabel labelrespuesta;
    private javax.swing.JLabel labelseguridad;
    private javax.swing.JLabel labelusu;
    private javax.swing.JTextField uSU;
    // End of variables declaration//GEN-END:variables
}
