package com.design.pattern.tetrastar.view;
/**
 *   @author Akshata, Rachna and  Shweta. 
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

/**
 * Hero1 moves in all directions
 *
 * @author Rachna Gajre <rgajre@scu.edu>
 */
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
		mapBase1Loc = new TMapBase();

		// create instances of heroes , vader and rovers.
		hero1 = TetraPeopleFactory.createTetraPeople(RoverTypes.HERO, '*');
		hero2 = TetraPeopleFactory.createTetraPeople(RoverTypes.HERO, '#');
		vader = TetraPeopleFactory.createTetraPeople(RoverTypes.VADER, '\0');
		rover1 = TetraPeopleFactory.createTetraPeople(RoverTypes.ROVER, '\0');
		rover2 = TetraPeopleFactory.createTetraPeople(RoverTypes.ROVER, '\0');

		Image img = null;

		tHero1Loc = new TFaceGrid(2, 2);
		tHero2Loc = new TFaceGrid(4, 2);
		tRover1Loc = new TFaceGrid(5, 0);
		tRover2Loc = new TFaceGrid(6, 4);
		tVaderLoc = new TFaceGrid(3, 3);
		tMapLoc = new TFaceGrid(3, 3);
		hero1bLoc = new TFaceGrid(0, 0);
		hero2bLoc = new TFaceGrid(6, 6);
		tMapbLoc = new TFaceGrid(2, 1);
		tMapb1Loc = new TFaceGrid(4, 6);

		// TODO : Star map (see prev code)
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
			System.err.printf("Error occurred " + e.getMessage());
		}

		gridOfButtons[0][6].setIcon(new ImageIcon(img));
		gridOfButtons[0][6].setDisabledIcon(new ImageIcon(img));

		// set the fly location
		flyLoc = new TFaceGrid(4, 6);

		this.setTetraPeopleToLocation();

		// register the mapbase, herobase and vaderbase location to the
		// mediator.
		tPeopleObserver.registerTHomeBase(hero1BaseLoc);
		tPeopleObserver.registerTHomeBase(hero2BaseLoc);
		tPeopleObserver.registerTHomeBase(vaderBaseLoc);
		tPeopleObserver.registerTHomeBase(mapBaseLoc);

		hero1.requestToFly();
	}

	@Override
	public void startSimulation() {
		try {

			// Hero1 moves demo
			CreateMessageUtility
					.createMsg("This Demo shows all inhabits of planet Tetra moves");
			CreateMessageUtility.createMsg("HERO1 Moves North");
			hero1.makeInitialMove(Direction.NORTH, gridOfButtons, tHero1Loc);
			sleepForHalfSecond();

			CreateMessageUtility.createMsg("HERO1 Moves East");
			hero1.makeMove(Direction.EAST, gridOfButtons);
			sleepForHalfSecond();

			CreateMessageUtility.createMsg("HERO1 Moves South");
			hero1.makeMove(Direction.SOUTH, gridOfButtons);
			sleepForHalfSecond();

			// Hero2 fly demo
			CreateMessageUtility.createMsg("HERO2 Fly Demo");
			CreateMessageUtility.createMsg("HERO2 Moves South");
			hero2.makeInitialMove(Direction.SOUTH, gridOfButtons, tHero2Loc);
			sleepForHalfSecond();

			CreateMessageUtility
					.createMsg("Hero2 Request to fly to different location.");
			hero2.requestToFly();
			sleepForHalfSecond();

			CreateMessageUtility.createMsg("FLY TO hero2 Base Location");
			hero2.flyToLocation(gridOfButtons, hero2bLoc);
			sleepForHalfSecond();

			// Vader fly moves demo
			CreateMessageUtility.createMsg("Vader moves Demo");
			CreateMessageUtility.createMsg("Vader flies to a empty location");
			vader.flyToLocation(gridOfButtons, new TFaceGrid(2, 5));
			sleepForHalfSecond();
			hero1.setVaderExit(true);
			CreateMessageUtility
					.createMsg("Vader try to fly back to base location");
			vader.flyToLocation(gridOfButtons, tVaderLoc);
			sleepForHalfSecond();

			// Rover Demo
			CreateMessageUtility.createMsg("Rover Moves Demo");
			CreateMessageUtility.createMsg("Rover1 Moves North");
			rover1.makeInitialMove(Direction.NORTH, gridOfButtons, tRover1Loc);
			sleepForHalfSecond();

			CreateMessageUtility.createMsg("Rover1 Moves East");
			rover1.makeMove(Direction.EAST, gridOfButtons);
			sleepForHalfSecond();

			CreateMessageUtility.createMsg("Rover2 Trying to Fly Demo");
			CreateMessageUtility
					.createMsg("Rover2 wants to fly to different location.");
			rover2.requestToFly();
			sleepForHalfSecond();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

}
