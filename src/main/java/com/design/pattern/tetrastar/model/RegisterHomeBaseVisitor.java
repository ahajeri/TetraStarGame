/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;

/**
 * @author Akshata, Rachna and Shweta.
 * Concrete Visitor for a Location of a base used to register a map base
 * Example of Visitor pattern
 */
public class RegisterHomeBaseVisitor implements LocationVisitor {
    
    private final TetraPeopleObserverAndMediator tetraPeopleObserverAndMediator;

    public RegisterHomeBaseVisitor(TetraPeopleObserverAndMediator tetraPeopleObserverAndMediator) {
        this.tetraPeopleObserverAndMediator = tetraPeopleObserverAndMediator;
    }

    @Override
    public void visit(THeroBase heroBase) {
        tetraPeopleObserverAndMediator.registerHeroBase(heroBase);
    }

    @Override
    public void visit(TMapBase mapBase) {
        tetraPeopleObserverAndMediator.registerMapBase(mapBase);
    }
    
    @Override
    public void visit(TVaderBase vaderBase) {
        tetraPeopleObserverAndMediator.registerVaderBase(vaderBase);
    }
    
}
