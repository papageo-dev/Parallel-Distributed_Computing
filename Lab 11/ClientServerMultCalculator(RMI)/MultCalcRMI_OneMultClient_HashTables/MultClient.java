package MultCalcRMI_OneMultClient_HashTables;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.StringTokenizer;


public class MultClient {
	
	private static final String HOST = "localhost";
	private static final int PORT = Registry.REGISTRY_PORT; // 1099
	
	public static void main(String[] args) {
				
		try {
			// Locate rmi registry
			Registry registry = LocateRegistry.getRegistry(HOST, PORT);
			
			// Look up for a specific name and get remote reference (stub)
			String rmiObjectName = "myObj";
			Interface ref = (Interface)registry.lookup(rmiObjectName);
			
			do { // Infinity Loop
				
			    // Ask user input
				BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Enter <Method Name> <Input Number> to calculate:");
				String theInput = user.readLine().toUpperCase();

				// Check if user's input is valid
				while (!isValid(theInput)) {
					System.out.println("Invalid Input, try again!");
					System.out.println("Enter <Method Name> <Input Number> to calculate:");
					theInput = user.readLine().toUpperCase();
				}
				
				//Input split
				String[] splited = theInput.trim().split("\\s+");
			    // 1. Method Name
				String methodName = splited[0];
				// 2. Input Number to calculate
				String inputNum  = splited[1];

				// Check which remote method(Pi, Sin, Cos) to call
				if (methodName.equals("PI")) {
					// Convert String input to Long
					long input = Long.parseLong(inputNum);
					// Do remote method invocation and print result
					double result = ref.piCalculator(input);
					System.out.println("Estimated Pi with " + input + " steps is: " + result);
				} else if (methodName.equals("COS")) {
					// Convert String input to Integer
					int input = Integer.parseInt(inputNum);
					// Do remote method invocation and print result
					double result = ref.cosCalculator(input);
					System.out.println("COS value for " + input + " is: " + result);
				} else if (methodName.equals("SIN")) {
					// Convert String input to Integer
					int input = Integer.parseInt(inputNum);
					// Do remote method invocation and print result
					double result = ref.sinCalculator(input);
					System.out.println("SIN value for " + input + " is: " + result);
				}
				
			} while(true);
			
		} catch (RemoteException re) {
			System.out.println("Remote Exception");
			re.printStackTrace();
		} catch (Exception e) {
			System.out.println("Other Exception");
			e.printStackTrace();
		}
	}
	
	
	// Function to check if input is valid
	private static boolean isValid(String stringInput) {
		
		// Array with all valid methods for user/client
		String validMethods[] = {"PI", "COS", "SIN"};
		
		boolean validInput = false;
		
		// Check if input elements' length is 2
	    if (countWords(stringInput) == 2) {
	    	
	    	//Input split
			String[] splited = stringInput.trim().split("\\s+");
		    // 1. Method Name
			String methodName = splited[0];
			// 2. Input Number to calculate
			String inputNum  = splited[1];
			
			// Check if input method and input number are valid 
	    	for (int i=0; i<validMethods.length; i++) {
	    		if (methodName.equals(validMethods[i]) && isNumeric(inputNum)) {
	    			validInput = true;
	    		}
	    	}
	    }
	    else {
	    	validInput = false;
	    }
	    return validInput;

	}
	
	
	// Function to count total number of words in the string
	private static int countWords(String str) {
			
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
	private static boolean isNumeric(String string) {
		
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

