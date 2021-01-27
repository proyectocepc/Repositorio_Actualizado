/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Conexiones.BDConexion;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia Mijares
 */
public class AperturaSeccion extends javax.swing.JInternalFrame {

    static ResultSet res;

    public void aperturarSeccion() {

    }

    /**
     * Creates new form AperturaSeccion
     */
    public AperturaSeccion() {

        initComponents();
        setSize(1243, 739);

        this.sTATUS.setVisible(false);
        this.jLabel1.setVisible(false);
        this.cambiossave.setVisible(false);
        this.Disponible.setVisible(false);
       this. jLabel10.setVisible(false);
    }

    public void guardarprofSecc() {
        try {
            String Ci, ce;
            BDConexion.EntradaProfSec(
                    Ci = Cedula.getText(),
                    ce = Seccion.getText());

        } catch (SQLException ex) {

        }
    }

    public void Validacion() {
        String mensaje = BDConexion.bucarCi(CodigoCurso.getText());
        if (mensaje.equals("El Codigo Existe")) {
            CodExiste.setText("Codigo Disponible");

            if (!Seccion.getText().isEmpty() && !Cedula.getText().isEmpty()) {
                GuardarAper.setEnabled(true);
            }
        } else if (CodigoCurso.getText().isEmpty()) {
            CodExiste.setText("");

        } else {
            CodExiste.setText("Codigo no Registrado");

            GuardarAper.setEnabled(false);
        }
    }

    public void Validacion2() {
        String mensaje = BDConexion.bucarCiProfesor(Cedula.getText());
        if (mensaje.equals("La Cedula ya Existe")) {
            PROF.setText("Cedula Profesor Existente");

            GuardarAper.setEnabled(true);
        } else if (Cedula.getText().isEmpty()) {
            PROF.setText("");

        } else {
            PROF.setText("Profesor no existe");
            GuardarAper.setEnabled(false);
        }

    }

    public void ValidacionSeccion() {
        String mensaje = BDConexion.ChequearNSeccion(Seccion.getText());
        if (mensaje.equals("Seccion ya registrada")) {
            CodSeccion.setText("Seccion ya Existe");
            Status.setEnabled(true);
            GuardarAper.setEnabled(false);

        } else if (Seccion.getText().isEmpty()) {
            CodSeccion.setText("");

        } else {
            CodSeccion.setText("Seccion Disponible");
            if (!Seccion.getText().isEmpty() && !Cedula.getText().isEmpty()) {
                GuardarAper.setEnabled(true);
            }
        }
    }

    public void GuardarApertura() {
        if (Seccion.getText().isEmpty() || Cedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "rellene todos los campos obligatorios", "informacion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                String sec, c, stat, Tur, horae, horas, He,Hs,e;
                BDConexion.EntradaSeccion(
                        sec = Seccion.getText(),
                        Tur = Turno.getText(),
                        He=  HoraE.getText(),
                         Hs=HoraS.getText(),
                         c = CodigoCurso.getText(),
                         e=sTATUS.getSelectedItem().toString());
                
                guardarprofSecc();
                Cedula.setText("");
                Turno.setText("");
                HoraE.setText("");
                HoraS.setText("");
                Seccion.setText("");
                CodigoCurso.setText("");
                CodSeccion.setText("");
                PROF.setText("");
                CodExiste.setText("");
                Status.setText("");
                GuardarAper.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Guardado Exitoso");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);

            }
        }

    }

    public void ModificarApertura() {
        if (Seccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Coloque Bien sus Datos", "Error", JOptionPane.ERROR_MESSAGE);
            Seccion.setText("");
            Seccion.requestFocus();
        } else {
            try {
                String b;
                BDConexion.BuscarSeccion(Seccion.getText());

                b = Seccion.getText();
                Turno.setText("");
                CodigoCurso.setText("");
                Cedula.setText("");
                sTATUS.setSelectedItem("");
                HoraE.setText("");
                HoraS.setText("");

                Turno.requestFocus();
                CodigoCurso.requestFocus();
                Cedula.requestFocus();
                sTATUS.requestFocus();
                HoraE.requestFocus();
                HoraS.requestFocus();

                res = Conexiones.Conexion.Consulta("select *from SECCION");
                while (res.next()) {
                    if (res.getString(1).equals(b)) {
                        JOptionPane.showMessageDialog(null, "Datos Encontrados");
                        Seccion.setText(res.getString(1));
                        Turno.setText(res.getString(2));
                        HoraE.setText(res.getString(3));
                        HoraS.setText(res.getString(4));
                        CodigoCurso.setText(res.getString(5));
                        sTATUS.setSelectedItem(res.getString(6));
                        
                    }
                }
                res = Conexiones.Conexion.Consulta("select CI,NOMBRE from PROF_IMP_SEC");
                while (res.next()) {
                    if (res.getString(2).equals(b)) {
                        Cedula.setText(res.getString(1));
                        Seccion.setText(res.getString(2));

                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        sTATUS.setEnabled(true);
        CodigoCurso.setEnabled(false);
        Cedula.setEnabled(true);
        this.cambiossave.setVisible(true);
        GuardarAper.setVisible(false);
        this.sTATUS.setVisible(true);
        this.jLabel1.setVisible(true);
            this.jLabel10.setVisible(true);
    }

    public void GuardarCambiosApertura() {
        try {
            PreparedStatement pps = Conexiones.Conexion.getConexion().prepareStatement("update SECCION set STATUS='"
                    + sTATUS.getSelectedItem()
                    + "' where NOMBRE='" + Seccion.getText() + "'");
            pps.executeUpdate();

            Seccion.setText("");
            Turno.setText("");
            CodigoCurso.setText("");
            HoraE.setText("");
            HoraS.setText("");
            Cedula.setText("");

            Seccion.requestFocus();
            Turno.requestFocus();
            CodigoCurso.requestFocus();
            Cedula.requestFocus();
            HoraE.requestFocus();
            HoraS.requestFocus();

            JOptionPane.showMessageDialog(null, "Cambios Guardados");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {
            PreparedStatement pps = Conexiones.Conexion.getConexion().prepareStatement("update PROF_IMP_SEC set CI='"
                    + Cedula.getText()
                    + "' where NOMBRE='" + Seccion.getText() + "'");
            pps.executeUpdate();

            Cedula.setText("");
            Cedula.requestFocus();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        this.sTATUS.setVisible(false);
        this.jLabel1.setVisible(false);
          this. jLabel10.setVisible(false);
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
        panelinferior1 = new javax.swing.JPanel();
        panelinferior = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        GuardarAper = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        sTATUS = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        CodigoCurso = new javax.swing.JTextField();
        Seccion = new javax.swing.JTextField();
        Cedula = new javax.swing.JTextField();
        CodExiste = new javax.swing.JTextField();
        CodSeccion = new javax.swing.JTextField();
        PROF = new javax.swing.JTextField();
        Ayuda2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        cambiossave = new javax.swing.JButton();
        Status = new javax.swing.JButton();
        Disponible = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        HoraE = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        HoraS = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Turno = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Secciones = new javax.swing.JButton();
        MostrarProfe = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(248, 247, 247));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(248, 247, 247));
        jPanel1.setLayout(null);

        panelinferior1.setBackground(new java.awt.Color(0, 153, 204));
        panelinferior1.setLayout(null);
        jPanel1.add(panelinferior1);
        panelinferior1.setBounds(0, 680, 1240, 110);

        panelinferior.setBackground(new java.awt.Color(0, 153, 204));
        panelinferior.setLayout(null);
        jPanel1.add(panelinferior);
        panelinferior.setBounds(0, 0, 1230, 40);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        GuardarAper.setBackground(new java.awt.Color(142, 252, 199));
        GuardarAper.setForeground(new java.awt.Color(51, 51, 51));
        GuardarAper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        GuardarAper.setText("Guardar");
        GuardarAper.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        GuardarAper.setEnabled(false);
        GuardarAper.setInheritsPopupMenu(true);
        GuardarAper.setMaximumSize(new java.awt.Dimension(75, 53));
        GuardarAper.setMinimumSize(new java.awt.Dimension(75, 53));
        GuardarAper.setPreferredSize(new java.awt.Dimension(75, 53));
        GuardarAper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GuardarAperMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                GuardarAperMouseExited(evt);
            }
        });
        GuardarAper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarAperActionPerformed(evt);
            }
        });
        jPanel2.add(GuardarAper);
        GuardarAper.setBounds(220, 480, 110, 60);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Status:");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(320, 360, 38, 20);

        sTATUS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disponible", "Iniciada", "Finalizada" }));
        sTATUS.setEnabled(false);
        jPanel2.add(sTATUS);
        sTATUS.setBounds(390, 360, 113, 20);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Profesor (CI):");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(140, 200, 80, 20);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Sección:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(170, 130, 48, 20);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Hora Salida:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(310, 290, 80, 20);

        CodigoCurso.setBorder(null);
        CodigoCurso.setDisabledTextColor(new java.awt.Color(0, 0, 0));
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
        jPanel2.add(CodigoCurso);
        CodigoCurso.setBounds(230, 60, 119, 20);

        Seccion.setBorder(null);
        Seccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeccionActionPerformed(evt);
            }
        });
        Seccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SeccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SeccionKeyTyped(evt);
            }
        });
        jPanel2.add(Seccion);
        Seccion.setBounds(230, 130, 119, 20);

        Cedula.setBorder(null);
        Cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CedulaActionPerformed(evt);
            }
        });
        Cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CedulaKeyTyped(evt);
            }
        });
        jPanel2.add(Cedula);
        Cedula.setBounds(230, 200, 120, 18);

        CodExiste.setEditable(false);
        CodExiste.setBackground(new java.awt.Color(255, 255, 255));
        CodExiste.setForeground(new java.awt.Color(51, 51, 51));
        CodExiste.setBorder(null);
        CodExiste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodExisteActionPerformed(evt);
            }
        });
        jPanel2.add(CodExiste);
        CodExiste.setBounds(370, 60, 143, 20);

        CodSeccion.setEditable(false);
        CodSeccion.setBackground(new java.awt.Color(255, 255, 255));
        CodSeccion.setForeground(new java.awt.Color(51, 51, 51));
        CodSeccion.setBorder(null);
        CodSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodSeccionActionPerformed(evt);
            }
        });
        jPanel2.add(CodSeccion);
        CodSeccion.setBounds(370, 130, 143, 20);

        PROF.setEditable(false);
        PROF.setBackground(new java.awt.Color(255, 255, 255));
        PROF.setForeground(new java.awt.Color(51, 51, 51));
        PROF.setBorder(null);
        jPanel2.add(PROF);
        PROF.setBounds(370, 190, 142, 20);

        Ayuda2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_help_26px.png"))); // NOI18N
        Ayuda2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Ayuda2MouseMoved(evt);
            }
        });
        Ayuda2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Ayuda2KeyTyped(evt);
            }
        });
        jPanel2.add(Ayuda2);
        Ayuda2.setBounds(430, 470, 30, 25);

        jSeparator1.setBackground(new java.awt.Color(22, 44, 81));
        jPanel2.add(jSeparator1);
        jSeparator1.setBounds(230, 80, 121, 10);

        jSeparator7.setBackground(new java.awt.Color(22, 44, 81));
        jPanel2.add(jSeparator7);
        jSeparator7.setBounds(230, 150, 120, 15);

        jSeparator8.setBackground(new java.awt.Color(22, 44, 81));
        jPanel2.add(jSeparator8);
        jSeparator8.setBounds(130, 380, 120, 10);

        cambiossave.setBackground(new java.awt.Color(142, 252, 199));
        cambiossave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        cambiossave.setText("Guardar ");
        cambiossave.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        cambiossave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cambiossave.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cambiossave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cambiossave.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                cambiossaveMouseMoved(evt);
            }
        });
        cambiossave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cambiossaveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cambiossaveMouseExited(evt);
            }
        });
        cambiossave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiossaveActionPerformed(evt);
            }
        });
        jPanel2.add(cambiossave);
        cambiossave.setBounds(220, 480, 100, 60);

        Status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_property_26px.png"))); // NOI18N
        Status.setText("Modificicar");
        Status.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Status.setEnabled(false);
        Status.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Status.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Status.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Status.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                StatusMouseMoved(evt);
            }
        });
        Status.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                StatusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                StatusMouseExited(evt);
            }
        });
        Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusActionPerformed(evt);
            }
        });
        jPanel2.add(Status);
        Status.setBounds(340, 480, 100, 60);

        Disponible.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disponible" }));
        Disponible.setEnabled(false);
        jPanel2.add(Disponible);
        Disponible.setBounds(40, 530, 80, 20);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Codigo:");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(170, 60, 43, 20);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Turno:");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(70, 360, 40, 20);

        HoraE.setBorder(null);
        HoraE.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        HoraE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                HoraEKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                HoraEKeyTyped(evt);
            }
        });
        jPanel2.add(HoraE);
        HoraE.setBounds(150, 290, 119, 20);

        jSeparator2.setBackground(new java.awt.Color(22, 44, 81));
        jPanel2.add(jSeparator2);
        jSeparator2.setBounds(150, 310, 121, 10);

        jSeparator9.setBackground(new java.awt.Color(22, 44, 81));
        jPanel2.add(jSeparator9);
        jSeparator9.setBounds(390, 310, 120, 15);

        HoraS.setBorder(null);
        HoraS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HoraSActionPerformed(evt);
            }
        });
        HoraS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                HoraSKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                HoraSKeyTyped(evt);
            }
        });
        jPanel2.add(HoraS);
        HoraS.setBounds(390, 290, 119, 20);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Hora Entrada:");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(60, 290, 80, 20);

        Turno.setBorder(null);
        Turno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TurnoActionPerformed(evt);
            }
        });
        Turno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TurnoKeyTyped(evt);
            }
        });
        jPanel2.add(Turno);
        Turno.setBounds(130, 360, 120, 20);

        jSeparator10.setBackground(new java.awt.Color(22, 44, 81));
        jPanel2.add(jSeparator10);
        jSeparator10.setBounds(230, 220, 120, 10);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_tick_box_26px.png"))); // NOI18N
        jPanel2.add(jLabel10);
        jLabel10.setBounds(290, 350, 30, 40);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_autograph_30px.png"))); // NOI18N
        jPanel2.add(jLabel11);
        jLabel11.setBounds(130, 50, 40, 40);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_course_24px.png"))); // NOI18N
        jPanel2.add(jLabel12);
        jLabel12.setBounds(140, 120, 30, 40);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_identification_documents_24px.png"))); // NOI18N
        jPanel2.add(jLabel13);
        jLabel13.setBounds(110, 190, 30, 40);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_time_26px.png"))); // NOI18N
        jPanel2.add(jLabel14);
        jLabel14.setBounds(280, 280, 30, 40);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_time_26px.png"))); // NOI18N
        jPanel2.add(jLabel15);
        jLabel15.setBounds(30, 280, 30, 40);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_sun_32px.png"))); // NOI18N
        jPanel2.add(jLabel16);
        jLabel16.setBounds(40, 350, 30, 40);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(290, 100, 610, 570);

        jLabel4.setFont(new java.awt.Font("Lao UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("APERTURA DE SECCIÓN");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(450, 60, 287, 30);

        jPanel3.setBackground(new java.awt.Color(0, 135, 180));

        Secciones.setBackground(new java.awt.Color(0, 153, 204));
        Secciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_list_30px.png"))); // NOI18N
        Secciones.setText("Secciones Registradas");
        Secciones.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Secciones.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Secciones.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Secciones.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Secciones.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                SeccionesMouseMoved(evt);
            }
        });
        Secciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SeccionesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SeccionesMouseExited(evt);
            }
        });
        Secciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeccionesActionPerformed(evt);
            }
        });

        MostrarProfe.setBackground(new java.awt.Color(0, 153, 204));
        MostrarProfe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_classroom_30px.png"))); // NOI18N
        MostrarProfe.setText("Profesores Registrados");
        MostrarProfe.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        MostrarProfe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MostrarProfe.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        MostrarProfe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MostrarProfe.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                MostrarProfeMouseMoved(evt);
            }
        });
        MostrarProfe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MostrarProfeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MostrarProfeMouseExited(evt);
            }
        });
        MostrarProfe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarProfeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MostrarProfe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Secciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Secciones)
                .addGap(18, 18, 18)
                .addComponent(MostrarProfe)
                .addContainerGap())
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 40, 150, 240);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOGOR.png"))); // NOI18N
        jPanel1.add(jLabel17);
        jLabel17.setBounds(680, 190, 650, 520);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/er.png"))); // NOI18N
        jPanel1.add(jLabel18);
        jLabel18.setBounds(0, 0, 1230, 680);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1240, 720);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SeccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SeccionKeyTyped
        char b = evt.getKeyChar();
        if (Seccion.getText().length() >= 10) {
            evt.consume();
        }
        if ((b < 'a' || b > 'z') && (b < '0' || b > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_SeccionKeyTyped

    private void CedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaKeyTyped
        char car = evt.getKeyChar();
        if (Cedula.getText().length() >= 8) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_CedulaKeyTyped

    private void CodigoCursoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodigoCursoKeyTyped
        char car = evt.getKeyChar();
        char b = evt.getKeyChar();
        if (CodigoCurso.getText().length() >= 4) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_CodigoCursoKeyTyped

    private void GuardarAperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarAperActionPerformed
        GuardarApertura();
    }//GEN-LAST:event_GuardarAperActionPerformed

    private void CodigoCursoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodigoCursoKeyReleased

        Validacion();

    }//GEN-LAST:event_CodigoCursoKeyReleased

    private void SeccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SeccionKeyReleased

        ValidacionSeccion();

    }//GEN-LAST:event_SeccionKeyReleased

    private void CedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaKeyReleased

        Validacion2();

    }//GEN-LAST:event_CedulaKeyReleased

    private void CodExisteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodExisteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodExisteActionPerformed

    private void CodSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodSeccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodSeccionActionPerformed

    private void StatusMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StatusMouseMoved

    }//GEN-LAST:event_StatusMouseMoved

    private void StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusActionPerformed
        ModificarApertura();

    }//GEN-LAST:event_StatusActionPerformed

    private void cambiossaveMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cambiossaveMouseMoved

    }//GEN-LAST:event_cambiossaveMouseMoved

    private void cambiossaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiossaveActionPerformed
        GuardarCambiosApertura();
    }//GEN-LAST:event_cambiossaveActionPerformed

    private void CedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaActionPerformed

    private void cambiossaveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cambiossaveMouseEntered

    }//GEN-LAST:event_cambiossaveMouseEntered

    private void cambiossaveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cambiossaveMouseExited

    }//GEN-LAST:event_cambiossaveMouseExited

    private void StatusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StatusMouseEntered


    }//GEN-LAST:event_StatusMouseEntered

    private void StatusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StatusMouseExited

    }//GEN-LAST:event_StatusMouseExited

    private void Ayuda2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ayuda2MouseMoved
        Ayuda2.setToolTipText("Paso 1-.Para Modificar debes Escribir el Nombre de la Sección"
        );
    }//GEN-LAST:event_Ayuda2MouseMoved

    private void Ayuda2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ayuda2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_Ayuda2KeyTyped

    private void GuardarAperMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarAperMouseExited

    }//GEN-LAST:event_GuardarAperMouseExited

    private void GuardarAperMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarAperMouseEntered

    }//GEN-LAST:event_GuardarAperMouseEntered

    private void HoraEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HoraEKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_HoraEKeyReleased

    private void HoraEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HoraEKeyTyped
        char car = evt.getKeyChar();
        if (HoraE.getText().length() >= 4) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_HoraEKeyTyped

    private void HoraSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HoraSKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_HoraSKeyReleased

    private void HoraSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HoraSKeyTyped
        char car = evt.getKeyChar();
        if (HoraS.getText().length() >= 8) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_HoraSKeyTyped

    private void Seccion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Seccion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Seccion1ActionPerformed

    private void TurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TurnoActionPerformed

    private void HoraSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HoraSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HoraSActionPerformed

    private void SeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SeccionActionPerformed

    private void TurnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TurnoKeyTyped
        char b = evt.getKeyChar();
        if (Turno.getText().length() >= 10) {
            evt.consume();
        }
        if ((b < 'a' || b > 'z')&& (b != 'ñ') && (b != 'Ñ')) {
            evt.consume();
        }
    }//GEN-LAST:event_TurnoKeyTyped

    private void MostrarProfeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarProfeActionPerformed
        Frame f = JOptionPane.getFrameForComponent(this);
        new ListaProfesores(f, true).setVisible(true);

    }//GEN-LAST:event_MostrarProfeActionPerformed

    private void MostrarProfeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MostrarProfeMouseExited

    }//GEN-LAST:event_MostrarProfeMouseExited

    private void MostrarProfeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MostrarProfeMouseEntered

    }//GEN-LAST:event_MostrarProfeMouseEntered

    private void MostrarProfeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MostrarProfeMouseMoved

    }//GEN-LAST:event_MostrarProfeMouseMoved

    private void SeccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeccionesActionPerformed
        Frame f = JOptionPane.getFrameForComponent(this);
        new Seccion(f, true).setVisible(true);
    }//GEN-LAST:event_SeccionesActionPerformed

    private void SeccionesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SeccionesMouseExited

    }//GEN-LAST:event_SeccionesMouseExited

    private void SeccionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SeccionesMouseEntered

    }//GEN-LAST:event_SeccionesMouseEntered

    private void SeccionesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SeccionesMouseMoved

    }//GEN-LAST:event_SeccionesMouseMoved

    private void CodigoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodigoCursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodigoCursoActionPerformed

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
            java.util.logging.Logger.getLogger(AperturaSeccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AperturaSeccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AperturaSeccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AperturaSeccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AperturaSeccion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Ayuda2;
    public static javax.swing.JTextField Cedula;
    public static javax.swing.JTextField CodExiste;
    public static javax.swing.JTextField CodSeccion;
    public static javax.swing.JTextField CodigoCurso;
    private javax.swing.JComboBox<String> Disponible;
    public static javax.swing.JButton GuardarAper;
    public static javax.swing.JTextField HoraE;
    public static javax.swing.JTextField HoraS;
    public static javax.swing.JButton MostrarProfe;
    public static javax.swing.JTextField PROF;
    public static javax.swing.JTextField Seccion;
    public static javax.swing.JButton Secciones;
    public static javax.swing.JButton Status;
    public javax.swing.JTextField Turno;
    public static javax.swing.JButton cambiossave;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel panelinferior;
    private javax.swing.JPanel panelinferior1;
    public static javax.swing.JComboBox<String> sTATUS;
    // End of variables declaration//GEN-END:variables
}
