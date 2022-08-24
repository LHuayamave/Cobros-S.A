package controlador.listenerGestionPropietario;

import Vista.FrmConsultarPropietario;
import controlador.PropietarioDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private Vehiculo vehiculo ;
    private CuentaBancaria cuentaBancaria;

    public ListenerFrmConsultarPropietario(FrmConsultarPropietario frmConsultarPropietario) {
        this.frmConsultarPropietario = frmConsultarPropietario;
    }

    /**
     * Este metodo recibe el evento del boton presionado y lo compara para 
     * tomar un desicion.
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
    void llenarFrmConsultarPropietario(String obtenerCedula, String Domiciliado) {
//        
//        
//        propietario = propietarioDB.obtenerPropietario(obtenerCedula);
//        vehiculo = vehiculoDB.obtenerVehiculo(propietario.getPlaca());
//        llenarPartePropietario(propietario);
//        llenarParteVehiculo(vehiculo);
    }

    public void llenarCamposConsultarPropietario() {
        propietario = new Propietario();
        cuentaBancaria = new CuentaBancaria();
        if(frmConsultarPropietario.getDomiciliado().equals("true")){ 
            propietarioDB.consultarPropietarioDomiciliado(
                    cuentaBancaria,propietario,frmConsultarPropietario.getCedula());
            llenarCampoPropietarioDomiciliado(propietario,cuentaBancaria);
        }else{
            propietarioDB.consultarPropietarioNoDomiciliado(
                    propietario,frmConsultarPropietario.getCedula());
            llenarCampoPropietarioNoDomiciliado(propietario);
        }

    }

    private void llenarCampoPropietarioDomiciliado(Propietario propietario, CuentaBancaria cuentaBancaria) {
//        frmConsultarPropietario.getTxtCedula().setText(propietario.getCedula()); 
//        frmConsultarPropietario.getTxtNombre().setText(propietario.getNombre());
//        frmConsultarPropietario.getTxtCorreo().setText(propietario.getCorreo());
//        frmConsultarPropietario.getTxtTelefono().setText(propietario.getTelefono());
//        frmConsultarPropietario.getTxtDireccion().setText(propietario.getDireccion());
//        //frmConsultarPropietario.getTxtFechaNacimiento().setText(propietario.getFechaNacimiento());
//        frmConsultarPropietario.getCmbEstadoPropietario();
//        frmConsultarPropietario.getrBtnDomiciliado();
//        
//        frmConsultarPropietario.getTxtCtaBancaria();
//        frmConsultarPropietario.getTxtCVV();
//        frmConsultarPropietario.getCmbMes();
//        frmConsultarPropietario.getCmbAnio();
//        frmConsultarPropietario.getCmbTipoCtaBancaria();
//        frmConsultarPropietario.getTxtSaldo();
//        frmConsultarPropietario.getCmbBanco();

    }

    private void llenarCampoPropietarioNoDomiciliado(Propietario propietario) {

    }
    

}
