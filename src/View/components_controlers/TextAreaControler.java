package View.components_controlers;

import javax.swing.JTextArea;

/**
 *
 * @author Eduardo
 */
public class TextAreaControler extends ComponentControler<JTextArea> implements Input {

    public TextAreaControler(JTextArea component) {
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
        if(getComponent().getText().equals("")) {
            return false;
        }
        else {
            return true;
        }
    }    
}
