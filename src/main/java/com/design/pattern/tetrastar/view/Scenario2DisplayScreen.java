package com.design.pattern.tetrastar.view;

/**
 * @author Akshata, Rachna and Shweta.
 */

import com.design.pattern.tetrastar.encryption.EncryptionAlgorithm;
import com.design.pattern.tetrastar.encryption.EncryptionAlgorithmFactory;
import com.design.pattern.tetrastar.enums.Direction;
import com.design.pattern.tetrastar.enums.EncryptionStrategy;
import com.design.pattern.tetrastar.enums.RoverTypes;
import com.design.pattern.tetrastar.model.StarAtlasComposite;
import com.design.pattern.tetrastar.model.StarMap;
import com.design.pattern.tetrastar.model.TFaceGrid;
import com.design.pattern.tetrastar.model.THeroBase;
import com.design.pattern.tetrastar.model.TMapBase;
import com.design.pattern.tetrastar.model.TVaderBase;
import com.design.pattern.tetrastar.model.TetraHero;
import com.design.pattern.tetrastar.model.TetraPeopleFactory;
import com.design.pattern.tetrastar.model.TetraPeopleObserverAndMediator;
import com.design.pattern.tetrastar.util.CreateMessageUtility;
import java.awt.Image;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Scenario2DisplayScreen extends GridDisplayScreen {

    // TODO : Should we move this to GridDisplayScreen abstract class ?
    private StarAtlasComposite starAtlas;
    private StarMap starMap1;
    private StarMap starMap2;
    private EncryptionAlgorithm encryptionAlgo;

    public Scenario2DisplayScreen() {
        super();
    }

    @Override
    public void setInitialPositions() {
        // Create different home base objects.
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

        tHero1Loc = new TFaceGrid(2, 1);
        tHero2Loc = new TFaceGrid(5, 4);
        tRover1Loc = new TFaceGrid(0, 2);
        tRover2Loc = new TFaceGrid(6, 2);
        tVaderLoc = new TFaceGrid(3, 3);
        hero1bLoc = new TFaceGrid(0, 0);
        hero2bLoc = new TFaceGrid(6, 6);
        tMapLoc = new TFaceGrid(3, 3);
        tMapbLoc = new TFaceGrid(2, 6);

        this.setTetraPeopleToLocation();

        /* Initializing Mediator */
        TetraPeopleObserverAndMediator tMediator = new TetraPeopleObserverAndMediator();

        tMediator.registerTPeople(hero1);
        tMediator.registerTPeople(hero2);
        tMediator.registerTPeople(vader);
        tMediator.registerTPeople(rover1);
        tMediator.registerTPeople(rover2);
        tMediator.registerTHomeBase(hero1BaseLoc);
        tMediator.registerTHomeBase(hero2BaseLoc);
        tMediator.registerTHomeBase(vaderBaseLoc);
        tMediator.registerTHomeBase(mapBaseLoc);

        /* Initializing StarMaps */
        /* StarAtlas - Case 1 */
        starAtlas = new StarAtlasComposite(idGenerator.nextId(), tMapbLoc);
        starMap1 = new StarMap(idGenerator.nextId(), tMapLoc, "Direction to planet Earth!!");
        starMap2 = new StarMap(idGenerator.nextId(), tMapLoc, "Direction to planet Mars!!");
        /* Get the unique strategy of hero for encryption */

        EncryptionStrategy strategy = hero1.getEncryptionStrategy();

        /* Using factory method pattern to get Encryption algorithm for an encryption strategy */
        encryptionAlgo = EncryptionAlgorithmFactory.getEncryptionAlgorithmForStrategy(strategy);
        starAtlas.addStarMap(starMap1);
        starAtlas.addStarMap(starMap2);
        starAtlas.setEncryptionAlgorithm(encryptionAlgo);
        //starAtlas.encrypt(hero1.getId(), new Date(), ((TetraHero)hero1).getSymbol());
        mapBaseLoc.setStarMapComponent(starAtlas);

        /* Initializing Flier */
        try {
            Image img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/tFlier.jpg"));
            gridOfButtons[0][6].setIcon(new ImageIcon(img));
            gridOfButtons[0][6].setDisabledIcon(new ImageIcon(img));
        } catch (IOException e) {
            System.err.println("Error occurred " + e.getMessage());
            System.exit(1);
        }

        flyLoc = new TFaceGrid(2, 6);

    }

    @Override
    public void startSimulation() {

        CreateMessageUtility.createMsg("STARMAP Demo");
        System.out.println("STARMAP Demo \n");

        CreateMessageUtility.createMsg("Creating StarAtlas: With two StarMaps");
        System.out.println("Creating StarAtlas: With two StarMaps \n");

        starAtlas.setEncryptionStatus(false);
        starAtlas.display();
        sleepForHalfSecond();

        CreateMessageUtility.createMsg("Hero1 reqests a flier");
        System.out.println("Hero1 reqests a flier \n");
        hero1.requestToFly();
        sleepForHalfSecond();

        CreateMessageUtility.createMsg("Hero1 wants to fly to StarAtlas MapBase location");
        System.out.println("Hero1 wants to fly to StarAtlas MapBase location \n");
        try {
            hero1.flyToLocation(gridOfButtons, tMapbLoc);
        } catch (Exception e) {
            System.err.println("Error occurred : " + e.getMessage());
            System.exit(1);
        }
        sleepForHalfSecond();

        CreateMessageUtility.createMsg("Hero1 wants to encrypt StarAtlas!!");
        System.out.println("Hero1 wants to encrypt StarAtlas!! \n");
        starAtlas.encrypt(hero1.getId(), new Date(), ((TetraHero) hero1).getSymbol());
        starAtlas.display();
        sleepForHalfSecond();

        CreateMessageUtility.createMsg("Hero1 wants to fly back to old location");
        System.out.println("Hero1 wants to fly back to old location \n");
        try {
            hero1.flyToLocation(gridOfButtons, tHero1Loc);
        } catch (Exception e) {
            System.err.println("Error occurred : " + e.getMessage());
            System.exit(1);
        }
        sleepForHalfSecond();

        CreateMessageUtility.createMsg("Vader Demo");
        System.out.println("Vader Demo \n");
        CreateMessageUtility.createMsg("Vader wants to fly to north.");
        System.out.println("Vader wants to fly to north. \n");
        try {
            vader.flyToLocation(gridOfButtons, new TFaceGrid(1, 2));
        } catch (Exception ex) {
            Logger.getLogger(Scenario2DisplayScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        sleepForHalfSecond();

        CreateMessageUtility.createMsg("Vader wants to fly to east.");
        System.out.println("Vader wants to fly to east. \n");
        vader.makeMove(Direction.EAST, gridOfButtons);
        sleepForHalfSecond();

        CreateMessageUtility.createMsg("Vader wants to fly to map base");
        System.out.println("Vader wants to fly to map base \n");
        try {
            vader.flyToLocation(gridOfButtons, tMapbLoc);
        } catch (Exception ex) {
            Logger.getLogger(Scenario2DisplayScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        sleepForHalfSecond();

        
        //   Reinitialize again
        
        // Create different home base objects.
        vaderBaseLoc = TVaderBase.getInstance();
        hero1BaseLoc = new THeroBase();
        hero2BaseLoc = new THeroBase();
        mapBaseLoc = new TMapBase();

        // Create TetraPeople
        TetraPeopleFactory rover = new TetraPeopleFactory();
        // create instances of heroes , vader and rovers.        		
        hero1 = TetraPeopleFactory.createTetraPeople(RoverTypes.HERO, '*');
        hero2 = TetraPeopleFactory.createTetraPeople(RoverTypes.HERO, '#');
        vader = TetraPeopleFactory.createTetraPeople(RoverTypes.VADER, '\0');
        rover1 = TetraPeopleFactory.createTetraPeople(RoverTypes.ROVER, '\0');
        rover2 = TetraPeopleFactory.createTetraPeople(RoverTypes.ROVER, '\0');

        //tMapb1Loc = new TFaceGrid(3, 6);
        this.setTetraPeopleToLocation();

        /* Initializing Mediator */
        TetraPeopleObserverAndMediator tMediator = new TetraPeopleObserverAndMediator();

        tMediator.registerTPeople(hero1);
        tMediator.registerTPeople(hero2);
        tMediator.registerTPeople(vader);
        tMediator.registerTPeople(rover1);
        tMediator.registerTPeople(rover2);
        tMediator.registerTHomeBase(hero1BaseLoc);
        tMediator.registerTHomeBase(hero2BaseLoc);
        tMediator.registerTHomeBase(vaderBaseLoc);
        tMediator.registerTHomeBase(mapBaseLoc);

        // Initializing StarMaps 
        // StarAtlas - Case 1 
        StarAtlasComposite stAtlas1 = new StarAtlasComposite(idGenerator.nextId(), tMapLoc);
        StarMap stMap1 = new StarMap(idGenerator.nextId(), tMapLoc, "Direction to planet Earth!!");
        StarMap stMap2 = new StarMap(idGenerator.nextId(), tMapLoc, "Direction to planet Mars!!");
        // Get the unique strategy of hero for encryption 
        EncryptionStrategy strategy = hero1.getEncryptionStrategy();

        //  Using factory method pattern to get Encryption algorithm for an encryption strategy 
        encryptionAlgo = EncryptionAlgorithmFactory.getEncryptionAlgorithmForStrategy(strategy);
        stAtlas1.addStarMap(stMap1);
        stAtlas1.addStarMap(stMap2);
        stAtlas1.setEncryptionAlgorithm(encryptionAlgo);
        stAtlas1.encrypt(hero1.getId(), new Date(), ((TetraHero) hero1).getSymbol());
        vaderBaseLoc.setStarMapComponent(stAtlas1);

        CreateMessageUtility.createMsg("Hero demo");
        System.out.println("Hero demo \n");

        CreateMessageUtility.createMsg("Vader wants to fly to empty location");
        System.out.println("Vader wants to fly to empty location \n");
        try {
            vader.flyToLocation(gridOfButtons, new TFaceGrid(0, 4));
            hero1.setVaderExit(true);
        } catch (Exception e) {
            System.err.println("Error occurred " + e.getMessage());
            System.exit(1);
        }
        sleepForHalfSecond();

        CreateMessageUtility.createMsg("Hero1 wants to request a flier");
        System.out.println("Hero1 wants to request a flier \n");
        hero1.requestToFly();
        sleepForHalfSecond();

        CreateMessageUtility.createMsg("Hero1 wants to fly to StarAtlas MapBase location");
        System.out.println("Hero1 wants to fly to StarAtlas MapBase location  \n");
        try {

            hero1.flyToLocation(gridOfButtons, tMapbLoc);
        } catch (Exception e) {
            System.err.println("Error occurred : " + e.getMessage());
            System.exit(1);
        }
        sleepForHalfSecond();

    }

}
