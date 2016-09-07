package com.design.pattern.tetrastar.view;
/**
 *   @author Akshata, Rachna and  Shweta. 
 */
import com.design.pattern.tetrastar.enums.ScenarioEnum;

public class GridDisplayScreenFactory {

    public static GridDisplayScreen getGridDisplayScreenForScenario(ScenarioEnum scenario) {
        GridDisplayScreen gridDisplayScreen = null;
        if(scenario != null) {
            switch (scenario) {
                case SCENARIO1:
                    gridDisplayScreen = new Scenario1DisplayScreen();
                    break;
                case SCENARIO2:
                    gridDisplayScreen = new Scenario2DisplayScreen();
                    break;
                case SCENARIO3:
                    gridDisplayScreen = new Scenario3DisplayScreen();
                    break;
            }
        } else {
            gridDisplayScreen = new DefaultGameDisplayScreen();
        }
        return gridDisplayScreen;
    }

}
