/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.ListenerSolicitudes;

import Vista.PnlListarSolicitud;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Sellan
 */
public class ListenerPnlListarSolicitud implements ActionListener{
    
    private PnlListarSolicitud ventanaListarSolicitud;
    
    /**
     * Constructor vacio para el control de la ventana PnlListarSolicitud.java
     */
    public ListenerPnlListarSolicitud(){}
    
    /**
     * Constructor para el control de la ventana PnlListarSolicitud.java
     *
     * @param ventanaListarSolicitud El parámetro ventanaListarSolicitud recibe el panel a
     * controlar
     */
    public ListenerPnlListarSolicitud(PnlListarSolicitud ventanaListarSolicitud){
        this.ventanaListarSolicitud = ventanaListarSolicitud;
        addListeners();
    }
    
    private void addListeners(){
        ventanaListarSolicitud.getBtnConsultar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ventanaListarSolicitud.getBtnConsultar()){
            System.out.println("boton consultar");
        }
    }
    
}
