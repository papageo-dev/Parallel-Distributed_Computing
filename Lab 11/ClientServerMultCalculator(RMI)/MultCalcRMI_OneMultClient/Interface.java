package MultCalcRMI_OneMultClient;

import java.rmi.*;

public interface Interface extends Remote {
	
	// Remote methods' signatures 
	
	// This method calculates Pi value for specific number of steps
	public double piCalculator(long input) throws RemoteException;
	
	// This method calculates COS value for a given number
	public double cosCalculator(int input) throws RemoteException;
	
	// This method calculates SIN value for a given number
	public double sinCalculator(int input) throws RemoteException;
	
}
