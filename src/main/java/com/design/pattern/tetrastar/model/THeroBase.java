package com.design.pattern.tetrastar.model;

import com.design.pattern.tetrastar.enums.PeopleType;
import com.design.pattern.tetrastar.util.CreateMessageUtility;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author Akshata, Rachna and Shweta. Hero Base
 */
public class THeroBase extends Location {

    public THeroBase() {
        starMap = new NullStarMap();
    }

    @Override
    public String getImageName() {
        return "heroBase.jpg";
    }

    @Override
    public void processMap(TetraPeople people) {
        /**
         *   Process request according to different Tetra People using visitor pattern
         *   and avoid using instanceof
         */
        people.accept(processMapVisitor);
    }
    
    @Override
    public void processMapByHero(TetraHero tHero) {
        if (tHero.getNewstarMap() != null) {
            starMap = tHero.getNewstarMap();
            String s = "Hero in HeroBase with Cloned Map";
            System.out.println("Hero with heroId " + tHero.getId() + " entered HeroBase with Cloned Map.");
            CreateMessageUtility.createMsg(s);
        } else {
            String s = "Hero in HeroBase ";
            System.out.println("Hero with heroId " + tHero.getId() + " is in HeroBase.");
            CreateMessageUtility.createMsg(s);
            Image currentImage = null;
            Image newImage = null;
            try {
                if (PeopleType.HERO1.equals(tHero.getHeroType())) {
                    newImage = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/hero1.png"));
                } else if (PeopleType.HERO2.equals(tHero.getHeroType())) {
                    newImage = ImageIO.read(getClass().getResource("/com/design/pattern/tetrastar/images/hero2.jpg"));
                }
            } catch (IOException e) {
                System.err.println("Error occurred " + e.getMessage());
                System.exit(1);
            }
            tHero.setIcons(gridOfLocations, currentImage, newImage);
        }
    }

    @Override
    public void processMapByRover(TetraRover tRover) {
        String message = "Rover cannot enter HeroBase";
        System.out.println(message);
        CreateMessageUtility.createMsg(message);
    }

    @Override
    public void processMapByVader(TetraVader tVader) {
        String message = "Vader cannot enter HeroBase";
        System.out.println(message);
        CreateMessageUtility.createMsg(message);
    }

    @Override
    public void accept(LocationVisitor locationVisitor) {
        locationVisitor.visit(this);
    }

}
