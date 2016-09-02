/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;

import com.design.pattern.tetrastar.encryption.EncryptionAlgorithm;
import com.design.pattern.tetrastar.util.CreateMessageUtility;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rachna Gajre <rgajre@scu.edu>
 */
public class StarMap extends StarMapComponent {

    String body;
    EncryptionAlgorithm encryptionAlgo;
    List<EncryptingHero> encryptedHeroes = new ArrayList<EncryptingHero>();
    EncryptingHeroIterator lehIterator;

    /**
     * Initializing StarMap.
     *
     * @param starMapID
     * @param loc
     * @param directions
     */
    public StarMap(int starMapID, TFaceGrid loc, String directions) {
        this.starComponentID = starMapID;
        this.mapLocation = loc;
        this.numberItems = 1;
        this.body = directions;
    }

    /**
     * Create Iterator for traversal.
     *
     * @param leh
     */
    private void createIterator(List<EncryptingHero> leh) {
        lehIterator = new EncryptingHeroIterator(leh);
    }

    /**
     * Get the message.
     *
     * @return
     */
    String getBody() {
        return body;
    }

    /**
     * Encrypt the StarMap.
     *
     * @param heroID
     * @param date
     * @param Symbol
     */
    @Override
    public void encrypt(int heroID, Date date, char symbol) {
        EncryptingHero eh = null;
        EncryptingHero previousHero;
        String strategy = null;
        boolean alreadyEncrypted = false;

        if (encryptionStatus == true) {
            createIterator(encryptedHeroes);
            lehIterator.begin();
            while (lehIterator.hasNext()) {
                previousHero = (EncryptingHero) lehIterator.next();
                if (previousHero.getHeroID() == heroID) {
                    alreadyEncrypted = true;
                    previousHero.updateCounter();
                    break;
                }
            }
            if (alreadyEncrypted == false) {
                eh = new EncryptingHero(heroID);
            }
        } else {
            eh = new EncryptingHero(heroID, date, symbol, 1);

            /* Encrypt the message */
            body = encryptionAlgo.encrypt(body);
            encryptionStatus = true;

        }
        if (eh != null) {
            encryptedHeroes.add(eh);
        }
    }

    /**
     * Decrypt the StarMap.
     *
     * @param heroID
     */
    @Override
    public void decrypt(int heroID) {
        EncryptingHero previousHero;

        createIterator(encryptedHeroes);
        lehIterator.begin();
        while (lehIterator.hasNext()) {
            previousHero = (EncryptingHero) lehIterator.next();
            if (previousHero.getHeroID() == heroID) {
                body = encryptionAlgo.decrypt(body);
                return;
            }
            else {
            	String message = "Hero" + heroID + " cannot decrypt the map, as it is not encrypted by him!";
            	CreateMessageUtility.createMsg(message);
            	return;
            }
        }
    }

    /**
     * To check whether StarMap is encrypted by passed heroID or not.
     *
     * @param heroID
     * @return
     */
    @Override
    boolean isEncryptedByMe(int heroID) {
        EncryptingHero previousHero;

        createIterator(encryptedHeroes);
        lehIterator.begin();
        while (lehIterator.hasNext()) {
            previousHero = (EncryptingHero) lehIterator.next();
            if (previousHero.getHeroID() == heroID) {
                return true;
            }
        }
        return false;
    }

    /**
     * Display the StarMap Contents.
     */
    @Override
     public void display() {
        String message = null;
        if (encryptionStatus != true) {
        	 message = nonEncryptedDisplay();
             /*for (int i = 0; i < 40; ++i) {
                 message = message ;// getEncryptedSymbol();
             }*/
        } else {
            message = encryptedDisplay();
            for (int i = 0; i < 40; ++i) {
                message = message + getEncryptedSymbol();
            }
        }
        CreateMessageUtility.createMsg(message);
    }

    /**
     * Get the encrypted Symbol.
     *
     * @return
     */
    @Override
    char getEncryptedSymbol() {
        EncryptingHero initialHero;

        createIterator(encryptedHeroes);
        lehIterator.begin();
        initialHero = (EncryptingHero) lehIterator.next();

        return initialHero.getSymbol();
    }

    /**
     * To display the encrypted content.
     *
     * @return
     */
    @Override
    String encryptedDisplay() {

        String message = "";
        createIterator(encryptedHeroes);
        lehIterator.begin();
        EncryptingHero initialHero = (EncryptingHero) lehIterator.next();
        for (int i = 0; i < 40; ++i) {
            message = message + initialHero.getSymbol();
        }

        message = message + "\n\t\t HeroID: " + initialHero.getHeroID() + " Date: " + initialHero.getDate() + "\n";
        while (lehIterator.hasNext()) {
            EncryptingHero newHero = (EncryptingHero) lehIterator.next();
            message = message + "\n\t\t HeroID: " + newHero.getHeroID() + "\n";
        }
        message = message + "\t\t" + body + "\n";

        return message;
    }

    String nonEncryptedDisplay() {

        String message = "";
       // createIterator(encryptedHeroes);
        //lehIterator.begin();
        //EncryptingHero initialHero = (EncryptingHero) lehIterator.next();
        /*for (int i = 0; i < 40; ++i) {
            message = message + "*";//initialHero.getSymbol();
        }*/

        message = message + "\n\t\t MapID: " + starComponentID + "\n";
        /*while (lehIterator.hasNext()) {
            EncryptingHero newHero = (EncryptingHero) lehIterator.next();
            message = message + "\n\t\tID: " + newHero.getHeroID() + "\n";
        }*/
        message = message + "\t\t" + body + "\n";

        return message;
    }

    
    /**
     * Set the encryption Algorithm.
     *
     * @param encryptAlgo
     * @param encrypt
     */
    @Override
    public void setEncryptionAlgorithm(EncryptionAlgorithm encryptAlgo) {
        this.encryptionAlgo = encryptAlgo;
    }

}
