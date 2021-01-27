/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Conexiones.BDConexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author rosan
 */
public class Estu extends javax.swing.JDialog {
  static ResultSet res;

  
    public Estu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    
        initComponents();
           this.setLocationRelativeTo(null);
    
        this.Preins_E.setVisible(false);
        this.ff1.setVisible(false);
        this.GuardarCambios.setVisible(false);
       this. jLabel13.setVisible(false);
         Preinscripcion p= new Preinscripcion();
        CedulaeEstu.setText(p.Texto);
        Validacion();
        Validacion2();
       
    }

    public void HabilitarBoton() {
        if (CedulaeEstu.getText().length() == 8) {
            SaveEstu.setEnabled(true);
        } else {
            SaveEstu.setEnabled(false);
        }
    }

    public void Validacion() {
        String mensaje = BDConexion.bucarCedula(CedulaeEstu.getText());
        if (mensaje.equals("La Cedula ya Existe")) {
            CodExiste.setText("Cedula Persona ");
            ModificarDatosE.setEnabled(true);
             SaveEstu.setEnabled(true);
            Constancia.setEnabled(true);
            Nivel.setEnabled(true);
        } else if (CedulaeEstu.getText().isEmpty()) {
            CodExiste.setText("");

        } else {
            CodExiste.setText("Cedula no Registrada");
            ModificarDatosE.setEnabled(false);
             SaveEstu.setEnabled(false);
            Constancia.setEnabled(false);
            Nivel.setEnabled(false);
        }
    }

    public void Validacion2() {
        String mensaje = BDConexion.bucarCedulaestu(CedulaeEstu.getText());
        if (mensaje.equals("La Cedula no Existe")) {
            CodExiste2.setText(" No es estudiante");
            ModificarDatosE.setEnabled(true);
            
            Constancia.setEnabled(true);
            Nivel.setEnabled(true);
        } else if (CedulaeEstu.getText().isEmpty()) {
            CodExiste2.setText("");

        } else {
            CodExiste2.setText("Cedula estudiante");

            Constancia.setEnabled(false);
            Nivel.setEnabled(false);
            SaveEstu.setEnabled(false);
        }
    }
    
    public void GuardarEstudiante(){
          try {
            String Const, C, Niv, Sta;
            BDConexion.EntradaEstudiante(
                C = CedulaeEstu.getText(),
                Const = Constancia.getSelectedItem().toString(),
                Niv = Nivel.getSelectedItem().toString(),
                Sta = Preins_E.getSelectedItem().toString());

             
            Constancia.setSelectedItem("");
            Nivel.setSelectedItem("");
            JOptionPane.showMessageDialog(null, "Guardado Exitoso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
          SaveEstu.setEnabled(false);
        Constancia.setEnabled(false);
        Nivel.setEnabled(false);
    }
   public void ModificarEstudiante(){
         if (CedulaeEstu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Coloque Bien sus Datos", "Error", JOptionPane.ERROR_MESSAGE);
            CedulaeEstu.setText("");
            CedulaeEstu.requestFocus();
        } else {
            try {
                String b;
                BDConexion.BuscarEstudiante(Integer.parseInt(CedulaeEstu.getText()));
                b = CedulaeEstu.getText();
                CedulaeEstu.setText("");
                Constancia.setSelectedItem("");
                Nivel.setSelectedItem("");
                Preins_E.setSelectedItem("");

                CedulaeEstu.requestFocus();
                Constancia.requestFocus();
                Nivel.requestFocus();
                Preins_E.requestFocus();

                res = Conexiones.Conexion.Consulta("select CI,ESTUDIOS_REALIZADOS,NIVEL_DF,ESTADO from Estudiante");
                while (res.next()) {
                    if (res.getString(1).equals(b)) {
                        JOptionPane.showMessageDialog(null, "Datos Encontrados");
                        CedulaeEstu.setText(res.getString(1));
                        Constancia.setSelectedItem(res.getString(2));
                        Nivel.setSelectedItem(res.getString(3));
                        Preins_E.setSelectedItem(res.getString(4));

                    }

                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        Constancia.setEnabled(true);
        Nivel.setEnabled(true);
        this.GuardarCambios.setVisible(true);
        this.SaveEstu.setVisible(false);
        this. jLabel13.setVisible(true);
        this.Preins_E.setVisible(true);
        this.ff1.setVisible(true);
   }
   public void GuardarCambios(){
       try {
            PreparedStatement pps = Conexiones.Conexion.getConexion().prepareStatement("update ESTUDIANTE set ESTUDIOS_REALIZADOS='"
                + Constancia.getSelectedItem() + "', NIVEL_DF='" + Nivel.getSelectedItem() + "', ESTADO='" + Preins_E.getSelectedItem()
                + "' where CI='" + CedulaeEstu.getText() + "'");
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "LOS DATOS HAN SIDO MODIFICADOS");

            Constancia.setSelectedItem("");
            Nivel.setSelectedItem("");
            Preins_E.setSelectedItem("");
            CedulaeEstu.requestFocus();
            Constancia.requestFocus();
            Nivel.requestFocus();
            Preins_E.requestFocus();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        Constancia.setEnabled(false);
        Nivel.setEnabled(false);
        this.GuardarCambios.setEnabled(false);
        
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
        VolverPre = new javax.swing.JButton();
        ff = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        SaveEstu = new javax.swing.JButton();
        CedulaeEstu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Constancia = new javax.swing.JComboBox<>();
        Nivel = new javax.swing.JComboBox<>();
        CodExiste = new javax.swing.JTextField();
        Preins_E = new javax.swing.JComboBox<>();
        ModificarDatosE = new javax.swing.JButton();
        GuardarCambios = new javax.swing.JButton();
        CodExiste2 = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        ff1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 206, 209));
        jPanel1.setLayout(null);

        VolverPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_back_arrow_64px.png"))); // NOI18N
        VolverPre.setContentAreaFilled(false);
        VolverPre.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                VolverPreMouseMoved(evt);
            }
        });
        VolverPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolverPreActionPerformed(evt);
            }
        });
        jPanel1.add(VolverPre);
        VolverPre.setBounds(10, 26, 70, 70);

        ff.setBackground(new java.awt.Color(255, 255, 255));
        ff.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ff.setText("Constancia");
        jPanel1.add(ff);
        ff.setBounds(70, 240, 70, 17);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Nivel de Formacion");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 280, 111, 30);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_student_male_32px.png"))); // NOI18N
        jLabel1.setText("Estudiante");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(120, 80, 150, 79);

        SaveEstu.setBackground(new java.awt.Color(142, 252, 199));
        SaveEstu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        SaveEstu.setText("Guardar");
        SaveEstu.setEnabled(false);
        SaveEstu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveEstuActionPerformed(evt);
            }
        });
        jPanel1.add(SaveEstu);
        SaveEstu.setBounds(130, 460, 110, 54);

        CedulaeEstu.setBackground(new java.awt.Color(204, 206, 209));
        CedulaeEstu.setBorder(null);
        CedulaeEstu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CedulaeEstuActionPerformed(evt);
            }
        });
        CedulaeEstu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CedulaeEstuKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CedulaeEstuKeyTyped(evt);
            }
        });
        jPanel1.add(CedulaeEstu);
        CedulaeEstu.setBounds(150, 200, 105, 20);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("CI");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(120, 200, 20, 17);

        Constancia.setBackground(new java.awt.Color(22, 44, 81));
        Constancia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Posee", "De Estudio", "De Trabajo" }));
        Constancia.setEnabled(false);
        Constancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConstanciaActionPerformed(evt);
            }
        });
        jPanel1.add(Constancia);
        Constancia.setBounds(150, 240, 105, 20);

        Nivel.setBackground(new java.awt.Color(22, 44, 81));
        Nivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Educación media general", "Educación media diversificada", "Educación superior" }));
        Nivel.setEnabled(false);
        jPanel1.add(Nivel);
        Nivel.setBounds(150, 290, 177, 20);

        CodExiste.setEditable(false);
        CodExiste.setBackground(new java.awt.Color(204, 206, 209));
        CodExiste.setBorder(null);
        CodExiste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodExisteActionPerformed(evt);
            }
        });
        jPanel1.add(CodExiste);
        CodExiste.setBounds(260, 200, 140, 14);

        Preins_E.setBackground(new java.awt.Color(22, 44, 81));
        Preins_E.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "preinscrito" }));
        Preins_E.setEnabled(false);
        jPanel1.add(Preins_E);
        Preins_E.setBounds(150, 340, 90, 20);

        ModificarDatosE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_property_26px.png"))); // NOI18N
        ModificarDatosE.setText("Modificar");
        ModificarDatosE.setEnabled(false);
        ModificarDatosE.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ModificarDatosEMouseMoved(evt);
            }
        });
        ModificarDatosE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarDatosEActionPerformed(evt);
            }
        });
        jPanel1.add(ModificarDatosE);
        ModificarDatosE.setBounds(240, 567, 110, 50);

        GuardarCambios.setBackground(new java.awt.Color(142, 252, 199));
        GuardarCambios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        GuardarCambios.setText("Guardar");
        GuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarCambiosActionPerformed(evt);
            }
        });
        GuardarCambios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                GuardarCambiosKeyTyped(evt);
            }
        });
        jPanel1.add(GuardarCambios);
        GuardarCambios.setBounds(130, 460, 110, 50);

        CodExiste2.setEditable(false);
        CodExiste2.setBackground(new java.awt.Color(204, 206, 209));
        CodExiste2.setBorder(null);
        CodExiste2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodExiste2ActionPerformed(evt);
            }
        });
        jPanel1.add(CodExiste2);
        CodExiste2.setBounds(260, 180, 140, 14);

        jSeparator8.setBackground(new java.awt.Color(22, 44, 81));
        jPanel1.add(jSeparator8);
        jSeparator8.setBounds(150, 220, 105, 12);

        ff1.setBackground(new java.awt.Color(255, 255, 255));
        ff1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ff1.setText("Status");
        jPanel1.add(ff1);
        ff1.setBounds(100, 340, 50, 17);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_tick_box_26px.png"))); // NOI18N
        jPanel1.add(jLabel13);
        jLabel13.setBounds(70, 330, 30, 40);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_paper_26px_1.png"))); // NOI18N
        jPanel1.add(jLabel14);
        jLabel14.setBounds(40, 230, 30, 40);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_identification_documents_24px.png"))); // NOI18N
        jPanel1.add(jLabel15);
        jLabel15.setBounds(90, 190, 30, 40);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/er.png"))); // NOI18N
        jPanel1.add(jLabel18);
        jLabel18.setBounds(620, 0, 610, 630);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VolverPreMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VolverPreMouseMoved
     
    }//GEN-LAST:event_VolverPreMouseMoved

    private void VolverPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolverPreActionPerformed
        dispose();
    }//GEN-LAST:event_VolverPreActionPerformed

    private void SaveEstuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveEstuActionPerformed
      GuardarEstudiante();
    }//GEN-LAST:event_SaveEstuActionPerformed

    private void CedulaeEstuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaeEstuKeyReleased
        HabilitarBoton();
        Validacion();
        Validacion2();
    }//GEN-LAST:event_CedulaeEstuKeyReleased

    private void CedulaeEstuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaeEstuKeyTyped
        char car = evt.getKeyChar();
        if (CedulaeEstu.getText().length() == 8) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_CedulaeEstuKeyTyped

    private void CodExisteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodExisteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodExisteActionPerformed

    private void ModificarDatosEMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarDatosEMouseMoved
        
    }//GEN-LAST:event_ModificarDatosEMouseMoved

    private void ModificarDatosEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarDatosEActionPerformed
      ModificarEstudiante();
    }//GEN-LAST:event_ModificarDatosEActionPerformed

    private void GuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarCambiosActionPerformed
        GuardarCambios();
    }//GEN-LAST:event_GuardarCambiosActionPerformed

    private void GuardarCambiosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GuardarCambiosKeyTyped

    }//GEN-LAST:event_GuardarCambiosKeyTyped

    private void CodExiste2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodExiste2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodExiste2ActionPerformed

    private void ConstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConstanciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConstanciaActionPerformed

    private void CedulaeEstuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaeEstuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaeEstuActionPerformed

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
            java.util.logging.Logger.getLogger(Estu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Estu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Estu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Estu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Estu dialog = new Estu(new javax.swing.JFrame(), true);
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
    public static javax.swing.JTextField CedulaeEstu;
    private javax.swing.JTextField CodExiste;
    private javax.swing.JTextField CodExiste2;
    public static javax.swing.JComboBox<String> Constancia;
    public static javax.swing.JButton GuardarCambios;
    public static javax.swing.JButton ModificarDatosE;
    public static javax.swing.JComboBox<String> Nivel;
    public static javax.swing.JComboBox<String> Preins_E;
    public static javax.swing.JButton SaveEstu;
    public static javax.swing.JButton VolverPre;
    private javax.swing.JLabel ff;
    private javax.swing.JLabel ff1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator8;
    // End of variables declaration//GEN-END:variables
}
