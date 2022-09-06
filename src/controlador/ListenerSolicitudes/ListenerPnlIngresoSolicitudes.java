/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.ListenerSolicitudes;

import Vista.PnlIngresoSolicitudes;
import controlador.SolicitudDB;
import controlador.ValidarCampos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import modelo.Empleado;
import modelo.Solicitud;
import sun.java2d.pipe.SolidTextRenderer;

/**
 *
 * @author Sellan
 */
public class ListenerPnlIngresoSolicitudes extends KeyAdapter implements ActionListener {
    
    private PnlIngresoSolicitudes panelIngresoSolicitudes;
    private Solicitud solicitud;
    private SolicitudDB solicitudDB;
    private ValidarCampos validarCampo;
    static int generadorIdSolicitud = 1;
    
    
    /**
     * Constructor vacio para el control de la ventana PnlIngresoSolicitudes.java
     */
    public ListenerPnlIngresoSolicitudes(){}
    
    /**
     * Constructor para el control de la ventana PnlIngresoSolicitudes.java
     *
     * @param panelIngresoSolicitudes El parámetro panelIngresoSolicitudes recibe el panel a
     * controlar
     */
    public ListenerPnlIngresoSolicitudes(PnlIngresoSolicitudes panelIngresoSolicitudes){
        this.panelIngresoSolicitudes = panelIngresoSolicitudes;
        validarCampo = new ValidarCampos();
        solicitudDB = new SolicitudDB(); 
        addListeners();
        addKeyListeners();
        incrementarIdSolicitud();
    }

    /**
     * Metodo que asigna los ActionListeners correspondientes
     */
    private void addListeners(){
        panelIngresoSolicitudes.getBtnCancelar().addActionListener(this);
        panelIngresoSolicitudes.getBtnGuardarSolicitud().addActionListener(this);
    
    }
    
    /**
     * Metodo que asigna los KeyListeners correspondientes
     */
    private void addKeyListeners(){
        panelIngresoSolicitudes.getTxtIdFactura().addKeyListener(this);
    }
    
    /**
     * Metodo que valida el registro de la solicitud antes de que se cree un objeto Solicitud
     */
     public void validarRegistroSolicitud() {
         if(validarCampo.validarCamposVaciosSolicitud(panelIngresoSolicitudes)){
             crearSolicitud();
             incrementarIdSolicitud();
         }
     }
    
    /**
     * Metodo que crea un objeto de tipo Solicitud obteniendo los datos de los campos 
     * textfield de la clase panelIngresoSolicitudes.java 
     */
    private void crearSolicitud(){
        solicitud = new Solicitud();
        solicitud.setId(generarIdSolicitud());
        solicitud.setTipo((String)panelIngresoSolicitudes.getCmbTipoSolicitud().getSelectedItem());
        solicitud.setDescripcion(panelIngresoSolicitudes.getTxtaDescripcion().getText());
        solicitud.setEstado("Pendiente");
        solicitud.setId_Factura(panelIngresoSolicitudes.getTxtIdFactura().getText());
        mensajeConfirmacion(solicitudDB.agregarSolicitud(solicitud));
        
    }
   
    /**
     * Metodo que genera un IdSolicitud
     */
    private String generarIdSolicitud(){
        //return String.valueOf(++generadorIdSolicitud);
        return String.valueOf(solicitudDB.obternerIdMaximoSoli()+1);
    }
    
     /**
     * Metodo que limpia los campos jTextField
     */
    private void limpiarCampos(){
        panelIngresoSolicitudes.getTxtIdFactura().setText("");
        panelIngresoSolicitudes.getTxtaDescripcion().setText("");        
    }
    
     /**
     * Metodo que incrementa el numero de solicitud en el JLabel lblNumeroSolicitud
     */
    private void incrementarIdSolicitud(){
        panelIngresoSolicitudes.getLblNumeroSolicitud().setText(generarIdSolicitud());
    }
    
    /**
     * Metodo que escucha los eventos segun los listeners asignados
     * @param e evento de tipo ActionListener recibido de un componente
     */
    @Override
    public void actionPerformed(ActionEvent e) {
            
        if(e.getSource() == panelIngresoSolicitudes.getBtnGuardarSolicitud()){
            System.out.println("boton guardar");
            crearSolicitud();
            limpiarCampos();      
        }
        
        else if(e.getSource() == panelIngresoSolicitudes.getBtnCancelar()){
            System.out.println("boton cancelar papa");
            
        }

    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == panelIngresoSolicitudes.getTxtIdFactura()){
            validarCampo.validarNumeros(e, panelIngresoSolicitudes.getTxtIdFactura());
        }
    }
     public void mensajeConfirmacion(int regAfectados){
        if(regAfectados>0){
            JOptionPane.showMessageDialog(null,"Se han guardado los datos correctamente");
        }else{
            JOptionPane.showMessageDialog(null,"No han guardado los datos.");
        }
    }
}