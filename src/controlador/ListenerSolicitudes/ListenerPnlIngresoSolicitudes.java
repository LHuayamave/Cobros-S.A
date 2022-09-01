/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.ListenerSolicitudes;

import Vista.PnlIngresoSolicitudes;
import controlador.ValidarCampos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import modelo.Solicitud;
import sun.java2d.pipe.SolidTextRenderer;

/**
 *
 * @author Sellan
 */
public class ListenerPnlIngresoSolicitudes extends KeyAdapter implements ActionListener {
    
    private PnlIngresoSolicitudes panelIngresoSolicitudes;
    private Solicitud solicitud;
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
        addListeners();
        addKeyListeners();
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
        
        String id_Factura = panelIngresoSolicitudes.getTxtIdFactura().getText();
        String descripcion = panelIngresoSolicitudes.getTxtaDescripcion().getText();
        String tipo = (String)panelIngresoSolicitudes.getCmbTipoSolicitud().getSelectedItem();
        
        solicitud = new Solicitud(String.valueOf(generadorIdSolicitud), id_Factura , "Pendiente", descripcion, tipo);
        
        System.out.println(solicitud);
    }
    
    /**
     * Metodo que genera un IdSolicitud
     */
    private String generarIdSolicitud(){
        return String.valueOf(++generadorIdSolicitud);
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
}
