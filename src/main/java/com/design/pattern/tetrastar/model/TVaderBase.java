package com.design.pattern.tetrastar.model;
/**
 *   @author Akshata, Rachna and Shweta. 
 */

import com.design.pattern.tetrastar.encryption.EncryptionAlgorithm;
import com.design.pattern.tetrastar.encryption.EncryptionAlgorithmFactory;
import com.design.pattern.tetrastar.enums.EncryptionStrategy;
import com.design.pattern.tetrastar.util.CreateMessageUtility;
import java.util.Date;

/**
 * Singleton pattern as there can be only one instance of VaderBase
 */
public class TVaderBase extends Location {

    private static TVaderBase INSTANCE = null;

    private TVaderBase() {
        starMap = new NullStarMap();
    }

    public static TVaderBase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TVaderBase();
        }
        return INSTANCE;
    }

    @Override
    public String getImageName() {
        return "vaderHouse.jpg";
    }

    @Override
    public void processMap(TetraPeople people) throws Exception {
        // Process request according to different Tetra People.
        if (people instanceof TetraHero) {
            TetraHero tHero = (TetraHero) people;
            boolean mapPresent = starMap.showSignal(this.getGridLocation());
            if (mapPresent) {
                String s = "Map Present in VaderBase";
                CreateMessageUtility.createMsg(s);
                StarMapComponent newMap = (StarMapComponent) starMap.clone();
                // Get encryption strategy for hero
                EncryptionStrategy encryptionStrategy = tHero.getEncryptionStrategy();

                // Use Factory Method pattern to get Encryption algorithm to be used
                EncryptionAlgorithm encryptionAlgo = EncryptionAlgorithmFactory.getEncryptionAlgorithmForStrategy(encryptionStrategy);

                starMap.setEncryptionAlgorithm(encryptionAlgo);
                starMap.encrypt(tHero.getId(), new Date(), tHero.getSymbol());

                // Fly to base with map
                tHero.flyWithMap(starMap, newMap, gridOfLocations, this.getGridLocation(), tHero.getMapToVader());
            }
        } else if (people instanceof TetraRover) {
            String message = "Rover cannot enter VaderBase";
            CreateMessageUtility.createMsg(message);
        } else if (people instanceof TetraVader) {
            TetraVader tVader = (TetraVader) people;
            if (tVader.getStarMap() != null) {
                String message = "Vader enters its VaderBase with Map";
                CreateMessageUtility.createMsg(message);
                this.starMap = tVader.getStarMap();
            } else {
                String message = "Vader enters its VaderBase";
                CreateMessageUtility.createMsg(message);
                tVader.reInitPath();
            }
        }
    }

}
