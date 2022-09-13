package modelo;

/**
 * Esta es una clase utilizada para instanciar objetos de los cobros de los
 * propietarios domiciliados y guardar información de estos.
 *
 * @author Grupo E
 */
public class CobroDomiciliados {
private int idFactura;
    private String ctaBancaria;
    private String cedulaPropietario;
    private String nombrePropietario;
    private String tipoImpuesto;
    private float valorImpuesto;
    private float saldo;
    private int mesPago;

    /*Constructores*/

    public CobroDomiciliados(int idFactura, String ctaBancaria, String cedulaPropietario, String nombrePropietario, String tipoImpuesto, float valorImpuesto, float saldo, int mesPago) {
        this.idFactura = idFactura;
        this.ctaBancaria = ctaBancaria;
        this.cedulaPropietario = cedulaPropietario;
        this.nombrePropietario = nombrePropietario;
        this.tipoImpuesto = tipoImpuesto;
        this.valorImpuesto = valorImpuesto;
        this.saldo = saldo;
        this.mesPago = mesPago;
    }
  
  

    public CobroDomiciliados() {
    }

    /*Getter and Setter*/
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

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getCtaBancaria() {
        return ctaBancaria;
    }

    public void setCtaBancaria(String ctaBancaria) {
        this.ctaBancaria = ctaBancaria;
    }

    public int getMesPago() {
        return mesPago;
    }

    public void setMesPago(int mesPago) {
        this.mesPago = mesPago;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

}
