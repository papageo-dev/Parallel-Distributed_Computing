package NewRMImailUserInput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.concurrent.TimeUnit;

public class MailClientUserInput {
	
	private static final String HOST = "localhost"; 
	private static final int PORT = Registry.REGISTRY_PORT; // 1099
	
	public static void main(String[] args) {
				
		try {
			// Locate rmi registry
			Registry registry = LocateRegistry.getRegistry(HOST, PORT);
			
			// Look up for a specific name and get remote reference (stub)
			String rmiObjectName = "Email";
			InterfaceManagmentEmails ref = (InterfaceManagmentEmails)registry.lookup(rmiObjectName);
			
			boolean quit = false;
			do {
				// Ask user input
				BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("\nAvailable Email Services: ");
				System.out.println("1. Put Email\n2. Get Email\n3. Update/Change Email\n4. Remove Email");
				System.out.println("Press the number of the service: ");
				int theInputChoice = Integer.parseInt(user.readLine());
				String inputName = null;
								
				switch (theInputChoice) {
				
	            case 1:
	            	// Put a new email address for given name
	            	System.out.println("Insert your name: ");
	            	inputName = user.readLine();
					ref.putEmail(inputName, inputName + "@gmail.com");
					System.out.println("Success! The insertion of " + inputName +"'s" + " email is OK!");
					break;
				
	            case 2:
	            	// Get email address for given name
	            	System.out.println("Insert your Email's address name, to search for it: ");
	            	inputName = user.readLine();
					String result = ref.getEmail(inputName);
					System.out.println("The email of " + inputName + " is " + result);
					break;
					
	            case 3:
	            	// Update an existed email address for given name
	            	System.out.println("Insert your Email's address name, to update/change: ");
	            	inputName = user.readLine();
					ref.updateEmail(inputName, inputName + "@yahoo.com");
					System.out.println("The NEW email of " + inputName + " is " + ref.getEmail(inputName));
					break;
					
	            case 4:
	            	// Remove email address of given name
	            	System.out.println("Insert your Email's address name, to delete: ");
	            	inputName = user.readLine();
					ref.removeEmail(inputName);
					System.out.println(inputName + "'s Email deleted!");
					break;
				
	            case -1:
	            	// Quit
	            	quit = true;
                    break;
				
	            default:
                    System.out.println("Invalid choice. Try again, please.");
	            } 
				
			} while(!quit);
		    

		} catch (RemoteException re) {
			System.out.println("Remote Exception");
			re.printStackTrace();
		} catch (Exception e) {
			System.out.println("Other Exception");
			e.printStackTrace();
		}
	}
}

