package com.design.pattern.tetrastar.model;
/**
 *   @author Akshata, Rachna and Shweta. 
 */

import com.design.pattern.tetrastar.enums.PeopleType;
import com.design.pattern.tetrastar.util.CreateMessageUtility;

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
    public void processMap(TetraPeople people) throws Exception {
        // Process request according to different Tetra People.
        if (people instanceof TetraHero) {
            TetraHero thero = (TetraHero) people;
            if (thero.getOldstarMap() == null) {
                boolean mapPresent = starMap.showSignal(this.getGridLocation());
                if (mapPresent) {
                	
                    if (starMap.isEncrypted()) {
                        if (starMap.isEncryptedByMe(people.getId())) {
                            String s = "Hero enters MapBase and map is encrypted by him";
                            CreateMessageUtility.createMsg(s);
                            starMap.decrypt(people.getId());
                            starMap.display();
                        } else {
                            String s = "Hero enters MapBase and map encrypted by other hero";
                            CreateMessageUtility.createMsg(s);
                            starMap.encrypt(people.getId(), null, '\0');
                            starMap.display();
                        }
                    } else {
                        String s = "Hero enters MapBase and there exists original non-encrypted map";
                        CreateMessageUtility.createMsg(s);
                        //starMap.display();
                    }
                } else {
                    String s = "Hero enters MapBase, but there is no map";
                    CreateMessageUtility.createMsg(s);
                    vaderBaselocation = new TFaceGrid(3, 3);
                    thero.setMapToVader(this.getGridLocation());

                    thero.fly(gridOfLocations, vaderBaselocation);
                }
            } else {
                String message = "Hero enters MapBase with encrypted map";
                CreateMessageUtility.createMsg(message);
                starMap = thero.getOldstarMap();
                starMap.setLocation(this.getGridLocation());

                if (thero.getId() == 1) {
                    thero.fly(gridOfLocations, new TFaceGrid(0, 0));
                } else {
                    thero.fly(gridOfLocations, new TFaceGrid(6, 6));
                }
            }
        } else if (people instanceof TetraRover) {
            String message = "Rover cannot enter MapBase";
            CreateMessageUtility.createMsg(message);
        } else if (people instanceof TetraVader) {
            TetraVader tVader = (TetraVader) people;
            TFaceGrid oldLoc, newLoc;
            String message = "Vader enters MapBase to steal map";
            CreateMessageUtility.createMsg(message);

            tVader.setReturnToHomePathStatus(true);

            // Vader flies back to its Vaderbase by backtracking its path.
            oldLoc = getGridLocation();
            newLoc = tVader.extractLastLocationFromPath();
            while (newLoc != null) {
                message = "Vader moving to VaderBase";
                CreateMessageUtility.createMsg(message);
                tVader.flyWithMap(starMap, gridOfLocations, oldLoc, newLoc);
                oldLoc = newLoc;
                newLoc = tVader.extractLastLocationFromPath();
            }
            // Map set to nullObject
            this.starMap = new NullStarMap();
        }
    }

}
