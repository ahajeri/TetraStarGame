/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;

import com.design.pattern.tetrastar.enums.PeopleType;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Rachna Gajre <rgajre@scu.edu>
 */
public class TFlier {

    private int flierId;

    private boolean flightSuccess = false;

    private Image img = null;

    private String message = null;

    private ArrayList<TFaceGrid> locationSet;

    public TFlier() {
    }

    public TFlier(int id) {
        flierId = id;
    }

    public int getFlierId() {
        return flierId;
    }

    public void setFlierId(int flierId) {
        this.flierId = flierId;
    }

    public boolean isFlightSuccess() {
        return flightSuccess;
    }

    public void setFlightSuccess(boolean flightSuccess) {
        this.flightSuccess = flightSuccess;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<TFaceGrid> getLocationSet() {
        return locationSet;
    }

    public void setLocationSet(ArrayList<TFaceGrid> locationSet) {
        this.locationSet = locationSet;
    }

    public ArrayList<TFaceGrid> flyToLocation(JButton[][] gridOfLocations, TFaceGrid currentLocation, TFaceGrid newLocation, PeopleType peopleType) throws IOException {
        int row = newLocation.getRow();
        int col = newLocation.getColumn();

        // Set the image according to the type of character.
        if (peopleType.equals(PeopleType.HERO1)) {
            img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/hero1.png"));
        } else if (peopleType.equals(PeopleType.HERO2)) {
            img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/hero2.jpg"));
        } else if (peopleType.equals(PeopleType.VADER)) {
            img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/tVader.jpg"));
        }

        // Set the new location with the image.		
        gridOfLocations[newLocation.getRow()][newLocation.getColumn()].setIcon(new ImageIcon(img));
        gridOfLocations[newLocation.getRow()][newLocation.getColumn()].setDisabledIcon(new ImageIcon(img));

        // Pass the current and New location back.
        locationSet = new ArrayList<TFaceGrid>();
        locationSet.add(currentLocation);
        locationSet.add(newLocation);

        return locationSet;

    }

}
