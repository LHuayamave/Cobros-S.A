package controlador;

/**
 * Esta clase permite controlar la informacion entre el programa y la base de
 * datos, haciendo uso de modelos y procedimientos almacenados en la BD.
 *
 * @author Grupo E
 */
import Vista.FrmConsultarEmpleado;
import Vista.FrmEditarEmpleado;
import configSQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Empleado;
import oracle.jdbc.OracleTypes;

public class EmpleadoDB {

    private Connection nuevaConeccion;
    private String sentenciaPL_SQL;
    private CallableStatement callableStatement;
    private ResultSet resultSet;
    private int regAfectados;
    private Empleado empleado;
    ArrayList<Empleado> arrayEmpleado;

    public EmpleadoDB() {
        arrayEmpleado = new ArrayList();
    }

    /**
     * Este metodo permite la cantidad maxima de empleados registrado lo que
     * permite definir el id del nuevo empleado.
     *
     * @return rs regresa el numero maximo de registro del empleado
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public int obternerIdMaximo() {
        int idEmpleado = 0;

        try {
            sentenciaPL_SQL = "{ call OBTENER_ID_MAX_EMPLEADO(?)}";
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
        } catch (SQLException ex) {
            System.out.println(ex + " Error al generar id empleado");
        }
        return idEmpleado;
    }

    /**
     * Este metodo permite hacer registro del empleado que recibe a la base de
     * datos.
     *
     * @param cs {@link Empleado} recibe una instancia de un empleado.
     * @return rs regresa las filas afectadas para verificar si se ingreso
     * correctamente.
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public int agregarEmpleado(Empleado empleado) {
        regAfectados = 0;
        try {
            sentenciaPL_SQL = "{ call AGREGAR_EMPLEADO(?,?,?,?,?,?,?,?,?,?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.setString(1, empleado.getIdEmpleado());
            callableStatement.setString(2, empleado.getCedula());
            callableStatement.setString(3, empleado.getNombre());
            callableStatement.setString(4, empleado.getCorreo());
            callableStatement.setString(5, empleado.getContrasenia());
            callableStatement.setString(6, empleado.getTelefono());
            callableStatement.setString(7, empleado.getDireccion());
            callableStatement.setString(8, new ValidarCampos().validarFormatoFecha(empleado.getFechaNacimiento()));
            callableStatement.setString(9, empleado.getIdTrabajo());
            callableStatement.registerOutParameter(10, java.sql.Types.INTEGER);
            callableStatement.executeQuery();
            regAfectados = callableStatement.getInt(10);
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Empleado no agregado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return regAfectados;

    }

    /**
     * Este metodo permite obtener el registro del un empleado de la BD mediante
     * el uso de una cedula.
     *
     * @param cs {@link String} recibe la cedula como cadena de caracteres.
     * @return rs regresa el empleado que se solicita.
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public Empleado obtenerEmpleado(String cedula) {
        empleado = new Empleado();
        try {
            sentenciaPL_SQL = "{ call VER_EMPLEADO(?,?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.setString(1, cedula);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(2);
            while (resultSet.next()) {
                empleado.setIdEmpleado(resultSet.getString("ID_EMPLEADO"));
                empleado.setCedula(cedula);
                empleado.setNombre(resultSet.getString("NOMBRE"));
                empleado.setCorreo(resultSet.getString("CORREO"));
                empleado.setContrasenia(resultSet.getString("CONTRASENIA"));
                empleado.setTelefono(resultSet.getString("TELEFONO"));
                empleado.setDireccion(resultSet.getString("DIRECCION"));
                empleado.setFechaNacimiento(resultSet.getDate("FECHA_NAC"));
                empleado.setIdTrabajo(resultSet.getString("ID_TRABAJO"));
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return empleado;
    }

    /**
     * Este metodo permite obtener los registros de los empleados ´para llenar
     * las tablas que se presentan.
     *
     * @return rs regresa una lista de empleados para rellenar las tablas.
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public ArrayList<Empleado> ListEmpleado() {
        arrayEmpleado = new ArrayList();
        try {
            sentenciaPL_SQL = "{ call VER_TABLA_EMPLEADO(?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(1);
            while (resultSet.next()) {
                empleado = new Empleado();
                empleado.setIdEmpleado(resultSet.getString("ID_EMPLEADO"));
                empleado.setCedula(resultSet.getString("CEDULA"));
                empleado.setNombre(resultSet.getString("NOMBRE"));
                empleado.setCorreo(resultSet.getString("CORREO"));
                empleado.setContrasenia(resultSet.getString("CONTRASENIA"));
                empleado.setTelefono(resultSet.getString("TELEFONO"));
                empleado.setDireccion(resultSet.getString("DIRECCION"));
                empleado.setFechaNacimiento(resultSet.getDate("FECHA_NAC"));
                empleado.setIdTrabajo(resultSet.getString("ID_TRABAJO"));
                arrayEmpleado.add(empleado);
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error en listado");
        }
        return arrayEmpleado;
    }

    /**
     * Este metodo permite obtener el empleado de una tabla.
     *
     * @param cs {@link JTable} Es la tabal donde se va tomar la informacion.
     * @param cs {@link FrmConsultarEmpleado} Es el formulario donde se va
     * presentar los valores.
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public void visualizarEmpleado(JTable tablaEmpleados, FrmConsultarEmpleado ventanaConsultarEmpleado) {
        int fila = tablaEmpleados.getSelectedRow();
        String cedula = tablaEmpleados.getValueAt(fila, 0).toString();

        try {
            sentenciaPL_SQL = "{ call VER_EMPLEADO(?,?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.setString(1, cedula);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(2);
            while (resultSet.next()) {
                empleado.setIdEmpleado(resultSet.getString("ID_EMPLEADO"));
                empleado.setCedula(cedula);
                empleado.setNombre(resultSet.getString("NOMBRE"));
                empleado.setCorreo(resultSet.getString("CORREO"));
                empleado.setContrasenia(resultSet.getString("CONTRASENIA"));
                empleado.setTelefono(resultSet.getString("TELEFONO"));
                empleado.setDireccion(resultSet.getString("DIRECCION"));
                empleado.setFechaNacimiento(resultSet.getDate("FECHA_NAC"));
                empleado.setIdTrabajo(resultSet.getString("ID_TRABAJO"));
            }
            ventanaConsultarEmpleado.getLblCedula().setText(empleado.getCedula());
            ventanaConsultarEmpleado.getLblNombre().setText(empleado.getNombre());
            ventanaConsultarEmpleado.getLblTelefono().setText(empleado.getTelefono());
            ventanaConsultarEmpleado.getLblDireccion().setText(empleado.getDireccion());
            ventanaConsultarEmpleado.getLblCorreo().setText(empleado.getCorreo());
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Este metodo permite obtener los registros de los empleados ´para llenar
     * las tablas de editar empleado.
     *
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public void llenarEditarEmpleado(JTable tablaEmpleados, FrmEditarEmpleado ventanaEmpleado) {
        int fila = tablaEmpleados.getSelectedRow();
        String cedula = tablaEmpleados.getValueAt(fila, 0).toString();

        try {
            sentenciaPL_SQL = "{ call VER_EMPLEADO(?,?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.setString(1, cedula);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(2);
            while (resultSet.next()) {
                empleado.setIdEmpleado(resultSet.getString("ID_EMPLEADO"));
                empleado.setCedula(cedula);
                empleado.setNombre(resultSet.getString("NOMBRE"));
                empleado.setCorreo(resultSet.getString("CORREO"));
                empleado.setContrasenia(resultSet.getString("CONTRASENIA"));
                empleado.setTelefono(resultSet.getString("TELEFONO"));
                empleado.setDireccion(resultSet.getString("DIRECCION"));
                empleado.setFechaNacimiento(resultSet.getDate("FECHA_NAC"));
                empleado.setIdTrabajo(resultSet.getString("ID_TRABAJO"));
            }
            ventanaEmpleado.txtCedula.setText(empleado.getCedula());
            ventanaEmpleado.txtNombre.setText(empleado.getNombre());
            ventanaEmpleado.txtTelefono.setText(empleado.getTelefono());
            ventanaEmpleado.txtDireccion.setText(empleado.getDireccion());
            ventanaEmpleado.txtCorreo.setText(empleado.getCorreo());
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Este metodo permite modificar la informacion del empleado atravex del uso
     * del store procedure en la BD.
     *
     * @return rs regresa el numero de registro afectados.
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public int modificarEmpleado(FrmEditarEmpleado ventanaEmpleado) {
        regAfectados = 0;
        try {
            sentenciaPL_SQL = "{ call MODIFICAR_EMPLEADO(?,?,?,?,?,?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.setString(1, ventanaEmpleado.txtCedula.getText());
            callableStatement.setString(2, ventanaEmpleado.txtNombre.getText());
            callableStatement.setString(3, ventanaEmpleado.txtCorreo.getText());
            callableStatement.setString(4, ventanaEmpleado.txtTelefono.getText());
            callableStatement.setString(5, ventanaEmpleado.txtDireccion.getText());
            callableStatement.registerOutParameter(6, java.sql.Types.INTEGER);
            callableStatement.executeQuery();
            regAfectados = callableStatement.getInt(6);
            nuevaConeccion.close();
            callableStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e + "No se logró actualizar");
            return regAfectados;
        }
        return regAfectados;
    }

    /**
     * Limpia la tabla donde se presenta la informacion del empleado para evitar
     * que se repitan los valores al refrescar la tabla.
     */
    public void LimpiarFormulario(JTable tablaEmpleados) {
        DefaultTableModel tb = (DefaultTableModel) tablaEmpleados.getModel();
        tb.getDataVector().removeAllElements();
        tb.fireTableDataChanged();
        System.out.println("Si funciono");
    }

    /**
     * Este metodo permite verificar si el empleado en la BD.
     *
     * @return rs regresa "000" si el empleado no existe
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public String verificarSiExisteEmpleado(String cedula) {
        try {
            sentenciaPL_SQL = "{ call VERIFICAR_SI_EXISTE_EMPLEADO(?,?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.setString(1, cedula);
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.executeQuery();
            cedula = callableStatement.getString(2);
            nuevaConeccion.close();
            callableStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return cedula;
    }

    /**
     * Este metodo permite ver que rol cumple el empleado en la base de datos y
     * devuelve el nombre que sera presentado en la ventana del programa.
     *
     * @return rs regresa el nombre del rol del empleado.
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public String obtenerNombreRol(int id_rol) {
        String nombreRol = "";
        try {
            sentenciaPL_SQL = "{ call OBTENER_NOMBRE_ROL(?,?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.setInt(1, id_rol);
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.executeQuery();
            nombreRol = callableStatement.getString(2);
            nuevaConeccion.close();
            callableStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return nombreRol;
    }

}
