package NewRMImailUserInput;

import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;

// This class implements the remote interface "InterfaceManagmentEmails"
public class ManagementEmailsImplement extends UnicastRemoteObject implements InterfaceManagmentEmails {
	
	private static final long serialVersionUID = 1;
	
	// private Hashtable storeEmails;
    private HashMap<String, String> storeEmails;

	public ManagementEmailsImplement() throws RemoteException {
		// Dhmioyrgia bashs dedomenon e-mails
		//storeEmails = new Hashtable();
        storeEmails = new HashMap<String, String>();
		storeEmails.put("Panos","panosm@uom.gr");
		storeEmails.put("John","johnf@gmail.com");
	}

	// Implementation code of the remote method "getEmail", which returns an email by name
	public synchronized String getEmail(String name) throws RemoteException {
		
		if (storeEmails.get(name) != null) {
			
			// Sleep to be more realistic
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//return (String) storeEmails.get(name);
	        return storeEmails.get(name);
		}
		else {
			return "These is no Email address for name: " + name;
		}
			
	}

	// Implementation code of the remote method "putEmail", which inserts a new email
	public synchronized void putEmail(String name, String email) throws RemoteException {
		
		// Wait to be more realistic
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Add a new email address
		storeEmails.put(name, email);	
	}

	// Implementation code of the remote method "removeEmail", which removes an email by name
	public synchronized void removeEmail(String name) throws RemoteException {
		
		if (storeEmails.containsKey(name)) {
			storeEmails.remove(name);
			// Sleep time to be more realistic
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
	}

	// Method to remove an email by name
	public synchronized void updateEmail(String name, String newMail) throws RemoteException {
		
		if (storeEmails.containsKey(name)) {
			storeEmails.replace(name, newMail);
			// Sleep to be more realistic
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	}
	

}
