/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Conexiones.BDConexion;
import Controlador.SoloLetras;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author rosan
 */
public class Repre extends javax.swing.JDialog {

    /**
     * Creates new form Repre
     */
    public Repre(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
       initComponents();
           this.setLocationRelativeTo(null);
     this.Panelyaregistrado.setVisible(false);
        
        Preinscripcion p= new Preinscripcion();
        CedulaeEstu.setText(p.Texto);
        Validacion();
        Validacion2();
    }

    public void registrarDatosRepresentante() {
        try {
            String Ced, nom, apelli, Sno = "", Sapell = "", Gen, Fecha;
            int dia, mes, año;
            dia = FECHA.getCalendar().get(Calendar.DAY_OF_MONTH);
            mes = FECHA.getCalendar().get(Calendar.MONTH);
            año = FECHA.getCalendar().get(Calendar.YEAR);
            Fecha = año + "-" + (1 + mes) + "-" + dia;

            BDConexion.EntradaPersonal(Ced = CIrepre.getText(),
                    nom = Nombrerepre.getText(),
                    apelli = Apellidorepre.getText(),
                    Sno,
                    Sapell,
                    Gen = gen.getSelectedItem().toString(),
                    Fecha);
            JOptionPane.showMessageDialog(null, "Guardado Exitoso");
         
     
            Nombrerepre.setText("");
            Apellidorepre.setText("");
            GUARDAR.setEnabled(false);

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void registrarTelefono() {
        try {
            String Cedu, Pref, Num;
            BDConexion.EntradaTelefono(
                    Pref = Prefijorepre.getSelectedItem().toString(),
                    Num = Telefonorepre.getText(),
                    Cedu = CIrepre.getText());
            Telefonorepre.setText("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void registarRepresentante() {
        try {
            String Ced;
            BDConexion.EntradaRepresentante(Ced = CIrepre.getText());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     public void registrarEstuRepre() {
        try {
            String Cedu, paren, estu;
            BDConexion.EntradaEstuPuedeTeneRepre(
                    estu = CedulaeEstu.getText(),
                    paren = Parentesco.getSelectedItem().toString(),
                    Cedu = CIrepre.getText());
            JOptionPane.showMessageDialog(null, "Guardado Exitoso");
            
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

 public void HabilitarBoton() {
        if (CIrepre.getText().length() == 8 &&!Nombrerepre.getText().isEmpty() && !Apellidorepre.getText().isEmpty() && !Telefonorepre.getText().isEmpty()) {
            GUARDAR.setEnabled(true);
        } else {
             GUARDAR.setEnabled(false);
        }
    }

    public void Validacion() {
        String mensaje = BDConexion.bucarCedula(CIrepre.getText());
        if (mensaje.equals("La Cedula ya Existe")) {
            CodExiste.setText("Cedula Registrada");
              Nombrerepre.setEditable(false);
          Apellidorepre.setEditable(false);
          Telefonorepre.setEditable(false);
           gen.setEnabled(false);
           FECHA.setEnabled(false);
            Prefijorepre.setEnabled(false);
             this.Parentesco.setEnabled(true);
        this.CedulaeEstu.setEditable(true);
           
            
        } else if (CIrepre.getText().isEmpty()) {
            CodExiste.setText("");

        } else {
            CodExiste.setText("Cedula no Registrada");
            
           
             Nombrerepre.setEditable(true);
          Apellidorepre.setEditable(true);
          Telefonorepre.setEditable(true);
           gen.setEnabled(true);
           FECHA.setEnabled(true);
            Prefijorepre.setEnabled(true);
             this.Parentesco.setEnabled(false);
        this.CedulaeEstu.setEditable(false);
        
        
        }
    }
public void Validacion2() {
     String mensaje=BDConexion.bucarCedulaestu(CedulaeEstu.getText());
     if(mensaje.equals("La Cedula no Existe")){
         CodExisteEstu.setText("Cedula no registrada");  
         GUARDARC.setEnabled(false);
     }else if(CedulaeEstu.getText().isEmpty()){
     CodExisteEstu.setText("");
         
     }else{
        CodExisteEstu.setText("Cedula estudiante"); 
        GUARDARC.setEnabled(true);
               
             }
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
        jLabel3 = new javax.swing.JLabel();
        CIrepre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        CodExiste = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        yaregistrados = new javax.swing.JButton();
        Obligatorios3 = new javax.swing.JLabel();
        Panelregistro = new javax.swing.JPanel();
        j1 = new javax.swing.JLabel();
        Obligatorios5 = new javax.swing.JLabel();
        Nombrerepre = new javax.swing.JTextField();
        s2 = new javax.swing.JSeparator();
        Obligatorios4 = new javax.swing.JLabel();
        j2 = new javax.swing.JLabel();
        Apellidorepre = new javax.swing.JTextField();
        s3 = new javax.swing.JSeparator();
        Obligatorios6 = new javax.swing.JLabel();
        j4 = new javax.swing.JLabel();
        FECHA = new com.toedter.calendar.JDateChooser();
        j3 = new javax.swing.JLabel();
        gen = new javax.swing.JComboBox<>();
        Obligatorios7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Prefijorepre = new javax.swing.JComboBox<>();
        Telefonorepre = new javax.swing.JTextField();
        s1 = new javax.swing.JSeparator();
        GUARDAR = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        Panelyaregistrado = new javax.swing.JPanel();
        Parentesco = new javax.swing.JComboBox<>();
        J8 = new javax.swing.JLabel();
        J9 = new javax.swing.JLabel();
        CedulaeEstu = new javax.swing.JTextField();
        S7 = new javax.swing.JSeparator();
        CodExisteEstu = new javax.swing.JTextField();
        GUARDARC = new javax.swing.JButton();
        Volver = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 206, 209));
        jPanel1.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_family_32px.png"))); // NOI18N
        jLabel3.setText("Representante");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(110, 30, 190, 59);

        CIrepre.setBackground(new java.awt.Color(204, 206, 209));
        CIrepre.setBorder(null);
        CIrepre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CIrepreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CIrepreKeyTyped(evt);
            }
        });
        jPanel1.add(CIrepre);
        CIrepre.setBounds(135, 133, 104, 14);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("CI");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(110, 133, 20, 17);

        CodExiste.setEditable(false);
        CodExiste.setBackground(new java.awt.Color(204, 206, 209));
        CodExiste.setBorder(null);
        CodExiste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodExisteActionPerformed(evt);
            }
        });
        jPanel1.add(CodExiste);
        CodExiste.setBounds(257, 133, 87, 14);

        jSeparator9.setBackground(new java.awt.Color(0, 0, 102));
        jSeparator9.setForeground(new java.awt.Color(0, 51, 102));
        jPanel1.add(jSeparator9);
        jSeparator9.setBounds(134, 155, 106, 14);

        yaregistrados.setBackground(new java.awt.Color(0, 102, 153));
        yaregistrados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_32px.png"))); // NOI18N
        yaregistrados.setText("Ya Registrado");
        yaregistrados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        yaregistrados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yaregistradosActionPerformed(evt);
            }
        });
        jPanel1.add(yaregistrados);
        yaregistrados.setBounds(210, 570, 140, 40);

        Obligatorios3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Obligatorios3.setForeground(new java.awt.Color(204, 0, 51));
        Obligatorios3.setText("*");
        Obligatorios3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Obligatorios3MouseMoved(evt);
            }
        });
        jPanel1.add(Obligatorios3);
        Obligatorios3.setBounds(90, 133, 17, 22);

        Panelregistro.setBackground(new java.awt.Color(204, 206, 209));
        Panelregistro.setLayout(null);

        j1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        j1.setText("Nombre");
        Panelregistro.add(j1);
        j1.setBounds(90, 20, 46, 17);

        Obligatorios5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Obligatorios5.setForeground(new java.awt.Color(204, 0, 51));
        Obligatorios5.setText("*");
        Obligatorios5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Obligatorios5MouseMoved(evt);
            }
        });
        Panelregistro.add(Obligatorios5);
        Obligatorios5.setBounds(70, 10, 17, 30);

        Nombrerepre.setEditable(false);
        Nombrerepre.setBackground(new java.awt.Color(204, 206, 209));
        Nombrerepre.setBorder(null);
        Nombrerepre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombrerepreActionPerformed(evt);
            }
        });
        Nombrerepre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NombrerepreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NombrerepreKeyTyped(evt);
            }
        });
        Panelregistro.add(Nombrerepre);
        Nombrerepre.setBounds(140, 20, 106, 20);

        s2.setBackground(new java.awt.Color(0, 0, 102));
        s2.setForeground(new java.awt.Color(0, 51, 102));
        Panelregistro.add(s2);
        s2.setBounds(140, 40, 106, 13);

        Obligatorios4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Obligatorios4.setForeground(new java.awt.Color(204, 0, 51));
        Obligatorios4.setText("*");
        Obligatorios4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Obligatorios4MouseMoved(evt);
            }
        });
        Panelregistro.add(Obligatorios4);
        Obligatorios4.setBounds(70, 70, 17, 20);

        j2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        j2.setText("Apellido");
        Panelregistro.add(j2);
        j2.setBounds(90, 70, 45, 17);

        Apellidorepre.setEditable(false);
        Apellidorepre.setBackground(new java.awt.Color(204, 206, 209));
        Apellidorepre.setBorder(null);
        Apellidorepre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApellidorepreActionPerformed(evt);
            }
        });
        Apellidorepre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ApellidorepreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ApellidorepreKeyTyped(evt);
            }
        });
        Panelregistro.add(Apellidorepre);
        Apellidorepre.setBounds(140, 70, 106, 20);

        s3.setBackground(new java.awt.Color(0, 0, 102));
        s3.setForeground(new java.awt.Color(0, 51, 102));
        Panelregistro.add(s3);
        s3.setBounds(140, 90, 110, 11);

        Obligatorios6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Obligatorios6.setForeground(new java.awt.Color(204, 0, 51));
        Obligatorios6.setText("*");
        Obligatorios6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Obligatorios6MouseMoved(evt);
            }
        });
        Panelregistro.add(Obligatorios6);
        Obligatorios6.setBounds(210, 130, 20, 20);

        j4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        j4.setText("Fecha");
        Panelregistro.add(j4);
        j4.setBounds(50, 130, 34, 17);

        FECHA.setBackground(new java.awt.Color(255, 255, 255));
        FECHA.setEnabled(false);
        FECHA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FECHAKeyReleased(evt);
            }
        });
        Panelregistro.add(FECHA);
        FECHA.setBounds(90, 130, 120, 20);

        j3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        j3.setText("Genero");
        Panelregistro.add(j3);
        j3.setBounds(230, 130, 41, 17);

        gen.setBackground(new java.awt.Color(22, 44, 81));
        gen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Femenino", "Masculino" }));
        gen.setBorder(null);
        gen.setEnabled(false);
        Panelregistro.add(gen);
        gen.setBounds(280, 130, 69, 18);

        Obligatorios7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Obligatorios7.setForeground(new java.awt.Color(204, 0, 51));
        Obligatorios7.setText("*");
        Obligatorios7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Obligatorios7MouseMoved(evt);
            }
        });
        Panelregistro.add(Obligatorios7);
        Obligatorios7.setBounds(50, 200, 17, 20);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Telèfono");
        Panelregistro.add(jLabel9);
        jLabel9.setBounds(60, 200, 50, 20);

        Prefijorepre.setBackground(new java.awt.Color(22, 44, 81));
        Prefijorepre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0414", "0424", "0412", "0416", "0426" }));
        Prefijorepre.setEnabled(false);
        Prefijorepre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrefijorepreActionPerformed(evt);
            }
        });
        Panelregistro.add(Prefijorepre);
        Prefijorepre.setBounds(110, 200, 77, 20);

        Telefonorepre.setEditable(false);
        Telefonorepre.setBackground(new java.awt.Color(204, 206, 209));
        Telefonorepre.setBorder(null);
        Telefonorepre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelefonorepreActionPerformed(evt);
            }
        });
        Telefonorepre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TelefonorepreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TelefonorepreKeyTyped(evt);
            }
        });
        Panelregistro.add(Telefonorepre);
        Telefonorepre.setBounds(200, 200, 57, 20);

        s1.setBackground(new java.awt.Color(0, 0, 102));
        s1.setForeground(new java.awt.Color(0, 51, 102));
        Panelregistro.add(s1);
        s1.setBounds(200, 220, 57, 2);

        GUARDAR.setBackground(new java.awt.Color(142, 252, 199));
        GUARDAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        GUARDAR.setText("Guardar");
        GUARDAR.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        GUARDAR.setEnabled(false);
        GUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUARDARActionPerformed(evt);
            }
        });
        Panelregistro.add(GUARDAR);
        GUARDAR.setBounds(100, 330, 120, 50);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_name_26px.png"))); // NOI18N
        Panelregistro.add(jLabel21);
        jLabel21.setBounds(40, 60, 26, 26);

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_iphone_x_32px.png"))); // NOI18N
        Panelregistro.add(jLabel26);
        jLabel26.setBounds(10, 190, 32, 32);

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_name_26px.png"))); // NOI18N
        Panelregistro.add(jLabel23);
        jLabel23.setBounds(40, 10, 26, 26);

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/e.png"))); // NOI18N
        Panelregistro.add(jLabel22);
        jLabel22.setBounds(10, 120, 32, 32);

        jPanel1.add(Panelregistro);
        Panelregistro.setBounds(10, 150, 360, 410);

        Panelyaregistrado.setBackground(new java.awt.Color(204, 206, 209));

        Parentesco.setBackground(new java.awt.Color(22, 44, 81));
        Parentesco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Mama", "Papa", "Abuel@", "Herman@", "Ti@" }));
        Parentesco.setEnabled(false);
        Parentesco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ParentescoActionPerformed(evt);
            }
        });

        J8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        J8.setText("Parentesco");

        J9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        J9.setText("CI Estudiante:");

        CedulaeEstu.setEditable(false);
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

        S7.setBackground(new java.awt.Color(0, 0, 102));
        S7.setForeground(new java.awt.Color(0, 51, 102));

        CodExisteEstu.setEditable(false);
        CodExisteEstu.setBackground(new java.awt.Color(204, 206, 209));
        CodExisteEstu.setBorder(null);
        CodExisteEstu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodExisteEstuActionPerformed(evt);
            }
        });

        GUARDARC.setBackground(new java.awt.Color(142, 252, 199));
        GUARDARC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        GUARDARC.setText("Guardar");
        GUARDARC.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        GUARDARC.setEnabled(false);
        GUARDARC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUARDARCActionPerformed(evt);
            }
        });

        Volver.setForeground(new java.awt.Color(204, 204, 204));
        Volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_back_arrow_48px.png"))); // NOI18N
        Volver.setBorder(null);
        Volver.setContentAreaFilled(false);
        Volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolverActionPerformed(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_identification_documents_24px.png"))); // NOI18N

        javax.swing.GroupLayout PanelyaregistradoLayout = new javax.swing.GroupLayout(Panelyaregistrado);
        Panelyaregistrado.setLayout(PanelyaregistradoLayout);
        PanelyaregistradoLayout.setHorizontalGroup(
            PanelyaregistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelyaregistradoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(GUARDARC, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
            .addGroup(PanelyaregistradoLayout.createSequentialGroup()
                .addGroup(PanelyaregistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelyaregistradoLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelyaregistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelyaregistradoLayout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(S7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelyaregistradoLayout.createSequentialGroup()
                                .addComponent(J9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(CedulaeEstu, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CodExisteEstu, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PanelyaregistradoLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(J8)
                        .addGap(18, 18, 18)
                        .addComponent(Parentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelyaregistradoLayout.setVerticalGroup(
            PanelyaregistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelyaregistradoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelyaregistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(J8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelyaregistradoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(Parentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(PanelyaregistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelyaregistradoLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(PanelyaregistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(J9)
                            .addGroup(PanelyaregistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(CedulaeEstu, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CodExisteEstu, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(S7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelyaregistradoLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(PanelyaregistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GUARDARC, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jPanel1.add(Panelyaregistrado);
        Panelyaregistrado.setBounds(20, 180, 300, 260);

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_identification_documents_24px.png"))); // NOI18N
        jPanel1.add(jLabel25);
        jLabel25.setBounds(60, 130, 24, 24);

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_identification_documents_24px.png"))); // NOI18N
        jPanel1.add(jLabel27);
        jLabel27.setBounds(60, 130, 24, 24);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolverActionPerformed
              
        this.Panelyaregistrado.setVisible(false);
         this.Panelregistro.setVisible(true);
         
    }//GEN-LAST:event_VolverActionPerformed

    private void CIrepreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CIrepreKeyReleased
        HabilitarBoton();
        Validacion();
    }//GEN-LAST:event_CIrepreKeyReleased

    private void CIrepreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CIrepreKeyTyped
        char car = evt.getKeyChar();
        if (CIrepre.getText().length() >= 8) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_CIrepreKeyTyped

    private void NombrerepreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombrerepreKeyReleased
       HabilitarBoton();
    }//GEN-LAST:event_NombrerepreKeyReleased

    private void NombrerepreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombrerepreKeyTyped
        SoloLetras.letrasYespacio(evt, Nombrerepre.getText());
    }//GEN-LAST:event_NombrerepreKeyTyped

    private void ApellidorepreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApellidorepreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApellidorepreActionPerformed

    private void ApellidorepreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ApellidorepreKeyReleased
       HabilitarBoton();
    }//GEN-LAST:event_ApellidorepreKeyReleased

    private void ApellidorepreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ApellidorepreKeyTyped
        SoloLetras.letrasYespacio(evt, Apellidorepre.getText());
    }//GEN-LAST:event_ApellidorepreKeyTyped

    private void PrefijorepreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrefijorepreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrefijorepreActionPerformed

    private void TelefonorepreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TelefonorepreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TelefonorepreActionPerformed

    private void TelefonorepreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TelefonorepreKeyTyped
        char car = evt.getKeyChar();
        if (Telefonorepre.getText().length() >= 7) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_TelefonorepreKeyTyped

    private void GUARDARCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GUARDARCActionPerformed
      
        registrarEstuRepre();
    }//GEN-LAST:event_GUARDARCActionPerformed

    private void GUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GUARDARActionPerformed
        /*para guardar los datos que se introduzcan en los campo

        */
        if (CIrepre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "rellene todos los campos obligatorios", "informacion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            registrarDatosRepresentante();
            registrarTelefono();

        }

    }//GEN-LAST:event_GUARDARActionPerformed

    private void CodExisteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodExisteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodExisteActionPerformed

    private void yaregistradosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yaregistradosActionPerformed
     
                this.Panelyaregistrado.setVisible(true);
                  this.Panelregistro.setVisible(false);

    }//GEN-LAST:event_yaregistradosActionPerformed

    private void CedulaeEstuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaeEstuActionPerformed
      
    }//GEN-LAST:event_CedulaeEstuActionPerformed

    private void CedulaeEstuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaeEstuKeyReleased
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

    private void CodExisteEstuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodExisteEstuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodExisteEstuActionPerformed

    private void Obligatorios3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obligatorios3MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Obligatorios3MouseMoved

    private void Obligatorios4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obligatorios4MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Obligatorios4MouseMoved

    private void Obligatorios5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obligatorios5MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Obligatorios5MouseMoved

    private void Obligatorios6MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obligatorios6MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Obligatorios6MouseMoved

    private void Obligatorios7MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obligatorios7MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Obligatorios7MouseMoved

    private void TelefonorepreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TelefonorepreKeyReleased
        HabilitarBoton();
    }//GEN-LAST:event_TelefonorepreKeyReleased

    private void FECHAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FECHAKeyReleased
        
    }//GEN-LAST:event_FECHAKeyReleased

    private void NombrerepreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombrerepreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombrerepreActionPerformed

    private void ParentescoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ParentescoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ParentescoActionPerformed

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
            java.util.logging.Logger.getLogger(Repre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Repre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Repre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Repre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Repre dialog = new Repre(new javax.swing.JFrame(), true);
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
    public static javax.swing.JTextField Apellidorepre;
    public static javax.swing.JTextField CIrepre;
    public static javax.swing.JTextField CedulaeEstu;
    public static javax.swing.JTextField CodExiste;
    private javax.swing.JTextField CodExisteEstu;
    public static com.toedter.calendar.JDateChooser FECHA;
    public static javax.swing.JButton GUARDAR;
    public static javax.swing.JButton GUARDARC;
    private javax.swing.JLabel J8;
    private javax.swing.JLabel J9;
    public static javax.swing.JTextField Nombrerepre;
    private javax.swing.JLabel Obligatorios3;
    private javax.swing.JLabel Obligatorios4;
    private javax.swing.JLabel Obligatorios5;
    private javax.swing.JLabel Obligatorios6;
    private javax.swing.JLabel Obligatorios7;
    private javax.swing.JPanel Panelregistro;
    private javax.swing.JPanel Panelyaregistrado;
    public static javax.swing.JComboBox<String> Parentesco;
    public static javax.swing.JComboBox<String> Prefijorepre;
    private javax.swing.JSeparator S7;
    public static javax.swing.JTextField Telefonorepre;
    public static javax.swing.JButton Volver;
    public static javax.swing.JComboBox<String> gen;
    private javax.swing.JLabel j1;
    private javax.swing.JLabel j2;
    private javax.swing.JLabel j3;
    private javax.swing.JLabel j4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JSeparator s1;
    private javax.swing.JSeparator s2;
    private javax.swing.JSeparator s3;
    public static javax.swing.JButton yaregistrados;
    // End of variables declaration//GEN-END:variables
}
