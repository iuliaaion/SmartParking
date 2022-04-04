package com.company;

import jade.Boot;

public class Main {

    public static void main(String[] args) {
	// write your code here
        // start the JADE environment and instantiate all six agents (CoordinatorAgent, EnglishAgent, GermanAgent, FrenchAgent, ItalianAgent and SpanishAgent)
        String jadeParameters[] = {"-gui", "CoordinatorAgent:com.company.CoordinatorAgent; EnteringAgent:com.company.EnteringAgent; ExitAgent:com.company.ExitAgent; CounterAgent:com.company.CounterAgent; GasAgent:com.company.GasAgent; ScannerAgent:com.company.ScannerAgent"};
        Boot.main(jadeParameters);
    }
}
