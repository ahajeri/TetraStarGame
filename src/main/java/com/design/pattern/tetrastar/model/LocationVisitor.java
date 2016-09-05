/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;


/**
 * @author Akshata, Rachna and Shweta.
 * 
 */
public interface LocationVisitor {
    
    public void visit(THeroBase heroBase);
    
    public void visit(TMapBase mapBase);
    
    public void visit(TVaderBase vaderBase);
    
}
