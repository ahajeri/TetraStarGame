package com.design.pattern.tetrastar.model;

public class TetraRoverMemento implements TRoverMementoToOriginator {

    private final TFaceGrid savedLocation;

    public TetraRoverMemento(TFaceGrid savedLocation) {
        this.savedLocation = savedLocation;
    }

    @Override
    public TFaceGrid getSavedLocation() {
        return savedLocation;
    }

}
