/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclientsocket;

/**
 *
 * @author Fabien-portable
 */
import java.io.*;
import java.net.*;
// A simple Client Server Protocol .. Client for Echo Server

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

public class Requester {

    public static void main(String args[]) throws IOException, Exception {

        //Récupération des entrées utilisateurs
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisissez l'adresse IP du serveur :");
        //String address = "192.168.43.209";
        String address = sc.nextLine();

        System.out.println("Saisissez maintenant le PORT :");
        int port = sc.nextInt();

        Socket socket = null;
        String lineCrypted = null;
        String line = null;
        BufferedReader br = null;
        BufferedReader is = null;
        PrintWriter os = null;

        try {
            socket = new Socket(address, port); // You can use static final constant PORT_NUM
            br = new BufferedReader(new InputStreamReader(System.in));
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.print("IO Exception");
        }

        System.out.println("Server Address : " + address);
        System.out.println("Enter Data to echo Server ( Enter QUIT to end):");

        //On créé la clé SYMETRIQUE
        //On établit la connexion avant de laisser le libre arbitre à l'utilisateur
        AssymetricEncryption ae = new AssymetricEncryption("pub_serveur.txt", "priv_client.txt");
        String messageToEncrypt = "client";
        
        //On génère la clé DH partagée avec le serveur
        DHEncryption dhe = new DHEncryption();
        String keyShared = Base64.getEncoder().encodeToString(dhe.generateCommonSecretKey(ae.getGlobal_privateKey(), ae.getGlobal_publicKey()));
        System.out.println("key : "+keyShared);
        

        //os.println(messageChiffre);
        os.flush();
        System.out.println("RéponseCrypted : " + is.readLine());
        //Attente de la réponse du serveur

        String response = null;
        try {
            lineCrypted = br.readLine();
            
            //On décripte le message
            line = DHEncryption.encryptMessage(keyShared.getBytes(), lineCrypted);
            while (line.compareTo("QUIT") != 0) {
                
                os.println(line);
                if (socket.isConnected()) {
                    System.out.println(socket.getRemoteSocketAddress());
                }
                os.flush();
                response = is.readLine();
                System.out.println("Server Response : " + response);
                line = br.readLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Socket read Error");
        } finally {

            is.close();
            os.close();
            br.close();
            socket.close();
            System.out.println("Connection Closed");

        }

    }
}
