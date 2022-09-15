/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vista.FrmEmitirRecibo;
import configSQL.ConexionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Soldado
 */
public class FacturaDB {

    private Connection nuevaConeccion;
    private String sentenciaPL_SQL;
    private CallableStatement callableStatement;
    private ResultSet resultSet;

    public FacturaDB() {
    }
    private ValidarCampos validarCampos;

    public void obtenerReciboDomiciliado(FrmEmitirRecibo frmEmitirRecibo) {
        validarCampos = new ValidarCampos();
        try {
            sentenciaPL_SQL = "{call VER_FACTURA_DOMICILIADO_EMITIR(?,?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.setString(1, frmEmitirRecibo.getIdFactura());
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(2);
            while (resultSet.next()) {
                frmEmitirRecibo.getTxtCorreo().setText(resultSet.getString(3));
                frmEmitirRecibo.getLblIdRecibo().setText("Recibo 00" + frmEmitirRecibo.getIdFactura());
                frmEmitirRecibo.getLblCedula().setText(resultSet.getString(1));
                frmEmitirRecibo.getLblNombre().setText(resultSet.getString(2));
                frmEmitirRecibo.getLblCorreo().setText(resultSet.getString(3));
                frmEmitirRecibo.getLblDireccion().setText(resultSet.getString(4));

                frmEmitirRecibo.getLblFechaEmision().setText(validarCampos.validarFormatoFecha(resultSet.getDate(5)));
                frmEmitirRecibo.getLblFechaExpira().setText(validarCampos.validarFormatoFecha(resultSet.getDate(6)));
                frmEmitirRecibo.getLblFechaPago().setText(validarCampos.validarFormatoFecha(resultSet.getDate(7)));

                frmEmitirRecibo.getLblCtaBancaria().setText(resultSet.getString(8));
                frmEmitirRecibo.getLblTipoCuenta().setText(resultSet.getString(9));
                frmEmitirRecibo.getLlblBanco().setText(resultSet.getString(10));

                Object[] fila = {resultSet.getString(11),
                    resultSet.getString(12) + " " + resultSet.getString(13),
                    resultSet.getString(14)};
                frmEmitirRecibo.getModeloTablaVehiculo().addRow(fila);
                frmEmitirRecibo.getLblValorTotal().setText(resultSet.getString(14));
                frmEmitirRecibo.getLblEstado().setText("Domiciliado");
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void obtenerReciboaNoDomiciliado(FrmEmitirRecibo frmEmitirRecibo) {
        validarCampos = new ValidarCampos();
        try {
            sentenciaPL_SQL = "{call VER_FACTURA_NO_DOMICILIADO_EMITIR(?,?)}";
            nuevaConeccion = ConexionDB.conectar();
            callableStatement = nuevaConeccion.prepareCall(sentenciaPL_SQL);
            callableStatement.setString(1, frmEmitirRecibo.getIdFactura());
            callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
            callableStatement.executeQuery();
            resultSet = (ResultSet) callableStatement.getObject(2);
            while (resultSet.next()) {
                frmEmitirRecibo.getLblIdRecibo().setText("Recibo 00" + frmEmitirRecibo.getIdFactura());
                frmEmitirRecibo.getLblCedula().setText(resultSet.getString(1));
                frmEmitirRecibo.getLblNombre().setText(resultSet.getString(2));
                frmEmitirRecibo.getLblCorreo().setText(resultSet.getString(3));
                frmEmitirRecibo.getLblDireccion().setText(resultSet.getString(4));

                frmEmitirRecibo.getLblFechaEmision().setText(validarCampos.validarFormatoFecha(resultSet.getDate(5)));
                frmEmitirRecibo.getLblFechaExpira().setText(validarCampos.validarFormatoFecha(resultSet.getDate(6)));
                frmEmitirRecibo.getLblFechaPago().setText(validarCampos.validarFormatoFecha(resultSet.getDate(7)));

                frmEmitirRecibo.getLblCtaBancaria().setText("XXXX-XXXX-XXXX-XXXX");
                frmEmitirRecibo.getLblTipoCuenta().setText("---");
                frmEmitirRecibo.getLlblBanco().setText("---");
                
                Object[] fila = {resultSet.getString(8),
                    resultSet.getString(9) + " " + resultSet.getString(10),
                    resultSet.getString(11)};
                frmEmitirRecibo.getModeloTablaVehiculo().addRow(fila);
                frmEmitirRecibo.getLblValorTotal().setText(resultSet.getString(11));
                frmEmitirRecibo.getLblEstado().setText("No domiciliado");
            }
            nuevaConeccion.close();
            callableStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
