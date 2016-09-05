package com.design.pattern.tetrastar.model;
import com.design.pattern.tetrastar.enums.Direction;
import com.design.pattern.tetrastar.enums.EncryptionStrategy;
import com.design.pattern.tetrastar.enums.PeopleType;
import java.util.Observer;

import javax.swing.*;

/**
 *   @author Akshata, Rachna and Shweta. 
 *   Abstract class for Tetra People
 */
public abstract class TetraPeople {

    protected int id;
    
    protected TetraPeopleObservable peopleObservable;

    protected TFaceGrid currentLocation, newLocation, oldLocation;

    public abstract void makeMove(Direction direction, JButton[][] gridOfLocations);

    public abstract void setLocation(JButton[][] gridOfLocations, TFaceGrid initialLocation, PeopleType peopleType);

    public abstract void flyToLocation(JButton[][] gridOfLocations, TFaceGrid locationToFlyTo) throws Exception;

    public abstract void requestToFly();

    public abstract EncryptionStrategy getEncryptionStrategy();

    protected boolean vaderExit;

    public void makeInitialMove(Direction direction, JButton[][] gridOfLocations, TFaceGrid locationToMoveTo) {
        this.currentLocation = locationToMoveTo;
        makeMove(direction, gridOfLocations);
    }

    public void addObserver(Observer observer) {
        peopleObservable.addObserver(observer);
    }

    public TFaceGrid getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(TFaceGrid currentLocation) {
        this.currentLocation = currentLocation;
    }

    public TFaceGrid getNewLocation() {
        return newLocation;
    }

    public void setNewLocation(TFaceGrid newLocation) {
        this.newLocation = newLocation;
    }

    public TFaceGrid getOldLocation() {
        return oldLocation;
    }

    public void setOldLocation(TFaceGrid oldLocation) {
        this.oldLocation = oldLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVaderExit() {
        return vaderExit;
    }

    public void setVaderExit(boolean vaderExit) {
        this.vaderExit = vaderExit;
    }

    public TetraPeopleObservable getPeopleObservable() {
        return peopleObservable;
    }

    public void setPeopleObservable(TetraPeopleObservable peopleObservable) {
        this.peopleObservable = peopleObservable;
    }

}
