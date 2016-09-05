/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;

import com.design.pattern.tetrastar.util.CreateMessageUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 *   @author Akshata, Rachna and Shweta. 
 */
public class TetraPeopleObserverAndMediator implements Observer {
 
    public List<TMapBase> mapHomes;

    public List<THeroBase> heroBaseHomes;

    public TVaderBase vaderBaseHome;
    
    //Visitor for visitor pattern (used to register homebases)
    private RegisterHomeBaseVisitor registerHomeBaseVisitor = new RegisterHomeBaseVisitor(this);

    public TetraPeopleObserverAndMediator() {
        mapHomes = new ArrayList<>();
        heroBaseHomes = new ArrayList<>();
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        PeopleNotify receivedObject = (PeopleNotify) arg1;
        if (NotificationType.MAPBASE.equals(receivedObject.notificationType)) {
            for (int i = 0; i < mapHomes.size(); ++i) {
                TMapBase mapHome = mapHomes.get(i);
                if (mapHome.getGridLocation().getRow() == receivedObject.getBaseLocation().getRow()
                        && mapHome.getGridLocation().getColumn() == receivedObject.getBaseLocation().getColumn()) {
                    try {
                        mapHome.processMap(receivedObject.getPeople());
                    } catch (Exception e) {
                        System.err.println("Error occurred " + e.getMessage());
                        System.exit(1);
                    }
                }
            }
        } else if (NotificationType.VADERBASE.equals(receivedObject.notificationType)) {
            String s = "Enters into Vader base";
            CreateMessageUtility.createMsg(s);
            try {
                vaderBaseHome.processMap(receivedObject.getPeople());
            } catch (Exception e) {
                System.err.println("Error occurred " + e.getMessage());
                System.exit(1);
            }
        } else if (NotificationType.HEROBASE.equals(receivedObject.notificationType)) {
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
                            System.exit(1);
                        }
                    }
                }
            }
        }
    }

    // This is another example of visitor pattern
    public void registerTPeople(TetraPeople tPeople) {
        tPeople.addObserver(this);
    }
    
    // This is visitor pattern to register homebase(mapbase or vaderbase or herobase)
    public void registerTHomeBase(Location homeBase) {
        homeBase.accept(registerHomeBaseVisitor);
    }
    
    protected void registerMapBase(TMapBase mapBase) {
        this.mapHomes.add(mapBase);
    }
    
    protected void registerHeroBase(THeroBase heroBase) {
        this.heroBaseHomes.add(heroBase);
    }
    
    protected void registerVaderBase(TVaderBase vaderBase) {
        this.vaderBaseHome = vaderBase;
    }
    
}
