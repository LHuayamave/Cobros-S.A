/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Sellan
 */
public class Solicitud {
    
    private String id;
    private String id_Factura;
    private String estado;
    private String descripcion;
    private String tipo;
    
    public Solicitud(){}

    public Solicitud(String id, String id_Factura, String estado, String descripcion, String tipo) {
        this.id = id;
        this.id_Factura = id_Factura;
        this.estado = estado;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Solicitud{" + "idSolicitud=" + id + ", id_Factura=" + id_Factura + ", estado=" + estado + ", descripcion=" + descripcion + ", tipo=" + tipo + '}';
    }

    
}
