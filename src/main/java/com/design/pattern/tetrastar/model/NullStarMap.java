/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;

import com.design.pattern.tetrastar.encryption.EncryptionAlgorithm;
import java.util.Date;

/**
 *
 *  @author Akshata, Rachna and Shweta.
 */
public class NullStarMap extends StarMapComponent {

    /**
     * Initializing Null StarMap.
     */
    NullStarMap() {
        starComponentID = 0;
        mapLocation = new TFaceGrid(100, 100);
    }

    /**
     * Do Nothing.
     */
    @Override
    boolean isEncryptedByMe(int heroID) {
        return false;
    }

    /**
     * Do Nothing.
     */
    @Override
    void encrypt(int heroID, Date date, char Symbol) {

    }

    /**
     * Do Nothing.
     */
    @Override
    void decrypt(int heroID) {

    }

    /**
     * Do Nothing.
     */
    @Override
    void display() {

    }

    /**
     * Do Nothing.
     */
    @Override
    void setEncryptionAlgorithm(EncryptionAlgorithm encrypt) {

    }

    /**
     * Do Nothing.
     */
    @Override
    String getBody() {
        return null;
    }

    /**
     * Do Nothing.
     */
    String encryptedDisplay() {
        return null;
    }

    /**
     * Do Nothing.
     */
    @Override
    char getEncryptedSymbol() {
        return '\0';
    }
}
