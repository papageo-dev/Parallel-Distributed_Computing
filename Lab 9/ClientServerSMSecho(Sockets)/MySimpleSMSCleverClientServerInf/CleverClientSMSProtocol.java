package MySimpleSMSCleverClientServerInf;

import java.net.*;
import java.util.StringTokenizer;
import java.io.*;

public class CleverClientSMSProtocol {

	BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
	
	public String prepareRequest() throws IOException {
	
     	System.out.print("Enter message to send to server:");
     	
     	// User's input
		String theInput = user.readLine();
		String theOutput = null;
		
		// Check if user input is correct
		while (!checkSMS(theInput)) {
			System.out.print("Enter message to send to server:");
			theInput = user.readLine();		
		}
		theOutput = theInput;
		
		return theOutput;
    }

	public void processReply(String theInput) throws IOException {
	
		System.out.println("Message received from server: " + theInput);
	}
	
	// Function to check input and build the final answer SMS to sent
	private boolean checkSMS(String theInput) {
			
		boolean okSMS = false;
		String wrongInputMsg = "Αποτυχία αποστολής. Παρακαλώ δοκιμάστε ξανά. Σφάλμα: ";
			
		// Check if input have the necessary size
		if (countWords(theInput) != 5 ) {
			System.out.println(wrongInputMsg + "001. Η δομή του μηνύματος πρέπει να είναι: \"Κωδικός μετακίνησης Όνομα Επώνυμο Διεύθυνση Αριθμός\".");
			return okSMS;
		}
			
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
				
			okSMS = true;
				
			// Parse to Integers
			code = Integer.parseInt(codeString);
			number = Integer.parseInt(numberString);
			    
			// Check if code is legal
			if (code<1 || code>6) {
				okSMS = false;
				System.out.println(wrongInputMsg + "002. Μη έγκυρος κωδικός μετακίνησης. " 
							+ "(Επιτρεπόμενοι κωδικοί: 1-7.)");
			}
			// Check if address number is legal
			else if (number <=0) {
				okSMS = false;
				System.out.println(wrongInputMsg + "003. Μη έγκυρος αριθμός διεύθυνσής.");
			}
		} else {
			okSMS = false;
			// Wrong input message
			System.out.println(wrongInputMsg + "004. Ο κωδικός μετακίνησης και ο αριθμός της διέυθυνσης σας πρέπει να είναι αριθμητικά ψηφία.");
		}
					
		return okSMS;	
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