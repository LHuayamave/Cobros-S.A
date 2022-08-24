package modelo;
/**
     * Esta es una clase extiende de la clase absatracta Persona y se la utiliza para instanciar objetos de Propietario
     * y guardar información.
     * @author Grupo E
*/
import java.util.Date;

public class Propietario extends Persona{
    private int mesPago;
    private int id_estado_propietario;
    private boolean domiciliado;
 /*Constructores*/ 
    public Propietario(){};

    public Propietario(String cedula, String nombre, String direccion, String correo, Date fechaNacimiento, 
            String telefono,int mesPago, int id_estado_propietario, boolean domiciliado) {
        super(cedula, nombre, direccion, correo, fechaNacimiento, telefono);
        this.mesPago = mesPago;
        this.id_estado_propietario = id_estado_propietario;
        this.domiciliado = domiciliado;
    }

    public int getMesPago() {
        return mesPago; 
    }

    public void setMesPago(int mesPago) {
        this.mesPago = mesPago;
    }

    public int getId_estado_propietario() {
        return id_estado_propietario;
    }

    public void setId_estado_propietario(int id_estado_propietario) {
        this.id_estado_propietario = id_estado_propietario;
    }

    public boolean getDomiciliado() {
        return domiciliado;
    }

    public void setDomiciliado(boolean domiciliado) {
        this.domiciliado = domiciliado;
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
    
    public int obtenerMesPago(){
        String ultimoDigito = this.cedula.substring(this.cedula.length() - 1);
        return Integer.valueOf(ultimoDigito);
    }
    
    @Override
    public String toString() {
        return "Propietario: \n Cedula: "+ cedula+", Nombre: " +nombre+ ", Correo: "+ correo+
                ",\n Telefono: "+ telefono+ ", Direccion: "+ direccion+ ", fechaNacimiento: "+ fechaNacimiento+
                ",\n Mes Pago: "+ obtenerMesPago()+ ", Id_estado_propietario: "+id_estado_propietario+ " "; 
    }

}
