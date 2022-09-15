package controlador;

/**
 * Este es el modelo que moneja los datos del vehiculo entre la BD y el
 * Programa.
 *
 * @author Grupo E
 */
import configSQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import modelo.Vehiculo;
import oracle.jdbc.OracleTypes;

public class VehiculoDB {

    private ArrayList<Vehiculo> listarVehiculo;
    private static ResultSet resultSet;
    private String ejecutarSentencia;
    private Connection nuevaConeccion;
    private CallableStatement callableStatement;
    private Vehiculo vehiculo;

    private int resultado;

    /**
     * Este metodo permite listar los vehiculos de un propietario en especifico.
     *
     * @param cs {@link String} recibe la cedula para buscar los vehiculos
     * relacionados
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public ArrayList<Vehiculo> listarVehiculo(String cedula) {
        listarVehiculo = new ArrayList();

        ejecutarSentencia = "{ call VER_TABLA_VEHICULO(?,?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);
            callableStatement.setString(1, cedula);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(2);
            while (resultSet.next()) {
                vehiculo = new Vehiculo();
                vehiculo.setPlaca(resultSet.getString(1));
                vehiculo.setMarca(resultSet.getString(2));
                vehiculo.setModelo(resultSet.getString(3));
                vehiculo.setAnioVehiculo(resultSet.getString(4));
                vehiculo.setId_tipo_impuesto(resultSet.getInt(5));
                listarVehiculo.add(vehiculo);
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error en listado");
        }
        return listarVehiculo;
    }

    /**
     * Este metodo permite listar un vehiculo en especifico de un propietario.
     *
     * @param cs {@link Vehiculo} Modelo que contiene la informcacion de los
     * vehiculos. relacionados
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public Vehiculo verVehiculo(Vehiculo vehiculo) {
        ejecutarSentencia = "{ call VER_VEHICULO (?,?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);
            callableStatement.setString(1, vehiculo.getPlaca());
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(2);
            while (resultSet.next()) {

                vehiculo.setPlaca(resultSet.getString(1));
                vehiculo.setMarca(resultSet.getString(2));
                vehiculo.setModelo(resultSet.getString(3));
                vehiculo.setAnioVehiculo(resultSet.getString(4));
                vehiculo.setId_tipo_impuesto(resultSet.getInt(5));
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error en listado");
        }
        return vehiculo;
    }

    /**
     * Este metodo permite modificar el vehiculo de un propietario en
     * especifico.
     *
     * @param cs {@link Vehiculo} Modelo que contiene la informcacion de los
     * vehiculos.
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */

    public int modificarVehiculo(Vehiculo vehiculo) {
        resultado = 0;
        ejecutarSentencia = "{ call MODIFICAR_VEHICULO(?,?,?,?,?,?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            CallableStatement cs = nuevaConeccion.prepareCall(ejecutarSentencia);

            cs.setString(1, vehiculo.getPlaca());
            cs.setString(2, vehiculo.getMarca());
            cs.setString(3, vehiculo.getModelo());
            cs.setString(4, vehiculo.getAnioVehiculo());
            cs.setInt(5, vehiculo.getId_tipo_impuesto());
            cs.registerOutParameter(6, java.sql.Types.INTEGER);
            cs.execute();
            resultado = cs.getInt(6);
            nuevaConeccion.close();
        } catch (Exception e) {
            new ValidarCampos().mensajeError("Error: " + e);
        }
        return resultado;
    }

    // Ingresa el vehiculo en la BD 
    // LLena el comboBox con los datos que se encuentra en la BD.
    public void llenarTipoImpuesto(JComboBox tipoImpuesto) {
        ejecutarSentencia = "{ call OBTENER_TIPO_VEHICULO(?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(1);
            while (resultSet.next()) {
                tipoImpuesto.addItem(resultSet.getString("DESCRIPCION"));
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error en llenar tipo impuesto");
        }
    }

    //Verifica si la placa existe n la BD
    public String verificarSiExistePlaca(String placa) {
        try {
            ejecutarSentencia = "{call VERIFICAR_SI_EXISTE_VEHICULO(?,?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);
            callableStatement.setString(1, placa);
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.executeQuery();
            placa = callableStatement.getString(2);
            nuevaConeccion.close();
            callableStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return placa;
    }
}
