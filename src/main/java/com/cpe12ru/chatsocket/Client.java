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
import java.net.Socket;

/**
 *
 * @author PC-BANK
 */
public class Client {

    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("127.0.0.1", 9090);
        DataInputStream stream_in = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream stream_out = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));

        try {

            String str1 = "", str2 = "";
            while (!str1.equals("stop")) {
                str1 = bufferReader.readLine();
                stream_out.writeUTF(str1);
                stream_out.flush();
                str2 = stream_in.readUTF();
                System.out.println("Server says : " + str2);
            }
        } catch (IOException e) {
            stream_out.close();
            clientSocket.close();
            System.out.println("IOException : " +e);
        }
    }
}
