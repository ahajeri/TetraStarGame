package com.design.pattern.tetrastar.model;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Akshata, Rachna and Shweta.
 * Abstract class to represent the location of a base (mapbase, vaderbase or
 * herobase)
 */
public abstract class Location {

    protected int locationId;

    protected TFaceGrid gridLocation;

    protected Image imageIcon = null;

    protected JButton[][] gridOfLocations;
    protected StarMapComponent starMap;

    public abstract String getImageName();

    public abstract void processMap(TetraPeople people) throws Exception;

    // Visitor pattern
    public abstract void accept(LocationVisitor locationVisitor);

    public TFaceGrid getGridLocation() {
        return this.gridLocation;
    }

    public void setStarMapComponent(StarMapComponent starMap) {
        this.starMap = starMap;
    }

    public void setBaseGridLocation(JButton[][] gridOfLocations, TFaceGrid location, String baseText) {
        this.gridLocation = location;
        this.gridOfLocations = gridOfLocations;

        gridOfLocations[gridLocation.getRow()][gridLocation.getColumn()].setText(baseText);

        try {
            String imgName = this.getImageName();
            imageIcon = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/" + imgName));
        } catch (IOException e) {
            System.err.println("Can not read image because of exception : " + e.getMessage());
            System.exit(1);
        }

        gridOfLocations[gridLocation.getRow()][gridLocation.getColumn()].setIcon(new ImageIcon(imageIcon));
        gridOfLocations[gridLocation.getRow()][gridLocation.getColumn()].setDisabledIcon(new ImageIcon(imageIcon));
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

}
