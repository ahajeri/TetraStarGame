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

 
    List<StarMapComponent> listStarMapComponent;

    StarMapComponentIterator(List<StarMapComponent> lsmc) {
        this.listStarMapComponent = lsmc;
        this.currentPosition = 0;
    }

    @Override
    public int getSize() {
        return listStarMapComponent.size();
    }

    @Override
    public Object next() {
        return listStarMapComponent.get(currentPosition++);
    }

    @Override
    public void remove() {
        try {
            listStarMapComponent.remove(currentPosition--);
        } catch (UnsupportedOperationException e) {
            System.err.println("Error occurred " + e.getMessage());
            System.exit(1);
        }
    }
}
