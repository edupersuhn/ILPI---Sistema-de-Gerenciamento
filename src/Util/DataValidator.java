package Util;

/**
 *
 * @author Eduardo
 */
public class DataValidator {

    public static boolean timeType(String time) {
        try {
            String[] s = time.split(":");
            if(s.length != 2) return false;
            int hora = Integer.parseInt(s[0]);
            int min = Integer.parseInt(s[1]);
            if((hora >= 0 && hora <= 23) && (min >= 0 && min <= 59)) return true;
            else return false;
        } catch(Exception ex) {
            return false;
        }
    }
    
    public static boolean dateType(String date) {
        try {
            String[] s = date.split("/");
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
    
}
