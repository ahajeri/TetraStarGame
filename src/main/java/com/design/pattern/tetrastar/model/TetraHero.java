package com.design.pattern.tetrastar.model;
/**
 *   @author Akshata, Rachna and Shweta. 
 */

import com.design.pattern.tetrastar.constants.TetraConstants;
import com.design.pattern.tetrastar.enums.Direction;
import com.design.pattern.tetrastar.enums.EncryptionStrategy;
import com.design.pattern.tetrastar.enums.PeopleType;
import com.design.pattern.tetrastar.util.CreateMessageUtility;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import javax.swing.*;

public class TetraHero extends TetraPeople {

    private Image img, currentImage, newImage, image = null;

    private TFlier flyVehicle;

    // Symbol for encryption - decryption
    private char symbol;

    //Hero1 or Hero2
    private PeopleType heroType;

    private EncryptionStrategy encryptionStrategy;

    private TFaceGrid mapToVader;

    private boolean enteredMapbase = false;

    private boolean enteredHomebase = false;

    //StarMap.
    private StarMapComponent oldstarMap = null; // Original map - encrypted
    private StarMapComponent NewstarMap = null;

    public TetraHero(char symbol) {
        this.peopleObservable = new TetraPeopleObservable();
        this.symbol = symbol;
        this.encryptionStrategy = EncryptionStrategy.SIMPLE;
        this.oldLocation = new TFaceGrid();
    }

    /**
     *
     * @param gridOfLocations
     * @param initialLocation
     * @param heroType
     */
    @Override
    public void setLocation(JButton[][] gridOfLocations, TFaceGrid initialLocation, PeopleType heroType) {

        this.heroType = heroType;

        try {
            if (heroType.equals(PeopleType.HERO1)) {
                img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/hero1.png"));
            } else {
                img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/hero2.jpg"));
            }
        } catch (IOException e) {
            System.err.println("Error occurred " + e.getMessage());

        }

        gridOfLocations[initialLocation.getRow()][initialLocation.getColumn()].setText("HERO");
        gridOfLocations[initialLocation.getRow()][initialLocation.getColumn()].setIcon(new ImageIcon(img));
        gridOfLocations[initialLocation.getRow()][initialLocation.getColumn()].setDisabledIcon(new ImageIcon(img));

        currentLocation = initialLocation;
    }

    @Override
    public void flyToLocation(JButton[][] gridOfLocations, TFaceGrid locationToFlyTo) throws Exception {
        Image flyImg = null;
        ArrayList newSetLocation;

        System.out.println("Hero wants to fly to grid location " + locationToFlyTo.getRow() + " " + locationToFlyTo.getColumn());

        if (flyVehicle == null) {
            String s = " Hero cannot fly without the flier. Request flier";
            CreateMessageUtility.createMsg(s);
        } else {
            String characterObj = checkLocation(gridOfLocations, locationToFlyTo);

            if (characterObj.equals("empty") || characterObj.equals("HEROBASE") || characterObj.equals("MAPBASE") || characterObj.equals("VADERBASE")) {

                if (heroType.equals(PeopleType.HERO1)) {

                    newSetLocation = (ArrayList) flyVehicle.flyToLocation(gridOfLocations, currentLocation, locationToFlyTo, PeopleType.HERO1);

                } else {
                    newSetLocation = (ArrayList) flyVehicle.flyToLocation(gridOfLocations, currentLocation, locationToFlyTo, PeopleType.HERO2);
                }

                TFaceGrid newCurrentLocation = (TFaceGrid) newSetLocation.get(1);

                // Set the old and the new locations of hero
                oldLocation = currentLocation;
                currentLocation = locationToFlyTo;

                String oldLocationText = gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].getText();

                // Check if the hero had entered the Mapbase anytime.
                if (enteredMapbase) {
                    if (oldLocationText.equals("MAPBASE")) {
                        flyImg = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/MapBase.jpg"));
                        gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(new ImageIcon(flyImg));
                        gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setDisabledIcon(new ImageIcon(flyImg));
                    } else if (oldLocationText.equals("VADERBASE")) {

                        if (vaderExit) {
                            flyImg = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/vaderHouse.jpg"));
                        } else {
                            flyImg = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/tVader.jpg"));
                        }
                        gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(new ImageIcon(flyImg));
                        gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setDisabledIcon(new ImageIcon(flyImg));
                    } else if (oldLocationText.equals("empty")) {
                        image = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/Surface.jpg"));
                        //gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(null);
                        gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(new ImageIcon(image));
                        gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setDisabledIcon(new ImageIcon(image));
                        gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setText("");
                    } else if (oldLocationText.equals("HEROBASE")) {
                        flyImg = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/heroBase.jpg"));
                        gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(new ImageIcon(flyImg));
                        gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setDisabledIcon(new ImageIcon(flyImg));
                    }

                } else {
                   // gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(null);
                    // gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setText("");
                    image = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/Surface.jpg"));
                    gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(new ImageIcon(image));
                    gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setDisabledIcon(new ImageIcon(image));
                    gridOfLocations[oldLocation.getRow()][oldLocation.getColumn()].setText("");
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
                }
            }
        }
    }

    @Override
    public void requestToFly() {
        flyVehicle = new TFlier();
    }

    @Override
    public EncryptionStrategy getEncryptionStrategy() {
        return encryptionStrategy;
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

    public void setIcons(JButton[][] gridOfLocations, Image currentImage, Image newImage) {
        if (currentImage == null) {
            try {
                image = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/Surface.jpg"));
            } catch (IOException e) {
                System.err.println("Exception occurred while reading image : " + e.getMessage());
            }
            gridOfLocations[currentLocation.getRow()][currentLocation.getColumn()].setIcon(new ImageIcon(image));
            gridOfLocations[currentLocation.getRow()][currentLocation.getColumn()].setDisabledIcon(new ImageIcon(image));
            gridOfLocations[currentLocation.getRow()][currentLocation.getColumn()].setText("");
            //gridOfLocations[currentLocation.getRow()][currentLocation.getColumn()].setIcon(null);
            //gridOfLocations[currentLocation.getRow()][currentLocation.getColumn()].setText("");
        } else {
            gridOfLocations[currentLocation.getRow()][currentLocation.getColumn()].setIcon(new ImageIcon(currentImage));
            gridOfLocations[currentLocation.getRow()][currentLocation.getColumn()].setDisabledIcon(new ImageIcon(currentImage));

        }

        gridOfLocations[newLocation.getRow()][newLocation.getColumn()].setIcon(new ImageIcon(newImage));
        gridOfLocations[newLocation.getRow()][newLocation.getColumn()].setDisabledIcon(new ImageIcon(newImage));

        currentLocation = newLocation;
    }

    private void checkAndMove(JButton[][] gridOfLocations, TFaceGrid newLocation) {
        String characterObject = checkLocation(gridOfLocations, newLocation);
        if (characterObject.equals("empty")) {
            try {
                System.out.println("Hero wants to move to grid location " + newLocation.getRow() + "  " + newLocation.getColumn());
                if (heroType.equals(PeopleType.HERO1)) {
                    newImage = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/hero1.png"));
                } else {
                    newImage = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/hero2.jpg"));
                }
                if (enteredHomebase == true) {
                    currentImage = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/heroBase.jpg"));
                    enteredHomebase = false;
                } else {
                    currentImage = null;
                	 //currentImage = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/heroBase.jpg"));
                }

            } catch (IOException e) {
                System.err.println("Error occurred " + e.getMessage());
            }

            setIcons(gridOfLocations, currentImage, newImage);
        } else if (characterObject.equals("HEROBASE") || characterObject.equals("MAPBASE")) {
            System.out.println("TetraHero:CheckandMove");
            PeopleNotify notification = new PeopleNotify();
            notification.people = this;
            notification.baseLocation = newLocation;
            if (characterObject.equals("HEROBASE")) {
                notification.notificationType = NotificationType.HEROBASE;
                enteredHomebase = true;
            } else {
                notification.notificationType = NotificationType.MAPBASE;
            }

            // Notify observers that the hero has entered the base.
            peopleObservable.setChanged();
            peopleObservable.notifyObservers(notification);
        } else if (characterObject.equals("VADERBASE")) {
            String s = "HERO needs a fly vehicle to enter the vaderbase.";
            CreateMessageUtility.createMsg(s);
        } else {
            String s = "TFACE occupied. Fly to some other location";
            CreateMessageUtility.createMsg(s);
        }

    }

   

    public void flyWithMap(StarMapComponent originalMap, StarMapComponent newMap, JButton[][] gridOfLocations, TFaceGrid currentLocation, TFaceGrid newLocation) {
        this.oldstarMap = originalMap;
        this.NewstarMap = newMap;
        try {
            this.flyToLocation(gridOfLocations, newLocation);
        } catch (Exception e) {
            System.err.println("Error occurred " + e.getMessage());
        }
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Image getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(Image currentImage) {
        this.currentImage = currentImage;
    }

    public Image getNewImage() {
        return newImage;
    }

    public void setNewImage(Image newImage) {
        this.newImage = newImage;
    }

    public TFlier getFlyVehicle() {
        return flyVehicle;
    }

    public void setFlyVehicle(TFlier flyVehicle) {
        this.flyVehicle = flyVehicle;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PeopleType getHeroType() {
        return heroType;
    }

    public void setHeroType(PeopleType heroType) {
        this.heroType = heroType;
    }

    public TFaceGrid getMapToVader() {
        return mapToVader;
    }

    public void setMapToVader(TFaceGrid mapToVader) {
        this.mapToVader = mapToVader;
    }

    public boolean isEnteredMapbase() {
        return enteredMapbase;
    }

    public void setEnteredMapbase(boolean enteredMapbase) {
        this.enteredMapbase = enteredMapbase;
    }

    public boolean isEnteredHomebase() {
        return enteredHomebase;
    }

    public void setEnteredHomebase(boolean enteredHomebase) {
        this.enteredHomebase = enteredHomebase;
    }

    public StarMapComponent getOldstarMap() {
        return oldstarMap;
    }

    public void setEncryptionStrategy(EncryptionStrategy encryptionStrategy) {
        this.encryptionStrategy = encryptionStrategy;
    }

    public void setOldstarMap(StarMapComponent oldstarMap) {
        this.oldstarMap = oldstarMap;
    }

    public StarMapComponent getNewstarMap() {
        return NewstarMap;
    }

    public void setNewstarMap(StarMapComponent NewstarMap) {
        this.NewstarMap = NewstarMap;
    }

}
