
package modelo;

/**
 *
 * @author Soldado
 */
public class CuentaBancaria {
    private String numeroCuentaBancaria;
    private String cvv;
    private int mes;
    private int anio;
    private int idTipoCuenta;
    private float saldo;
    private int idBanco;
    
    public CuentaBancaria(){}

    public CuentaBancaria(String numeroCuentaBancaria, String cvv, int mes, int anio, int idTipoCuenta, 
            float saldo, int idBanco) {
        this.numeroCuentaBancaria = numeroCuentaBancaria;
        this.cvv = cvv;
        this.mes = mes;
        this.anio = anio;
        this.idTipoCuenta = idTipoCuenta;
        this.saldo = saldo;
        this.idBanco = idBanco;
    }

    
    public int getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(int idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }
    

    public String getNumeroCuentaBancaria() {
        return numeroCuentaBancaria;
    }

    public void setNumeroCuentaBancaria(String numeroCuentaBancaria) {
        this.numeroCuentaBancaria = numeroCuentaBancaria;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }
    
}
