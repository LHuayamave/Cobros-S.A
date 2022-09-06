package controlador.listenerGestionPropietario;

import Vista.FrmEditarVehiculo;
import controlador.ValidarCampos;
import controlador.VehiculoDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import modelo.EvaluarCampo;
import modelo.Vehiculo;

/**
 *
 * @author Soldado
 */
public class ListenerFrmEditarVehiculo extends KeyAdapter implements ActionListener {

    private FrmEditarVehiculo frmEditarVehiculo;

    private ValidarCampos validarCampos = new ValidarCampos();
    private VehiculoDB vehiculoDB;
    private Vehiculo vehiculo;

    public ListenerFrmEditarVehiculo(FrmEditarVehiculo frmEditarVehiculo) {
        this.frmEditarVehiculo = frmEditarVehiculo;
    }

    public void llenarCampos() {
        vehiculo = new Vehiculo();
        vehiculoDB = new VehiculoDB();
        vehiculo.setPlaca(frmEditarVehiculo.getPlaca());
        vehiculo = vehiculoDB.verVehiculo(vehiculo);
        frmEditarVehiculo.getTxtPlaca().setText(vehiculo.getPlaca());
        frmEditarVehiculo.getTxtMarca().setText(vehiculo.getMarca());
        frmEditarVehiculo.getTxtModelo().setText(vehiculo.getModelo());
        frmEditarVehiculo.getTxtAnioVehiculo().setText(vehiculo.getAnioVehiculo());
        frmEditarVehiculo.getCmbTipoImpuesto().setSelectedIndex(vehiculo.getId_tipo_impuesto());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmEditarVehiculo.getBtnModificar()) {
            validarCampos();
        } else if (e.getSource() == frmEditarVehiculo.getBtnCancelar()) {
            frmEditarVehiculo.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == frmEditarVehiculo.getTxtAnioVehiculo()) {
            validarCampos.validarCampoNumerico(new EvaluarCampo(
                    e, frmEditarVehiculo.getTxtAnioVehiculo(), 3));
        }
    }

    private Vehiculo extraerDatosVehiculo() {
        vehiculo = new Vehiculo();
        vehiculo.setPlaca(frmEditarVehiculo.getTxtPlaca().getText());
        vehiculo.setMarca(frmEditarVehiculo.getTxtMarca().getText());
        vehiculo.setModelo(frmEditarVehiculo.getTxtModelo().getText());
        vehiculo.setAnioVehiculo(frmEditarVehiculo.getTxtAnioVehiculo().getText());
        vehiculo.setId_tipo_impuesto(frmEditarVehiculo.getCmbTipoImpuesto().getSelectedIndex());
        return vehiculo;
    }

    private void validarCampos() {
        vehiculoDB = new VehiculoDB();
        vehiculo = extraerDatosVehiculo();
        if (validarCampos.validarDatosVehiculo(vehiculo)) {
            validarCampos.confirmarRegistrosModificados(
                    vehiculoDB.modificarVehiculo(vehiculo)
            );
            frmEditarVehiculo.getListenerFrmEditarPropietario().getFrmEditarPropietario().LimpiarFormulario();
            frmEditarVehiculo.getListenerFrmEditarPropietario().llenarDatosVehiculo(
                    vehiculoDB.listarVehiculo(
                            frmEditarVehiculo.getListenerFrmEditarPropietario().getFrmEditarPropietario().getCedula()
                    )
            );
            frmEditarVehiculo.dispose();
        }
    }

}
