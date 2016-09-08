package com.design.pattern.tetrastar.model;

import com.design.pattern.tetrastar.enums.PeopleType;
import com.design.pattern.tetrastar.util.CreateMessageUtility;

/**
 * @author Akshata, Rachna and Shweta. Map Base
 */
public class TMapBase extends Location {

    private TFaceGrid vaderBaselocation;

    public TMapBase() {
        starMap = new NullStarMap();
    }

    @Override
    public String getImageName() {
        return "mapBase.jpg";
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
        if (tHero.getOldstarMap() == null) {
            boolean mapPresent = starMap.showSignal(this.getGridLocation());
            if (mapPresent) {
                System.out.println("StarAtlas is present at " + "[" + this.gridLocation.getRow() + "," + this.gridLocation.getColumn() + "]");
                if (starMap.isEncrypted()) {
                    if (starMap.ckhEncryptedBy(tHero.getId())) {
                        String s = "Hero enters MapBase and map is encrypted by him";
                        System.out.println("Hero with heroId " + tHero.getId() + " enters MapBase and map is encrypted by himself previously.");
                        CreateMessageUtility.createMsg(s);
                        starMap.decrypt(tHero.getId());
                        starMap.display();
                    } else {
                        String s = "Hero enters MapBase and map encrypted by another hero";
                        System.out.println("Hero with heroId " + tHero.getId() + " enters MapBase and map is encrypted by other hero previously.");
                        CreateMessageUtility.createMsg(s);
                        starMap.encrypt(tHero.getId(), null, '\0');
                        starMap.display();
                    }
                } else {
                    String s = "Hero enters MapBase and there exists original non encrypted map";
                    System.out.println("Hero with heroId " + tHero.getId() + " enters MapBase and non-encrypted map is found in mapbase.");
                    CreateMessageUtility.createMsg(s);
                }
            } else {
                String s = "Hero enters MapBase but there is no map";
                System.out.println("Hero with heroId " + tHero.getId() + " enters MapBase but there is no map in mapbase.");
                System.out.println("Hero with heroId " + tHero.getId() + " flies to VaderBase location to check if map is there.");
                CreateMessageUtility.createMsg(s);
                vaderBaselocation = new TFaceGrid(3, 3);
                tHero.setMapToVader(this.getGridLocation());
                try {
                    tHero.flyToLocation(gridOfLocations, vaderBaselocation);
                } catch (Exception e) {
                    System.err.println("Error occurred " + e.getMessage());
                    System.exit(1);
                }
            }
        } else {
            String message = "Hero enters MapBase with encrypted map";
            CreateMessageUtility.createMsg(message);
            System.out.println("Hero with heroId " + tHero.getId() + " enters MapBase with encrypted map.");
            starMap.setEncryptionStatus(true);
            boolean enc = starMap.isEncrypted();
            if (enc == true) {
                int restoreCount = starMap.getRestorationCounter();
                System.out.println("\n StarAtlas was already encrypted before Vader stole it\n");
                System.out.println("Therefore, Hero1 increments restoration counter \n");
                System.out.println("Old Restoration counter:" + restoreCount);
                restoreCount = restoreCount + 1;
                starMap.setRestorationCounter(restoreCount);
                System.out.println("New Restoration counter:" + starMap.getRestorationCounter() + " \n");
            }
            starMap = tHero.getOldstarMap();
            starMap.setLocation(this.getGridLocation());
            try {
                if (PeopleType.HERO1.equals(tHero.getHeroType())) {
                    tHero.flyToLocation(gridOfLocations, new TFaceGrid(6, 6));
                } else if (PeopleType.HERO2.equals(tHero.getHeroType())) {
                    tHero.flyToLocation(gridOfLocations, new TFaceGrid(0, 0));
                }
            } catch (Exception e) {
                System.err.println("Error occurred " + e.getMessage());
                System.exit(1);
            }
        }
    }

    @Override
    public void processMapByRover(TetraRover tRover) {
        String message = "Rover cannot enter MapBase";
        System.out.println(message);
        CreateMessageUtility.createMsg(message);
    }

    @Override
    public void processMapByVader(TetraVader tVader) {
        TFaceGrid oldLoc, newLoc;
        String message = "Vader enters MapBase to steal map";
        System.out.println(message);
        CreateMessageUtility.createMsg(message);
        tVader.setReturnToHomePathStatus(true);
        // Vader flies back to its Vaderbase by backtracking its path.
        oldLoc = getGridLocation();
        newLoc = tVader.extractLastLocationFromPath();
        while (newLoc != null) {
            message = "Vader flying back to VaderBase with stolen map by backtracking";
            CreateMessageUtility.createMsg(message);
            tVader.flyWithMap(starMap, gridOfLocations, oldLoc, newLoc);
            oldLoc = newLoc;
            newLoc = tVader.extractLastLocationFromPath();
        }
        // Map set to nullObject
        this.starMap = new NullStarMap();
    }

    @Override
    public void accept(LocationVisitor locationVisitor) {
        locationVisitor.visit(this);
    }

}
