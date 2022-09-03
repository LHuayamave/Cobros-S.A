
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
import javax.swing.table.TableRowSorter;
import modelo.CobroDomiciliados;
import modelo.ImgTabla;


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
            pnlListarPropietariosDomiciliados.getTblTodos().setAutoCreateRowSorter(true);
            sorter = new TableRowSorter<>(tb);
            pnlListarPropietariosDomiciliados.getTblTodos().setRowSorter(sorter);
            i++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pnlListarPropietariosDomiciliados.getBtnEmitirAvisoCobro()) {
            ActualizarSaldo(1, pnlListarPropietariosDomiciliados.getTblEnero());
            ActualizarSaldo(2,pnlListarPropietariosDomiciliados.getTblFebrero());
            ActualizarSaldo(3,pnlListarPropietariosDomiciliados.getTblMarzo());
            ActualizarSaldo(4,pnlListarPropietariosDomiciliados.getTblAbril());
            ActualizarSaldo(5,pnlListarPropietariosDomiciliados.getTblMayo());
            ActualizarSaldo(6,pnlListarPropietariosDomiciliados.getTblJunio());
            ActualizarSaldo(7,pnlListarPropietariosDomiciliados.getTblJulio());
            ActualizarSaldo(8,pnlListarPropietariosDomiciliados.getTblAgosto());
            ActualizarSaldo(9,pnlListarPropietariosDomiciliados.getTblSeptiembre());
            ActualizarSaldo(10,pnlListarPropietariosDomiciliados.getTblOctubre());
            ActualizarSaldo(11,pnlListarPropietariosDomiciliados.getTblNoviembre());
            ActualizarSaldo(12,pnlListarPropietariosDomiciliados.getTblDiciembre());
        }
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
