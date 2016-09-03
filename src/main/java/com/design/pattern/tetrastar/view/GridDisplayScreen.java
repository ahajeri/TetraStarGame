package com.design.pattern.tetrastar.view;
/**
 *   @author Akshata, Rachna and  Shweta. 
 */

import com.design.pattern.tetrastar.enums.PeopleType;
import com.design.pattern.tetrastar.generator.IdGenerator;
import com.design.pattern.tetrastar.model.Location;
import com.design.pattern.tetrastar.model.TFaceGrid;
import com.design.pattern.tetrastar.model.TFlier;
import com.design.pattern.tetrastar.model.TetraPeople;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class GridDisplayScreen extends JPanel {

    public static final int rows = 7;
    public static final int columns = 7;

    protected JButton[][] gridOfButtons = new JButton[rows][columns];

    protected IdGenerator idGenerator;

    final int numberOfBlocks = 49;

    // Character objects
    protected TetraPeople hero1, hero2, vader, rover1, rover2;

    // Locations for all characters 
    protected TFaceGrid tHero1Loc, tHero2Loc;
    protected TFaceGrid tRover1Loc, tRover2Loc, tMap1Loc;
    protected TFaceGrid tVaderLoc, tMapLoc, tMapbLoc, tMapb1Loc;
    protected TFaceGrid flyLoc;
    protected TFaceGrid hero1bLoc, hero2bLoc;

    // Locations of bases
    protected Location vaderBaseLoc;
    protected Location hero1BaseLoc, hero2BaseLoc;
    protected Location mapBaseLoc, mapBase1Loc;

    protected TFlier flyIcon;
    protected JOptionPane messageLabel;

    public abstract void setInitialPositions();

    public abstract void startSimulation();

    public GridDisplayScreen() {
        super();

        this.setBackground(Color.black);

        this.generateGrid();

        this.idGenerator = IdGenerator.getInstance();
    }

    private void generateGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.gridOfButtons[i][j] = new JButton();
                BufferedImage img = null;
                try {
                    img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/Surface.jpg"));
                } catch (IOException e) {
                    System.err.println("Error occurred while reading imag : " + e.getMessage());
                }
                JButton button = new JButton();
                button.setIcon(new ImageIcon(img));
                button.setDisabledIcon(new ImageIcon(img));
                this.gridOfButtons[i][j] = button;
                this.gridOfButtons[i][j].setEnabled(false);
            }
        }

        this.setLayout(new GridLayout(rows, columns));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                BufferedImage img = null;
                try {
                    img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/Surface.jpg"));
                } catch (IOException e) {
                    System.err.println("Error occurred while reading imag : " + e.getMessage());
                }
                JButton button = new JButton();
                button.setIcon(new ImageIcon(img));
                button.setDisabledIcon(new ImageIcon(img));
                this.gridOfButtons[i][j] = button;
                this.gridOfButtons[i][j].setEnabled(false);
                add(this.gridOfButtons[i][j]);
            }
        }
    }

    public static void sleepForHalfSecond() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            System.err.println("Error occurred " + ex.getMessage());
        }
    }

    /**
     * Place all characters initially on grid
     */
    public void setTetraPeopleToLocation() {
        // set the initial location of the heroes
        hero1.setLocation(gridOfButtons, tHero1Loc, PeopleType.HERO1);
        hero2.setLocation(gridOfButtons, tHero2Loc, PeopleType.HERO2);

        // set the initial location of the rovers
        rover1.setLocation(gridOfButtons, tRover1Loc, PeopleType.ROVER1);
        rover2.setLocation(gridOfButtons, tRover2Loc, PeopleType.ROVER2);

        // set the location of the vaderbase
        vaderBaseLoc.setBaseGridLocation(gridOfButtons, tVaderLoc, "VADERBASE");

        // set the initial location of the vader.
        vader.setLocation(gridOfButtons, tVaderLoc, PeopleType.VADER);

        // sets the id of the heroes.
        hero1.setId(idGenerator.nextId());
        hero2.setId(idGenerator.nextId());
        rover1.setId(idGenerator.nextId());
        rover2.setId(idGenerator.nextId());

        // set the location of the herobase and set the id to the respective heroid
        hero1BaseLoc.setBaseGridLocation(gridOfButtons, hero1bLoc, "HEROBASE");
        hero1BaseLoc.setLocationId(hero1.getId());

        hero2BaseLoc.setBaseGridLocation(gridOfButtons, hero2bLoc, "HEROBASE");
        hero2BaseLoc.setLocationId(hero2.getId());

        // set the location of the mapbase.
        mapBaseLoc.setBaseGridLocation(gridOfButtons, tMapbLoc, "MAPBASE");
        // set the location of the mapbase.
        //mapBase1Loc.setBaseGridLocation(gridOfButtons, tMapb1Loc, "MAPBASE");
    }
}
