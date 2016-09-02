/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;

import com.design.pattern.tetrastar.encryption.EncryptionAlgorithm;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Rachna Gajre <rgajre@scu.edu>
 */
public abstract class StarMapComponent implements Cloneable {

    /**
     * Id of StarMap.
     */
    int starComponentID;

    /**
     * Number of maps in atlas.
     */
    int numberItems;

    /**
     * Grid location of StarMap.
     */
    TFaceGrid mapLocation;

    /**
     * Encryption Status.
     */
     boolean encryptionStatus;
     
     public void setEncryptionStatus(boolean encryptionStatus)
     {
    	 this.encryptionStatus = encryptionStatus;
     }

    /**
     * To check whether StarMap is encrypted or not.
     *
     * @return
     */
    public boolean isEncrypted() {
        return encryptionStatus;
    }

    /**
     * To support cloning of StarMaps.
     *
     * @return
     */
    @Override
    public StarMapComponent clone() throws CloneNotSupportedException {
        return (StarMapComponent) super.clone();
    }

    /**
     * To check whether StarMap exists at passed Location or not.
     *
     * @param location
     * @return
     */
    boolean showSignal(TFaceGrid location) {
        if (location.getRow() == mapLocation.getRow() && location.getColumn() == mapLocation.getColumn()) {
            return true;
        }
        return false;
    }

    /**
     * Set the location of StarMap.
     *
     * @param newLoc
     */
    public void setLocation(TFaceGrid newLoc) {
        mapLocation = newLoc;
    }

    /**
     * To check whether StarMap is encrypted by passed heroID or not.
     *
     * @param heroID
     * @return
     */
    abstract boolean isEncryptedByMe(int heroID);

    /**
     * Encrypt the Map.
     *
     * @param heroID
     * @param date
     * @param Symbol
     */
    abstract void encrypt(int heroID, Date date, char Symbol);

    /**
     * Decrypt the Map.
     *
     * @param heroID
     */
    abstract void decrypt(int heroID);

    /**
     * Display the StarMap Contents.
     */
    abstract void display();

    /**
     * Get the message.
     *
     * @return
     */
    abstract String getBody();

    /**
     * To display the encrypted content.
     *
     * @return
     */
    abstract String encryptedDisplay();

    /**
     * Get the encrypted Symbol.
     *
     * @return
     */
    abstract char getEncryptedSymbol();

    /**
     * Set the encryption Algorithm.
     *
     * @param encrypt
     */
    abstract void setEncryptionAlgorithm(EncryptionAlgorithm encrypt);

}
