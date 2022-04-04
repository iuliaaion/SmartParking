package com.company;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ScannerAgent extends Agent {
    protected void setup() {

        // add the ProcessRequestBehaviour() to all agents
        addBehaviour(new ScannerAgent.ProcessRequestBehaviour());

        // print the list of started agents
        System.out.println("Hello! Agent " + getAID().getName() + " is ready.");
    }
    protected void takeDown() {

        // print the list of closing agents
        System.out.println("Agent " + getAID().getName() + " terminating.");
    }
    private class ProcessRequestBehaviour extends CyclicBehaviour {

        public void action() {
            // receive messages from CoordinatorAgent
            ACLMessage receivedMessage = receive();

            if (receivedMessage != null) {
                AID senderAgent = receivedMessage.getSender();
                String message = receivedMessage.getContent();
                String car_no = message.split("_")[0];
                String car_type = message.split("_")[1];
                if(senderAgent.getName().contains("EnteringAgent")){
                    DataStorage.car_dictionary.put(car_no, car_type);
                }
                else if(senderAgent.getName().contains("ExitAgent")){
                    DataStorage.car_dictionary.remove(car_no, car_type);
                }

            } else {
                // no message received, block the behaviour (saves CPU time)
                block();
            }
        }
    }
}
