
package controlador.ListenerGestionAdministrador;


import Vista.PnlRegistroEmpleado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import modelo.Empleado;
import controlador.EmpleadoDB;
import controlador.ValidarCampos;
import javax.swing.JOptionPane;


public class ListenerRegistroEmpleado extends KeyAdapter implements ActionListener{
    
    private PnlRegistroEmpleado ventanaRegistroEmpleado;
    private Empleado empleado;
    private ArrayList<Empleado> arrayEmpleado;
    private EmpleadoDB empleadoDB;
    static int generadorEmpleado = 1;
    static int generadorAdministrador = 1;
 
    public ListenerRegistroEmpleado(){}

    public ListenerRegistroEmpleado(PnlRegistroEmpleado ventanaRegistroEmpleado) {
        this.ventanaRegistroEmpleado = ventanaRegistroEmpleado;
        arrayEmpleado = new ArrayList();
        empleadoDB = new EmpleadoDB();
        addActionListeners();
        addKeyListeners();
    }


    private void addActionListeners(){ 
        ventanaRegistroEmpleado.getBtnRegistrar().addActionListener(this);
        ventanaRegistroEmpleado.getBtnLimpiarRegistro().addActionListener(this);
        ventanaRegistroEmpleado.getBtnCancelar().addActionListener(this);
    }
    
    private void addKeyListeners(){
        ventanaRegistroEmpleado.getTxtCedula().addKeyListener(this);
        ventanaRegistroEmpleado.getTxtTelefono().addKeyListener(this);        
    }
    
    private void crearEmpleado(){
        empleado = new Empleado();
        empleado.setIdEmpleado(generarIdEmpleado());
        empleado.setNombre(ventanaRegistroEmpleado.getTxtNombre().getText());
        empleado.setCedula(ventanaRegistroEmpleado.getTxtCedula().getText());
        empleado.setDireccion(ventanaRegistroEmpleado.getTxtDireccion().getText());
        empleado.setCorreo(ventanaRegistroEmpleado.getTxtCorreo().getText());
        empleado.setTelefono(ventanaRegistroEmpleado.getTxtTelefono().getText());
        empleado.setFechaNacimiento(new java.sql.Date(
                ((Date) ventanaRegistroEmpleado.getDtcFechaNacimiento().getDate()).getTime()));
        empleado.setContrasenia(new String(ventanaRegistroEmpleado.getjPasswordField().getPassword()));
        empleado.setIdTrabajo(ventanaRegistroEmpleado.getCmbRolEmpleado().getSelectedIndex()+1+""); 
        mensajeConfirmacion(empleadoDB.agregarEmpleado(empleado));
    }
    
    private void addArrayListEmpleado(Empleado empleado){       
        arrayEmpleado.add(empleado);
         limpiarCampos();
    }
    
    private String generarIdEmpleado(){
        //System.out.println("IdEmpleado" +empleadoDB.obternerIdMaximo());
        return String.valueOf(empleadoDB.obternerIdMaximo()+1);  
    }

    private void limpiarCampos(){
        ventanaRegistroEmpleado.getTxtNombre().setText("");
        ventanaRegistroEmpleado.getTxtCedula().setText("");
        ventanaRegistroEmpleado.getTxtDireccion().setText("");
        ventanaRegistroEmpleado.getTxtTelefono().setText("");
        ventanaRegistroEmpleado.getTxtCorreo().setText("");
        ventanaRegistroEmpleado.getjPasswordField().setText("");
        ventanaRegistroEmpleado.getDtcFechaNacimiento().setDate(Date.from(Instant.now()));
    }
    
    private void ingresoSoloNumeros(char validar, KeyEvent e){
        if(validar >= 32 && validar <= 45 || validar == 47 || validar >= 58 && validar <= 8482) {
                e.consume();
            }
    }

    public ArrayList<Empleado> getArrayEmpleado() {
        return arrayEmpleado;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == ventanaRegistroEmpleado.getBtnRegistrar()) {  
            crearEmpleado();
        }
        
        else if (e.getSource() == ventanaRegistroEmpleado.getBtnLimpiarRegistro()) {
            
            limpiarCampos();
        }
        
        else if (e.getSource() == ventanaRegistroEmpleado.getBtnCancelar()) {
            System.out.println("Volviendo ventana atras");
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e){
        if(e.getSource() == ventanaRegistroEmpleado.getTxtCedula()) {
            char validar = e.getKeyChar();	
            ingresoSoloNumeros(validar, e);
        }
        
        if(e.getSource() == ventanaRegistroEmpleado.getTxtTelefono()){
            char validad = e.getKeyChar();
            ingresoSoloNumeros(validad, e);
        }
    }

    @Override
    public String toString() {
        return "CtrRegistroEmpleado{" + "ventanaRegistroEmpleado=" + ventanaRegistroEmpleado + ", empleado=" + empleado + ", arrayEmpleado=" + arrayEmpleado + '}';
    }
    
    public void mensajeConfirmacion(int regAfectados){
        if(regAfectados>0){
            JOptionPane.showMessageDialog(null,"Se han guardado los datos correctamente");
        }else{
            JOptionPane.showMessageDialog(null,"No han guardado los datos.");
        }
    }
}