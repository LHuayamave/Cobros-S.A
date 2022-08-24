package controlador;

/**
 * Esta clase hace las validaciones de las cajas de texto de los formularios.
 *
 * @author Grupo E
 */
import Vista.FrmEditarPropietario;
import Vista.PnlRegistroPropietario;
import controlador.Excepciones.CedulaPropietarioException;
import controlador.Excepciones.EdadExcepcion;
import controlador.Excepciones.PlacaException;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.EvaluarCampo;

public class ValidarCampos {

    private PropietarioDB propietarioDB;
    private EmpleadoDB empleadoDB;
    private VehiculoDB vehiculoDB;

    /**
     * Este método verifica que solo ingrese letras en la caja de texto, hace
     * uso de KeyEvent para verificar que letra ingreso.
     *
     * @param cs {@link KeyEvent} clase que captura el evento y permite
     * recuperar la letra que se ingreso.
     * @return rs no regresa ningun valor pero borra el caracter si no es una
     * letra o si es un espacio.
     */
    public void validaLetras(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (Character.isDigit(c) && !Character.isSpaceChar(0)) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo Letras");
        }
    }

    /**
     * Este método verifica que solo ingrese numeros en la caja de texto y que
     * su tamño no sea mayor a 10, hace uso de KeyEvent para verificar el
     * caracter y un JTextField para obtener el tamaño de la cadena.
     *
     * @param cs {@link KeyEvent} clase que captura el evento y permite
     * recuperar la letra que se ingreso.
     * @param cs {@link JTextField} clase que permite crear una caja de texto
     * para el ingreso de informacion.
     * @return rs no regresa ningun valor pero borra el caracter si no es un
     * numero o si es mayor 10.
     */
    public void validarTelefono(KeyEvent evt, JTextField txtTelefono) {
        char c = evt.getKeyChar();
        if (Character.isLetter(c) || txtTelefono.getText().length() > 9) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo Números de 10 dígitos");
        }
    }

    /**
     * Este método verifica que solo ingrese numeros en la caja de texto y que
     * su tamño no sea mayor a 10, hace uso de KeyEvent para verificar el
     * caracter y un JTextField para obtener el tamaño de la cadena.
     *
     * @param cs {@link KeyEvent} clase que captura el evento y permite
     * recuperar la letra que se ingreso.
     * @param cs {@link JTextField} clase que permite crear una caja de texto
     * para el ingreso de informacion.
     * @return rs no regresa ningun valor pero borra el caracter si no es un
     * numero o si es mayor 10.
     */
    public void validarCedula(KeyEvent evt, JTextField txtCedula) {
        char c = evt.getKeyChar();
        if (Character.isLetter(c) || txtCedula.getText().length() > 9) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo números de 10 dígitos");
        }
    }
    /**
     * Este método verifica que solo ingrese numeros en la caja de texto y que
     * su tamño no sea mayor a 4, hace uso de KeyEvent para verificar el
     * caracter y un JTextField para obtener el tamaño de la cadena.
     *
     * @param cs {@link KeyEvent} clase que captura el evento y permite
     * recuperar la letra que se ingreso.
     * @param cs {@link JTextField} clase que permite crear una caja de texto
     * para el ingreso de informacion.
     * @return rs no regresa ningun valor pero borra el caracter si no es un
     * numero o si es mayor 4.
     */
    public void validarAnioVehiculo(KeyEvent evt, JTextField txtAnioVehiculo) {
        char c = evt.getKeyChar();
        if (Character.isLetter(c) || txtAnioVehiculo.getText().length() > 3) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "El año no puede tener más de 4 dígitos");

        }
    }
    public void validarCampoNumerico(EvaluarCampo evaluarCampo ){
        char c = evaluarCampo.getKeyEvent().getKeyChar();
        if (Character.isLetter(c) || evaluarCampo.getjTextField().getText().length() > evaluarCampo.getTamanio()) {
            evaluarCampo.getKeyEvent().consume();
            JOptionPane.showMessageDialog(null, evaluarCampo.getMensajeError());

        }
    }
    /**
     * Este método verifica que solo ingrese numeros en la caja de texto y que
     * su tamño no sea mayor a 10, hace uso de KeyEvent para verificar el
     * caracter y un JTextField para obtener el tamaño de la cadena.
     *
     * @param cs {@link KeyEvent} clase que captura el evento y permite
     * recuperar la letra que se ingreso.
     * @param cs {@link JTextField} clase que permite crear una caja de texto
     * para el ingreso de informacion.
     * @return rs no regresa ningun valor pero borra el caracter si no es un
     * numero o si es mayor 10.
     */
    public boolean validarEdad(Date date) {
        LocalDate fechaNacimiento = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - fechaNacimiento.getYear();
        try {
            if (edad < 18) {
                throw new EdadExcepcion();
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public void verificarSiExistePropietario(JTextField txtCedula) {
        if (txtCedula.getText().length() == 10) {
            mensajeSiExistePropietario(txtCedula);
        }
    }

    public void mensajeSiExistePropietario(JTextField txtCedula) {
        propietarioDB = new PropietarioDB();
        if (propietarioDB.verificarSiExistePropietario(txtCedula.getText()) != null) {
            try {
                throw new CedulaPropietarioException();
            } catch (CedulaPropietarioException cedulaPropietarioException) {
                JOptionPane.showMessageDialog(null, cedulaPropietarioException.getMessage());
                txtCedula.setText(null);
            }
        } else {
            System.out.print("ok");
        }
    }

    public void verificarSiExisteEmpleado(JTextField txtCedula) {
        if (txtCedula.getText().length() == 10) {
            mensajeSiExisteEmpleado(txtCedula.getText());
        }
    }

    public void mensajeSiExisteEmpleado(String cedula) {
        empleadoDB = new EmpleadoDB();
        if (!empleadoDB.verificarSiExisteEmpleado(cedula).equals("000")) {
            JOptionPane.showMessageDialog(null, "Esta cedula ya esta registrada.");
            System.out.print(cedula);
        } else {
            System.out.print("ok");
        }
    }

    public void verificarSiExistePlaca(JTextField txtPlaca) {
        if (txtPlaca.getText().length() == 8) {
            mensajeSiExistePlaca(txtPlaca);
        }
    }

    public void mensajeSiExistePlaca(JTextField txtPlaca) {
        vehiculoDB = new VehiculoDB();
        if (vehiculoDB.verificarSiExistePlaca(txtPlaca.getText()) != null) {
            try {
                throw new PlacaException();
            } catch (PlacaException placaException) {
                JOptionPane.showMessageDialog(null, placaException.getMessage());
            }
        } else {
            System.out.print("ok");
        }
    }

    public boolean validarCampoVacioNoDomiciliado(PnlRegistroPropietario pnlRegistroPropietario) {
        if (!pnlRegistroPropietario.getTxtCedula().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtNombre().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtTelefono().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtDireccion().getText().isEmpty()
                && pnlRegistroPropietario.getDtcFechaNacimiento().getDate() != null
                && !pnlRegistroPropietario.getTxtCorreo().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtPlaca().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtMarca().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtModelo().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtAnioVehiculo().getText().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "LLene todos los campos");
            return false;
        }
    }
    public boolean validarCampoVacioDomiciliado(PnlRegistroPropietario pnlRegistroPropietario) {
        if (!pnlRegistroPropietario.getTxtCedula().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtNombre().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtTelefono().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtDireccion().getText().isEmpty()
                && pnlRegistroPropietario.getDtcFechaNacimiento().getDate() != null
                && !pnlRegistroPropietario.getTxtCorreo().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtPlaca().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtMarca().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtModelo().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtAnioVehiculo().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtCtaBancaria().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtCVV().getText().isEmpty()
                && !pnlRegistroPropietario.getTxtSaldo().getText().isEmpty()   
            ) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "LLene todos los campos");
            return false;
        }
    }
    public boolean validarCampoVacio(FrmEditarPropietario frmEditarPropietario) {
        if (!frmEditarPropietario.getTxtCedula().getText().isEmpty()
                && !frmEditarPropietario.getTxtNombre().getText().isEmpty()
                && !frmEditarPropietario.getTxtTelefono().getText().isEmpty()
                && !frmEditarPropietario.getTxtDireccion().getText().isEmpty()
                && frmEditarPropietario.getDtcFechaNacimiento().getDate() != null
                && !frmEditarPropietario.getTxtCorreo().getText().isEmpty()
                && !frmEditarPropietario.getTxtPlaca().getText().isEmpty()
                && !frmEditarPropietario.getTxtMarca().getText().isEmpty()
                && !frmEditarPropietario.getTxtModelo().getText().isEmpty()
                && !frmEditarPropietario.getTxtAnioVehiculo().getText().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "LLene todos los campos");
            return false;
        }
    }
    
    public String validarFormatoFecha( Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String fechaConFormato = simpleDateFormat.format(date);
        return fechaConFormato;
    }
}
