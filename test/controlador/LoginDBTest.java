/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Vista.FrmLogin;
import modelo.Empleado;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Soldado
 */
public class LoginDBTest {
    EmpleadoDB empleadoDB ;
    FrmLogin frmLogin= new FrmLogin();
    LoginDB instance ;
    public LoginDBTest() {
    }

    @Test
    public void testObtenerEmpleado() {
        System.out.println("obtener Empleado"); 
        String cedula = "9876543210";
        String contrasenia = "C123456";
        instance = new LoginDB(frmLogin);
        int expResult = 2;
        int result = instance.obtenerEmpleado(cedula, contrasenia);
        assertEquals(expResult, result);
    }
    
}
