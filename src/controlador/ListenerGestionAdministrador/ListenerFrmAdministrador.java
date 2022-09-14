
package controlador.ListenerGestionAdministrador;


import Vista.FrmAdministrador;
import Vista.FrmListarEmpleado;
import Vista.PnlEstadoSolicitud;
import Vista.PnlIngresoSolicitudes;
import Vista.PnlRegistroEmpleado;
import controlador.EmpleadoDB;
import controlador.ListenerSolicitudes.ListenerPnlEstadoSolicitud;
import controlador.ListenerSolicitudes.ListenerPnlIngresoSolicitudes;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;


public class ListenerFrmAdministrador implements ActionListener {
    
    private FrmAdministrador frmAdministrador;
    private PnlRegistroEmpleado pnlRegistroEmpleado;
    private ListenerRegistroEmpleado listenerRegistroEmpleado;
    private FrmListarEmpleado frmListarEmpleado;
    private ListenerListarEmpleado controlListarEmpleado;
    EmpleadoDB empdb = new EmpleadoDB();
    private ListenerPnlEstadoSolicitud oListenerPnlEstadoSolicitud;
    private PnlEstadoSolicitud oestadoSolicitud;

    
    public ListenerFrmAdministrador(){}

    public ListenerFrmAdministrador(FrmAdministrador oFrmAdministrador) {
        this.frmAdministrador = oFrmAdministrador;
        pnlRegistroEmpleado= new PnlRegistroEmpleado();
        listenerRegistroEmpleado = new ListenerRegistroEmpleado(pnlRegistroEmpleado);
        frmListarEmpleado = new FrmListarEmpleado();
        controlListarEmpleado = new ListenerListarEmpleado(frmListarEmpleado);
        oestadoSolicitud = new PnlEstadoSolicitud();
        oListenerPnlEstadoSolicitud = new ListenerPnlEstadoSolicitud(oestadoSolicitud);
        addListeners();
        
    }   
    
    private void addListeners(){
        frmAdministrador.getMniRegistrarEmpleado().addActionListener(this);
        frmAdministrador.getMniListarEmpleado().addActionListener(this);
        frmAdministrador.getMniEditarSolicitudes().addActionListener(this);

    }
    
    private void mostrarPanel(JPanel panel){
        panel.setSize(853,363);
        panel.setLocation(0,0);
        frmAdministrador.getPanelAdmin().removeAll();
        frmAdministrador.getPanelAdmin().add(panel, BorderLayout.CENTER);
        frmAdministrador.getPanelAdmin().revalidate();
        frmAdministrador.getPanelAdmin().repaint();
     }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == frmAdministrador.getMniRegistrarEmpleado()){
            mostrarPanel(pnlRegistroEmpleado);
        }
        
        else if(e.getSource() == frmAdministrador.getMniListarEmpleado()){ 
            empdb.LimpiarFormulario(frmListarEmpleado.getTablaEmpleados());
            controlListarEmpleado.mostrarDatosTabla();
            mostrarPanel(frmListarEmpleado); 
            
        }else if (e.getSource() == frmAdministrador.getMniEditarSolicitudes()) {
           mostrarPanel(oestadoSolicitud);
            System.out.println("kk");
       }    
    }
}

