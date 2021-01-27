package vista;

import Conexiones.BDConexion;
import Conexiones.Conexion;
import Controlador.SoloLetras;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.getFrameForComponent;

/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Familia Mijares
 */
public class empleados extends javax.swing.JInternalFrame{

    static ResultSet res;
    ImageIcon verde = new ImageIcon(getClass().getResource("/img/si.png"));
    ImageIcon rojo = new ImageIcon(getClass().getResource("/img/delete.png"));
    Calendar calendario = new GregorianCalendar();

    /**
     * Creates new form empleados
     */
    public empleados() {
         
        initComponents();
        
        setSize(1243, 739);
        
        fecha.setText(fechaActual());
        this.Bmodificarempleado.setVisible(false);
   
      
        this.PanelProf.setVisible(false);
        this.jPanel3.setVisible(false);
        

        
    }
     public void Validacionprof() {
        String mensaje = BDConexion.bucarCedulaE(Cedulaprof.getText());
        if (mensaje.equals("La Cedula ya Existe")) {
            CodExisteprof.setText("Cedula empleado");
            
            Especialidadprof.setEditable(true);
            EstudiosRprof.setEditable(true);
            if (Cedulaprof.getText().length() == 8) {
            guardarprof.setEnabled(true);
        }
            
        } else if (Cedulaprof.getText().isEmpty()) {
            CodExisteprof.setText("");

        } else {
            CodExisteprof.setText("Cedula no Registrada");
              Especialidadprof.setEditable(false);
            EstudiosRprof.setEditable(false);
            guardarprof.setEnabled(false);

        }
    }



    public void Validacion() {
        String mensaje = BDConexion.bucarCedula(Cedula.getText());
        if (mensaje.equals("La Cedula ya Existe")) {
            CodExiste.setText("Cedula Empleado ya Registrada");

            Pnombre.setEditable(false);
            Snombre.setEditable(false);
            Papellido.setEditable(false);
            Sapellido.setEditable(false);
            GeneroCB.setEnabled(false);
            Ciudad.setEditable(false);
            Sector.setEditable(false);
            Calle.setEditable(false);
            Casa.setEditable(false);
            CodPOS.setEditable(false);
            Correo.setEditable(false);
            PrefijoCorreo.setEnabled(false);
            Prefijo.setEnabled(false);
            Numero.setEditable(false);
            Cargo.setEnabled(false);
            jDateChooser1.setEnabled(false);
            Bsaveempleados.setEnabled(false);
          

        } else if (Cedula.getText().isEmpty()) {
            CodExiste.setText("");

        } else {
            CodExiste.setText("Cedula no Registrada");
            if (Cedula.getText().length() == 8) {
                Pnombre.setEditable(true);
                Snombre.setEditable(true);
                Papellido.setEditable(true);
                Sapellido.setEditable(true);
                GeneroCB.setEnabled(true);
                Ciudad.setEditable(true);
                Sector.setEditable(true);
                Calle.setEditable(true);
                Casa.setEditable(true);
                CodPOS.setEditable(true);
                Correo.setEditable(true);
                PrefijoCorreo.setEnabled(true);
                Prefijo.setEnabled(true);
                Numero.setEditable(true);
                Cargo.setEnabled(true);
                jDateChooser1.setEnabled(true);
              

                if (!Pnombre.getText().isEmpty() && !Papellido.getText().isEmpty()) {
                    Bsaveempleados.setEnabled(true);
                }
            }
        }

        if (Cedula.getText().length() <= 7) {
            qqq.setText("caracteres faltantes");
            jcheck.setIcon(rojo);
        } else if (Cedula.getText().isEmpty()) {

            jcheck.setIcon(null);
            qqq.setText("");

        } else {
            qqq.setText("caracteres necesarios");
            jcheck.setIcon(verde);

        }
    }

    public void HabilitarProf() {

        String i = Cargo.getSelectedItem().toString();
         
        if (i.equals("Profesor") ) {
            DatosProfesor.setBackground(Color.green);
            DatosProfesor.setEnabled(true);
            this.PanelProf.setVisible(true);
            DatosAdm.setEnabled(false);
            DatosAdm.setBackground(Color.WHITE);
        }
    }

    public void HabilitarAdm() {
        String i = Cargo.getSelectedItem().toString();
        
        if (i.equals("Administrativo")  ) {
            DatosAdm.setBackground(Color.green);
            DatosAdm.setEnabled(true);
             this.jPanel3.setVisible(true);
            DatosProfesor.setEnabled(false);
            DatosProfesor.setBackground(Color.WHITE);
            
        }
    }
    
    

    public static String fechaActual() {

        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY/MM/dd");

        return formatoFecha.format(fecha);

    }

   
     public void Validacionadm() {
        String mensaje = BDConexion.bucarCedulaE(Cedulaadm2.getText());
        if (mensaje.equals("La Cedula ya Existe")) {
            CodExisteadm2.setText("Cedula empleado");
          
            EspecialidadAdm2.setEditable(true);
           
            saveadm2.setEnabled(true);
            
        } else if (Cedulaadm2.getText().isEmpty()) {
            CodExisteadm2.setText("");

        } else {
            CodExisteadm2.setText("Cedula no Registrada");
            
            EspecialidadAdm2.setEditable(false);
            saveadm2.setEnabled(false);
        }
    }


    
    public void GuardarDatosEmpleados(){
        if (Cedula.getText().isEmpty() || Pnombre.getText().isEmpty() || Papellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "rellene todos los campos obligatorios", "informacion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                String Cedu, Pnom, Snom, Papelli, Sapelli, Gene, Fecha;
                int dia, mes, año;
                dia = jDateChooser1.getCalendar().get(Calendar.DAY_OF_MONTH);
                mes = jDateChooser1.getCalendar().get(Calendar.MONTH);
                año = jDateChooser1.getCalendar().get(Calendar.YEAR);
                Fecha = año + "-" + (1 + mes) + "-" + dia;

                Date fn;
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                BDConexion.EntradaPersonal(Cedu = Cedula.getText(),
                        Pnom = Pnombre.getText(),
                        Papelli = Papellido.getText(),
                        Snom = Snombre.getText(),
                        Sapelli = Sapellido.getText(),
                        Gene = GeneroCB.getSelectedItem().toString(),
                        Fecha);

                if (Cedula.getText().length() < 8) {
                    JOptionPane.showMessageDialog(null, "Debe Colocar la Cedula Correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Guardado Exitoso");
                }
               
               Texto=Cedula.getText();
                Validacion();
                HabilitarAdm();
                HabilitarProf();
                Pnombre.setText("");
                Papellido.setText("");
                Snombre.setText("");
                Sapellido.setText("");
                
                jDateChooser1.setCalendar(null);
                
                 
              
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }
        try {
            String Cedu, ciu, Sect, Call, Ncasa, Codp;
            BDConexion.EntradaDireccion(
                    ciu = Ciudad.getText(),
                    Sect = Sector.getText(),
                    Ncasa = Casa.getText(),
                    Codp = CodPOS.getText(),
                    Cedu = Cedula.getText(),
                    Call = Calle.getText());

            Ciudad.setText("");
            Sector.setText("");
            Casa.setText("");
            CodPOS.setText("");
            Calle.setText("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {
            String Cedu, Pref, Num;
            BDConexion.EntradaTelefono(
                    Pref = Prefijo.getSelectedItem().toString(),
                    Num = Numero.getText(),
                    Cedu = Cedula.getText());

            Numero.setText("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {
            String Cedu, PrefC, Corre;
            BDConexion.EntradaCorreo(
                    PrefC = PrefijoCorreo.getSelectedItem().toString(),
                    Corre = Correo.getText(),
                    Cedu = Cedula.getText());

            Correo.setText("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        try {
            String Cedu, Carg, Fi;

            BDConexion.EntradaEmpleado(
                    Fi = fecha.getText(),
                    Carg = Cargo.getSelectedItem().toString(),
                    Cedu = Cedula.getText());
            
        Cedulaprof.setText(Texto);
       
        Validacionprof();
        
        
        Cedulaadm2.setText(Texto);
       
        Validacionadm();
        

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
         
        Bsaveempleados.setEnabled(false);
    }
    
    public void GuardarDatosProf(){
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
        Especialidadprof.setEnabled(true);
        EstudiosRprof.setEnabled(true);
    }
    public void GuardarDatosAdmin(){
         if (Cedulaadm2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "rellene todos los campos obligatorios", "informacion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                String Ced, Espec;
                BDConexion.EntradaAdministrativo(
                      Espec = EspecialidadAdm2.getText(),
                    Ced = Cedulaadm2.getText()
                    
                );
                EspecialidadAdm2.setText("");

                JOptionPane.showMessageDialog(null, "Guardado Exitoso");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

        }

        EspecialidadAdm2.setEnabled(true);
        Texto2 = Cedulaadm2.getText();
    }
    public static String Texto = "";
    public static String Texto2 = "";
   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelsuperior = new javax.swing.JPanel();
        panelinferior = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        fecha = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        panelempleado = new javax.swing.JPanel();
        Pnombre = new javax.swing.JTextField();
        Snombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Papellido = new javax.swing.JTextField();
        Sapellido = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        Bsaveempleados = new javax.swing.JButton();
        Numero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        Cedula = new javax.swing.JTextField();
        Sector = new javax.swing.JTextField();
        Casa = new javax.swing.JTextField();
        CodPOS = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        Cargo = new javax.swing.JComboBox<>();
        GeneroCB = new javax.swing.JComboBox<>();
        Obligatorios1 = new javax.swing.JLabel();
        Obligatorios2 = new javax.swing.JLabel();
        Obligatorios3 = new javax.swing.JLabel();
        PrefijoCorreo = new javax.swing.JComboBox<>();
        Prefijo = new javax.swing.JComboBox<>();
        Ciudad = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        Correo = new javax.swing.JTextField();
        Calle = new javax.swing.JTextField();
        jcheck = new javax.swing.JLabel();
        CodExiste = new javax.swing.JTextField();
        qqq = new javax.swing.JTextField();
        Obligatorios4 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        EspecialidadAdm2 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        Cedulaadm2 = new javax.swing.JTextField();
        CodExisteadm2 = new javax.swing.JTextField();
        saveadm2 = new javax.swing.JButton();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        PanelProf = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Especialidadprof = new javax.swing.JTextField();
        EstudiosRprof = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        Cedulaprof = new javax.swing.JTextField();
        CodExisteprof = new javax.swing.JTextField();
        guardarprof = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        DatosProfesor = new javax.swing.JButton();
        DatosAdm = new javax.swing.JButton();
        Bmodificarempleado = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Registro Datos Personales Empleados");
        getContentPane().setLayout(null);

        panelsuperior.setBackground(new java.awt.Color(0, 153, 204));
        panelsuperior.setEnabled(false);

        javax.swing.GroupLayout panelsuperiorLayout = new javax.swing.GroupLayout(panelsuperior);
        panelsuperior.setLayout(panelsuperiorLayout);
        panelsuperiorLayout.setHorizontalGroup(
            panelsuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1250, Short.MAX_VALUE)
        );
        panelsuperiorLayout.setVerticalGroup(
            panelsuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(panelsuperior);
        panelsuperior.setBounds(0, 680, 1250, 50);

        panelinferior.setBackground(new java.awt.Color(0, 153, 204));
        panelinferior.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Fecha:");
        panelinferior.add(jLabel8);
        jLabel8.setBounds(1049, 25, 40, 14);

        fecha.setEditable(false);
        fecha.setBackground(new java.awt.Color(0, 153, 204));
        fecha.setForeground(new java.awt.Color(0, 255, 255));
        fecha.setBorder(null);
        fecha.setDisabledTextColor(new java.awt.Color(0, 255, 255));
        fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActionPerformed(evt);
            }
        });
        panelinferior.add(fecha);
        fecha.setBounds(1093, 25, 79, 17);

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_calendar_32px.png"))); // NOI18N
        panelinferior.add(jLabel33);
        jLabel33.setBounds(1007, 11, 36, 42);

        getContentPane().add(panelinferior);
        panelinferior.setBounds(-1, -2, 1240, 60);

        panelempleado.setBackground(new java.awt.Color(255, 255, 255));
        panelempleado.setLayout(null);

        Pnombre.setEditable(false);
        Pnombre.setBackground(new java.awt.Color(255, 255, 255));
        Pnombre.setBorder(null);
        Pnombre.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Pnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PnombreActionPerformed(evt);
            }
        });
        Pnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PnombreKeyTyped(evt);
            }
        });
        panelempleado.add(Pnombre);
        Pnombre.setBounds(130, 150, 120, 14);

        Snombre.setEditable(false);
        Snombre.setBackground(new java.awt.Color(255, 255, 255));
        Snombre.setBorder(null);
        Snombre.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Snombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SnombreActionPerformed(evt);
            }
        });
        Snombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SnombreKeyTyped(evt);
            }
        });
        panelempleado.add(Snombre);
        Snombre.setBounds(420, 150, 120, 14);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Genero");
        panelempleado.add(jLabel2);
        jLabel2.setBounds(350, 280, 41, 30);

        jSeparator2.setBackground(new java.awt.Color(22, 44, 81));
        panelempleado.add(jSeparator2);
        jSeparator2.setBounds(130, 170, 120, 8);

        jSeparator3.setBackground(new java.awt.Color(22, 44, 81));
        panelempleado.add(jSeparator3);
        jSeparator3.setBounds(420, 170, 120, 14);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText(" Nombre");
        panelempleado.add(jLabel4);
        jLabel4.setBounds(70, 150, 60, 17);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Segundo Nombre");
        panelempleado.add(jLabel5);
        jLabel5.setBounds(310, 150, 99, 17);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText(" Apellido");
        panelempleado.add(jLabel6);
        jLabel6.setBounds(70, 220, 60, 17);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Segundo Apellido");
        panelempleado.add(jLabel7);
        jLabel7.setBounds(310, 220, 98, 17);

        Papellido.setEditable(false);
        Papellido.setBackground(new java.awt.Color(255, 255, 255));
        Papellido.setBorder(null);
        Papellido.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Papellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PapellidoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PapellidoKeyTyped(evt);
            }
        });
        panelempleado.add(Papellido);
        Papellido.setBounds(130, 220, 120, 14);

        Sapellido.setEditable(false);
        Sapellido.setBackground(new java.awt.Color(255, 255, 255));
        Sapellido.setBorder(null);
        Sapellido.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Sapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SapellidoActionPerformed(evt);
            }
        });
        Sapellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SapellidoKeyTyped(evt);
            }
        });
        panelempleado.add(Sapellido);
        Sapellido.setBounds(420, 220, 120, 14);

        jSeparator4.setBackground(new java.awt.Color(22, 44, 81));
        panelempleado.add(jSeparator4);
        jSeparator4.setBounds(420, 240, 120, 8);

        jSeparator5.setBackground(new java.awt.Color(22, 44, 81));
        panelempleado.add(jSeparator5);
        jSeparator5.setBounds(130, 240, 120, 10);

        jSeparator6.setBackground(new java.awt.Color(22, 44, 81));
        panelempleado.add(jSeparator6);
        jSeparator6.setBounds(360, 370, 85, 14);

        Bsaveempleados.setBackground(new java.awt.Color(142, 252, 199));
        Bsaveempleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        Bsaveempleados.setText("Guardar");
        Bsaveempleados.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Bsaveempleados.setEnabled(false);
        Bsaveempleados.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BsaveempleadosMouseMoved(evt);
            }
        });
        Bsaveempleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BsaveempleadosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BsaveempleadosMouseExited(evt);
            }
        });
        Bsaveempleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BsaveempleadosActionPerformed(evt);
            }
        });
        panelempleado.add(Bsaveempleados);
        Bsaveempleados.setBounds(480, 540, 110, 51);

        Numero.setEditable(false);
        Numero.setBackground(new java.awt.Color(255, 255, 255));
        Numero.setBorder(null);
        Numero.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroActionPerformed(evt);
            }
        });
        Numero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NumeroKeyTyped(evt);
            }
        });
        panelempleado.add(Numero);
        Numero.setBounds(175, 350, 80, 20);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Telèfono");
        panelempleado.add(jLabel9);
        jLabel9.setBounds(60, 350, 60, 17);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Fecha de Nacimiento");
        panelempleado.add(jLabel10);
        jLabel10.setBounds(70, 290, 119, 20);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Correo");
        panelempleado.add(jLabel3);
        jLabel3.setBounds(310, 350, 39, 17);

        jSeparator7.setBackground(new java.awt.Color(22, 44, 81));
        panelempleado.add(jSeparator7);
        jSeparator7.setBounds(175, 370, 80, 11);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("CI");
        panelempleado.add(jLabel12);
        jLabel12.setBounds(230, 30, 14, 17);

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
        panelempleado.add(Cedula);
        Cedula.setBounds(260, 30, 101, 14);

        Sector.setEditable(false);
        Sector.setBackground(new java.awt.Color(255, 255, 255));
        Sector.setBorder(null);
        Sector.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Sector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SectorActionPerformed(evt);
            }
        });
        Sector.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SectorKeyTyped(evt);
            }
        });
        panelempleado.add(Sector);
        Sector.setBounds(90, 540, 80, 14);

        Casa.setEditable(false);
        Casa.setBackground(new java.awt.Color(255, 255, 255));
        Casa.setBorder(null);
        Casa.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Casa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CasaActionPerformed(evt);
            }
        });
        Casa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CasaKeyTyped(evt);
            }
        });
        panelempleado.add(Casa);
        Casa.setBounds(380, 540, 50, 14);

        CodPOS.setEditable(false);
        CodPOS.setBackground(new java.awt.Color(255, 255, 255));
        CodPOS.setBorder(null);
        CodPOS.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        CodPOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodPOSActionPerformed(evt);
            }
        });
        CodPOS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CodPOSKeyTyped(evt);
            }
        });
        panelempleado.add(CodPOS);
        CodPOS.setBounds(310, 470, 70, 14);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Sector");
        panelempleado.add(jLabel11);
        jLabel11.setBounds(40, 540, 37, 17);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Calle");
        panelempleado.add(jLabel14);
        jLabel14.setBounds(200, 540, 27, 17);

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("N° Casa");
        panelempleado.add(jLabel15);
        jLabel15.setBounds(330, 540, 47, 17);

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("Código Postal");
        panelempleado.add(jLabel16);
        jLabel16.setBounds(228, 470, 80, 17);

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("Cargo");
        panelempleado.add(jLabel17);
        jLabel17.setBounds(240, 90, 34, 17);

        jSeparator8.setBackground(new java.awt.Color(22, 44, 81));
        panelempleado.add(jSeparator8);
        jSeparator8.setBounds(260, 50, 107, 14);

        Cargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Profesor", "Administrativo" }));
        Cargo.setBorder(null);
        Cargo.setEnabled(false);
        Cargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargoActionPerformed(evt);
            }
        });
        Cargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CargoKeyReleased(evt);
            }
        });
        panelempleado.add(Cargo);
        Cargo.setBounds(280, 90, 80, 18);

        GeneroCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Femenino", "Masculino" }));
        GeneroCB.setBorder(null);
        GeneroCB.setEnabled(false);
        panelempleado.add(GeneroCB);
        GeneroCB.setBounds(400, 280, 80, 30);

        Obligatorios1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Obligatorios1.setForeground(new java.awt.Color(255, 0, 0));
        Obligatorios1.setText("*");
        Obligatorios1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Obligatorios1MouseMoved(evt);
            }
        });
        panelempleado.add(Obligatorios1);
        Obligatorios1.setBounds(250, 220, 20, 20);

        Obligatorios2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Obligatorios2.setForeground(new java.awt.Color(255, 0, 0));
        Obligatorios2.setText("*");
        Obligatorios2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Obligatorios2MouseMoved(evt);
            }
        });
        panelempleado.add(Obligatorios2);
        Obligatorios2.setBounds(250, 150, 20, 22);

        Obligatorios3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Obligatorios3.setForeground(new java.awt.Color(255, 0, 0));
        Obligatorios3.setText("*");
        Obligatorios3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Obligatorios3MouseMoved(evt);
            }
        });
        panelempleado.add(Obligatorios3);
        Obligatorios3.setBounds(200, 30, 17, 22);

        PrefijoCorreo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "@gmail.com", "@hotmail.com", "@yahoo.com" }));
        PrefijoCorreo.setBorder(null);
        PrefijoCorreo.setEnabled(false);
        panelempleado.add(PrefijoCorreo);
        PrefijoCorreo.setBounds(450, 350, 90, 18);

        Prefijo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0414", "0424", "0412", "0416", "0426", "0283" }));
        Prefijo.setBorder(null);
        Prefijo.setEnabled(false);
        Prefijo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrefijoActionPerformed(evt);
            }
        });
        panelempleado.add(Prefijo);
        Prefijo.setBounds(110, 350, 60, 20);

        Ciudad.setEditable(false);
        Ciudad.setBackground(new java.awt.Color(255, 255, 255));
        Ciudad.setBorder(null);
        Ciudad.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Ciudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CiudadKeyTyped(evt);
            }
        });
        panelempleado.add(Ciudad);
        Ciudad.setBounds(100, 470, 80, 14);

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("Ciudad");
        panelempleado.add(jLabel18);
        jLabel18.setBounds(50, 470, 39, 17);

        Correo.setEditable(false);
        Correo.setBackground(new java.awt.Color(255, 255, 255));
        Correo.setBorder(null);
        Correo.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CorreoKeyTyped(evt);
            }
        });
        panelempleado.add(Correo);
        Correo.setBounds(360, 350, 85, 20);

        Calle.setEditable(false);
        Calle.setBackground(new java.awt.Color(255, 255, 255));
        Calle.setBorder(null);
        Calle.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Calle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CalleKeyTyped(evt);
            }
        });
        panelempleado.add(Calle);
        Calle.setBounds(230, 540, 60, 14);
        panelempleado.add(jcheck);
        jcheck.setBounds(283, 60, 23, 0);

        CodExiste.setEditable(false);
        CodExiste.setBackground(new java.awt.Color(255, 255, 255));
        CodExiste.setBorder(null);
        CodExiste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodExisteActionPerformed(evt);
            }
        });
        panelempleado.add(CodExiste);
        CodExiste.setBounds(400, 40, 131, 14);

        qqq.setEditable(false);
        qqq.setBackground(new java.awt.Color(255, 255, 255));
        qqq.setBorder(null);
        qqq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qqqActionPerformed(evt);
            }
        });
        panelempleado.add(qqq);
        qqq.setBounds(400, 20, 131, 14);

        Obligatorios4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Obligatorios4.setForeground(new java.awt.Color(255, 0, 0));
        Obligatorios4.setText("*");
        Obligatorios4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Obligatorios4MouseMoved(evt);
            }
        });
        panelempleado.add(Obligatorios4);
        Obligatorios4.setBounds(310, 290, 30, 22);

        jDateChooser1.setEnabled(false);
        panelempleado.add(jDateChooser1);
        jDateChooser1.setBounds(190, 280, 110, 30);

        jSeparator12.setBackground(new java.awt.Color(22, 44, 81));
        panelempleado.add(jSeparator12);
        jSeparator12.setBounds(310, 490, 70, 10);

        jSeparator13.setBackground(new java.awt.Color(22, 44, 81));
        panelempleado.add(jSeparator13);
        jSeparator13.setBounds(100, 490, 80, 10);

        jSeparator14.setBackground(new java.awt.Color(22, 44, 81));
        panelempleado.add(jSeparator14);
        jSeparator14.setBounds(90, 560, 80, 10);

        jSeparator15.setBackground(new java.awt.Color(22, 44, 81));
        panelempleado.add(jSeparator15);
        jSeparator15.setBounds(230, 560, 60, 10);

        jSeparator18.setBackground(new java.awt.Color(22, 44, 81));
        panelempleado.add(jSeparator18);
        jSeparator18.setBounds(380, 560, 50, 10);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_name_26px.png"))); // NOI18N
        panelempleado.add(jLabel21);
        jLabel21.setBounds(30, 140, 30, 30);

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_identification_documents_24px.png"))); // NOI18N
        panelempleado.add(jLabel24);
        jLabel24.setBounds(170, 30, 30, 20);

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_email_24px.png"))); // NOI18N
        panelempleado.add(jLabel25);
        jLabel25.setBounds(280, 350, 30, 20);

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_iphone_x_32px.png"))); // NOI18N
        panelempleado.add(jLabel26);
        jLabel26.setBounds(30, 340, 30, 30);

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_home_24px.png"))); // NOI18N
        panelempleado.add(jLabel27);
        jLabel27.setBounds(300, 530, 30, 30);

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_name_26px.png"))); // NOI18N
        panelempleado.add(jLabel28);
        jLabel28.setBounds(280, 210, 30, 30);

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_name_26px.png"))); // NOI18N
        panelempleado.add(jLabel29);
        jLabel29.setBounds(30, 210, 30, 30);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel13.setText("Dirección");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap())
        );

        panelempleado.add(jPanel1);
        jPanel1.setBounds(40, 400, 540, 50);

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/e.png"))); // NOI18N
        panelempleado.add(jLabel22);
        jLabel22.setBounds(30, 280, 36, 40);

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_name_26px.png"))); // NOI18N
        panelempleado.add(jLabel34);
        jLabel34.setBounds(280, 140, 30, 30);

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_city_30px.png"))); // NOI18N
        panelempleado.add(jLabel35);
        jLabel35.setBounds(20, 460, 30, 30);

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_postcode_30px.png"))); // NOI18N
        panelempleado.add(jLabel37);
        jLabel37.setBounds(190, 460, 30, 40);

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_google_maps_32px.png"))); // NOI18N
        panelempleado.add(jLabel38);
        jLabel38.setBounds(10, 530, 32, 32);

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_streets_24px.png"))); // NOI18N
        panelempleado.add(jLabel36);
        jLabel36.setBounds(174, 534, 30, 30);

        getContentPane().add(panelempleado);
        panelempleado.setBounds(440, 60, 600, 610);

        jPanel3.setBackground(new java.awt.Color(0, 153, 204));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dsadasd.png"))); // NOI18N
        jLabel30.setText("Administrador");

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Especialidad");

        EspecialidadAdm2.setEditable(false);
        EspecialidadAdm2.setBackground(new java.awt.Color(0, 153, 204));
        EspecialidadAdm2.setForeground(new java.awt.Color(255, 255, 255));
        EspecialidadAdm2.setBorder(null);
        EspecialidadAdm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EspecialidadAdm2ActionPerformed(evt);
            }
        });
        EspecialidadAdm2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EspecialidadAdm2KeyTyped(evt);
            }
        });

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Cédula");

        Cedulaadm2.setBackground(new java.awt.Color(0, 153, 204));
        Cedulaadm2.setForeground(new java.awt.Color(255, 255, 255));
        Cedulaadm2.setBorder(null);
        Cedulaadm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cedulaadm2ActionPerformed(evt);
            }
        });
        Cedulaadm2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Cedulaadm2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Cedulaadm2KeyTyped(evt);
            }
        });

        CodExisteadm2.setEditable(false);
        CodExisteadm2.setBackground(new java.awt.Color(0, 153, 204));
        CodExisteadm2.setForeground(new java.awt.Color(255, 255, 255));
        CodExisteadm2.setBorder(null);

        saveadm2.setBackground(new java.awt.Color(142, 252, 199));
        saveadm2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        saveadm2.setText("Guardar");
        saveadm2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        saveadm2.setEnabled(false);
        saveadm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveadm2ActionPerformed(evt);
            }
        });

        jSeparator16.setBackground(new java.awt.Color(255, 255, 51));
        jSeparator16.setForeground(new java.awt.Color(255, 255, 51));

        jSeparator17.setBackground(new java.awt.Color(255, 255, 51));
        jSeparator17.setForeground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel30))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel32))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(Cedulaadm2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(CodExisteadm2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel31))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(EspecialidadAdm2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(saveadm2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel30)
                .addGap(46, 46, 46)
                .addComponent(jLabel32)
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cedulaadm2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CodExisteadm2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel31)
                .addGap(3, 3, 3)
                .addComponent(EspecialidadAdm2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(saveadm2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 50, 330, 580);

        PanelProf.setBackground(new java.awt.Color(0, 153, 204));
        PanelProf.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Especialidad");
        PanelProf.add(jLabel1);
        jLabel1.setBounds(54, 235, 73, 15);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Estudios Realizados");
        PanelProf.add(jLabel19);
        jLabel19.setBounds(53, 310, 120, 15);

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/aaaa.png"))); // NOI18N
        jLabel20.setText("Profersor");
        PanelProf.add(jLabel20);
        jLabel20.setBounds(53, 57, 150, 52);

        Especialidadprof.setEditable(false);
        Especialidadprof.setBackground(new java.awt.Color(0, 153, 204));
        Especialidadprof.setForeground(new java.awt.Color(255, 255, 255));
        Especialidadprof.setBorder(null);
        Especialidadprof.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Especialidadprof.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EspecialidadprofKeyTyped(evt);
            }
        });
        PanelProf.add(Especialidadprof);
        Especialidadprof.setBounds(54, 254, 169, 21);

        EstudiosRprof.setEditable(false);
        EstudiosRprof.setBackground(new java.awt.Color(0, 153, 204));
        EstudiosRprof.setForeground(new java.awt.Color(255, 255, 255));
        EstudiosRprof.setBorder(null);
        EstudiosRprof.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        EstudiosRprof.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EstudiosRprofKeyTyped(evt);
            }
        });
        PanelProf.add(EstudiosRprof);
        EstudiosRprof.setBounds(53, 336, 169, 20);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Cedula");
        PanelProf.add(jLabel23);
        jLabel23.setBounds(53, 142, 41, 15);

        Cedulaprof.setBackground(new java.awt.Color(0, 153, 204));
        Cedulaprof.setForeground(new java.awt.Color(255, 255, 255));
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
        PanelProf.add(Cedulaprof);
        Cedulaprof.setBounds(76, 163, 103, 22);

        CodExisteprof.setEditable(false);
        CodExisteprof.setBackground(new java.awt.Color(0, 153, 204));
        CodExisteprof.setForeground(new java.awt.Color(255, 255, 255));
        CodExisteprof.setBorder(null);
        PanelProf.add(CodExisteprof);
        CodExisteprof.setBounds(192, 163, 95, 22);

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
        PanelProf.add(guardarprof);
        guardarprof.setBounds(53, 486, 126, 59);

        jSeparator9.setBackground(new java.awt.Color(255, 204, 102));
        jSeparator9.setForeground(new java.awt.Color(255, 204, 102));
        PanelProf.add(jSeparator9);
        jSeparator9.setBounds(53, 191, 103, 13);

        jSeparator10.setBackground(new java.awt.Color(255, 204, 102));
        jSeparator10.setForeground(new java.awt.Color(255, 204, 102));
        PanelProf.add(jSeparator10);
        jSeparator10.setBounds(53, 362, 164, 12);

        jSeparator11.setBackground(new java.awt.Color(255, 204, 102));
        jSeparator11.setForeground(new java.awt.Color(255, 204, 102));
        PanelProf.add(jSeparator11);
        jSeparator11.setBounds(53, 286, 161, 18);

        getContentPane().add(PanelProf);
        PanelProf.setBounds(0, 50, 330, 620);

        jPanel4.setBackground(new java.awt.Color(0, 135, 180));
        jPanel4.setLayout(null);

        DatosProfesor.setBackground(new java.awt.Color(0, 153, 204));
        DatosProfesor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_classroom_30px.png"))); // NOI18N
        DatosProfesor.setText("Profesor");
        DatosProfesor.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        DatosProfesor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DatosProfesor.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        DatosProfesor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DatosProfesor.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                DatosProfesorMouseMoved(evt);
            }
        });
        DatosProfesor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DatosProfesorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DatosProfesorMouseExited(evt);
            }
        });
        DatosProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DatosProfesorActionPerformed(evt);
            }
        });
        jPanel4.add(DatosProfesor);
        DatosProfesor.setBounds(10, 50, 140, 60);

        DatosAdm.setBackground(new java.awt.Color(0, 153, 204));
        DatosAdm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_microsoft_admin_30px.png"))); // NOI18N
        DatosAdm.setText("Administrador");
        DatosAdm.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        DatosAdm.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DatosAdm.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        DatosAdm.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DatosAdm.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                DatosAdmMouseMoved(evt);
            }
        });
        DatosAdm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DatosAdmMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DatosAdmMouseExited(evt);
            }
        });
        DatosAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DatosAdmActionPerformed(evt);
            }
        });
        jPanel4.add(DatosAdm);
        DatosAdm.setBounds(10, 120, 140, 60);

        Bmodificarempleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_property_26px.png"))); // NOI18N
        Bmodificarempleado.setText("Modificar");
        Bmodificarempleado.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Bmodificarempleado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Bmodificarempleado.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Bmodificarempleado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Bmodificarempleado.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BmodificarempleadoMouseMoved(evt);
            }
        });
        Bmodificarempleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BmodificarempleadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BmodificarempleadoMouseExited(evt);
            }
        });
        Bmodificarempleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BmodificarempleadoActionPerformed(evt);
            }
        });
        jPanel4.add(Bmodificarempleado);
        Bmodificarempleado.setBounds(10, 220, 140, 60);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(1080, 50, 180, 340);

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOGOR.png"))); // NOI18N
        getContentPane().add(jLabel40);
        jLabel40.setBounds(670, 250, 650, 520);

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/er.png"))); // NOI18N
        getContentPane().add(jLabel39);
        jLabel39.setBounds(0, 0, 1230, 680);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BmodificarempleadoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BmodificarempleadoMouseMoved

    }//GEN-LAST:event_BmodificarempleadoMouseMoved

    private void BmodificarempleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BmodificarempleadoActionPerformed
     
    }//GEN-LAST:event_BmodificarempleadoActionPerformed

    private void CalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CalleKeyTyped
        Character s = evt.getKeyChar();
        char b = evt.getKeyChar();
        if (Calle.getText().length() >= 20) {
            evt.consume();
        }
        if ((b < 'a' || b > 'z') && (b < '0' || b > '9') && (b < 'A' || b > 'Z')&& (s != 'ñ') && (s != 'Ñ') && (s != KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_CalleKeyTyped

    private void CorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CorreoKeyTyped
        char b = evt.getKeyChar();
        if (Correo.getText().length() >= 25) {
            evt.consume();
        }
        if ((b < 'a' || b > 'z') && (b < '0' || b > '9') && (b < 'A' || b > 'Z')&& (b != 'ñ') && (b != 'Ñ')) {
            evt.consume();
        }
    }//GEN-LAST:event_CorreoKeyTyped

    private void CiudadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CiudadKeyTyped
        SoloLetras.letrasYespacio(evt, Ciudad.getText());
    }//GEN-LAST:event_CiudadKeyTyped

    private void PrefijoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrefijoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrefijoActionPerformed

    private void Obligatorios3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obligatorios3MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Obligatorios3MouseMoved

    private void Obligatorios2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obligatorios2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Obligatorios2MouseMoved

    private void Obligatorios1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obligatorios1MouseMoved
        Obligatorios1.setToolTipText("Campo Obligatorio");
    }//GEN-LAST:event_Obligatorios1MouseMoved

    private void CargoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CargoKeyReleased

    }//GEN-LAST:event_CargoKeyReleased

    private void CargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargoActionPerformed
    
    }//GEN-LAST:event_CargoActionPerformed

    private void DatosAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DatosAdmActionPerformed
        int posicion = jPanel3.getX();
        if (posicion > -1) {
            Animacion.Animacion.mover_izquierda(0, -284, 2, 2, jPanel3);
        }else {
            Animacion.Animacion.mover_derecha(-264, 0, 2, 2, jPanel3);
        }
    }//GEN-LAST:event_DatosAdmActionPerformed

    private void DatosAdmMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatosAdmMouseMoved
         
    }//GEN-LAST:event_DatosAdmMouseMoved

    private void DatosProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DatosProfesorActionPerformed
          int posicion = PanelProf.getX();
        if (posicion > -1) {
            Animacion.Animacion.mover_izquierda(0, -284, 2, 2, PanelProf);
        }else {
            Animacion.Animacion.mover_derecha(-264, 0, 2, 2, PanelProf);
        }    

    }//GEN-LAST:event_DatosProfesorActionPerformed

    private void DatosProfesorMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatosProfesorMouseMoved
       
    }//GEN-LAST:event_DatosProfesorMouseMoved

    private void CodPOSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodPOSKeyTyped
        char car = evt.getKeyChar();
        if (CodPOS.getText().length() >= 4) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_CodPOSKeyTyped

    private void CodPOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodPOSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodPOSActionPerformed

    private void CasaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CasaKeyTyped
        char car = evt.getKeyChar();
        if (Casa.getText().length() >= 5) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_CasaKeyTyped

    private void CasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CasaActionPerformed

    private void SectorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SectorKeyTyped
        Character s = evt.getKeyChar();
        char b = evt.getKeyChar();
        if (Sector.getText().length() >= 20) {
            evt.consume();
        }
        if ((b < 'a' || b > 'z') && (b < '0' || b > '9') && (b < 'A' || b > 'Z') && (s != 'ñ') && (s != 'Ñ')&& (s != KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_SectorKeyTyped

    private void CedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaKeyTyped
        char car = evt.getKeyChar();
        if (Cedula.getText().length() >= 8) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }

    }//GEN-LAST:event_CedulaKeyTyped

    private void CedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaKeyReleased

        Validacion();
    }//GEN-LAST:event_CedulaKeyReleased

    private void CedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaActionPerformed

    private void NumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumeroKeyTyped
        char car = evt.getKeyChar();
        if (Numero.getText().length() >= 7) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_NumeroKeyTyped

    private void NumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumeroActionPerformed

    private void BsaveempleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BsaveempleadosActionPerformed
        GuardarDatosEmpleados();

    }//GEN-LAST:event_BsaveempleadosActionPerformed

    private void BsaveempleadosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BsaveempleadosMouseMoved
        
    }//GEN-LAST:event_BsaveempleadosMouseMoved

    private void SapellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SapellidoKeyTyped
        SoloLetras.letrasYespacio(evt, Sapellido.getText());
    }//GEN-LAST:event_SapellidoKeyTyped

    private void SapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SapellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SapellidoActionPerformed

    private void PapellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PapellidoKeyTyped
        // TODO add your handling code here:
        char b = evt.getKeyChar();
        if (Papellido.getText().length() >= 10) {
            evt.consume();
        }
        if ((b < 'a' || b > 'z') && (b < 'A' || b > 'Z')&& (b != 'ñ') && (b != 'Ñ')) {
            evt.consume();
        }
    }//GEN-LAST:event_PapellidoKeyTyped

    private void PapellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PapellidoKeyReleased
    Validacion();
    }//GEN-LAST:event_PapellidoKeyReleased

    private void SnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SnombreKeyTyped
        // TODO add your handling code here:
        SoloLetras.letrasYespacio(evt, Snombre.getText());
    }//GEN-LAST:event_SnombreKeyTyped

    private void SnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SnombreActionPerformed

    private void PnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PnombreKeyTyped
        // TODO add your handling code here:
        char b = evt.getKeyChar();
        if (Pnombre.getText().length() >= 10) {
            evt.consume();
        }
        if ((b < 'a' || b > 'z') && (b < 'A' || b > 'Z')&& (b != 'ñ') && (b != 'Ñ')) {
            evt.consume();
        }
    }//GEN-LAST:event_PnombreKeyTyped

    private void PnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PnombreKeyReleased
   Validacion();       
    }//GEN-LAST:event_PnombreKeyReleased

    private void PnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PnombreActionPerformed

    private void DatosProfesorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatosProfesorMouseEntered
        
    }//GEN-LAST:event_DatosProfesorMouseEntered

    private void DatosProfesorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatosProfesorMouseExited
       
    }//GEN-LAST:event_DatosProfesorMouseExited

    private void DatosAdmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatosAdmMouseEntered
         
    }//GEN-LAST:event_DatosAdmMouseEntered

    private void DatosAdmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatosAdmMouseExited
      
    }//GEN-LAST:event_DatosAdmMouseExited

    private void BmodificarempleadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BmodificarempleadoMouseEntered
         
    }//GEN-LAST:event_BmodificarempleadoMouseEntered

    private void BmodificarempleadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BmodificarempleadoMouseExited
       
    }//GEN-LAST:event_BmodificarempleadoMouseExited

    private void SectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SectorActionPerformed

    private void BsaveempleadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BsaveempleadosMouseEntered
        
    }//GEN-LAST:event_BsaveempleadosMouseEntered

    private void BsaveempleadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BsaveempleadosMouseExited
       
    }//GEN-LAST:event_BsaveempleadosMouseExited

    private void fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaActionPerformed

    private void Obligatorios4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obligatorios4MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Obligatorios4MouseMoved

    private void CodExisteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodExisteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodExisteActionPerformed

    private void EspecialidadprofKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EspecialidadprofKeyTyped
        SoloLetras.letrasYespacio(evt, Especialidadprof.getText());
    }//GEN-LAST:event_EspecialidadprofKeyTyped

    private void EstudiosRprofKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EstudiosRprofKeyTyped
        SoloLetras.letrasYespacio(evt, EstudiosRprof.getText());
    }//GEN-LAST:event_EstudiosRprofKeyTyped

    private void CedulaprofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaprofActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaprofActionPerformed

    private void CedulaprofKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaprofKeyReleased
        Validacionprof();

    }//GEN-LAST:event_CedulaprofKeyReleased

    private void CedulaprofKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaprofKeyTyped
        char car = evt.getKeyChar();
        if (Cedulaprof.getText().length() >= 8) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_CedulaprofKeyTyped

    private void guardarprofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarprofActionPerformed
        GuardarDatosProf();
    }//GEN-LAST:event_guardarprofActionPerformed

    private void EspecialidadAdm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EspecialidadAdm2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EspecialidadAdm2ActionPerformed

    private void EspecialidadAdm2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EspecialidadAdm2KeyTyped
        SoloLetras.letrasYespacio(evt, EspecialidadAdm2.getText());
    }//GEN-LAST:event_EspecialidadAdm2KeyTyped

    private void Cedulaadm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cedulaadm2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cedulaadm2ActionPerformed

    private void Cedulaadm2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Cedulaadm2KeyReleased
        Validacionadm();

    }//GEN-LAST:event_Cedulaadm2KeyReleased

    private void Cedulaadm2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Cedulaadm2KeyTyped
        char car = evt.getKeyChar();
        if (Cedulaadm2.getText().length() >= 8) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_Cedulaadm2KeyTyped

    private void saveadm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveadm2ActionPerformed
       GuardarDatosAdmin();
        
    }//GEN-LAST:event_saveadm2ActionPerformed

    private void qqqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qqqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qqqActionPerformed
    
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
            java.util.logging.Logger.getLogger(empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new empleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton Bmodificarempleado;
    public static javax.swing.JButton Bsaveempleados;
    public static javax.swing.JTextField Calle;
    public static javax.swing.JComboBox<String> Cargo;
    public static javax.swing.JTextField Casa;
    public static javax.swing.JTextField Cedula;
    public static javax.swing.JTextField Cedulaadm2;
    public static javax.swing.JTextField Cedulaprof;
    public static javax.swing.JTextField Ciudad;
    public static javax.swing.JTextField CodExiste;
    public static javax.swing.JTextField CodExisteadm2;
    public static javax.swing.JTextField CodExisteprof;
    public static javax.swing.JTextField CodPOS;
    public static javax.swing.JTextField Correo;
    public static javax.swing.JButton DatosAdm;
    public static javax.swing.JButton DatosProfesor;
    public static javax.swing.JTextField EspecialidadAdm2;
    public static javax.swing.JTextField Especialidadprof;
    public static javax.swing.JTextField EstudiosRprof;
    public static javax.swing.JComboBox<String> GeneroCB;
    public static javax.swing.JTextField Numero;
    private javax.swing.JLabel Obligatorios1;
    private javax.swing.JLabel Obligatorios2;
    private javax.swing.JLabel Obligatorios3;
    private javax.swing.JLabel Obligatorios4;
    private javax.swing.JPanel PanelProf;
    public static javax.swing.JTextField Papellido;
    public static javax.swing.JTextField Pnombre;
    public static javax.swing.JComboBox<String> Prefijo;
    public static javax.swing.JComboBox<String> PrefijoCorreo;
    public static javax.swing.JTextField Sapellido;
    public static javax.swing.JTextField Sector;
    public static javax.swing.JTextField Snombre;
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JTextField fecha;
    public static javax.swing.JButton guardarprof;
    public static com.toedter.calendar.JDateChooser jDateChooser1;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel jcheck;
    private javax.swing.JPanel panelempleado;
    private javax.swing.JPanel panelinferior;
    private javax.swing.JPanel panelsuperior;
    public static javax.swing.JTextField qqq;
    public static javax.swing.JButton saveadm2;
    // End of variables declaration//GEN-END:variables
}
