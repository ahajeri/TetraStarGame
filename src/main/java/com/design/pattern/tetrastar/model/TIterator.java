/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;

import java.util.Iterator;

/**
 *
 * @author Akshata, Rachna and Shweta.
 */
public abstract class TIterator implements Iterator<Object> {

    /**
     * Tracks the current Position.
     */
    int currentPosition;

    /**
     * Begin traversal.
     */
    public void begin() {
        currentPosition = 0;
    }

    /**
     * End traversal.
     */
    public void end() {
        int size = getSize();
        currentPosition = --size;
    }

    /**
     * To Check whether next element exists or not.
     */
    public boolean hasNext() {
        int size = getSize();
        if (currentPosition < size) {
            return true;
        }
        return false;
    }

    /**
     * Get the size of Aggregate.
     */
    abstract int getSize();

    /**
     * Get the next Aggregate Element.
     */
    @Override
    public abstract Object next();

    /**
     * Remove the next Aggregate Element.
     */
    @Override
    public abstract void remove();

}
