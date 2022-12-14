package controlador.listenerControlPago;

import Vista.FrmEmitirRecibo;
import Vista.PnlListarPropietariosDomiciliados;
import controlador.PropietarioDB;
import controlador.ValidarCampos;
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
import modelo.CobroDomiciliados;
import modelo.ImgTabla;

/**
 * Esta clase agrega los listener al botón del panel listar propietarios
 * domiciliados haciendo uso de la interfaz ActionListener .
 *
 * @author Grupo E
 */
public class ListenerPnlListarCobrosDomiciliados implements ActionListener {

    private final PnlListarPropietariosDomiciliados pnlListarPropietariosDomiciliados;
    private ArrayList<CobroDomiciliados> listaCobrosDomiciliados;
    private ArrayList<CobroDomiciliados> listaCobrosDomiciliadosMes;
    private TableRowSorter<DefaultTableModel> sorter;
    private PropietarioDB propietarioDB = new PropietarioDB();
    private CobroDomiciliados cobrosDom;
    private ValidarCampos validarCampos;

    public ListenerPnlListarCobrosDomiciliados(PnlListarPropietariosDomiciliados pnlListarPropietariosDomiciliados) {
        this.pnlListarPropietariosDomiciliados = pnlListarPropietariosDomiciliados;
    }

    /**
     * Este metodo permite llenar la tabla de la pestaña todos con los datos de
     * los propietarios domiciliados
     *
     * @author Grupo E
     */
    public void llenarTablaTodosCobrosDomiciliados() {
        int mesActual;
        String recurso = "";
        mesActual = LocalDate.now().getMonth().getValue();
        pnlListarPropietariosDomiciliados.getTblTodos().setDefaultRenderer(Object.class, new ImgTabla());
        listaCobrosDomiciliados = propietarioDB.listarCobrosDomiciliados();
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) pnlListarPropietariosDomiciliados.getTblTodos().getModel();
        for (CobroDomiciliados cobros : listaCobrosDomiciliados) {
            if (cobros.getSaldo() > cobros.getValorImpuesto() && cobros.getMesPago() <= mesActual) {
                recurso = "/img/hecho.png";
            } else if (cobros.getMesPago() > mesActual) {
                recurso = "/img/pendiente.png";
            } else {
                recurso = "/img/nohecho.png";
            }
            tb.addRow(new Object[]{cobros.getIdFactura(),cobros.getCedulaPropietario(), cobros.getNombrePropietario(), cobros.getTipoImpuesto(),
                cobros.getValorImpuesto(), Month.of(cobros.getMesPago()), new JLabel(new ImageIcon(getClass().getResource(recurso)))});
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
            ActualizarSaldo(2, pnlListarPropietariosDomiciliados.getTblFebrero());
            ActualizarSaldo(3, pnlListarPropietariosDomiciliados.getTblMarzo());
            ActualizarSaldo(4, pnlListarPropietariosDomiciliados.getTblAbril());
            ActualizarSaldo(5, pnlListarPropietariosDomiciliados.getTblMayo());
            ActualizarSaldo(6, pnlListarPropietariosDomiciliados.getTblJunio());
            ActualizarSaldo(7, pnlListarPropietariosDomiciliados.getTblJulio());
            ActualizarSaldo(8, pnlListarPropietariosDomiciliados.getTblAgosto());
            ActualizarSaldo(9, pnlListarPropietariosDomiciliados.getTblSeptiembre());
            ActualizarSaldo(10, pnlListarPropietariosDomiciliados.getTblOctubre());
            ActualizarSaldo(11, pnlListarPropietariosDomiciliados.getTblNoviembre());
            ActualizarSaldo(12, pnlListarPropietariosDomiciliados.getTblDiciembre());
            
            ImageIcon icon1 = new ImageIcon("src/img/cobrorealizado.png");
            ImageIcon icon2 = new ImageIcon("src/img/notificacionenviada.png");
            int input1 = JOptionPane.showConfirmDialog(null, "Se ha procedido a realizar el cobro del impuesto. \n"
                    + "¿Desea notificar al propietario sobre esta acción?", "Informe",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon1);

            if(input1 == JOptionPane.YES_OPTION){
                int input2 = JOptionPane.showConfirmDialog(null, "Se ha notificado al cliente con el siguiente mensaje: \n"
                        + "Estimado cliente, le informamos que se ha procedido a realizar \n"
                        + "el cobro de su impuesto, pronto le estaremos enviando su recibo \n"
                        + "como evidencia de dicha transacción.", "Notificar",
                    JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE, icon2);
            }   
        }else if(e.getSource() == pnlListarPropietariosDomiciliados.getBtnEmitirRecibo()){
            obtenerReciboDomiciliado();
        }  
    }
    public void obtenerReciboDomiciliado(){
        String datosDomiciliado[] = new String[1];
        datosDomiciliado[0] = pnlListarPropietariosDomiciliados.obtenerNumFactura();
        verFactura(datosDomiciliado);
    }
    public void verFactura(String datosDomiciliado[]) {
        validarCampos = new ValidarCampos();
        try {
            if (datosDomiciliado[0] != null) {
                FrmEmitirRecibo frmEmitirRecibo = new FrmEmitirRecibo(
                        pnlListarPropietariosDomiciliados.getFrmEmpleado(), true, datosDomiciliado[0]);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para ingresar el pago.");
            }
        } catch (Exception e) {
            validarCampos.mensajeError("El usuario aun no cancela, no se puede emitir el recibo");
        }

    }

    /**
     * Esta método permite llenar las tablas de las pestañas del panel listar
     * propietarios domiciliados de acuerdo al mes correspondiente de cobro de
     * cada uno de los propietarios.
     *
     * @param mes es el número del mes del que se desea sean llenadas las tablas
     * @param tabla es la tabla en la que se llenarán los datos.
     * @author Grupo E
     */
    public void llenarTablaMesCobrosDomiciliados(Integer mes, JTable tabla) {
        int mesActual;
        mesActual = LocalDate.now().getMonth().getValue();
        String recurso = "";
        tabla.setDefaultRenderer(Object.class, new ImgTabla());
        listaCobrosDomiciliadosMes = propietarioDB.listarCobrosDomiciliadosMes(mes);
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        for (CobroDomiciliados cobros : listaCobrosDomiciliadosMes) {
            if (cobros.getSaldo() > cobros.getValorImpuesto() && cobros.getMesPago() <= mesActual) {
                recurso = "/img/hecho.png";
            } else if (cobros.getMesPago() > mesActual) {
                recurso = "/img/pendiente.png";
            } else {
                recurso = "/img/nohecho.png";
            }
            tb.addRow(new Object[]{cobros.getIdFactura(),cobros.getCedulaPropietario(), cobros.getNombrePropietario(), cobros.getTipoImpuesto(),
                cobros.getValorImpuesto(), new JLabel(new ImageIcon(getClass().getResource(recurso)))});
            tabla.setAutoCreateRowSorter(true);
            sorter = new TableRowSorter<>(tb);
            tabla.setRowSorter(sorter);
            i++;
        }
    }

    
    /**
     * Esta método permite actualizar los datos de los propietarios domiciliados
     * al momento de realizarles el cobro de su impuesto vehicular.
     *
     * @param mes es el número del mes del que se desea sean llenadas las tablas
     * @param tabla es la tabla en la que se llenarán los datos.
     * @author Grupo E
     */
    public void ActualizarSaldo(Integer mes, JTable tabla) {
        float saldoActual;
        int mesActual;
        int fila;
        mesActual = LocalDate.now().getMonth().getValue();
        listaCobrosDomiciliadosMes = propietarioDB.listarCobrosDomiciliadosMes(mes);
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        for (CobroDomiciliados cobros : listaCobrosDomiciliadosMes) {
            if (cobros.getSaldo() > cobros.getValorImpuesto() && cobros.getMesPago() <= mesActual) {
                saldoActual = cobros.getSaldo() - cobros.getValorImpuesto();
            } else {
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
