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
 * @author Akshata, Rachna and Shweta.
 */
public class StarMap extends StarMapComponent {

    String body;
    EncryptionAlgorithm encryptionAlgo;
    List<EncryptingHero> encHero = new ArrayList<EncryptingHero>();
    EncryptingHeroIterator encryptingHeroIterator;

 
    public StarMap(int starMapID, TFaceGrid loc, String directions) {
        this.starComponentID = starMapID;
        this.mapLocation = loc;
        this.noOfItems = 1;
        this.body = directions;
    }

    private void createIterator(List<EncryptingHero> listEncryptingHero) {
        encryptingHeroIterator = new EncryptingHeroIterator(listEncryptingHero);
    }

    String getBody() {
        return body;
    }

    @Override
    public void encrypt(int heroID, Date date, char symbol) {
        EncryptingHero encryptingHero = null;
        EncryptingHero previousHero;
        boolean alreadyEncrypted = false;

        if (encryptionStatus == true) {
            createIterator(encHero);
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
            encHero.add(encryptingHero);
        }
    }

    //Decrypt the StarMap.
    @Override
    public void decrypt(int heroID) {
        EncryptingHero previousHero;

        createIterator(encHero);
        encryptingHeroIterator.begin();
        while (encryptingHeroIterator.hasNext()) {
            previousHero = (EncryptingHero) encryptingHeroIterator.next();
            if (previousHero.getHeroID() == heroID) {
                body = encryptionAlgo.decrypt(body);
                return;
            } else {
                String message = "Hero" + heroID + " cannot decrypt the map, as it is not encrypted by him!";
                CreateMessageUtility.createMsg(message);
                return;
            }
        }
    }

    @Override
    boolean ckhEncryptedBy(int heroID) {
        EncryptingHero previousHero;

        createIterator(encHero);
        encryptingHeroIterator.begin();
        while (encryptingHeroIterator.hasNext()) {
            previousHero = (EncryptingHero) encryptingHeroIterator.next();
            if (previousHero.getHeroID() == heroID) {
                return true;
            }
        }
        return false;
    }

 
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
    }


    @Override
    char getEncryptedSymbol() {
        EncryptingHero initialHero;

        createIterator(encHero);
        encryptingHeroIterator.begin();
        initialHero = (EncryptingHero) encryptingHeroIterator.next();

        return initialHero.getSymbol();
    }


    @Override
    String encryptedDisplay() {

        String message = "";
        createIterator(encHero);
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
