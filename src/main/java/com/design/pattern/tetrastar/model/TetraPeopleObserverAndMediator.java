/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;
/**
 *   @author Akshata, Rachna and Shweta. 
 */

import com.design.pattern.tetrastar.util.CreateMessageUtility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class TetraPeopleObserverAndMediator implements Observer {
 
    public List<TMapBase> mapHomes;

    public List<THeroBase> heroBaseHomes;

    public TVaderBase vaderBaseHome;

    public TetraPeopleObserverAndMediator() {
        mapHomes = new ArrayList<TMapBase>();
        heroBaseHomes = new ArrayList<THeroBase>();
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        PeopleNotify receivedObject = (PeopleNotify) arg1;
        if (receivedObject.notificationType.equals(NotificationType.MAPBASE)) {
            System.out.println("TetraPeopleObserverAndMediator: Mapbase");
            for (int i = 0; i < mapHomes.size(); ++i) {
                TMapBase mapHome = mapHomes.get(i);
                if (mapHome.getGridLocation().getRow() == receivedObject.getBaseLocation().getRow()
                        && mapHome.getGridLocation().getColumn() == receivedObject.getBaseLocation().getColumn()) {
                    try {
                        mapHome.processMap(receivedObject.getPeople());
                    } catch (Exception e) {
                        System.err.println("Error occurred " + e.getMessage());
                    }
                }
            }
        } else if (receivedObject.notificationType.equals(NotificationType.VADERBASE)) {
            System.out.println("TetraPeopleObserverAndMediator: VaderBase");
            String s = "Enters into Vader base";
            CreateMessageUtility.createMsg(s);
            try {
                vaderBaseHome.processMap(receivedObject.getPeople());
            } catch (Exception e) {
                System.err.println("Error occurred " + e.getMessage());
            }
        } else if (receivedObject.notificationType.equals(NotificationType.HEROBASE)) {
            if (receivedObject.getPeople() instanceof TetraHero) {
                System.out.println("Hero with id " + receivedObject.getPeople().getId());
                for (int i = 0; i < heroBaseHomes.size(); ++i) {
                    THeroBase heroBase = heroBaseHomes.get(i);
                    if (heroBase.getLocationId() == receivedObject.people.getId()) {
                        String s = "Hero wants to enter his own base";
                        CreateMessageUtility.createMsg(s);
                        try {
                            heroBase.processMap(receivedObject.getPeople());
                        } catch (Exception e) {
                            System.err.println("Error occurred " + e.getMessage());
                        }
                    }
                }
            }
        }
    }

    public void registerTPeople(TetraPeople tPeople) {
        tPeople.addObserver(this);
    }

    public void registerTHomeBase(Location homeBase) {
        if (homeBase instanceof TMapBase) {
            mapHomes.add((TMapBase) homeBase);
        } else if (homeBase instanceof THeroBase) {
            heroBaseHomes.add((THeroBase) homeBase);
        } else if (homeBase instanceof TVaderBase) {
            vaderBaseHome = (TVaderBase) homeBase;
        }
    }

}
