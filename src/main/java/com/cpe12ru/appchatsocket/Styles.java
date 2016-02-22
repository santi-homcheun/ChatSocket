/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpe12ru.appchatsocket;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author PC-BANK
 */
public class Styles {
    
    public static SimpleAttributeSet setStyleMessageWelcome(JTextPane _jTextPane, String _message) throws BadLocationException{
        
        StyledDocument document = _jTextPane.getStyledDocument();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setForeground(attributeSet, Color.BLACK);
        StyleConstants.setBold(attributeSet, true);
        document.insertString(document.getLength(), _message, attributeSet);
        return attributeSet;
    }
    
    public static SimpleAttributeSet setStyleMessageSend(JTextPane _jTextPane, String _message) throws BadLocationException{
        
        StyledDocument document = _jTextPane.getStyledDocument();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBackground(attributeSet, Color.ORANGE);
        StyleConstants.setBold(attributeSet, true);
        
        if (_message.contains("Successful")) {
            document.insertString(document.getLength(), "\n", attributeSet);
        }
        else{
            document.insertString(document.getLength(), "\nYou say :: "+_message, attributeSet);
        }
        return attributeSet;
    }
    
    public static SimpleAttributeSet setStyleMessageRecieved(JTextPane _jTextPane, String _message) throws BadLocationException{
       
        StyledDocument document = _jTextPane.getStyledDocument(); 
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBackground(attributeSet, Color.GREEN);
        StyleConstants.setBold(attributeSet, true);
        document.insertString(document.getLength(), "\n" +_message, attributeSet);
        return attributeSet;
    }

    
}
