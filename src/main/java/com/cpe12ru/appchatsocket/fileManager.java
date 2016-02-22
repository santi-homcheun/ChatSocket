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

    public static void sendFile(File _file, OutputStream _OutputStream) {

        try {

            byte[] buffer = new byte[10240];
            BufferedOutputStream bos = new BufferedOutputStream(_OutputStream, 10240);
            FileInputStream fis = new FileInputStream(_file);
            int len = 0;
            int countByte = 0;
            while ((len = fis.read(buffer, 0, 10240)) != -1) {
                bos.write(buffer, 0, len);
                bos.flush();
                countByte += len;
                System.out.println(len + " " + countByte);
            }
            System.out.println("upload file " + _file.getName() + " Successful " + countByte);
        } catch (Exception ex) {
        }
    }

    public static void recieveFile(File _file, InputStream _InputStream, int _fileRecieved) throws FileNotFoundException, IOException {

        byte[] buffer = new byte[10240];
        int len = 0;
        int countByte = 0;
        FileOutputStream fos = new FileOutputStream(_file);
        BufferedInputStream bis = new BufferedInputStream(_InputStream, 10240);

        while (true) {
            len = bis.read(buffer, 0, 10240);
            System.out.println(len);
            fos.write(buffer, 0, len);
            fos.flush();
            countByte += len;
            System.out.println(countByte);
            if (_fileRecieved == countByte) {
                fos.close();
                break;
            }
        }
        System.out.println(_file.getAbsoluteFile() + " was downloaded " + countByte);
    }

    public static String findFileName(String _path) {
        String fileName = "";
        File file = new File(_path);
        fileName = (file.getName()).substring(0, (file.getName()).indexOf("."));
        return fileName;
    }

    public static String findFileType(String _path) {
        String fileType = "";
        String[] seperate = _path.split("\"");
        fileType = seperate[seperate.length - 1].substring(seperate[seperate.length - 1].indexOf(".") + 1);
        return fileType;
    }

    public static void copyFileAndDelete(File _fileCopy, File _fileDestination) {

        try {
            InputStream is = new FileInputStream(_fileCopy);
            OutputStream os = new FileOutputStream(_fileDestination);
            byte[] buffer = new byte[4096];
            int len = 0;
            while ((len = is.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            is.close();
            os.close();
            _fileCopy.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
