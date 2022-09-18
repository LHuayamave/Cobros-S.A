package controlador.listenerGestionPropietario;

import Vista.FrmConsultarPropietario;
import Vista.FrmEditarPropietario;
import Vista.FrmListarPropietario;
import controlador.PropietarioDB;
import controlador.VehiculoDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.Propietario;

/**
 * Esta clase agraga los listener a los botones del formulario listar propietario
 *  haciendo uso de la interfaz ActionListener .
 *
 * @param cs {@link ActionListener } clase que permite agregar a los
 * escuchadores a los botones.
 * @author Grupo E
 */
public class ListenerFrmListarPropietario implements ActionListener {

    private final FrmListarPropietario frmListarPropietario;
    private ArrayList<Propietario> listaPropietario;
    private TableRowSorter<DefaultTableModel> sorter;
    private PropietarioDB propietarioDB = new PropietarioDB();
    private VehiculoDB vehiculoDB = new VehiculoDB();

    public ListenerFrmListarPropietario(FrmListarPropietario frmListarPropietario) {
        this.frmListarPropietario = frmListarPropietario;
    }

    /**
     * Este metodo recibe el evento del boton presionado y lo compara para tomar
     * un desicion.
     *
     * @param cs {@link ActionEvent } captura el evento que se causo al
     * presionar un boton y ejecuta la acciones definidas.
     */
    
    public void llenarTablaPropietario() {
        listaPropietario = propietarioDB.listarPropietarios();
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) frmListarPropietario.getTblPropietarios().getModel();
        for (Propietario propietario : listaPropietario) {
            tb.addRow(new Object[]{propietario.getCedula(), propietario.getNombre(), propietario.getCorreo(),
                propietario.getTelefono(), Boolean.toString (propietario.getDomiciliado())});
            frmListarPropietario.getTblPropietarios().setAutoCreateRowSorter(true);
            frmListarPropietario.getSorter().setModel(tb);
            frmListarPropietario.getTblPropietarios().setRowSorter(frmListarPropietario.getSorter());
            i++;
        }
    }
    
    public void Filtrar() {
        try {
            frmListarPropietario.getSorter().setRowFilter(RowFilter.regexFilter(frmListarPropietario.getTxtBusqueda().getText()));
        } catch (Exception e) {

        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmListarPropietario.getBtnConsultar()) {
            validarSeleccionPropietarioConsultar();
        } else if (e.getSource() == frmListarPropietario.getBtnModificar()) {
            validarSeleccionPropietarioEditar();
        } else if (e.getSource() == frmListarPropietario.getBtnRefrescar()) {
            frmListarPropietario.LimpiarFormulario();
            llenarTablaPropietario();
        } else if (e.getSource() == frmListarPropietario.getBtnBuscar()) {
            Filtrar();
        }
    }
    
    public void validarSeleccionPropietarioConsultar(){
        if(frmListarPropietario.obtenerCedula()!=null){
            FrmConsultarPropietario frmConsultarPropietario = new FrmConsultarPropietario(
                    frmListarPropietario.obtenerCedula(),frmListarPropietario.obtenerDomiciliado()
                );
            frmConsultarPropietario.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Selecione un Propietario"); 
        }
    }
    
    public void validarSeleccionPropietarioEditar(){
        if(frmListarPropietario.obtenerCedula()!=null){
            FrmEditarPropietario frmEditarPropietario = new FrmEditarPropietario(
                    frmListarPropietario.obtenerCedula(),frmListarPropietario.obtenerDomiciliado()
                );
            frmEditarPropietario.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Selecione un Propietario"); 
        }
    }
    
//    public void validarSeleccionPropietario(){
//        if(frmListarPropietario.obtenerCedula()!=null){
//            FrmConsultarPropietario frmConsultarPropietario = new FrmConsultarPropietario(
//                    frmListarPropietario.obtenerCedula(),frmListarPropietario.obtenerDomiciliado()
//                );
//            frmConsultarPropietario.setVisible(true);
//        }else{
//            JOptionPane.showMessageDialog(null, "Selecione un Propietario"); 
//        }
//    }
}
