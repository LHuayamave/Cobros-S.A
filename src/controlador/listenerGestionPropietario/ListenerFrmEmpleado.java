package controlador.listenerGestionPropietario;


import Vista.FrmEmpleado;
import Vista.FrmListarPropietario;
import Vista.FrmLogin;
import Vista.PnlIngresoSolicitudes;
import Vista.PnlListarPropietariosDomiciliados;
import Vista.PnlRegistroPropietario;
import Vista.PnlListarPropietariosNoDomiciliados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.PnlControlPagos;

/**
 * Esta clase agraga los listener a los botones del formulario empleado haciendo
 * uso de la interfaz ActionListener .
 *
 * @param cs {@link ActionListener } clase que permite agregar a los
 * escuchadores a los botones.
 * @author Grupo E
 */
public class ListenerFrmEmpleado implements ActionListener {
    
    FrmEmpleado frmEmpleado;

    public ListenerFrmEmpleado(FrmEmpleado frmEmpleado) {
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
        if (e.getSource() == frmEmpleado.getMniRegistrarPropietario()) {
            PnlRegistroPropietario pnlRegistroPropietario = new PnlRegistroPropietario(frmEmpleado);
            frmEmpleado.ShowPanel(pnlRegistroPropietario);
        } else if (e.getSource() == frmEmpleado.getMniListarPropietario()) {
            FrmListarPropietario frmListaPropietario = new FrmListarPropietario();
            frmEmpleado.ShowPanel(frmListaPropietario);
        } else if (e.getSource() == frmEmpleado.getMniListarPropietariosDomiciliados()) {
            PnlListarPropietariosDomiciliados frmListaPropietarioDom = new PnlListarPropietariosDomiciliados();
            frmEmpleado.ShowPanel(frmListaPropietarioDom);
        }else if (e.getSource() == frmEmpleado.getMniListarPropietariosNoDomiciliados()) {
            PnlListarPropietariosNoDomiciliados frmListaPropietarioNoDom = new PnlListarPropietariosNoDomiciliados();
            frmEmpleado.ShowPanel(frmListaPropietarioNoDom);
        }  else if (e.getSource() == frmEmpleado.getBtnCerrarSesion()) {
            frmEmpleado.dispose();
        } else if (e.getSource() == frmEmpleado.getMniControlPagos()) {
            PnlControlPagos frmControlPagos = new PnlControlPagos();
            ListenerControlPagos controlPago = new ListenerControlPagos(frmControlPagos);
            controlPago.llenarTablaControlTodos();
            frmEmpleado.ShowPanel(frmControlPagos);
        }
    }

}