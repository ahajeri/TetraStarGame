package com.design.pattern.tetrastar.view;

import com.design.pattern.tetrastar.enums.ScenarioEnum;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.PrintStream;
import java.util.Observable;


public class CommandAndGridView extends AView {

    private static final int noOfScenarios = 6;

    // Main side panel that contains dropdown and buttons
    private JPanel sidePanel;

    //Dropdowns and buttons for side panel
    private JComboBox scenariosDropDown;
    private JButton startSimulation, resetScreen, setScreen;
    private JLabel scenarioDescription;

    private ScenarioEnum currentScenario;

    // Grid screen for grid view that contains 7x7 grid
    private GridDisplayScreen gridDisplayScreen;
    
    // Console panel for showing logger for each step
    private JPanel consolePanel;

    public CommandAndGridView() {

        initializeConsolePanel();

        /**
         *   Create Side Panel for Commands
         */
        // Set the subpanel details
        this.sidePanel = new JPanel();
        TitledBorder sidePanelBorder = new TitledBorder("Command Panel");
        sidePanelBorder.setTitleColor(Color.WHITE);
        this.sidePanel.setBorder(sidePanelBorder);
        this.sidePanel.setLayout(new FlowLayout());
        this.sidePanel.setBackground(Color.GRAY);
        this.sidePanel.setPreferredSize(new Dimension(350,100));
        this.sidePanel.setSize(new Dimension(350,100));

        JLabel welcomeMessage = new JLabel("Welcome to the Tetra Star Game Simulation \n");
        welcomeMessage.setFont(new Font("Times New Roman", Font.BOLD, 15));
        welcomeMessage.setForeground(Color.WHITE);
        this.sidePanel.add(welcomeMessage);
        this.sidePanel.add(Box.createRigidArea(new Dimension(0, 70)));

        JLabel scenarioMessage = new JLabel("Please Choose a scenario :\n");
        scenarioMessage.setFont(new Font("Serif", Font.ITALIC, 15));
        scenarioMessage.setForeground(Color.WHITE);
        this.sidePanel.add(scenarioMessage);

        String[] scenarios =  new String[noOfScenarios];
        for(int i = 0 ; i < noOfScenarios ; i++ ) {
            if(i == 0) continue;
            else scenarios[i] = "Scenario" + (i);
        }

        this.scenariosDropDown = new JComboBox<Object>(scenarios);
        this.scenariosDropDown.setSize(30, 50);
        this.scenariosDropDown.setBackground(Color.WHITE);
        this.scenariosDropDown.setForeground(Color.BLACK);
        this.sidePanel.add(this.scenariosDropDown);
        this.sidePanel.add(Box.createRigidArea(new Dimension(410, 50)));

        this.startSimulation = new JButton("Start Simulation");
        this.startSimulation.setActionCommand("START SIMULATION");
        this.startSimulation.setToolTipText("Start the simulation of the selected scenario above.");

        this.resetScreen = new JButton("Reset Screen");
        this.resetScreen.setActionCommand("RESET SCREEN");
        this.resetScreen.setToolTipText("Reset the screen to the start or original screen.");

        this.setScreen = new JButton("Set Display Screen");
        this.setScreen.setActionCommand("SET SCREEN");
        this.setScreen.setToolTipText("Set the screen according to the scenario specified.");

        this.scenarioDescription = new JLabel();
        this.scenarioDescription.setText("Scenario");
        this.scenarioDescription.setFont(new Font("Times New Roman", Font.BOLD, 12));
        this.scenarioDescription.setForeground(Color.WHITE);

        this.sidePanel.add(this.setScreen);
        this.sidePanel.add(Box.createRigidArea(new Dimension(20, 50)));
        this.sidePanel.add(this.startSimulation);
        this.sidePanel.add(Box.createRigidArea(new Dimension(20, 50)));
        this.sidePanel.add(this.resetScreen);
        this.sidePanel.add(Box.createRigidArea(new Dimension(300, 60)));
        this.sidePanel.add(this.scenarioDescription);

        /**
         *    Create Default Game Screen panel for holding grid
         */
        this.gridDisplayScreen = GridDisplayScreenFactory.getGridDisplayScreenForScenario(null);

    }
    
    private void initializeConsolePanel() {
                this.consolePanel = new JPanel();
        TitledBorder commandPanelBorder = new TitledBorder("Console Panel");
        commandPanelBorder.setTitleColor(Color.WHITE);
        this.consolePanel.setBorder(commandPanelBorder);
        this.consolePanel.setLayout(new FlowLayout());
        this.consolePanel.setBackground(Color.GRAY);
        this.consolePanel.setPreferredSize(new Dimension(250,100));
        this.consolePanel.setSize(new Dimension(250, 100));
        JTextArea consoleLogger = new JTextArea(42, 20);
        consoleLogger.setLineWrap(true);
        consoleLogger.setWrapStyleWord(true);
        consoleLogger.setText("");
        
        PrintStream printStream = new PrintStream(new CustomOutputStream(consoleLogger));
        System.setOut(printStream);
        
        JScrollPane scrollPane = new JScrollPane(consoleLogger);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.consolePanel.add(scrollPane);
    }
    
    public JPanel resetConsolePanel() {
        
        this.consolePanel = new JPanel();
        TitledBorder commandPanelBorder = new TitledBorder("Console Panel");
        commandPanelBorder.setTitleColor(Color.WHITE);
        this.consolePanel.setBorder(commandPanelBorder);
        this.consolePanel.setLayout(new FlowLayout());
        this.consolePanel.setBackground(Color.GRAY);
        this.consolePanel.setPreferredSize(new Dimension(250,100));
        this.consolePanel.setSize(new Dimension(250, 100));
        JTextArea consoleLogger = new JTextArea(42, 20);
        consoleLogger.setLineWrap(true);
        consoleLogger.setWrapStyleWord(true);
        consoleLogger.setText("");
        
        PrintStream printStream = new PrintStream(new CustomOutputStream(consoleLogger));
        System.setOut(printStream);
        
        JScrollPane scrollPane = new JScrollPane(consoleLogger);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.consolePanel.add(scrollPane);
        
        System.out.println("Resetting console and grid panel.");
        return this.consolePanel;
    }

    @Override
    public void update(Observable o, Object selectedScenario) {

    }

    public JPanel getSidePanel() {
        return sidePanel;
    }

    public void setSidePanel(JPanel sidePanel) {
        this.sidePanel = sidePanel;
    }

    public GridDisplayScreen getGridDisplayScreen() {
        return gridDisplayScreen;
    }

    public void setGridDisplayScreen(GridDisplayScreen gridDisplayScreen) {
        this.gridDisplayScreen = gridDisplayScreen;
    }

    public JComboBox getScenariosDropDown() {
        return scenariosDropDown;
    }

    public void setScenariosDropDown(JComboBox scenariosDropDown) {
        this.scenariosDropDown = scenariosDropDown;
    }

    public JButton getStartSimulation() {
        return startSimulation;
    }

    public void setStartSimulation(JButton startSimulation) {
        this.startSimulation = startSimulation;
    }

    public JButton getResetScreen() {
        return resetScreen;
    }

    public void setResetScreen(JButton resetScreen) {
        this.resetScreen = resetScreen;
    }

    public JButton getSetScreen() {
        return setScreen;
    }

    public void setSetScreen(JButton setScreen) {
        this.setScreen = setScreen;
    }

    public JLabel getScenarioDescription() {
        return scenarioDescription;
    }

    public void setScenarioDescription(JLabel scenarioDescription) {
        this.scenarioDescription = scenarioDescription;
    }

    public ScenarioEnum getCurrentScenario() {
        return currentScenario;
    }

    public void setCurrentScenario(ScenarioEnum currentScenario) {
        this.currentScenario = currentScenario;
    }

    public JPanel getConsolePanel() {
        return consolePanel;
    }

    public void setConsolePanel(JPanel consolePanel) {
        this.consolePanel = consolePanel;
    }
    
}
