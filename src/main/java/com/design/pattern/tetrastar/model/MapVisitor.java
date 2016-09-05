/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;

/**
 * @author Akshata, Rachna and Shweta.
 *   Abstract Visitor used during processing of map whenever a character reaches home base (map base or hero base or vader base)
 * and if map is available at that home base (mapbase / herobase / vaderbase)
 */
public interface MapVisitor {
    
    public void visit(TetraHero tetraHero);
    
    public void visit(TetraVader tetraVader);
    
    public void visit(TetraRover tetraRover);
    
}
