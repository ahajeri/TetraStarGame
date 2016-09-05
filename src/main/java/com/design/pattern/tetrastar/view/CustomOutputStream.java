/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.view;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;

/**
 *   @author Akshata, Rachna and  Shweta. 
 *   Class to bind Standart Output to a textarea so that anything output on System.out will be 
 *   printed in console logger's JTextArea
 */
public class CustomOutputStream extends OutputStream {
    private JTextArea textArea;
     
    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }
     
    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
