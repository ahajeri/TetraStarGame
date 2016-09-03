/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;
/**
 * @author Akshata, Rachna and Shweta.
 */

import java.util.Date;

public class EncryptingHero {

    private int heroID;
    private Date date;
    private char symbol;
    private int restorationCounter;

    /**
     * Initialize first EncryptingHero.
     *
     * @param heroID
     * @param date
     * @param symbol
     * @param restorationCounter
     */
    public EncryptingHero(int heroID, Date date, char symbol, int restorationCounter) {
        this.heroID = heroID;
        this.date = date;
        this.symbol = symbol;
        this.restorationCounter = restorationCounter;
    }

    /**
     * Adding other hero's.
     *
     * @param heroID
     */
    public EncryptingHero(int heroID) {
        this.heroID = heroID;
        this.date = null;
        this.symbol = '\0';
        this.restorationCounter = 0;
    }

    /**
     * Updating the restoration Counter.
     */
    public void updateCounter() {
        restorationCounter++;
    }

    public int getHeroID() {
        return heroID;
    }

    public void setHeroID(int heroID) {
        this.heroID = heroID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getRestorationCounter() {
        return restorationCounter;
    }

    public void setRestorationCounter(int restorationCounter) {
        this.restorationCounter = restorationCounter;
    }

    
}
