package com.company;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class GasAgent extends Agent {
    //static double get_random_gas_level(double min, double max){return min + Math.random() * (max - min);}

    protected void setup() {

        // add the ProcessRequestBehaviour() to all agents
        addBehaviour(new GasAgent.ProcessRequestBehaviour());

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
                // a message was received
                String message = receivedMessage.getContent();
                DataStorage.gasLevel += 0.125;    //GasAgent.get_random_gas_level(0.01, 0.1);

                if(DataStorage.gasLevel > 0.25){
                    ACLMessage messageToSend = new ACLMessage(ACLMessage.REQUEST);
                    messageToSend.addReceiver(new AID("CoordinatorAgent", AID.ISLOCALNAME));
                    messageToSend.setContent("Gas level exceeded");
                    send(messageToSend);
                }
            } else {
                // no message received, block the behaviour (saves CPU time)
                block();
            }
        }
    }
}
