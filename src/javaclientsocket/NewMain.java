/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclientsocket;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import javax.crypto.SecretKey;

/**
 *
 * @author Fabien-portable
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        // TODO code application logic here
        cryptage c = new cryptage();
        c.createFile(c.getKey());
        
        byte[] result = c.crypt("Xavier");
        
        System.out.println(c.getStringCrypt("Xavier"));
        
    }
    
}
