package View.components_controlers;

import Util.DataValidator;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Eduardo
 */
public class DateFieldControler extends ComponentControler<JFormattedTextField> implements Input {    

    public DateFieldControler(JFormattedTextField component) {
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
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date data;
        try {
            data = new Date(format.parse(getComponent().getText()).getTime());
        } catch(ParseException ex) {
            data = new Date(0L);
            ex.printStackTrace();
            return data;
        }
        return data;
    }

    @Override
    public boolean isValid() {
        if(getComponent().getText().equals("  /  /    ")){
            return false;
        }
        else if(DataValidator.dateType(getComponent().getText()) == false) {
            return false;
        }
        else {
            return true;
        }
    }

}
