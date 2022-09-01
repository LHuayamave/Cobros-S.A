
package modelo;

import java.util.Date;


public class Factura extends Persona{
    private String id_factura;
    private Date fecha_emision;
    private Date fecha_vencimiento;
    private Date fecha_pago_factura;
    private float valor_recargo;
    private int id_estado_factura;
    private float valor_total;
    private String aviso_pago;
    private String multa;
    private String aviso_recargo;

    public Factura(){};
    
    public Factura(String id_factura, Date fecha_emision,
            Date fecha_vencimiento, Date fecha_pago_factura, float valor_recargo,
            int id_estado_factura, float valor_total, String aviso_pago, String multa, 
            String aviso_recargo, String cedula, String nombre, String direccion, 
            String correo, Date fechaNacimiento, String telefono) {

        super(cedula, nombre, direccion, correo, fechaNacimiento, telefono);
        this.id_factura = id_factura;
        this.fecha_emision = fecha_emision;
        this.fecha_vencimiento = fecha_vencimiento;
        this.fecha_pago_factura = fecha_pago_factura;
        this.valor_recargo = valor_recargo;
        this.id_estado_factura = id_estado_factura;
        this.valor_total = valor_total;
        this.aviso_pago = aviso_pago;
        this.multa = multa;
        this.aviso_recargo = aviso_recargo;
    }

    public String getId_factura() {
        return id_factura;
    }

    public void setId_factura(String id_factura) {
        this.id_factura = id_factura;
    }

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Date getFecha_pago_factura() {
        return fecha_pago_factura;
    }

    public void setFecha_pago_factura(Date fecha_pago_factura) {
        this.fecha_pago_factura = fecha_pago_factura;
    }

    public float getValor_recargo() {
        return valor_recargo;
    }

    public void setValor_recargo(float valor_recargo) {
        this.valor_recargo = valor_recargo;
    }

    public int getId_estado_factura() {
        return id_estado_factura;
    }

    public void setId_estado_factura(int id_estado_factura) {
        this.id_estado_factura = id_estado_factura;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public String getAviso_pago() {
        return aviso_pago;
    }

    public void setAviso_pago(String aviso_pago) {
        this.aviso_pago = aviso_pago;
    }

    public String getMulta() {
        return multa;
    }

    public void setMulta(String multa) {
        this.multa = multa;
    }

    public String getAviso_recargo() {
        return aviso_recargo;
    }

    public void setAviso_recargo(String aviso_recargo) {
        this.aviso_recargo = aviso_recargo;
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