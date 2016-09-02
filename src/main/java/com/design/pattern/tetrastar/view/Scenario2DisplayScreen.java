package com.design.pattern.tetrastar.view;

import java.awt.Image;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.design.pattern.tetrastar.encryption.EncryptionAlgorithm;
import com.design.pattern.tetrastar.encryption.EncryptionAlgorithmFactory;
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

public class Scenario2DisplayScreen extends GridDisplayScreen {

	StarAtlasComposite starAtlas;
	StarMap starMap1;
	StarMap starMap2;
	EncryptionAlgorithm encryptionAlgo;
	
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
        mapBase1Loc = new TMapBase();

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
        tMapLoc = new TFaceGrid(2, 6);
        tMap1Loc = new TFaceGrid(3, 6);
        hero1bLoc = new TFaceGrid(0, 0);
        hero2bLoc = new TFaceGrid(6, 6);
        tMapbLoc = new TFaceGrid(2, 6);
        tMapb1Loc = new TFaceGrid(3, 6);

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
        starAtlas = new StarAtlasComposite(idGenerator.nextId(), tMapLoc);
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
        }

        flyLoc = new TFaceGrid(2, 6);

    }

    @Override
    public void startSimulation() {
    	
    	CreateMessageUtility.createMsg("Creating StarAtlas: With two StarMaps");
    	
    	starAtlas.setEncryptionStatus(false);
    	starAtlas.display();
    	sleepForHalfSecond();
    	
    	CreateMessageUtility.createMsg("Hero1 Request Flier");
        hero1.requestToFly();
        sleepForHalfSecond();

        CreateMessageUtility.createMsg("Hero1 FLY TO StarAtlas MAPBASE LOCATION");
        try {
            hero1.flyToLocation(gridOfButtons, tMapbLoc);
        } catch (Exception e) {
            System.err.println("Error occurred : " + e.getMessage());
        }
        sleepForHalfSecond();

        CreateMessageUtility.createMsg("StarAtlas is encrypted by Hero1");
        starAtlas.encrypt(hero1.getId(), new Date(), ((TetraHero)hero1).getSymbol());
        starAtlas.display();
        sleepForHalfSecond(); 
        
        CreateMessageUtility.createMsg("Hero1 FLY Back TO his old LOCATION");
        try {
            hero1.flyToLocation(gridOfButtons, tHero1Loc);
        } catch (Exception e) {
            System.err.println("Error occurred : " + e.getMessage());
        }
        sleepForHalfSecond();
        
        /*CreateMessageUtility.createMsg("Hero2 Request Flier");
        hero2.requestToFly();
        sleepForHalfSecond();
        
        CreateMessageUtility.createMsg("Hero2 FLY TO StarAtlas MAPBASE LOCATION");
        try {
			hero2.flyToLocation(gridOfButtons, tMapbLoc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        sleepForHalfSecond();*/
    
    }

}
