package com.design.pattern.tetrastar.view;

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
                case SCENARIO4:
                    gridDisplayScreen = new Scenario4DisplayScreen();
                    break;
                case SCENARIO5:
                   // gridDisplayScreen = new Scenario5DisplayScreen();
                    break;
            }
        } else {
            gridDisplayScreen = new DefaultGameDisplayScreen();
        }
        return gridDisplayScreen;
    }

}
