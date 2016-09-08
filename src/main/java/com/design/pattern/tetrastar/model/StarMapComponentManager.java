package com.design.pattern.tetrastar.model;

import java.util.HashMap;

public class StarMapComponentManager {

    static HashMap<Integer, StarMapComponent> starHashMap = new HashMap<>();

    public static void addStarMapComponent(Integer key, StarMapComponent starMapComponent) {
        starHashMap.put(key, starMapComponent);
    }

    public static StarMapComponent getStarMapComponent(Integer key) {
        StarMapComponent map = starHashMap.get(key);
        try {
            return map.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println("Error Occurred : " + e.getMessage());
            System.exit(1);
        }
        return map;
    }

}
