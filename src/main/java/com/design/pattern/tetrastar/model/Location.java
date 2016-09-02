package com.design.pattern.tetrastar.model;

import com.design.pattern.tetrastar.enums.PeopleType;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public abstract class Location {

    protected int locationId;

    protected TFaceGrid gridLocation;

    protected Image imageIcon = null;

    protected JButton[][] gridOfLocations;
    protected StarMapComponent starMap;

    public abstract String getImageName();

    public abstract void processMap(TetraPeople people) throws Exception;
    
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
            System.out.println("Can not read image because of exception : " + e.getMessage());
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
