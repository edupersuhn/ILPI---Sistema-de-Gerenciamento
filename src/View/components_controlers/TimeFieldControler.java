package View.components_controlers;

import Util.DataValidator;
import javax.swing.JTextField;

/**
 *
 * @author Eduardo
 */
public class TimeFieldControler extends ComponentControler<JTextField> implements Input {

    public TimeFieldControler(JTextField component) {
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
        if(getComponent().getText().equals("  :  ")) {
            return false;
        }
        else if(DataValidator.timeType(getComponent().getText()) == false) {
            return false;
        }
        else {
            return true;
        }
    }

}
