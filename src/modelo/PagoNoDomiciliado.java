package modelo;

/**
 *
 * @author Luis
 */
public class PagoNoDomiciliado {
    private String cedulaPropietario;
    private String nombrePropietario;
    private String tipoImpuesto;
    private float valorImpuesto;
    private int mesPago;

    public PagoNoDomiciliado(String cedulaPropietario, String nombrePropietario, String tipoImpuesto, float valorImpuesto, int mesPago) {
        this.cedulaPropietario = cedulaPropietario;
        this.nombrePropietario = nombrePropietario;
        this.tipoImpuesto = tipoImpuesto;
        this.valorImpuesto = valorImpuesto;
        this.mesPago = mesPago;
    }

    public PagoNoDomiciliado() {

    }

    public String getCedulaPropietario() {
        return cedulaPropietario;
    }

    public void setCedulaPropietario(String cedulaPropietario) {
        this.cedulaPropietario = cedulaPropietario;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(String tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public float getValorImpuesto() {
        return valorImpuesto;
    }

    public void setValorImpuesto(float valorImpuesto) {
        this.valorImpuesto = valorImpuesto;
    }

    public int getMesPago() {
        return mesPago;
    }

    public void setMesPago(int mesPago) {
        this.mesPago = mesPago;
    }
    
    
}