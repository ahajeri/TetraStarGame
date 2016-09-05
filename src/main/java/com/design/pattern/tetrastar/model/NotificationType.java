/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;

/**
 * 
 *  @author Akshata, Rachna and Shweta.
 *   
 * Notification Type enum for sending notification for observer pattern
 */
public enum NotificationType {
    
    HEROBASE("HeroBase"),
    VADERBASE("VaderBase"),
    MAPBASE("MapBase");
    
    private String typeString;

    private NotificationType(String typeString) {
        this.typeString = typeString;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }
    
    public static NotificationType getNotificationTypeUsingString(String type) {
        if(type != null) {
            for(NotificationType n : values()) {
                if(n.getTypeString().equals(type)) {
                    return n;
                }
            }
        }
        return null;
    }
}
