/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import Controlador.SoloLetras;
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

public class mostrarempleados extends javax.swing.JInternalFrame {
static ResultSet res;
    /**
     * Creates new form mostrarempleados
     */
    
public mostrarempleados() {
         
        initComponents();
        setSize(1243, 739);
         
        CargarDatosEmpleado();
    }
public void HabilitarBoton(){
        if(Cedula.getText().length()==8){
            Buscarempleado.setEnabled(true);
        }else{
            Buscarempleado.setEnabled(false);
        }
    }
    public void AgregarCargoo(){
         try {
            String Cedu,Carg,FI;
            BDConexion.EntradaEmpleado(
                FI=fecha.getText(),
                Carg=s.getSelectedItem().toString(),
                Cedu=Cedula.getText());
            JOptionPane.showMessageDialog(null, "CARGO AGREGADO A LA PERSONA");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void ModificarCargoo(){
        try{
            PreparedStatement pps = Conexiones.Conexion.getConexion().prepareStatement("update EMPLEADO set CARGO='" +
                s.getSelectedItem()+
                "' where CI='" + Cedula.getText() + "'");
            pps.executeUpdate();

            s.setSelectedItem("");
            s.requestFocus();

            JOptionPane.showMessageDialog(null,"Cambios Guardados");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void HabilitaraDMParaComboS() {
        String h = s.getSelectedItem().toString();
        
        if (h.equals("Administrativo")  ) {
            Adm1.setBackground(new Color(136,232,190));
            Adm1.setEnabled(true);

            Profesor1.setEnabled(false);
            Profesor1.setBackground(Color.WHITE);
            
        
        }
    }
    public void ModificarEmpleado(){
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
                
                s.setSelectedItem("");
                s.requestFocus();

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
                res= Conexiones.Conexion.Consulta("select CARGO,CI FROM EMPLEADO");
                while(res.next()){
                    if(res.getString(2).equals(b)){
                        s.setSelectedItem(res.getString(1));
                        Cedula.setText(res.getString(2));
                        
                        
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
               this.EditarP.setVisible(true);
        this.EditarP2.setVisible(true);
        this.AgregarCargo.setVisible(true);
        this.ModificarCargo.setVisible(true);
        this.GuardarCambios.setVisible(true);
        this.Profesor1.setVisible(true);
       this.Adm1.setVisible(true);
        this.GuardarCambios.setEnabled(true);
           this.s.setVisible(true);
       
               Cedula.setEditable(false);      
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
              
            }
        }
        
    }
        public void CargarDatosEmpleado(){
        DefaultTableModel modelo =(DefaultTableModel) Tmostrarempleados.getModel();
        modelo.setRowCount(0);
        res = Conexiones.Conexion.Consulta("SELECT  PERSONA.CI,PERSONA.P_NOMBRE,PERSONA.P_APELLIDO,CARGO,PREF,NUMERO FROM PERSONA JOIN TELEFONO ON PERSONA.CI=TELEFONO.CI JOIN EMPLEADO ON PERSONA.CI = EMPLEADO.CI");
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
            Tmostrarempleados.setModel(modelo);
            }
        }catch (SQLException e){            
        }
    }
        public void FiltrarDatos(String Valor){
        DefaultTableModel modelo =(DefaultTableModel) Tmostrarempleados.getModel();
        modelo.setRowCount(0);
        res = Conexiones.Conexion.Consulta("select  PERSONA.CI,PERSONA.P_NOMBRE,PERSONA.P_APELLIDO,CARGO,PREF,NUMERO FROM PERSONA JOIN TELEFONO ON PERSONA.CI=TELEFONO.CI JOIN EMPLEADO ON PERSONA.CI = EMPLEADO.CI WHERE PERSONA.CI LIKE'%"+Valor+"%'");
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
            }
             Tmostrarempleados.setModel(modelo);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al filtrar"+ e.getMessage());
            
        }
    }
 public void HabilitarAdmS() {
        String h = s.getSelectedItem().toString();
        
        if (h.equals("Administrativo")  ) {
            Adm1.setBackground(new Color(136,232,190));
            Adm1.setEnabled(true);

            Profesor1.setEnabled(false);
            Profesor1.setBackground(Color.WHITE);
            
        adm ne=new adm(new javax.swing.JFrame(),true);
         ne.setVisible(true);
        }
    }
    public void HabilitarProfParaComboS() {

        String r = s.getSelectedItem().toString();
         
        if (r.equals("Profesor") ) {
            Profesor1.setBackground(new Color(136,232,190));
            Profesor1.setEnabled(true);
   
            Adm1.setEnabled(false);
            Adm1.setBackground(Color.WHITE);
       
          
        }
    }
    public void HabilitarProfS() 
    {

        String r = s.getSelectedItem().toString();
         
        if (r.equals("Profesor") ) {
            Profesor1.setBackground(new Color(136,232,190));
            Profesor1.setEnabled(true);
   
            Adm1.setEnabled(false);
            Adm1.setBackground(Color.WHITE);
        Profesor ne=new Profesor(new javax.swing.JFrame(),true);
         ne.setVisible(true);
          
        }
    }
     public void modificarPersona()
    {
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
    
    public void modificarDireccion()
    {
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
    
    public void modificarCorreo()
    {
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
     public void modificarTelefono()
    {
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
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        panelsuperior1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tmostrarempleados = new javax.swing.JTable();
        Bagregarrempleado = new javax.swing.JButton();
        Bactualizar = new javax.swing.JButton();
        panelsuperior2 = new javax.swing.JPanel();
        Profesor1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Adm1 = new javax.swing.JButton();
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
        GuardarCambios = new javax.swing.JButton();
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
        GeneroCB = new javax.swing.JComboBox<>();
        PrefijoCorreo = new javax.swing.JComboBox<>();
        Prefijo = new javax.swing.JComboBox<>();
        Ciudad = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        Correo = new javax.swing.JTextField();
        Calle = new javax.swing.JTextField();
        jcheck = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        s = new javax.swing.JComboBox<>();
        EditarP2 = new javax.swing.JButton();
        AgregarCargo = new javax.swing.JButton();
        ModificarCargo = new javax.swing.JButton();
        Buscarempleado = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        fecha = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        buscar = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        EditarP = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(248, 247, 247));
        jPanel1.setLayout(null);

        panelsuperior1.setBackground(new java.awt.Color(0, 153, 204));
        panelsuperior1.setLayout(null);
        jPanel1.add(panelsuperior1);
        panelsuperior1.setBounds(-10, 680, 1420, 30);

        Tmostrarempleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombre", "Apellido", "Cargo", "Prefijo", "Telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tmostrarempleados.setGridColor(new java.awt.Color(0, 0, 0));
        Tmostrarempleados.setRowHeight(25);
        Tmostrarempleados.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jScrollPane1.setViewportView(Tmostrarempleados);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(870, 140, 520, 350);

        Bagregarrempleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_plus_32px.png"))); // NOI18N
        Bagregarrempleado.setText("Registrar Empleado");
        Bagregarrempleado.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Bagregarrempleado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Bagregarrempleado.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Bagregarrempleado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Bagregarrempleado.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BagregarrempleadoMouseMoved(evt);
            }
        });
        Bagregarrempleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BagregarrempleadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BagregarrempleadoMouseExited(evt);
            }
        });
        Bagregarrempleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BagregarrempleadoActionPerformed(evt);
            }
        });
        jPanel1.add(Bagregarrempleado);
        Bagregarrempleado.setBounds(1020, 510, 130, 60);

        Bactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_refresh_32px.png"))); // NOI18N
        Bactualizar.setText("Actualizar");
        Bactualizar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Bactualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Bactualizar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Bactualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
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
        jPanel1.add(Bactualizar);
        Bactualizar.setBounds(1160, 510, 110, 60);

        panelsuperior2.setBackground(new java.awt.Color(0, 153, 204));
        panelsuperior2.setLayout(null);
        jPanel1.add(panelsuperior2);
        panelsuperior2.setBounds(0, 0, 1400, 50);

        Profesor1.setBackground(new java.awt.Color(0, 153, 204));
        Profesor1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_classroom_30px.png"))); // NOI18N
        Profesor1.setText("Profesor");
        Profesor1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Profesor1.setEnabled(false);
        Profesor1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Profesor1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Profesor1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Profesor1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Profesor1MouseMoved(evt);
            }
        });
        Profesor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Profesor1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Profesor1MouseExited(evt);
            }
        });
        Profesor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Profesor1ActionPerformed(evt);
            }
        });
        jPanel1.add(Profesor1);
        Profesor1.setBounds(130, 100, 110, 57);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_32px_2.png"))); // NOI18N
        jButton1.setToolTipText("Buscar Mediante La Cedula del Empleado");
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(1010, 100, 33, 33);

        Adm1.setBackground(new java.awt.Color(0, 153, 204));
        Adm1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_microsoft_admin_30px.png"))); // NOI18N
        Adm1.setText("Administrativo");
        Adm1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Adm1.setEnabled(false);
        Adm1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Adm1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Adm1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Adm1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                Adm1MouseMoved(evt);
            }
        });
        Adm1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Adm1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Adm1MouseExited(evt);
            }
        });
        Adm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Adm1ActionPerformed(evt);
            }
        });
        jPanel1.add(Adm1);
        Adm1.setBounds(130, 170, 110, 57);

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

        GuardarCambios.setBackground(new java.awt.Color(142, 252, 199));
        GuardarCambios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_26px_1.png"))); // NOI18N
        GuardarCambios.setText("Guardar");
        GuardarCambios.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        GuardarCambios.setEnabled(false);
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
        panelempleado.add(GuardarCambios);
        GuardarCambios.setBounds(480, 540, 110, 51);

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

        GeneroCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Femenino", "Masculino" }));
        GeneroCB.setBorder(null);
        GeneroCB.setEnabled(false);
        panelempleado.add(GeneroCB);
        GeneroCB.setBounds(400, 280, 80, 30);

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
        Calle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalleActionPerformed(evt);
            }
        });
        Calle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CalleKeyTyped(evt);
            }
        });
        panelempleado.add(Calle);
        Calle.setBounds(230, 540, 60, 14);
        panelempleado.add(jcheck);
        jcheck.setBounds(283, 60, 23, 0);

        jDateChooser1.setEnabled(false);
        panelempleado.add(jDateChooser1);
        jDateChooser1.setBounds(190, 280, 110, 30);

        jSeparator12.setBackground(new java.awt.Color(22, 44, 81));
        panelempleado.add(jSeparator12);
        jSeparator12.setBounds(310, 490, 70, 10);

        jSeparator13.setBackground(new java.awt.Color(22, 44, 81));
        panelempleado.add(jSeparator13);
        jSeparator13.setBounds(100, 490, 80, 10);

        jSeparator17.setBackground(new java.awt.Color(22, 44, 81));
        panelempleado.add(jSeparator17);
        jSeparator17.setBounds(90, 560, 80, 10);

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

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel13.setText("Dirección");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap())
        );

        panelempleado.add(jPanel3);
        jPanel3.setBounds(40, 400, 540, 50);

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/e.png"))); // NOI18N
        panelempleado.add(jLabel22);
        jLabel22.setBounds(30, 280, 36, 40);

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_name_26px.png"))); // NOI18N
        panelempleado.add(jLabel34);
        jLabel34.setBounds(280, 140, 30, 30);

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_city_30px.png"))); // NOI18N
        panelempleado.add(jLabel35);
        jLabel35.setBounds(20, 460, 30, 30);

        s.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Profesor", "Administrativo" }));
        s.setBorder(null);
        s.setEnabled(false);
        s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sActionPerformed(evt);
            }
        });
        s.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sKeyReleased(evt);
            }
        });
        panelempleado.add(s);
        s.setBounds(280, 90, 90, 18);

        EditarP2.setBackground(new java.awt.Color(0, 102, 153));
        EditarP2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        EditarP2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_16px_1.png"))); // NOI18N
        EditarP2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        EditarP2.setContentAreaFilled(false);
        EditarP2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EditarP2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        EditarP2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EditarP2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EditarP2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                EditarP2MouseExited(evt);
            }
        });
        EditarP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarP2ActionPerformed(evt);
            }
        });
        panelempleado.add(EditarP2);
        EditarP2.setBounds(390, 70, 70, 40);

        AgregarCargo.setBackground(new java.awt.Color(0, 102, 153));
        AgregarCargo.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        AgregarCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_plus_16px.png"))); // NOI18N
        AgregarCargo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        AgregarCargo.setContentAreaFilled(false);
        AgregarCargo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AgregarCargo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        AgregarCargo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AgregarCargo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AgregarCargoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AgregarCargoMouseExited(evt);
            }
        });
        AgregarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarCargoActionPerformed(evt);
            }
        });
        panelempleado.add(AgregarCargo);
        AgregarCargo.setBounds(430, 70, 70, 40);

        ModificarCargo.setBackground(new java.awt.Color(0, 102, 153));
        ModificarCargo.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        ModificarCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_save_16px.png"))); // NOI18N
        ModificarCargo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        ModificarCargo.setContentAreaFilled(false);
        ModificarCargo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ModificarCargo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ModificarCargo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ModificarCargo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ModificarCargoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ModificarCargoMouseExited(evt);
            }
        });
        ModificarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarCargoActionPerformed(evt);
            }
        });
        panelempleado.add(ModificarCargo);
        ModificarCargo.setBounds(470, 70, 70, 40);

        Buscarempleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_30px.png"))); // NOI18N
        Buscarempleado.setBorder(null);
        Buscarempleado.setContentAreaFilled(false);
        Buscarempleado.setEnabled(false);
        Buscarempleado.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BuscarempleadoMouseMoved(evt);
            }
        });
        Buscarempleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarempleadoActionPerformed(evt);
            }
        });
        panelempleado.add(Buscarempleado);
        Buscarempleado.setBounds(380, 20, 31, 31);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setText("Fecha:");
        panelempleado.add(jLabel8);
        jLabel8.setBounds(470, 10, 40, 14);

        fecha.setEditable(false);
        fecha.setBackground(new java.awt.Color(255, 255, 255));
        fecha.setBorder(null);
        fecha.setDisabledTextColor(new java.awt.Color(0, 255, 255));
        fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActionPerformed(evt);
            }
        });
        panelempleado.add(fecha);
        fecha.setBounds(510, 10, 79, 17);

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_postcode_30px.png"))); // NOI18N
        panelempleado.add(jLabel37);
        jLabel37.setBounds(190, 460, 30, 40);

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_google_maps_32px.png"))); // NOI18N
        panelempleado.add(jLabel38);
        jLabel38.setBounds(10, 530, 32, 32);

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_streets_24px.png"))); // NOI18N
        panelempleado.add(jLabel36);
        jLabel36.setBounds(174, 534, 20, 30);

        jPanel1.add(panelempleado);
        panelempleado.setBounds(250, 70, 600, 610);

        buscar.setBackground(new java.awt.Color(0, 0, 0));
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
        buscar.setBounds(1060, 104, 170, 20);

        jSeparator14.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.add(jSeparator14);
        jSeparator14.setBounds(1060, 130, 170, 2);

        EditarP.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        EditarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_edit_32px.png"))); // NOI18N
        EditarP.setToolTipText("Para habilitar el Campo\n");
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
        EditarP.setBounds(160, 340, 70, 50);

        jPanel2.setBackground(new java.awt.Color(0, 135, 180));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 109, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(140, 50, 109, 210);

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LOGOR.png"))); // NOI18N
        jPanel1.add(jLabel20);
        jLabel20.setBounds(640, 260, 650, 520);

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/er.png"))); // NOI18N
        jPanel1.add(jLabel19);
        jLabel19.setBounds(0, 0, 1400, 680);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(-140, 0, 1410, 800);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BagregarrempleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BagregarrempleadoActionPerformed
     empleados obj = new empleados();
        menu.Escritorio.add(obj);
        obj.toFront();
        obj.setVisible(true);
     
    }//GEN-LAST:event_BagregarrempleadoActionPerformed

    private void BagregarrempleadoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BagregarrempleadoMouseMoved
         
    }//GEN-LAST:event_BagregarrempleadoMouseMoved

    private void BactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BactualizarActionPerformed
    CargarDatosEmpleado();
    }//GEN-LAST:event_BactualizarActionPerformed

    private void BagregarrempleadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BagregarrempleadoMouseEntered
      
    }//GEN-LAST:event_BagregarrempleadoMouseEntered

    private void BactualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BactualizarMouseEntered
        
    }//GEN-LAST:event_BactualizarMouseEntered

    private void BagregarrempleadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BagregarrempleadoMouseExited
        
    }//GEN-LAST:event_BagregarrempleadoMouseExited

    private void BactualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BactualizarMouseExited
        
    }//GEN-LAST:event_BactualizarMouseExited

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        FiltrarDatos(buscar.getText());
    }//GEN-LAST:event_buscarKeyReleased

    private void buscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyTyped

    }//GEN-LAST:event_buscarKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Profesor1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Profesor1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_Profesor1MouseMoved

    private void Profesor1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Profesor1MouseEntered
        
    }//GEN-LAST:event_Profesor1MouseEntered

    private void Profesor1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Profesor1MouseExited
         
    }//GEN-LAST:event_Profesor1MouseExited

    private void Profesor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Profesor1ActionPerformed
        Profesor ne=new Profesor(new javax.swing.JFrame(),true);
        ne.setVisible(true);
    }//GEN-LAST:event_Profesor1ActionPerformed

    private void EditarP2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarP2MouseEntered
        EditarP2.setText("Habilitar");
    }//GEN-LAST:event_EditarP2MouseEntered

    private void EditarP2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarP2MouseExited
        EditarP2.setText("");
    }//GEN-LAST:event_EditarP2MouseExited

    private void EditarP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarP2ActionPerformed
        this.s.setEnabled(true);

        HabilitarProfS();
        HabilitarAdmS();

    }//GEN-LAST:event_EditarP2ActionPerformed

    private void AgregarCargoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AgregarCargoMouseEntered
        AgregarCargo.setText("Agregar");
    }//GEN-LAST:event_AgregarCargoMouseEntered

    private void AgregarCargoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AgregarCargoMouseExited
        AgregarCargo.setText("");
    }//GEN-LAST:event_AgregarCargoMouseExited

    private void AgregarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarCargoActionPerformed
        AgregarCargoo();
    }//GEN-LAST:event_AgregarCargoActionPerformed

    private void ModificarCargoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarCargoMouseEntered
        ModificarCargo.setText("Guardar");
    }//GEN-LAST:event_ModificarCargoMouseEntered

    private void ModificarCargoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarCargoMouseExited
        ModificarCargo.setText("");
    }//GEN-LAST:event_ModificarCargoMouseExited

    private void ModificarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarCargoActionPerformed
        ModificarCargoo();
    }//GEN-LAST:event_ModificarCargoActionPerformed

    private void EditarPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarPMouseEntered
        EditarP.setText("Habilitar Campos");
    }//GEN-LAST:event_EditarPMouseEntered

    private void EditarPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarPMouseExited
        EditarP.setText("");
    }//GEN-LAST:event_EditarPMouseExited

    private void EditarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarPActionPerformed

        Pnombre.setEditable(true);
        Snombre.setEditable(true);
        Papellido.setEditable(true);
        Sapellido.setEditable(true);
        GeneroCB.setEnabled(true);
        jDateChooser1.setEnabled(true);
        Ciudad.setEditable(true);
        Sector.setEditable(true);
        Calle.setEditable(true);
        Casa.setEditable(true);
        CodPOS.setEditable(true);
        Correo.setEditable(true);
        PrefijoCorreo.setEnabled(true);
        Prefijo.setEnabled(true);
        Numero.setEditable(true);
    }//GEN-LAST:event_EditarPActionPerformed

    private void Adm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Adm1ActionPerformed
        adm ne=new adm(new javax.swing.JFrame(),true);
        ne.setVisible(true);
    }//GEN-LAST:event_Adm1ActionPerformed

    private void Adm1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Adm1MouseExited
       
    }//GEN-LAST:event_Adm1MouseExited

    private void Adm1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Adm1MouseEntered
         
    }//GEN-LAST:event_Adm1MouseEntered

    private void Adm1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Adm1MouseMoved

    }//GEN-LAST:event_Adm1MouseMoved

    private void sKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sKeyReleased

    }//GEN-LAST:event_sKeyReleased

    private void sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sActionPerformed
        HabilitarProfParaComboS();
        HabilitaraDMParaComboS();
    }//GEN-LAST:event_sActionPerformed

    private void PnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PnombreActionPerformed

    private void PnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PnombreKeyReleased

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

    private void GuardarCambiosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarCambiosMouseMoved

    }//GEN-LAST:event_GuardarCambiosMouseMoved

    private void GuardarCambiosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarCambiosMouseEntered

    }//GEN-LAST:event_GuardarCambiosMouseEntered

    private void GuardarCambiosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarCambiosMouseExited

    }//GEN-LAST:event_GuardarCambiosMouseExited

    private void GuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarCambiosActionPerformed
        
    }//GEN-LAST:event_GuardarCambiosActionPerformed

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
HabilitarBoton();
        
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

    private void BuscarempleadoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuscarempleadoMouseMoved
       
    }//GEN-LAST:event_BuscarempleadoMouseMoved

    private void BuscarempleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarempleadoActionPerformed
    ModificarEmpleado();

    }//GEN-LAST:event_BuscarempleadoActionPerformed

    private void fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaActionPerformed

    private void CalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CalleActionPerformed

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
            java.util.logging.Logger.getLogger(mostrarempleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mostrarempleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mostrarempleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mostrarempleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mostrarempleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton Adm1;
    public static javax.swing.JButton AgregarCargo;
    public static javax.swing.JButton Bactualizar;
    public static javax.swing.JButton Bagregarrempleado;
    public static javax.swing.JButton Buscarempleado;
    public static javax.swing.JTextField Calle;
    public static javax.swing.JTextField Casa;
    public static javax.swing.JTextField Cedula;
    public static javax.swing.JTextField Ciudad;
    public static javax.swing.JTextField CodPOS;
    public static javax.swing.JTextField Correo;
    public static javax.swing.JButton EditarP;
    public static javax.swing.JButton EditarP2;
    public static javax.swing.JComboBox<String> GeneroCB;
    public static javax.swing.JButton GuardarCambios;
    public static javax.swing.JButton ModificarCargo;
    public static javax.swing.JTextField Numero;
    public static javax.swing.JTextField Papellido;
    public static javax.swing.JTextField Pnombre;
    public static javax.swing.JComboBox<String> Prefijo;
    public static javax.swing.JComboBox<String> PrefijoCorreo;
    public static javax.swing.JButton Profesor1;
    public static javax.swing.JTextField Sapellido;
    public static javax.swing.JTextField Sector;
    public static javax.swing.JTextField Snombre;
    public static javax.swing.JTable Tmostrarempleados;
    public static javax.swing.JTextField buscar;
    public static javax.swing.JTextField fecha;
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
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator17;
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
    private javax.swing.JPanel panelsuperior1;
    private javax.swing.JPanel panelsuperior2;
    public static javax.swing.JComboBox<String> s;
    // End of variables declaration//GEN-END:variables
}
