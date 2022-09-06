package controlador.listenerGestionPropietario;

import Vista.FrmEditarPropietario;
import Vista.FrmEditarVehiculo;
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
import java.util.Date;
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
    private final PropietarioDB propietarioDB = new PropietarioDB();
    private final ValidarCampos validarCampos = new ValidarCampos();
    private Propietario propietario;
    private CuentaBancaria cuentaBancaria;
    private ArrayList<Vehiculo> listaVehiculo;
    private VehiculoDB vehiculoDB;
    private int confirmarCambio;

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
            extraerDatosPropietario();
        } else if (e.getSource() == frmEditarPropietario.getBtnModificarVehiculo()) {
            validarSeleccionVehiculo();
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
        llenarDatosVehiculo(listaVehiculo);
    }
    
    private void llenarDatosPropietario(Propietario propietario) {
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
        frmEditarPropietario.setAntiguaCtaBancaria(cuentaBancaria.getNumeroCuentaBancaria()); 
        frmEditarPropietario.getTxtCtaBancaria().setText(cuentaBancaria.getNumeroCuentaBancaria());
        frmEditarPropietario.getTxtCVV().setText(cuentaBancaria.getCvv());
        frmEditarPropietario.getCmbMes().setSelectedIndex(cuentaBancaria.getMes() - 1);
        frmEditarPropietario.getTxtAnio().setText(cuentaBancaria.getAnio() + "");
        frmEditarPropietario.getCmbTipoCtaBancaria().setSelectedIndex(cuentaBancaria.getIdTipoCuenta());
        frmEditarPropietario.getTxtSaldo().setText(String.valueOf(cuentaBancaria.getSaldo()));
        frmEditarPropietario.getCmbBanco().setSelectedIndex(cuentaBancaria.getIdBanco());
    }
    public void llenarDatosVehiculo(ArrayList<Vehiculo> listaVehiculo) {

        for (int i = 0; i < listaVehiculo.size(); i++) {
            Object[] fila = {
                listaVehiculo.get(i).getPlaca(),
                listaVehiculo.get(i).getMarca(),
                listaVehiculo.get(i).getModelo(),
                listaVehiculo.get(i).getAnioVehiculo(),
                listaVehiculo.get(i).getId_tipo_impuesto()
            };
            frmEditarPropietario.getModeloTablaVehiculo().addRow(fila);
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
        frmEditarPropietario.getTxtCtaBancaria().setEditable(estado);
        frmEditarPropietario.getTxtCVV().setEditable(estado);
        frmEditarPropietario.getCmbMes().setEnabled(estado);
        frmEditarPropietario.getTxtAnio().setEditable(estado);
        frmEditarPropietario.getTxtSaldo().setEditable(estado);
        frmEditarPropietario.getCmbTipoCtaBancaria().setEnabled(estado);
        frmEditarPropietario.getCmbBanco().setEnabled(estado);
    }
    
    /*Valida la edad haciendo uso de la clase ValidarCampos*/
    public boolean validarEdadIngreso() {
        return validarCampos.validarEdad(frmEditarPropietario.getDtcFechaNacimiento().getDate());
    }
    
    public void extraerDatosPropietario() {
        cuentaBancaria = new CuentaBancaria();
        propietario = new Propietario();
        cuentaBancaria.setNumeroCuentaBancaria(frmEditarPropietario.getTxtCtaBancaria().getText());
        cuentaBancaria.setCvv(frmEditarPropietario.getTxtCVV().getText());
        cuentaBancaria.setMes(frmEditarPropietario.getCmbMes().getSelectedIndex()+1);
        cuentaBancaria.setAnio(Integer.valueOf(frmEditarPropietario.getTxtAnio().getText()));
        cuentaBancaria.setIdTipoCuenta(frmEditarPropietario.getCmbTipoCtaBancaria().getSelectedIndex()); 
        cuentaBancaria.setSaldo(Float.valueOf(frmEditarPropietario.getTxtSaldo().getText())); 
        cuentaBancaria.setIdBanco(frmEditarPropietario.getCmbBanco().getSelectedIndex());
        
        propietario.setCedula(frmEditarPropietario.getTxtCedula().getText());
        propietario.setNombre(frmEditarPropietario.getTxtNombre().getText());
        propietario.setCorreo(frmEditarPropietario.getTxtCorreo().getText());
        propietario.setTelefono(frmEditarPropietario.getTxtTelefono().getText());
        propietario.setDireccion(frmEditarPropietario.getTxtDireccion().getText());
        propietario.setFechaNacimiento(
                new java.sql.Date(((Date) frmEditarPropietario.getDtcFechaNacimiento().getDate()).getTime()));
        propietario.setId_estado_propietario(frmEditarPropietario.getCmbEstadoPropietario().getSelectedIndex());
        propietario.setDomiciliado(frmEditarPropietario.getrBtnDomiciliado().isSelected());
 
        validarCampos(cuentaBancaria,propietario);
    }
    
    private void validarCampos(CuentaBancaria cuentaBancaria, Propietario propietario) {
        if(propietario.getDomiciliado()){
            validarRegistroDomiciliado(cuentaBancaria,propietario);
        }else{
            validarRegistroNoDomiciliado(propietario);
        }
    }
    
    public void validarRegistroNoDomiciliado(Propietario propietario) {
        if (validarCampos.validarDatosPropietario(propietario)
                && validarEdadIngreso()) {
            verficarAntiguaCuentaBancaria( propietario);
        }else{
            validarCampos.mensajeError("Llene todos los campos correctamente.");
        }
    }
    
    public void validarRegistroDomiciliado(CuentaBancaria cuentaBancaria, Propietario propietario) {
        if (validarCampos.validarDatosCuentaBancaria(cuentaBancaria)
                && validarCampos.validarDatosPropietario(propietario)
                && validarEdadIngreso()) {
            verficarAntiguaCuentaBancaria(cuentaBancaria, propietario);
        }else{
            validarCampos.mensajeError("Llene todos los campos correctamente.");
        }
    }
    public void verficarAntiguaCuentaBancaria( Propietario propietario){
        System.out.println("antigua cuenta bancaria: " +frmEditarPropietario.getAntiguaCtaBancaria());
        if(frmEditarPropietario.getAntiguaCtaBancaria().isEmpty()){
            propietarioDB.confirmarRegistrosModificados( 
                    propietarioDB.modificarPropietarioNoDomiciliado(propietario));
        }else{
            propietarioDB.confirmarRegistrosModificados(
                    propietarioDB.modificarPropietarioDomiciliadoANoDomiciliado(propietario));
        } 
    }
    
    public void verficarAntiguaCuentaBancaria(CuentaBancaria cuentaBancaria, Propietario propietario){
        if(frmEditarPropietario.getAntiguaCtaBancaria().isEmpty()){
            propietarioDB.confirmarRegistrosModificados(
                    propietarioDB.modificarPropietarioNoDomiciliadoADomiciliado(cuentaBancaria, propietario));
        }else if(frmEditarPropietario.getAntiguaCtaBancaria().equals(cuentaBancaria.getNumeroCuentaBancaria())){
            propietarioDB.confirmarRegistrosModificados(
                    propietarioDB.modificarPropietarioDomiciliado(cuentaBancaria, propietario));
        }else{
            propietarioDB.confirmarRegistrosModificados(
                    propietarioDB.modificarPropietarioDomiciliadoNuevaCtaBancaria(cuentaBancaria, propietario));
        }  
    }

    public void validarSeleccionVehiculo(){
        if(frmEditarPropietario.obtenerPlaca()!=null){
            FrmEditarVehiculo frmEditarVehiculo = new FrmEditarVehiculo(
                    frmEditarPropietario,true,frmEditarPropietario.obtenerPlaca()
                );
            frmEditarVehiculo.setListenerFrmEditarPropietario(this);
            frmEditarVehiculo.setVisible(true);
        }else{
            validarCampos.mensajeError("Seleccione un vehiculo.");
        }
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

    public FrmEditarPropietario getFrmEditarPropietario() {
        return frmEditarPropietario;
    }

    public void setFrmEditarPropietario(FrmEditarPropietario frmEditarPropietario) {
        this.frmEditarPropietario = frmEditarPropietario;
    }
    
}
