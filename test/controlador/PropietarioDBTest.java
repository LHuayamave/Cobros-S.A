/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import modelo.CobroDomiciliados;
import modelo.CuentaBancaria;
import modelo.PagoNoDomiciliado;
import modelo.Propietario;
import modelo.Vehiculo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Soldado
 */
public class PropietarioDBTest {
    
    /**
     * Modulo Propietario - Test #9
     * Obtener lista de propietarios.
     */

//    @Test
//    public void testListarPropietarios() {
//        System.out.println("listarPropietarios");
//        PropietarioDB instance = new PropietarioDB();
//        int tamanio = 15;
//        ArrayList<Propietario> result = instance.listarPropietarios();
//        assertEquals(tamanio, result.size());
//
//    }

    /**
     * Modulo Propietario - Test #10
     * Registrar propietario no domiciliado.
     */
//    @Test
//    public void testRegistrarPropietarioNoDomiciliado() throws ParseException {
//        System.out.println("registrarPropietarioNoDomiciliado");
//        Propietario propietario = new Propietario();
//        Vehiculo vehiculo = new Vehiculo();
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//        
//        propietario.setCedula("7778889997");
//        propietario.setCorreo("Prueba");
//        propietario.setDireccion("Prueba");
//        propietario.setDomiciliado(false);
//        propietario.setFechaNacimiento(formato.parse("23/11/2015"));
//        propietario.setId_estado_propietario(1);
//        propietario.setMesPago(7);
//        propietario.setNombre("Agusto");
//        propietario.setTelefono("8754124578");
//        
//        vehiculo.setAnioVehiculo("2015");
//        vehiculo.setIdPropietario("7778889997");
//        vehiculo.setId_tipo_impuesto(1);
//        vehiculo.setMarca("Prueba");
//        vehiculo.setModelo("Prueba pum");
//        vehiculo.setPlaca("Fiu-4125");
//        PropietarioDB instance = new PropietarioDB();
//        int expResult = 1;
//        int result = instance.registrarPropietarioNoDomiciliado(propietario, vehiculo);
//        assertEquals(expResult, result);
//    }

    /**
     * Modulo Propietario - Test #11
     * Registrar propietario  domiciliado.
     */
//    @Test
//    public void testRegistrarPropietarioDomiciliado() throws ParseException {
//        System.out.println("registrarPropietarioDomiciliado");
//        CuentaBancaria cuentaBancaria = new CuentaBancaria();
//        Propietario propietario = new Propietario();
//        Vehiculo vehiculo = new Vehiculo();
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//        
//        cuentaBancaria.setAnio(2022);
//        cuentaBancaria.setCvv("015");
//        cuentaBancaria.setIdBanco(3);
//        cuentaBancaria.setIdTipoCuenta(1);
//        cuentaBancaria.setMes(2);
//        cuentaBancaria.setNumeroCuentaBancaria("1145-5487-9845-1114");
//        cuentaBancaria.setSaldo(15);
//        
//        propietario.setCedula("7778889977");
//        propietario.setCorreo("Prueba");
//        propietario.setDireccion("Prueba");
//        propietario.setDomiciliado(false);
//        propietario.setFechaNacimiento(formato.parse("23/11/2015"));
//        propietario.setId_estado_propietario(1);
//        propietario.setMesPago(7);
//        propietario.setNombre("Agusto");
//        propietario.setTelefono("8754124578");
//        
//        vehiculo.setAnioVehiculo("2015");
//        vehiculo.setIdPropietario("7778889977");
//        vehiculo.setId_tipo_impuesto(1);
//        vehiculo.setMarca("Prueba");
//        vehiculo.setModelo("Prueba pum");
//        vehiculo.setPlaca("Piu-4125");
//        PropietarioDB instance = new PropietarioDB();
//        int expResult = 0;
//        int result = instance.registrarPropietarioDomiciliado(cuentaBancaria, propietario, vehiculo);
//        assertEquals(expResult, result);
//    }

    /**
     * Modulo Propietario - Test #12
     * Validar mayores de edad.
     */
//    @Test
//    public void testValidarEdad() throws ParseException {
//        System.out.println("validarEdad");
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//        Date date = formato.parse("23/11/2015");
//        PropietarioDB instance = new PropietarioDB();
//        boolean expResult = false;
//        boolean result = instance.validarEdad(date);
//        assertEquals(expResult, result);
//
//    }

    /**
     * Modulo Propietario - Test #13
     * LLenar comboBox del estado propietario.
     */
//    @Test
//    public void testLlenarCmbEstadoPropietario() {
//        System.out.println("llenarCmbEstadoPropietario");
//        JComboBox cmbEstadoPropietario = new JComboBox();
//        PropietarioDB instance = new PropietarioDB();
//        instance.llenarCmbEstadoPropietario(cmbEstadoPropietario);
//    }

    /**
     * Modulo Propietario - Test #14
     * LLenar comboBox de tipo de cuenta bancaria.
     */
//    @Test
//    public void testLlenarCmbTipoCtaBancaria() {
//        System.out.println("llenarCmbTipoCtaBancaria");
//        JComboBox cmbTipoCtaBancaria = new JComboBox();
//        PropietarioDB instance = new PropietarioDB();
//        instance.llenarCmbTipoCtaBancaria(cmbTipoCtaBancaria);
//
//    }
    /**
     * Modulo Propietario - Test #15
     * LLenar comboBox de tipo de tipos de banco.
     */
    
//    @Test
//    public void testLlenarCmbBanco() {
//        System.out.println("llenarCmbBanco");
//        JComboBox cmbBanco = new JComboBox();
//        PropietarioDB instance = new PropietarioDB();
//        instance.llenarCmbBanco(cmbBanco);
//  
//    }

    /**
     * Modulo Propietario - Test #16
     * Consultar un propietario domiciliado.
     */
//    @Test
//    public void testConsultarPropietarioDomiciliado() {
//        System.out.println("consultarPropietarioDomiciliado");
//        CuentaBancaria cuentaBancaria = new CuentaBancaria();
//        Propietario propietario = new Propietario();
//        String cedula = "0923725129";
//        
//        PropietarioDB instance = new PropietarioDB();
//        instance.consultarPropietarioDomiciliado(cuentaBancaria, propietario, cedula);
//        assertEquals(propietario.getCedula(), cedula);
//    }

    /**
     * Modulo Propietario - Test #17
     * Consultar un propietario no domiciliado.
     */
//    @Test
//    public void testConsultarPropietarioNoDomiciliado() {
//        System.out.println("consultarPropietarioNoDomiciliado");
//        Propietario propietario = new Propietario();
//        String cedula = "0984512457";
//        PropietarioDB instance = new PropietarioDB();
//        instance.consultarPropietarioNoDomiciliado(propietario, cedula);
//        assertEquals(propietario.getCedula(), cedula);
//    }

    /**
     * Modulo Propietario - Test #18
     * Listar los cobros domiciliados.
     */
//    @Test
//    public void testListarCobrosDomiciliados() {
//        System.out.println("listarCobrosDomiciliados");
//        PropietarioDB instance = new PropietarioDB();
//        int tamanio =10;
//        ArrayList<CobroDomiciliados> result = instance.listarCobrosDomiciliados();
//        assertEquals(tamanio, result.size());
//  
//    }

    /**
     * Modulo Propietario - Test #19
     * Listar los cobros domiciliados por mes.
     */
//    @Test
//    public void testListarCobrosDomiciliadosMes() {
//        Integer mes = 2;
//        PropietarioDB instance = new PropietarioDB();
//        int tamanio = 1;
//        ArrayList<CobroDomiciliados> result = instance.listarCobrosDomiciliadosMes(mes);
//        assertEquals(tamanio, result.size());
//    }

    /**
     * Modulo Propietario - Test #20
     * Actualizar saldos a las cuentas bancarias.
     */
//    @Test
//    public void testActualizarSaldoDomiciliados() {
//        System.out.println("ActualizarSaldoDomiciliados");
//        String numCtaBancaria = "99999999999";
//        float saldoActualizado = 19F;
//        PropietarioDB instance = new PropietarioDB();
//        int resultado = instance.ActualizarSaldoDomiciliados(numCtaBancaria, saldoActualizado);
//        assertEquals(1, resultado);
//    }

    /**
     * Modulo Propietario - Test #21
     * Listar pagos no domiciliados.
     */
//    @Test
//    public void testListarPagosNoDomiciliados() {
//        System.out.println("listarPagosNoDomiciliados");
//        PropietarioDB instance = new PropietarioDB();
//        int tamanio = 3;
//        ArrayList<PagoNoDomiciliado> result = instance.listarPagosNoDomiciliados();
//        assertEquals(tamanio, result.size());
//
//    }

    /**
     * Modulo Propietario - Test #22
     * Listar pagos no domiciliados por mes.
     */
//    @Test
//    public void testListarPagosNoDomiciliadosMes() {
//        System.out.println("listarPagosNoDomiciliadosMes");
//        Integer mes = 4;
//        PropietarioDB instance = new PropietarioDB();
//        int resultado =1;
//        ArrayList<PagoNoDomiciliado> result = instance.listarPagosNoDomiciliadosMes(mes);
//        assertEquals(resultado, result.size());
//    }

    /**
     * Modulo Propietario - Test #23
     * modificar propietario no domiciliado.
     */
//    @Test
//    public void testModificarPropietarioNoDomiciliado() throws ParseException {
//        System.out.println("modificarPropietarioNoDomiciliado");
//        Propietario propietario = new  Propietario();
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//        propietario.setCedula("7778889977");
//        propietario.setCorreo("Prueba");
//        propietario.setDireccion("Prueba");
//        propietario.setDomiciliado(false);
//        propietario.setFechaNacimiento(formato.parse("23/11/2015"));
//        propietario.setId_estado_propietario(1);
//        propietario.setMesPago(7);
//        propietario.setNombre("Agusto");
//        propietario.setTelefono("8754124578");
//        PropietarioDB instance = new PropietarioDB();
//        int expResult = 0;
//        int result = instance.modificarPropietarioNoDomiciliado(propietario);
//        assertEquals(expResult, result);
//    }

    /**
     * Modulo Propietario - Test #24
     * Actualizar fecha de pago
     */
//    @Test
//    public void testActualizarFechaPago() {
//        System.out.println("ActualizarFechaPago");
//        String id_factura = "14";
//        PropietarioDB instance = new PropietarioDB();
//        int expResult = 1;
//        int result = instance.ActualizarFechaPago(id_factura);
//        assertEquals(expResult, result);
//    }

    /**
     * Modulo Propietario - Test #25
     * Verificar si el propietario existe
     */
//    @Test
//    public void testVerificarSiExistePropietario() {
//        System.out.println("verificarSiExistePropietario");
//        String cedula = "0984124574";
//        PropietarioDB instance = new PropietarioDB();
//        String expResult = "0984124574";
//        String result = instance.verificarSiExistePropietario(cedula);
//        assertEquals(expResult, result);
//
//    }    
}
