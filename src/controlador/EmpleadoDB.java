package controlador;

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
        } catch (Exception e) {
            System.out.println(e + " Error al generar id empleado");
        }
        return idEmpleado;
    }

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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Empleado no agregado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return regAfectados;

    }

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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Empleado no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return empleado;
    }

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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

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

        } catch (Exception e) {
            System.out.println(e);
        }
    }

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

    public void LimpiarFormulario(JTable tablaEmpleados) {
        DefaultTableModel tb = (DefaultTableModel) tablaEmpleados.getModel();
        tb.getDataVector().removeAllElements();
        tb.fireTableDataChanged();
        System.out.println("Si funciono");
    }

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
        } catch (Exception e) {
            System.out.println(e);
        }
        return cedula;
    }

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
        } catch (Exception e) {
            System.out.println(e);
        }
        return nombreRol;
    }

}
