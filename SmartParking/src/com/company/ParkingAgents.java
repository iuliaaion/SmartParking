package com.company;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.Arrays;

public class ParkingAgents extends Agent {
    protected void setup() {

        // add the ProcessRequestBehaviour() to all agents
        addBehaviour(new ParkingAgents.ProcessRequestBehaviour());

        // print the list of started agents
        System.out.println("Hello! Agent " + getAID().getName() + " is ready.");
    }

    protected void takeDown() {

        // print the list of closing agents
        System.out.println("Agent " + getAID().getName() + " terminating.");
    }

    private class ProcessRequestBehaviour extends CyclicBehaviour {

        public void action() {
            // receive messages only for the language corresponding to the agent
            ACLMessage receivedMessage = receive();

            if (receivedMessage != null) {
                // a message was received
                String message = receivedMessage.getContent();
                System.out.println("from entering " + message);

                ACLMessage replyMessage = receivedMessage.createReply();
                replyMessage.setContent("EnteringAgent activat");
                System.out.println(getAID().getName() + ">" + "EnteringAgent activat");
                // reply to the coordinator agent
                send(replyMessage);
            } else {
                // no message received, block the behaviour (saves CPU time)
                block();
            }
        }
    }
}


