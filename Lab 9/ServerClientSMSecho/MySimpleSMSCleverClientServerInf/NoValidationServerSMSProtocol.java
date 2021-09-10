package MySimpleSMSCleverClientServerInf;

import java.net.*;
import java.util.StringTokenizer;
import java.io.*;

// Server validation is missing. Message is validated only from client's side, before sending

public class NoValidationServerSMSProtocol {

	public String processRequest(String theInput) {
		
		System.out.println("Received message from client: " + theInput);
		
		// Build the message to send
		String theOutput = createSMS(theInput);
		
		// Debugging message
		System.out.println("Send message to client: " + theOutput);
		
		// Return final answer SMS
		return theOutput;
	}
	
	
	// Function to build(without validation) the final answer SMS to sent
	private String createSMS(String theInput) {
		
		// Message variables
		String m = "Ã≈‘¡ …Õ«”«";	
		String answerSMS;
				
		//Input split - Split message content
		String[] splited = theInput.trim().split("\\s+");
	    // 1. Transaction code
		int code = Integer.parseInt(splited[0]);
		// 2. First name
		String firstName = splited[1];
		// 3. Last name
		String lastName = splited[2];
		// 4. Address
		String address = splited[3];
		// 5. Number
		int number = Integer.parseInt(splited[4]);

		// Initialize the answer SMS
		answerSMS = (m + " " + code + " " + firstName + " " + lastName + " " + address + " " + number).toUpperCase();
		
		//Return final answer SMS
		return answerSMS;	
	}
	
	
}