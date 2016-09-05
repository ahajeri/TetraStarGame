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
 * Notification to send to observers when something changes for TetraPeople
 */
public class PeopleNotify {
    
    public TetraPeople people;
    
    public TFaceGrid baseLocation;
    
    public NotificationType notificationType;

    public PeopleNotify() {
    }

    public PeopleNotify(TetraPeople people, TFaceGrid baseLocation, NotificationType notificationType) {
        this.people = people;
        this.baseLocation = baseLocation;
        this.notificationType = notificationType;
    }

    public TetraPeople getPeople() {
        return people;
    }

    public void setPeople(TetraPeople people) {
        this.people = people;
    }

    public TFaceGrid getBaseLocation() {
        return baseLocation;
    }

    public void setBaseLocation(TFaceGrid baseLocation) {
        this.baseLocation = baseLocation;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }
    
}
