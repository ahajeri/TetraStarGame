package com.design.pattern.tetrastar.model;
/**
 *  @author Akshata, Rachna and Shweta. 
 */

import com.design.pattern.tetrastar.enums.PeopleType;
import com.design.pattern.tetrastar.util.CreateMessageUtility;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class THeroBase extends Location {

    public THeroBase() {
        starMap = new NullStarMap();
    }

    @Override
    public String getImageName() {
        return "heroBase.jpg";
    }

    @Override
    public void processMap(TetraPeople people) throws Exception {
        // Process request according to different Tetra People.
        if (people instanceof TetraHero) {
            TetraHero thero = (TetraHero) people;

            if (thero.getNewstarMap() != null) {
                starMap = thero.getNewstarMap();
                String s = "Hero in HeroBase with Cloned Map";
                CreateMessageUtility.createMsg(s);
            } else {
                String s = "Hero in HeroBase ";
                CreateMessageUtility.createMsg(s);

                Image currentImage = null;
                Image newImage = null;

                try {
                    if(PeopleType.HERO1.equals(thero.getHeroType())) {
                        newImage = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/thero.png"));
                    } else if(PeopleType.HERO2.equals(thero.getHeroType())) {
                        newImage = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/hero2.jpg"));
                    }                    
                } catch (IOException e) {
                    System.err.println("Error occurred " + e.getMessage());
                }

                thero.setIcons(gridOfLocations, currentImage, newImage);
            }
        } else if (people instanceof TetraRover) {
            String message = "Rover cannot enter HeroBase";
            CreateMessageUtility.createMsg(message);
        } else if (people instanceof TetraVader) {
            String message = "Vader cannot enter HeroBase";
            CreateMessageUtility.createMsg(message);
        }
    }
 
}
