package controlador.listenerGestionPropietario;

import Vista.PnlRegistroPropietario;
import controlador.PropietarioDB;
import controlador.ValidarCampos;
import controlador.VehiculoDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import modelo.CuentaBancaria;
import modelo.EvaluarCampo;
import modelo.Propietario;
import modelo.Vehiculo;

/**
 * Esta clase agraga los listener a los botones al panel registrar propietario
 * haciendo uso de la interfaz ActionListener y extiende de la clase KeyAdapter
 * para agregar listener de tipeo a las cajas de texto.
 *
 * @param cs {@link ActionListener } clase que permite agregar a los
 * escuchadores a los botones.
 * @param cs {@link KeyAdapter } clase que implementa KeyListener.
 * @author Grupo E
 */
public class ListenerPnlRegistroPropietario extends KeyAdapter implements ActionListener, MouseListener {

    private final PnlRegistroPropietario pnlRegistroPropietario;
    private final PropietarioDB propietarioDB = new PropietarioDB();
    private final VehiculoDB vehiculoDB = new VehiculoDB();
    private final ValidarCampos validarCampos = new ValidarCampos();
    private Propietario propietario;
    private Vehiculo vehiculo;
    private CuentaBancaria cuentaBancaria;

    // Obtiene lel formulario que lo esta utilizando.
    public ListenerPnlRegistroPropietario(PnlRegistroPropietario pnlRegistroPropietario) {
        this.pnlRegistroPropietario = pnlRegistroPropietario;
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
        if (e.getSource() == pnlRegistroPropietario.getBtnRegistrar()) {
            extraerDatosPropietario();
        } else if (e.getSource() == pnlRegistroPropietario.getBtnCancelar()) {
            pnlRegistroPropietario.limpiarCampos();
        }
    }

    /**
     * Este metodo recibe el evento de la caja de texto que esta siendo tipeado
     * las compara para saber cual es y realiza las acciones predefinidas.
     *
     * @param cs {@link KeyEvent } captura el evento que se causo al presionar
     * un boton y ejecuta la acciones definidas.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == pnlRegistroPropietario.getTxtCedula()) {
            validarCampos.validarCampoNumerico(new EvaluarCampo(
                    e, pnlRegistroPropietario.getTxtCedula(), 9));
            validarCampos.verificarSiExistePropietario(pnlRegistroPropietario.getTxtCedula());
        } else if (e.getSource() == pnlRegistroPropietario.getTxtNombre()) {
            validarCampos.validaLetras(e);
        } else if (e.getSource() == pnlRegistroPropietario.getTxtTelefono()) {
            validarCampos.validarCampoNumerico(new EvaluarCampo(
                    e, pnlRegistroPropietario.getTxtTelefono(), 9));
        } else if (e.getSource() == pnlRegistroPropietario.getTxtPlaca()) {
            validarCampos.verificarSiExistePlaca(pnlRegistroPropietario.getTxtPlaca());
        } else if (e.getSource() == pnlRegistroPropietario.getTxtAnioVehiculo()) {
            validarCampos.validarCampoNumerico(new EvaluarCampo(
                    e, pnlRegistroPropietario.getTxtAnioVehiculo(), 3));
        } else if (e.getSource() == pnlRegistroPropietario.getTxtCVV()) {
            validarCampos.validarCampoNumerico(new EvaluarCampo(
                    e, pnlRegistroPropietario.getTxtCVV(), 2));
        } else if (e.getSource() == pnlRegistroPropietario.getTxtAnio()) {
            validarCampos.validarCampoNumerico(new EvaluarCampo(
                    e, pnlRegistroPropietario.getTxtAnio(), 3));
        }
    }

    public void extraerDatosPropietario() {
        cuentaBancaria = new CuentaBancaria();
        propietario = new Propietario();
        vehiculo = new Vehiculo();
        cuentaBancaria.setNumeroCuentaBancaria(pnlRegistroPropietario.getTxtCtaBancaria().getText());
        cuentaBancaria.setCvv(pnlRegistroPropietario.getTxtCVV().getText());
        cuentaBancaria.setMes(pnlRegistroPropietario.getCmbMes().getSelectedIndex() + 1);
        cuentaBancaria.setAnio(Integer.valueOf(pnlRegistroPropietario.getTxtAnio().getText()));
        cuentaBancaria.setIdTipoCuenta(pnlRegistroPropietario.getCmbTipoCtaBancaria().getSelectedIndex());
        cuentaBancaria.setSaldo(Float.valueOf(pnlRegistroPropietario.getTxtSaldo().getText()));
        cuentaBancaria.setIdBanco(pnlRegistroPropietario.getCmbBanco().getSelectedIndex());

        propietario.setCedula(pnlRegistroPropietario.getTxtCedula().getText());
        propietario.setNombre(pnlRegistroPropietario.getTxtNombre().getText());
        propietario.setCorreo(pnlRegistroPropietario.getTxtCorreo().getText());
        propietario.setTelefono(pnlRegistroPropietario.getTxtTelefono().getText());
        propietario.setDireccion(pnlRegistroPropietario.getTxtDireccion().getText());
        propietario.setFechaNacimiento(
                new java.sql.Date(((Date) pnlRegistroPropietario.getDtcFechaNacimiento().getDate()).getTime()));
        propietario.setId_estado_propietario(pnlRegistroPropietario.getCmbEstadoPropietario().getSelectedIndex());
        propietario.setDomiciliado(pnlRegistroPropietario.getrBtnDomiciliado().isSelected());

        vehiculo.setPlaca(pnlRegistroPropietario.getTxtPlaca().getText());
        vehiculo.setMarca(pnlRegistroPropietario.getTxtMarca().getText());
        vehiculo.setModelo(pnlRegistroPropietario.getTxtModelo().getText());
        vehiculo.setAnioVehiculo(pnlRegistroPropietario.getTxtAnioVehiculo().getText());
        vehiculo.setId_tipo_impuesto(pnlRegistroPropietario.getCmbTipoImpuesto().getSelectedIndex());

        validarCampos(cuentaBancaria, propietario, vehiculo);
    }

    private void validarCampos(CuentaBancaria cuentaBancaria, Propietario propietario, Vehiculo vehiculo) {
        if (propietario.getDomiciliado()) {
            validarRegistroDomiciliado(cuentaBancaria, propietario, vehiculo);
        } else {
            validarRegistroNoDomiciliado(propietario, vehiculo);
        }
    }

    public void validarRegistroDomiciliado(CuentaBancaria cuentaBancaria, Propietario propietario, Vehiculo vehiculo) {
        if (validarCampos.validarDatosCuentaBancaria(cuentaBancaria)
                && validarCampos.validarDatosPropietario(propietario)
                && validarCampos.validarDatosVehiculo(vehiculo)
                && validarEdadIngreso()) {
            propietarioDB.registrarPropietarioDomiciliado(cuentaBancaria, propietario, vehiculo);
        }else{
            validarCampos.mensajeError("Llene todos los campos correctamente.");
        }
    }

    public void validarRegistroNoDomiciliado(Propietario propietario, Vehiculo vehiculo) {
        if (validarCampos.validarDatosPropietario(propietario)
                && validarCampos.validarDatosVehiculo(vehiculo)
                && validarEdadIngreso()) {
            propietarioDB.registrarPropietarioNoDomiciliado(propietario, vehiculo);
        }else{
            validarCampos.mensajeError("Llene todos los campos correctamente.");
        }
    }

    /*Metodo que validad la edad haciendo uso del metodo validarEdad */
    public boolean validarEdadIngreso() {
        return validarCampos.validarEdad(pnlRegistroPropietario.getDtcFechaNacimiento().getDate());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == pnlRegistroPropietario.getrBtnDomiciliado()) {
            isSelectBtnDomiciliado();
        }
    }

    private void isSelectBtnDomiciliado() {
        if (pnlRegistroPropietario.getrBtnDomiciliado().isSelected()) {
            habilitarCtaBancaria(true);
        } else {
            habilitarCtaBancaria(false);
        }
    }

    private void habilitarCtaBancaria(Boolean estado) {
        pnlRegistroPropietario.getTxtCtaBancaria().setEditable(estado);
        pnlRegistroPropietario.getTxtCVV().setEditable(estado);
        pnlRegistroPropietario.getCmbMes().setEnabled(estado);
        pnlRegistroPropietario.getTxtAnio().setEditable(estado);
        pnlRegistroPropietario.getTxtSaldo().setEditable(estado);
        pnlRegistroPropietario.getCmbTipoCtaBancaria().setEnabled(estado);
        pnlRegistroPropietario.getCmbBanco().setEnabled(estado);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
