/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;
/**
 *  @author Akshata, Rachna and Shweta.
 */

import com.design.pattern.tetrastar.encryption.EncryptionAlgorithm;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public abstract class StarMapComponent implements Cloneable {

 
    int starComponentID;

    int numberItems;

    int restorationCounter;
 
    TFaceGrid mapLocation;

     boolean encryptionStatus;
     
     public void setEncryptionStatus(boolean encryptionStatus)
     {
    	 this.encryptionStatus = encryptionStatus;
     }

    
    //To check whether StarMap is encrypted or not.
 
    public boolean isEncrypted() {
        return encryptionStatus;
    }

    //To support cloning of StarMaps.
    @Override
    public StarMapComponent clone() throws CloneNotSupportedException {
        return (StarMapComponent) super.clone();
    }

   //To check whether StarMap exists at passed Location or not.
    boolean showSignal(TFaceGrid location) {
        if (location.getRow() == mapLocation.getRow() && location.getColumn() == mapLocation.getColumn()) {
            return true;
        }
        return false;
    }

   //Set the location of StarMap.
    public void setLocation(TFaceGrid newLoc) {
        mapLocation = newLoc;
    }

    //To check whether StarMap is encrypted by passed heroID or not.
    abstract boolean isEncryptedByMe(int heroID);

    //Encrypt the Map.
    abstract void encrypt(int heroID, Date date, char Symbol);

    ///Decrypt the Map.
    abstract void decrypt(int heroID);

    //Display the StarMap Contents.
    abstract void display();

    abstract String getBody();

    abstract String encryptedDisplay();

    abstract char getEncryptedSymbol();

    abstract void setEncryptionAlgorithm(EncryptionAlgorithm encrypt);

    abstract void addStarMap(StarMapComponent starMap);
    
    abstract void removeStarMap(StarMapComponent starMap);
    
	public int getRestorationCounter() {
		return restorationCounter;
	}

	public void setRestorationCounter(int restorationCounter) {
		this.restorationCounter = restorationCounter;
	}

}
