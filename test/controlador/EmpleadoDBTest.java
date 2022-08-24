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
import java.util.Date;
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
    /*En uso*/
    @Test
    public void testObtenerEmpleado() {
        System.out.println("obtenerEmpleado");
        String cedula = "0123456789";
        EmpleadoDB instance = new EmpleadoDB();
        Empleado empleado = new Empleado();
        empleado.setCedula(cedula);
        //Obtiene el empleado si existe de la BD
        Empleado result = instance.obtenerEmpleado(cedula); 
        assertEquals(empleado.getCedula(), result.getCedula());
    }
    /*En uso*/
    @Test
    public void testObternerIdMaximo() {
        System.out.println("obterner IdMaximo");
        EmpleadoDB instance = new EmpleadoDB();
        // Obtiene el numero de maximo de empleados registrado para agg el ID
        int expResult = 5; 
        int result = instance.obternerIdMaximo();
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    /*En uso*/
    @Test
    public void testVerificarSiExisteEmpleado() {
        System.out.println("verificarSiExisteEmpleado");
        String cedula = "9876543210";
        EmpleadoDB instance = new EmpleadoDB();
        // si existe se obtiene de nuevo la cedula como resultado 
        String expResult = "9876543210"; 
        String result = instance.verificarSiExisteEmpleado(cedula);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }


/*En uso*/    
    @Test 
    public void testObtenerNombreRol() {
        System.out.println("obtenerNombreRol");
        int id_rol = 1;
        EmpleadoDB empleadoDB = new EmpleadoDB();
        String expResult = "Administrador";
        String result = empleadoDB.obtenerNombreRol(id_rol);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }
    
}
