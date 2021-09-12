package MySimpleSMSClientServer;

import java.net.*;
import java.util.StringTokenizer;
import java.io.*;

public class ValidationServerSMSProtocol {

	public String processRequest(String theInput) {
		
		System.out.println("Received message from client: " + theInput);
		
		String theOutput;
        
		// Check if user input is correct
		if (countWords(theInput) == 5) {
			// Create answer SMS to send
			theOutput = createSMS(theInput);
		}
		else {
			// Wrong input message to send
			theOutput = "Η δομή του μηνύματος πρέπει να είναι: \"Κωδικός μετακίνησης Όνομα Επώνυμο Διεύθυνση Αριθμός\".";
		}
		
		// Debugging message
		System.out.println("Send message to client: " + theOutput);
		
		// Return final answer SMS
		return theOutput;
	}
	
	
	private String createSMS(String theInput) {
		
		// Message variables
		String m = "ΜΕΤΑΚΙΝΗΣΗ";	
		String answerSMS;
				
		//Input split - Split message content
		String[] splited = theInput.trim().split("\\s+");
	    // 1. Transaction code
		String codeString = splited[0];
		int code;
		// 2. First name
		String firstName = splited[1];
		// 3. Last name
		String lastName = splited[2];
		// 4. Address
		String address = splited[3];
		// 5. Number
		String numberString = splited[4];
		int number;
		
		// Check if input code & address number is numeric
		if (isNumeric(codeString) && isNumeric(numberString)) { 
			
			// Parse to Integers
		    code = Integer.parseInt(codeString);
		    number = Integer.parseInt(numberString);
		    
		    // Check if code is legal
			if (code<1 || code>6) {
				answerSMS = "Ο κωδικός μετακίνησης που πληκτρολογήσατε δεν είναι έγκυρος. " 
							+ "Παρακαλώ δοκιμάστε ξανά. (Επιτρεπόμενοι κωδικοί: 1-7.)";
			}
			// Check if address number is legal
			else if (number <=0) {
				answerSMS = "Παρακαλώ εισάγεται σωστά τον αριθμό της διεύθυνσής σας και δοκιμάστε ξανά.";
			}
			else {
				// Initialize the answer SMS
			    answerSMS = (m + " " + code + " " + firstName + " " + lastName + " " + address + " " + number).toUpperCase();
			}
		} else {
			// Wrong input message
			answerSMS = "Ο κωδικός μετακίνησης και ο αριθμός της διέυθυνσης σας πρέπει να είναι αριθμητικά ψηφία.";
		}
				
		//Return final answer SMS
		return answerSMS;	
	}
	
	
	// Function to count total number of words in the string
	private int countWords(String str) {
			
		// Check if the string is null or empty then return zero
		if (str == null || str.isEmpty()) {
			return 0;
		}
			
		// Create a StringTokenizer with the given string passed as a parameter
		StringTokenizer tokens = new StringTokenizer(str);
			
		// Return the number of words in the given string using countTokens() method
		return tokens.countTokens();
	}
	
	
	// Function to check if input is number
	private boolean isNumeric(String string) {
		
	    int intValue;
			
	    if (string == null || string.equals("")) {
	        return false;
	    }
	    
	    try {
	        intValue = Integer.parseInt(string);
	        return true;
	    } catch (NumberFormatException e) {
	    }
	    return false;
	}
	
}