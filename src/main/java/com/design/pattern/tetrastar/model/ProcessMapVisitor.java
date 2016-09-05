/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;

/**
 * @author Akshata, Rachna and Shweta.
 *   Concrete Visitor used during processing of map whenever a character reaches a home base
 *   and if map is available at that home base (mapbase / herobase / vaderbase)
 */
public class ProcessMapVisitor implements MapVisitor {

    private final Location homeBase;

    public ProcessMapVisitor(Location homeBase) {
        this.homeBase = homeBase;
    }
    
    @Override
    public void visit(TetraHero tetraHero) {
        homeBase.processMapByHero(tetraHero);
    }

    @Override
    public void visit(TetraVader tetraVader) {
        homeBase.processMapByVader(tetraVader);
    }

    @Override
    public void visit(TetraRover tetraRover) {
        homeBase.processMapByRover(tetraRover);
    }
    
}
