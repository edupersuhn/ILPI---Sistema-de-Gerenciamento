package View.components_controlers;

import java.util.Iterator;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Eduardo
 */
public class ComboBoxControler <T> extends ComponentControler<JComboBox> implements Input {

    public ComboBoxControler(JComboBox component) {
        super(component);
    }
    
    @Override
    public void limpar() {
        getComponent().setSelectedIndex(0);
    }

    @Override
    public void setValue(Object obj) {
        getComponent().addItem(obj);
    }

    @Override
    public Object getValue() {
        return getComponent().getSelectedItem();
    }

    @Override
    public boolean isValid() {
        return !(getComponent().getSelectedIndex() == 0);
    }

}