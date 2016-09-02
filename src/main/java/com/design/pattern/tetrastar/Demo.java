/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar;

import com.design.pattern.tetrastar.controller.MainDisplayController;
import com.design.pattern.tetrastar.controller.impl.MainDisplayControllerImpl;

/**
 *
 * @author Rachna Gajre <rgajre@scu.edu>
 */
public class Demo {

    // Controller for MVC pattern
    private static MainDisplayController mainDisplayController;

    public static void main(String[] args) {
        mainDisplayController = new MainDisplayControllerImpl();

        mainDisplayController.startGame();
    }
    
}
