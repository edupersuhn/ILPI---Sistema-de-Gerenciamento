package View.components_controlers;

import javax.swing.JLabel;

/**
 *
 * @author Eduardo
 */
public class LabelControler extends ComponentControler<JLabel> {

    public LabelControler(JLabel component) {
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
    
    
    
}
