package PiClientMultServerTCPSyncHash;

import java.net.*;
import java.io.*;

public class PiClientProtocol {

	BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
	
	public String prepareRequest() throws IOException {
	
		// Ask user for an integer = number of steps
		System.out.print("Enter number of steps to send to server:");
		String theOutput = user.readLine();
		return theOutput;
    }
        
    public String prepareExit() throws IOException {
	
     	String theOutput = "EXIT";
		return theOutput;
    }

	public void processReply(String theInput) throws IOException {
	
		System.out.println("Reply: " + theInput);
	}
	
	
}