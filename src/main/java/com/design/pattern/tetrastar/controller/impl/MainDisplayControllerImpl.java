/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.design.pattern.tetrastar.controller.impl;
/**
 * @author Akshata, Rachna and Shweta. 
 */

import com.design.pattern.tetrastar.controller.MainDisplayController;
import com.design.pattern.tetrastar.enums.ScenarioEnum;
import com.design.pattern.tetrastar.view.CommandAndGridView;
import com.design.pattern.tetrastar.view.GridDisplayScreen;
import com.design.pattern.tetrastar.view.GridDisplayScreenFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainDisplayControllerImpl implements MainDisplayController {

    // View for MVC pattern
    private CommandAndGridView commandAndGridView;

    // Application frame
    private JFrame applicationFrame;

    public MainDisplayControllerImpl() {
    }
    
    private void initializeGameUi() {
        // Create a view
        commandAndGridView = new CommandAndGridView();

        //Display the window.
        this.applicationFrame = new JFrame("Tetra Star Game Simulation");
        this.applicationFrame.pack();
        this.applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        this.applicationFrame.getContentPane().add(commandAndGridView.getConsolePanel(), BorderLayout.WEST);
        this.applicationFrame.getContentPane().add(commandAndGridView.getGridDisplayScreen(), BorderLayout.CENTER);      
        this.applicationFrame.getContentPane().add(commandAndGridView.getSidePanel(), BorderLayout.EAST);

        this.applicationFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.applicationFrame.setVisible(true);

        /**
         *  Listener for Dropdown
         */
        commandAndGridView.setCurrentScenario(null);
        commandAndGridView.getScenariosDropDown().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox scenarioSelected = (JComboBox) e.getSource();
                String scenarioName = (String) scenarioSelected.getSelectedItem();
                if (scenarioName != null) {
                    commandAndGridView.setCurrentScenario(ScenarioEnum.getScenarioFromName(scenarioName));
                } else {
                    commandAndGridView.setCurrentScenario(null);
                }
            }
        });

        /**
         *   Listener for Reset Screen button
         */
        commandAndGridView.getResetScreen().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Generate a default game display screen (by passing null to factory method)
                commandAndGridView.setGridDisplayScreen(GridDisplayScreenFactory.getGridDisplayScreenForScenario(null));
                
                commandAndGridView.setConsolePanel(commandAndGridView.resetConsolePanel());
                
                applicationFrame.getContentPane().add(commandAndGridView.getConsolePanel(),BorderLayout.WEST);
                applicationFrame.getContentPane().add(commandAndGridView.getGridDisplayScreen(),BorderLayout.CENTER);
                applicationFrame.setVisible(true);                
            }
        });

        /**
         *  Listener for Set screen button that sets screen UI according to scenarios selected
         */
        commandAndGridView.getSetScreen().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScenarioEnum currScenario = commandAndGridView.getCurrentScenario();
                commandAndGridView.getScenarioDescription().setText((currScenario == null) ? "Scenario" : currScenario.getScenarioDescription());

                // Get Grid Display Screen based on scenario
                GridDisplayScreen gridDisplayScreen = GridDisplayScreenFactory.getGridDisplayScreenForScenario(currScenario);
                // Initialize Grid Display Screen
                gridDisplayScreen.setInitialPositions();
                
                commandAndGridView.setGridDisplayScreen(gridDisplayScreen);

                applicationFrame.getContentPane().add(commandAndGridView.getGridDisplayScreen(),BorderLayout.CENTER);
                applicationFrame.setVisible(true);
            }
        });

        /**
         *    Listener for start simulation button to start simulation scenario selected
         */
        commandAndGridView.getStartSimulation().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(commandAndGridView.getCurrentScenario() != null) {
                    ScenarioEnum currScenario = commandAndGridView.getCurrentScenario();
                    GridDisplayScreen gridDisplayScreen = commandAndGridView.getGridDisplayScreen();
                    switch (currScenario) {
                            
                        /**
                         *  Use Command pattern to invoke scenarios
                         *  TODO : Invoke scenario
                         */
                        case SCENARIO1:
                            gridDisplayScreen.startSimulation();
                            break;
                        case SCENARIO2:
                             // Start the simulation
                            gridDisplayScreen.startSimulation();
                            break;
                        case SCENARIO3:
                            // Start the simulation
                            gridDisplayScreen.startSimulation();
                            break;
                        case SCENARIO4:
                        	 // Start the simulation
                            gridDisplayScreen.startSimulation();
                            break;
                        case SCENARIO5:
                            break;
                    }
                } else {
                    // TODO :  Do nothing or pring a warning message
                }
            }
        });

    }

    @Override
    public void startGame() {        
        initializeGameUi();
    }
    
    
}
