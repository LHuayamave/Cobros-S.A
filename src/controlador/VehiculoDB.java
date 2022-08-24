package controlador;
/**
* Este es el modelo que moneja los datos del vehiculo entre la BD y el Programa.
* @author Grupo E
*/

import configSQL.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import modelo.Vehiculo;


public class VehiculoDB {

    private static PreparedStatement sentencia_preparada;
    private static ResultSet resultSet;
    Statement statement;
    private Connection nuevaConexion;
    private Vehiculo vehiculo;
    private ArrayList<Vehiculo> Vehiculo = new ArrayList();
    private int resultado;
    private String ejecutarSentencia;
    
    // Ingresa el vehiculo en la BD 
    public int registrarVehiculo(String placa, String marca, String modelo, 
            String anioVehiculo, String idTipoImpuesto) {
        resultado = 0;
        ejecutarSentencia = ("INSERT INTO ADMIN_COBROS.VEHICULO( PLACA, MARCA, MODELO,"
                + "ANIO_VEHICULO, ID_TIPO_IMPUESTO) VALUES(?,?,?,?,?)");
        try {
            nuevaConexion = ConexionDB.conectar();
            sentencia_preparada = nuevaConexion.prepareStatement(ejecutarSentencia);
            sentencia_preparada.setString(1, placa);
            sentencia_preparada.setString(2, marca);
            sentencia_preparada.setString(3, modelo);
            sentencia_preparada.setString(4, anioVehiculo);
            sentencia_preparada.setString(5, idTipoImpuesto);
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
            nuevaConexion.close();
        } catch (Exception e) {
            System.out.println(e);       
        }
        return resultado;
    }
    // LLena el comboBox con los datos que se encuentra en la BD.
    public  void llenarTipoImpuesto(JComboBox tipoImpuesto){
        try {
            nuevaConexion = ConexionDB.conectar();
            statement = nuevaConexion.createStatement();
            resultSet = statement.executeQuery("SELECT DESCRIPCION FROM TIPO_IMPUESTO");
            while (resultSet.next()) {
                tipoImpuesto.addItem(resultSet.getString("DESCRIPCION"));          
            }
            statement.close();
            nuevaConexion.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error en llenar tipo impuesto");
        }
    }
    // Modifica la informacion del vehiculo 
    public int modificarVehiculo(String placa, String marca, String modelo, String anioVehiculo,int idTipoImpuesto) {
        resultado = 0;
        ejecutarSentencia ="UPDATE ADMIN_COBROS.VEHICULO SET "
                    + "MARCA='" + marca +"'"
                    + ",MODELO='" + modelo+"'"
                    + ",ANIO_VEHICULO='"+ anioVehiculo+"'"
                    + ",ID_TIPO_IMPUESTO='" + idTipoImpuesto+"'"
                    + " WHERE PLACA='" + placa+"'";
        try {
            nuevaConexion = ConexionDB.conectar();
            sentencia_preparada = nuevaConexion.prepareStatement(ejecutarSentencia);
            resultado = sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
            nuevaConexion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "No se logró actualizar");
        }
        return resultado;
    }
    
//    // Obtiene la informcaion del vechiculo con la Placa
//    public Vehiculo obtenerVehiculo(String placa) { // EN USO
//        vehiculo = new Vehiculo(); //connection
//        nuevaConexion = ConexionDB.conectar();
//        try {
//            String sentenciaBuscarVehiculo = ("SELECT * FROM ADMIN_COBROS.VEHICULO WHERE PLACA="+"'" + placa+"'");
//            sentencia_preparada = nuevaConexion.prepareStatement(sentenciaBuscarVehiculo);
//            resultSet = sentencia_preparada.executeQuery();
//            if (resultSet.next()) {
//                vehiculo.setPlaca(placa);
//                vehiculo.setMarca(resultSet.getString("MARCA"));
//                vehiculo.setModelo(resultSet.getString("MODELO"));
//                vehiculo.setAnioVehiculo(resultSet.getString("ANIO_VEHICULO"));
//                vehiculo.setId_tipo_impuesto(resultSet.getString("ID_TIPO_IMPUESTO"));
//            }
//            nuevaConexion.close();
//            sentencia_preparada.close();
//            resultSet.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }        
//        return vehiculo;
//    }
    //Verifica si la placa existe n la BD
    public String verificarSiExistePlaca(String placa){
        try {
            String sentenciaBuscarVehiculo = ("SELECT * FROM ADMIN_COBROS.VEHICULO WHERE PLACA="+"'" + placa+"'");
            nuevaConexion= ConexionDB.conectar();
            statement = nuevaConexion.createStatement();
            resultSet = statement.executeQuery(sentenciaBuscarVehiculo);
            placa = null;
            while (resultSet.next()) {
                placa = resultSet.getString("PLACA");
            }  
            statement.close();
            nuevaConexion.close();
            resultSet.close();
        } catch (Exception e) {
            System.out.println(e);  
        }    
        return placa;
    }
}
