package View.components_controlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo
 * @param <C>
 */
public abstract class ComponentControler <C extends JComponent> {
    
    private C component;

    public ComponentControler(C component) {
        this.component = component;
    }
    
    public C getComponent() {
        return component;
    }
    
    public void habilitado(boolean flag) {
        getComponent().setEnabled(flag);
    }
    public abstract void limpar();
    public abstract void setValue(Object obj);
    public abstract Object getValue();

}
