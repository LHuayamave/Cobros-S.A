package Vista;

/**
     * Este es el formulario principal del modulo Empleado.
     * @author Grupo E
*/
import controlador.EmpleadoDB;
import controlador.listenerGestionPropietario.ListenerFrmEmpleado;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import modelo.Empleado;

public class FrmEmpleado extends javax.swing.JFrame {

    private ImageIcon imagen;
    private Icon icono;

    public FrmEmpleado(Empleado empleado) {
        initComponents();
        lblNombreUsuario.setText(empleado.getNombre());
        lblRol.setText(new EmpleadoDB().obtenerNombreRol(Integer.parseInt(empleado.getIdTrabajo())));
        this.setLocationRelativeTo(this);
        this.pintarImagen(lblLogo, "src/img/cobros.png");
        aniadirListenerFrmEmpleado();
    }
 // ---------------- temporal   
    public FrmEmpleado() {
        initComponents();
        //lblNombreUsuario.setText(empleado.getNombre());
        //lblRol.setText(new EmpleadoDB().obtenerNombreRol(Integer.parseInt(empleado.getIdTrabajo())));
        this.setLocationRelativeTo(this);
        this.pintarImagen(lblLogo, "src/img/cobros.png");
        aniadirListenerFrmEmpleado();
    }
// --------------------------------------------
    // Getter and Setter
    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }

    public void setBtnCerrarSesion(JButton btnCerrarSesion) {
        this.btnCerrarSesion = btnCerrarSesion;
    }

    public JMenuItem getMniListarPropietario() {
        return mniListarPropietario;
    }

    public void setMniListarPropietario(JMenuItem mniListarPropietario) {
        this.mniListarPropietario = mniListarPropietario;
    }

    public JMenuItem getMniRegistrarPropietario() {
        return mniRegistrarPropietario;
    }

    public void setMniRegistrarPropietario(JMenuItem mniRegistrarPropietario) {
        this.mniRegistrarPropietario = mniRegistrarPropietario;
    }

    public JMenuItem getMniListarPropietariosDomiciliados() {
        return mniListarPropietariosDomiciliados;
    }

    public void setMniListarPropietariosDomiciliados(JMenuItem mniListarPropietariosDomiciliados) {
        this.mniListarPropietariosDomiciliados = mniListarPropietariosDomiciliados;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblNombreUsuario = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        panelAdmin = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        mniRegistrarPropietario = new javax.swing.JMenuItem();
        mniListarPropietario = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        mniListarPropietariosDomiciliados = new javax.swing.JMenuItem();
        mniEstadisticaPag = new javax.swing.JMenuItem();
        mniEstadisticaDeu = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mniListarSolicitudes = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        jPanel3.setBackground(new java.awt.Color(205, 235, 217));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 659, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(140, 191, 160));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(140, 191, 160));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user_2_32.png"))); // NOI18N

        lblNombreUsuario.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lblNombreUsuario.setText("Nombre Completo Usuario");

        lblRol.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblRol.setText("ROL");

        btnCerrarSesion.setBackground(new java.awt.Color(116, 164, 142));
        btnCerrarSesion.setText("Cerrar Sesión");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreUsuario)
                            .addComponent(lblRol))
                        .addGap(100, 100, 100)
                        .addComponent(jLabel1)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCerrarSesion)
                        .addGap(23, 23, 23))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCerrarSesion)
                .addGap(9, 9, 9))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblNombreUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblRol)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(140, 191, 160));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
        );

        panelAdmin.setBackground(new java.awt.Color(255, 255, 255));
        panelAdmin.setMinimumSize(new java.awt.Dimension(0, 0));
        panelAdmin.setPreferredSize(new java.awt.Dimension(853, 363));

        javax.swing.GroupLayout panelAdminLayout = new javax.swing.GroupLayout(panelAdmin);
        panelAdmin.setLayout(panelAdminLayout);
        panelAdminLayout.setHorizontalGroup(
            panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelAdminLayout.setVerticalGroup(
            panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.setBackground(new java.awt.Color(141, 218, 172));
        jMenuBar1.setAutoscrolls(true);
        jMenuBar1.setPreferredSize(new java.awt.Dimension(56, 30));

        jMenu3.setBackground(new java.awt.Color(165, 204, 184));
        jMenu3.setText("Gestión de Propietarios    |");

        mniRegistrarPropietario.setBackground(new java.awt.Color(255, 255, 255));
        mniRegistrarPropietario.setText("Registrar Propietario");
        jMenu3.add(mniRegistrarPropietario);

        mniListarPropietario.setBackground(new java.awt.Color(255, 255, 255));
        mniListarPropietario.setText("Listar Propietario");
        jMenu3.add(mniListarPropietario);

        jMenuBar1.add(jMenu3);

        jMenu4.setBackground(new java.awt.Color(255, 255, 255));
        jMenu4.setText("Gestión de Pagos    |");

        mniListarPropietariosDomiciliados.setBackground(new java.awt.Color(255, 255, 255));
        mniListarPropietariosDomiciliados.setText("Listar Propietarios Domiciliados");
        jMenu4.add(mniListarPropietariosDomiciliados);

        mniEstadisticaPag.setBackground(new java.awt.Color(255, 255, 255));
        mniEstadisticaPag.setText("Listar Propietarios no Domiciliados");
        jMenu4.add(mniEstadisticaPag);

        mniEstadisticaDeu.setBackground(new java.awt.Color(255, 255, 255));
        mniEstadisticaDeu.setText("Control de Pagos");
        jMenu4.add(mniEstadisticaDeu);

        jMenuBar1.add(jMenu4);

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Gestión de Solicitudes");

        mniListarSolicitudes.setBackground(new java.awt.Color(255, 255, 255));
        mniListarSolicitudes.setText("Ingresar Solicitudes");
        jMenu1.add(mniListarSolicitudes);

        jMenuItem1.setText("Verificar Estado de Solicitudes");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    // Permite agregar  la imagen del logotipo de la empresa en el formulario
    private void pintarImagen(JLabel lbl, String ruta) {
        this.imagen = new ImageIcon(ruta);
        this.icono = new ImageIcon(
                this.imagen.getImage().getScaledInstance(
                        lbl.getWidth(),
                        lbl.getHeight(),
                        Image.SCALE_DEFAULT)
        );
        lbl.setIcon(this.icono);
        this.repaint();
    }
    
    // Permite agregar  la imagen del logotipo de la empresa en el formulario
    public void ShowPanel(JPanel p) {
        p.setSize(864, 424);
        p.setLocation(0, 0);
        panelAdmin.removeAll();
        panelAdmin.add(p, BorderLayout.CENTER);
        panelAdmin.revalidate();
        panelAdmin.repaint();

    }
    /**
     * Metodo que agrega los listener a sus respectivo botones mediante una clase 
     * que implementa las interfaces ActionListener.
     * @param cs {@link ActionEvent} clase que permite agregar listener a los botones.
     */
    private void aniadirListenerFrmEmpleado(){
        this.mniRegistrarPropietario.addActionListener(new ListenerFrmEmpleado(this));
        this.mniListarPropietario.addActionListener(new ListenerFrmEmpleado(this));
        this.mniListarPropietariosDomiciliados.addActionListener(new ListenerFrmEmpleado(this));
        this.btnCerrarSesion.addActionListener(new ListenerFrmEmpleado(this));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JLabel lblRol;
    private javax.swing.JMenuItem mniEstadisticaDeu;
    private javax.swing.JMenuItem mniEstadisticaPag;
    private javax.swing.JMenuItem mniListarPropietario;
    private javax.swing.JMenuItem mniListarPropietariosDomiciliados;
    private javax.swing.JMenuItem mniListarSolicitudes;
    private javax.swing.JMenuItem mniRegistrarPropietario;
    private javax.swing.JPanel panelAdmin;
    // End of variables declaration//GEN-END:variables
}
