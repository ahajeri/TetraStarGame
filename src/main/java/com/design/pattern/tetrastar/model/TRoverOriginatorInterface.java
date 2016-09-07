package com.design.pattern.tetrastar.model;

import javax.swing.*;

public interface TRoverOriginatorInterface {

    TetraRoverMemento backupLastPosition();

    void restoreLastPosition(TetraRoverMemento tetraRoverMemento, JButton[][] gridOfLocations);

}
