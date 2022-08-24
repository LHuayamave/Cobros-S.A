
package controlador.listenerGestionPropietario;

import Vista.FrmEmpleado;
import Vista.FrmListarPropietario;
import Vista.FrmLogin;
import Vista.PnlRegistroPropietario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase agraga los listener a los botones del formulario empleado
 *  haciendo uso de la interfaz ActionListener .
 *
 * @param cs {@link ActionListener } clase que permite agregar a los
 * escuchadores a los botones.
 * @author Grupo E
 */
public class ListenerFrmEmpleado implements ActionListener  {
    FrmEmpleado frmEmpleado;
    public ListenerFrmEmpleado(FrmEmpleado frmEmpleado){
        this.frmEmpleado = frmEmpleado;
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
        if(e.getSource() == frmEmpleado.getMniRegistrarPropietario()){
            PnlRegistroPropietario pnlRegistroPropietario = new PnlRegistroPropietario(frmEmpleado);
            frmEmpleado.ShowPanel(pnlRegistroPropietario);
        }else if(e.getSource() == frmEmpleado.getMniListarPropietario()){
            FrmListarPropietario frmListaPropietario = new FrmListarPropietario();
            frmEmpleado.ShowPanel(frmListaPropietario);
        }else if(e.getSource() == frmEmpleado.getBtnCerrarSesion()){
            frmEmpleado.dispose();
        }
    }
    
}
