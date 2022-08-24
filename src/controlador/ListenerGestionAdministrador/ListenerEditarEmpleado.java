package controlador.ListenerGestionAdministrador;

import Vista.FrmEditarEmpleado;
import controlador.EmpleadoDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ListenerEditarEmpleado implements ActionListener {
    EmpleadoDB empdb = new EmpleadoDB();
    FrmEditarEmpleado ventanaEditarEmpleado = new FrmEditarEmpleado();  

    public ListenerEditarEmpleado() {}
    
    public ListenerEditarEmpleado(FrmEditarEmpleado ventanaEditarEmpleado) {
        this.ventanaEditarEmpleado = ventanaEditarEmpleado;
        addListeners();
    }
    
    private void addListeners(){
        ventanaEditarEmpleado.getBtnGuardar().addActionListener(this);
        ventanaEditarEmpleado.getBtnCancelar().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ventanaEditarEmpleado.getBtnGuardar()){
            mensajeConfirmacion(empdb.modificarEmpleado(ventanaEditarEmpleado));
        }
        
        if(e.getSource() == ventanaEditarEmpleado.getBtnCancelar()){
        }
    }
    
    public void mensajeConfirmacion(int regAfectados){
        if(regAfectados>0){
            JOptionPane.showMessageDialog(null,"Se actualizo correctamente");
        }else{
            JOptionPane.showMessageDialog(null,"No se actualizo correctamente");
        }
        
    }
    
}
