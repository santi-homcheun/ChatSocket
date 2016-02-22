/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpe12ru.appchatsocket;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author PC-BANK
 */
public class Dialogs extends JDialog{
    
    public int Dialogs(String _fileName, String _path){
        
        Object[] options = {"Save", "Discard"};
        String fileType = _fileName.substring(_fileName.indexOf(".")+1, _fileName.length());
        int chk = 0;
        ImageIcon image = null;
        
        if (fileType.equals("jpg")||fileType.equals("png")||fileType.equals("gif")||fileType.equals("bmp")) {
         
            image = new ImageIcon(_path);
            Image img = (image.getImage()).getScaledInstance(80, 95, java.awt.Image.SCALE_SMOOTH);
            image = new ImageIcon(img);
            
             chk = JOptionPane.showOptionDialog(null,
                    "Server is trying to transfer file : \"" + _fileName + "\" to you.\nDo you want to save or discard it?",
                    "Save or Discard",
//                    new JLabel("Server3 is trying to transfer file : \"" + fileName + 
//                            "\" to you.\nDo you want to save or discard it?", image, JLabel.LEFT),
//                    "Save or Discard",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    image,
                    options,
                    options[0]);
        }
        else{
            image = new ImageIcon(getClass().getClassLoader().getResource("resource/file-icon.png"));
            Image img = (image.getImage()).getScaledInstance(80, 95, java.awt.Image.SCALE_SMOOTH);
            image = new ImageIcon(img);
            chk = JOptionPane.showOptionDialog(null,
                    "Server is trying to transfer file : \"" + _fileName + "\" to you.\nDo you want to save or discard it?",
                    "Save or Discard",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    image,
                    options,
                    options[0]);
            
        }
        
        return chk;
        
    }
}
