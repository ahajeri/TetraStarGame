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
import com.design.pattern.tetrastar.util.CreateMessageUtility;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StarAtlasComposite extends StarMapComponent {

    /**
     * List of StarMaps.
     */
    List<StarMapComponent> lmaps = new ArrayList<StarMapComponent>();

    /**
     * External Iterator used to traverse several StarMaps in StarAtlas.
     */
    StarMapComponentIterator starMapComponentIterator;

    /**
     * Initialize StarAtlas.
     *
     * @param starComponentID
     * @param loc
     */
    public StarAtlasComposite(int starComponentID, TFaceGrid loc) {
        this.starComponentID = starComponentID;
        this.mapLocation = loc;
        this.numberItems = 0;

    }

    /**
     * Create Iterator for traversal.
     *
     * @param lsmc
     */
    private void createIterator(List<StarMapComponent> lsmc) {
        this.starMapComponentIterator = new StarMapComponentIterator(lsmc);
    }

    /**
     * Get the message.
     *
     * @return
     */
    @Override
    String getBody() {
        return null;
    }

    /**
     * Get the encrypted Symbol.
     *
     * @return
     */
    @Override
    char getEncryptedSymbol() {
        StarMapComponent map;

        encryptionStatus = true;
        createIterator(lmaps);
        starMapComponentIterator.begin();
        if (starMapComponentIterator.hasNext()) {
            map = (StarMapComponent) starMapComponentIterator.next();
            return map.getEncryptedSymbol();
        }

        return '\0';
    }

    /**
     * Encrypt the StarAtlas.
     *
     * @param heroID
     * @param date
     * @param Symbol
     */
    @Override
    public void encrypt(int heroID, Date date, char symbol) {
        StarMapComponent map;

        encryptionStatus = true;
        createIterator(lmaps);
        starMapComponentIterator.begin();
        while (starMapComponentIterator.hasNext()) {
            map = (StarMapComponent) starMapComponentIterator.next();
            map.encrypt(heroID, date, symbol);
        }
    }

    /**
     * To check whether StarAtlas is encrypted by passed heroID or not.
     *
     * @param heroID
     * @return
     */
    @Override
    boolean isEncryptedByMe(int heroID) {
        StarMapComponent map;

        createIterator(lmaps);
        starMapComponentIterator.begin();
        while (starMapComponentIterator.hasNext()) {
            map = (StarMapComponent) starMapComponentIterator.next();
            if (map.isEncryptedByMe(heroID) == true) {
                return true;
            }
        }
        return false;
    }

    /**
     * Decrypt the StarAtlas.
     *
     * @param heroID
     */
    @Override
    public void decrypt(int heroID) {
        StarMapComponent map;

        createIterator(lmaps);
        starMapComponentIterator.begin();
        while (starMapComponentIterator.hasNext()) {
            map = (StarMapComponent) starMapComponentIterator.next();
            map.decrypt(heroID);
        }
    }

    /**
     * Display the StarAtlas Contents.
     */
    @Override
    public void display() {
        StarMapComponent map;

       if (encryptionStatus != true) {
            createIterator(lmaps);
            starMapComponentIterator.begin();
            while (starMapComponentIterator.hasNext()) {
                map = (StarMapComponent) starMapComponentIterator.next();
                map.display();
            }
        } else {
            String message = encryptedDisplay();
            CreateMessageUtility.createMsg(message);
        }
    }

    /**
     * To display the encrypted content.
     *
     * @return
     */
    @Override
    public String encryptedDisplay() {
        String message = null;
        StarMapComponent map;

        createIterator(lmaps);
        starMapComponentIterator.begin();
        map = (StarMapComponent) starMapComponentIterator.next();
        message = map.encryptedDisplay();
        while (starMapComponentIterator.hasNext()) {
            map = (StarMapComponent) starMapComponentIterator.next();
            message += "\n";
            message += "\t";
            message += map.getBody();
           
        }
        message += "\n";
        for (int i = 0; i < 40; ++i) {
            message = message + map.getEncryptedSymbol();
        }

        return message;
    }
    

    /**
     * Set the encryption Algorithm.
     *
     * @param encrypt
     */
    @Override
    public void setEncryptionAlgorithm(EncryptionAlgorithm encrypt) {
        StarMapComponent map;

        createIterator(lmaps);
        starMapComponentIterator.begin();
        while (starMapComponentIterator.hasNext()) {
            map = (StarMapComponent) starMapComponentIterator.next();
            map.setEncryptionAlgorithm(encrypt);
        }
    }

    /**
     * Add new StarMap in StarAtlas.
     *
     * @param starMap
     */
    public void addStarMap(StarMapComponent starMap) {
        lmaps.add(starMap);
        numberItems++;
    }
}
