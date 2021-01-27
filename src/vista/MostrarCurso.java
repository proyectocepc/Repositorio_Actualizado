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
public class MostrarCurso extends javax.swing.JInternalFrame {

    static ResultSet res;
    Connection con = null;
    TableRowSorter trs;

    /**
     * Creates new form MostrarCurso
     */
    public MostrarCurso() {
        initComponents();
        setSize(1243, 739);
        
        CargarDatosCurso();
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
            v.add(res.getString(4));
            v.add(res.getString(5));
            v.add(res.getString(6));
            modelo.addRow(v);
            Tmostrarcursos.setModel(modelo);
            }
        }catch (SQLException e){  
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void HabilitarBoton(){
        if(j1.getText().length()>=4){
            BuscarCursos.setEnabled(true);
        }else{
            BuscarCursos.setEnabled(false);
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
            v.add(res.getString(4));
            v.add(res.getString(5));
            v.add(res.getString(6));
            v.add(res.getInt(7));
            modelo.addRow(v);
            }
             Tmostrarcursos.setModel(modelo);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al filtrar"+ e.getMessage());
            
        }
    }
    public void GuardarCambios(){
         try {
            PreparedStatement pps = Conexiones.Conexion.getConexion().prepareStatement("update CURSO set NOMBREC='"
                    + j2.getText() + "', TIPO='" + j3.getSelectedItem() + "', PASANTIA='" + j5.getSelectedItem() + "', MATERIAL='" + j6.getText()
                    + "', STATUS='" + j7.getSelectedItem() + Costo.getText()+ "' where ID_Curso='" + j1.getText() + "'");
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "LOS DATOS HAN SIDO MODIFICADOS");

            j2.setText("");
            Costo.setText("");
            j3.setSelectedItem("");
            j5.setSelectedItem("");
            j6.setText("");
            j7.setSelectedItem("");
            j1.requestFocus();
            j2.requestFocus();
            j3.requestFocus();
          Costo.requestFocus();
            j5.requestFocus();
            j6.requestFocus();
            

         j2.setEnabled(false);
        j3.setEnabled(false);
    
        j5.setEnabled(false);
        j6.setEnabled(false);
        j7.setEnabled(false);
         Costo.setEnabled(true);
        Addmodulos.setEnabled(false);
        j1.setEditable(true);
        } catch (SQLException e) {
        }
    }
    public void BuscarCurso(){
         if (j1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Coloque Bien sus Datos", "Error", JOptionPane.ERROR_MESSAGE);
            j1.setText("");
            j1.requestFocus();
        } else {
            try {
                String b;
                BDConexion.BuscarCurso(Integer.parseInt(j1.getText()));
                b = j1.getText();
                j1.setText("");
                j2.setText("");
                j3.setSelectedItem("");
               
                j5.setSelectedItem("");
                j6.setText("");
               j7.setSelectedItem("");
               Costo.setText("");
                j1.requestFocus();
                j2.requestFocus();
                j3.requestFocus();
             
                j5.requestFocus();
                j6.requestFocus();
               j7.requestFocus();
               Costo.requestFocus();
                res = Conexiones.Conexion.Consulta("select *from CURSO");
                while (res.next()) {
                    
                    if (res.getString(1).equals(b)) {
                      JOptionPane.showMessageDialog(null, "Datos Encontrados");
                        j1.setText(res.getString(1));
                        j2.setText(res.getString(2));
                        j3.setSelectedItem(res.getString(3));
              
                        j5.setSelectedItem(res.getString(4));
                        j6.setText(res.getString(5));
                        j7.setSelectedItem(res.getString(6));
                        Costo.setText(res.getString(7));
                     j2.setEnabled(true);
        j3.setEnabled(true);
   
        j5.setEnabled(true);
        j6.setEnabled(true);
        j7.setEnabled(true);
        Costo.setEnabled(true);
      
        GuardarCambios.setEnabled(true);
        Addmodulos.setEnabled(true);
                
               j1.setEditable(false);
                   
               break;
                   
                  
                    } 
                     
                } 
                       
                      
                  
               
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
     
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelsuperior4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tmostrarcursos = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        panelsuperior2 = new javax.swing.JPanel();
        buscar1 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        Bagregarcursos = new javax.swing.JButton();
        Bactualizar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        panelestu = new javax.swing.JPanel();
        GuardarCambios = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        j5 = new javax.swing.JComboBox<>();
        j2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        j1 = new javax.swing.JTextField();
        j3 = new javax.swing.JComboBox<>();
        j7 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        BuscarCursos = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        j6 = new javax.swing.JTextField();
        Addmodulos = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        Costo = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cursos Registrados");
        getContentPane().setLayout(null);

        panelsuperior4.setBackground(new java.awt.Color(0, 153, 204));

        javax.swing.GroupLayout panelsuperior4Layout = new javax.swing.GroupLayout(panelsuperior4);
        panelsuperior4.setLayout(panelsuperior4Layout);
        panelsuperior4Layout.setHorizontalGroup(
            panelsuperior4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1270, Short.MAX_VALUE)
        );
        panelsuperior4Layout.setVerticalGroup(
            panelsuperior4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        getContentPane().add(panelsuperior4);
        panelsuperior4.setBounds(0, 2, 1270, 60);

        Tmostrarcursos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Tmostrarcursos.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        Tmostrarcursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Tipo", "Pasantia", "material", "status"
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
        jScrollPane1.setBounds(740, 170, 460, 390);

        jSeparator2.setBackground(new java.awt.Color(255, 204, 102));
        jSeparator2.setForeground(new java.awt.Color(255, 204, 102));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(700, 60, 10, 570);

        panelsuperior2.setBackground(new java.awt.Color(0, 153, 204));
        panelsuperior2.setLayout(null);
        getContentPane().add(panelsuperior2);
        panelsuperior2.setBounds(0, 680, 1280, 50);

        buscar1.setBackground(new java.awt.Color(0, 0, 0));
        buscar1.setForeground(new java.awt.Color(255, 255, 255));
        buscar1.setBorder(null);
        buscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar1ActionPerformed(evt);
            }
        });
        buscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscar1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscar1KeyTyped(evt);
            }
        });
        getContentPane().add(buscar1);
        buscar1.setBounds(900, 124, 146, 20);

        jSeparator3.setBackground(new java.awt.Color(255, 204, 102));
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(900, 150, 146, 10);

        jPanel2.setBackground(new java.awt.Color(248, 247, 247));
        jPanel2.setLayout(null);

        Bagregarcursos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_plus_32px.png"))); // NOI18N
        Bagregarcursos.setText("Registrar Curso");
        Bagregarcursos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Bagregarcursos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Bagregarcursos.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Bagregarcursos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Bagregarcursos.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BagregarcursosMouseMoved(evt);
            }
        });
        Bagregarcursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BagregarcursosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BagregarcursosMouseExited(evt);
            }
        });
        Bagregarcursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BagregarcursosActionPerformed(evt);
            }
        });
        jPanel2.add(Bagregarcursos);
        Bagregarcursos.setBounds(859, 507, 107, 59);

        Bactualizar.setBackground(new java.awt.Color(255, 153, 51));
        Bactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_refresh_32px.png"))); // NOI18N
        Bactualizar.setText("Actualizar ");
        Bactualizar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
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
        jPanel2.add(Bactualizar);
        Bactualizar.setBounds(972, 507, 90, 59);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_32px_2.png"))); // NOI18N
        jButton1.setToolTipText("Buscar Mediante el Nombre del Curso");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(829, 60, 49, 33);

        panelestu.setBackground(new java.awt.Color(255, 255, 255));

        GuardarCambios.setBackground(new java.awt.Color(142, 252, 199));
        GuardarCambios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        GuardarCambios.setText("Guardar");
        GuardarCambios.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        GuardarCambios.setEnabled(false);
        GuardarCambios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        GuardarCambios.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        GuardarCambios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        GuardarCambios.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                GuardarCambiosMouseMoved(evt);
            }
        });
        GuardarCambios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GuardarCambiosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                GuardarCambiosMouseExited(evt);
            }
        });
        GuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarCambiosActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Pasantia");

        j5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No ", "Si" }));
        j5.setEnabled(false);

        j2.setBorder(null);
        j2.setEnabled(false);
        j2.setSelectionColor(new java.awt.Color(102, 102, 102));
        j2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                j2KeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Tipo:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Código");

        j1.setBorder(null);
        j1.setSelectionColor(new java.awt.Color(102, 102, 102));
        j1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                j1ActionPerformed(evt);
            }
        });
        j1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                j1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                j1KeyTyped(evt);
            }
        });

        j3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Especial", "Tecnico" }));
        j3.setEnabled(false);

        j7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disponible", "Finalizado" }));
        j7.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Status:");

        BuscarCursos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_30px.png"))); // NOI18N
        BuscarCursos.setBorder(null);
        BuscarCursos.setContentAreaFilled(false);
        BuscarCursos.setEnabled(false);
        BuscarCursos.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuscarCursosMouseMoved(evt);
            }
        });
        BuscarCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarCursosActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Material");

        j6.setBorder(null);
        j6.setEnabled(false);
        j6.setSelectionColor(new java.awt.Color(102, 102, 102));
        j6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                j6KeyTyped(evt);
            }
        });

        Addmodulos.setBackground(new java.awt.Color(0, 153, 255));
        Addmodulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_plus_32px.png"))); // NOI18N
        Addmodulos.setText("Modulos");
        Addmodulos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Addmodulos.setEnabled(false);
        Addmodulos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Addmodulos.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Addmodulos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Addmodulos.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                AddmodulosMouseMoved(evt);
            }
        });
        Addmodulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AddmodulosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AddmodulosMouseExited(evt);
            }
        });
        Addmodulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddmodulosActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(22, 44, 81));

        jSeparator4.setBackground(new java.awt.Color(22, 44, 81));

        jSeparator5.setBackground(new java.awt.Color(22, 44, 81));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_autograph_30px.png"))); // NOI18N

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_course_24px.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_tick_box_26px.png"))); // NOI18N

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_course_24px.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_deployment_32px.png"))); // NOI18N

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_paper_money_32px.png"))); // NOI18N

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setText("Costo:");

        Costo.setBorder(null);
        Costo.setEnabled(false);
        Costo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CostoActionPerformed(evt);
            }
        });
        Costo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CostoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CostoKeyTyped(evt);
            }
        });

        jSeparator8.setBackground(new java.awt.Color(22, 44, 81));

        javax.swing.GroupLayout panelestuLayout = new javax.swing.GroupLayout(panelestu);
        panelestu.setLayout(panelestuLayout);
        panelestuLayout.setHorizontalGroup(
            panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelestuLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelestuLayout.createSequentialGroup()
                        .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelestuLayout.createSequentialGroup()
                                .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelestuLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)
                                        .addGap(2, 2, 2)
                                        .addComponent(j5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelestuLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(59, 59, 59)
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(5, 5, 5))
                            .addGroup(panelestuLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(j2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelestuLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(Costo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(67, 67, 67)
                        .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16))
                        .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelestuLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(j3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelestuLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel6)
                                .addGap(20, 20, 20)
                                .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(j6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(103, 103, 103))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelestuLayout.createSequentialGroup()
                        .addComponent(GuardarCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(Addmodulos, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))))
            .addGroup(panelestuLayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelestuLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(2, 2, 2)
                        .addComponent(j7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelestuLayout.createSequentialGroup()
                        .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BuscarCursos)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelestuLayout.setVerticalGroup(
            panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelestuLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BuscarCursos)
                    .addGroup(panelestuLayout.createSequentialGroup()
                        .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelestuLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(j1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelestuLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(j3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGroup(panelestuLayout.createSequentialGroup()
                                    .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(j2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                    .addGap(3, 3, 3)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelestuLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelestuLayout.createSequentialGroup()
                        .addGap(0, 32, Short.MAX_VALUE)
                        .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(j5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelestuLayout.createSequentialGroup()
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4))
                                .addGroup(panelestuLayout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6)
                                        .addComponent(j6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(9, 9, 9))
                    .addGroup(panelestuLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(panelestuLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(panelestuLayout.createSequentialGroup()
                                .addComponent(Costo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20)
                .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(j7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(panelestuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(GuardarCambios)
                    .addComponent(Addmodulos))
                .addGap(11, 11, 11))
        );

        jPanel2.add(panelestu);
        panelestu.setBounds(52, 77, 598, 499);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_property_26px.png"))); // NOI18N
        jPanel2.add(jLabel5);
        jLabel5.setBounds(217, 45, 26, 26);

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Lao UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MODIFICAR CURSO");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(249, 41, 240, 30);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOGOR.png"))); // NOI18N
        jPanel2.add(jLabel17);
        jLabel17.setBounds(680, 190, 650, 520);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/er.png"))); // NOI18N
        jPanel2.add(jLabel11);
        jLabel11.setBounds(0, 0, 1230, 620);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(20, 60, 1230, 630);

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
        
    }//GEN-LAST:event_BactualizarMouseEntered

    private void BactualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BactualizarMouseExited
        
    }//GEN-LAST:event_BactualizarMouseExited

    private void BactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BactualizarActionPerformed
        CargarDatosCurso();

    }//GEN-LAST:event_BactualizarActionPerformed

    private void BagregarcursosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BagregarcursosMouseMoved

    }//GEN-LAST:event_BagregarcursosMouseMoved

    private void BagregarcursosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BagregarcursosMouseEntered
        
    }//GEN-LAST:event_BagregarcursosMouseEntered

    private void BagregarcursosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BagregarcursosMouseExited
       
    }//GEN-LAST:event_BagregarcursosMouseExited

    private void BagregarcursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BagregarcursosActionPerformed
       Recursos obj = new Recursos();
        menu.Escritorio.add(obj);
        obj.toFront();
        obj.setVisible(true);
    }//GEN-LAST:event_BagregarcursosActionPerformed

    private void GuardarCambiosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarCambiosMouseMoved

    }//GEN-LAST:event_GuardarCambiosMouseMoved

    private void GuardarCambiosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarCambiosMouseEntered
  
    }//GEN-LAST:event_GuardarCambiosMouseEntered

    private void GuardarCambiosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarCambiosMouseExited
      
    }//GEN-LAST:event_GuardarCambiosMouseExited

    private void GuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarCambiosActionPerformed
       GuardarCambios();
       
    }//GEN-LAST:event_GuardarCambiosActionPerformed

    private void j2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_j2KeyTyped
        char b = evt.getKeyChar();
        if (j2.getText().length() >= 4) {
            evt.consume();
        }
        if ((b < 'a' || b > 'z') && (b < 'A' || b > 'Z')) {
            evt.consume();
        }
    }//GEN-LAST:event_j2KeyTyped

    private void j1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_j1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_j1ActionPerformed

    private void j1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_j1KeyReleased
        HabilitarBoton();
    }//GEN-LAST:event_j1KeyReleased

    private void j1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_j1KeyTyped
        char car = evt.getKeyChar();
        if (j1.getText().length() >= 25) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_j1KeyTyped

    private void j6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_j6KeyTyped
        SoloLetras.letrasYespacio(evt, j6.getText());
    }//GEN-LAST:event_j6KeyTyped

    private void BuscarCursosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuscarCursosMouseMoved
        BuscarCursos.setToolTipText("Buscar Codigo del Curso");
    }//GEN-LAST:event_BuscarCursosMouseMoved

    private void BuscarCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarCursosActionPerformed
       BuscarCurso();
       
    }//GEN-LAST:event_BuscarCursosActionPerformed

    private void AddmodulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddmodulosActionPerformed
         Frame f=JOptionPane.getFrameForComponent(this);
        new Moduls(f,true).setVisible(true);
    }//GEN-LAST:event_AddmodulosActionPerformed

    private void AddmodulosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddmodulosMouseExited
      
    }//GEN-LAST:event_AddmodulosMouseExited

    private void AddmodulosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddmodulosMouseEntered
        
    }//GEN-LAST:event_AddmodulosMouseEntered

    private void AddmodulosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddmodulosMouseMoved

    }//GEN-LAST:event_AddmodulosMouseMoved

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscar1ActionPerformed

    private void CostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CostoActionPerformed

    private void CostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CostoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_CostoKeyReleased

    private void CostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CostoKeyTyped
        char car = evt.getKeyChar();
        char b = evt.getKeyChar();
        if (Costo.getText().length() >= 10) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_CostoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton Addmodulos;
    public static javax.swing.JButton Bactualizar;
    public static javax.swing.JButton Bagregarcursos;
    public static javax.swing.JButton BuscarCursos;
    public static javax.swing.JTextField Costo;
    public static javax.swing.JButton GuardarCambios;
    public static javax.swing.JTable Tmostrarcursos;
    public static javax.swing.JTextField buscar1;
    public static javax.swing.JTextField j1;
    public static javax.swing.JTextField j2;
    public static javax.swing.JComboBox<String> j3;
    public static javax.swing.JComboBox<String> j5;
    public static javax.swing.JTextField j6;
    public static javax.swing.JComboBox<String> j7;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JPanel panelestu;
    private javax.swing.JPanel panelsuperior2;
    private javax.swing.JPanel panelsuperior4;
    // End of variables declaration//GEN-END:variables
}
