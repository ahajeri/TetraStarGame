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

public class StarMap extends StarMapComponent {

    String body;
    EncryptionAlgorithm encryptionAlgo;
    List<EncryptingHero> encryptedHeroes = new ArrayList<EncryptingHero>();
    EncryptingHeroIterator encryptingHeroIterator;

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
    private void createIterator(List<EncryptingHero> listEncryptingHero) {
        encryptingHeroIterator = new EncryptingHeroIterator(listEncryptingHero);
    }

    /**
     * Get the message.
     *
     * @return
     */
    String getBody() {
        return body;
    }


    @Override
    public void encrypt(int heroID, Date date, char symbol) {
        EncryptingHero encryptingHero = null;
        EncryptingHero previousHero;
        String strategy = null;
        boolean alreadyEncrypted = false;

        if (encryptionStatus == true) {
            createIterator(encryptedHeroes);
            encryptingHeroIterator.begin();
            while (encryptingHeroIterator.hasNext()) {
                previousHero = (EncryptingHero) encryptingHeroIterator.next();
                if (previousHero.getHeroID() == heroID) {
                    alreadyEncrypted = true;
                    previousHero.updateCounter();
                    break;
                }
            }
            if (alreadyEncrypted == false) {
                encryptingHero = new EncryptingHero(heroID);
            }
        } else {
            encryptingHero = new EncryptingHero(heroID, date, symbol, 1);

            /* Encrypt the message */
            body = encryptionAlgo.encrypt(body);
            encryptionStatus = true;

        }
        if (encryptingHero != null) {
            encryptedHeroes.add(encryptingHero);
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
        encryptingHeroIterator.begin();
        while (encryptingHeroIterator.hasNext()) {
            previousHero = (EncryptingHero) encryptingHeroIterator.next();
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
        encryptingHeroIterator.begin();
        while (encryptingHeroIterator.hasNext()) {
            previousHero = (EncryptingHero) encryptingHeroIterator.next();
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
        } else {
            message = encryptedDisplay();
            for (int i = 0; i < 50; ++i) {
                message = message + getEncryptedSymbol();
            }
        }
        CreateMessageUtility.createMsg(message);
        //System.out.println(message);
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
        encryptingHeroIterator.begin();
        initialHero = (EncryptingHero) encryptingHeroIterator.next();

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
        encryptingHeroIterator.begin();
        EncryptingHero initialHero = (EncryptingHero) encryptingHeroIterator.next();
        for (int i = 0; i < 50; ++i) {
            message = message + initialHero.getSymbol();
        }

        message = message + "\n\t\t HeroID: " + initialHero.getHeroID() + " Date: " + initialHero.getDate() + "\n";
        while (encryptingHeroIterator.hasNext()) {
            EncryptingHero newHero = (EncryptingHero) encryptingHeroIterator.next();
            message = message + "\n\t\t HeroID: " + newHero.getHeroID() + "\n";
        }
        message = message + "\t\t" + body + "\n";
        return message;
    }

    String nonEncryptedDisplay() {

        String message = "";
        message = message + "\n MapID: " + starComponentID + " " + body + "\n";
        System.out.println(message);
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

	@Override
	void addStarMap(StarMapComponent starMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void removeStarMap(StarMapComponent starMap) {
		// TODO Auto-generated method stub
		
	}

}
