package Vista;
/**
     * Este formulario permite Listar a los propietarios mediante una tabla 
     * donde se puede elegir una opcion para listarla o modificarla.
     * @author Grupo E
*/
import controlador.PropietarioDB;
import controlador.VehiculoDB;
import controlador.listenerGestionPropietario.ListenerFrmListarPropietario;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class FrmListarPropietario extends javax.swing.JPanel {
    public FrmListarPropietario() {
        initComponents();
        new ListenerFrmListarPropietario(this).llenarTablaPropietario();
        // Añade los listeners a los botones.
        aniadirListenerFrmListarPropietario();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPropietarios = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnRefrescar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(853, 363));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("Listar Propietarios");

        tblPropietarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRE", "CORREO", "TELEFONO", "DOMICILIADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPropietarios);

        jLabel2.setText("Buscar:");

        btnBuscar.setBackground(new java.awt.Color(152, 202, 167));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N

        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/consultar.png"))); // NOI18N

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N

        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh_32.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(301, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefrescar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Getter and Setter
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JButton getBtnConsultar() {
        return btnConsultar;
    }

    public void setBtnConsultar(JButton btnConsultar) {
        this.btnConsultar = btnConsultar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(JButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public JButton getBtnRefrescar() {
        return btnRefrescar;
    }

    public void setBtnRefrescar(JButton btnRefrescar) {
        this.btnRefrescar = btnRefrescar;
    }
    /**
     * Metodo que rellena el contenido de la tabla haciendo uso de los controladores
     * de VehiculoDB y PropietarioDB
     * @param cs {@link VehiculoDB} clase que maneja la informacion de vehiculo
     * @param cs {@link PropietarioDB} clase que maneja la informacion de propietario.
     */
//    public void llenarTablaPropietario() {
//        listaPropietario = propietarioDB.listarPropietarios();
//        int i = 0;
//        DefaultTableModel tb = (DefaultTableModel) tblPropietarios.getModel();
//        for (Propietario propietario : listaPropietario) {
////            vehiculo = vehiculoDB.obtenerVehiculo(propietario.getPlaca());
//            tb.addRow(new Object[]{propietario.getCedula(), propietario.getNombre(), propietario.getTelefono(),
//                vehiculo.getPlaca(), vehiculo.getMarca()});
//            tblPropietarios.setAutoCreateRowSorter(true);
//            sorter = new TableRowSorter<>(tb);
//            tblPropietarios.setRowSorter(sorter);
//            i++;
//        }
//    }
    /*Permite validar que se haya selecionado una celda de la tabla*/
    public void validarCedulaModificar(FrmEditarPropietario frmEditarPropietario, String cedula) {
        if (obtenerCedula() != null) {
            frmEditarPropietario.llenarFrmEditarPropietario(obtenerCedula());
            frmEditarPropietario.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un Propietario");
        }
    }
    /*Permite validar que se haya selecionado una celda de la tabla*/
//    public void validarCedulaCosultar(FrmConsultarPropietario frmConsultarPropietario, String cedula) {
//        if (obtenerCedula() != null) {
//            frmConsultarPropietario.llenarFrmConsultarPropietario(obtenerCedula());
//            frmConsultarPropietario.setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "Seleccione un Propietario");
//        }
//    }
    /*Metodo que obtiene la cedula de la celda selecionada*/
    public String obtenerCedula() {
        int fila = -1;
        fila = tblPropietarios.getSelectedRow();
        if (fila != -1) {
            return tblPropietarios.getValueAt(fila, 0).toString();
        } else {
            return null;
        }
    }
    
    public String obtenerDomiciliado(){
        int fila = -1;
        fila = tblPropietarios.getSelectedRow();
        if (fila != -1) {
            return tblPropietarios.getValueAt(fila, 4).toString();
        } else {
            return null;
        }
    }
    /*Borra el contenido de la tabla*/
    public void LimpiarFormulario() {
        DefaultTableModel tb = (DefaultTableModel) tblPropietarios.getModel();
        for (int i = tb.getRowCount() - 1; i >= 0; i--) {
            tb.removeRow(i);
        }
    }
//    /*Permite Filtar el contenido de la tabla.*/
//    public void Filtrar() {
//        try {
//            sorter.setRowFilter(RowFilter.regexFilter(txtBusqueda.getText()));
//        } catch (Exception e) {
//
//        }
//    }
    /**
     * Metodo que agrega los listener a sus respectivo botones mediante una clase 
     * que implementa las interfaces ActionListener.
     * @param cs {@link ActionEvent} clase que permite agregar listener a los botones.
     */
    private void aniadirListenerFrmListarPropietario(){
        btnBuscar.addActionListener(new ListenerFrmListarPropietario(this));
        btnConsultar.addActionListener(new ListenerFrmListarPropietario(this));
        btnModificar.addActionListener(new ListenerFrmListarPropietario(this));
        btnRefrescar.addActionListener(new ListenerFrmListarPropietario(this));
    }

    public JTable getTblPropietarios() {
        return tblPropietarios;
    }

    public void setTblPropietarios(JTable tblPropietarios) {
        this.tblPropietarios = tblPropietarios;
    }

    public JTextField getTxtBusqueda() {
        return txtBusqueda;
    }

    public void setTxtBusqueda(JTextField txtBusqueda) {
        this.txtBusqueda = txtBusqueda;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tblPropietarios;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables

}
