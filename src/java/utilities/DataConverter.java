package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Helps converting various date formats to each other.
 */
public class DataConverter {

    /**
     * Converts Java Date to sql Date format
     * @param javaDate java date
     * @return java.sql.Date sql date
     */
    public static java.sql.Date javaDateToSQL(java.util.Date javaDate) {
        java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
        return sqlDate;
    }
    
    /**
     * Converts Java date to sql DateTime format
     * @param javaDate java date
     * @return String representation of sql DateTime (yyyy-MM-dd HH:mm:ss).
     */
    public static String javaDateToSQLDateTime(java.util.Date javaDate) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        return sdf.format(javaDate);
    }
    
    /**
     * Converts sql Date to Java Date
     * @param sqlDate sql date
     * @return java.util.Date representation of a date
     */
    public static java.util.Date sqlDateToJava(java.sql.Date sqlDate) {
        java.util.Date javaDate = new java.util.Date(sqlDate.getTime());
    return javaDate;
    }
    
    /**
     * Converts String Date format to Java Date
     * @param stringDate String representation of sql Date (yyyy-MM-dd).
     * @return java.util.Date representation of a date
     */
    public static java.util.Date stringDateToJava(String stringDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
        
        try {
            parsedDate = dateFormat.parse(stringDate);  
            return parsedDate;
        } catch (Exception ex) {
            try {
                return (dateFormat.parse("01/01/1975"));
            } catch (ParseException ex1) {
            }
        }
        
        return parsedDate;
    }
    
    /**
     * Converts String DateTime format to Java Date
     * @param stringDateTime String representation of sql DateTime (yyyy-MM-dd HH:mm:ss).
     * @return java.util.Date representation of a date
     */
    public static java.util.Date stringDateTimeToJava(String stringDateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate = null;
        
        try {
            parsedDate = dateFormat.parse(stringDateTime);  
            return parsedDate;
        } catch (Exception ex) {
            try {
                return (dateFormat.parse("01/01/1975 01:01:01"));
            } catch (ParseException ex1) {
            }
        }
        
        return parsedDate;
    }
    
    /**
     * Converts Java date to String format.
     * @param date java.util.Date representation of a date
     * @return String representation of Date (yyyy-MM-dd).
     */
    public static String javaDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
        
        try {
            return dateFormat.format(date);
        } catch (Exception ex) {
            return ("01/01/1975");
        }
    }
}
