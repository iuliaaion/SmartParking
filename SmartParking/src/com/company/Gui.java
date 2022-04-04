package com.company;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.*;

public class Gui extends JFrame {

    // declaration of GUI elements
    private CoordinatorAgent coordinatorAgent = null;
    private EnteringAgent enteringAgent = null;
    private JLabel headingLabel = null;
    private JLabel infoLabel = null;
    private JLabel freeSpacesLabel = null;
    private JButton inParkingButton = null;
    private JButton scanButton = null;
    private JButton outParkingButton = null;
    private JButton quitButton = null;
    private JPanel panel = null;
    private Integer freeParkingNo = 1000;
    private JOptionPane infoPopup;
    public Gui(CoordinatorAgent agent) {

        // pass a reference to coordinatorAgent (needed for data exchange between the GUI and the agent)
        coordinatorAgent = agent;

        // start the Graphical User Interface
        InitialiseGui();
    }

    private void InitialiseGui() {
        DataStorage dataStorage = new DataStorage();

        // labels
        headingLabel = new JLabel("Smart parking");
        headingLabel.setBounds(90, 20, 150, 20);

        infoLabel = new JLabel("Free parking spaces:");
        infoLabel.setBounds(30, 55, 150, 20);

        freeSpacesLabel = new JLabel(String.valueOf(freeParkingNo));
        freeSpacesLabel.setBounds(152, 55, 30, 20);

        inParkingButton = new JButton("Entrance to the parking");
        inParkingButton.setBounds(20, 100, 200, 25);

        scanButton = new JButton("Please scan the receipt");
        scanButton.setBounds(20, 150, 200, 25);

        outParkingButton = new JButton("Exit from the parking");
        outParkingButton.setBounds(20, 200, 200, 25);
        outParkingButton.setEnabled(false);

        quitButton = new JButton("Quit");
        quitButton.setBounds(20, 250, 75, 25);

        inParkingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                // close the application

                //freeParkingNo--;
                //freeSpacesLabel.setText(String.valueOf(freeParkingNo));
                GuiEvent guiEvent = new GuiEvent(this, 1);
                coordinatorAgent.postGuiEvent(guiEvent);
            }
        });

        scanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                GuiEvent guiEvent = new GuiEvent(this, 3);
                coordinatorAgent.postGuiEvent(guiEvent);
            }
        });

        outParkingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // close the application
                //freeParkingNo++;
                //freeSpacesLabel.setText(String.valueOf(freeParkingNo));
                GuiEvent guiEvent = new GuiEvent(this, 2);
                coordinatorAgent.postGuiEvent(guiEvent);
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                // close the application
                System.exit(0);
            }
        });


        // panel
        panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        // set the properties of the window
        setTitle("Smart Parking");
        setResizable(false);
        setSize(290, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // add GUI elements to the panel
        panel.add(headingLabel);
        panel.add(infoLabel);
        panel.add(freeSpacesLabel);
        panel.add(inParkingButton);
        panel.add(scanButton);
        panel.add(outParkingButton);
        panel.add(quitButton);

    }

    protected void displayPopup(String infoMessage, String titleBar){
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    protected void displayFreeNo(String freeNo){
        freeSpacesLabel.setText(freeNo);
    }
    protected  void changeEnableStateButton(boolean state){
        outParkingButton.setEnabled(state);
    }

}