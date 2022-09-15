package controlador.listenerGestionPropietario;

import Vista.FrmConsultarPropietario;
import controlador.PropietarioDB;
import controlador.ValidarCampos;
import controlador.VehiculoDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.CuentaBancaria;
import modelo.Propietario;
import modelo.Vehiculo;

/**
 * Esta clase agraga los listener a los botones del formulario consultar
 * propietario haciendo uso de la interfaz ActionListener .
 *
 * @param cs {@link ActionListener } clase que permite agregar a los
 * escuchadores a los botones.
 * @author Grupo E
 */
public class ListenerFrmConsultarPropietario implements ActionListener {

    private FrmConsultarPropietario frmConsultarPropietario;
    private PropietarioDB propietarioDB;
    private Propietario propietario;
    private CuentaBancaria cuentaBancaria;
    private ValidarCampos validarCampos;
    private ArrayList<Vehiculo> listaVehiculo;
    private VehiculoDB vehiculoDB;

    public ListenerFrmConsultarPropietario(FrmConsultarPropietario frmConsultarPropietario) {
        this.frmConsultarPropietario = frmConsultarPropietario;
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
        if (e.getSource() == frmConsultarPropietario.getBtnSalir()) {
            frmConsultarPropietario.dispose();
        }
    }

    /**
     * Acontinuacion se llena los diferentes campos del Formulario consultar
     * Propietario
     *
     */
    public void llenarCamposConsultarPropietario() {
        propietarioDB = new PropietarioDB();
        vehiculoDB = new VehiculoDB();
        propietario = new Propietario();
        cuentaBancaria = new CuentaBancaria();
        if (frmConsultarPropietario.getDomiciliado().equals("true")) {
            propietarioDB.consultarPropietarioDomiciliado(
                    cuentaBancaria, propietario, frmConsultarPropietario.getCedula());
            llenarDatosPropietario(propietario);
            llenarDatosCuentaBancaria(cuentaBancaria);
        } else {
            propietarioDB.consultarPropietarioNoDomiciliado(
                    propietario, frmConsultarPropietario.getCedula());
            propietarioDB.consultarPropietarioNoDomiciliado(propietario, frmConsultarPropietario.getCedula());
            llenarDatosPropietario(propietario);
        }
        listaVehiculo = vehiculoDB.listarVehiculo(frmConsultarPropietario.getCedula());
        llenarDatosVechiculo(listaVehiculo);
    }

    private void llenarDatosPropietario(Propietario propietario) {
        validarCampos = new ValidarCampos();
        frmConsultarPropietario.getTxtCedula().setText(propietario.getCedula());
        frmConsultarPropietario.getTxtNombre().setText(propietario.getNombre());
        frmConsultarPropietario.getTxtCorreo().setText(propietario.getCorreo());
        frmConsultarPropietario.getTxtTelefono().setText(propietario.getTelefono());
        frmConsultarPropietario.getTxtDireccion().setText(propietario.getDireccion());
        frmConsultarPropietario.getTxtFechaNacimiento().setText(
                validarCampos.validarFormatoFecha(propietario.getFechaNacimiento()));
        frmConsultarPropietario.getCmbEstadoPropietario().setSelectedIndex(propietario.getId_estado_propietario());
        frmConsultarPropietario.getrBtnDomiciliado().setSelected(propietario.getDomiciliado());
    }

    private void llenarDatosCuentaBancaria(CuentaBancaria cuentaBancaria) {
        frmConsultarPropietario.getTxtCtaBancaria().setText(cuentaBancaria.getNumeroCuentaBancaria());
        frmConsultarPropietario.getTxtCVV().setText(cuentaBancaria.getCvv());
        frmConsultarPropietario.getCmbMes().setSelectedIndex(cuentaBancaria.getMes() - 1);
        frmConsultarPropietario.getTxtAnio().setText(cuentaBancaria.getAnio() + "");
        frmConsultarPropietario.getCmbTipoCtaBancaria().setSelectedIndex(cuentaBancaria.getIdTipoCuenta());
        frmConsultarPropietario.getTxtSaldo().setText(String.valueOf(cuentaBancaria.getSaldo()));
        frmConsultarPropietario.getCmbBanco().setSelectedIndex(cuentaBancaria.getIdBanco());
    }

    private void llenarDatosVechiculo(ArrayList<Vehiculo> listaVehiculo) {

        for (int i = 0; i < listaVehiculo.size(); i++) {
            Object[] fila = {
                listaVehiculo.get(i).getPlaca(),
                listaVehiculo.get(i).getMarca(),
                listaVehiculo.get(i).getModelo(),
                listaVehiculo.get(i).getAnioVehiculo(),
                listaVehiculo.get(i).getId_tipo_impuesto()
            };
            frmConsultarPropietario.getModeloTabla().addRow(fila);
        }
    }
}
