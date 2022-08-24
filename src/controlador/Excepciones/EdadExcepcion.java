package controlador.Excepciones;

/**
 * Este es una excepcion pèrsonalizada que se usa para el control de la edad.
 *
 * @author Grupo E
 */
public class EdadExcepcion extends Exception {

    /**
     * Metodo que permite ejecutar nuevas excepciones.
     *
     * @param cs {@link Exception} clase que permite crear nuevas excepciones.
     * @return rs regresa un mensaje personalizado cuando se llame esta
     * excepcion
     * @throws EdadExcepcion
     */
    public EdadExcepcion() {
        super("No se puede ingresar menores de edad");
    }
}
