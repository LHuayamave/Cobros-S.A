
package configSQL;
/**
     * Esta clase es la que permite la coneccion entre la BD y el Programa.
     * @author Grupo E
*/
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB {
    private static Connection connection;
    /**
     * Metodo que permite crear la coneccion a las BD 
     * @param cs {@link Connection} clase que une el programa con la BD.
     * @return rs regresa la coneccion si no hay inconvenientes.
     * @throws SQLException 
     * @throws ClassNotFoundException
     */
    public static Connection conectar() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "ADMIN_COBROS";
            String password = "ADMIN_COBROS";

            connection = DriverManager.getConnection(myDB, usuario, password);
            System.out.println("Conexion exitosa");
            return connection;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
