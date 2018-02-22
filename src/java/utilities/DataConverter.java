/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 *
 * @author 742227
 */
public class DataConverter {

    public static java.sql.Date javaDate(java.util.Date javaDate) {
        java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
        return sqlDate;
    }

    public static java.util.Date sqlDate(java.sql.Date sqlDate) {
            java.util.Date javaDate = new java.util.Date(sqlDate.getTime());
        return javaDate;
    }


}
