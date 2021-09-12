package PiCalcRMI_HashTable;

import java.rmi.*;

public interface Pi extends Remote {
	
	// Remote methods' signatures 
	
	// This method calculates Pi value for specific number of steps
	public double piCalculator(long input) throws RemoteException;
	
}
