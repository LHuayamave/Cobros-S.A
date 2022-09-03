package controlador.listenerGestionPropietario;

import Vista.PnlControlPagos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.Factura;
import controlador.ControlPagoDB;
import java.awt.event.KeyAdapter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.ImgTabla;


public class ListenerControlPagos extends KeyAdapter implements ActionListener{
   ControlPagoDB ctrlPagoDB = new ControlPagoDB();
    private PnlControlPagos controlPagos;
    private ArrayList<Factura> arrayFactura;
    private TableRowSorter<DefaultTableModel> sorter;
    private String recurso;
    private String recurso2;

    public ListenerControlPagos() {
    }

    public ListenerControlPagos(PnlControlPagos controlPagos) {
        this.controlPagos = controlPagos;
        addListeners();
    }

    public void llenarTablaControlTodos() {
        recurso = "";
        recurso2 = "";
        controlPagos.getTblControlPago().setDefaultRenderer(Object.class, new ImgTabla());
        arrayFactura = ctrlPagoDB.ListFactura();
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) controlPagos.getTblControlPago().getModel();
        for (Factura pagos : arrayFactura) {
            if (pagos.getFecha_pago_factura() != null) {
                recurso = "/img/hecho.png";
            } else {
                recurso = "/img/nohecho.png";
            }

            if (pagos.getDias_restantes_pago() <= 0 && pagos.getFecha_pago_factura() == null && pagos.getMulta().equalsIgnoreCase("false")) {
                recurso2 = "/img/notificar.png";
            } else if (pagos.getFecha_pago_factura() == null && pagos.getMulta().equalsIgnoreCase("true")) {
                recurso2 = "/img/notificado.png";
            } else {
                recurso2 = "/img/nonotificar.png";
            }
            tb.addRow(new Object[]{pagos.getCedula(), pagos.getNombre(), pagos.getFecha_emision(), pagos.getFecha_vencimiento(), new JLabel(new ImageIcon(getClass().getResource(recurso))), new JLabel(new ImageIcon(getClass().getResource(recurso2))), pagos.getValor_recargo(), pagos.getTipo_impuesto()});
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
        recurso = "";
        recurso2 = "";
        controlPagos.getTblControlPago().setDefaultRenderer(Object.class, new ImgTabla());
        arrayFactura = ctrlPagoDB.ListFactura();
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) controlPagos.getTblControlPago().getModel();
        for (Factura pagos : arrayFactura) {
            if (pagos.getFecha_pago_factura() == null && pagos.getMulta().equalsIgnoreCase("true")) {
                recurso2 = "/img/notificado.png";
            } else if (pagos.getFecha_pago_factura() == null && pagos.getMulta().equalsIgnoreCase("false")) {
                recurso2 = "/img/notificar.png";
            }
            if (pagos.getFecha_pago_factura() == null) {
                recurso = "/img/nohecho.png";
                tb.addRow(new Object[]{pagos.getCedula(), pagos.getNombre(), pagos.getFecha_emision(), pagos.getFecha_vencimiento(), new JLabel(new ImageIcon(getClass().getResource(recurso))), new JLabel(new ImageIcon(getClass().getResource(recurso2))), pagos.getValor_recargo(), pagos.getTipo_impuesto()});
                controlPagos.getTblControlPago().setAutoCreateRowSorter(true);
                sorter = new TableRowSorter<>(tb);
                controlPagos.getTblControlPago().setRowSorter(sorter);
            }

            i++;
        }
    }

    public void llenarTablaControlPago() {
        recurso = "";
        recurso2 = "";
        controlPagos.getTblControlPago().setDefaultRenderer(Object.class, new ImgTabla());
        arrayFactura = ctrlPagoDB.ListFactura();
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) controlPagos.getTblControlPago().getModel();
        for (Factura pagos : arrayFactura) {
            if (pagos.getFecha_pago_factura() != null) {
                recurso = "/img/hecho.png";
                recurso2 = "/img/nonotificar.png";
                tb.addRow(new Object[]{pagos.getCedula(), pagos.getNombre(), pagos.getFecha_emision(), pagos.getFecha_vencimiento(), new JLabel(new ImageIcon(getClass().getResource(recurso))), new JLabel(new ImageIcon(getClass().getResource(recurso2))), pagos.getValor_recargo(), pagos.getTipo_impuesto()});
                controlPagos.getTblControlPago().setAutoCreateRowSorter(true);
                sorter = new TableRowSorter<>(tb);
                controlPagos.getTblControlPago().setRowSorter(sorter);
            }

            i++;
        }
    }

    public void ActualizarMulta(JTable tabla) {
        float recargo;
        int fila = 0;
        arrayFactura = ctrlPagoDB.ListFactura();
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        fila = tabla.getSelectedRow();
        System.out.println("" + fila);
        for (Factura pagos : arrayFactura) {
            if (pagos.getDias_restantes_pago() <= 0 && pagos.getFecha_pago_factura() == null) {
                recargo = (pagos.getValor_total() * 15) / 100;
            } else {
                recargo = pagos.getValor_recargo();
            }

            if (fila == i) {
                ctrlPagoDB.ActualizarEstadoMulta(pagos.getId_factura(), recargo);

            }
            tabla.setAutoCreateRowSorter(true);
            sorter = new TableRowSorter<>(tb);
            tabla.setRowSorter(sorter);
            i++;
        }

    }

    private void addListeners() {
        controlPagos.getBtnGenerarMulta().addActionListener(this);
        controlPagos.getJcbFiltro().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == controlPagos.getJcbFiltro()) {
            String filtrar = controlPagos.getJcbFiltro().getSelectedItem().toString();
            System.out.println(filtrar);
            if (filtrar == "Deuda") {
                LimpiarTabla();
                llenarTablaControlDeuda();
            } else if (filtrar == "Pago") {
                LimpiarTabla();
                llenarTablaControlPago();
            } else {
                LimpiarTabla();
                llenarTablaControlTodos();
            }
        }
        if (e.getSource() == controlPagos.getBtnGenerarMulta()) {
            ImageIcon icon = new ImageIcon("src/img/multa.png");
            int input = JOptionPane.showConfirmDialog(null, "Se procederá a generar la multa de los usuarios \n"
                    + "seleccionados, recuerde que la misma corresponde \n"
                    + "al 15% del valor del impuesto a cancelar.\n ¿Está seguro de generar la multa?", "Advertencia",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);

            if(input == JOptionPane.YES_OPTION){
                ActualizarMulta(controlPagos.getTblControlPago());
                LimpiarTabla();
                llenarTablaControlTodos();
            }   
        }

    }
}
