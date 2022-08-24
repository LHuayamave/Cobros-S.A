package controlador;
/**
 * Este es el modelo que maneja los datos de los propietarios que se encuentra 
 * la BD y la interfaz.
 *
 * @author Grupo E
 */
import configSQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import modelo.CuentaBancaria;
import modelo.Propietario;
import modelo.Vehiculo;
import oracle.jdbc.OracleTypes;

public class PropietarioDB {
    
    private Connection nuevaConeccion;
    private String sentenciaPL_SQL;
    private CallableStatement callableStatement;
    private ResultSet resultSet;
    private int regAfectados;

    private static PreparedStatement sentencia_preparada;
    private Statement statement;
    private Connection nuevaConexion;
    private ArrayList<Propietario> listaPropietarios;
    private Propietario propietario;
    private ValidarCampos validarCampos;
    private int resultado;
    private String ejecutarSentencia;
    
    
    public PropietarioDB() {};
    /*
    * Este metodo extrae la lista de los propietarios para presentarlo en la tabla listarEmpleado
    */
    public ArrayList<Propietario> listarPropietarios() { 
        listaPropietarios = new ArrayList();
        ejecutarSentencia= "{ call VER_TABLA_PROPIETARIO(?)}";
        try {
            
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);
            
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet)callableStatement.getObject(1);  
            while (resultSet.next()) {
                propietario = new Propietario();
                propietario.setCedula(resultSet.getString("CEDULA"));
                propietario.setNombre(resultSet.getString("NOMBRE"));
                propietario.setCorreo(resultSet.getString("CORREO"));
                propietario.setTelefono(resultSet.getString("TELEFONO"));
                propietario.setDomiciliado(Boolean.valueOf(resultSet.getString("DOMICILIADO")));
                listaPropietarios.add(propietario); 
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();   
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error en listado");
        }
        return listaPropietarios;
    }
    /*
    * Este metodo permite obtener el propietario apartir de la cedula.
    */
//    public Propietario obtenerPropietario(String cedula) { 
//        propietario = new Propietario();
//        try {
//            nuevaConexion= ConexionDB.conectar();
//            statement = nuevaConexion.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM ADMIN_COBROS.PROPIETARIO WHERE CEDULA="+ cedula);
//            while (resultSet.next()) {
//                propietario.setCedula(resultSet.getString("CEDULA"));
//                propietario.setNombre(resultSet.getString("NOMBRE"));
//                propietario.setCorreo(resultSet.getString("CORREO"));
//                propietario.setTelefono(resultSet.getString("TELEFONO"));
//                propietario.setDireccion(resultSet.getString("DIRECCION"));
//                propietario.setFechaNacimiento(resultSet.getDate("FECHA_NAC"));
//                //propietario.setPlaca(resultSet.getString("PLACA"));
//                propietario.setId_estado_propietario(resultSet.getInt("ID_ESTADO_PROPIETARIO"));
//            }  
//            statement.close();
//            nuevaConexion.close();
//            resultSet.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return propietario ;
//    }
    /*
    * Este metodo permite registrar a los propietarios. en la BD
    */
    public int registrarPropietarioNoDomiciliado( Propietario propietario, Vehiculo vehiculo){
        resultado = 0;
        ejecutarSentencia= "{ call AGREGAR_PROPIETARIO_NO_DOMICILIADO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            nuevaConexion = ConexionDB.conectar();
            CallableStatement cs = nuevaConexion.prepareCall(ejecutarSentencia);
            
            cs.setString(1, propietario.getCedula());
            cs.setString(2, propietario.getNombre());
            cs.setString(3, propietario.getCorreo());
            cs.setString(4, propietario.getTelefono());
            cs.setString(5, propietario.getDireccion());
            cs.setString(6, new ValidarCampos().validarFormatoFecha(propietario.getFechaNacimiento()));
            cs.setInt(7, propietario.obtenerMesPago()); 
            cs.setInt(8, propietario.getId_estado_propietario()); 
            cs.setString(9, null);// cuenta bancaria
            cs.setString(10,Boolean.toString (propietario.getDomiciliado())); // domiciliado
            cs.setString(11,vehiculo.getPlaca());
            cs.setString(12,vehiculo.getMarca());
            cs.setString(13,vehiculo.getModelo());
            cs.setString(14,vehiculo.getAnioVehiculo());
            cs.setInt(15,vehiculo.getId_tipo_impuesto());
            cs.registerOutParameter(16,java.sql.Types.INTEGER);

            cs.execute();
            resultado = cs.getInt(16);
            nuevaConexion.close();
            mensajeConfirmacion("El ropietario insertado con exito.");
        } catch (Exception e) {
            mensajeConfirmacion("El propietario no a sido insertado.Error: " +e);
        }
        return resultado;
    }
    public int registrarPropietarioDomiciliado( CuentaBancaria cuentaBancaria, Propietario propietario, 
            Vehiculo vehiculo){
        resultado = 0;
        ejecutarSentencia= "{ call AGREGAR_PROPIETARIO_DOMICILIADO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            nuevaConexion = ConexionDB.conectar();
            CallableStatement cs = nuevaConexion.prepareCall(ejecutarSentencia);
            
            cs.setString(1, cuentaBancaria.getNumeroCuentaBancaria());
            cs.setString(2,cuentaBancaria.getCvv());
            cs.setInt(3, cuentaBancaria.getMes());
            cs.setInt(4, cuentaBancaria.getAnio());
            cs.setInt(5, cuentaBancaria.getIdTipoCuenta());
            cs.setFloat(6, cuentaBancaria.getSaldo());
            cs.setInt(7, cuentaBancaria.getIdBanco());
            
            cs.setString(8, propietario.getCedula());
            cs.setString(9, propietario.getNombre());
            cs.setString(10, propietario.getCorreo());
            cs.setString(11, propietario.getTelefono());
            cs.setString(12, propietario.getDireccion());
            cs.setString(13, new ValidarCampos().validarFormatoFecha(propietario.getFechaNacimiento()));
            cs.setInt(14, propietario.obtenerMesPago()); 
            cs.setInt(15, propietario.getId_estado_propietario()); 
            cs.setString(16,"true"); // domiciliado
            
            cs.setString(17,vehiculo.getPlaca());
            cs.setString(18,vehiculo.getMarca());
            cs.setString(19,vehiculo.getModelo());
            cs.setString(20,vehiculo.getAnioVehiculo());
            cs.setInt(21,vehiculo.getId_tipo_impuesto());
            cs.registerOutParameter(22,java.sql.Types.INTEGER);

            cs.execute();
            resultado = cs.getInt(22);
            nuevaConexion.close();
            mensajeConfirmacion("El ropietario insertado con exito.");
        } catch (Exception e) {
            System.out.println(e);
            mensajeConfirmacion("El propietario no a sido insertado.Error: " +e);
        }
        return resultado;
    }
    
    public boolean validarEdad(Date date){
        validarCampos = new ValidarCampos();
        return validarCampos.validarEdad(date);
    }

    public int modificarPropietario(Propietario propietario) {
        resultado = 0;
        ejecutarSentencia ="UPDATE ADMIN_COBROS.PROPIETARIO SET "
            + "NOMBRE='" + propietario.getNombre()+"'"
            + ",CORREO='" + propietario.getCorreo()+"'"
            + ",TELEFONO='"+ propietario.getTelefono()+"'"
            + ",DIRECCION='" + propietario.getDireccion()+"'"
            + ",FECHA_NAC=TO_DATE('" + (java.sql.Date)propietario.getFechaNacimiento()
            +"', 'YYYY-MM-DD HH24:MI:SS')"
            //+ ",PLACA='" + propietario.getPlaca()+"'"
            + ",ID_ESTADO_PROPIETARIO='" +propietario.getId_estado_propietario()+"'"
            + " WHERE CEDULA=" + propietario.getCedula();
        
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
    
    public String verificarSiExistePropietario(String cedula){
        try {
            nuevaConexion= ConexionDB.conectar();
            statement = nuevaConexion.createStatement();
            resultSet = statement.executeQuery("SELECT CEDULA FROM ADMIN_COBROS.PROPIETARIO WHERE CEDULA="+ cedula);
            cedula = null;
            while (resultSet.next()) {
                cedula = resultSet.getString("CEDULA");
            }  
            statement.close();
            nuevaConexion.close();
            resultSet.close();
        } catch (Exception e) {
            System.out.println(e);  
        }
        return cedula;
    }

    public void llenarCmbEstadoPropietario(JComboBox cmbEstadoPropietario){
        try{
            sentenciaPL_SQL= "{call OBTENER_ESTADO_PROPIETARIO(?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet)callableStatement.getObject(1);
            while(resultSet.next()) {
                cmbEstadoPropietario.addItem(resultSet.getString(1));
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    public void llenarCmbTipoCtaBancaria (JComboBox cmbTipoCtaBancaria){
        try{
            sentenciaPL_SQL= "{call OBTENER_NOMBRE_TIPO_CUENTA(?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet)callableStatement.getObject(1);
            while(resultSet.next()) {
                cmbTipoCtaBancaria.addItem(resultSet.getString(1));
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    public void llenarCmbBanco(JComboBox cmbBanco){
        try{
            sentenciaPL_SQL= "{call OBTENER_NOMBRE_BANCO(?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet)callableStatement.getObject(1);
            while(resultSet.next()) {
                cmbBanco.addItem(resultSet.getString(1));
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }

    private void mensajeConfirmacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void consultarPropietarioDomiciliado(CuentaBancaria cuentaBancaria, Propietario propietario, String cedula) {
        try{
            sentenciaPL_SQL= "{call VER_PROPIETARIO_DOMICILIADO(?,?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.setString(1, cedula); 
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet)callableStatement.getObject(2);
            while(resultSet.next()) {
                propietario.setCedula(resultSet.getString(1));
                propietario.setNombre(resultSet.getString(2));
                propietario.setCorreo(resultSet.getString(3));
                propietario.setTelefono(resultSet.getString(4));
                propietario.setDireccion(resultSet.getString(5));
                propietario.setFechaNacimiento(resultSet.getDate(6));
                propietario.setMesPago(resultSet.getInt(7));  
                propietario.setId_estado_propietario(resultSet.getInt(8));
                propietario.setDomiciliado(Boolean.valueOf(resultSet.getString(9)));
                
                cuentaBancaria.setNumeroCuentaBancaria(resultSet.getString(10));
                cuentaBancaria.setCvv(resultSet.getString(11));
                cuentaBancaria.setMes(resultSet.getInt(12));
                cuentaBancaria.setAnio(resultSet.getInt(13));
                cuentaBancaria.setIdTipoCuenta(resultSet.getInt(14));
                cuentaBancaria.setSaldo(resultSet.getFloat(15)); 
                cuentaBancaria.setIdBanco(resultSet.getInt(16));     
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }

    public void consultarPropietarioNoDomiciliado(Propietario propietario, String cedula) {
        try{
            sentenciaPL_SQL= "{call VER_PROPIETARIO_NO_DOMICILIADO(?,?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.setString(1, cedula); 
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet)callableStatement.getObject(2);
            while(resultSet.next()) {
                propietario.setCedula(resultSet.getString(1));
                propietario.setNombre(resultSet.getString(2));
                propietario.setCorreo(resultSet.getString(3));
                propietario.setTelefono(resultSet.getString(4));
                propietario.setDireccion(resultSet.getString(5));
                propietario.setFechaNacimiento(resultSet.getDate(6));
                propietario.setMesPago(resultSet.getInt(7));  
                propietario.setId_estado_propietario(resultSet.getInt(8));
                propietario.setDomiciliado(Boolean.valueOf(resultSet.getString(9)));   
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        }catch(SQLException ex){
            System.out.println(ex);
        }

    }
}
