package modelo;

/**
     * Esta es una clase abstracta que permite declarar atributos y métodos que tienen en común las clases hijas.
     * @author Grupo E
*/
import java.util.Date;


public abstract class Persona {
    
    protected String cedula;
    protected String nombre;
    protected String direccion;
    protected String correo;
    protected Date fechaNacimiento;
    protected String  telefono;
    
    public Persona(){}

    public Persona(String cedula, String nombre, String direccion, String correo, 
            Date fechaNacimiento, String telefono) {
        
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
    }

}
