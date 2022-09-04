/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import configSQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
   
}
