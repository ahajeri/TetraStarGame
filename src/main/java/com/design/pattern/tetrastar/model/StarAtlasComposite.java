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
 * @author Akshata, Rachna and Shweta. Composite Design Pattern
 */
public class StarAtlasComposite extends StarMapComponent {


    List<StarMapComponent> listStarMapComponent = new ArrayList<StarMapComponent>();
    StarMapComponentIterator starMapComponentIterator;

    public StarAtlasComposite(int starComponentID, TFaceGrid loc) {
        this.starComponentID = starComponentID;
        this.mapLocation = loc;
        this.noOfItems = 0;

    }

    /**Create Iterator for traversal.*/
    private void createIterator(List<StarMapComponent> listStarMapComponent) {
        this.starMapComponentIterator = new StarMapComponentIterator(listStarMapComponent);
    }

    @Override
    String getBody() {
        return null;
    }

    @Override
    char getEncryptedSymbol() {
        StarMapComponent map;

        encryptionStatus = true;
        createIterator(listStarMapComponent);
        starMapComponentIterator.begin();
        if (starMapComponentIterator.hasNext()) {
            map = (StarMapComponent) starMapComponentIterator.next();
            return map.getEncryptedSymbol();
        }

        return '\0';
    }

    //Encrypt the StarAtlas.
    @Override
    public void encrypt(int heroID, Date date, char symbol) {
        StarMapComponent map;

        encryptionStatus = true;
        createIterator(listStarMapComponent);
        starMapComponentIterator.begin();
        while (starMapComponentIterator.hasNext()) {
            map = (StarMapComponent) starMapComponentIterator.next();
            map.encrypt(heroID, date, symbol);
        }
    }


    @Override
    boolean ckhEncryptedBy(int heroID) {
        StarMapComponent map;

        createIterator(listStarMapComponent);
        starMapComponentIterator.begin();
        while (starMapComponentIterator.hasNext()) {
            map = (StarMapComponent) starMapComponentIterator.next();
            if (map.ckhEncryptedBy(heroID) == true) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void decrypt(int heroID) {
        StarMapComponent map;

        createIterator(listStarMapComponent);
        starMapComponentIterator.begin();
        while (starMapComponentIterator.hasNext()) {
            map = (StarMapComponent) starMapComponentIterator.next();
            map.decrypt(heroID);
        }
    }


    @Override
    public String encryptedDisplay() {
        String message = null;
        StarMapComponent map;

        createIterator(listStarMapComponent);
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
        for (int i = 0; i < 50; ++i) {
            message = message + map.getEncryptedSymbol();
        }
        return message;
    }

    @Override
    public void display() {
        StarMapComponent mapComponent;

        if (encryptionStatus != true) {
            createIterator(listStarMapComponent);
            starMapComponentIterator.begin();
            while (starMapComponentIterator.hasNext()) {
                mapComponent = (StarMapComponent) starMapComponentIterator.next();
                mapComponent.display();
            }
        } else {
            String message = encryptedDisplay();
            CreateMessageUtility.createMsg(message);
        }
    }
    @Override
    public void setEncryptionAlgorithm(EncryptionAlgorithm encrypt) {
        StarMapComponent map;
        createIterator(listStarMapComponent);
        starMapComponentIterator.begin();
        while (starMapComponentIterator.hasNext()) {
            map = (StarMapComponent) starMapComponentIterator.next();
            map.setEncryptionAlgorithm(encrypt);
        }
    }

    @Override
    public void addStarMap(StarMapComponent starMap) {
        listStarMapComponent.add(starMap);
        noOfItems++;
    }

    @Override
    void removeStarMap(StarMapComponent starMap) {
		// TODO Auto-generated method stub

    }
}
