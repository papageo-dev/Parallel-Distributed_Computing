package NewRMImail;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.concurrent.TimeUnit;

public class MailClientRandomNameGen {
	
	private static final String HOST = "localhost"; 
	private static final int PORT = Registry.REGISTRY_PORT; // 1099
	
	public static void main(String[] args) {
				
		try {
			// Locate rmi registry
			Registry registry = LocateRegistry.getRegistry(HOST, PORT);
			
			// Look up for a specific name and get remote reference (stub)
			String rmiObjectName = "Email";
			InterfaceManagmentEmails ref = (InterfaceManagmentEmails)registry.lookup(rmiObjectName);
			
			// Generate Random Strings and do remote method(s) invocation(s)
			RandomEmailNameGenerator random = new RandomEmailNameGenerator();
			int iters = 10;
			for (int i=0; i<iters; i++) {
				String randomName = random.randomIdentifier();

				// Put a new email address for given name
				ref.putEmail(randomName, randomName + "@gmail.com");
				System.out.println("Success! The insertion of " + randomName +"'s" + " email is OK!");
				Thread.sleep(600);
				
				// Get email address for given name
				String result = ref.getEmail(randomName);
				System.out.println("The email of " + randomName + " is " + result);
				Thread.sleep(200);
				
				// Update an existed email address for given name
				ref.updateEmail(randomName, randomName + "@yahoo.com");
				System.out.println("The NEW email of " + randomName + " is " + ref.getEmail(randomName));
				Thread.sleep(800);
				
				// Remove email address of given name
				ref.removeEmail(randomName);
				System.out.println(randomName + "'s Email deleted!\n");
				Thread.sleep(1000);
			}
	
		} catch (RemoteException re) {
			System.out.println("Remote Exception");
			re.printStackTrace();
		} catch (Exception e) {
			System.out.println("Other Exception");
			e.printStackTrace();
		}
	}
}

