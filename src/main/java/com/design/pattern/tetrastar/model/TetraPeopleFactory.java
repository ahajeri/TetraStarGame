/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;
/**
 *   @author Akshata, Rachna and  Shweta. 
 */

import com.design.pattern.tetrastar.enums.RoverTypes;


public class TetraPeopleFactory {
    
    public static TetraPeople createTetraPeople(RoverTypes roverType, char symbol) {
        switch(roverType) {
            case HERO:
                return new TetraHero(symbol);
            case ROVER:
                return new TetraRover();
            case VADER:
                return TetraVader.getInstance();
            default:
                throw new AssertionError("Please pass correct rover type.");
            
        }
    }
    
}
