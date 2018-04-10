/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 742227
 */
public class DataConverter {

    public static java.sql.Date javaDateToSQL(java.util.Date javaDate) {
        java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
        return sqlDate;
    }
    
    public static String javaDateToSQLDateTime(java.util.Date javaDate) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        return sdf.format(javaDate);
    }
    
    public static java.util.Date sqlDateToJava(java.sql.Date sqlDate) {
        java.util.Date javaDate = new java.util.Date(sqlDate.getTime());
    return javaDate;
    }
    
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
