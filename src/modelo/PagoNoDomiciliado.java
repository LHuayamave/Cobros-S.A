package modelo;

/**
 *
 * @author Luis
 */
public class PagoNoDomiciliado {
    private String id_factura;
    private String cedulaPropietario;
    private String nombrePropietario;
    private String tipoImpuesto;
    private float valorImpuesto;
    private int mesPago;
    private String valor;

    public PagoNoDomiciliado(String id_factura, String cedulaPropietario, String nombrePropietario, String tipoImpuesto, float valorImpuesto, int mesPago, String valor) {
        this.id_factura = id_factura;
        this.cedulaPropietario = cedulaPropietario;
        this.nombrePropietario = nombrePropietario;
        this.tipoImpuesto = tipoImpuesto;
        this.valorImpuesto = valorImpuesto;
        this.mesPago = mesPago;
        this.valor = valor;
    }
    
    public PagoNoDomiciliado(){
        
    }

    public String getId_factura() {
        return id_factura;
    }

    public void setId_factura(String id_factura) {
        this.id_factura = id_factura;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}