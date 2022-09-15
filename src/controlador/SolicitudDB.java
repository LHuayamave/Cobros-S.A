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
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Solicitud;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Grupo G
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

    /**
     * Este metodo permite agregar una solicitud a la BD.
     *
     * @param cs {@link Solicitud} modelo que contiene los datos de la
     * solicitud.
     * @throws SQLException como se esta usando una correccion a la BD se debe.
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public int agregarSolicitud(Solicitud solicitud) {
        resultado = 0;
        ejecutarSentencia = "{ call AGREGAR_SOLICITUD(?,?,?,?,?,?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            CallableStatement cs = nuevaConeccion.prepareCall(ejecutarSentencia);

            cs.setString(1, solicitud.getId());
            cs.setString(2, solicitud.getTipo());
            cs.setString(3, solicitud.getDescripcion());
            cs.setString(4, solicitud.getEstado());
            cs.setString(5, solicitud.getId_Factura());
            cs.registerOutParameter(6, java.sql.Types.INTEGER);

            cs.execute();
            resultado = cs.getInt(6);
            nuevaConeccion.close();
            mensajeConfirmacion("Solicitud insertada con exito.");
        } catch (Exception e) {
            mensajeConfirmacion("La solicitud no a sido insertada.Error: " + e);
        }
        return resultado;
    }

    private void mensajeConfirmacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    /**
     * Este metodo permite obtener el registro macximo de la tabla
     * solicitud,donde se puede definir el nuevo id..
     *
     * @param cs {@link Solicitud} modelo que contiene los datos de la
     * solicitud.
     * @throws SQLException como se esta usando una correccion a la BD se debe.
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
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

    /**
     * Este metodo permite listar las solicitudes que se va presentar en la
     * tabla.
     *
     * @param cs {@link Solicitud} modelo que contiene los datos de la
     * solicitud.
     * @throws SQLException como se esta usando una correccion a la BD se debe.
     * usar un try catch para atrapar algun error propio de la base de datos.
     */

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
