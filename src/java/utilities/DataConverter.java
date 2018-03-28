/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author 742227
 */
public class DataConverter {

    public static java.sql.Date javaDateToSQL(java.util.Date javaDate) {
        java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
        return sqlDate;
    }

    public static java.util.Date sqlDateToJava(java.sql.Date sqlDate) {
            java.util.Date javaDate = new java.util.Date(sqlDate.getTime());
        return javaDate;
    }


}
