package com.design.pattern.tetrastar.enums;
/**
 * 
 * @author Akshata, Rachna and Shweta.
 */

public enum RoverTypes {

    HERO("Hero"),
    ROVER("Rover"),
    VADER("Vader");

    private String type;

    RoverTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static RoverTypes getRoverTypeFromTypeString(String typeString) {
        if(typeString != null) {
            for(RoverTypes r : values()) {
                if(r.getType().equalsIgnoreCase(typeString)) {
                    return r;
                }
            }
        }
        return null;
    }

}
