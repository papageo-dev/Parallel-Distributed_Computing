package MultCalcRMI_Hashtables;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.*;
import java.rmi.registry.*;


public class SinClient {
	
	private static final String HOST = "localhost";
	private static final int PORT = Registry.REGISTRY_PORT; // 1099
	
	public static void main(String[] args) {
				
		try {
			// Locate rmi registry
			Registry registry = LocateRegistry.getRegistry(HOST, PORT);
			
			// Look up for a specific name and get remote reference (stub)
			String rmiObjectName = "myObj";
			Interface ref = (Interface)registry.lookup(rmiObjectName);
			
			//do { // Infinity Loop
			    // Ask user input
				BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Enter a number to calculate its SIN:");
				String theInput = user.readLine();
				// Check if user's input is valid
				while (!isValid(theInput)) {
					System.out.println("Invalid Input, try again!");
					System.out.println("Enter a number to calculate its SIN:");
					theInput = user.readLine();
				}
				// Convert String input to Long
				int input = Integer.parseInt(theInput);
				// Do remote method invocation and print result
				double result = ref.sinCalculator(input);
				System.out.println("SIN value for " + input + " is: " + result);
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
		
	    int intValue;
			
	    if (string == null || string.equals("") || string.isBlank() || string.isEmpty()) {
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


