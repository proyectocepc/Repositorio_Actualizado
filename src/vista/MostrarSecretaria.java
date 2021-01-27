/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Conexiones.BDConexion;
import Controlador.SoloLetras;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author rosan
 */
public class MostrarSecretaria extends javax.swing.JInternalFrame {

    static ResultSet res;
    Connection con = null;
    TableRowSorter trs;

    /**
     * Creates new form MostrarCurso
     */
    public MostrarSecretaria() {
        initComponents();
        setSize(1243, 739);
        
        CargarDatosCurso();
        CargarDatosC();
    }

     public void  CargarDatosCurso(){
        DefaultTableModel modelo =(DefaultTableModel) Tmostrarcursos.getModel();
        modelo.setRowCount(0);
        res = Conexiones.Conexion.Consulta("select * from CURSO");
        try{
            while(res.next()){
                Vector v = new Vector();
            v.add(res.getInt(1));
            v.add(res.getString(2));
            v.add(res.getString(3));
            v.add(res.getInt(4));
            v.add(res.getString(5));
            v.add(res.getString(6));
            modelo.addRow(v);
            Tmostrarcursos.setModel(modelo);
            }
        }catch (SQLException e){            
        }
    }
    
    public void FiltrarDatos(String Valor){
        DefaultTableModel modelo =(DefaultTableModel) Tmostrarcursos.getModel();
        modelo.setRowCount(0);
        res = Conexiones.Conexion.Consulta("select * from CURSO where NOMBREC LIKE '%"+Valor+"%'");
        try{
            while(res.next()){
                Vector v = new Vector();
            v.add(res.getInt(1));
            v.add(res.getString(2));
            v.add(res.getString(3));
            v.add(res.getInt(4));
            v.add(res.getString(5));
            v.add(res.getString(6));
            modelo.addRow(v);
            }
             Tmostrarcursos.setModel(modelo);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al filtrar"+ e.getMessage());
            
        }
    }
    
 
         public void  CargarDatosC(){
        DefaultTableModel modelo =(DefaultTableModel) TABLAp.getModel();
        modelo.setRowCount(0);
        res = Conexiones.Conexion.Consulta("SELECT CURSO.NOMBREC,SECCION.NOMBRE, SECCION.STATUS FROM CURSO JOIN SECCION ON CURSO.ID_CURSO=SECCION.ID_CURSO");
        try{
            while(res.next()){
                Vector v = new Vector();
            v.add(res.getString(1));
            v.add(res.getString(2));
            v.add(res.getString(3));
           
            modelo.addRow(v);
            TABLAp.setModel(modelo);
            }
        }catch (SQLException e){            
        }
    }
     public void FiltrarDatosS(String Valor){
        DefaultTableModel modelo =(DefaultTableModel) TABLAp.getModel();
        modelo.setRowCount(0);
        res = Conexiones.Conexion.Consulta(" SELECT CURSO.NOMBREC,SECCION.NOMBRE, SECCION.STATUS FROM CURSO JOIN SECCION "
                + "ON CURSO.ID_CURSO=SECCION.ID_CURSO WHERE CURSO.NOMBREC LIKE'%"+Valor+"%'");
        try{
            while(res.next()){
                Vector v = new Vector();
            v.add(res.getString(1));
            v.add(res.getString(2));
            v.add(res.getString(3));
           
            modelo.addRow(v);
            }
             TABLAp.setModel(modelo);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al filtrar"+ e.getMessage());
            
        }
    }
     
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelsuperior4 = new javax.swing.JPanel();
        buscar1 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        buscar = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        Bactualizar = new javax.swing.JButton();
        irmenu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tmostrarcursos = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        panelsuperior2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TABLAp = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cursos Registrados");
        getContentPane().setLayout(null);

        panelsuperior4.setBackground(new java.awt.Color(22, 44, 81));

        buscar1.setBackground(new java.awt.Color(22, 44, 81));
        buscar1.setForeground(new java.awt.Color(255, 255, 255));
        buscar1.setBorder(null);
        buscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscar1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscar1KeyTyped(evt);
            }
        });

        jSeparator3.setBackground(new java.awt.Color(51, 153, 255));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_32px_2.png"))); // NOI18N
        jButton1.setToolTipText("Buscar Mediante el Nombre del Curso");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_32px_2.png"))); // NOI18N
        jButton4.setToolTipText("Buscar Mediante el Nombre del Curso"); // NOI18N
        jButton4.setBorder(null);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        buscar.setBackground(new java.awt.Color(22, 44, 81));
        buscar.setForeground(new java.awt.Color(255, 255, 255));
        buscar.setBorder(null);
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscarKeyTyped(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(153, 204, 255));

        javax.swing.GroupLayout panelsuperior4Layout = new javax.swing.GroupLayout(panelsuperior4);
        panelsuperior4.setLayout(panelsuperior4Layout);
        panelsuperior4Layout.setHorizontalGroup(
            panelsuperior4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelsuperior4Layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jButton4)
                .addGap(7, 7, 7)
                .addGroup(panelsuperior4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 446, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelsuperior4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buscar1)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(169, 169, 169))
        );
        panelsuperior4Layout.setVerticalGroup(
            panelsuperior4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelsuperior4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelsuperior4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelsuperior4Layout.createSequentialGroup()
                        .addComponent(buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(panelsuperior4Layout.createSequentialGroup()
                .addGroup(panelsuperior4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelsuperior4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(panelsuperior4);
        panelsuperior4.setBounds(-10, 2, 1260, 70);

        Bactualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Bactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ACTUALIZAR.png"))); // NOI18N
        Bactualizar.setBorder(null);
        Bactualizar.setContentAreaFilled(false);
        Bactualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Bactualizar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Bactualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Bactualizar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BactualizarMouseMoved(evt);
            }
        });
        Bactualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BactualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BactualizarMouseExited(evt);
            }
        });
        Bactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BactualizarActionPerformed(evt);
            }
        });
        getContentPane().add(Bactualizar);
        Bactualizar.setBounds(770, 560, 90, 90);

        irmenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        irmenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_home_64px_1.png"))); // NOI18N
        irmenu.setBorder(null);
        irmenu.setContentAreaFilled(false);
        irmenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        irmenu.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        irmenu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        irmenu.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                irmenuMouseMoved(evt);
            }
        });
        irmenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                irmenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                irmenuMouseExited(evt);
            }
        });
        irmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irmenuActionPerformed(evt);
            }
        });
        getContentPane().add(irmenu);
        irmenu.setBounds(10, 70, 108, 90);

        Tmostrarcursos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Tmostrarcursos.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        Tmostrarcursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Tipo", "Costo", "Pasantía", "Material"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tmostrarcursos.setGridColor(new java.awt.Color(0, 0, 0));
        Tmostrarcursos.setRowHeight(25);
        Tmostrarcursos.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jScrollPane1.setViewportView(Tmostrarcursos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(720, 170, 460, 390);

        jSeparator2.setBackground(new java.awt.Color(22, 44, 81));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(680, 60, 10, 570);

        panelsuperior2.setBackground(new java.awt.Color(22, 44, 81));
        panelsuperior2.setLayout(null);
        getContentPane().add(panelsuperior2);
        panelsuperior2.setBounds(-20, 660, 1280, 70);

        TABLAp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Curso", "Seccion", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TABLAp.setRowHeight(25);
        TABLAp.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jScrollPane2.setViewportView(TABLAp);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(180, 170, 452, 402);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Lao UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SECCIONES APERTURADAS");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(250, 120, 320, 30);

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Lao UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("CURSOS REGISTRADOS");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(830, 120, 280, 32);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/om.png"))); // NOI18N
        getContentPane().add(jLabel11);
        jLabel11.setBounds(0, 54, 1250, 730);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar1KeyReleased
        FiltrarDatos(buscar1.getText());
    }//GEN-LAST:event_buscar1KeyReleased

    private void buscar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_buscar1KeyTyped

    private void BactualizarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BactualizarMouseMoved

    }//GEN-LAST:event_BactualizarMouseMoved

    private void BactualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BactualizarMouseEntered
        Bactualizar.setText("Actualizar");
    }//GEN-LAST:event_BactualizarMouseEntered

    private void BactualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BactualizarMouseExited
        Bactualizar.setText("");
    }//GEN-LAST:event_BactualizarMouseExited

    private void BactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BactualizarActionPerformed
        CargarDatosCurso();

    }//GEN-LAST:event_BactualizarActionPerformed

    private void irmenuMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_irmenuMouseMoved
        irmenu.setToolTipText("Ir al Menu Principal");
    }//GEN-LAST:event_irmenuMouseMoved

    private void irmenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_irmenuMouseEntered
        irmenu.setText("Ir a Menu");
    }//GEN-LAST:event_irmenuMouseEntered

    private void irmenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_irmenuMouseExited
        irmenu.setText("");
    }//GEN-LAST:event_irmenuMouseExited

    private void irmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irmenuActionPerformed

       
        dispose();
    }//GEN-LAST:event_irmenuActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarActionPerformed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        FiltrarDatos(buscar.getText());
    }//GEN-LAST:event_buscarKeyReleased

    private void buscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyTyped

    }//GEN-LAST:event_buscarKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton Bactualizar;
    public static javax.swing.JTable TABLAp;
    public static javax.swing.JTable Tmostrarcursos;
    public static javax.swing.JTextField buscar;
    public static javax.swing.JTextField buscar1;
    public static javax.swing.JButton irmenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel panelsuperior2;
    private javax.swing.JPanel panelsuperior4;
    // End of variables declaration//GEN-END:variables
}
