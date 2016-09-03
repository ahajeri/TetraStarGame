package com.design.pattern.tetrastar.view;

import com.design.pattern.tetrastar.encryption.EncryptionAlgorithm;
import com.design.pattern.tetrastar.encryption.EncryptionAlgorithmFactory;
import com.design.pattern.tetrastar.encryption.SimpleEncryptionAlgorithm;
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

public class Scenario4DisplayScreen extends GridDisplayScreen {

    public Scenario4DisplayScreen() {
        super();
    }

    @Override
    public void setInitialPositions() {
        // Create different home base objects.
        vaderBaseLoc = TVaderBase.getInstance();
        hero1BaseLoc = new THeroBase();
        hero2BaseLoc = new THeroBase();
        mapBaseLoc = new TMapBase();
        //mapBase1Loc = new TMapBase();

        // Create TetraPeople
        TetraPeopleFactory rover = new TetraPeopleFactory();
        // create instances of heroes , vader and rovers.        		
        hero1 = TetraPeopleFactory.createTetraPeople(RoverTypes.HERO, '*');
        hero2 = TetraPeopleFactory.createTetraPeople(RoverTypes.HERO, '#');
        vader = TetraPeopleFactory.createTetraPeople(RoverTypes.VADER, '\0');
        rover1 = TetraPeopleFactory.createTetraPeople(RoverTypes.ROVER, '\0');
        rover2 = TetraPeopleFactory.createTetraPeople(RoverTypes.ROVER, '\0');

        tHero1Loc = new TFaceGrid(2, 2);
        tHero2Loc = new TFaceGrid(4, 2);
        tRover1Loc = new TFaceGrid(5, 0);
        tRover2Loc = new TFaceGrid(6, 4);
        tVaderLoc = new TFaceGrid(3, 3);
        tMapLoc = new TFaceGrid(3, 3);
        //tMap1Loc = new TFaceGrid(3, 6);
        hero1bLoc = new TFaceGrid(0, 0);
        hero2bLoc = new TFaceGrid(6, 6);
        tMapbLoc = new TFaceGrid(2, 6);
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
        tMediator.registerTHomeBase(mapBase1Loc);

        /* Initializing StarMaps */
        /* StarAtlas - Case 1 */
        StarAtlasComposite stAtlas1 = new StarAtlasComposite(idGenerator.nextId(), tMapLoc);
        StarMap stMap1 = new StarMap(idGenerator.nextId(), tMapLoc, "Welcome to tetra Planet:StarMap1");
        StarMap stMap2 = new StarMap(idGenerator.nextId(), tMapLoc, "Welcome to tetra Planet:StarMap2");
        /* Get the unique strategy of hero for encryption */
        EncryptionStrategy strategy = hero1.getEncryptionStrategy();

        /* Using factory method pattern to get Encryption algorithm for an encryption strategy */
        EncryptionAlgorithm encryptionAlgo;
        encryptionAlgo = EncryptionAlgorithmFactory.getEncryptionAlgorithmForStrategy(strategy);
        stAtlas1.addStarMap(stMap1);
        stAtlas1.addStarMap(stMap2);
        stAtlas1.setEncryptionAlgorithm(encryptionAlgo);
        stAtlas1.encrypt(hero1.getId(), new Date(), ((TetraHero)hero1).getSymbol());
        vaderBaseLoc.setStarMapComponent(stAtlas1);

        /* StarMap - Case 2 */
       /* StarMap stMap3 = new StarMap(idGenerator.nextId(), tMap1Loc, "Goto north,then east and there is plenty of gold");
        strategy = hero2.getEncryptionStrategy();
        encryptionAlgo = EncryptionAlgorithmFactory.getEncryptionAlgorithmForStrategy(strategy);
        stMap3.setEncryptionAlgorithm(encryptionAlgo);
        stMap3.encrypt(hero2.getId(), new Date(), ((TetraHero)hero2).getSymbol());
        mapBase1Loc.setStarMapComponent(stMap3);*/

        /* Initializing Flier */
        try {
            Image img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/tFlier.jpg"));
            gridOfButtons[0][6].setIcon(new ImageIcon(img));
            gridOfButtons[0][6].setDisabledIcon(new ImageIcon(img));
        } catch (IOException e) {
            System.err.println("Error occurred " + e.getMessage());
        }

        flyLoc = new TFaceGrid(4, 6);

    }

    @Override
    public void startSimulation() {
        try {
            CreateMessageUtility.createMsg("Vader flies to a empty location");
            vader.flyToLocation(gridOfButtons, new TFaceGrid(0, 4));
            sleepForHalfSecond();

            hero1.setVaderExit(true);

            CreateMessageUtility.createMsg("Request Flier: Hero1");
            hero1.requestToFly();
            sleepForHalfSecond();

            CreateMessageUtility.createMsg("FLY TO MAPBASE LOCATION: Hero1");
            hero1.flyToLocation(gridOfButtons, tMapbLoc);
            sleepForHalfSecond();

        } catch (Exception ex) {
            Logger.getLogger(Scenario4DisplayScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
