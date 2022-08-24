package controlador.Excepciones;

/**
     * Este es una excepcion pèrsonalizada que se usa para el control del ingreso de la placa.
     * @author Grupo E
*/
public class PlacaException extends Exception{
    /**
     * Metodo que permite ejecutar nuevas excepciones.
     * @param cs {@link Exception} clase que permite crear nuevas excepciones.
     * @return rs regresa un mensaje personalizado cuando se llame esta excepcion 
     * @throws PlacaException
     */
    public PlacaException(){
        super("Esta placa ya existe");
    }
}
