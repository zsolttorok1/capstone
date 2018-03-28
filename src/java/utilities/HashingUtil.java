/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import keccak.FIPS202;

/**
 *
 * @author 725899
 */
public class HashingUtil {
    
    public static void main (String[] args) {
        
        System.out.println("4F7CAE26D8C6340784AF911B39E84E92A44F6E0145A4AA34B017EA30CBA4FF0CA844E3534B56F3E2614A25278D54232FEB310C2DABE102D0D1A5F4814414C2D9");
        System.out.println("b0ef2e0564cff7ec65dd14d35ecff09e");
        
        System.out.println(HashingUtil.hashByKeccak512("q", ""));
    }
    
    public static String hash(String uuid) {
        return HashingUtil.hashByKeccak512(uuid);
    }
    
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[16];
        random.nextBytes(bytes);
        
        String salt = "";
        
        for (byte b : bytes) {
            salt += String.format("%02x", b);
        }
        
        return salt;
    }
    
    public static String hashByKeccak512(String string, String salt) {
        string = string + salt;

        return hashByKeccak512(string);
    }
    
    public static String hashByKeccak512(String string) {
        byte[] stringBytes = string.getBytes();
        byte[] hashBytes = FIPS202.HashFunction.SHA3_512.apply(stringBytes);
        String hashHex = FIPS202.hexFromBytes(hashBytes);
        
        return hashHex;
    }
    
    public static String hashBySHA512(String string, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(string.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            string = sb.toString();
        } 
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HashingUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return string;
    }
}