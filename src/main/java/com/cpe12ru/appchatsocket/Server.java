/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpe12ru.appchatsocket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;

/**
 *
 * @author PC-BANK
 */
public class Server extends javax.swing.JFrame {

    /**
     * Creates new form Server
     */
    static ServerSocket welcomeSocket;
    static Socket connectionSocket;

    public Server() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        sendButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        browseButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jTextPane1);

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Server ");

        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sendButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(browseButton))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(25, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(browseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        // TODO add your handling code here:

        String messageOut = jTextField1.getText();
        PrintWriter printWriter = null;
        OutputStream outputStream = null;
        File file = new File(messageOut);

        if (file.isFile()) {
            try {
                outputStream = connectionSocket.getOutputStream();
                printWriter = new PrintWriter(connectionSocket.getOutputStream(), true);
                if (file.exists()) {
                    printWriter.println("fine#" + file.length() + "#" + file.getName());
                    fileManager.sendFile(file, outputStream);
                    Styles.setStyleMessageSend(jTextPane1, "upload successful...");
                } else {
                    System.out.println("File does not exist!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BadLocationException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                printWriter = new PrintWriter(connectionSocket.getOutputStream(), true);
                printWriter.println("Server says : " + messageOut);
                Styles.setStyleMessageSend(jTextPane1, messageOut);
                jTextField1.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_sendButtonActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser jFileChooser = new JFileChooser();

        int fileChooser = jFileChooser.showDialog(null, "Choose file");

        if (fileChooser == JFileChooser.APPROVE_OPTION) {
            jTextField1.setText(jFileChooser.getSelectedFile().toString());
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Server().setVisible(true);
            }
        });

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Look and feel not set");
        }

        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            System.out.println("Debug : "+serverSocket.getLocalPort());
            connectionSocket = serverSocket.accept();
            Styles.setStyleMessageWelcome(jTextPane1, "Server is ready to connetions...");
            Styles.setStyleMessageWelcome(jTextPane1, "\nConnected with Client IP " + connectionSocket.getInetAddress().getHostAddress());
            try {
                DataInputStream dataInputStream = null;
                DataOutputStream dataOutputStream = null;
                BufferedReader bufferedReader = null;
                PrintWriter printWriter = null;
                String messageIn = "";
                String messageOut = "";
                InputStream inputStream = null;
                OutputStream outputStream = null;

                do {
                    bufferedReader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                    messageIn = bufferedReader.readLine();
                    inputStream = connectionSocket.getInputStream();

                    if (!messageIn.contains("fine#")) {
                        Styles.setStyleMessageRecieved(jTextPane1, messageIn);
                    } else {
                        System.out.println("Debug check file!!!");
                        String[] splitMsg = messageIn.split("#");
                        System.out.println(splitMsg[0] + " " + splitMsg[1] + " " + splitMsg[2]);
                        if (splitMsg[0].equals("fine")) {
                            String downloadPath = System.getProperty("user.home") + "\\Downloads\\server\\";
                            File dir = new File(downloadPath);
                            if (!dir.exists()) {
                                try {
                                    System.out.println("Creating... directory " + downloadPath);
                                    dir.mkdir();
                                    System.out.println("The directory created");
                                } catch (SecurityException securityException) {
                                    System.out.println("SecurityException occure!!!");
                                    securityException.printStackTrace();
                                }
                            }

                            File file = new File(downloadPath + splitMsg[2]);
                            fileManager.recieveFile(file, inputStream, Integer.parseInt(splitMsg[1]));
                            System.out.println("Download file successful");
                            Dialogs dialogs = new Dialogs();
                            int keepOrDiscard = dialogs.Dialogs(splitMsg[2], file.getAbsoluteFile().toString());

                            if (keepOrDiscard == 0) {
                                JFileChooser jFileChooser = new JFileChooser();
                                jFileChooser.setSelectedFile(file);
                                int n = jFileChooser.showSaveDialog(jTextPane1);
                                if (n == JFileChooser.APPROVE_OPTION) {
                                    if ((jFileChooser.getSelectedFile().toString()).equals(file.getAbsoluteFile().toString())) {
                                        Styles.setStyleMessageRecieved(jTextPane1, "Download Completed.");
                                        continue;
                                    } else {
                                        File fileDestination = new File(jFileChooser.getSelectedFile().toString());
                                        fileManager.copyFileAndDelete(file, fileDestination);
                                        System.out.println("Save new file successful!");
                                        Styles.setStyleMessageRecieved(jTextPane1, "Download Completed.");
                                    }
                                }
                            } else {
                                file.delete();
                                dialogs.dispose();
                            }
                        }
                    }
                } while (!messageIn.equals("bye"));
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                try {
                    connectionSocket.close();
                    System.exit(0);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton browseButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextField jTextField1;
    private static javax.swing.JTextPane jTextPane1;
    private static javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables
}
