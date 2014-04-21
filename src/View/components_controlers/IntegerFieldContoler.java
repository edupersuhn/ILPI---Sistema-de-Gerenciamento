package View.components_controlers;

import javax.swing.JFormattedTextField;

/**
 *
 * @author Eduardo
 */
public class IntegerFieldContoler extends ComponentControler<JFormattedTextField> implements Input {

    public IntegerFieldContoler(JFormattedTextField component) {
        super(component);
    }

    @Override
    public boolean isValid() {
        try {
            int i = Integer.parseInt(getComponent().getText());
            return i > 0;
        } catch(NumberFormatException ex) {
            return false;
        }
    }

    @Override
    public void limpar() {
        getComponent().setText("");
    }

    @Override
    public void setValue(Object obj) {
        getComponent().setText(obj.toString());
    }

    @Override
    public Object getValue() {
        return Integer.parseInt(getComponent().getText());
    }
    
}
