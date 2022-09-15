/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.ListenerSolicitudes;

import Vista.FrmEditarSolicitud;
import Vista.PnlEstadoSolicitud;
import controlador.SolicitudDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.Solicitud;

/**
 *
 * @author Sellan
 */
public class ListenerPnlEstadoSolicitud implements ActionListener{
    SolicitudDB solidb = new SolicitudDB();
    private PnlEstadoSolicitud ventanaEstadoSolicitud;
    private ArrayList<Solicitud> arraySolicitudes;
    private TableRowSorter<DefaultTableModel> sorter;
    private FrmEditarSolicitud ventanaEditarSolicitud;
    private ListenerFrmEditarSolicitud olistenerFrmEditarSolicitud;
    /**
     * Constructor vacio para el control de la ventana PnlListarSolicitud.java
     */
    public ListenerPnlEstadoSolicitud(){}
    
    /**
     * Constructor para el control de la ventana PnlListarSolicitud.java
     *
     * @param ventanaEstadoSolicitud El parámetro ventanaListarSolicitud recibe el panel a
     * controlar
     */
    public ListenerPnlEstadoSolicitud(PnlEstadoSolicitud ventanaEstadoSolicitud){
        this.ventanaEstadoSolicitud = ventanaEstadoSolicitud;
        ventanaEditarSolicitud = new FrmEditarSolicitud();
        olistenerFrmEditarSolicitud = new ListenerFrmEditarSolicitud(ventanaEditarSolicitud);
        addListeners();
    }
    
    private void addListeners(){
        ventanaEstadoSolicitud.getBtnEditar().addActionListener(this);
        
    }
     public void obtenerSolicitud(ArrayList<Solicitud> arraySolicitud){
        this.arraySolicitudes = arraySolicitud;
        for(int i = 0; i < arraySolicitud.size(); i++) {
            System.out.println(arraySolicitud.get(i));
        }
    }
     public void mostrarDatosTabla(){

        arraySolicitudes = solidb.ListSolicitud();
        for(int i = 0; i < arraySolicitudes.size(); i++) {
            Object[] fila = {
                arraySolicitudes.get(i).getId(),
                arraySolicitudes.get(i).getTipo(),
                arraySolicitudes.get(i).getDescripcion(),
                arraySolicitudes.get(i).getEstado()
            };
        ventanaEstadoSolicitud.getModeloTabla().addRow(fila);
        }
    }
      public void ListarDatos() {

        arraySolicitudes = solidb.ListSolicitud();
        
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) ventanaEstadoSolicitud.getTablaEstadoSolicitudes().getModel();
        for (Solicitud solicitud : arraySolicitudes) {
        
            tb.addRow(new Object[]{solicitud.getId(), solicitud.getTipo(), solicitud.getDescripcion(), solicitud.getEstado()});
            ventanaEstadoSolicitud.getTablaEstadoSolicitudes().setAutoCreateRowSorter(true);
            sorter = new TableRowSorter<>(tb);
            ventanaEstadoSolicitud.getTablaEstadoSolicitudes().setRowSorter(sorter);
            i++;
        }
    }
      

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ventanaEstadoSolicitud.getBtnEditar()){
            System.out.println("boton Editar");
            ventanaEditarSolicitud.setVisible(true);
        }
    }
    
}
