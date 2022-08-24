/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.JComboBox;
import modelo.Vehiculo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Soldado
 */
public class VehiculoDBTest {
    
    public VehiculoDBTest() {
    }
//
//    @Test
//    public void testObtenerVehiculo() {
//        System.out.println("obtenerVehiculo");
//        String placa = "VVS-555";
//        VehiculoDB instance = new VehiculoDB();
//        Vehiculo vehiculo = new Vehiculo();
//        vehiculo.setPlaca(placa);
//        // Si el vehiculo existe se obtiene sus datos, si no existe sale error
//        Vehiculo result = instance.obtenerVehiculo(placa);
//        assertEquals(vehiculo.getPlaca(), result.getPlaca());
//        fail("The test case is a prototype.");
//    }

    @Test
    public void testVerificarSiExistePlaca() {
        System.out.println("verificarSiExistePlaca");
        String placa = "CAA-1248";
        VehiculoDB instance = new VehiculoDB();
        String expResult = "CAA-1248";
        // verifica si la placa existe si es asi devuelve la placa caso contrario
        //devuelve null.
        String result = instance.verificarSiExistePlaca(placa);
        assertEquals(expResult, result);
    }
    
}
