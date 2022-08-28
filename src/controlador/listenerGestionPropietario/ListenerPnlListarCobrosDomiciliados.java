/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.listenerGestionPropietario;

import Vista.PnlListarPropietariosDomiciliados;
import controlador.PropietarioDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import modelo.CobroDomiciliados;
import modelo.ImgTabla;

/**
 *
 * @author NIEVES
 */
public class ListenerPnlListarCobrosDomiciliados implements ActionListener{
    
    private final PnlListarPropietariosDomiciliados pnlListarPropietariosDomiciliados;
    private ArrayList<CobroDomiciliados> listaCobrosDomiciliados;
    private ArrayList<CobroDomiciliados> listaCobrosDomiciliadosMes;
    private TableRowSorter<DefaultTableModel> sorter;
    private PropietarioDB propietarioDB = new PropietarioDB();
    private CobroDomiciliados cobrosDom;

    public ListenerPnlListarCobrosDomiciliados(PnlListarPropietariosDomiciliados pnlListarPropietariosDomiciliados) {
        this.pnlListarPropietariosDomiciliados = pnlListarPropietariosDomiciliados;
    }
    
    public void llenarTablaTodosCobrosDomiciliados() {
        int mesActual;
        String recurso="";
        mesActual = LocalDate.now().getMonth().getValue();
        pnlListarPropietariosDomiciliados.getTblTodos().setDefaultRenderer(Object.class, new ImgTabla());
        listaCobrosDomiciliados = propietarioDB.listarCobrosDomiciliados();
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) pnlListarPropietariosDomiciliados.getTblTodos().getModel();
        for (CobroDomiciliados cobros : listaCobrosDomiciliados) {
            if(cobros.getSaldo()>cobros.getValorImpuesto() && cobros.getMesPago()<= mesActual){
                recurso = "/img/hecho.png";
             }
            else if(cobros.getMesPago()> mesActual){
                recurso="/img/pendiente.png";
            }
            else{
                 recurso="/img/nohecho.png";
            }
            tb.addRow(new Object[]{cobros.getCedulaPropietario(), cobros.getNombrePropietario(), cobros.getTipoImpuesto(),
            cobros.getValorImpuesto(),Month.of(cobros.getMesPago()),new JLabel(new ImageIcon(getClass().getResource(recurso)))});
            addCheckBox(6,pnlListarPropietariosDomiciliados.getTblTodos());
            pnlListarPropietariosDomiciliados.getTblTodos().setAutoCreateRowSorter(true);
            sorter = new TableRowSorter<>(tb);
            pnlListarPropietariosDomiciliados.getTblTodos().setRowSorter(sorter);
            i++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
   public void addCheckBox(int column, JTable tabla)
    {
        TableColumn tc = tabla.getColumnModel().getColumn(column);
        tc.setCellEditor(tabla.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(tabla.getDefaultRenderer(Boolean.class));
    }
   
   public void llenarTablaMesCobrosDomiciliados(Integer mes,JTable tabla) {
        int mesActual;
        mesActual = LocalDate.now().getMonth().getValue();
        String recurso="";
        tabla.setDefaultRenderer(Object.class, new ImgTabla());
        listaCobrosDomiciliadosMes = propietarioDB.listarCobrosDomiciliadosMes(mes);
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        for (CobroDomiciliados cobros : listaCobrosDomiciliadosMes) {
            if(cobros.getSaldo()>cobros.getValorImpuesto() && cobros.getMesPago()<= mesActual){
                recurso = "/img/hecho.png";
             }
             else if(cobros.getMesPago()> mesActual){
                recurso="/img/pendiente.png";
            }
            else{
                 recurso="/img/nohecho.png";
            }
            tb.addRow(new Object[]{cobros.getCedulaPropietario(), cobros.getNombrePropietario(), cobros.getTipoImpuesto(),
            cobros.getValorImpuesto(),new JLabel(new ImageIcon(getClass().getResource(recurso)))});
            addCheckBox(5,tabla);
            tabla.setAutoCreateRowSorter(true);
            sorter = new TableRowSorter<>(tb);
            tabla.setRowSorter(sorter);
            i++;
        }
    }
   
   public void ActualizarSaldo(Integer mes,JTable tabla){
        float saldoActual;
        int mesActual;
        int fila;
        mesActual = LocalDate.now().getMonth().getValue();
        listaCobrosDomiciliadosMes = propietarioDB.listarCobrosDomiciliadosMes(mes);
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        for (CobroDomiciliados cobros : listaCobrosDomiciliadosMes) {
            if(cobros.getSaldo()>cobros.getValorImpuesto() && cobros.getMesPago()<= mesActual){
                saldoActual = cobros.getSaldo() - cobros.getValorImpuesto();
             }
             else{
                 saldoActual = cobros.getSaldo();
             }
            fila = tabla.getSelectedRow();
            if (fila != -1) {
                propietarioDB.ActualizarSaldoDomiciliados(cobros.getCtaBancaria(), saldoActual);
            } 
            tabla.setAutoCreateRowSorter(true);
            sorter = new TableRowSorter<>(tb);
            tabla.setRowSorter(sorter);
            i++;
        }    
   }

}
