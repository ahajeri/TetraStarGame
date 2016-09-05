package com.design.pattern.tetrastar.view;

/**
 * @author Akshata, Rachna and Shweta.
 */

import com.design.pattern.tetrastar.enums.Direction;
import com.design.pattern.tetrastar.enums.RoverTypes;
import com.design.pattern.tetrastar.model.TFaceGrid;
import com.design.pattern.tetrastar.model.THeroBase;
import com.design.pattern.tetrastar.model.TMapBase;
import com.design.pattern.tetrastar.model.TVaderBase;
import com.design.pattern.tetrastar.model.TetraPeopleFactory;
import com.design.pattern.tetrastar.model.TetraPeopleObserverAndMediator;
import com.design.pattern.tetrastar.util.CreateMessageUtility;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Scenario1DisplayScreen extends GridDisplayScreen {

    private static final Logger logger = Logger.getLogger(Scenario1DisplayScreen.class.getName());

    public Scenario1DisplayScreen() {
        super();
    }

    @Override
    public void setInitialPositions() {

        // Create the different base location objects
        vaderBaseLoc = TVaderBase.getInstance();
        hero1BaseLoc = new THeroBase();
        hero2BaseLoc = new THeroBase();
        mapBaseLoc = new TMapBase();
        
        // Create instances of heroes, vader and rovers aka TetraPeople using factory        
        hero1 = TetraPeopleFactory.createTetraPeople(RoverTypes.HERO, '*');
        hero2 = TetraPeopleFactory.createTetraPeople(RoverTypes.HERO, '#');
        vader = TetraPeopleFactory.createTetraPeople(RoverTypes.VADER, '\0');
        rover1 = TetraPeopleFactory.createTetraPeople(RoverTypes.ROVER, '\0');
        rover2 = TetraPeopleFactory.createTetraPeople(RoverTypes.ROVER, '\0');

        Image img = null;

        tHero1Loc = new TFaceGrid(2, 1);
        tHero2Loc = new TFaceGrid(5, 4);
        tRover1Loc = new TFaceGrid(0, 2);
        tRover2Loc = new TFaceGrid(6, 2);
        tVaderLoc = new TFaceGrid(3, 3);
        tMapLoc = new TFaceGrid(3, 3);
        hero1bLoc = new TFaceGrid(0, 0);
        hero2bLoc = new TFaceGrid(6, 6);
        tMapbLoc = new TFaceGrid(4, 6);

        TetraPeopleObserverAndMediator tPeopleObserver = new TetraPeopleObserverAndMediator();

        // register the people to the mediator.
        tPeopleObserver.registerTPeople(hero1);
        tPeopleObserver.registerTPeople(hero2);
        tPeopleObserver.registerTPeople(vader);
        tPeopleObserver.registerTPeople(rover1);
        tPeopleObserver.registerTPeople(rover2);

        // Place the flier vehicle on grid
        try {
            img = ImageIO.read(getClass().getResource(
                    "/com/design/pattern/tetrastar/images/tFlier.jpg"));
        } catch (IOException e) {
            System.err.println("Error occurred " + e.getMessage());
        }

        gridOfButtons[0][6].setIcon(new ImageIcon(img));
        gridOfButtons[0][6].setDisabledIcon(new ImageIcon(img));

        // set the fly location
        flyLoc = new TFaceGrid(6, 6);

        this.setTetraPeopleToLocation();

		// register the mapbase, herobase and vaderbase location to the
        // mediator.
        tPeopleObserver.registerTHomeBase(hero1BaseLoc);
        tPeopleObserver.registerTHomeBase(hero2BaseLoc);
        tPeopleObserver.registerTHomeBase(vaderBaseLoc);
        tPeopleObserver.registerTHomeBase(mapBaseLoc);

    }

    @Override
    public void startSimulation() {
        try {

            // Hero1 moves demo
            CreateMessageUtility
                    .createMsg("Demo for movements of all inhabitants of Tetra Planet");
            
            System.out.println("Hero1 movements demo");
            CreateMessageUtility.createMsg("Hero1 wants to move north.");
            System.out.println("Hero1 wants to move north.");
            hero1.makeInitialMove(Direction.NORTH, gridOfButtons, tHero1Loc);
            sleepForHalfSecond();

            CreateMessageUtility.createMsg("Hero1 wants to move east.");
            System.out.println("Hero1 wants to move east.");
            hero1.makeMove(Direction.EAST, gridOfButtons);
            sleepForHalfSecond();

            CreateMessageUtility.createMsg("Hero1 wants to move south.");
            System.out.println("Hero1 wants to move south.");
            hero1.makeMove(Direction.SOUTH, gridOfButtons);
            sleepForHalfSecond();

            CreateMessageUtility.createMsg("Hero1 wants to move east.");
            System.out.println("Hero1 wants to move east.");
            hero1.makeMove(Direction.EAST, gridOfButtons);
            sleepForHalfSecond();

            // Hero2 fly demo
            CreateMessageUtility.createMsg("Hero2 fly demo");
            System.out.println("Hero2 fly demo");
            CreateMessageUtility.createMsg("Hero2 wants to move south.");
            System.out.println("Hero2 wants to move south.");
            hero2.makeMove(Direction.SOUTH, gridOfButtons);

            sleepForHalfSecond();

            CreateMessageUtility
                    .createMsg("Hero2 requests flier to fly at different location");
            System.out.println("Hero2 requests flier to fly at different location");
            hero2.requestToFly();
            sleepForHalfSecond();

            CreateMessageUtility.createMsg("Hero2 wants to fly to empty location.");
            System.out.println("Hero2 wants to fly to empty location.");
            try {
                hero2.flyToLocation(gridOfButtons, new TFaceGrid(1, 4));
            } catch (Exception e) {
                System.err.println("Error occurred : " + e.getMessage());
                System.exit(1);
            }
            sleepForHalfSecond();

            // Vader fly moves demo
            CreateMessageUtility.createMsg("Vader moves demo");
            System.out.println("Vader moves demo");
            CreateMessageUtility.createMsg("Vader wants to fly to empty location.");
            System.out.println("Vader wants to fly to empty location.");
            vader.flyToLocation(gridOfButtons, new TFaceGrid(2, 5));
            sleepForHalfSecond();

            hero1.setVaderExit(true);
            CreateMessageUtility
                    .createMsg("Vader wants to fly back to his base location");
            System.out.println("Vader wants to fly back to his base location");
            vader.flyToLocation(gridOfButtons, tVaderLoc);
            sleepForHalfSecond();

            // Rover Demo
            CreateMessageUtility.createMsg("Rover moves demo");
            System.out.println("Rover moves demo");
            CreateMessageUtility.createMsg("Rover1 wants to move to north.");
            System.out.println("Rover1 wants to move to north.");
            rover1.makeInitialMove(Direction.NORTH, gridOfButtons, tRover1Loc);
            sleepForHalfSecond();

            CreateMessageUtility.createMsg("Rover1 wants to move to east.");
            System.out.println("Rover1 wants to move to east.");
            rover1.makeMove(Direction.EAST, gridOfButtons);
            sleepForHalfSecond();

            CreateMessageUtility.createMsg("Rover2 tries to fly demo");
            System.out.println("Rover2 tries to fly demo");
            CreateMessageUtility
                    .createMsg("Rover2 wants to fly to different location");
            System.out.println("Rover2 wants to fly to different location");
            rover2.requestToFly();
            sleepForHalfSecond();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

}
