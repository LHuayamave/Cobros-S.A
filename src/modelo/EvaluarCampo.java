
package modelo;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author Soldado
 */
public class EvaluarCampo {
    private KeyEvent keyEvent;
    private JTextField jTextField;
    private int tamanio;
    private String mensajeError;
    private int punto;

    public EvaluarCampo(KeyEvent keyEvent, JTextField jTextField, int tamanio) {
        this.keyEvent = keyEvent;
        this.jTextField = jTextField;
        this.tamanio = tamanio;
        this.mensajeError = "Solo digitos, maximo: "+(tamanio+1);
        this.punto=0;
    }

    public KeyEvent getKeyEvent() {
        return keyEvent;
    }

    public void setKeyEvent(KeyEvent keyEvent) {
        this.keyEvent = keyEvent;
    }

    public JTextField getjTextField() {
        return jTextField;
    }

    public void setjTextField(JTextField jTextField) {
        this.jTextField = jTextField;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
    public int getPunto() {
        return punto;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }

}