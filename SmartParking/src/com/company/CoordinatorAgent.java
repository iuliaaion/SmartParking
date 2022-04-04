package com.company;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;


public class CoordinatorAgent extends GuiAgent {

    private Gui gui = null;

    protected void setup() {

        // add the ProcessResponseBehaviour() behaviour to the agent
        addBehaviour(new ProcessResponseBehaviour());

        // show that the agent is initialized
        System.out.println("Hello! Agent " + getAID().getName() + " is ready.");

        // instantiate the GUI
        gui = new Gui(this);
        gui.setVisible(true);
    }

    protected void takeDown() {

        // show that agent is shutting down
        System.out.println("Agent " + getAID().getName() + " terminating.");
    }

    protected void onGuiEvent(GuiEvent event) {

        DataStorage dataStorage = new DataStorage();
        String car_no = dataStorage.getRandomCar().car;
        String car_type = dataStorage.getRandomCar().type;
        // receive data from the GUI
        if (event.getType() == 1) {
            gui.displayPopup("Entry barrier is opening", "INFO");
            // create a new message of type REQUEST
            ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

            // create receiver for message from CoordinatorAgent
            message.addReceiver(new AID("EnteringAgent", AID.ISLOCALNAME));
            // set the content of the message as a text to be displayed in terminal from the EnteringAgent agent
            message.setContent(car_no + "_" + car_type);

            // send the message
            send(message);

            if(car_type.equals("gas")){
                ACLMessage messageToSend = new ACLMessage(ACLMessage.REQUEST);
                messageToSend.addReceiver(new AID("GasAgent", AID.ISLOCALNAME));
                messageToSend.setContent("Gas");
                send(messageToSend);
            }
        }

        if (event.getType() == 2) {
            gui.displayPopup("Exit barrier is opening", "INFO");
                // create a new message of type REQUEST
            ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
                // create receiver for message from CoordinatorAgent
            message.addReceiver(new AID("ExitAgent", AID.ISLOCALNAME));
                // set the content of the message as a text to be displayed in terminal from the EnteringAgent agent
            message.setContent(DataStorage.exit_car+"_"+DataStorage.exit_type);
            send(message);
            gui.changeEnableStateButton(false);
        }

        if (event.getType() == 3){
            DataStorage.exit_car = dataStorage.getRandomCarForExit().car;
            DataStorage.exit_type = dataStorage.getRandomCarForExit().type;
            System.out.println("Car with registration number " + DataStorage.exit_car + " scanned the receipt");
            gui.changeEnableStateButton(true);
        }
    }

    private class ProcessResponseBehaviour extends CyclicBehaviour {

        public void action() {

            // receive the reply message from the language agents
            ACLMessage receivedMessage = receive();
            if (receivedMessage != null) {
                AID senderAgent = receivedMessage.getSender();
                String message = receivedMessage.getContent();
                if(senderAgent.getName().contains("GasAgent")){
                    gui.displayPopup(message, "WARNING");
                } else {
                    gui.displayFreeNo(message);
                }
            }
            else {
                // no message received, block the behaviour (saves CPU time)
                block();
            }

        }
    }
}