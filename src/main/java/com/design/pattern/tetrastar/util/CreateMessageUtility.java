package com.design.pattern.tetrastar.util;
/**
 *   @author Akshata, Rachna and  Shweta. 
 */

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class CreateMessageUtility {
    
    public static void createMsg(String msg) {
        //String message = "<html><font name='sansserif' size='4'/>" + msg + "</html>";
    	String message = msg;
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, null, null);

        JDialog dialog = optionPane.createDialog(null, "Scenario");
        dialog.setLocation(1050, 430);
        dialog.setVisible(true);
    }
    
}
