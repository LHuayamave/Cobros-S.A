
package controlador.ListenerGestionAdministrador;

import Vista.FrmConsultarEmpleado;
import controlador.EmpleadoDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ListenerConsultarEmpleado implements ActionListener{
    EmpleadoDB empdb = new EmpleadoDB();
    FrmConsultarEmpleado ventanaConsultarEmpleado = new FrmConsultarEmpleado();
    
    public ListenerConsultarEmpleado(){}
    
    public ListenerConsultarEmpleado(FrmConsultarEmpleado ventanaConsultarEmpleado){
        this.ventanaConsultarEmpleado = ventanaConsultarEmpleado;
        addListeners();
    }
    
    public void addListeners(){
        ventanaConsultarEmpleado.getBtnSalir();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ventanaConsultarEmpleado.getBtnSalir()){
        }
    }
}
