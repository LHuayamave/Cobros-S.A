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
//        int expResult = 5;
//        int result = empleadoDB.obternerIdMaximo();
//        assertEquals(expResult, result);
//    }

    /**
     * Modulo Empleado - Test #3
     * Agregar un empleado.
     */
//    @Test
//    public void testAgregarEmpleado() throws ParseException {
//        System.out.println("agregarEmpleado");
//        Empleado empleado = new Empleado();
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//                
//        empleado.setCedula("5554448881");
//        empleado.setContrasenia("L123456");
//        empleado.setCorreo("prueba");
//        empleado.setDireccion("prueba");
//        empleado.setFechaNacimiento(formato.parse("23/11/2015"));
//        empleado.setIdEmpleado("5");
//        empleado.setIdTrabajo("1");
//        empleado.setNombre("Juanito Alimaña");
//        empleado.setTelefono("000000000");
//        EmpleadoDB instance = new EmpleadoDB();
//        int expResult = 1;
//        int result = instance.agregarEmpleado(empleado);
//        assertEquals(expResult, result);
//    }

    /**
     * Modulo Empleado - Test #4
     * Obtener un empleado.
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
     * Modulo Empleado - Test #5
     * Obtener lista de un empleado.
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

    /**
     * Modulo Empleado - Test #6
     * Modificar un empleado.
     */

//    @Test
//    public void testModificarEmpleado() {
//        System.out.println("modificarEmpleado");
//        FrmEditarEmpleado frmEditarEmpleado = new FrmEditarEmpleado();
//        frmEditarEmpleado.txtCedula.setText("1231457845");
//        frmEditarEmpleado.txtCorreo.setText("prueba");
//        frmEditarEmpleado.txtDireccion.setText("Prueba");
//        frmEditarEmpleado.txtNombre.setText("Fantasmita");
//        frmEditarEmpleado.txtTelefono.setText("4444445557");
//        EmpleadoDB instance = new EmpleadoDB();
//        int expResult = 0;
//        int result = instance.modificarEmpleado(frmEditarEmpleado);
//        assertEquals(expResult, result);
//    }

    /**
     * Modulo Empleado - Test #7
     * Verificar si existe empleado.
     */

//    @Test
//    public void testVerificarSiExisteEmpleado() {
//        System.out.println("verificarSiExisteEmpleado");
//        String cedula = "0123456789";
//        EmpleadoDB instance = new EmpleadoDB();
//        String expResult = "0123456789";
//        String result = instance.verificarSiExisteEmpleado(cedula);
//        assertEquals(expResult, result);
//    }

    /**
     * Modulo Empleado - Test #8
     * Obtener nombre del rol del empleado.
     */
//    @Test
//    public void testObtenerNombreRol() {
//        System.out.println("obtenerNombreRol");
//        int id_rol = 0;
//        EmpleadoDB instance = new EmpleadoDB();
//        String expResult = "Administrador";
//        String result = instance.obtenerNombreRol(id_rol);
//        assertEquals(expResult, result);
//    }   
}
