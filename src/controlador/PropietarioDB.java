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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import modelo.CobroDomiciliados;
import modelo.CuentaBancaria;
import modelo.PagoNoDomiciliado;
import modelo.Propietario;
import modelo.Vehiculo;
import oracle.jdbc.OracleTypes;

public class PropietarioDB {

    private Connection nuevaConeccion;

    private CallableStatement callableStatement;
    private ResultSet resultSet;
    private ArrayList<Propietario> listaPropietarios;
    private Propietario propietario;
    private ValidarCampos validarCampos;
    private int resultado;
    private String ejecutarSentencia;

    private ArrayList<CobroDomiciliados> listaCobrosDomiciliados;
    private ArrayList<CobroDomiciliados> listaCobrosDomiciliadosMes;
    private ArrayList<PagoNoDomiciliado> listaPagosNoDomiciliados;
    private ArrayList<PagoNoDomiciliado> listaPagoNoDomiciliadosMes;

    private PagoNoDomiciliado pagoNoDomiciliado;
    private CobroDomiciliados cobrosDomiciliados;

    public PropietarioDB() {
    }

    ;
    /**
     * Este metodo permite obtener la lista de los propietarios que se va presentar
     * en una tabla.
     * 
     * @param cs {@link frmEmitirRecibo} recibe el formulario que va presentar 
     * los valores.
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public ArrayList<Propietario> listarPropietarios() {
        listaPropietarios = new ArrayList();
        ejecutarSentencia = "{ call VER_TABLA_PROPIETARIO(?)}";
        try {

            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);

            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(1);
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

    /**
     * Este metodo permite regirtrar a un propietario no domiciliado en la BD.
     *
     * @param cs {@link Propietario} modelo que contiene los datos del
     * propietario
     * @param cs {@link Vehiculo} modelo que contiene los datos del pvehiculo
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public int registrarPropietarioNoDomiciliado(Propietario propietario, Vehiculo vehiculo) {
        resultado = 0;
        ejecutarSentencia = "{ call AGREGAR_PROPIETARIO_NO_DOMICILIADO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            CallableStatement cs = nuevaConeccion.prepareCall(ejecutarSentencia);

            cs.setString(1, propietario.getCedula());
            cs.setString(2, propietario.getNombre());
            cs.setString(3, propietario.getCorreo());
            cs.setString(4, propietario.getTelefono());
            cs.setString(5, propietario.getDireccion());
            cs.setString(6, new ValidarCampos().validarFormatoFecha(propietario.getFechaNacimiento()));
            cs.setInt(7, propietario.obtenerMesPago());
            cs.setInt(8, propietario.getId_estado_propietario());
            cs.setString(9, null);// cuenta bancaria
            cs.setString(10, Boolean.toString(propietario.getDomiciliado()));
            cs.setString(11, vehiculo.getPlaca());
            cs.setString(12, vehiculo.getMarca());
            cs.setString(13, vehiculo.getModelo());
            cs.setString(14, vehiculo.getAnioVehiculo());
            cs.setInt(15, vehiculo.getId_tipo_impuesto());
            cs.registerOutParameter(16, java.sql.Types.INTEGER);

            cs.execute();
            resultado = cs.getInt(16);
            nuevaConeccion.close();
            mensajeError("El ropietario insertado con exito.");
        } catch (Exception e) {
            mensajeError("El propietario no a sido insertado.Error: " + e);
        }
        return resultado;
    }

    /**
     * Este metodo permite regirtrar a un propietario domiciliado en la BD.
     *
     * @param cs {@link Propietario} modelo que contiene los datos del
     * propietario
     * @param cs {@link Vehiculo} modelo que contiene los datos del pvehiculo
     * @param cs {@link CuentaBancaria} modelo que contiene los datos del cuenta
     * Bancaria
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public int registrarPropietarioDomiciliado(CuentaBancaria cuentaBancaria, Propietario propietario,
            Vehiculo vehiculo) {
        resultado = 0;
        ejecutarSentencia = "{ call AGREGAR_PROPIETARIO_DOMICILIADO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            CallableStatement cs = nuevaConeccion.prepareCall(ejecutarSentencia);

            cs.setString(1, cuentaBancaria.getNumeroCuentaBancaria());
            cs.setString(2, cuentaBancaria.getCvv());
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
            cs.setString(16, "true"); // domiciliado

            cs.setString(17, vehiculo.getPlaca());
            cs.setString(18, vehiculo.getMarca());
            cs.setString(19, vehiculo.getModelo());
            cs.setString(20, vehiculo.getAnioVehiculo());
            cs.setInt(21, vehiculo.getId_tipo_impuesto());
            cs.registerOutParameter(22, java.sql.Types.INTEGER);

            cs.execute();
            resultado = cs.getInt(22);
            nuevaConeccion.close();
            mensajeError("El ropietario insertado con exito.");
        } catch (Exception e) {
            System.out.println(e);
            mensajeError("El propietario no a sido insertado.Error: " + e);
        }
        return resultado;
    }

    public boolean validarEdad(Date date) {
        validarCampos = new ValidarCampos();
        return validarCampos.validarEdad(date);
    }

    /**
     * Este metodo permite obtener los diferentes estados que puede tener un
     * propietario y llenar el comboBox.
     *
     * @param cs {@link JComboBox} permite hacer una lista oara el propietario
     * elija una opcion.
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public void llenarCmbEstadoPropietario(JComboBox cmbEstadoPropietario) {
        try {
            ejecutarSentencia = "{call OBTENER_ESTADO_PROPIETARIO(?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(1);
            while (resultSet.next()) {
                cmbEstadoPropietario.addItem(resultSet.getString(1));
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void llenarCmbTipoCtaBancaria(JComboBox cmbTipoCtaBancaria) {
        try {
            ejecutarSentencia = "{call OBTENER_NOMBRE_TIPO_CUENTA(?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(1);
            while (resultSet.next()) {
                cmbTipoCtaBancaria.addItem(resultSet.getString(1));
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Este metodo permite obtener los diferentes tipos de bancos que puede
     * tener una cuenta bancaria y llenar el comboBox.
     *
     * @param cs {@link JComboBox} permite hacer una lista oara el propietario
     * elija una opcion.
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public void llenarCmbBanco(JComboBox cmbBanco) {
        try {
            ejecutarSentencia = "{call OBTENER_NOMBRE_BANCO(?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(1);
            while (resultSet.next()) {
                cmbBanco.addItem(resultSet.getString(1));
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Este metodo permite obtener un propietario domiciliado que se va
     * presentar en un formulario.
     *
     * @param cs {@link Propietario} modelo que contiene los datos del
     * propietario
     * @param cs {@link Vehiculo} modelo que contiene los datos del pvehiculo
     * @param cs {@link CuentaBancaria} modelo que contiene los datos del cuenta
     * Bancaria
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public void consultarPropietarioDomiciliado(CuentaBancaria cuentaBancaria, Propietario propietario, String cedula) {
        try {
            ejecutarSentencia = "{call VER_PROPIETARIO_DOMICILIADO(?,?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);
            callableStatement.setString(1, cedula);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(2);
            while (resultSet.next()) {
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
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Este metodo permite obtener un propietario no domiciliado que se va
     * presentar en un formulario.
     *
     * @param cs {@link Propietario} modelo que contiene los datos del
     * propietario
     * @param cs {@link Vehiculo} modelo que contiene los datos del pvehiculo
     * @param cs {@link CuentaBancaria} modelo que contiene los datos del cuenta
     * Bancaria
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public void consultarPropietarioNoDomiciliado(Propietario propietario, String cedula) {
        try {
            ejecutarSentencia = "{call VER_PROPIETARIO_NO_DOMICILIADO(?,?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);
            callableStatement.setString(1, cedula);
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(2);
            while (resultSet.next()) {
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
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    /*
    * Este metodo extrae la lista de los cobros de los propietarios domiciliados
     */
    public ArrayList<CobroDomiciliados> listarCobrosDomiciliados() {
        listaCobrosDomiciliados = new ArrayList();
        ejecutarSentencia = "{ call VER_TABLA_COBROS_PROPIETARIOS_DOMICILIADOS(?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);

            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(1);
            while (resultSet.next()) {
                cobrosDomiciliados = new CobroDomiciliados();
                cobrosDomiciliados.setIdFactura(resultSet.getInt("ID_FACTURA"));
                cobrosDomiciliados.setCedulaPropietario(resultSet.getString("CEDULA"));
                cobrosDomiciliados.setNombrePropietario(resultSet.getString("NOMBRE"));
                cobrosDomiciliados.setTipoImpuesto(resultSet.getString("DESCRIPCION"));
                cobrosDomiciliados.setValorImpuesto(resultSet.getFloat("VALOR"));
                cobrosDomiciliados.setSaldo(resultSet.getFloat("SALDO"));
                cobrosDomiciliados.setCtaBancaria(resultSet.getString("NUM_CTA_BANCARIA"));
                cobrosDomiciliados.setMesPago(resultSet.getInt("MES_PAGO"));
                listaCobrosDomiciliados.add(cobrosDomiciliados);
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error en listado");
        }
        return listaCobrosDomiciliados;
    }

    public ArrayList<CobroDomiciliados> listarCobrosDomiciliadosMes(Integer mes) {
        listaCobrosDomiciliadosMes = new ArrayList();

        ejecutarSentencia = "{ call VER_TABLA_COBROS_DOMICILIADOS2(" + mes + ",?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);

            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(1);
            while (resultSet.next()) {
                cobrosDomiciliados = new CobroDomiciliados();
                cobrosDomiciliados.setIdFactura(resultSet.getInt("ID_FACTURA"));
                cobrosDomiciliados.setCedulaPropietario(resultSet.getString("CEDULA"));
                cobrosDomiciliados.setNombrePropietario(resultSet.getString("NOMBRE"));
                cobrosDomiciliados.setTipoImpuesto(resultSet.getString("DESCRIPCION"));
                cobrosDomiciliados.setValorImpuesto(resultSet.getFloat("VALOR"));
                cobrosDomiciliados.setSaldo(resultSet.getFloat("SALDO"));
                cobrosDomiciliados.setCtaBancaria(resultSet.getString("NUM_CTA_BANCARIA"));
                cobrosDomiciliados.setMesPago(resultSet.getInt("MES_PAGO"));
                listaCobrosDomiciliadosMes.add(cobrosDomiciliados);
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error en listado");
        }
        return listaCobrosDomiciliadosMes;
    }

    public int ActualizarSaldoDomiciliados(String numCtaBancaria, float saldoActualizado) {
        ejecutarSentencia = "{call ACTUALIZAR_SALDO_PROPIETARIO_DOMICILIADO('" + numCtaBancaria + "'," + saldoActualizado + ")}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);
            callableStatement.executeQuery();
            nuevaConeccion.close();
            callableStatement.close();
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error en listado");
            return 0;
        }
    }

    /* Metodos para emitir aviso de Cobro*/

 /*Metodos para emitir aviso de Pago*/
 /* Metodos para Listar Pagos No Domicilaidos*/
    public ArrayList<PagoNoDomiciliado> listarPagosNoDomiciliados() {
        listaPagosNoDomiciliados = new ArrayList();
        ejecutarSentencia = "{ call VER_TABLA_PAGOS_NO_DOMICILIADO(?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);
            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();

            resultSet = (ResultSet) callableStatement.getObject(1);
            while (resultSet.next()) {
                pagoNoDomiciliado = new PagoNoDomiciliado();
                pagoNoDomiciliado.setId_factura(resultSet.getString("ID_FACTURA"));
                pagoNoDomiciliado.setCedulaPropietario(resultSet.getString("CEDULA"));
                pagoNoDomiciliado.setNombrePropietario(resultSet.getString("NOMBRE"));
                pagoNoDomiciliado.setTipoImpuesto(resultSet.getString("DESCRIPCION"));
                pagoNoDomiciliado.setValorImpuesto(resultSet.getFloat("VALOR_TOTAL"));
                pagoNoDomiciliado.setMesPago(resultSet.getInt("MES_PAGO"));
                listaPagosNoDomiciliados.add(pagoNoDomiciliado);
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Erro en listado");
        }

        return listaPagosNoDomiciliados;
    }

    public ArrayList<PagoNoDomiciliado> listarPagosNoDomiciliadosMes(Integer mes) {
        listaPagoNoDomiciliadosMes = new ArrayList();

        ejecutarSentencia = "{ call VER_TABLA_PAGOS_NO_DOMICILIADOS2(" + mes + ",?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);

            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(1);
            while (resultSet.next()) {
                pagoNoDomiciliado = new PagoNoDomiciliado();
                pagoNoDomiciliado.setId_factura(resultSet.getString("ID_FACTURA"));
                pagoNoDomiciliado.setCedulaPropietario(resultSet.getString("CEDULA"));
                pagoNoDomiciliado.setNombrePropietario(resultSet.getString("NOMBRE"));
                pagoNoDomiciliado.setTipoImpuesto(resultSet.getString("DESCRIPCION"));
                pagoNoDomiciliado.setValorImpuesto(resultSet.getFloat("VALOR_TOTAL"));
                pagoNoDomiciliado.setMesPago(resultSet.getInt("MES_PAGO"));
                listaPagoNoDomiciliadosMes.add(pagoNoDomiciliado);
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error en listado");
        }
        return listaPagoNoDomiciliadosMes;
    }

    /**
     * Acontiniacio se va mostrar las 5 diferentes modificaiones que puede el
     * propietario.
     *
     * @param cs {@link Propietario} modelo que contiene los datos del
     * propietario
     * @param cs {@link Vehiculo} modelo que contiene los datos del pvehiculo
     * @param cs {@link CuentaBancaria} modelo que contiene los datos del cuenta
     * Bancaria
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public int modificarPropietarioNoDomiciliado(Propietario propietario) {
        resultado = 0;
        ejecutarSentencia = "{ call MODIFICAR_PROPIETARIO_NO_DOMICILIADO(?,?,?,?,?,?,?,?,?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            CallableStatement cs = nuevaConeccion.prepareCall(ejecutarSentencia);

            cs.setString(1, propietario.getCedula());
            cs.setString(2, propietario.getNombre());
            cs.setString(3, propietario.getCorreo());
            cs.setString(4, propietario.getTelefono());
            cs.setString(5, propietario.getDireccion());
            cs.setString(6, new ValidarCampos().validarFormatoFecha(propietario.getFechaNacimiento()));
            cs.setInt(7, propietario.getMesPago());
            cs.setInt(8, propietario.getId_estado_propietario());
            cs.registerOutParameter(9, java.sql.Types.INTEGER);
            cs.execute();
            resultado = cs.getInt(9);
            nuevaConeccion.close();
        } catch (Exception e) {
            mensajeError("Error: " + e);
        }
        return resultado;
    }

    public int modificarPropietarioNoDomiciliadoADomiciliado(CuentaBancaria cuentaBancaria,
            Propietario propietario) {
        resultado = 0;
        ejecutarSentencia = "{ call MODIFICAR_PROPIETARIO_NO_DOMICILIADO_A_DOMICILIADO (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            CallableStatement cs = nuevaConeccion.prepareCall(ejecutarSentencia);

            cs.setString(1, cuentaBancaria.getNumeroCuentaBancaria());
            cs.setString(2, cuentaBancaria.getCvv());
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
            cs.setInt(14, propietario.getMesPago());
            cs.setInt(15, propietario.getId_estado_propietario());
            cs.setString(16, "true");
            cs.registerOutParameter(17, java.sql.Types.INTEGER);
            cs.execute();
            resultado = cs.getInt(17);
            nuevaConeccion.close();
        } catch (Exception e) {
            mensajeError("Error: " + e);
        }
        return resultado;
    }

    public int modificarPropietarioDomiciliado(CuentaBancaria cuentaBancaria,
            Propietario propietario) {
        resultado = 0;
        ejecutarSentencia = "{ call MODIFICAR_PROPIETARIO_DOMICILIADO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            CallableStatement cs = nuevaConeccion.prepareCall(ejecutarSentencia);

            cs.setString(1, cuentaBancaria.getNumeroCuentaBancaria());
            cs.setString(2, cuentaBancaria.getCvv());
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
            cs.setInt(14, propietario.getMesPago());
            cs.setInt(15, propietario.getId_estado_propietario());
            cs.setString(16, "true");
            cs.registerOutParameter(17, java.sql.Types.INTEGER);
            cs.execute();
            resultado = cs.getInt(17);
            nuevaConeccion.close();
        } catch (Exception e) {
            mensajeError("Error: " + e);
        }
        return resultado;
    }

    public int modificarPropietarioDomiciliadoANoDomiciliado(Propietario propietario) {
        resultado = 0;
        ejecutarSentencia = "{ call MODIFICAR_PROPIETARIO_DOMICILIADO_A_NO_DOMICILIADO(?,?,?,?,?,?,?,?,?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            CallableStatement cs = nuevaConeccion.prepareCall(ejecutarSentencia);

            cs.setString(1, propietario.getCedula());
            cs.setString(2, propietario.getNombre());
            cs.setString(3, propietario.getCorreo());
            cs.setString(4, propietario.getTelefono());
            cs.setString(5, propietario.getDireccion());
            cs.setString(6, new ValidarCampos().validarFormatoFecha(propietario.getFechaNacimiento()));
            cs.setInt(7, propietario.getMesPago());
            cs.setInt(8, propietario.getId_estado_propietario());
            cs.registerOutParameter(9, java.sql.Types.INTEGER);
            cs.execute();
            resultado = cs.getInt(9);
            nuevaConeccion.close();
        } catch (Exception e) {
            mensajeError("Error: " + e);
        }
        return resultado;
    }

    public int modificarPropietarioDomiciliadoNuevaCtaBancaria(CuentaBancaria cuentaBancaria,
            Propietario propietario) {
        resultado = 0;
        ejecutarSentencia = "{ call MODIFICAR_PROPIETARIO_DOMICILIADO_NUEVA_CTA_BANCARIA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            CallableStatement cs = nuevaConeccion.prepareCall(ejecutarSentencia);

            cs.setString(1, cuentaBancaria.getNumeroCuentaBancaria());
            cs.setString(2, cuentaBancaria.getCvv());
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
            cs.setInt(14, propietario.getMesPago());
            cs.setInt(15, propietario.getId_estado_propietario());
            cs.setString(16, "true");
            cs.registerOutParameter(17, java.sql.Types.INTEGER);
            cs.execute();
            resultado = cs.getInt(17);
            nuevaConeccion.close();
        } catch (Exception e) {
            mensajeError("Error: " + e);
        }
        return resultado;
    }

    private void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void confirmarRegistrosModificados(int regAlterados) {
        if (regAlterados > 0) {
            JOptionPane.showMessageDialog(null, "La informacion se ingreso correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "La informacion no se ingreso");
        }
    }

    /**
     * Acualiza la factura cuando el propietario no domiciliado canceña.
     *
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public int ActualizarFechaPago(String id_factura) {
        resultado = 0;
        ejecutarSentencia = "{call actualizar_pago (?,?)}";
        try {
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);
            callableStatement.setString(1, id_factura);
            callableStatement.registerOutParameter(2, java.sql.Types.INTEGER);
            callableStatement.executeQuery();
            resultado = callableStatement.getInt(2);
            nuevaConeccion.close();
            callableStatement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Error en actualizar");
        }
        return resultado;
    }

    /**
     * Veridica si la cedula del propietario existe en la BD si no existe
     * regresa el valor de "000".
     *
     * @throws SQLException como se esta usando una correccion a la BD se debe
     * usar un try catch para atrapar algun error propio de la base de datos.
     */
    public String verificarSiExistePropietario(String cedula) {
        try {
            ejecutarSentencia = "{call VERIFICAR_SI_EXISTE_PROPIETARIO(?,?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(ejecutarSentencia);
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
}
