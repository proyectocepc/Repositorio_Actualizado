/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Conexiones.BDConexion;
import Controlador.SoloLetras;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import static javafx.scene.paint.Color.color;

/**
 *
 * @author Familia Mijares
 */
public class Preinscripcion extends  javax.swing.JInternalFrame {

    ImageIcon verde = new ImageIcon(getClass().getResource("/img/si.png"));
    ImageIcon rojo = new ImageIcon(getClass().getResource("/img/delete.png"));
    static ResultSet res;

    Calendar cal = Calendar.getInstance();

    int año_actual2 = cal.get(Calendar.YEAR);

    public void validacionAñoActual() {
        int año = jDateChooser1.getCalendar().get(Calendar.YEAR);

        int resultado = año_actual2 - año;
        if (resultado < 18) {
            this.DatosRepresentante.setVisible(true);
        } else if (resultado < 1) {
            this.DatosRepresentante.setVisible(false);
        } else {
            this.DatosRepresentante.setVisible(false);

        }
    }

    /**
     * Creates new form Preinscripcion
     */
    public Preinscripcion() {
         
        initComponents();
        setSize(1243, 739);
         
        CargarDatosPreins();
        this.GuardarCambios.setVisible(false);
        this.ModificarPreins.setVisible(false);
        this.EditarP.setVisible(false);
        
        
        Tpreins.getTableHeader().setBackground(new Color(51, 153, 255));
        Tpreins.getTableHeader().setBackground(new Color(255, 255, 255));
        Tpreins.setRowHeight(25);
    }

    public void HabilitarBoton() {
        if (Cedula.getText().length() == 8 ) {
            Bsaveestudiante.setEnabled(true);
            this.ModificarPreins.setVisible(true);
        } else {
            Bsaveestudiante.setEnabled(false);
             this.ModificarPreins.setVisible(false);
        }
    }

    public void Validacion() {
        String mensaje = BDConexion.bucarCedula(Cedula.getText());
        if (mensaje.equals("La Cedula ya Existe")) {
            CodExiste.setText("Cedula Registrada");
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
            jDateChooser1.setEnabled(false);
            Bsaveestudiante.setEnabled(false);
        } else if (Cedula.getText().isEmpty()) {
            CodExiste.setText("");

        } else {
            CodExiste.setText("Cedula no Registrada");
            if (!Pnombre.getText().isEmpty() && !Papellido.getText().isEmpty()) {
                Bsaveestudiante.setEnabled(true);

            }
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
                jDateChooser1.setEnabled(true);

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

    public void CargarDatosPreins() {
        DefaultTableModel modelo = (DefaultTableModel) Tpreins.getModel();
        modelo.setRowCount(0);
        res = Conexiones.Conexion.Consulta("SELECT PERSONA.CI,PERSONA.P_NOMBRE,PERSONA.P_APELLIDO,PREF,NUMERO FROM PERSONA JOIN TELEFONO ON "
                + "                         PERSONA.CI=TELEFONO.CI JOIN ESTUDIANTE ON ESTUDIANTE.CI=PERSONA.CI where ESTADO = 'preinscrito'");
        try {
            while (res.next()) {
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getInt(4));
                v.add(res.getInt(5));
                modelo.addRow(v);
                Tpreins.setModel(modelo);
            }
        } catch (SQLException e) {
        }
    }

    public void FiltrarDatos(String Valor) {
        DefaultTableModel modelo = (DefaultTableModel) Tpreins.getModel();
        modelo.setRowCount(0);
        res = Conexiones.Conexion.Consulta("SELECT PERSONA.CI,PERSONA.P_NOMBRE,PERSONA.P_APELLIDO,PREF,NUMERO FROM PERSONA JOIN TELEFONO ON "
                + "                         PERSONA.CI=TELEFONO.CI JOIN ESTUDIANTE ON ESTUDIANTE.CI=PERSONA.CI WHERE PERSONA.CI LIKE'%" + Valor + "%'");
        try {
            while (res.next()) {
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getString(5));

                modelo.addRow(v);
            }
            Tpreins.setModel(modelo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al filtrar" + e.getMessage());

        }
    }
    public void GuardarPreinscripcion(){
        if (Cedula.getText().isEmpty() || Pnombre.getText().isEmpty() || Papellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "rellene todos los campos obligatorios", "informacion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                String Ced, Pno, Sno, Papell, Sapell, Gen, Fecha;
                int dia, mes, año;
                dia = jDateChooser1.getCalendar().get(Calendar.DAY_OF_MONTH);
                mes = jDateChooser1.getCalendar().get(Calendar.MONTH);
                año = jDateChooser1.getCalendar().get(Calendar.YEAR);
                Fecha = año + "-" + (1 + mes) + "-" + dia;

                BDConexion.EntradaPersonal(Ced = Cedula.getText(),
                        Pno = Pnombre.getText(),
                        Papell = Papellido.getText(),
                        Sno = Snombre.getText(),
                        Sapell = Sapellido.getText(),
                        Gen = GeneroCB.getSelectedItem().toString(),
                        Fecha);
                JOptionPane.showMessageDialog(null, "Guardado Exitoso");
                Pnombre.setText("");
                Papellido.setText("");
                Snombre.setText("");
                Sapellido.setText("");
                 jDateChooser1.setCalendar(null);
                Validacion();
                
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
        
//        validacionAñoActual();
        estudiante.setEnabled(true);
        Texto = Cedula.getText();

       Frame f=JOptionPane.getFrameForComponent(this);
        new Estu(f,true).setVisible(true);
    }
    public void ModificarPreinscripcion(){
         if(Cedula.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Coloque Bien sus Datos","Error",JOptionPane.ERROR_MESSAGE);
            Cedula.setText("");
            Cedula.requestFocus();
        }else{
            try{
                String b;
                BDConexion.BUSCP(Integer.parseInt(Cedula.getText()));
                b=Cedula.getText();
                Pnombre.setText("");
                Snombre.setText("");
                Papellido.setText("");
                Sapellido.setText("");
                GeneroCB.setSelectedItem("");

                Cedula.requestFocus();
                Pnombre.requestFocus();
                Snombre.requestFocus();
                Papellido.requestFocus();
                Sapellido.requestFocus();
                GeneroCB.requestFocus();

                Ciudad.setText("");
                Sector.setText("");
                Casa.setText("");
                CodPOS.setText("");
                Calle.setText("");
                Ciudad.requestFocus();
                Sector.requestFocus();
                Calle.requestFocus();
                Casa.requestFocus();
                CodPOS.requestFocus();

                Correo.setText("");
                PrefijoCorreo.setSelectedItem("");
                Prefijo.setSelectedItem("");
                Numero.setText("");
                Correo.requestFocus();
                PrefijoCorreo.requestFocus();
                Prefijo.requestFocus();
                Numero.requestFocus();

                res= Conexiones.Conexion.Consulta("select *from PERSONA");
                while(res.next()){
                    if(res.getString(1).equals(b)){
                        JOptionPane.showMessageDialog(null,"Datos Encontrados");
                        Cedula.setText(res.getString(1));
                        Pnombre .setText(res.getString(2));
                        Snombre.setText(res.getString(3));
                        Papellido.setText(res.getString(4));
                        Sapellido.setText(res.getString(5));
                        GeneroCB.setSelectedItem(res.getString(6));
                    }
                }
                res= Conexiones.Conexion.Consulta("SELECT CUIDAD, SECTOR,NCASA,CODPOS, CI, CALLE FROM DIRECCION");
                while(res.next()){
                    if(res.getString(5).equals(b)){

                        Ciudad.setText(res.getString(1));
                        Sector .setText(res.getString(2));

                        Casa.setText(res.getString(3));
                        CodPOS.setText(res.getString(4));
                        Cedula.setText(res.getString(5));
                        Calle.setText(res.getString(6));
                    }
                }
                res= Conexiones.Conexion.Consulta("SELECT PREF_C, CORREO, CI FROM CORREO");
                while(res.next()){
                    if(res.getString(3).equals(b)){
                        PrefijoCorreo .setSelectedItem(res.getString(1));
                        Correo.setText(res.getString(2));
                        Cedula.setText(res.getString(3));
                    }
                }
               
                res= Conexiones.Conexion.Consulta("SELECT PREF,NUMERO, CI FROM TELEFONO");
                while(res.next()){
                    if(res.getString(3).equals(b)){

                        Prefijo.setSelectedItem(res.getString(1));
                        Numero .setText(res.getString(2));
                        Cedula.setText(res.getString(3));
                    }
                }
                Cedula.setEditable(false);
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
                JOptionPane.showMessageDialog(null,"Datos no Encontrados");
            }
        }
        this.ModificarPreins.setVisible(true);
        this.EditarP.setVisible(true);
        this.GuardarCambios.setVisible(true);
        Bsaveestudiante.setVisible(false);
        estudiante.setEnabled(true);
        DatosRepresentante.setEnabled(true);
    }
    public void modificarPersona(){
        try{
            PreparedStatement pps = Conexiones.Conexion.getConexion().prepareStatement("update PERSONA set P_NOMBRE='" +
                Pnombre.getText() + "', S_NOMBRE='" + Snombre.getText() + "', P_APELLIDO='" + Papellido.getText() + "', S_APELLIDO='" + Sapellido.getText()
                + "', GENERO='" + GeneroCB.getSelectedItem() +
                "' where CI='" + Cedula.getText() + "'");
            pps.executeUpdate();

            Pnombre.setText("");
            Snombre.setText("");
            Papellido.setText("");
            Sapellido.setText("");
            GeneroCB.setSelectedItem("");

            Cedula.requestFocus();
            Pnombre.requestFocus();
            Snombre.requestFocus();
            Papellido.requestFocus();
            Sapellido.requestFocus();
            GeneroCB.requestFocus();

            JOptionPane.showMessageDialog(null,"Cambios Guardados");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
    }
    }
    public void modificarCorreo(){
         try{
            PreparedStatement pps = Conexiones.Conexion.getConexion().prepareStatement("update CORREO set CORREO='"
                + Correo.getText() +"', PREF_C='" + PrefijoCorreo.getSelectedItem() +
                "' where CI='" + Cedula.getText() + "'");
            pps.executeUpdate();

            Correo.setText("");
            PrefijoCorreo.setSelectedItem("");

            Correo.requestFocus();
            PrefijoCorreo.requestFocus();

            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void modificarDireccion(){
        try{
            PreparedStatement pps = Conexiones.Conexion.getConexion().prepareStatement("update DIRECCION set CUIDAD='" + Ciudad.getText()
                +"', SECTOR='" + Sector.getText() +"', CALLE='" + Calle.getText() +"', NCASA='" + Casa.getText() +
                "', CODPOS='" + CodPOS.getText() +
                "' where CI='" + Cedula.getText() + "'");
            pps.executeUpdate();

            Ciudad.setText("");
            Sector.setText("");
            Calle.setText("");
            Casa.setText("");
            CodPOS.setText("");
            Ciudad.requestFocus();
            Sector.requestFocus();
            Calle.requestFocus();
            Casa.requestFocus();
            CodPOS.requestFocus();

           
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void modificarTelefono(){
        try{
            PreparedStatement pps = Conexiones.Conexion.getConexion().prepareStatement("update TELEFONO set PREF='"
                + Prefijo.getSelectedItem() + "', NUMERO='" + Numero.getText()+ "' where CI='" + Cedula.getText() + "'");
            pps.executeUpdate();

            Prefijo.setSelectedItem("");
            Numero.setText("");

            Prefijo.requestFocus();
            Numero.requestFocus();

           
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        panelsuperior = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tpreins = new javax.swing.JTable();
        Bactualizar1 = new javax.swing.JButton();
        REPREYESTU = new javax.swing.JButton();
        panelsuperior1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        buscar = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        EditarP = new javax.swing.JButton();
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
        jSeparator8 = new javax.swing.JSeparator();
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
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        ModificarPreins = new javax.swing.JButton();
        Bsaveestudiante = new javax.swing.JButton();
        GuardarCambios = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        estudiante = new javax.swing.JButton();
        DatosRepresentante = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Registro Datos Personales");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(248, 247, 247));
        jPanel1.setLayout(null);

        panelsuperior.setBackground(new java.awt.Color(0, 153, 204));

        javax.swing.GroupLayout panelsuperiorLayout = new javax.swing.GroupLayout(panelsuperior);
        panelsuperior.setLayout(panelsuperiorLayout);
        panelsuperiorLayout.setHorizontalGroup(
            panelsuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        panelsuperiorLayout.setVerticalGroup(
            panelsuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel1.add(panelsuperior);
        panelsuperior.setBounds(0, 690, 1280, 60);

        Tpreins.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Tpreins.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Tpreins.setForeground(new java.awt.Color(0, 0, 51));
        Tpreins.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CI", "Nombre", "Apellido", " Prefijo", "Telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tpreins.setFocusable(false);
        Tpreins.setGridColor(new java.awt.Color(0, 0, 0));
        Tpreins.setRowHeight(25);
        Tpreins.setSelectionBackground(new java.awt.Color(255, 0, 51));
        Tpreins.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Tpreins);
        if (Tpreins.getColumnModel().getColumnCount() > 0) {
            Tpreins.getColumnModel().getColumn(0).setResizable(false);
            Tpreins.getColumnModel().getColumn(3).setResizable(false);
            Tpreins.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(770, 130, 470, 480);

        Bactualizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_refresh_32px.png"))); // NOI18N
        Bactualizar1.setText("Actualizar");
        Bactualizar1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Bactualizar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Bactualizar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Bactualizar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Bactualizar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Bactualizar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Bactualizar1MouseExited(evt);
            }
        });
        Bactualizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bactualizar1ActionPerformed(evt);
            }
        });
        jPanel1.add(Bactualizar1);
        Bactualizar1.setBounds(1090, 620, 93, 60);

        REPREYESTU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_family_32px.png"))); // NOI18N
        REPREYESTU.setText("Listado Representante");
        REPREYESTU.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        REPREYESTU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                REPREYESTUMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                REPREYESTUMouseExited(evt);
            }
        });
        REPREYESTU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REPREYESTUActionPerformed(evt);
            }
        });
        jPanel1.add(REPREYESTU);
        REPREYESTU.setBounds(840, 620, 210, 60);

        panelsuperior1.setBackground(new java.awt.Color(0, 153, 204));
        panelsuperior1.setLayout(null);
        jPanel1.add(panelsuperior1);
        panelsuperior1.setBounds(0, 0, 1270, 50);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_32px_2.png"))); // NOI18N
        jButton1.setToolTipText("Buscar Mediante la Cedula del Estudiante");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(860, 80, 33, 33);

        buscar.setBackground(new java.awt.Color(248, 247, 247));
        buscar.setForeground(new java.awt.Color(255, 255, 255));
        buscar.setBorder(null);
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscarKeyTyped(evt);
            }
        });
        jPanel1.add(buscar);
        buscar.setBounds(910, 90, 130, 20);

        jSeparator14.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(jSeparator14);
        jSeparator14.setBounds(910, 110, 130, 2);

        EditarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_32px.png"))); // NOI18N
        EditarP.setBorder(null);
        EditarP.setContentAreaFilled(false);
        EditarP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EditarPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EditarPMouseExited(evt);
            }
        });
        EditarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarPActionPerformed(evt);
            }
        });
        jPanel1.add(EditarP);
        EditarP.setBounds(10, 490, 100, 70);

        panelempleado.setBackground(new java.awt.Color(255, 255, 255));

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

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Genero");

        jSeparator2.setBackground(new java.awt.Color(22, 44, 81));

        jSeparator3.setBackground(new java.awt.Color(22, 44, 81));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText(" Nombre");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Segundo Nombre");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText(" Apellido");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Segundo Apellido");

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

        jSeparator4.setBackground(new java.awt.Color(22, 44, 81));

        jSeparator5.setBackground(new java.awt.Color(22, 44, 81));

        jSeparator6.setBackground(new java.awt.Color(22, 44, 81));

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

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Telèfono");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Fecha de Nacimiento");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Correo");

        jSeparator7.setBackground(new java.awt.Color(22, 44, 81));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("CI");

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

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Sector");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Calle");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("N° Casa");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("Código Postal");

        jSeparator8.setBackground(new java.awt.Color(22, 44, 81));

        GeneroCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Femenino", "Masculino" }));
        GeneroCB.setBorder(null);
        GeneroCB.setEnabled(false);

        Obligatorios1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Obligatorios1.setForeground(new java.awt.Color(255, 0, 0));
        Obligatorios1.setText("*");
        Obligatorios1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Obligatorios1MouseMoved(evt);
            }
        });

        Obligatorios2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Obligatorios2.setForeground(new java.awt.Color(255, 0, 0));
        Obligatorios2.setText("*");
        Obligatorios2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Obligatorios2MouseMoved(evt);
            }
        });

        Obligatorios3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Obligatorios3.setForeground(new java.awt.Color(255, 0, 0));
        Obligatorios3.setText("*");
        Obligatorios3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Obligatorios3MouseMoved(evt);
            }
        });

        PrefijoCorreo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "@gmail.com", "@hotmail.com", "@yahoo.com" }));
        PrefijoCorreo.setBorder(null);
        PrefijoCorreo.setEnabled(false);

        Prefijo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0414", "0424", "0412", "0416", "0426", "0283" }));
        Prefijo.setBorder(null);
        Prefijo.setEnabled(false);
        Prefijo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrefijoActionPerformed(evt);
            }
        });

        Ciudad.setEditable(false);
        Ciudad.setBackground(new java.awt.Color(255, 255, 255));
        Ciudad.setBorder(null);
        Ciudad.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Ciudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CiudadKeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("Ciudad");

        Correo.setEditable(false);
        Correo.setBackground(new java.awt.Color(255, 255, 255));
        Correo.setBorder(null);
        Correo.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CorreoKeyTyped(evt);
            }
        });

        Calle.setEditable(false);
        Calle.setBackground(new java.awt.Color(255, 255, 255));
        Calle.setBorder(null);
        Calle.setDisabledTextColor(new java.awt.Color(69, 121, 185));
        Calle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CalleKeyTyped(evt);
            }
        });

        CodExiste.setEditable(false);
        CodExiste.setBackground(new java.awt.Color(255, 255, 255));
        CodExiste.setBorder(null);
        CodExiste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodExisteActionPerformed(evt);
            }
        });

        qqq.setEditable(false);
        qqq.setBackground(new java.awt.Color(255, 255, 255));
        qqq.setBorder(null);
        qqq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qqqActionPerformed(evt);
            }
        });

        Obligatorios4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Obligatorios4.setForeground(new java.awt.Color(255, 0, 0));
        Obligatorios4.setText("*");
        Obligatorios4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Obligatorios4MouseMoved(evt);
            }
        });

        jDateChooser1.setEnabled(false);

        jSeparator12.setBackground(new java.awt.Color(22, 44, 81));

        jSeparator13.setBackground(new java.awt.Color(22, 44, 81));

        jSeparator15.setBackground(new java.awt.Color(22, 44, 81));

        jSeparator16.setBackground(new java.awt.Color(22, 44, 81));

        jSeparator18.setBackground(new java.awt.Color(22, 44, 81));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_name_26px.png"))); // NOI18N

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_identification_documents_24px.png"))); // NOI18N

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_email_24px.png"))); // NOI18N

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_iphone_x_32px.png"))); // NOI18N

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_home_24px.png"))); // NOI18N

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_name_26px.png"))); // NOI18N

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_name_26px.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel13.setText("Dirección");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap())
        );

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/e.png"))); // NOI18N

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_name_26px.png"))); // NOI18N

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_city_30px.png"))); // NOI18N

        ModificarPreins.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_property_26px.png"))); // NOI18N
        ModificarPreins.setText("Modificar");
        ModificarPreins.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        ModificarPreins.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ModificarPreins.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ModificarPreins.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ModificarPreins.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ModificarPreinsMouseMoved(evt);
            }
        });
        ModificarPreins.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ModificarPreinsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ModificarPreinsMouseExited(evt);
            }
        });
        ModificarPreins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarPreinsActionPerformed(evt);
            }
        });

        Bsaveestudiante.setBackground(new java.awt.Color(142, 252, 199));
        Bsaveestudiante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        Bsaveestudiante.setText("Guardar");
        Bsaveestudiante.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Bsaveestudiante.setEnabled(false);
        Bsaveestudiante.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BsaveestudianteMouseMoved(evt);
            }
        });
        Bsaveestudiante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BsaveestudianteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BsaveestudianteMouseExited(evt);
            }
        });
        Bsaveestudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BsaveestudianteActionPerformed(evt);
            }
        });

        GuardarCambios.setBackground(new java.awt.Color(142, 252, 199));
        GuardarCambios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        GuardarCambios.setText("Guardar");
        GuardarCambios.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
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

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_streets_24px.png"))); // NOI18N

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_postcode_30px.png"))); // NOI18N

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_google_maps_32px.png"))); // NOI18N

        javax.swing.GroupLayout panelempleadoLayout = new javax.swing.GroupLayout(panelempleado);
        panelempleado.setLayout(panelempleadoLayout);
        panelempleadoLayout.setHorizontalGroup(
            panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelempleadoLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addComponent(GuardarCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ModificarPreins, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(Bsaveestudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel18)
                        .addGap(11, 11, 11)
                        .addComponent(Ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(12, 12, 12)
                                .addComponent(CodPOS, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(panelempleadoLayout.createSequentialGroup()
                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(Prefijo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addComponent(Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel3)
                        .addGap(11, 11, 11)
                        .addComponent(Correo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(PrefijoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel10)
                                .addGap(1, 1, 1)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(Obligatorios4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2)
                                .addGap(9, 9, 9)
                                .addComponent(GeneroCB, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Papellido, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Obligatorios1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel7)
                                .addGap(12, 12, 12)
                                .addComponent(Sapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(13, 13, 13)
                                .addComponent(Sector, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel36)
                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel14)
                                .addGap(13, 13, 13)
                                .addComponent(Calle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel15)
                                .addGap(3, 3, 3)
                                .addComponent(Casa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelempleadoLayout.createSequentialGroup()
                                        .addGap(170, 170, 170)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(Obligatorios3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelempleadoLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcheck, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Pnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(Obligatorios2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel5)))
                        .addGap(11, 11, 11)
                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(qqq, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CodExiste, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Snombre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        panelempleadoLayout.setVerticalGroup(
            panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelempleadoLayout.createSequentialGroup()
                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Obligatorios3)
                            .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(Cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addComponent(qqq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(CodExiste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jcheck, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel24)))
                .addGap(38, 38, 38)
                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addComponent(Pnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Obligatorios2)
                            .addComponent(jLabel5)
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addComponent(Snombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(26, 26, 26)
                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(Papellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Obligatorios1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(Sapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GeneroCB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Obligatorios4)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32)
                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(Prefijo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(Correo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PrefijoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelempleadoLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelempleadoLayout.createSequentialGroup()
                                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel35)
                                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel18)
                                                    .addComponent(Ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27))
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(CodPOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)))
                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelempleadoLayout.createSequentialGroup()
                                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelempleadoLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(Sector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14)
                                            .addComponent(Calle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15)
                                            .addComponent(Casa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(panelempleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ModificarPreins, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bsaveestudiante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GuardarCambios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(panelempleado);
        panelempleado.setBounds(150, 60, 600, 610);

        jPanel7.setBackground(new java.awt.Color(0, 135, 180));
        jPanel7.setLayout(null);

        estudiante.setBackground(new java.awt.Color(0, 153, 204));
        estudiante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_student_male_32px.png"))); // NOI18N
        estudiante.setText("Recaudos Estudiante");
        estudiante.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        estudiante.setEnabled(false);
        estudiante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        estudiante.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        estudiante.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        estudiante.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                estudianteMouseMoved(evt);
            }
        });
        estudiante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                estudianteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                estudianteMouseExited(evt);
            }
        });
        estudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estudianteActionPerformed(evt);
            }
        });
        jPanel7.add(estudiante);
        estudiante.setBounds(-10, 40, 140, 60);

        DatosRepresentante.setBackground(new java.awt.Color(0, 153, 204));
        DatosRepresentante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_family_32px.png"))); // NOI18N
        DatosRepresentante.setText("Representante");
        DatosRepresentante.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        DatosRepresentante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        DatosRepresentante.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        DatosRepresentante.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        DatosRepresentante.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                DatosRepresentanteMouseMoved(evt);
            }
        });
        DatosRepresentante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DatosRepresentanteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DatosRepresentanteMouseExited(evt);
            }
        });
        DatosRepresentante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DatosRepresentanteActionPerformed(evt);
            }
        });
        jPanel7.add(DatosRepresentante);
        DatosRepresentante.setBounds(-20, 110, 150, 60);

        jPanel1.add(jPanel7);
        jPanel7.setBounds(0, 50, 140, 250);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOGOR.png"))); // NOI18N
        jPanel1.add(jLabel17);
        jLabel17.setBounds(690, 280, 650, 520);

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/er.png"))); // NOI18N
        jPanel1.add(jLabel19);
        jLabel19.setBounds(0, 0, 1260, 690);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1260, 780);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BsaveestudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BsaveestudianteActionPerformed
        GuardarPreinscripcion();
    }//GEN-LAST:event_BsaveestudianteActionPerformed

    private void DatosRepresentanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DatosRepresentanteActionPerformed
       Frame f=JOptionPane.getFrameForComponent(this);
        new Repre(f,true).setVisible(true);
    }//GEN-LAST:event_DatosRepresentanteActionPerformed

    private void BsaveestudianteMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BsaveestudianteMouseMoved

    }//GEN-LAST:event_BsaveestudianteMouseMoved

    private void ModificarPreinsMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarPreinsMouseMoved

    }//GEN-LAST:event_ModificarPreinsMouseMoved

    private void DatosRepresentanteMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatosRepresentanteMouseMoved

    }//GEN-LAST:event_DatosRepresentanteMouseMoved

    private void estudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estudianteActionPerformed
       Frame f=JOptionPane.getFrameForComponent(this);
        new Estu(f,true).setVisible(true);
    }//GEN-LAST:event_estudianteActionPerformed

    private void ModificarPreinsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarPreinsActionPerformed
       ModificarPreinscripcion();
    }//GEN-LAST:event_ModificarPreinsActionPerformed

    private void estudianteMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_estudianteMouseMoved
         
    }//GEN-LAST:event_estudianteMouseMoved

    private void Bactualizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bactualizar1ActionPerformed
        CargarDatosPreins();
    }//GEN-LAST:event_Bactualizar1ActionPerformed

    private void ModificarPreinsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarPreinsMouseExited
        

    }//GEN-LAST:event_ModificarPreinsMouseExited

    private void ModificarPreinsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarPreinsMouseEntered
         

    }//GEN-LAST:event_ModificarPreinsMouseEntered

    private void Bactualizar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bactualizar1MouseEntered
      
    }//GEN-LAST:event_Bactualizar1MouseEntered

    private void Bactualizar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Bactualizar1MouseExited
        
    }//GEN-LAST:event_Bactualizar1MouseExited

    private void estudianteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_estudianteMouseEntered
         
    }//GEN-LAST:event_estudianteMouseEntered

    private void estudianteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_estudianteMouseExited
         
    }//GEN-LAST:event_estudianteMouseExited

    private void DatosRepresentanteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatosRepresentanteMouseEntered
       
    }//GEN-LAST:event_DatosRepresentanteMouseEntered

    private void DatosRepresentanteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatosRepresentanteMouseExited
         
    }//GEN-LAST:event_DatosRepresentanteMouseExited

    private void BsaveestudianteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BsaveestudianteMouseEntered
        
    }//GEN-LAST:event_BsaveestudianteMouseEntered

    private void BsaveestudianteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BsaveestudianteMouseExited
       
    }//GEN-LAST:event_BsaveestudianteMouseExited

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        FiltrarDatos(buscar.getText());
    }//GEN-LAST:event_buscarKeyReleased

    private void buscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyTyped
        char car = evt.getKeyChar();
        if (buscar.getText().length() >= 8) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_buscarKeyTyped

    private void EditarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarPActionPerformed
       

        Pnombre.setEnabled(true);
        Snombre.setEnabled(true);
        Papellido.setEnabled(true);
        Sapellido.setEnabled(true);
        GeneroCB.setEnabled(true);
        jDateChooser1.setEnabled(true);
        Ciudad.setEnabled(true);
        Sector.setEnabled(true);
        Calle.setEnabled(true);
        Casa.setEnabled(true);
        CodPOS.setEnabled(true);
        Correo.setEnabled(true);
        PrefijoCorreo.setEnabled(true);
        Prefijo.setEnabled(true);
        Numero.setEnabled(true);
    }//GEN-LAST:event_EditarPActionPerformed

    private void GuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarCambiosActionPerformed
        
        
        modificarPersona();
        modificarCorreo();
        modificarDireccion();
        modificarTelefono();
       this.GuardarCambios.setVisible(false);
        this.ModificarPreins.setVisible(false);
        this.EditarP.setVisible(false);
        Bsaveestudiante.setVisible(true);
    }//GEN-LAST:event_GuardarCambiosActionPerformed

    private void GuardarCambiosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarCambiosMouseEntered
         GuardarCambios.setText("Guardar Cambios");
    }//GEN-LAST:event_GuardarCambiosMouseEntered

    private void GuardarCambiosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarCambiosMouseExited
         GuardarCambios.setText("");
    }//GEN-LAST:event_GuardarCambiosMouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void EditarPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarPMouseEntered
         EditarP.setText("Habilitar Campos");
    }//GEN-LAST:event_EditarPMouseEntered

    private void EditarPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarPMouseExited
       EditarP.setText("");
    }//GEN-LAST:event_EditarPMouseExited

    private void REPREYESTUMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_REPREYESTUMouseEntered
       
    }//GEN-LAST:event_REPREYESTUMouseEntered

    private void REPREYESTUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REPREYESTUActionPerformed
          Frame f=JOptionPane.getFrameForComponent(this);
        new EstuyRepre(f,true).setVisible(true);
    }//GEN-LAST:event_REPREYESTUActionPerformed

    private void REPREYESTUMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_REPREYESTUMouseExited
      
    }//GEN-LAST:event_REPREYESTUMouseExited

    private void PnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PnombreActionPerformed

    private void PnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PnombreKeyReleased
 Validacion();
    }//GEN-LAST:event_PnombreKeyReleased

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

    private void SnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SnombreActionPerformed

    private void SnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SnombreKeyTyped
        // TODO add your handling code here:
        SoloLetras.letrasYespacio(evt, Snombre.getText());
    }//GEN-LAST:event_SnombreKeyTyped

    private void PapellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PapellidoKeyReleased
 Validacion();
    }//GEN-LAST:event_PapellidoKeyReleased

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

    private void SapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SapellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SapellidoActionPerformed

    private void SapellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SapellidoKeyTyped
        SoloLetras.letrasYespacio(evt, Sapellido.getText());
    }//GEN-LAST:event_SapellidoKeyTyped

    private void NumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumeroActionPerformed

    private void NumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumeroKeyTyped
        char car = evt.getKeyChar();
        if (Numero.getText().length() >= 7) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_NumeroKeyTyped

    private void CedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaActionPerformed

    private void CedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaKeyReleased

        Validacion();
    }//GEN-LAST:event_CedulaKeyReleased

    private void CedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CedulaKeyTyped
        char car = evt.getKeyChar();
        if (Cedula.getText().length() >= 8) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_CedulaKeyTyped

    private void SectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SectorActionPerformed

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

    private void CasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CasaActionPerformed

    private void CasaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CasaKeyTyped
        char car = evt.getKeyChar();
        if (Casa.getText().length() >= 5) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_CasaKeyTyped

    private void CodPOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodPOSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodPOSActionPerformed

    private void CodPOSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodPOSKeyTyped
        char car = evt.getKeyChar();
        if (CodPOS.getText().length() >= 4) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_CodPOSKeyTyped

    private void Obligatorios1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obligatorios1MouseMoved
        Obligatorios1.setToolTipText("Campo Obligatorio");
    }//GEN-LAST:event_Obligatorios1MouseMoved

    private void Obligatorios2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obligatorios2MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Obligatorios2MouseMoved

    private void Obligatorios3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obligatorios3MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Obligatorios3MouseMoved

    private void PrefijoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrefijoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrefijoActionPerformed

    private void CiudadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CiudadKeyTyped
        SoloLetras.letrasYespacio(evt, Ciudad.getText());
    }//GEN-LAST:event_CiudadKeyTyped

    private void CorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CorreoKeyTyped
        char b = evt.getKeyChar();
        if (Correo.getText().length() >= 25) {
            evt.consume();
        }
        if ((b < 'a' || b > 'z') && (b < '0' || b > '9') && (b < 'A' || b > 'Z')&& (b != 'ñ') && (b != 'Ñ')) {
            evt.consume();
        }
    }//GEN-LAST:event_CorreoKeyTyped

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

    private void CodExisteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodExisteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodExisteActionPerformed

    private void qqqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qqqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qqqActionPerformed

    private void Obligatorios4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obligatorios4MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Obligatorios4MouseMoved

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
            java.util.logging.Logger.getLogger(Preinscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Preinscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Preinscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Preinscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Preinscripcion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton Bactualizar1;
    public static javax.swing.JButton Bsaveestudiante;
    public static javax.swing.JTextField Calle;
    public static javax.swing.JTextField Casa;
    public static javax.swing.JTextField Cedula;
    public static javax.swing.JTextField Ciudad;
    public static javax.swing.JTextField CodExiste;
    public static javax.swing.JTextField CodPOS;
    public static javax.swing.JTextField Correo;
    public static javax.swing.JButton DatosRepresentante;
    public static javax.swing.JButton EditarP;
    public static javax.swing.JComboBox<String> GeneroCB;
    public static javax.swing.JButton GuardarCambios;
    public static javax.swing.JButton ModificarPreins;
    public static javax.swing.JTextField Numero;
    private javax.swing.JLabel Obligatorios1;
    private javax.swing.JLabel Obligatorios2;
    private javax.swing.JLabel Obligatorios3;
    private javax.swing.JLabel Obligatorios4;
    public static javax.swing.JTextField Papellido;
    public static javax.swing.JTextField Pnombre;
    public static javax.swing.JComboBox<String> Prefijo;
    public static javax.swing.JComboBox<String> PrefijoCorreo;
    private javax.swing.JButton REPREYESTU;
    public static javax.swing.JTextField Sapellido;
    public static javax.swing.JTextField Sector;
    public static javax.swing.JTextField Snombre;
    public static javax.swing.JTable Tpreins;
    public static javax.swing.JTextField buscar;
    public static javax.swing.JButton estudiante;
    private javax.swing.JButton jButton1;
    public static com.toedter.calendar.JDateChooser jDateChooser1;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel jcheck;
    private javax.swing.JPanel panelempleado;
    private javax.swing.JPanel panelsuperior;
    private javax.swing.JPanel panelsuperior1;
    public static javax.swing.JTextField qqq;
    // End of variables declaration//GEN-END:variables
}
