package Vista;

/**
 * Este panel permite visualizar los propietarios domiciliados y el estado de
 * los cobros realizados a los mismos, así mismo se puede realizar el débito de
 * los impuestos de dichos propietarios haciendo clic en el botón enviar
 * notificación de cobros que a su vez informará a los propietarios sobre dicha
 * acción.
 *
 * @author Grupo E
 */
import controlador.listenerGestionPropietario.ListenerPnlListarCobrosDomiciliados;
import javax.swing.JButton;
import javax.swing.JTable;

public class PnlListarPropietariosDomiciliados extends javax.swing.JPanel {
    private FrmEmpleado frmEmpleado;

    public PnlListarPropietariosDomiciliados(FrmEmpleado frmEmpleado) {
        initComponents();
        this.frmEmpleado = frmEmpleado;
        new ListenerPnlListarCobrosDomiciliados(this).llenarTablaTodosCobrosDomiciliados();
        new ListenerPnlListarCobrosDomiciliados(this).llenarTablaMesCobrosDomiciliados(1, tblEnero);
        new ListenerPnlListarCobrosDomiciliados(this).llenarTablaMesCobrosDomiciliados(2, tblFebrero);
        new ListenerPnlListarCobrosDomiciliados(this).llenarTablaMesCobrosDomiciliados(3, tblMarzo);
        new ListenerPnlListarCobrosDomiciliados(this).llenarTablaMesCobrosDomiciliados(4, tblAbril);
        new ListenerPnlListarCobrosDomiciliados(this).llenarTablaMesCobrosDomiciliados(5, tblMayo);
        new ListenerPnlListarCobrosDomiciliados(this).llenarTablaMesCobrosDomiciliados(6, tblJunio);
        new ListenerPnlListarCobrosDomiciliados(this).llenarTablaMesCobrosDomiciliados(7, tblJulio);
        new ListenerPnlListarCobrosDomiciliados(this).llenarTablaMesCobrosDomiciliados(8, tblAgosto);
        new ListenerPnlListarCobrosDomiciliados(this).llenarTablaMesCobrosDomiciliados(9, tblSeptiembre);
        new ListenerPnlListarCobrosDomiciliados(this).llenarTablaMesCobrosDomiciliados(10, tblOctubre);
        new ListenerPnlListarCobrosDomiciliados(this).llenarTablaMesCobrosDomiciliados(11, tblNoviembre);
        new ListenerPnlListarCobrosDomiciliados(this).llenarTablaMesCobrosDomiciliados(12, tblDiciembre);

        // Añade los listeners a los botones.
        aniadirListenerPnlListarPropietarioDomiciliado();
    }

    // Getter and Setter
    public JTable getTblTodos() {
        return tblTodos;
    }

    public void setTblTodos(JTable tblTodos) {
        this.tblTodos = tblTodos;
    }

    public JButton getBtnEmitirAvisoCobro() {
        return btnEmitirAvisoCobro;
    }

    public void setBtnEmitirAvisoCobro(JButton btnEmitirAvisoCobro) {
        this.btnEmitirAvisoCobro = btnEmitirAvisoCobro;
    }

    public JTable getTblAbril() {
        return tblAbril;
    }

    public void setTblAbril(JTable tblAbril) {
        this.tblAbril = tblAbril;
    }

    public JTable getTblAgosto() {
        return tblAgosto;
    }

    public void setTblAgosto(JTable tblAgosto) {
        this.tblAgosto = tblAgosto;
    }

    public JTable getTblDiciembre() {
        return tblDiciembre;
    }

    public void setTblDiciembre(JTable tblDiciembre) {
        this.tblDiciembre = tblDiciembre;
    }

    public JTable getTblEnero() {
        return tblEnero;
    }

    public void setTblEnero(JTable tblEnero) {
        this.tblEnero = tblEnero;
    }

    public JTable getTblFebrero() {
        return tblFebrero;
    }

    public void setTblFebrero(JTable tblFebrero) {
        this.tblFebrero = tblFebrero;
    }

    public JTable getTblJulio() {
        return tblJulio;
    }

    public void setTblJulio(JTable tblJulio) {
        this.tblJulio = tblJulio;
    }

    public JTable getTblJunio() {
        return tblJunio;
    }

    public void setTblJunio(JTable tblJunio) {
        this.tblJunio = tblJunio;
    }

    public JTable getTblMarzo() {
        return tblMarzo;
    }

    public void setTblMarzo(JTable tblMarzo) {
        this.tblMarzo = tblMarzo;
    }

    public JTable getTblMayo() {
        return tblMayo;
    }

    public void setTblMayo(JTable tblMayo) {
        this.tblMayo = tblMayo;
    }

    public JTable getTblNoviembre() {
        return tblNoviembre;
    }

    public void setTblNoviembre(JTable tblNoviembre) {
        this.tblNoviembre = tblNoviembre;
    }

    public JTable getTblOctubre() {
        return tblOctubre;
    }

    public void setTblOctubre(JTable tblOctubre) {
        this.tblOctubre = tblOctubre;
    }

    public JTable getTblSeptiembre() {
        return tblSeptiembre;
    }

    public void setTblSeptiembre(JTable tblSeptiembre) {
        this.tblSeptiembre = tblSeptiembre;
    }

    public JButton getBtnEmitirRecibo() {
        return btnEmitirRecibo;
    }

    public void setBtnEmitirRecibo(JButton btnEmitirRecibo) {
        this.btnEmitirRecibo = btnEmitirRecibo;
    }

    public FrmEmpleado getFrmEmpleado() {
        return frmEmpleado;
    }

    public void setFrmEmpleado(FrmEmpleado frmEmpleado) {
        this.frmEmpleado = frmEmpleado;
    }

    private void aniadirListenerPnlListarPropietarioDomiciliado() {
        btnEmitirAvisoCobro.addActionListener(new ListenerPnlListarCobrosDomiciliados(this));
        btnEmitirRecibo.addActionListener(new ListenerPnlListarCobrosDomiciliados(this));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tbdMesesPago = new javax.swing.JTabbedPane();
        pnlEnero = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblEnero = new javax.swing.JTable();
        pnlFebrero = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblFebrero = new javax.swing.JTable();
        pnlMarzo = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblMarzo = new javax.swing.JTable();
        pnlAbril = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tblAbril = new javax.swing.JTable();
        pnlMayo = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblMayo = new javax.swing.JTable();
        pnlJunio = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tblJunio = new javax.swing.JTable();
        pnlJulio = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblJulio = new javax.swing.JTable();
        pnlAgosto = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tblAgosto = new javax.swing.JTable();
        pnlSeptiembre = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        tblSeptiembre = new javax.swing.JTable();
        pnlOctubre = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        tblOctubre = new javax.swing.JTable();
        pnlNoviembre = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        tblNoviembre = new javax.swing.JTable();
        pnlDiciembre = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        tblDiciembre = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblTodos = new javax.swing.JTable();
        btnEmitirAvisoCobro = new javax.swing.JButton();
        btnEmitirRecibo = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("Propietarios Domiciliados");

        tbdMesesPago.setBackground(new java.awt.Color(154, 227, 182));

        pnlEnero.setBackground(new java.awt.Color(255, 255, 255));

        tblEnero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRE", "TIPO IMPUESTO", "VALOR", "COBRO REALIZADO"
            }
        ));
        jScrollPane14.setViewportView(tblEnero);

        javax.swing.GroupLayout pnlEneroLayout = new javax.swing.GroupLayout(pnlEnero);
        pnlEnero.setLayout(pnlEneroLayout);
        pnlEneroLayout.setHorizontalGroup(
            pnlEneroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEneroLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlEneroLayout.setVerticalGroup(
            pnlEneroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEneroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdMesesPago.addTab("Enero", pnlEnero);

        pnlFebrero.setBackground(new java.awt.Color(255, 255, 255));

        tblFebrero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRE", "TIPO IMPUESTO", "VALOR", "COBRO REALIZADO"
            }
        ));
        jScrollPane15.setViewportView(tblFebrero);

        javax.swing.GroupLayout pnlFebreroLayout = new javax.swing.GroupLayout(pnlFebrero);
        pnlFebrero.setLayout(pnlFebreroLayout);
        pnlFebreroLayout.setHorizontalGroup(
            pnlFebreroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFebreroLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlFebreroLayout.setVerticalGroup(
            pnlFebreroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFebreroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdMesesPago.addTab("Febrero", pnlFebrero);

        pnlMarzo.setBackground(new java.awt.Color(255, 255, 255));

        tblMarzo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRE", "TIPO IMPUESTO", "VALOR", "COBRO REALIZADO"
            }
        ));
        jScrollPane16.setViewportView(tblMarzo);

        javax.swing.GroupLayout pnlMarzoLayout = new javax.swing.GroupLayout(pnlMarzo);
        pnlMarzo.setLayout(pnlMarzoLayout);
        pnlMarzoLayout.setHorizontalGroup(
            pnlMarzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMarzoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlMarzoLayout.setVerticalGroup(
            pnlMarzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMarzoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdMesesPago.addTab("Marzo", pnlMarzo);

        pnlAbril.setBackground(new java.awt.Color(255, 255, 255));

        tblAbril.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRE", "TIPO IMPUESTO", "VALOR", "COBRO REALIZADO"
            }
        ));
        jScrollPane17.setViewportView(tblAbril);

        javax.swing.GroupLayout pnlAbrilLayout = new javax.swing.GroupLayout(pnlAbril);
        pnlAbril.setLayout(pnlAbrilLayout);
        pnlAbrilLayout.setHorizontalGroup(
            pnlAbrilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAbrilLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlAbrilLayout.setVerticalGroup(
            pnlAbrilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAbrilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdMesesPago.addTab("Abril", pnlAbril);

        pnlMayo.setBackground(new java.awt.Color(255, 255, 255));

        tblMayo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRE", "TIPO IMPUESTO", "VALOR", "COBRO REALIZADO"
            }
        ));
        jScrollPane18.setViewportView(tblMayo);

        javax.swing.GroupLayout pnlMayoLayout = new javax.swing.GroupLayout(pnlMayo);
        pnlMayo.setLayout(pnlMayoLayout);
        pnlMayoLayout.setHorizontalGroup(
            pnlMayoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMayoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlMayoLayout.setVerticalGroup(
            pnlMayoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMayoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdMesesPago.addTab("Mayo", pnlMayo);

        pnlJunio.setBackground(new java.awt.Color(255, 255, 255));

        tblJunio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRE", "TIPO IMPUESTO", "VALOR", "COBRO REALIZADO"
            }
        ));
        jScrollPane19.setViewportView(tblJunio);

        javax.swing.GroupLayout pnlJunioLayout = new javax.swing.GroupLayout(pnlJunio);
        pnlJunio.setLayout(pnlJunioLayout);
        pnlJunioLayout.setHorizontalGroup(
            pnlJunioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJunioLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlJunioLayout.setVerticalGroup(
            pnlJunioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJunioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane19, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdMesesPago.addTab("Junio", pnlJunio);

        pnlJulio.setBackground(new java.awt.Color(255, 255, 255));

        tblJulio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRE", "TIPO IMPUESTO", "VALOR", "COBRO REALIZADO"
            }
        ));
        jScrollPane20.setViewportView(tblJulio);

        javax.swing.GroupLayout pnlJulioLayout = new javax.swing.GroupLayout(pnlJulio);
        pnlJulio.setLayout(pnlJulioLayout);
        pnlJulioLayout.setHorizontalGroup(
            pnlJulioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJulioLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlJulioLayout.setVerticalGroup(
            pnlJulioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlJulioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdMesesPago.addTab("Julio", pnlJulio);

        pnlAgosto.setBackground(new java.awt.Color(255, 255, 255));

        tblAgosto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRE", "TIPO IMPUESTO", "VALOR", "COBRO REALIZADO"
            }
        ));
        jScrollPane21.setViewportView(tblAgosto);

        javax.swing.GroupLayout pnlAgostoLayout = new javax.swing.GroupLayout(pnlAgosto);
        pnlAgosto.setLayout(pnlAgostoLayout);
        pnlAgostoLayout.setHorizontalGroup(
            pnlAgostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgostoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlAgostoLayout.setVerticalGroup(
            pnlAgostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgostoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdMesesPago.addTab("Agosto", pnlAgosto);

        pnlSeptiembre.setBackground(new java.awt.Color(255, 255, 255));

        tblSeptiembre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRE", "TIPO IMPUESTO", "VALOR", "COBRO REALIZADO"
            }
        ));
        jScrollPane22.setViewportView(tblSeptiembre);

        javax.swing.GroupLayout pnlSeptiembreLayout = new javax.swing.GroupLayout(pnlSeptiembre);
        pnlSeptiembre.setLayout(pnlSeptiembreLayout);
        pnlSeptiembreLayout.setHorizontalGroup(
            pnlSeptiembreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSeptiembreLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlSeptiembreLayout.setVerticalGroup(
            pnlSeptiembreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSeptiembreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdMesesPago.addTab("Septiembre", pnlSeptiembre);

        pnlOctubre.setBackground(new java.awt.Color(255, 255, 255));

        tblOctubre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRE", "TIPO IMPUESTO", "VALOR", "COBRO REALIZADO"
            }
        ));
        jScrollPane23.setViewportView(tblOctubre);

        javax.swing.GroupLayout pnlOctubreLayout = new javax.swing.GroupLayout(pnlOctubre);
        pnlOctubre.setLayout(pnlOctubreLayout);
        pnlOctubreLayout.setHorizontalGroup(
            pnlOctubreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOctubreLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlOctubreLayout.setVerticalGroup(
            pnlOctubreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOctubreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdMesesPago.addTab("Octubre", pnlOctubre);

        pnlNoviembre.setBackground(new java.awt.Color(255, 255, 255));

        tblNoviembre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRE", "TIPO IMPUESTO", "VALOR", "COBRO REALIZADO"
            }
        ));
        jScrollPane24.setViewportView(tblNoviembre);

        javax.swing.GroupLayout pnlNoviembreLayout = new javax.swing.GroupLayout(pnlNoviembre);
        pnlNoviembre.setLayout(pnlNoviembreLayout);
        pnlNoviembreLayout.setHorizontalGroup(
            pnlNoviembreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoviembreLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlNoviembreLayout.setVerticalGroup(
            pnlNoviembreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNoviembreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdMesesPago.addTab("Noviembre", pnlNoviembre);

        pnlDiciembre.setBackground(new java.awt.Color(255, 255, 255));

        tblDiciembre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRE", "TIPO IMPUESTO", "VALOR", "COBRO REALIZADO"
            }
        ));
        jScrollPane25.setViewportView(tblDiciembre);

        javax.swing.GroupLayout pnlDiciembreLayout = new javax.swing.GroupLayout(pnlDiciembre);
        pnlDiciembre.setLayout(pnlDiciembreLayout);
        pnlDiciembreLayout.setHorizontalGroup(
            pnlDiciembreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDiciembreLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlDiciembreLayout.setVerticalGroup(
            pnlDiciembreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDiciembreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdMesesPago.addTab("Diciembre", pnlDiciembre);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblTodos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRE", "TIPO IMPUESTO", "VALOR", "MES COBRO", "COBRO REALIZADO"
            }
        ));
        jScrollPane13.setViewportView(tblTodos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdMesesPago.addTab("Todos", jPanel1);

        btnEmitirAvisoCobro.setBackground(new java.awt.Color(51, 51, 51));
        btnEmitirAvisoCobro.setForeground(new java.awt.Color(255, 255, 255));
        btnEmitirAvisoCobro.setText("Emitir aviso de cobro");

        btnEmitirRecibo.setBackground(new java.awt.Color(51, 51, 51));
        btnEmitirRecibo.setForeground(new java.awt.Color(255, 255, 255));
        btnEmitirRecibo.setText("Emitir recibo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEmitirAvisoCobro)
                        .addGap(54, 54, 54)
                        .addComponent(btnEmitirRecibo))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tbdMesesPago, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(0, 29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(tbdMesesPago, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEmitirAvisoCobro)
                    .addComponent(btnEmitirRecibo))
                .addGap(19, 19, 19))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEmitirAvisoCobro;
    private javax.swing.JButton btnEmitirRecibo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JPanel pnlAbril;
    private javax.swing.JPanel pnlAgosto;
    private javax.swing.JPanel pnlDiciembre;
    private javax.swing.JPanel pnlEnero;
    private javax.swing.JPanel pnlFebrero;
    private javax.swing.JPanel pnlJulio;
    private javax.swing.JPanel pnlJunio;
    private javax.swing.JPanel pnlMarzo;
    private javax.swing.JPanel pnlMayo;
    private javax.swing.JPanel pnlNoviembre;
    private javax.swing.JPanel pnlOctubre;
    private javax.swing.JPanel pnlSeptiembre;
    private javax.swing.JTabbedPane tbdMesesPago;
    private javax.swing.JTable tblAbril;
    private javax.swing.JTable tblAgosto;
    private javax.swing.JTable tblDiciembre;
    private javax.swing.JTable tblEnero;
    private javax.swing.JTable tblFebrero;
    private javax.swing.JTable tblJulio;
    private javax.swing.JTable tblJunio;
    private javax.swing.JTable tblMarzo;
    private javax.swing.JTable tblMayo;
    private javax.swing.JTable tblNoviembre;
    private javax.swing.JTable tblOctubre;
    private javax.swing.JTable tblSeptiembre;
    private javax.swing.JTable tblTodos;
    // End of variables declaration//GEN-END:variables
}
