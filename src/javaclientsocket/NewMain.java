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
        SymetricEncryption c = new SymetricEncryption();
        c.createFileKey();
        
        System.out.println("Clé actuelle : "+c.getStringKey());
        
        String cleActuelle = c.getStringKey();
        
        //Reconstruction de la clé
        c.constructKey(cleActuelle);
        
        System.out.println("New Clé : "+c.getStringKey());
        
    }
    
}
