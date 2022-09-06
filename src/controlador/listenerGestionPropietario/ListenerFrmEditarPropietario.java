package controlador.listenerGestionPropietario;

import Vista.FrmEditarPropietario;
import controlador.PropietarioDB;
import controlador.ValidarCampos;
import controlador.VehiculoDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import modelo.CuentaBancaria;
import modelo.EvaluarCampo;
import modelo.Propietario;
import modelo.Vehiculo;

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
public class ListenerFrmEditarPropietario extends KeyAdapter implements ActionListener, MouseListener {

    FrmEditarPropietario frmEditarPropietario;
    private PropietarioDB propietarioDB;
    private Propietario propietario;
    private CuentaBancaria cuentaBancaria;
    private ValidarCampos validarCampos;
    private ArrayList<Vehiculo> listaVehiculo;
    private VehiculoDB vehiculoDB;
    
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
            
        } else if (e.getSource() == frmEditarPropietario.getBtnModificarVehiculo()) {
            //
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
        if (e.getSource() == frmEditarPropietario.getTxtCedula()) {
            validarCampos.validarCampoNumerico(new EvaluarCampo(
                    e, frmEditarPropietario.getTxtCedula(), 9));
        } else if (e.getSource() == frmEditarPropietario.getTxtNombre()) {
            validarCampos.validaLetras(e);
        } else if (e.getSource() == frmEditarPropietario.getTxtTelefono()) {
            validarCampos.validarCampoNumerico(new EvaluarCampo(
                    e, frmEditarPropietario.getTxtTelefono(), 9));
        } else if (e.getSource() == frmEditarPropietario.getTxtCVV()) {
            validarCampos.validarCampoNumerico(new EvaluarCampo(
                    e, frmEditarPropietario.getTxtCVV(), 2));
        } else if (e.getSource() == frmEditarPropietario.getTxtAnio()) {
            validarCampos.validarCampoNumerico(new EvaluarCampo(
                    e, frmEditarPropietario.getTxtAnio(), 3));
        }
    }
    
    public void llenarCamposEditarPropietario() {
        propietarioDB = new PropietarioDB();
        cuentaBancaria = new CuentaBancaria();
        propietario = new Propietario();
        vehiculoDB = new VehiculoDB();
        if (frmEditarPropietario.getDomiciliado().equals("true")) {
            propietarioDB.consultarPropietarioDomiciliado(
                    cuentaBancaria, propietario, frmEditarPropietario.getCedula());
            llenarDatosPropietario(propietario);
            llenarDatosCuentaBancaria(cuentaBancaria);
            habilitarCtaBancaria(true);
        } else {
            propietarioDB.consultarPropietarioNoDomiciliado(
                    propietario, frmEditarPropietario.getCedula());
            propietarioDB.consultarPropietarioNoDomiciliado(propietario, frmEditarPropietario.getCedula());
            llenarDatosPropietario(propietario);
        }
        listaVehiculo = vehiculoDB.listarVehiculo(frmEditarPropietario.getCedula());
        llenarDatosVechiculo(listaVehiculo);
    }
    
    private void llenarDatosPropietario(Propietario propietario) {
        validarCampos = new ValidarCampos();
        frmEditarPropietario.getTxtCedula().setText(propietario.getCedula());
        frmEditarPropietario.getTxtNombre().setText(propietario.getNombre());
        frmEditarPropietario.getTxtCorreo().setText(propietario.getCorreo());
        frmEditarPropietario.getTxtTelefono().setText(propietario.getTelefono());
        frmEditarPropietario.getTxtDireccion().setText(propietario.getDireccion());
        frmEditarPropietario.getDtcFechaNacimiento().setDate(propietario.getFechaNacimiento());
        frmEditarPropietario.getCmbEstadoPropietario().setSelectedIndex(propietario.getId_estado_propietario());
        frmEditarPropietario.getrBtnDomiciliado().setSelected(propietario.getDomiciliado());
    }
    
    private void llenarDatosCuentaBancaria(CuentaBancaria cuentaBancaria) {
        frmEditarPropietario.setCuentaBancariaAntigua(cuentaBancaria.getNumeroCuentaBancaria()); 
        frmEditarPropietario.getTxtCtaBancaria().setText(cuentaBancaria.getNumeroCuentaBancaria());
        frmEditarPropietario.getTxtCVV().setText(cuentaBancaria.getCvv());
        frmEditarPropietario.getCmbMes().setSelectedIndex(cuentaBancaria.getMes() - 1);
        frmEditarPropietario.getTxtAnio().setText(cuentaBancaria.getAnio() + "");
        frmEditarPropietario.getCmbTipoCtaBancaria().setSelectedIndex(cuentaBancaria.getIdTipoCuenta());
        frmEditarPropietario.getTxtSaldo().setText(String.valueOf(cuentaBancaria.getSaldo()));
        frmEditarPropietario.getCmbBanco().setSelectedIndex(cuentaBancaria.getIdBanco());
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
            frmEditarPropietario.getModeloTabla().addRow(fila);
        }
    }
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == frmEditarPropietario.getrBtnDomiciliado()) {
            isSelectBtnDomiciliado();
        }
    }

    private void isSelectBtnDomiciliado() {
        if (frmEditarPropietario.getrBtnDomiciliado().isSelected()) {
            habilitarCtaBancaria(true);
        } else {
            habilitarCtaBancaria(false);
        }
    }
    
    private void habilitarCtaBancaria(Boolean estado) {
        frmEditarPropietario.getTxtCVV().setEditable(estado);
        frmEditarPropietario.getCmbMes().setEnabled(estado);
        frmEditarPropietario.getTxtAnio().setEditable(estado);
        frmEditarPropietario.getTxtSaldo().setEditable(estado);
        frmEditarPropietario.getCmbTipoCtaBancaria().setEnabled(estado);
        frmEditarPropietario.getCmbBanco().setEnabled(estado);
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
    
    /*Valida la edad haciendo uso de la clase ValidarCampos*/
    public boolean validarEdadIngreso() {
        return validarCampos.validarEdad(frmEditarPropietario.getDtcFechaNacimiento().getDate());
    }
}
