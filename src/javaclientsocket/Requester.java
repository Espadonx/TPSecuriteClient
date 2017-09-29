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
import java.util.Scanner;

public class Requester {

    public static void main(String args[]) throws IOException {

        //Récupération des entrées utilisateurs
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisissez l'adresse IP du serveur :");
        //String address = "192.168.43.209";
        String address = sc.nextLine();
        
        System.out.println("Saisissez maintenant le PORT :");
        int port = sc.nextInt();
        
        
        Socket s1 = null;
        String line = null;
        BufferedReader br = null;
        BufferedReader is = null;
        PrintWriter os = null;

        try {
            s1 = new Socket(address, port); // You can use static final constant PORT_NUM
            br = new BufferedReader(new InputStreamReader(System.in));
            is = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            os = new PrintWriter(s1.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.print("IO Exception");
        }

        System.out.println("Client Address : " + address);
        System.out.println("Enter Data to echo Server ( Enter QUIT to end):");

        String response = null;
        try {
            line = br.readLine();
            while (line.compareTo("QUIT") != 0) {
                os.println(line);
                if(s1.isConnected()){
                    System.out.println(s1.getRemoteSocketAddress());
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
            s1.close();
            System.out.println("Connection Closed");

        }

    }
}
