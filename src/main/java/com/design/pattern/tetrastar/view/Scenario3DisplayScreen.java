package com.design.pattern.tetrastar.view;
/**
 *   @author Akshata, Rachna and  Shweta. 
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

public class Scenario3DisplayScreen extends GridDisplayScreen {

    public Scenario3DisplayScreen() {
        super();
    }

    @Override
    public void setInitialPositions() {
        // Create the different base location objects.
        vaderBaseLoc = TVaderBase.getInstance();
        hero1BaseLoc = new THeroBase();
        hero2BaseLoc = new THeroBase();
        mapBaseLoc = new TMapBase();
        //mapBase1Loc = new TMapBase();

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
        tMapLoc = new TFaceGrid(2, 6);
        hero1bLoc = new TFaceGrid(0, 0);
        hero2bLoc = new TFaceGrid(6, 6);
        tMapbLoc = new TFaceGrid(2, 6);
        //tMapb1Loc = new TFaceGrid(4, 6);

        this.setTetraPeopleToLocation();

        StarAtlasComposite stAtlas1;
        stAtlas1 = new StarAtlasComposite(10, tMapLoc);
        StarMap stMap1 = new StarMap(11, tMapLoc, "Welcome to tetra Planet");
        /* Get the unique strategy of hero for encryption */
        EncryptionStrategy strategy = hero1.getEncryptionStrategy();

        /* Using factory method pattern to get Encryption algorithm for an encryption strategy */
        EncryptionAlgorithm encryptionAlgo;
        try {
            encryptionAlgo = EncryptionAlgorithmFactory.getEncryptionAlgorithmForStrategy(strategy);
            stAtlas1.addStarMap(stMap1);
            stAtlas1.setEncryptionAlgorithm(encryptionAlgo);
            stAtlas1.encrypt(hero1.getId(), new Date(), ((TetraHero) hero1).getSymbol());
        } catch (Exception e) {
            System.err.println("Error occurred " + e.getMessage());
        }

        mapBaseLoc.setStarMapComponent(stAtlas1);

        TetraPeopleObserverAndMediator tMediator = new TetraPeopleObserverAndMediator();

        // Register the tetra people to mediator
        tMediator.registerTPeople(hero1);
        tMediator.registerTPeople(hero2);
        tMediator.registerTPeople(vader);
        tMediator.registerTPeople(rover1);
        tMediator.registerTPeople(rover2);

        try {
            img = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/tFlier.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        gridOfButtons[0][6].setIcon(new ImageIcon(img));
        gridOfButtons[0][6].setDisabledIcon(new ImageIcon(img));

        flyLoc = new TFaceGrid(4, 6);

        // Register the locations to the mediator
        tMediator.registerTHomeBase(hero1BaseLoc);
        tMediator.registerTHomeBase(hero2BaseLoc);
        tMediator.registerTHomeBase(vaderBaseLoc);
        tMediator.registerTHomeBase(mapBaseLoc);

    }

    @Override
    public void startSimulation() {}
}
