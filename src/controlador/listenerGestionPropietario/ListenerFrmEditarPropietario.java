package controlador.listenerGestionPropietario;

import Vista.FrmEditarPropietario;
import controlador.ValidarCampos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Esta clase agraga los listener a los botones del formulario editar
 * propietario haciendo uso de la interfaz ActionListener y extiende de la clase
 * KeyAdapter para agregar listener de tipeo a las cajas de texto.
 *
 * @param cs {@link ActionListener } clase que permite agregar a los
 * escuchadores a los botones.
 * @param cs {@link KeyAdapter } clase que implementa KeyListener.
 * @author Grupo E
 */
public class ListenerFrmEditarPropietario extends KeyAdapter implements ActionListener {

    FrmEditarPropietario frmEditarPropietario;
    private final ValidarCampos validarCampos = new ValidarCampos();

    public ListenerFrmEditarPropietario(FrmEditarPropietario frmEditarPropietario) {
        this.frmEditarPropietario = frmEditarPropietario;
    }

    /**
     * Este metodo recibe el evento del boton presionado y lo compara para tomar
     * un desicion.
     *
     * @param cs {@link ActionEvent } captura el evento que se causo al
     * presionar un boton y ejecuta la acciones definidas.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmEditarPropietario.getBtnModificar()) {
            if (validarCampos.validarCampoVacio(frmEditarPropietario) && validarEdadIngreso()) {
                frmEditarPropietario.modificarPropietario();
            }
        } else if (e.getSource() == frmEditarPropietario.getBtnCancelar()) {
            frmEditarPropietario.dispose();
        }
    }

    /**
     * Este metodo recibe el evento de la caja de texto que esta siendo tipeado 
     * las compara para saber cual es y realiza las acciones predefinidas.
     *
     * @param cs {@link KeyEvent } captura el evento que se causo al
     * presionar un boton y ejecuta la acciones definidas.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == frmEditarPropietario.getTxtNombre()) {
            validarCampos.validaLetras(e);
        } else if (e.getSource() == frmEditarPropietario.getTxtTelefono()) {
            validarCampos.validarTelefono(e, frmEditarPropietario.getTxtTelefono());
        } else if (e.getSource() == frmEditarPropietario.getTxtAnioVehiculo()) {
            validarCampos.validarAnioVehiculo(e, frmEditarPropietario.getTxtAnioVehiculo());
        }
    }

    /*Valida la edad haciendo uso de la clase ValidarCampos*/
    public boolean validarEdadIngreso() {
        return validarCampos.validarEdad(frmEditarPropietario.getDtcFechaNacimiento().getDate());
    }
}
