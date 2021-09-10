package PiClientMultServerTCPSyncHash;

import java.net.*;
import java.util.Hashtable;
import java.io.*;

public class ServerProtocol {

	private Pi pi;

	public ServerProtocol (Pi pi) {
		
		this.pi = pi;
    }

	public String processRequest(String theInput, Hashtable<Long,Double> calculatedPi) {
		
		String theOutput;
	    
		// Check if input is valid
        if (isValid(theInput)) {
        	// Parse number of steps to long variable
        	long theInputLong = Long.parseLong(theInput);
        	// Check if Pi value is calculated for the input number of steps
        	if (calculatedPi.containsKey(theInputLong)) {
        		// Return already calculated Pi from the hashtable as output
        		theOutput = "[Already Calculated] Estimated Pi with " + theInput + " steps is: " + calculatedPi.get(theInputLong);
        	}
        	else {
            	// Calculate Pi
    			pi.calculator(theInputLong, calculatedPi);
    			// Return Pi calculation as output
    			theOutput = "Estimated Pi with " + theInput + " steps is: " + pi.print();
        	}
		}
        else {
        	// Return a warning message as output
        	theOutput = "Enter a valid number of steps and try again, please.";	
        }
        // Return the output, to send to client
		return theOutput;
	}
	
	// Function to check if input is valid
	private boolean isValid(String string) {
		
	    long intValue;
			
	    if (string == null || string.equals("") || string.isBlank() || string.isEmpty()) {
	        return false;
	    }
	    
	    try {
	        intValue = Long.parseLong(string);
	        return true;
	    } catch (NumberFormatException e) {
	    }
	    return false;
	}
}