/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vista.FrmEditarSolicitud;
import configSQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
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
//            sentenciaPL_SQL = "{ call MODIFICAR_SOLICITUD(?,?,?,?,?,?)}";
//            nuevaConeccion = ConexionDB.conectar();
//            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
//            callableStatement.setString(1, ventanaSolicitud.txtNombre.getText());
//            callableStatement.setString(2, ventanaSolicitud.txtNombre.getText());
//            callableStatement.setString(3, ventanaSolicitud.txtCorreo.getText());
//            callableStatement.setString(4, ventanaSolicitud.txtTelefono.getText());
//            callableStatement.setString(5, ventanaSolicitud.txtDireccion.getText());
//            callableStatement.registerOutParameter(6, java.sql.Types.INTEGER);
//            callableStatement.executeQuery();
//            regAfectados = callableStatement.getInt(6);
//            nuevaConeccion.close();
//            callableStatement.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e + "No se logró actualizar");
//            return regAfectados;
//        }
//        return regAfectados;
//    }
}
