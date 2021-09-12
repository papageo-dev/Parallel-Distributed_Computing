package PiCalcRMI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.*;
import java.rmi.registry.*;


public class PiClient {
	
	private static final String HOST = "localhost";
	private static final int PORT = Registry.REGISTRY_PORT; // 1099
	
	public static void main(String[] args) {
				
		try {
			// Locate rmi registry
			Registry registry = LocateRegistry.getRegistry(HOST, PORT);
			
			// Look up for a specific name and get remote reference (stub)
			String rmiObjectName = "Pi";
			Pi ref = (Pi)registry.lookup(rmiObjectName);
			
			//do { // Infinity Loop
			    // Ask user input
				BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Enter number of steps to send to server:");
				String theInput = user.readLine();
				// Check if user's input is valid
				while (!isValid(theInput)) {
					System.out.println("Invalid Input, try again!");
					System.out.println("Enter number of steps to send to server:");
					theInput = user.readLine();
				}
				// Convert String input to Long
				long input = Long.parseLong(theInput);
				// Do remote method invocation and print result
				double result = ref.piCalculator(input);
				System.out.println("Estimated Pi with " + input + " steps is: " + result);
			//} while(true);
			
		} catch (RemoteException re) {
			System.out.println("Remote Exception");
			re.printStackTrace();
		} catch (Exception e) {
			System.out.println("Other Exception");
			e.printStackTrace();
		}
	}
	
	// Function to check if input is valid
	private static boolean isValid(String string) {
		
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

