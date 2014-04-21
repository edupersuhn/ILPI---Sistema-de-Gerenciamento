package View.components_controlers;

import javax.swing.JFormattedTextField;

/**
 *
 * @author Eduardo
 */
public class FoneFieldControler extends ComponentControler<JFormattedTextField> implements Input {

    public FoneFieldControler(JFormattedTextField component) {
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
        if(getComponent().getText().equals("(  )     -    ")) {
            return false;
        }
        else return true;
    }    
}
