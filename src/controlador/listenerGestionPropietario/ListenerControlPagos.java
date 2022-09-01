package controlador.listenerGestionPropietario;

import Vista.PnlControlPagos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.Factura;
import controlador.ControlPagoDB;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.Month;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.CobroDomiciliados;
import modelo.ImgTabla;


public class ListenerControlPagos extends KeyAdapter implements ActionListener{
    
    ControlPagoDB ctrlPagoDB = new ControlPagoDB();
    
    private PnlControlPagos controlPagos;
    
    private ArrayList<Factura> arrayFactura;
    
    private TableRowSorter<DefaultTableModel> sorter;
    
    public ListenerControlPagos(){}
    
    public ListenerControlPagos(PnlControlPagos controlPagos) {
        this.controlPagos = controlPagos;
        addListeners();
    }
    
    public void obtenerFactura(ArrayList<Factura> arrayFactura){
        this.arrayFactura = arrayFactura;
        for(int i = 0; i < arrayFactura.size(); i++) {
            System.out.println(arrayFactura.get(i).getNombre());
        }
    }
    
    public void llenarTablaControlTodos() {
        String recurso="";
        controlPagos.getTblControlPago().setDefaultRenderer(Object.class, new ImgTabla());
        arrayFactura = ctrlPagoDB.ListFactura();
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) controlPagos.getTblControlPago().getModel();
        for (Factura pagos : arrayFactura) {
            if(pagos.getMulta().equals("No")){
                recurso = "/img/hecho.png";
             }
            else if(pagos.getMulta().equals("Si")){
                recurso="/img/nohecho.png";
            }
            
            tb.addRow(new Object[] {pagos.getCedula(), pagos.getNombre(), pagos.getAviso_pago(), pagos.getMulta(), pagos.getAviso_recargo(), new JLabel(new ImageIcon(getClass().getResource(recurso)))});
            controlPagos.getTblControlPago().setAutoCreateRowSorter(true);
            sorter = new TableRowSorter<>(tb);
            controlPagos.getTblControlPago().setRowSorter(sorter);
            i++;
        }
    }
    
    public void LimpiarTabla() {
        DefaultTableModel tablaModel = (DefaultTableModel) controlPagos.getTblControlPago().getModel();
        tablaModel.getDataVector().removeAllElements();
        tablaModel.fireTableDataChanged();
        System.out.println("tabla Limpiada");
    }
    
    public void llenarTablaControlDeuda() {
        String recurso="";
        controlPagos.getTblControlPago().setDefaultRenderer(Object.class, new ImgTabla());
        arrayFactura = ctrlPagoDB.ListFactura();
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) controlPagos.getTblControlPago().getModel();
        for (Factura pagos : arrayFactura) {
            if(pagos.getMulta().equals("Si")){
                recurso="/img/nohecho.png";
                tb.addRow(new Object[] {pagos.getCedula(), pagos.getNombre(), pagos.getAviso_pago(), pagos.getMulta(), pagos.getAviso_recargo(), new JLabel(new ImageIcon(getClass().getResource(recurso)))});
                controlPagos.getTblControlPago().setAutoCreateRowSorter(true);
                sorter = new TableRowSorter<>(tb);
                controlPagos.getTblControlPago().setRowSorter(sorter);
            }
            
            i++;
        }
    }
    
        public void llenarTablaControlPago() {
        String recurso="";
        controlPagos.getTblControlPago().setDefaultRenderer(Object.class, new ImgTabla());
        arrayFactura = ctrlPagoDB.ListFactura();
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) controlPagos.getTblControlPago().getModel();
        for (Factura pagos : arrayFactura) {
            if(pagos.getMulta().equals("No")){
                recurso="/img/hecho.png";
                tb.addRow(new Object[] {pagos.getCedula(), pagos.getNombre(), pagos.getAviso_pago(), pagos.getMulta(), pagos.getAviso_recargo(), new JLabel(new ImageIcon(getClass().getResource(recurso)))});
                controlPagos.getTblControlPago().setAutoCreateRowSorter(true);
                sorter = new TableRowSorter<>(tb);
                controlPagos.getTblControlPago().setRowSorter(sorter);
            }
            
            i++;
        }
    }
   
    
    private void addListeners(){
        controlPagos.getBtnGenerarMulta().addActionListener(this);
        controlPagos.getJcbFiltro().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == controlPagos.getJcbFiltro()){
            String filtrar = controlPagos.getJcbFiltro().getSelectedItem().toString();
            System.out.println(filtrar);
            if(filtrar == "Deuda"){
                LimpiarTabla();
                llenarTablaControlDeuda();
            }else if(filtrar == "Pago"){
                LimpiarTabla();
                llenarTablaControlPago();
            }else{
                LimpiarTabla();
                llenarTablaControlTodos();
            }
        }
        if(e.getSource() == controlPagos.getBtnGenerarMulta()){
            System.out.println("Boton multa");
        }
    }
}
