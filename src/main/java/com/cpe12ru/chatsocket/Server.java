/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpe12ru.chatsocket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author PC-BANK
 */
public class Server {
    
    public static void main(String[] args) throws IOException {
        
        ServerSocket welcomeSocket = new ServerSocket(9090);
        
        System.out.println("Server is waiting. . . . ");
        Socket connectionSocket = welcomeSocket.accept();
        System.out.println("Client connected with IP " + 
                    connectionSocket
                    .getLocalAddress()
                    .getHostAddress());
            
        if (connectionSocket.isConnected()) {
            System.out.println("Clinet connected\n");
        }
 
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream stream_out = new DataOutputStream(connectionSocket.getOutputStream());
        DataInputStream  stream_in = new DataInputStream(connectionSocket.getInputStream());
        
        try {

            String str1 = "",str2 = "";
           
            while (!str1.equals("stop")) {
                str1 = stream_in.readUTF();
                System.out.println("Client says : "+str1);
                str2 = bufferReader.readLine();
                stream_out.writeUTF(str2);
                stream_out.flush();
            } 
        } catch (IOException e) {
            connectionSocket.close();
            welcomeSocket.close();
            System.out.println("IOException : "+e);
            
        }    
    }
}
