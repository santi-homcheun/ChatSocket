/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpe12ru.appchatsocket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author PC-BANK
 */
public class fileManager {

    public static void sendFile(File _file, OutputStream _outputStream) {
        
        try {
            byte[] bytesBuffer = new byte[10240];
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(_outputStream, 10240);
            FileInputStream fileInputStream = new FileInputStream(_file);
            int len = 0;
            int countBytes = 0;
            while ((len = fileInputStream.read(bytesBuffer, 0, 10240)) != -1) {
                bufferedOutputStream.write(bytesBuffer, 0, len);
                bufferedOutputStream.flush();
                countBytes = countBytes + len;
//                System.out.println(len + " " + countBytes);
            }
            System.out.println("upload file " + _file.getName() + " Successfull " + countBytes);
        } catch (Exception ee) {
        }
    }

    public static void recieveFile(File _file, InputStream _inputStream, int _fileRecieved) throws FileNotFoundException, IOException {
        byte[] byteBuff = new byte[10240];
        int len = 0;
        int countBytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(_file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(_inputStream, 10240);
        while (true) {
            len = bufferedInputStream.read(byteBuff, 0, 10240);
            fileOutputStream.write(byteBuff, 0, len);
            fileOutputStream.flush();
            countBytes = countBytes + len;
            if (_fileRecieved == countBytes) {
                fileOutputStream.close();
                break;
            }

        }
        System.out.println(_file.getAbsolutePath() + " was downloaded " + countBytes);
    }

    public static String findFileName(String _path) {
        String fileName = "";
        File file = new File(_path);
        fileName = (file.getName()).substring(0, (file.getName()).indexOf("."));
        return fileName;
    }

    public static String findFileType(String _path) {
        String fileType = "";
        String[] seperate = _path.split("/");
        fileType = seperate[seperate.length - 1].substring(seperate[seperate.length - 1].indexOf(".") + 1);
        return fileType;
    }

    public static void copyFileAndDelete(File _fileCopy, File _fileDestination) {
        
        try {
            
            InputStream is = new FileInputStream(_fileCopy);
            OutputStream os = new FileOutputStream(_fileDestination);
            byte[] buffer = new byte[4048];
            int len = 0;
            while ((len = is.read(buffer)) > 0){
    	    	os.write(buffer, 0, len);
    	    }
            is.close();
            os.close();
            _fileCopy.delete();
        } catch(Exception e){
        } 
    }
}
