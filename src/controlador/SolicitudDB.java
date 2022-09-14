/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vista.FrmEditarSolicitud;
import Vista.PnlEstadoSolicitud;
import configSQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.Solicitud;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author ximen
 */
public class SolicitudDB {
    private Connection nuevaConeccion;
    private String sentenciaPL_SQL;
    private CallableStatement callableStatement;
    private ResultSet resultSet;
    private int regAfectados;
    private Solicitud solicitud;
    private int resultado;
    private String ejecutarSentencia;
    ArrayList<Solicitud> arraySolicitud;
            
     public SolicitudDB() {
        arraySolicitud = new ArrayList();
    }     
     
    public int agregarSolicitud( Solicitud solicitud){
        resultado = 0;
        ejecutarSentencia= "{ call AGREGAR_SOLICITUD(?,?,?,?,?,?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            CallableStatement cs = nuevaConeccion.prepareCall(ejecutarSentencia);
            
            cs.setString(1, solicitud.getId());
            cs.setString(2, solicitud.getTipo());
            cs.setString(3, solicitud.getDescripcion());
            cs.setString(4, solicitud.getEstado());
            cs.setString(5, solicitud.getId_Factura());
            cs.registerOutParameter(6,java.sql.Types.INTEGER);

            cs.execute();
            resultado = cs.getInt(6);
            nuevaConeccion.close();
            mensajeConfirmacion("Solicitud insertada con exito.");
        } catch (Exception e) {
            mensajeConfirmacion("La solicitud no a sido insertada.Error: " +e);
        }
        return resultado;
    }
    private void mensajeConfirmacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public int obternerIdMaximoSoli() {
        int idEmpleado = 0;

        try {
            sentenciaPL_SQL = "{ call OBTENER_ID_MAX_SOLICITUD(?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(1);
            while (resultSet.next()) {
                idEmpleado = Integer.valueOf(resultSet.getString(1));
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (Exception e) {
            System.out.println(e + " Error al generar id solicitud");
        }
        return idEmpleado;
    }
//   public int modificarSolicitud(FrmEditarSolicitud ventanaSolicitud) {
//        regAfectados = 0;
//        try {
//            sentenciaPL_SQL = "{ call MODIFICAR_SOLICITUD(?,?)}";
//            nuevaConeccion = ConexionDB.conectar();
//            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
//            callableStatement.setString(1, ventanaSolicitud.cmbEstado.getItemAt(resultado));
//            callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);
//            callableStatement.executeQuery();
//            regAfectados = callableStatement.getInt(2);
//            nuevaConeccion.close();
//            callableStatement.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e + "No se logró actualizar");
//            return regAfectados;
//        }
//        return regAfectados;
//    }
//   public void visualizarSolicitud(JTable tablaSolicitudes, FrmEditarSolicitud ventanaSolicitud) {
//        int fila = tablaSolicitudes.getSelectedRow();
//        String id = tablaSolicitudes.getValueAt(fila, 0).toString();
//
//        try {
//            sentenciaPL_SQL = "{ call VER_SOLICITUD(?,?)}";
//            nuevaConeccion = ConexionDB.conectar();
//            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
//            callableStatement.setString(1, id);
//            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
//            callableStatement.executeQuery();
//            resultSet = (ResultSet) callableStatement.getObject(2);
//            while (resultSet.next()) {
//                solicitud.setId(resultSet.getString("ID_SOLICITUD"));
//                solicitud.setTipo(resultSet.getString("TIPO"));
//                solicitud.setDescripcion(resultSet.getString("DESCRIPCION"));
//                solicitud.setEstado(resultSet.getString("ESTADO"));
//                
//               
//            }
//            ventanaSolicitud.getLblNumeroSolicitud().setText(solicitud.getId());
//            ventanaSolicitud.getLblTipoSolicitud().setText(solicitud.getTipo());
//            ventanaSolicitud.getTxtaDescripcion().setText(solicitud.getDescripcion());
//            ventanaSolicitud.getCmbEstado().setText(solicitud.getEstado());
//            
//            nuevaConeccion.close();
//            callableStatement.close();
//            resultSet.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//    public void llenarEditarSolicitud(JTable tablaSolicitudes, FrmEditarSolicitud ventanaSolicitud) {
//        int fila = tablaSolicitudes.getSelectedRow();
//        String id = tablaSolicitudes.getValueAt(fila, 0).toString();
//
//        try {
//            sentenciaPL_SQL = "{ call VER_SOLICITUD(?,?)}";
//            nuevaConeccion = ConexionDB.conectar();
//            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
//            callableStatement.setString(1, id);
//            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
//            callableStatement.executeQuery();
//            resultSet = (ResultSet) callableStatement.getObject(2);
//            while (resultSet.next()) {
//                solicitud.setId(resultSet.getString("ID_SOLICITUD"));
//                solicitud.setTipo(resultSet.getString("TIPO"));
//                solicitud.setDescripcion(resultSet.getString("DESCRIPCION"));
//                solicitud.setEstado(resultSet.getString("ESTADO"));
//            }
//            ventanaSolicitud.lblNumeroSolicitud.setText(solicitud.getId());
//            ventanaSolicitud.lblTipoSolicitud.setText(solicitud.getTipo());
//            ventanaSolicitud.txtaDescripcion.setText(solicitud.getDescripcion());
//            ventanaSolicitud.cmbEstado.setSelectedItem(solicitud.getEstado());
//            nuevaConeccion.close();
//            callableStatement.close();
//            resultSet.close();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//     public Solicitud obtenerSolicitud(String id) {
//        solicitud = new Solicitud();
//        try {
//            sentenciaPL_SQL = "{ call VER_SOLICITUD(?,?)}";
//            nuevaConeccion = ConexionDB.conectar();
//            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
//            callableStatement.setString(1, id);
//            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
//            callableStatement.executeQuery();
//            resultSet = (ResultSet) callableStatement.getObject(2);
//            while (resultSet.next()) {
//                solicitud.setId(resultSet.getString("ID_SOLICITUD"));
//                solicitud.setTipo(resultSet.getString("TIPO"));
//                solicitud.setDescripcion(resultSet.getString("DESCRIPCION"));
//                solicitud.setEstado(resultSet.getString("ESTADO"));
//                
//            }
//            nuevaConeccion.close();
//            callableStatement.close();
//            resultSet.close();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Solicitud no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//        return solicitud;
//    }
     
     public ArrayList<Solicitud> ListSolicitud() {
        arraySolicitud = new ArrayList();

        try {            
            sentenciaPL_SQL = "{ call VER_TABLA_SOLICITUD(?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(1);
            while (resultSet.next()) {
                solicitud = new Solicitud();
                solicitud.setId(resultSet.getString("ID_SOLICITUD"));
                solicitud.setTipo(resultSet.getString("TIPO"));
                solicitud.setDescripcion(resultSet.getString("DESCRIPCION"));
                solicitud.setEstado(resultSet.getString("ESTADO"));
                arraySolicitud.add(solicitud);
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error en listado");
        }
        return arraySolicitud;
    }
}

