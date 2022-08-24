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
import modelo.Propietario;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Soldado
 */
public class PropietarioDBTest {
    
    public PropietarioDBTest() {
    }
/*En uso*/
//@Test
//public void testObtenerPropietario() {
//System.out.println("obtenerPropietario");
//String cedula = "0984512458";
//PropietarioDB instance = new PropietarioDB();
//Propietario propietario = new Propietario();
//propietario.setCedula(cedula);
////extrae el propietario si existe y los compara 
//Propietario result = instance.obtenerPropietario(cedula);
//assertEquals(propietario.getCedula(), result.getCedula());
//}

/* En uso*/
    @Test
    public void testValidarEdad() throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("validarEdad");
        Date fechaNacimiento = formato.parse("23/11/1997");
        PropietarioDB instance = new PropietarioDB();
        boolean expResult = true;
        boolean result = instance.validarEdad(fechaNacimiento);
        assertEquals(expResult, result);

    }


    @Test
    public void testVerificarSiExistePropietario() {
        System.out.println("verificar Si Existe Propietario");
        String cedula = "9845125548";
        PropietarioDB instance = new PropietarioDB();
        String expResult = null;
        String result = instance.verificarSiExistePropietario(cedula);
        assertEquals(expResult, result);
    }

}
