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
import javax.swing.JOptionPane;
import modelo.CuentaBancaria;
import modelo.EvaluarCampo;
import modelo.Propietario;
import modelo.Vehiculo;

/**
 * Esta clase agraga los listener a los botones al panel  registrar
 * propietario haciendo uso de la interfaz ActionListener y extiende de la clase
 * KeyAdapter para agregar listener de tipeo a las cajas de texto.
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
    private  Propietario propietario;
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
//            validarRegistro();
            extraerDatosPropietario();
        } else if (e.getSource() == pnlRegistroPropietario.getBtnCancelar()) {
            pnlRegistroPropietario.limpiarCampos();
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
        if (e.getSource() == pnlRegistroPropietario.getTxtCedula()) {
            validarCampos.validarCampoNumerico(new EvaluarCampo(
                    e,pnlRegistroPropietario.getTxtCedula(),9) ); 
            validarCampos.verificarSiExistePropietario(pnlRegistroPropietario.getTxtCedula());
        } else if (e.getSource() == pnlRegistroPropietario.getTxtNombre()) {
            validarCampos.validaLetras(e);
        } else if (e.getSource() == pnlRegistroPropietario.getTxtTelefono()) {
            validarCampos.validarCampoNumerico(new EvaluarCampo(
                    e,pnlRegistroPropietario.getTxtTelefono(),9) ); 
        } else if (e.getSource() == pnlRegistroPropietario.getTxtPlaca()) {
            validarCampos.verificarSiExistePlaca(pnlRegistroPropietario.getTxtPlaca());
        } else if (e.getSource() == pnlRegistroPropietario.getTxtAnioVehiculo()) {
            validarCampos.validarCampoNumerico(new EvaluarCampo(
                    e,pnlRegistroPropietario.getTxtAnioVehiculo(),3) ); 
        }else if(e.getSource() == pnlRegistroPropietario.getTxtCVV()){
            validarCampos.validarCampoNumerico(new EvaluarCampo(
                    e,pnlRegistroPropietario.getTxtCVV(),2) ); 
        }
    }
//       public void registrarPropietarioNoDomiciliado() {
//        if (ingresarDatosPropietario() > 0) {
//            JOptionPane.showMessageDialog(null, "Datos Guardados Correctamente");
//            pnlRegistroPropietario.limpiarCampos();
//        } else {
//            JOptionPane.showMessageDialog(null, "No se han podido guardar los datos");
//        }
//    }
        public void extraerDatosPropietario() {
        cuentaBancaria = new CuentaBancaria();
        propietario = new Propietario();
        vehiculo = new Vehiculo();
        cuentaBancaria.setNumeroCuentaBancaria(pnlRegistroPropietario.getTxtCtaBancaria().getText());
        cuentaBancaria.setCvv(pnlRegistroPropietario.getTxtCVV().getText());
        cuentaBancaria.setMes(pnlRegistroPropietario.getCmbMes().getSelectedIndex()+1);
        cuentaBancaria.setAnio(pnlRegistroPropietario.getCmbAnio().getSelectedIndex()+1);
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
        
        validarCampos(cuentaBancaria,propietario,vehiculo);
        //return propietarioDB.registrarPropietarioNoDomiciliado( propietario,  vehiculo);
    }
    private void validarCampos(CuentaBancaria cuentaBancaria, Propietario propietario, Vehiculo vehiculo) {
        if(propietario.getDomiciliado()){
            validarRegistroDomiciliado(cuentaBancaria,propietario,vehiculo);
        }else{
            validarRegistroNoDomiciliado(propietario,vehiculo);
        }

    }
    public void validarRegistroDomiciliado(CuentaBancaria cuentaBancaria, Propietario propietario, Vehiculo vehiculo) {
        if (validarCampos.validarCampoVacioDomiciliado(this.pnlRegistroPropietario) && validarEdadIngreso()) {
            propietarioDB.registrarPropietarioDomiciliado(cuentaBancaria, propietario, vehiculo);
        }
    }
    public void validarRegistroNoDomiciliado(Propietario propietario, Vehiculo vehiculo) {
        if (validarCampos.validarCampoVacioNoDomiciliado(this.pnlRegistroPropietario) && validarEdadIngreso()) {
            propietarioDB.registrarPropietarioNoDomiciliado(propietario, vehiculo);
        }
    }
    /*Este metodo verifica que la informacion ingreso corrcatmente*/
//    public void registrarPropietario() {
//        if (ingresarVehiculo() > 0 && ingresarPropietario() > 0) {
//            JOptionPane.showMessageDialog(null, "Datos Guardados Correctamente");
//            pnlRegistroPropietario.limpiarCampos();
//        } else {
//            JOptionPane.showMessageDialog(null, "No se han podido guardar los datos");
//        }
//    }
    

//    public int ingresarVehiculo() {
//        return vehiculoDB.registrarVehiculo(
//                pnlRegistroPropietario.getTxtPlaca().getText(),
//                pnlRegistroPropietario.getTxtMarca().getText(),
//                pnlRegistroPropietario.getTxtModelo().getText(),
//                pnlRegistroPropietario.getTxtAnioVehiculo().getText(),
//                pnlRegistroPropietario.getCmbTipoImpuesto().getSelectedIndex() + "");
//    }
     /*este metodo registra la informacion del propietario en la BD haciendo uso 
    del controlador PropietarioDB*/
//    public int ingresarPropietario() {
//        propietario = new Propietario();
//        propietario.setCedula(pnlRegistroPropietario.getTxtCedula().getText());
//        propietario.setNombre(pnlRegistroPropietario.getTxtNombre().getText());
//        propietario.setCorreo(pnlRegistroPropietario.getTxtCorreo().getText());
//        propietario.setTelefono(pnlRegistroPropietario.getTxtTelefono().getText());
//        propietario.setDireccion(pnlRegistroPropietario.getTxtDireccion().getText());
//        propietario.setFechaNacimiento(
//                new java.sql.Date(((Date) pnlRegistroPropietario.getDtcFechaNacimiento().getDate()).getTime()));
//        propietario.setPlaca(pnlRegistroPropietario.getTxtPlaca().getText());
//        propietario.setId_estado_propietario(pnlRegistroPropietario.getCmbEstadoPropietario().getSelectedIndex());
//        
//        return propietarioDB.registrarPropietario(propietario);
//    }

    /*Valida que las cajas de texto no esten vacio y valida la edad*/
    
    /*Metodo que validad la edad haciendo uso del metodo validarEdad */
    public boolean validarEdadIngreso() {
        return validarCampos.validarEdad(pnlRegistroPropietario.getDtcFechaNacimiento().getDate());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()== pnlRegistroPropietario.getrBtnDomiciliado()){
            if(pnlRegistroPropietario.getrBtnDomiciliado().isSelected()){
                pnlRegistroPropietario.getTxtCtaBancaria().setEditable(true);
                pnlRegistroPropietario.getTxtCVV().setEditable(true);
                pnlRegistroPropietario.getCmbMes().setEnabled(true);
                pnlRegistroPropietario.getCmbAnio().setEnabled(true);
                pnlRegistroPropietario.getTxtSaldo().setEditable(true);
                pnlRegistroPropietario.getCmbTipoCtaBancaria().setEnabled(true);
                pnlRegistroPropietario.getCmbBanco().setEnabled(true); 
            }else{
                pnlRegistroPropietario.getTxtCtaBancaria().setEditable(false);
                pnlRegistroPropietario.getTxtCVV().setEditable(false);
                pnlRegistroPropietario.getCmbMes().setEnabled(false);
                pnlRegistroPropietario.getCmbAnio().setEnabled(false);
                pnlRegistroPropietario.getTxtSaldo().setEditable(false);
                pnlRegistroPropietario.getCmbTipoCtaBancaria().setEnabled(false);
                pnlRegistroPropietario.getCmbBanco().setEnabled(false);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}


}
