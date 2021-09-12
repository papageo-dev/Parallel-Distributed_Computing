package NewRMImail;

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
			//return (String) storeEmails.get(name);
	        return storeEmails.get(name);
		}
		else {
			return "These is no Email address for name: " + name;
		}
	}

	// Implementation code of the remote method "putEmail", which inserts a new email
	public synchronized void putEmail(String name, String email) throws RemoteException {
		storeEmails.put(name, email);	
	}

	// Implementation code of the remote method "removeEmail", which removes an email by name
	public synchronized void removeEmail(String name) throws RemoteException {
		storeEmails.remove(name);	
	}

	// Method to remove an email by name
	public synchronized void updateEmail(String name, String newMail) throws RemoteException {
		storeEmails.replace(name, newMail);
	}

}
