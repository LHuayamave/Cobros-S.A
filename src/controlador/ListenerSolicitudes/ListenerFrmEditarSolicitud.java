/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.ListenerSolicitudes;

import Vista.FrmEditarSolicitud;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Sellan
 */
public class ListenerFrmEditarSolicitud implements ActionListener {
    
    private FrmEditarSolicitud ventanaEditarSolicitud;
    
    
    /**
     * Constructor vacio para el control de la ventana PnlEditarSolicitud.java
     */
    public ListenerFrmEditarSolicitud(){}
    
    /**
     * Constructor para el control de la ventana PnlEditarSolicitud.java
     *
     * @param ventanaEditarSolicitud El parámetro ventanaEditarSolicitud recibe el panel a
     * controlar
     */
    public ListenerFrmEditarSolicitud(FrmEditarSolicitud ventanaEditarSolicitud){
        this.ventanaEditarSolicitud = ventanaEditarSolicitud;
        addListeners();
    }
    
    /**
     * Metodo que asigna los ActionListeners correspondientes
     */
    private void addListeners(){
        ventanaEditarSolicitud.getCmbEstado().addActionListener(this);
        ventanaEditarSolicitud.getBtnGuardarCambios().addActionListener(this);
        ventanaEditarSolicitud.getBtnCancelar().addActionListener(this);
    }

    /**
     * Metodo que escucha los eventos segun los listeners asignados
     * @param e evento de tipo ActionListener recibido de un componente
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ventanaEditarSolicitud.getBtnGuardarCambios()){
            System.out.println("guardar cambios");
        }
    }
}
