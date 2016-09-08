package com.design.pattern.tetrastar.model;

import com.design.pattern.tetrastar.encryption.EncryptionAlgorithm;
import com.design.pattern.tetrastar.encryption.EncryptionAlgorithmFactory;
import com.design.pattern.tetrastar.enums.EncryptionStrategy;
import com.design.pattern.tetrastar.util.CreateMessageUtility;
import java.util.Date;

/**
 * @author Akshata, Rachna and Shweta.
 */
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

    public void setLocationId(int id) {
        this.locationId = id;
    }

    int getLocation() {
        return locationId;
    }

    @Override
    public String getImageName() {
        return "vaderHouse.jpg";
    }

    @Override
    public void processMap(TetraPeople people) {
        /**
         * Process request according to different Tetra People using visitor
         * pattern and avoid using instanceof
         */
        people.accept(processMapVisitor);
    }

    @Override
    public void processMapByHero(TetraHero tHero) {
        boolean mapPresent = starMap.showSignal(this.getGridLocation());
        if (mapPresent) {
            String s = "Map Present in VaderBase";
            System.out.println("Hero with heroId " + tHero.getId() + " finds out that map is present in VaderBase");
            CreateMessageUtility.createMsg(s);
            StarMapComponent newMap = null;
            // Clone the map
            newMap = StarMapComponentManager.getStarMapComponent(starMap.getStarComponentID());
            System.out.println("Hero with heroId " + tHero.getId() + " clones the map that is present in VaderBase");

            // Get encryption strategy for hero
            EncryptionStrategy encryptionStrategy = tHero.getEncryptionStrategy();
            // Use Factory Method pattern to get Encryption algorithm to be used
            EncryptionAlgorithm encryptionAlgo = EncryptionAlgorithmFactory.getEncryptionAlgorithmForStrategy(encryptionStrategy);
            starMap.setEncryptionAlgorithm(encryptionAlgo);
            starMap.encrypt(tHero.getId(), new Date(), tHero.getSymbol());
            System.out.println("Hero with heroId " + tHero.getId() + " encrypts the cloned copy of map.");
            // Fly to base with map
            System.out.println("Hero flies back with map to mapbase to restore the map's original copy.");
            tHero.flyWithMap(starMap, newMap, gridOfLocations, this.getGridLocation(), tHero.getMapToVader());
        }
    }

    @Override
    public void processMapByRover(TetraRover tRover) {
        String message = "Rover cannot enter VaderBase";
        CreateMessageUtility.createMsg(message);
    }

    @Override
    public void processMapByVader(TetraVader tVader) {
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

    @Override
    public void accept(LocationVisitor locationVisitor) {
        locationVisitor.visit(this);
    }

}
