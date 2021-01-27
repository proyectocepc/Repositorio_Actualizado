/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Conexiones.BDConexion;
import Controlador.SoloLetras;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia Mijares
 */
public class Profesor extends javax.swing.JDialog {

    static ResultSet res;

    /**
     * Creates new form Profesor
     */
    public Profesor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        this.setLocationRelativeTo(null);

        Cedulaprof.setText(empleados.Texto);

        Validacionprof();
        this.Cambiosprof.setVisible(false);
    }

    public void Validacionprof() {
        String mensaje = BDConexion.bucarCedulaE(Cedulaprof.getText());
        if (mensaje.equals("La Cedula ya Existe")) {
            CodExisteprof.setText("Cedula empleado");
            ModificarProf.setEnabled(true);
            Especialidadprof.setEnabled(true);
            EstudiosRprof.setEnabled(true);
            if (Cedulaprof.getText().length() == 8) {
                guardarprof.setEnabled(true);
            }

        } else if (Cedulaprof.getText().isEmpty()) {
            CodExisteprof.setText("");

        } else {
            CodExisteprof.setText("Cedula no Registrada");
            Especialidadprof.setEnabled(false);
            EstudiosRprof.setEnabled(false);
            guardarprof.setEnabled(false);

        }
    }

    public void GuardarDatosProf() {
        if (Cedulaprof.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "rellene todos los campos obligatorios", "informacion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                String Cedul, Espec, estu;
                BDConexion.EntradaProfesor(
                        Espec = Especialidadprof.getText(),
                        estu = EstudiosRprof.getText(),
                        Cedul = Cedulaprof.getText());
                JOptionPane.showMessageDialog(null, "Guardado Exitoso");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }

        Especialidadprof.setText("");
        EstudiosRprof.setText("");
        Especialidadprof.setEnabled(false);
        EstudiosRprof.setEnabled(false);
    }

    public void ModificarDatosProf() {
        if (Cedulaprof.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Coloque Bien sus Datos", "Error", JOptionPane.ERROR_MESSAGE);
            Cedulaprof.setText("");
            Cedulaprof.requestFocus();
        } else {
            try {
                String b;
                BDConexion.BuscarProfesor(Integer.parseInt(Cedulaprof.getText()));
                b = Cedulaprof.getText();
                Cedulaprof.setText("");
                EstudiosRprof.setText("");
                Especialidadprof.setText("");

                EstudiosRprof.requestFocus();
                Especialidadprof.requestFocus();

                res = Conexiones.Conexion.Consulta("Select CI,ESPECIALIDAD,ESTUDIOS_REAL from PROFESOR");
                while (res.next()) {
                    if (res.getString(1).equals(b)) {
                        JOptionPane.showMessageDialog(null, "Datos Encontrados");
                        EstudiosRprof.setText(res.getString(3));
                        Especialidadprof.setText(res.getString(2));
                        Cedulaprof.setText(res.getString(1));
                    }

                }
                Cedulaprof.setEditable(false);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        Especialidadprof.setEnabled(true);
        EstudiosRprof.setEnabled(true);
        this.Cambiosprof.setVisible(true);
        guardarprof.setVisible(false);
    }
    public void GuardarCambiosDatosProf(){
           try {
            PreparedStatement pps = Conexiones.Conexion.getConexion().prepareStatement("update PROFESOR set ESPECIALIDAD='"
                    + Especialidadprof.getText() + "', ESTUDIOS_REAL='" + EstudiosRprof.getText()
                    + "' where CI='" + Cedulaprof.getText() + "'");
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "LOS DATOS HAN SIDO MODIFICADOS");

            Especialidadprof.setText("");
            EstudiosRprof.setText("");

            Especialidadprof.requestFocus();
            EstudiosRprof.requestFocus();

        } catch (SQLException e) {
        }

        Especialidadprof.setEnabled(false);
        EstudiosRprof.setEnabled(false);
        this.Cambiosprof.setVisible(false);
        guardarprof.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Especialidadprof = new javax.swing.JTextField();
        EstudiosRprof = new javax.swing.JTextField();
        VolverEmple = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        Cedulaprof = new javax.swing.JTextField();
        CodExisteprof = new javax.swing.JTextField();
        guardarprof = new javax.swing.JButton();
        ModificarProf = new javax.swing.JButton();
        Cambiosprof = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();

        jPanel1.setBackground(new java.awt.Color(204, 206, 209));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Especialidad");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Estudios Realizados");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_classroom_30px.png"))); // NOI18N
        jLabel3.setText("Profesor");

        Especialidadprof.setBackground(new java.awt.Color(204, 206, 209));
        Especialidadprof.setBorder(null);
        Especialidadprof.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Especialidadprof.setEnabled(false);
        Especialidadprof.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EspecialidadprofKeyTyped(evt);
            }
        });

        EstudiosRprof.setBackground(new java.awt.Color(204, 206, 209));
        EstudiosRprof.setBorder(null);
        EstudiosRprof.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        EstudiosRprof.setEnabled(false);
        EstudiosRprof.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EstudiosRprofKeyTyped(evt);
            }
        });

        VolverEmple.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_back_arrow_64px.png"))); // NOI18N
        VolverEmple.setBorder(null);
        VolverEmple.setContentAreaFilled(false);
        VolverEmple.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                VolverEmpleMouseMoved(evt);
            }
        });
        VolverEmple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolverEmpleActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Cédula");

        Cedulaprof.setBackground(new java.awt.Color(204, 206, 209));
        Cedulaprof.setBorder(null);
        Cedulaprof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CedulaprofActionPerformed(evt);
            }
        });
        Cedulaprof.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CedulaprofKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CedulaprofKeyTyped(evt);
            }
        });

        CodExisteprof.setEditable(false);
        CodExisteprof.setBackground(new java.awt.Color(204, 206, 209));
        CodExisteprof.setBorder(null);

        guardarprof.setBackground(new java.awt.Color(142, 252, 199));
        guardarprof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        guardarprof.setText("Guardar");
        guardarprof.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        guardarprof.setEnabled(false);
        guardarprof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarprofActionPerformed(evt);
            }
        });

        ModificarProf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_property_26px.png"))); // NOI18N
        ModificarProf.setText("Modificar");
        ModificarProf.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        ModificarProf.setEnabled(false);
        ModificarProf.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ModificarProfMouseMoved(evt);
            }
        });
        ModificarProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarProfActionPerformed(evt);
            }
        });

        Cambiosprof.setBackground(new java.awt.Color(142, 252, 199));
        Cambiosprof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        Cambiosprof.setText("Guardar");
        Cambiosprof.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Cambiosprof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CambiosprofActionPerformed(evt);
            }
        });
        Cambiosprof.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CambiosprofKeyTyped(evt);
            }
        });

        jSeparator8.setBackground(new java.awt.Color(0, 51, 102));
        jSeparator8.setForeground(new java.awt.Color(0, 51, 102));

        jSeparator9.setBackground(new java.awt.Color(0, 51, 102));
        jSeparator9.setForeground(new java.awt.Color(0, 51, 102));

        jSeparator10.setBackground(new java.awt.Color(0, 51, 102));
        jSeparator10.setForeground(new java.awt.Color(0, 51, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(VolverEmple)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(Cedulaprof, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(CodExisteprof, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(Especialidadprof, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(EstudiosRprof, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(Cambiosprof))
                            .addComponent(guardarprof, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(ModificarProf, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(VolverEmple)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cedulaprof, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CodExisteprof, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(Especialidadprof, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(11, 11, 11)
                .addComponent(EstudiosRprof, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cambiosprof, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardarprof, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ModificarProf, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VolverEmpleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolverEmpleActionPerformed
        dispose();
    }//GEN-LAST:event_VolverEmpleActionPerformed

    private void VolverEmpleMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VolverEmpleMouseMoved
        
    }//GEN-LAST:event_VolverEmpleMouseMoved

    private void guardarprofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarprofActionPerformed
        GuardarDatosProf();
    }//GEN-LAST:event_guardarprofActionPerformed

    private void CedulaprofKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaprofKeyTyped
        char car = evt.getKeyChar();
        if (Cedulaprof.getText().length() >= 8) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_CedulaprofKeyTyped

    private void EspecialidadprofKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EspecialidadprofKeyTyped
        SoloLetras.letrasYespacio(evt, Especialidadprof.getText());
    }//GEN-LAST:event_EspecialidadprofKeyTyped

    private void EstudiosRprofKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EstudiosRprofKeyTyped
        SoloLetras.letrasYespacio(evt, EstudiosRprof.getText());
    }//GEN-LAST:event_EstudiosRprofKeyTyped

    private void CedulaprofKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaprofKeyReleased
        Validacionprof();

    }//GEN-LAST:event_CedulaprofKeyReleased

    private void ModificarProfMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarProfMouseMoved

    }//GEN-LAST:event_ModificarProfMouseMoved

    private void ModificarProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarProfActionPerformed
        ModificarDatosProf();
    }//GEN-LAST:event_ModificarProfActionPerformed

    private void CambiosprofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CambiosprofActionPerformed
     GuardarCambiosDatosProf();
    }//GEN-LAST:event_CambiosprofActionPerformed

    private void CambiosprofKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CambiosprofKeyTyped

    }//GEN-LAST:event_CambiosprofKeyTyped

    private void CedulaprofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaprofActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaprofActionPerformed

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
            java.util.logging.Logger.getLogger(Profesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Profesor dialog = new Profesor(new javax.swing.JFrame(), true);
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
    public static javax.swing.JButton Cambiosprof;
    public static javax.swing.JTextField Cedulaprof;
    public static javax.swing.JTextField CodExisteprof;
    public static javax.swing.JTextField Especialidadprof;
    public static javax.swing.JTextField EstudiosRprof;
    public static javax.swing.JButton ModificarProf;
    public static javax.swing.JButton VolverEmple;
    public static javax.swing.JButton guardarprof;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    // End of variables declaration//GEN-END:variables
}
