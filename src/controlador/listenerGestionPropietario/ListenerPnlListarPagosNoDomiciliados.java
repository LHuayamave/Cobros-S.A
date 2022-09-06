package controlador.listenerGestionPropietario;

import Vista.PnlListarPropietariosNoDomiciliados;
import controlador.PropietarioDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.ImgTabla;
import modelo.PagoNoDomiciliado;

/**
 *
 * @author Luis
 */
public class ListenerPnlListarPagosNoDomiciliados implements ActionListener{

    private final PnlListarPropietariosNoDomiciliados pnlListarPropietariosNoDomiciliados;
    private ArrayList<PagoNoDomiciliado> listaPagoNoDomiciliado;
    private ArrayList<PagoNoDomiciliado> listaPagoNoDomiciliadoMes;
    private TableRowSorter<DefaultTableModel> sorter;
    private PropietarioDB propietarioDB = new PropietarioDB();
    private PagoNoDomiciliado pagoNoDom;

    public ListenerPnlListarPagosNoDomiciliados(PnlListarPropietariosNoDomiciliados pnlListarPropietariosNoDomiciliados) {
        this.pnlListarPropietariosNoDomiciliados = pnlListarPropietariosNoDomiciliados;
    }

    public void llenarTablaTodosPagosNoDomiciliados() {
        int mesActual;
        String recurso = "";
        mesActual = LocalDate.now().getMonth().getValue();
        pnlListarPropietariosNoDomiciliados.getTblTodos().setDefaultRenderer(Object.class, new ImgTabla());
        listaPagoNoDomiciliado = propietarioDB.listarPagosNoDomiciliados();
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) pnlListarPropietariosNoDomiciliados.getTblTodos().getModel();
        for (PagoNoDomiciliado pagos : listaPagoNoDomiciliado) {
            if (pagos.getMesPago() > mesActual) {
                recurso = "/img/pendiente.png";
            } else {
                recurso = "/img/nohecho.png";
            }
            tb.addRow(new Object[]{pagos.getCedulaPropietario(), pagos.getNombrePropietario(), pagos.getTipoImpuesto(),
                pagos.getValorImpuesto(), Month.of(pagos.getMesPago()), new JLabel(new ImageIcon(getClass().getResource(recurso)))});
            pnlListarPropietariosNoDomiciliados.getTblTodos().setAutoCreateRowSorter(true);
            sorter = new TableRowSorter<>(tb);
            pnlListarPropietariosNoDomiciliados.getTblTodos().setRowSorter(sorter);
            i++;

        }
    }

    public void llenarTablaMesPagosNoDomiciliados(Integer mes, JTable tabla) {
        int mesActual;
        mesActual = LocalDate.now().getMonth().getValue();
        String recurso = "";
        tabla.setDefaultRenderer(Object.class, new ImgTabla());
        listaPagoNoDomiciliadoMes = propietarioDB.listarPagosNoDomiciliadosMes(mes);
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        for (PagoNoDomiciliado pagos : listaPagoNoDomiciliadoMes) {
            if (pagos.getMesPago() > mesActual) {
                recurso = "/img/pendiente.png";
            } else {
                recurso = "/img/nohecho.png";
            }
            tb.addRow(new Object[]{pagos.getCedulaPropietario(), pagos.getNombrePropietario(), pagos.getTipoImpuesto(),
                pagos.getValorImpuesto(), new JLabel(new ImageIcon(getClass().getResource(recurso)))});
            tabla.setAutoCreateRowSorter(true);
            sorter = new TableRowSorter<>(tb);
            tabla.setRowSorter(sorter);
            i++;
        }
    }

    public void LimpiarTabla() {
        DefaultTableModel tablaModel = (DefaultTableModel) pnlListarPropietariosNoDomiciliados.getTblTodos().getModel();
        tablaModel.getDataVector().removeAllElements();
        tablaModel.fireTableDataChanged();
        System.out.println("tabla Limpiada");
    }

    private void addListeners() {
        pnlListarPropietariosNoDomiciliados.getBtnEmitirAvisoPago().addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pnlListarPropietariosNoDomiciliados.getBtnEmitirAvisoPago()) {
            ImageIcon icon = new ImageIcon("src/img/emitir.png");
            int input = JOptionPane.showConfirmDialog(null, "Se va a producir un aviso de pago"
                    + "¿Desea continuar?", "Advertencia",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
            if (input == JOptionPane.YES_OPTION) {
                LimpiarTabla();
                llenarTablaTodosPagosNoDomiciliados();
            }
        }
    }
}
