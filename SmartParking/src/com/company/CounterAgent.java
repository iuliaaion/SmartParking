package com.company;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class   CounterAgent extends Agent {
    protected void setup() {

        // add the ProcessRequestBehaviour() to all agents
        addBehaviour(new CounterAgent.ProcessRequestBehaviour());

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
                if(message.equals("A parking space has been occupied")){
                    DataStorage.freeNo--;
                }
                else if(message.equals("A parking space has been released")){
                    DataStorage.freeNo++;
                }

                ACLMessage messageToSend = new ACLMessage(ACLMessage.REQUEST);
                messageToSend.addReceiver(new AID("CoordinatorAgent", AID.ISLOCALNAME));
                messageToSend.setContent(String.valueOf(DataStorage.freeNo));
                send(messageToSend);
            } else {
                // no message received, block the behaviour (saves CPU time)
                block();
            }
        }
    }
}
