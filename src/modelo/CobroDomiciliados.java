/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author NIEVES
 */
public class CobroDomiciliados {

    private String ctaBancaria;
    private String cedulaPropietario;
    private String nombrePropietario;
    private String tipoImpuesto;
    private float valorImpuesto;
    private float saldo;
    private int mesPago;

    public CobroDomiciliados(String ctaBancaria, String cedulaPropietario, String nombrePropietario, String tipoImpuesto, float valorImpuesto, float saldo, int mesPago) {
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

}
