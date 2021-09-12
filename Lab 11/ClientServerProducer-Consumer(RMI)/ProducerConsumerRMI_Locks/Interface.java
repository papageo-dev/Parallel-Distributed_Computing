package ProducerConsumerRMI_Locks;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote {
	
	// Remote methods' signatures 

	// Method to put an item in buffer
	void put(int data) throws RemoteException;
	
	// Method to get an item from buffer
	int get() throws RemoteException;

	
}
