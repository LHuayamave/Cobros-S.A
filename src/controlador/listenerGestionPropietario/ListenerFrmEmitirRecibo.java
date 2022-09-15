package controlador.listenerGestionPropietario;

import Vista.FrmEmitirRecibo;
import controlador.FacturaDB;
import controlador.ValidarCampos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Soldado
 */
public class ListenerFrmEmitirRecibo implements ActionListener {

    private FacturaDB facturaDB;
    private FrmEmitirRecibo frmEmitirRecibo;
    private ValidarCampos validarCampos;

    public ListenerFrmEmitirRecibo(FrmEmitirRecibo frmEmitirRecibo) {
        this.frmEmitirRecibo = frmEmitirRecibo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmEmitirRecibo.getBtnEnviar()) {
            frmEmitirRecibo.dispose();
        }
    }

    public int llenarCampos() {
        facturaDB = new FacturaDB();
        if (frmEmitirRecibo.getEsDomiciliado()) {
            facturaDB.obtenerReciboDomiciliado(frmEmitirRecibo);
        } else {
            frmEmitirRecibo.getLblEmail().setText("");
            facturaDB.obtenerReciboaNoDomiciliado(frmEmitirRecibo);
            frmEmitirRecibo.getBtnEnviar().setText("Imprimir recibo"); 
        }

        return 0;
    }

}
