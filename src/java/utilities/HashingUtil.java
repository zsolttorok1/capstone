package utilities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import keccak.FIPS202;

/**
 * All the SHA3 Keccak hashing algorithm calling methods.
 * @author 725899
 */
public class HashingUtil {
    
    /**
     * for testing/debugging purposes.
     * @param args 
     */
    public static void main (String[] args) {        
//        System.out.println(HashingUtil.hashByKeccak512("a", "33434323423423423434"));
    }
    
    /**
     * Gets the hashed value of the provided UUID, using Keccak512 algorithm
     * @param uuid the UUID about to get hashed
     * @return the hashed UUID in 512bit long String format 
     */
    public static String hash(String uuid) {
        return HashingUtil.hashByKeccak512(uuid);
    }
    
    /**
     * Generates the next securely random 128bit long salt.
     * @return Hexadecimal String representation of a 128bit salt.
     */
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
    
    /**
     * Gets the hashed value of the provided string, concatenated with the provided salt, using Keccak512 algorithm
     * @param string the string about to get hashed
     * @param salt the salt to get concatenated with the UUID
     * @return the 512bit long hashed string 
     */
    public static String hashByKeccak512(String string, String salt) {
        string = string + salt;

        return hashByKeccak512(string);
    }
    
    /**
     * Gets the hashed value of the provided string, using Keccak512 algorithm
     * @param string the string about to get hashed
     * @return the 512bit long hashed string 
     */
    public static String hashByKeccak512(String string) {
        byte[] stringBytes = string.getBytes();
        byte[] hashBytes = FIPS202.HashFunction.SHA3_512.apply(stringBytes);
        String hashHex = FIPS202.hexFromBytes(hashBytes);
        
        return hashHex;
    }
    
    /**
     * Gets the hashed value of the provided string, concatenated with the provided salt, using SHA512 algorithm
     * @param string the string about to get hashed
     * @param salt the salt to get concatenated with the UUID
     * @return the 512bit long hashed string 
     */
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