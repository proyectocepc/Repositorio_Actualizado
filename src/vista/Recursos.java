/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Conexiones.BDConexion;
import Controlador.SoloLetras;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import vista.MostrarCurso;
import vista.menu;

/**
 *
 * @author rosan
 */
public class Recursos extends javax.swing.JInternalFrame {
  static ResultSet res;

    ImageIcon verde = new ImageIcon(getClass().getResource("/img/si.png"));
    ImageIcon rojo = new ImageIcon(getClass().getResource("/img/delete.png"));
    
    
    public Recursos() {
        initComponents();
         
        setSize(1243, 739);
       
        
        this.Disponible.setVisible(false);
        this.jPanel1.setVisible(false);
        fecha.setText(fechaActual());
        
    }
     public void HabilitarBoton() {
        if (CodigoCurso.getText().length() == 4 && !Cname.getText().isEmpty()) {
            saveButb.setEnabled(true);
        } else {
            saveButb.setEnabled(false);
        }
    }

    public void Validacion() {
        String mensaje = BDConexion.bucarCi(CodigoCurso.getText());
        if (mensaje.equals("El Codigo Existe")) {
            CodExiste.setText("Codigo Registrado");
            CodExiste1.setText("Codigo Registrado");
            Cname.setEditable(false);
            
            Pasantia.setEnabled(false);
            MaterialC.setEditable(false);
            Tcursos.setEnabled(false);
            SaveModulo.setEnabled(true);
            modulo.setEditable(true);
            saveButb.setEnabled(false);
            Costo.setEnabled(false);

        } else if (CodigoCurso.getText().isEmpty()) {
            CodExiste.setText("");

        } else {
            CodExiste.setText("Codigo no Registrado");

            Cname.setEditable(true);
            
            Pasantia.setEnabled(true);
            MaterialC.setEditable(true);
             Costo.setEditable(true);
            Tcursos.setEnabled(true);
            if (!Cname.getText().isEmpty() ) {
                saveButb.setEnabled(true);
            }

        }

        if (CodigoCurso.getText().length() <= 3) {
            qqq.setText("caracteres faltantes");
            jcheck.setIcon(rojo);
        } else if (CodigoCurso.getText().isEmpty()) {

            jcheck.setIcon(null);
            qqq.setText("");

        } else {
            qqq.setText("caracteres necesarios");
            jcheck.setIcon(verde);

        }
    }

    public static String fechaActual() {

        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");

        return formatoFecha.format(fecha);

    }
    public void GuardarCurso(){
        if (Cname.getText().isEmpty() ||  CodigoCurso.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "rellene todos los campos obligatorios", "informacion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                String Ccod, Cnom, Tcur, Ccos, Pasan, Materia, dis,cos;
                BDConexion.EntradaCurso(Ccod = CodigoCurso.getText(),
                    Cnom = Cname.getText(),
                    Tcur = Tcursos.getSelectedItem().toString(),
                    Pasan = Pasantia.getSelectedItem().toString(),
                    Materia = MaterialC.getText(),
                    dis = Disponible.getSelectedItem().toString(),
                      cos = Costo.getText()
                        );

                JOptionPane.showMessageDialog(null, "Guardado Exitoso");
                Cname.setText("");
                Costo.setText("");
                
                MaterialC.setText("");
                
             } catch (SQLException ex) {
                  JOptionPane.showMessageDialog(null, ex);
            }

        }
        this.jPanel1.setVisible(true);
        int posicion = jPanel1.getX();
        if (posicion > -1) {

        } else {
            Animacion.Animacion.mover_derecha(-264, 0, 2, 2, jPanel1);
        }
        Addmodulos.setEnabled(true);
        Texto=CodigoCurso.getText();
        DD.setText(Texto);
        Validacion();
    }
    public void GuardarModulo(){
          if (CodigoCurso.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "rellene todos los campos obligatorios", "informacion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                String cod, mod;
                BDConexion.EntradaModulo(
                    cod = CodigoCurso.getText().toString(),
                    mod = modulo.getText());
                JOptionPane.showMessageDialog(null, "Guardado Exitoso");
                modulo.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }
    }
    
    public static String Texto = "";

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelinferior = new javax.swing.JPanel();
        panelsuperior = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        fecha = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        panelregistrocurso = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Cname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        saveButb = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        MaterialC = new javax.swing.JTextField();
        CodigoCurso = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        Addmodulos = new javax.swing.JButton();
        Tcursos = new javax.swing.JComboBox<>();
        Pasantia = new javax.swing.JComboBox<>();
        Obligatorios = new javax.swing.JLabel();
        Obligatorios1 = new javax.swing.JLabel();
        Ayuda = new javax.swing.JLabel();
        jcheck = new javax.swing.JLabel();
        qqq = new javax.swing.JTextField();
        CodExiste = new javax.swing.JTextField();
        Disponible = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        Mcursos = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Costo = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        modulo = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        SaveModulo = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        CodExiste1 = new javax.swing.JTextField();
        DD = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Registro de Curso");
        getContentPane().setLayout(null);

        panelinferior.setBackground(new java.awt.Color(0, 153, 204));

        javax.swing.GroupLayout panelinferiorLayout = new javax.swing.GroupLayout(panelinferior);
        panelinferior.setLayout(panelinferiorLayout);
        panelinferiorLayout.setHorizontalGroup(
            panelinferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1260, Short.MAX_VALUE)
        );
        panelinferiorLayout.setVerticalGroup(
            panelinferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(panelinferior);
        panelinferior.setBounds(-10, 670, 1260, 50);

        panelsuperior.setBackground(new java.awt.Color(0, 153, 204));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha:");

        fecha.setEditable(false);
        fecha.setBackground(new java.awt.Color(0, 153, 204));
        fecha.setForeground(new java.awt.Color(255, 255, 255));
        fecha.setBorder(null);
        fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActionPerformed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_calendar_32px.png"))); // NOI18N

        javax.swing.GroupLayout panelsuperiorLayout = new javax.swing.GroupLayout(panelsuperior);
        panelsuperior.setLayout(panelsuperiorLayout);
        panelsuperiorLayout.setHorizontalGroup(
            panelsuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelsuperiorLayout.createSequentialGroup()
                .addContainerGap(934, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
        );
        panelsuperiorLayout.setVerticalGroup(
            panelsuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelsuperiorLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(panelsuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(panelsuperior);
        panelsuperior.setBounds(0, 0, 1240, 40);

        jPanel2.setBackground(new java.awt.Color(248, 247, 247));
        jPanel2.setLayout(null);

        panelregistrocurso.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Nombre Curso");

        jSeparator1.setBackground(new java.awt.Color(22, 44, 81));

        Cname.setEditable(false);
        Cname.setBackground(new java.awt.Color(255, 255, 255));
        Cname.setBorder(null);
        Cname.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Cname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CnameActionPerformed(evt);
            }
        });
        Cname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CnameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CnameKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Tipo de Curso");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Material");

        saveButb.setBackground(new java.awt.Color(142, 252, 199));
        saveButb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        saveButb.setText("Guardar");
        saveButb.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        saveButb.setEnabled(false);
        saveButb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButb.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        saveButb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveButb.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                saveButbMouseMoved(evt);
            }
        });
        saveButb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveButbMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveButbMouseExited(evt);
            }
        });
        saveButb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButbActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Pasantìa");

        MaterialC.setEditable(false);
        MaterialC.setBackground(new java.awt.Color(255, 255, 255));
        MaterialC.setBorder(null);
        MaterialC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MaterialCKeyTyped(evt);
            }
        });

        CodigoCurso.setBorder(null);
        CodigoCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodigoCursoActionPerformed(evt);
            }
        });
        CodigoCurso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CodigoCursoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CodigoCursoKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Código");

        jSeparator6.setBackground(new java.awt.Color(22, 44, 81));

        jSeparator7.setBackground(new java.awt.Color(22, 44, 81));

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

        Tcursos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Especial", "Tecnico" }));
        Tcursos.setBorder(null);
        Tcursos.setEnabled(false);

        Pasantia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No ", "Si" }));
        Pasantia.setBorder(null);
        Pasantia.setEnabled(false);
        Pasantia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasantiaActionPerformed(evt);
            }
        });

        Obligatorios.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Obligatorios.setForeground(new java.awt.Color(204, 0, 51));
        Obligatorios.setText("*");
        Obligatorios.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ObligatoriosMouseMoved(evt);
            }
        });

        Obligatorios1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Obligatorios1.setForeground(new java.awt.Color(204, 0, 51));
        Obligatorios1.setText("*");
        Obligatorios1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Obligatorios1MouseMoved(evt);
            }
        });

        Ayuda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Ayuda.setForeground(new java.awt.Color(255, 0, 51));
        Ayuda.setText("?");
        Ayuda.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                AyudaMouseMoved(evt);
            }
        });

        qqq.setEditable(false);
        qqq.setBackground(new java.awt.Color(255, 255, 255));
        qqq.setBorder(null);

        CodExiste.setEditable(false);
        CodExiste.setBackground(new java.awt.Color(255, 255, 255));
        CodExiste.setBorder(null);
        CodExiste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodExisteActionPerformed(evt);
            }
        });

        Disponible.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disponible" }));
        Disponible.setEnabled(false);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_autograph_30px.png"))); // NOI18N

        Mcursos.setBackground(new java.awt.Color(252, 106, 106));
        Mcursos.setFont(new java.awt.Font("Lao UI", 0, 14)); // NOI18N
        Mcursos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_list_26px_1.png"))); // NOI18N
        Mcursos.setText("Cursos Registrados");
        Mcursos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Mcursos.setContentAreaFilled(false);
        Mcursos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Mcursos.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Mcursos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Mcursos.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                McursosMouseMoved(evt);
            }
        });
        Mcursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                McursosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                McursosMouseExited(evt);
            }
        });
        Mcursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                McursosActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_tick_box_26px.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_deployment_32px.png"))); // NOI18N

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_course_24px.png"))); // NOI18N

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_course_24px.png"))); // NOI18N

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setText("Costo:");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_paper_money_32px.png"))); // NOI18N

        Costo.setEditable(false);
        Costo.setBackground(new java.awt.Color(255, 255, 255));
        Costo.setBorder(null);
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

        javax.swing.GroupLayout panelregistrocursoLayout = new javax.swing.GroupLayout(panelregistrocurso);
        panelregistrocurso.setLayout(panelregistrocursoLayout);
        panelregistrocursoLayout.setHorizontalGroup(
            panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelregistrocursoLayout.createSequentialGroup()
                .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelregistrocursoLayout.createSequentialGroup()
                        .addGap(610, 610, 610)
                        .addComponent(qqq, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelregistrocursoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                .addGap(216, 216, 216)
                                .addComponent(Disponible, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                    .addComponent(Obligatorios1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel16)
                                    .addGap(2, 2, 2)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(Tcursos, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                    .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Mcursos)
                                        .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                            .addGap(37, 37, 37)
                                            .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                                    .addComponent(jLabel6)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel8)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(Pasantia, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                                    .addComponent(jLabel20)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel19)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                                        .addComponent(Costo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                            .addGap(74, 74, 74)
                                            .addComponent(Addmodulos, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(saveButb, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                            .addGap(98, 98, 98)
                                            .addComponent(jLabel14)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(MaterialC, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(Ayuda, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(panelregistrocursoLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(Cname, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Obligatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addGap(26, 26, 26)
                                .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(CodigoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcheck, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CodExiste, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelregistrocursoLayout.setVerticalGroup(
            panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelregistrocursoLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qqq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelregistrocursoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(CodigoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Obligatorios))
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jcheck, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                                    .addComponent(CodExiste))))))
                .addGap(29, 29, 29)
                .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelregistrocursoLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(Cname, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelregistrocursoLayout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(MaterialC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Ayuda)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelregistrocursoLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(jLabel14)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelregistrocursoLayout.createSequentialGroup()
                        .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(Pasantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(Tcursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))
                                        .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                            .addGap(2, 2, 2)
                                            .addComponent(Obligatorios1)))
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)))
                        .addGap(34, 34, 34)
                        .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addGroup(panelregistrocursoLayout.createSequentialGroup()
                                        .addComponent(Costo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelregistrocursoLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(Disponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addGroup(panelregistrocursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Addmodulos, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(saveButb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(panelregistrocursoLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(Mcursos, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.add(panelregistrocurso);
        panelregistrocurso.setBounds(446, 11, 575, 599);

        jPanel1.setBackground(new java.awt.Color(0, 153, 204));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Código");

        jLabel10.setBackground(new java.awt.Color(204, 204, 204));
        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 51));
        jLabel10.setText("Contenido");

        modulo.setBackground(new java.awt.Color(0, 153, 204));
        modulo.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        modulo.setForeground(new java.awt.Color(255, 255, 255));
        modulo.setBorder(null);
        modulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                moduloKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                moduloKeyTyped(evt);
            }
        });

        jSeparator3.setBackground(new java.awt.Color(255, 204, 102));
        jSeparator3.setForeground(new java.awt.Color(255, 204, 102));

        jSeparator4.setBackground(new java.awt.Color(255, 204, 102));
        jSeparator4.setForeground(new java.awt.Color(255, 204, 102));

        SaveModulo.setBackground(new java.awt.Color(142, 252, 199));
        SaveModulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        SaveModulo.setText("Guardar");
        SaveModulo.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        SaveModulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SaveModuloMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SaveModuloMouseExited(evt);
            }
        });
        SaveModulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveModuloActionPerformed(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_shortlist_64px.png"))); // NOI18N

        CodExiste1.setEditable(false);
        CodExiste1.setBackground(new java.awt.Color(0, 153, 204));
        CodExiste1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        CodExiste1.setForeground(new java.awt.Color(255, 255, 255));
        CodExiste1.setBorder(null);

        DD.setBackground(new java.awt.Color(0, 153, 204));
        DD.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        DD.setForeground(new java.awt.Color(255, 255, 255));
        DD.setBorder(null);
        DD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DDKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Modulos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10)
                    .addComponent(modulo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(DD)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CodExiste1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(SaveModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CodExiste1, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(DD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(modulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(SaveModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1);
        jPanel1.setBounds(0, 0, 280, 640);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOGOR.png"))); // NOI18N
        jPanel2.add(jLabel17);
        jLabel17.setBounds(680, 190, 650, 520);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/er.png"))); // NOI18N
        jPanel2.add(jLabel18);
        jLabel18.setBounds(0, 0, 1230, 630);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 40, 1230, 630);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void moduloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_moduloKeyReleased
      
    }//GEN-LAST:event_moduloKeyReleased

    private void moduloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_moduloKeyTyped
        SoloLetras.letrasYespacio(evt, modulo.getText());
    }//GEN-LAST:event_moduloKeyTyped

    private void SaveModuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveModuloActionPerformed

      GuardarModulo();
    }//GEN-LAST:event_SaveModuloActionPerformed

    private void fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaActionPerformed

    private void McursosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_McursosMouseMoved

    }//GEN-LAST:event_McursosMouseMoved

    private void McursosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_McursosMouseEntered
        
    }//GEN-LAST:event_McursosMouseEntered

    private void McursosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_McursosMouseExited
    
    }//GEN-LAST:event_McursosMouseExited

    private void McursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_McursosActionPerformed
        MostrarCurso obj = new MostrarCurso();
        menu.Escritorio.add(obj);
        obj.toFront();
        obj.setVisible(true);
    }//GEN-LAST:event_McursosActionPerformed

    private void CnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CnameActionPerformed

    private void CnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CnameKeyReleased
        HabilitarBoton();
    }//GEN-LAST:event_CnameKeyReleased

    private void CnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CnameKeyTyped
        SoloLetras.letrasYespacio(evt, Cname.getText());
    }//GEN-LAST:event_CnameKeyTyped

    private void saveButbMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButbMouseMoved

    }//GEN-LAST:event_saveButbMouseMoved

    private void saveButbMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButbMouseEntered

    }//GEN-LAST:event_saveButbMouseEntered

    private void saveButbMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButbMouseExited
    
    }//GEN-LAST:event_saveButbMouseExited

    private void saveButbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButbActionPerformed

        GuardarCurso();

    }//GEN-LAST:event_saveButbActionPerformed

    private void MaterialCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MaterialCKeyTyped
        SoloLetras.letrasYespacio(evt, MaterialC.getText());
    }//GEN-LAST:event_MaterialCKeyTyped

    private void CodigoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodigoCursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodigoCursoActionPerformed

    private void CodigoCursoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodigoCursoKeyReleased
        HabilitarBoton();
        Validacion();
    }//GEN-LAST:event_CodigoCursoKeyReleased

    private void CodigoCursoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodigoCursoKeyTyped
        char car = evt.getKeyChar();
        char b = evt.getKeyChar();
        if (CodigoCurso.getText().length() >= 9) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_CodigoCursoKeyTyped

    private void AddmodulosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddmodulosMouseMoved

    }//GEN-LAST:event_AddmodulosMouseMoved

    private void AddmodulosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddmodulosMouseEntered
       
    }//GEN-LAST:event_AddmodulosMouseEntered

    private void AddmodulosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddmodulosMouseExited
  
    }//GEN-LAST:event_AddmodulosMouseExited

    private void AddmodulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddmodulosActionPerformed
        int posicion = jPanel1.getX();
        if (posicion > -1) {
            Animacion.Animacion.mover_izquierda(0, -284, 2, 2, jPanel1);
        }else {
            Animacion.Animacion.mover_derecha(-264, 0, 2, 2, jPanel1);
        }
    }//GEN-LAST:event_AddmodulosActionPerformed

    private void ObligatoriosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ObligatoriosMouseMoved
        Obligatorios.setToolTipText("Campo Obligatorio");
    }//GEN-LAST:event_ObligatoriosMouseMoved

    private void Obligatorios1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obligatorios1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Obligatorios1MouseMoved

    private void AyudaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AyudaMouseMoved
        Ayuda.setToolTipText("Ejm: Guías, Copias etc. ");
    }//GEN-LAST:event_AyudaMouseMoved

    private void DDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DDKeyTyped
       char car = evt.getKeyChar();
        char b = evt.getKeyChar();
        if (DD.getText().length() >= 4) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_DDKeyTyped

    private void SaveModuloMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveModuloMouseEntered
        
    }//GEN-LAST:event_SaveModuloMouseEntered

    private void SaveModuloMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveModuloMouseExited
         
    }//GEN-LAST:event_SaveModuloMouseExited

    private void PasantiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasantiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasantiaActionPerformed

    private void CodExisteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodExisteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodExisteActionPerformed

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
    private javax.swing.JLabel Ayuda;
    public static javax.swing.JTextField Cname;
    public static javax.swing.JTextField CodExiste;
    public static javax.swing.JTextField CodExiste1;
    public static javax.swing.JTextField CodigoCurso;
    public static javax.swing.JTextField Costo;
    public static javax.swing.JTextField DD;
    public static javax.swing.JComboBox<String> Disponible;
    public static javax.swing.JTextField MaterialC;
    public static javax.swing.JButton Mcursos;
    private javax.swing.JLabel Obligatorios;
    private javax.swing.JLabel Obligatorios1;
    public static javax.swing.JComboBox<String> Pasantia;
    public static javax.swing.JButton SaveModulo;
    public static javax.swing.JComboBox<String> Tcursos;
    public static javax.swing.JTextField fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    public static javax.swing.JLabel jcheck;
    public static javax.swing.JTextField modulo;
    private javax.swing.JPanel panelinferior;
    private javax.swing.JPanel panelregistrocurso;
    private javax.swing.JPanel panelsuperior;
    public static javax.swing.JTextField qqq;
    public static javax.swing.JButton saveButb;
    // End of variables declaration//GEN-END:variables
}
