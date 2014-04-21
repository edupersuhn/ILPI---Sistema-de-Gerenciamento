package View.components_controlers;

import javax.swing.JTextField;

/**
 *
 * @author Eduardo
 */
public class TextFieldControler extends ComponentControler<JTextField> implements Input {

    public TextFieldControler(JTextField component) {
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
        return ! getComponent().getText().equals("");
    }

}
