package View.components_controlers;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Eduardo
 */
public class CPFFieldControler extends ComponentControler<JFormattedTextField> implements Input {

    public CPFFieldControler(JFormattedTextField component) {
        super(component);
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
        return getComponent().getText();
    }

    @Override
    public boolean isValid() {
        return !getComponent().getText().equals("   .   .   -  ");
    }

    
}
