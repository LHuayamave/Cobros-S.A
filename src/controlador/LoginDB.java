package controlador;

/**
 * Este es el modelo que maneja los datos con respecto al logeo
 *
 * @author Grupo E
 */
import Vista.FrmLogin;
import javax.swing.JOptionPane;
import modelo.Empleado;

public class LoginDB {

    private EmpleadoDB empleadoDB;
    private FrmLogin frmLogin;

    public LoginDB() {
    }

    public LoginDB(FrmLogin frmLogin) {
        this.frmLogin = frmLogin;
    }

    /**
     * Este metodo obtiene el empleado con la cedula para la verificacion.
     */
    public int obtenerEmpleado(String cedula, String contrasenia) {
        empleadoDB = new EmpleadoDB();
        this.frmLogin.setEmpleado(empleadoDB.obtenerEmpleado(cedula));
        return verificarAcceso(frmLogin.getEmpleado(), contrasenia);
    }

    /**
     * Este metodo verifica el tipo de acceso que va tener el empleado
     */
    public int verificarAcceso(Empleado empleado, String contrasenia) {
        try{
          if (empleado.getContrasenia().equals(contrasenia)) {
            JOptionPane.showMessageDialog(null, "Bienvenido al sistema ");
            return Integer.parseInt(empleado.getIdTrabajo());
        } else {
            JOptionPane.showMessageDialog(null, "COntraseña Incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }  
        }catch(Exception e){
            System.out.print("No existe empleado");
            return -1;
        }
    }
}
