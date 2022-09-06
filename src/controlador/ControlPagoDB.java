package controlador;

/**
 * Este es el modelo que maneja los datos del control de pagos que se encuentra
 * la BD y la interfaz.
 *
 * @author Grupo E
 */

import configSQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;
import modelo.Factura;

public class ControlPagoDB {
    private Connection nuevaConeccion;
    private String sentenciaPL_SQL;
    private CallableStatement callableStatement;
    private ResultSet resultSet;
    private Factura factura;

    ConexionDB cnx = new ConexionDB();
    Connection conexion = cnx.conectar();
    
    ArrayList<Factura> arrayFactura;
    
    public ControlPagoDB() {
        arrayFactura = new ArrayList();
    }
    
    /*
    * Este metodo extrae la lista de los pagos para presentarlo en la tabla listarControlPago
    */
    public ArrayList<Factura> ListFactura() {
        arrayFactura = new ArrayList();
        try {
            sentenciaPL_SQL = "{ call VER_TABLA_CONTROL_PAGO3(?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(1);
            while (resultSet.next()) {
                factura = new Factura();
                factura.setCedula(resultSet.getString("CEDULA"));
                factura.setNombre(resultSet.getString("NOMBRE"));
                factura.setFecha_emision(resultSet.getDate("FECHA_EMISION"));
                factura.setFecha_vencimiento(resultSet.getDate("FECHA_VENCIMIENTO"));
                factura.setFecha_pago_factura(resultSet.getDate("FECHA_PAGO_FACTURA"));
                factura.setMulta(resultSet.getString("MULTA"));
                factura.setValor_recargo(resultSet.getFloat("VALOR_RECARGO"));
                factura.setTipo_impuesto(resultSet.getString("DESCRIPCION"));
                factura.setDias_restantes_pago(resultSet.getInt("DIAS_RESTANTES"));
                factura.setId_factura(resultSet.getString("ID_FACTURA"));
                factura.setValor_total(resultSet.getFloat("VALOR_TOTAL"));
                arrayFactura.add(factura);
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error en listado");
        }
        System.out.println(arrayFactura);
        return arrayFactura;
    }
    
    /*
    * Este metodo actualiza la multa del propietario presentado en la tablaControlPago
    */
    public void ActualizarEstadoMulta(String cod_factura, float recargo){
        sentenciaPL_SQL= "{call ACTUALIZAR_MULTA_FACTURA('"+cod_factura+"',"+recargo+")}";
        try {    
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.executeQuery();
            nuevaConeccion.close();
            callableStatement.close();  
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error en listado");
        }
    }
    
}
