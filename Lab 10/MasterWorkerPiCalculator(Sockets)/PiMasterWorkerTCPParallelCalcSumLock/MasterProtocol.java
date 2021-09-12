package PiMasterWorkerTCPParallelCalcSumLock;

import java.net.*;
import java.io.*;

public class MasterProtocol {

	private Pi mySum;
	private int myId;

	public MasterProtocol (Pi s, int id) {
		mySum = s;
		myId = id;
    }

	// Prepare request to send to each worker. Sends Pi sum till this time and worker's id, to calculate next piece.
	public String prepareRequest() {
		
		String theOutput = mySum.printInit() + " " + String.valueOf(myId);
		return theOutput;
	}
	
	// Add worker's piece of calculation to total Pi calculation
	public void processReply(String theInput) {
	
		double reply = Double.parseDouble(theInput);
		mySum.add(reply);
	}	
	
		
}