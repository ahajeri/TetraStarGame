/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;

import java.util.List;

/**
 * StarMapComponentIterator provides traversal for StarMapComponent Aggregate.
 * This class acts as Concrete Iterator.
 */
public class StarMapComponentIterator extends TIterator {

    /**
     * Aggregate List.
     */
    List<StarMapComponent> lsmc;

    StarMapComponentIterator(List<StarMapComponent> lsmc) {
        this.lsmc = lsmc;
        this.currentPosition = 0;
    }

    /**
     * Get the size of Aggregate.
     */
    @Override
    public int getSize() {
        return lsmc.size();
    }

    /**
     * Get the next Aggregate Element.
     */
    @Override
    public Object next() {
        return lsmc.get(currentPosition++);
    }

    /**
     * Remove the next Aggregate Element.
     */
    @Override
    public void remove() {
        try {
            lsmc.remove(currentPosition--);
        } catch (UnsupportedOperationException e) {
            System.err.println("Error occurred " + e.getMessage());
            System.exit(1);
        }
    }
}
