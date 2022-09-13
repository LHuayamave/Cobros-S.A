package controlador.listenerGestionPropietario;

import Vista.FrmEmitirRecibo;
import Vista.PnlListarPropietariosDomiciliados;
import controlador.PropietarioDB;
import controlador.VehiculoDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.CuentaBancaria;
import modelo.Propietario;

/**
 *
 * @author Soldado
 */
public class ListenerFrmEmitirRecibo implements ActionListener{
    private FrmEmitirRecibo frmEmitirRecibo;
    private PropietarioDB propietarioDB;
    private VehiculoDB vehiculoDB;
    private Propietario propietario;
    private CuentaBancaria  cuentaBancaria;

    public ListenerFrmEmitirRecibo(FrmEmitirRecibo frmEmitirRecibo) {
        this.frmEmitirRecibo = frmEmitirRecibo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==frmEmitirRecibo.getBtnEnviar()){
//            llenarCampos();
        }
    }

    public int llenarCampos(Boolean esDomiciliado) {
        propietarioDB = new PropietarioDB();
        vehiculoDB = new VehiculoDB();
        propietario = new Propietario();
        cuentaBancaria = new  CuentaBancaria();

        System.out.print(propietario.getCedula()); 
        if(esDomiciliado){
            frmEmitirRecibo.getTxtCorreo().setEditable(true);
        }else{
            frmEmitirRecibo.getTxtCorreo().setEditable(false);
        }
        return 0;
    }
    

}
