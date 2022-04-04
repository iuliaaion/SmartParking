package com.company;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ExitAgent extends Agent {
    protected void setup() {

        // add the ProcessRequestBehaviour() to all agents
        addBehaviour(new ExitAgent.ProcessRequestBehaviour());

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
                System.out.println("Car with registration number " + message.split("_")[0] + " exited from parking lot");

                // send free parking number to CounterAgent
                ACLMessage messageToSend = new ACLMessage(ACLMessage.REQUEST);
                messageToSend.addReceiver(new AID("CounterAgent", AID.ISLOCALNAME));
                messageToSend.setContent("A parking space has been released");
                send(messageToSend);

                // send car object to ScannerAgent
                ACLMessage messageToSendToScanner = new ACLMessage(ACLMessage.REQUEST);
                messageToSendToScanner.addReceiver(new AID("ScannerAgent", AID.ISLOCALNAME));
                messageToSendToScanner.setContent(message);
                send(messageToSendToScanner);
            } else {
                // no message received, block the behaviour (saves CPU time)
                block();
            }
        }
    }
}
