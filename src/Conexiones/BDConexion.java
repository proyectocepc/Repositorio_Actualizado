package Conexiones;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

public class BDConexion {
//comentario
      
    public static void EntradaCurso(String a, String b, String c, String d, String e, String f,String g) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaCurso(?,?,?,?,?,?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.setString(3, c);
        entrada.setString(4, d);
        entrada.setString(5, e);
        entrada.setString(6, f);
        entrada.setString(7, g);
        entrada.execute();

    }
    int jesus = 1;
    

    public static void EntradaPersonal(String a, String b, String c, String d, String e, String f, String g) throws SQLException {
        CallableStatement r = Conexion.getConexion().prepareCall("{call EntradaPersona(?,?,?,?,?,?,?)}");
        r.setString(1, a);
        r.setString(2, b);
        r.setString(3, c);
        r.setString(4, d);
        r.setString(5, e);
        r.setString(6, f);
        r.setString(7, g);

        r.execute();

    }

    public static void BuscarCurso(int a) throws SQLException {
        CallableStatement Buscar = Conexion.getConexion().prepareCall("{call BuscarCurso(?)}");
        Buscar.setInt(1, a);
        Buscar.execute();
    }

    public static void BuscaPregunta(String a) throws SQLException {
        CallableStatement Buscar = Conexion.getConexion().prepareCall("{call BuscarpREGUNTA(?)}");
        Buscar.setString(1, a);
        Buscar.execute();
    }

    public static void BuscarNota(int a) throws SQLException {
        CallableStatement Buscar = Conexion.getConexion().prepareCall("{call BuscarNota(?)}");
        Buscar.setInt(1, a);
        Buscar.execute();
    }

    public static void BuscarSeccion(String a) throws SQLException {
        CallableStatement Buscar = Conexion.getConexion().prepareCall("{call BuscarNSeccion(?)}");
        Buscar.setString(1, a);
        Buscar.execute();
    }

    public static void BUSCP(int a) throws SQLException {
        CallableStatement Buscar = Conexion.getConexion().prepareCall("{call BuscarPersona(?)}");
        Buscar.setInt(1, a);
        Buscar.execute();
    }

    public static void EntradaUsuario(String a, String b, String c, String d, String e, String f) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaUsuario(?,?,?,?,?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.setString(3, c);
        entrada.setString(4, d);
        entrada.setString(5, e);
        entrada.setString(6, f);
        entrada.execute();

    }

    public static void BuscarUsuario(int a) throws SQLException {
        CallableStatement Buscar = Conexion.getConexion().prepareCall("{call BuscarUsuario(?)}");
        Buscar.setInt(1, a);
        Buscar.execute();
    }

    public static void BuscarEstudiante(int a) throws SQLException {
        CallableStatement Buscar = Conexion.getConexion().prepareCall("{call BuscarEstudiante(?)}");
        Buscar.setInt(1, a);
        Buscar.execute();
    }

    public static void BuscarProfesor(int a) throws SQLException {
        CallableStatement Buscar = Conexion.getConexion().prepareCall("{call BuscarProfesor(?)}");
        Buscar.setInt(1, a);
        Buscar.execute();
    }

    public static void BuscarAdm(int a) throws SQLException {
        CallableStatement Buscar = Conexion.getConexion().prepareCall("{call BuscarAdministrador(?)}");
        Buscar.setInt(1, a);
        Buscar.execute();
    }

    public static void BuscarNotas(int a) throws SQLException {
        CallableStatement Buscar = Conexion.getConexion().prepareCall("{call BuscarNotas(?)}");
        Buscar.setInt(1, a);
        Buscar.execute();
    }

    public static void EntradaDireccion(String a, String b, String c, String d, String e, String f) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaDireccion(?,?,?,?,?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.setString(3, c);
        entrada.setString(4, d);
        entrada.setString(5, e);
        entrada.setString(6, f);
        entrada.execute();
    }

    public static void BuscarD(int a) throws SQLException {
        CallableStatement Buscar = Conexion.getConexion().prepareCall("{call Usuario(?)}");
        Buscar.setInt(1, a);
        Buscar.execute();
    }

    public static void EntradaTelefono(String a, String b, String c) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaTelefono(?,?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.setString(3, c);
        entrada.execute();

    }

    public static void EntradaCorreo(String a, String b, String c) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaCorreo(?,?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.setString(3, c);
        entrada.execute();
    }

    public static void EntradaEstudiante(String a, String b, String c, String d) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaEstudiante(?,?,?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.setString(3, c);
        entrada.setString(4, d);

        entrada.execute();
    }

    public static void EntradaEmpleado(String a, String b, String c) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaEmpleado(?,?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.setString(3, c);
        entrada.execute();
    }

    public static void EntradaProfesor(String a, String b, String c) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaProfesor(?,?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.setString(3, c);
        entrada.execute();
    }

    public static void EntradaAdministrativo(String a, String b) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaAdministrativo(?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.execute();
    }

    public static void EntradaModulo(String a, String b) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaModulo(?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.execute();
    }

   public static void EntradaSeccion(String a, String b, String c,String d, String e, String f) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaSeccion(?,?,?,?,?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.setString(3, c);
        entrada.setString(4, d);
        entrada.setString(5, e);
        entrada.setString(6, f);
        /*EntradaSec;*/
        entrada.execute();
    }
    public static void EntradaProfSec(String a, String b) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaPIS(?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.execute();
    }
    public static void EntradaPago(String a, String b,String c, String d,String e,String f) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaPago(?,?,?,?,?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.setString(3, c);
        entrada.setString(4, d);
        entrada.setString(5, e);
        entrada.setString(6, f);
        entrada.execute();
    }

    public static void EntradaEstudianteEstIns(String a, String b, String c) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaEIS(?,?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.setString(3, c);
        entrada.execute();
    }

    public static void EntradaEstuReInscrip(String a, String b, String c, String d) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaERI(?,?,?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.setString(3, c);
        entrada.setString(4, d);
        entrada.execute();
    }

    public static void EntradaRepresentante(String a) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaRepresentante(?)}");
        entrada.setString(1, a);

        entrada.execute();
    }

    public static void EntradaEstuPuedeTeneRepre(String a, String b, String c) throws SQLException {
        CallableStatement entrada = Conexion.getConexion().prepareCall("{call EntradaEPTR(?,?,?)}");
        entrada.setString(1, a);
        entrada.setString(2, b);
        entrada.setString(3, c);
        entrada.execute();
    }

    public static Connection Conectar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static Connection Cone;
    private static PreparedStatement sentencia_preparada;
    private static ResultSet resultado;

    public static String bucarCi(String CodigoCurso) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("SELECT ID_CURSO FROM CURSO WHERE ID_CURSO=?");
            sentencia_preparada.setString(1, CodigoCurso);
            resultado = sentencia_preparada.executeQuery();

            if (resultado.next()) {
                mensaje = "El Codigo Existe";
            } else {
                mensaje = "El Codigo no Existe";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;
    }

    public static String bucarCedula(String Cedula) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("SELECT CI FROM PERSONA WHERE CI=?");
            sentencia_preparada.setString(1, Cedula);
            resultado = sentencia_preparada.executeQuery();

            if (resultado.next()) {
                mensaje = "La Cedula ya Existe";
            } else {
                mensaje = "La Cedula no Existe";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;
    }

    public static String bucarCedulaUsuario(String Ciusuario) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("SELECT CI FROM USUARIO WHERE CI=?");
            sentencia_preparada.setString(1, Ciusuario);
            resultado = sentencia_preparada.executeQuery();

            if (resultado.next()) {
                mensaje = "La Cedula ya Existe";
            } else {
                mensaje = "La Cedula no Existe";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;
    }

    public static String bucarCedulaestu(String CedulaeEstu) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("SELECT CI FROM ESTUDIANTE WHERE CI=?");
            sentencia_preparada.setString(1, CedulaeEstu);
            resultado = sentencia_preparada.executeQuery();

            if (resultado.next()) {
                mensaje = "La Cedula ya Existe";
            } else {
                mensaje = "La Cedula no Existe";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;
    }

    public static String buscarUsuario(String Reusuario) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("SELECT USUARIO FROM USUARIO WHERE USUARIO=?");
            sentencia_preparada.setString(1, Reusuario);
            resultado = sentencia_preparada.executeQuery();

            if (resultado.next()) {
                mensaje = "Usuario ya Existe";
            } else {
                mensaje = "Usuario no Existe";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;

    }

    public static String ChequearSeccion(String Reusuario) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("SELECT NOMBRE FROM SECCION WHERE NOMBRE=? AND STATUS='DISPONIBLE'");
            sentencia_preparada.setString(1, Reusuario);
            resultado = sentencia_preparada.executeQuery();

            if (resultado.next()) {
                mensaje = "Seccion disponible";
            } else {
                mensaje = "Seccion no aperturada o finalizada";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;

    }

    public static String ChequearNSeccion(String Reusuario) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("SELECT NOMBRE FROM SECCION WHERE NOMBRE=?");
            sentencia_preparada.setString(1, Reusuario);
            resultado = sentencia_preparada.executeQuery();

            if (resultado.next()) {
                mensaje = "Seccion ya registrada";
            } else {
                mensaje = "Seccion disponible";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;

    }

    public static String bucarCedulaE(String Cedula) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("SELECT CI FROM EMPLEADO WHERE CI=?");
            sentencia_preparada.setString(1, Cedula);
            resultado = sentencia_preparada.executeQuery();

            if (resultado.next()) {
                mensaje = "La Cedula ya Existe";
            } else {
                mensaje = "La Cedula no Existe";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;
    }

    public static String bucarCiProfesor(String cip) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("SELECT CI FROM PROFESOR WHERE CI=?");
            sentencia_preparada.setString(1, cip);
            resultado = sentencia_preparada.executeQuery();

            if (resultado.next()) {
                mensaje = "La Cedula ya Existe";
            } else {
                mensaje = "El CI no Existe";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;
    }

    public static String bucarCedulaestuINS(String CedulaeEstu) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("select CI from ESTUDIANTE_ESTA_INS_C WHERE CI=?");
            sentencia_preparada.setString(1, CedulaeEstu);
            resultado = sentencia_preparada.executeQuery();

            if (resultado.next()) {
                mensaje = "La Cedula ya Existe";
            } else {
                mensaje = "La Cedula no Existe";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;
    }

    public static String bucarCiAdmin(String ciA) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("SELECT CI FROM ADMINISTRATIVO WHERE CI=?");
            sentencia_preparada.setString(1, ciA);
            resultado = sentencia_preparada.executeQuery();

            if (resultado.next()) {
                mensaje = "La Cedula ya Existe";
            } else {
                mensaje = "El CI no Existe";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;
    }

    public static String BuscarFactura(String id) {
        String mensaje, mensaje2;

        mensaje = null;
        mensaje2 = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("select ESTUDIANTE_ESTA_INS_C.ID_INSCRIP from ESTUDIANTE_ESTA_INS_C JOIN ESTU_RE_INSCRIPCION ON ESTUDIANTE_ESTA_INS_C.CI=ESTU_RE_INSCRIPCION.CI WHERE ESTUDIANTE_ESTA_INS_C.ID_INSCRIP=?");
            sentencia_preparada.setString(1, id);
            resultado = sentencia_preparada.executeQuery();

            if (resultado.next()) {
                mensaje = "Factura Utilizada";

            } else {
                mensaje = "Factura Disponible";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;

    }

    public static String BuscarEFactura(String id) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("select ID_INSCRIP from ESTU_RE_INSCRIPCION WHERE ID_INSCRIP=?");
            sentencia_preparada.setString(1, id);
            resultado = sentencia_preparada.executeQuery();

            if (resultado.next()) {
                mensaje = "Factura Existe";
            } else {
                mensaje = "Factura no Existe";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;
    }

    public static String ChequearSeccionReporteAprobados(String NombreS) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("SELECT NOMBRE FROM SECCION WHERE NOMBRE=? AND STATUS='Finalizada'");
            sentencia_preparada.setString(1, NombreS);
            resultado = sentencia_preparada.executeQuery();

            if (resultado.next()) {
                mensaje = "Seccion Finalizada";
            } else {
                mensaje = "Seccion Disponible o ya Iniciada";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;

    }

    public static String BuscarFacturaU(String id, String ci) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("select ID_INSCRIP from ESTU_RE_INSCRIPCION WHERE"
                    + " ID_INSCRIP=? AND CI =?");
            sentencia_preparada.setString(1, id);
            sentencia_preparada.setString(2, ci);
            resultado = sentencia_preparada.executeQuery();
            if (resultado.next()) {
                mensaje = "Factura encontrada";
            } else {
                mensaje = "Factura no perteneca a estudiante";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;
    }

    /*public static String buscarEstudianteSecciom(String nombreseccion, String ci) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("select CI,NOMBRE FROM ESTUDIANTE_ESTA_INS_C WHERE NOMBRE=? AND CI =?");
            sentencia_preparada.setString(1, nombreseccion);
            sentencia_preparada.setString(2, ci);
            resultado = sentencia_preparada.executeQuery();
            if (resultado.next()) {
                mensaje = "CI ENCONTRADA";
            } else {
                mensaje = "CI NO ENCONTRADA";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;
    }*/
    
    
    
public static String recuperarContra(String a, String b) {
        String mensaje;
        mensaje = null;
        try {
            Cone = Conexion.getConexion();
            sentencia_preparada = Cone.prepareStatement("select USUARIO,CONTRASEÑA from USUARIO where PREGUNTA_SECRETA=? AND RESPUESTA_sEC=?");
            sentencia_preparada.setString(1, a);
            sentencia_preparada.setString(2, b);
            resultado = sentencia_preparada.executeQuery();
            if (resultado.next()) {
                mensaje = "ENCONTRADA";
            } else {
                mensaje = "NO ENCONTRADA";
            }
            Cone.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                Cone.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return mensaje;
    }


public static void Backup(String a, String b) throws SQLException {
    
       
        CallableStatement Backup = Conexion.getConexion().prepareCall("{call CREARBACKUP(?,?)}");
        Backup.setString(1, a);
        Backup.setString(2, b);
        Backup.execute();
    }

public static void alterar(String a) throws SQLException {
        CallableStatement Buscar = Conexion.MasterConexion().prepareCall("{call RESTORE1(?)}");
        Buscar.setString(1, a);
        Buscar.execute();
}
public static void DESCONECTAR(String a) throws SQLException {
        CallableStatement DISC = Conexion.MasterConexion().prepareCall("{call KILLTHEM(?)}");
        DISC.setString(1, a);
        DISC.execute();
}

public static void Restaurar(String a,String b) throws SQLException {
        CallableStatement restaurar = Conexion.MasterConexion().prepareCall("{call RESTAURARDEF(?,?)}");
        restaurar.setString(1, a);
        restaurar.setString(2, b);
        restaurar.execute();

}
public static void restore(String a) throws SQLException {
    
        CallableStatement rest = Conexion.MasterConexion().prepareCall("{call RESTORE3(?)}");
        rest.setString(1, a);
        rest.execute();
}

}


