package Util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Eduardo
 */
public class DataConverter {
    
    public static int timeTypeToInt(String time) {
        try {
            String[] s = time.split(":");
            if(s.length != 2) return -1;
            int hora = Integer.parseInt(s[0]);
            int min = Integer.parseInt(s[1]);
            if((hora >= 0 && hora <= 23) && (min >= 0 && min <= 59)) {
                return Integer.parseInt(s[0] + s[1]);
            }
            else return -1;
        } catch(Exception ex) {
            return -1;
        }
    }
    
    public static long dateTypeToLong(String date) {
        try {
            String[] s = date.split("/");
            if(s.length != 3) return -1;
            int d = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int a = Integer.parseInt(s[2]);
            if((d >= 1 && d <= 31) && (m >= 1 && m <= 12) && (a >= 1000)) {
                return Long.parseLong(s[0] + s[1] + s[2]);
            }
            else {
                return -1;
            }
        } catch(Exception ex) {
            return -1;
        }
    }
    
    public static Date stringTypeToSQLDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        Date data;
        try {
            data = new Date(format.parse(date).getTime());
        } catch(ParseException ex) {
            data = new Date(0L);
            ex.printStackTrace();
            return data;
        }
        return data;
    }
    
    public static String sqlDateTypeToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        return format.format(date);
    }
    
}
