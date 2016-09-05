/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;

import java.util.List;


/**
 * EncryptingHeroIterator provides traversal for EncryptingHero Aggregate. This
 * class acts as Concrete Iterator.
 *   Iterator Design Pattern
 */
public class EncryptingHeroIterator extends TIterator {

    /**
     * Aggregate List.
     */
    List<EncryptingHero> leh;

    EncryptingHeroIterator(List<EncryptingHero> leh) {
        this.leh = leh;
        this.currentPosition = 0;
    }

    /**
     * Get the size of Aggregate.
     */
    @Override
    public int getSize() {
        return leh.size();
    }

    /**
     * Get the next Aggregate Element.
     */
    @Override
    public Object next() {
        return leh.get(currentPosition++);
    }

    /**
     * Remove the next Aggregate Element.
     */
    @Override
    public void remove() {
        try {
            leh.remove(currentPosition--);
        } catch (UnsupportedOperationException e) {
            System.err.println("Error occurred " + e.getMessage());
            System.exit(1);
        }
    }
}
