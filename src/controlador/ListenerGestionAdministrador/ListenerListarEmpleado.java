package controlador.ListenerGestionAdministrador;

import Vista.FrmConsultarEmpleado;
import Vista.FrmListarEmpleado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.Empleado;
import controlador.EmpleadoDB;
import Vista.FrmEditarEmpleado;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;



public class ListenerListarEmpleado extends KeyAdapter implements ActionListener{
    
    EmpleadoDB empdb = new EmpleadoDB();
    
    private FrmEditarEmpleado ventanaEditarEmpleado;
    private ListenerEditarEmpleado controlEditarEmpleado;
    private ListenerConsultarEmpleado controlCosnultarEmpleado;
    private FrmListarEmpleado ventanaListarEmpleado;
    private FrmConsultarEmpleado ventanaConsultarEmpleado;
 
    private ArrayList<Empleado> arrayEmpleados;
    
    private TableRowSorter<DefaultTableModel> sorter;
    
    public ListenerListarEmpleado(){}

    public ListenerListarEmpleado(FrmListarEmpleado ventanaListarEmpleado) {
        this.ventanaListarEmpleado = ventanaListarEmpleado;
        ventanaEditarEmpleado = new FrmEditarEmpleado();
        controlEditarEmpleado = new ListenerEditarEmpleado(ventanaEditarEmpleado);
        
        ventanaConsultarEmpleado = new FrmConsultarEmpleado();
        controlCosnultarEmpleado = new ListenerConsultarEmpleado(ventanaConsultarEmpleado);
        
        addListener();
        addKeyListeners();
    }
    
    private void addListener(){
        
        ventanaListarEmpleado.getBtnBuscar().addActionListener(this);
        ventanaListarEmpleado.getBtnConsultar().addActionListener(this);
        ventanaListarEmpleado.getBtnModificar().addActionListener(this);
    }
    
    private void addKeyListeners(){
        
        ventanaListarEmpleado.getTxtBusqueda().addKeyListener(this);
    }
    
    public void obtenerEmpleados(ArrayList<Empleado> arrayEmpleados){
        this.arrayEmpleados = arrayEmpleados;
        for(int i = 0; i < arrayEmpleados.size(); i++) {
            System.out.println(arrayEmpleados.get(i).getNombre());
        }
    }
    
    public void mostrarDatosTabla(){

        arrayEmpleados = empdb.ListEmpleado();
        for(int i = 0; i < arrayEmpleados.size(); i++) {
            Object[] fila = {
                arrayEmpleados.get(i).getCedula(),
                arrayEmpleados.get(i).getNombre(),
                arrayEmpleados.get(i).getCorreo(),
                arrayEmpleados.get(i).getTelefono()
            };
        ventanaListarEmpleado.getModeloTabla().addRow(fila);
        }
    }
    
    public void LimpiarTabla() {
        DefaultTableModel tablaModel = (DefaultTableModel) ventanaListarEmpleado.getTablaEmpleados().getModel();
        tablaModel.getDataVector().removeAllElements();
        tablaModel.fireTableDataChanged();
        System.out.println("tabla Limpiada");
    }
    
     public void ListarDatos() {

        arrayEmpleados = empdb.ListEmpleado();
        
        int i = 0;
        DefaultTableModel tb = (DefaultTableModel) ventanaListarEmpleado.getTablaEmpleados().getModel();
        for (Empleado empleado : arrayEmpleados) {
        
            tb.addRow(new Object[]{empleado.getCedula(), empleado.getNombre(), empleado.getCorreo(), empleado.getTelefono()});
            ventanaListarEmpleado.getTablaEmpleados().setAutoCreateRowSorter(true);
            sorter = new TableRowSorter<>(tb);
            ventanaListarEmpleado.getTablaEmpleados().setRowSorter(sorter);
            i++;
        }
    }
     
     public void Filtrar() {
        try {
            sorter.setRowFilter(RowFilter.regexFilter(ventanaListarEmpleado.getTxtBusqueda().getText()));
        } catch (Exception e) {

        }
    }
        
        
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == ventanaListarEmpleado.getBtnBuscar()){
           System.out.println("boton buscar");
           Filtrar();
       }
       else if(e.getSource() == ventanaListarEmpleado.getBtnConsultar()){
           empdb.visualizarEmpleado(ventanaListarEmpleado.getTablaEmpleados(), ventanaConsultarEmpleado);
           ventanaConsultarEmpleado.setVisible(true);
       }
       
       else if(e.getSource() == ventanaListarEmpleado.getBtnModificar()){
        empdb.llenarEditarEmpleado(ventanaListarEmpleado.getTablaEmpleados(), ventanaEditarEmpleado);
        ventanaEditarEmpleado.setVisible(true);
       }
       
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == ventanaListarEmpleado.getTxtBusqueda()){
            String busqueda = ventanaListarEmpleado.getTxtBusqueda().getText().trim();
            if (busqueda.isEmpty()) {
                LimpiarTabla();
                ListarDatos();
               
            } 
        }
    }
    
}
