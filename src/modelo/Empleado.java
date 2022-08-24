package modelo;
/**
     * Esta es una clase extiende de la clase absatracta Persona y se la utiliza para instanciar objetos empleados
     * y guardar información.
     * @author Grupo E
*/
import java.util.Date;

public class Empleado extends Persona{
   private String idEmpleado;
   private String idTrabajo;
   private String contrasenia;

  /*Constructores*/ 
   public Empleado(){};
   
    public Empleado(String idEmpleado, String nombre, String direccion, String correo, String telefono, 
            Date fechaNacimiento,String cedula,String contrasenia,String idTrabajo ) {
        super(cedula, nombre, direccion, correo, fechaNacimiento, telefono);
        this.idEmpleado = idEmpleado;
        this.idTrabajo = idTrabajo;
        this.contrasenia = contrasenia;
    }
/*Getter and Setter*/
    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(String idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
