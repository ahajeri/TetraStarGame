package com.design.pattern.tetrastar.view;
/**
 *   @author Akshata, Rachna and  Shweta. 
 */

import com.design.pattern.tetrastar.constants.TetraConstants;
import com.design.pattern.tetrastar.enums.PeopleType;
import com.design.pattern.tetrastar.generator.IdGenerator;
import com.design.pattern.tetrastar.model.Location;
import com.design.pattern.tetrastar.model.TFaceGrid;
import com.design.pattern.tetrastar.model.TetraPeople;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class GridDisplayScreen extends JPanel {

    protected JButton[][] gridOfButtons = new JButton[TetraConstants.maxRows][TetraConstants.maxColumns];

    protected IdGenerator idGenerator;

    final int numberOfBlocks = TetraConstants.maxRows * TetraConstants.maxColumns;

    // Character objects
    protected TetraPeople hero1, hero2, vader, rover1, rover2;

    // Locations for all characters 
    protected TFaceGrid tHero1Loc, tHero2Loc;
    protected TFaceGrid tRover1Loc, tRover2Loc, tMap1Loc;
    protected TFaceGrid tVaderLoc, tMapLoc, tMapbLoc;
    // A Location to fly to for either a hero or vader
    protected TFaceGrid flyLoc;
    protected TFaceGrid hero1bLoc, hero2bLoc;

    // Locations of bases
    protected Location vaderBaseLoc;
    protected Location hero1BaseLoc, hero2BaseLoc;
    protected Location mapBaseLoc;

    public abstract void setInitialPositions();

    public abstract void startSimulation();

    public GridDisplayScreen() {
        super();

        this.setBackground(Color.black);

        this.generateGrid();

        this.idGenerator = IdGenerator.getInstance();
    }

    private void generateGrid() {
        for (int i = 0; i < TetraConstants.maxRows; i++) {
            for (int j = 0; j < TetraConstants.maxColumns; j++) {
                this.gridOfButtons[i][j] = new JButton();
                BufferedImage img = null;
                try {
                    img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/Surface.jpg"));
                } catch (IOException e) {
                    System.err.println("Error occurred while reading image : " + e.getMessage());
                    System.exit(1);
                }
                JButton button = new JButton();
                button.setIcon(new ImageIcon(img));
                button.setDisabledIcon(new ImageIcon(img));
                this.gridOfButtons[i][j] = button;
                this.gridOfButtons[i][j].setEnabled(false);
            }
        }

        this.setLayout(new GridLayout(TetraConstants.maxRows, TetraConstants.maxColumns));

        for (int i = 0; i < TetraConstants.maxRows; i++) {
            for (int j = 0; j < TetraConstants.maxColumns; j++) {
                BufferedImage img = null;
                try {
                    img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/Surface.jpg"));
                } catch (IOException e) {
                    System.err.println("Error occurred while reading image : " + e.getMessage());
                    System.exit(1);
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
            System.exit(1);
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
        
    }
}
