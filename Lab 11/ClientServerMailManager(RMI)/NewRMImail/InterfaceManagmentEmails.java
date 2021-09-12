package NewRMImail;

import java.rmi.*;

public interface InterfaceManagmentEmails extends Remote {
	
	// Remote methods' signatures

	// Method to get an email by name
	String getEmail(String name) throws RemoteException;
	
	// Method to insert a new email
	void putEmail(String name, String email) throws RemoteException;
	
	// Method to remove an email by name
	void removeEmail(String name) throws RemoteException;
	
	// Method to update an email by name
	void updateEmail(String name, String newMail) throws RemoteException;
}
