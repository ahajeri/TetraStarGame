package com.design.pattern.tetrastar.enums;
/**
 * 
 * @author Akshata, Rachna and Shweta.
 */

public enum Direction {

    NORTH("North"),
    SOUTH("South"),
    EAST("East"),
    WEST("West");

    private String directionLabel;

    private Direction(String directionLabel) {
        this.directionLabel = directionLabel;
    }

    public String getDirectionLabel() {
        return directionLabel;
    }

    public void setDirectionLabel(String directionLabel) {
        this.directionLabel = directionLabel;
    }

    public static Direction getDirectionFromLabel(String label) {
        if(label != null) {
            for(Direction d : values()) {
                if(d.getDirectionLabel().equalsIgnoreCase(label)) {
                    return d;
                }
            }
        }
        return null;
    }

}
