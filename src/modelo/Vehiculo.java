package modelo;
/**
     * Esta clase  se la utiliza para instanciar objetos vehiculos y guardar su información.
     * @author Grupo E
*/
public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private String anioVehiculo;
    private int id_tipo_impuesto;
    private String idPropietario;

    /*Constructores*/
    public Vehiculo(){}

    public Vehiculo( String placa, String marca, String modelo, String anioVehiculo, int id_tipo_impuesto) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anioVehiculo = anioVehiculo;
        this.id_tipo_impuesto = id_tipo_impuesto;
    } 
    
    /*Getter and Setter*/
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public String getAnioVehiculo() {
        return anioVehiculo;
    }

    public void setAnioVehiculo(String anioVehiculo) {
        this.anioVehiculo = anioVehiculo;
    }

    public int getId_tipo_impuesto() {
        return id_tipo_impuesto;
    }

    public void setId_tipo_impuesto(int id_tipo_impuesto) {
        this.id_tipo_impuesto = id_tipo_impuesto;
    }

    public String getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(String idPropietario) {
        this.idPropietario = idPropietario;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "placa=" + placa + ", marca=" + marca + ", modelo=" +
                modelo + ", anioVehiculo=" + anioVehiculo + ", id_tipo_impuesto=" +
                id_tipo_impuesto + ", idPropietario=" + idPropietario + '}';
    }
    
    
}