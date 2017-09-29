/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclientsocket;

import java.awt.RenderingHints.Key;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Fabien-portable
 */
public class SymetricEncryption {

    byte[] data;
    byte[] result;
    SecretKey key;

    public SymetricEncryption() {
        this.key = getKey();
    }

    public SecretKey getKey() {
        SecretKey k = null;
        try {
            KeyGenerator kg = KeyGenerator.getInstance("DES");
             k = kg.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    public byte[] crypt(String msg) {
        byte[] crypted = null;
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, (java.security.Key) key);
            crypted = cipher.doFinal(msg.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crypted;
    }

    public byte[] decrypt(byte[] stream) {
        byte[] crypted = null;
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            crypted = cipher.doFinal(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crypted;
    }
    
    public String getStringDecrypt(byte[] stream){
        return new String(this.decrypt(stream));
    }
    
    public String getStringCrypt(String stream){
        return new String(this.crypt(stream));
    }

    public boolean createFile(SecretKey k) throws UnsupportedEncodingException {
        PrintWriter writer;
        try {
            writer = new PrintWriter("C:/Users/Fabien-portable/Desktop/symetric_key.txt");
            //String keyencoded = Base64.getEncoder().encodeToString(k.getEncoded());
            String keyencoded = new String(k.getEncoded(), "Windows-1252");
            writer.write(keyencoded);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(cryptage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (new File("C:/Users/Fabien-portable/Desktop/symetric_key.txt")).exists();
    }

}
