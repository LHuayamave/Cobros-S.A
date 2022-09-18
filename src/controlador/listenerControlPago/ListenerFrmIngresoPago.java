package controlador.listenerControlPago;

import Vista.FrmIngresoPago;
import Vista.PnlListarPropietariosNoDomiciliados;
import controlador.PropietarioDB;
import controlador.ValidarCampos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lu
 */
public class ListenerFrmIngresoPago extends KeyAdapter implements ActionListener {

    private FrmIngresoPago frmIngresoPago;

    private PnlListarPropietariosNoDomiciliados pnlListarPropietariosNoDomiciliados;
    private ListenerPnlListarPagosNoDomiciliados listenerPnlListarPagosNoDomiciliados;
    private ValidarCampos validarCampos = new ValidarCampos();
    private PropietarioDB propietarioDB;

    public ListenerFrmIngresoPago(FrmIngresoPago frmIngresoPago) {
        this.frmIngresoPago = frmIngresoPago;
    }
    
    public void limpiarTabla() {
        pnlListarPropietariosNoDomiciliados = frmIngresoPago.getPnlListarPropietariosNoDomiciliados();
        DefaultTableModel tablaModel = (DefaultTableModel) pnlListarPropietariosNoDomiciliados.getTblTodos().getModel();
        tablaModel.getDataVector().removeAllElements();
        tablaModel.fireTableDataChanged();
        System.out.println("tabla Limpiada");
    }
    
    public void llenarCampos() {
        frmIngresoPago.getTxtFactura().setText(frmIngresoPago.getNum_factura());
        frmIngresoPago.getTxtCedula().setText(frmIngresoPago.getCedula());
        frmIngresoPago.getTxtNombre().setText(frmIngresoPago.getNombre());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listenerPnlListarPagosNoDomiciliados = frmIngresoPago.getListenerPnlListarPagosNoDomiciliados();
        if (e.getSource() == frmIngresoPago.getBtnGuardar()) {
            verificarValor();
            limpiarTabla();
            listenerPnlListarPagosNoDomiciliados.llenarTablaTodosPagosNoDomiciliados();
            frmIngresoPago.dispose();
        } else if (e.getSource() == frmIngresoPago.getBtnCancelar()){
            frmIngresoPago.dispose();
        }
    }
    
    public void verificarValor() {
        if (frmIngresoPago.getTxtValor().getText() != null) {
            compararValor();
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un valor");
        }
    }

    private void compararValor() {
        propietarioDB = new PropietarioDB();
        if(convertirFloat(frmIngresoPago.getTxtValor().getText()) == convertirFloat(frmIngresoPago.getValor())){
            propietarioDB.ActualizarFechaPago(frmIngresoPago.getNum_factura());
        }
    }
    
    private float convertirFloat(String valor) {
        float result = 0;
        try {
            result = Float.valueOf(valor);
            System.out.println(result);
        }catch (Exception e){
            System.out.println("Valor numerico");
        }
        return result; 
    }

}
