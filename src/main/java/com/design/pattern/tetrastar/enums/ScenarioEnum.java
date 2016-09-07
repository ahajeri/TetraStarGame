package com.design.pattern.tetrastar.enums;
/**
 * 
 * @author Akshata, Rachna and Shweta.
 */

public enum ScenarioEnum {

    SCENARIO1(1, "Scenario1", "Scenario1:Moving all characters."),
    SCENARIO2(2, "Scenario2", "Scenario2:StarMap and StarAtlas encrypt or steal."),
    SCENARIO3(3, "Scenario3", "Scenario3:Undo move for Rover.");
    
    private int scenarioNumber;
    private String scenarioName;
    private String scenarioDescription;

    ScenarioEnum(int scenarioNumber, String scenarioName, String scenarioDescription) {
        this.scenarioNumber = scenarioNumber;
        this.scenarioName = scenarioName;
        this.scenarioDescription = scenarioDescription;
    }

    public int getScenarioNumber() {
        return scenarioNumber;
    }

    public void setScenarioNumber(int scenarioNumber) {
        this.scenarioNumber = scenarioNumber;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public String getScenarioDescription() {
        return scenarioDescription;
    }

    public void setScenarioDescription(String scenarioDescription) {
        this.scenarioDescription = scenarioDescription;
    }

    public static ScenarioEnum getScenarioFromName(String name) {
        if(name != null) {
            for(ScenarioEnum s : values()) {
                if(s.getScenarioName().equalsIgnoreCase(name)) {
                    return s;
                }
            }
        }
        return null;
    }
}