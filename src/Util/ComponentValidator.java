package Util;

import javax.swing.JTextField;

/**
 *
 * @author Eduardo
 */
public class ComponentValidator {

    public static boolean date(JTextField campo) {
        try {
            String[] s = campo.getText().split("/");
            if(s.length != 3) return false;
            int d = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int a = Integer.parseInt(s[2]);
            if((d >= 1 && d <= 31) && (m >= 1 && m <= 12) && (a >= 1000)) {
                return true;
            }
            else {
                return false;
            }
        } catch(Exception ex) {
            return false;
        }
    }
    
    public static boolean time(JTextField campo) {
        try {
            String[] s = campo.getText().split(":");
            if(s.length != 2) return false;
            int hora = Integer.parseInt(s[0]);
            int min = Integer.parseInt(s[1]);
            if((hora >= 0 && hora <= 23) && (min >= 0 && min <= 59)) {
                return true;
            }
            else {
                return false;
            }
        } catch(Exception ex) {
            return false;
        }
    }
    
    public static boolean cpf(JTextField campo) {
        if(campo.getText().equals("   .   .   -  ")) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public static boolean fone(JTextField campo) {
        if(campo.getText().equals("(  )     -    ")) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public static boolean rg(JTextField campo) {
        if(campo.getText().equals("")) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public static boolean integer(JTextField campo) {
        try {
            Integer.parseInt(campo.getText());
            if(campo.getText().equals("")) {
                return false;
            }
            else {
                return true;
            }
        } catch(Exception ex) {
            return false;
        }
    }
    
    public static boolean integerNotNegative(JTextField campo) {
        try {
            int i = Integer.parseInt(campo.getText());
            if(campo.getText().equals("")) {
                return false;
            }
            else if(i < 0) {
                return false;
            }
            else {
                return true;
            }
        } catch(Exception ex) {
            return false;
        }
    }
    
    public static boolean integerNotNegativeAndNotZero(JTextField campo) {
        try {
            int i = Integer.parseInt(campo.getText());
            if(campo.getText().equals("")) {
                return false;
            }
            else if(i <= 0) {
                return false;
            }
            else {
                return true;
            }
        } catch(Exception ex) {
            return false;
        }
    }
    
}
