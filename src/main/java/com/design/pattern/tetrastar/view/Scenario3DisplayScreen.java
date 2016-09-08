package com.design.pattern.tetrastar.view;

import com.design.pattern.tetrastar.enums.Direction;
import com.design.pattern.tetrastar.enums.RoverTypes;
import com.design.pattern.tetrastar.model.TFaceGrid;
import com.design.pattern.tetrastar.model.THeroBase;
import com.design.pattern.tetrastar.model.TMapBase;
import com.design.pattern.tetrastar.model.TVaderBase;
import com.design.pattern.tetrastar.model.TetraPeopleFactory;
import com.design.pattern.tetrastar.model.TetraPeopleObserverAndMediator;
import com.design.pattern.tetrastar.model.TetraRover;
import com.design.pattern.tetrastar.model.TetraRoverCareTaker;
import com.design.pattern.tetrastar.util.CreateMessageUtility;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Scenario3DisplayScreen extends GridDisplayScreen {

    private static final Logger logger = Logger.getLogger(Scenario1DisplayScreen.class.getName());

    public Scenario3DisplayScreen() {
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
            // Rover Demo
            CreateMessageUtility.createMsg("Rover moves demo");
            System.out.println("Rover moves demo");

            TetraRoverCareTaker careTaker = new TetraRoverCareTaker();

            CreateMessageUtility.createMsg("Rover1 wants to move to east.");
            System.out.println("Rover1 wants to move to east.");
            rover1.setCurrentLocation(tRover1Loc);
            careTaker.addMemento(((TetraRover) rover1).backupLastPosition());
            rover1.makeMove(Direction.EAST, gridOfButtons);
            sleepForHalfSecond();

            careTaker.addMemento(((TetraRover) rover1).backupLastPosition());
            CreateMessageUtility.createMsg("Rover1 wants to move to east.");
            System.out.println("Rover1 wants to move to east.");
            rover1.makeMove(Direction.EAST, gridOfButtons);
            sleepForHalfSecond();

            careTaker.addMemento(((TetraRover) rover1).backupLastPosition());
            CreateMessageUtility.createMsg("Rover1 wants to move to south.");
            System.out.println("Rover1 wants to move to south.");
            rover1.makeMove(Direction.SOUTH, gridOfButtons);
            sleepForHalfSecond();

            careTaker.addMemento(((TetraRover) rover1).backupLastPosition());
            CreateMessageUtility.createMsg("Rover1 wants to move to south.");
            System.out.println("Rover1 wants to move to south.");
            rover1.makeMove(Direction.SOUTH, gridOfButtons);
            sleepForHalfSecond();

            careTaker.addMemento(((TetraRover) rover1).backupLastPosition());
            CreateMessageUtility.createMsg("Rover1 wants to move to east.");
            System.out.println("Rover1 wants to move to east.");
            rover1.makeMove(Direction.EAST, gridOfButtons);
            sleepForHalfSecond();

            careTaker.addMemento(((TetraRover) rover1).backupLastPosition());
            CreateMessageUtility.createMsg("Rover1 wants to move to east.");
            System.out.println("Rover1 wants to move to east.");
            rover1.makeMove(Direction.EAST, gridOfButtons);
            sleepForHalfSecond();

            CreateMessageUtility.createMsg("Rover1 wants undo last move to east.");
            System.out.println("Rover1 wants undo last move to east.");
            ((TetraRover) rover1).restoreLastPosition(careTaker.getMemento(), gridOfButtons);
            sleepForHalfSecond();

            CreateMessageUtility.createMsg("Rover1 wants undo last move to east.");
            System.out.println("Rover1 wants undo last move to east.");
            ((TetraRover) rover1).restoreLastPosition(careTaker.getMemento(), gridOfButtons);
            sleepForHalfSecond();

            CreateMessageUtility.createMsg("Rover1 wants undo last move to south.");
            System.out.println("Rover1 wants undo last move to south.");
            ((TetraRover) rover1).restoreLastPosition(careTaker.getMemento(), gridOfButtons);
            sleepForHalfSecond();

            CreateMessageUtility.createMsg("Rover1 wants undo last move to south.");
            System.out.println("Rover1 wants undo last move to south.");
            ((TetraRover) rover1).restoreLastPosition(careTaker.getMemento(), gridOfButtons);
            sleepForHalfSecond();

            CreateMessageUtility.createMsg("Rover1 wants undo last move to east.");
            System.out.println("Rover1 wants undo last move to east.");
            ((TetraRover) rover1).restoreLastPosition(careTaker.getMemento(), gridOfButtons);
            sleepForHalfSecond();

            CreateMessageUtility.createMsg("Rover1 wants undo last move to east.");
            System.out.println("Rover1 wants undo last move to east.");
            ((TetraRover) rover1).restoreLastPosition(careTaker.getMemento(), gridOfButtons);
            sleepForHalfSecond();

            CreateMessageUtility.createMsg("Rover1 is now back to initial position at the start of demo.");

        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}