/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;

import com.design.pattern.tetrastar.constants.TetraConstants;
import com.design.pattern.tetrastar.enums.Direction;
import com.design.pattern.tetrastar.enums.EncryptionStrategy;
import com.design.pattern.tetrastar.enums.PeopleType;
import com.design.pattern.tetrastar.util.CreateMessageUtility;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Rachna Gajre <rgajre@scu.edu>
 */
public class TetraVader extends TetraPeople {

    private Image img, img2, image = null;
    private TFlier flyVehicle;
    private StarMapComponent starMap;

    private boolean enteredMapbase = false;
    private Stack<TFaceGrid> returnToHomePath;
    private boolean returnToHomePathStatus = false;

    private static TetraVader vader = null;

    private TetraVader() {
        peopleObservable = new TetraPeopleObservable();
        returnToHomePath = new Stack<TFaceGrid>();
        addLocationToPath(new TFaceGrid(3, 3));
        flyVehicle = new TFlier();
    }

    public static TetraPeople getInstance() {
        if (vader == null) {
            vader = new TetraVader();
        }
        return vader;
    }

    public void addLocationToPath(TFaceGrid location) {
        if (location != null) {
            returnToHomePath.push(location);
        }
    }

    public TFaceGrid extractLastLocationFromPath() {
        if (!returnToHomePath.isEmpty()) {
            return returnToHomePath.pop();
        }
        return null;
    }

    public void reInitPath() {
        while (!returnToHomePath.isEmpty()) {
            returnToHomePath.pop();
        }
        addLocationToPath(new TFaceGrid(3, 3));
    }

    @Override
    public void makeMove(Direction direction, JButton[][] gridOfLocations) {
        if (direction != null) {
            switch (direction) {
                case NORTH:
                    makeNorthMove(gridOfLocations);
                    break;
                case SOUTH:
                    makeSouthMove(gridOfLocations);
                    break;
                case EAST:
                    makeEastMove(gridOfLocations);
                    break;
                case WEST:
                    makeWestMove(gridOfLocations);
                    break;
            }
        }
    }

    private void makeNorthMove(JButton[][] gridOfLocations) {
        int newRow = currentLocation.getRow() - 1;
        if (newRow < TetraConstants.minRows) {
            String s = "Cannot move North. Out of the Grid";
            CreateMessageUtility.createMsg(s);
        } else {
            newLocation = new TFaceGrid(newRow, currentLocation.getColumn());

            checkAndMove(gridOfLocations, newLocation);
        }
    }

    private void makeSouthMove(JButton[][] gridOfLocations) {
        int newRow = currentLocation.getRow() + 1;
        if (newRow > TetraConstants.maxRows) {
            String s = "Cannot move South. Out of the Grid";
            CreateMessageUtility.createMsg(s);
        } else {
            newLocation = new TFaceGrid(newRow, currentLocation.getColumn());

            checkAndMove(gridOfLocations, newLocation);
        }
    }

    private void makeEastMove(JButton[][] gridOfLocations) {
        int newCol = currentLocation.getColumn() + 1;
        if (newCol > TetraConstants.maxColumns) {
            String s = "Cannot move East. Out of the Grid";
            CreateMessageUtility.createMsg(s);
        } else {
            newLocation = new TFaceGrid(currentLocation.getRow(), newCol);

            checkAndMove(gridOfLocations, newLocation);
        }
    }

    private void makeWestMove(JButton[][] gridOfLocations) {
        int newCol = currentLocation.getColumn() - 1;
        if (newCol < TetraConstants.minColumns) {
            String s = "Cannot move West. Out of the Grid";
            CreateMessageUtility.createMsg(s);
        } else {
            newLocation = new TFaceGrid(currentLocation.getRow(), newCol);

            checkAndMove(gridOfLocations, newLocation);
        }
    }

    public String checkLocation(JButton[][] gridOfLocations, TFaceGrid currentLocation) {
        int row = currentLocation.getRow();
        int col = currentLocation.getColumn();
        String characterObject;

        if (gridOfLocations[row][col].getText().equals("")) {
            characterObject = "empty";
        } else {
            characterObject = gridOfLocations[row][col].getText();
        }

        return characterObject;
    }

    private void checkAndMove(JButton[][] gridOfLocations, TFaceGrid newLocation) {
        String characterObject = checkLocation(gridOfLocations, newLocation);
        if (characterObject.equals("empty")) {
            try {
                img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/tVader.jpg"));
            } catch (IOException e) {
                System.err.println("Error occurred " + e.getMessage());
            }
            if (!returnToHomePathStatus) {
                addLocationToPath(newLocation);
            }
            setIcons(gridOfLocations);

        } else {
            String s = "Location occupied. Fly to some other location.";
            CreateMessageUtility.createMsg(s);
        }
    }

    private void setIcons(JButton[][] gridOfLocations) {
        try {
            image = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/Surface.jpg"));
        } catch (IOException e) {
            System.err.println("Error occurred reading image : " + e.getMessage());
        }
        gridOfLocations[currentLocation.getRow()][currentLocation.getColumn()].setIcon(new ImageIcon(image));
        gridOfLocations[currentLocation.getRow()][currentLocation.getColumn()].setDisabledIcon(new ImageIcon(image));
        gridOfLocations[currentLocation.getRow()][currentLocation.getColumn()].setText("");

        gridOfLocations[newLocation.getRow()][newLocation.getColumn()].setIcon(new ImageIcon(img));
        gridOfLocations[newLocation.getRow()][newLocation.getColumn()].setDisabledIcon(new ImageIcon(img));

        currentLocation = newLocation;
    }

    // Set initial Location of vader
    @Override
    public void setLocation(JButton[][] gridOfLocations, TFaceGrid initialLocation, PeopleType peopleType) {

        try {
            img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/tVader.jpg"));
            img2 = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/river.jpg"));
        } catch (IOException e) {
            System.err.println("Exception occurred " + e.getMessage());
        }

        gridOfLocations[initialLocation.getRow()][initialLocation.getColumn()].setIcon(new ImageIcon(img));
        gridOfLocations[initialLocation.getRow()][initialLocation.getColumn()].setDisabledIcon(new ImageIcon(img));

        // set the image of river around the vader.
        gridOfLocations[initialLocation.getRow() - 1][initialLocation.getColumn()].setIcon(new ImageIcon(img2));
        gridOfLocations[initialLocation.getRow() - 1][initialLocation.getColumn()].setDisabledIcon(new ImageIcon(img2));
        gridOfLocations[initialLocation.getRow() - 1][initialLocation.getColumn()].setText("RIVER");

        gridOfLocations[initialLocation.getRow() + 1][initialLocation.getColumn()].setIcon(new ImageIcon(img2));
        gridOfLocations[initialLocation.getRow() + 1][initialLocation.getColumn()].setDisabledIcon(new ImageIcon(img2));
        gridOfLocations[initialLocation.getRow() + 1][initialLocation.getColumn()].setText("RIVER");

        gridOfLocations[initialLocation.getRow()][initialLocation.getColumn() - 1].setIcon(new ImageIcon(img2));
        gridOfLocations[initialLocation.getRow()][initialLocation.getColumn() - 1].setDisabledIcon(new ImageIcon(img2));
        gridOfLocations[initialLocation.getRow()][initialLocation.getColumn() - 1].setText("RIVER");

        gridOfLocations[initialLocation.getRow()][initialLocation.getColumn() + 1].setIcon(new ImageIcon(img2));
        gridOfLocations[initialLocation.getRow()][initialLocation.getColumn() + 1].setDisabledIcon(new ImageIcon(img2));
        gridOfLocations[initialLocation.getRow()][initialLocation.getColumn() + 1].setText("RIVER");

        // set the current location of the vader which will be used in the simulation
        currentLocation = initialLocation;
    }

    @Override
    public void flyToLocation(JButton[][] gridOfLocations, TFaceGrid locationToFlyTo) throws Exception {
        Image flyImg = null;

        String characterObj = checkLocation(gridOfLocations, locationToFlyTo);

        if (characterObj.equals("empty") || characterObj.equals("HEROBASE") || characterObj.equals("MAPBASE") || characterObj.equals("VADERBASE")) {
            ArrayList newSetLocation = (ArrayList) flyVehicle.flyToLocation(gridOfLocations, currentLocation, locationToFlyTo, PeopleType.VADER);

            // Set the old and the new locations of hero
            oldLocation = currentLocation;
            currentLocation = locationToFlyTo;

            String oldLocationText = gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].getText();
            System.out.println("--->" + oldLocationText);

            // Check if the vader had entered the Mapbase anytime.						
            if (oldLocationText.equals("MAPBASE")) {
                flyImg = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/MapBase.jpg"));
                gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(new ImageIcon(flyImg));
                gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setDisabledIcon(new ImageIcon(flyImg));
            } else if (oldLocationText.equals("VADERBASE")) {
                flyImg = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/vaderHouse.jpg"));
                gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(new ImageIcon(flyImg));
                gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setDisabledIcon(new ImageIcon(flyImg));
            } else if (oldLocationText.equals("empty") || (oldLocationText.equals(""))) {
                //gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(null);
                image = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/Surface.jpg"));
                gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(new ImageIcon(image));
                gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setDisabledIcon(new ImageIcon(image));
                gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setText("");
            } else if (oldLocationText.equals("HEROBASE")) {
                flyImg = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/heroBase.jpg"));
                gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(new ImageIcon(flyImg));
                gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setDisabledIcon(new ImageIcon(flyImg));
            }
        }

        if (!characterObj.equals("empty")) {
            PeopleNotify notification = new PeopleNotify();
            notification.people = this;
            notification.baseLocation = locationToFlyTo;

            if (characterObj.equals("HEROBASE")) {
                notification.notificationType = NotificationType.HEROBASE;
            } else if (characterObj.equals("VADERBASE")) {
                notification.notificationType = NotificationType.VADERBASE;
            } else {
                notification.notificationType = NotificationType.MAPBASE;
                enteredMapbase = true;
            }
            peopleObservable.setChanged();
            peopleObservable.notifyObservers(notification);
        } else {
            if (!returnToHomePathStatus) {
                addLocationToPath(currentLocation);
            }
        }
    }

    public void flyWithMap(StarMapComponent map, JButton[][] gridOfLocations, TFaceGrid currentLocation, TFaceGrid newLocation) {
        this.starMap = map;
        try {
            this.flyToLocation(gridOfLocations, newLocation);
        } catch (Exception e) {
            System.err.println("Error occurred " + e.getMessage());
        }
    }

    @Override
    public void requestToFly() {
        flyVehicle = new TFlier();
    }

    @Override
    public EncryptionStrategy getEncryptionStrategy() {
        return EncryptionStrategy.NULL;
    }

    public StarMapComponent getStarMap() {
        return starMap;
    }

    public void setStarMap(StarMapComponent starMap) {
        this.starMap = starMap;
    }

    public boolean isReturnToHomePathStatus() {
        return returnToHomePathStatus;
    }

    public void setReturnToHomePathStatus(boolean returnToHomePathStatus) {
        this.returnToHomePathStatus = returnToHomePathStatus;
    }

}
