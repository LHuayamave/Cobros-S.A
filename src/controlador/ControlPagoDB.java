
package controlador;


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
    
    public ArrayList<Factura> ListFactura() {
        arrayFactura = new ArrayList();
        try {
            sentenciaPL_SQL = "{ call VER_TABLA_CONTROL_PAGO(?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(1);
            while (resultSet.next()) {
                factura = new Factura();
                factura.setCedula(resultSet.getString("CEDULA"));
                factura.setNombre(resultSet.getString("NOMBRE"));
                factura.setAviso_pago(resultSet.getString("DIAS"));
                factura.setMulta(resultSet.getString("MULTA"));
                factura.setAviso_recargo(resultSet.getString("RECARGO"));
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
}
