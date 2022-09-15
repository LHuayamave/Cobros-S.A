/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vista.FrmConsultarEmpleado;
import Vista.FrmEditarEmpleado;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import modelo.Empleado;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Soldado
 */
public class EmpleadoDBTest {
    
    public EmpleadoDBTest() {
    }

    /**
     * Modulo Empleado - Test #2
     * Este Test permite obtener el numero maximo de registro en la base de datos
     * lo que define el id del empleado.
     */
//    @Test
//    public void testObternerIdMaximo() {
//        System.out.println("obternerIdMaximo: ");
//        EmpleadoDB empleadoDB = new EmpleadoDB();
//        int expResult = 4;
//        int result = empleadoDB.obternerIdMaximo();
//        assertEquals(expResult, result);
//
//    }

    @Test
    public void testAgregarEmpleado() throws ParseException {
        System.out.println("agregarEmpleado");
        Empleado empleado = new Empleado();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                
        empleado.setCedula("5554448881");
        empleado.setContrasenia("L123456");
        empleado.setCorreo("prueba");
        empleado.setDireccion("prueba");
        empleado.setFechaNacimiento(formato.parse("23/11/2015"));
        empleado.setIdEmpleado("5");
        empleado.setIdTrabajo("1");
        empleado.setNombre("Juanito Alimaña");
        empleado.setTelefono("000000000");
        EmpleadoDB instance = new EmpleadoDB();
        int expResult = 1;
        int result = instance.agregarEmpleado(empleado);
        assertEquals(expResult, result);
    }

    /**
     * Modulo Empleado - Test #3.
     */
//    @Test
//    public void testObtenerEmpleado() {
//        System.out.println("obtener un empleado");
//        String cedula = "0123456789";
//        EmpleadoDB instance = new EmpleadoDB();
//        Empleado empleado = new Empleado();
//        empleado.setCedula(cedula);
//        //Obtiene el empleado si existe de la BD
//        Empleado result = instance.obtenerEmpleado(cedula); 
//        assertEquals(empleado.getCedula(), result.getCedula());
//    }

    /**
     * Modulo Empleado - Test #4.
     */
//    @Test
//    public void testListEmpleado() {
//        System.out.println("Lista del Empleado");
//        EmpleadoDB empleadoDB = new EmpleadoDB();
//        int tamanio = 4;
//        ArrayList<Empleado> result = empleadoDB.ListEmpleado();
//        assertEquals(result.size(), tamanio);
//
//    }

//    @Test
//    public void testVisualizarEmpleado() {
//        System.out.println("visualizarEmpleado");
//        JTable tablaEmpleados = null;
//        FrmConsultarEmpleado ventanaConsultarEmpleado = null;
//        EmpleadoDB instance = new EmpleadoDB();
//        instance.visualizarEmpleado(tablaEmpleados, ventanaConsultarEmpleado);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testLlenarEditarEmpleado() {
//        System.out.println("llenarEditarEmpleado");
//        JTable tablaEmpleados = null;
//        FrmEditarEmpleado ventanaEmpleado = null;
//        EmpleadoDB instance = new EmpleadoDB();
//        instance.llenarEditarEmpleado(tablaEmpleados, ventanaEmpleado);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testModificarEmpleado() {
//        System.out.println("modificarEmpleado");
//        FrmEditarEmpleado ventanaEmpleado = null;
//        EmpleadoDB instance = new EmpleadoDB();
//        int expResult = 0;
//        int result = instance.modificarEmpleado(ventanaEmpleado);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testLimpiarFormulario() {
//        System.out.println("LimpiarFormulario");
//        JTable tablaEmpleados = null;
//        EmpleadoDB instance = new EmpleadoDB();
//        instance.LimpiarFormulario(tablaEmpleados);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testVerificarSiExisteEmpleado() {
//        System.out.println("verificarSiExisteEmpleado");
//        String cedula = "";
//        EmpleadoDB instance = new EmpleadoDB();
//        String expResult = "";
//        String result = instance.verificarSiExisteEmpleado(cedula);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testObtenerNombreRol() {
//        System.out.println("obtenerNombreRol");
//        int id_rol = 0;
//        EmpleadoDB instance = new EmpleadoDB();
//        String expResult = "";
//        String result = instance.obtenerNombreRol(id_rol);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
    
}
