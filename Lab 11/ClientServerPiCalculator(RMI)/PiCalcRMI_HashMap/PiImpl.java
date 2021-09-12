package PiCalcRMI_HashMap;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Hashtable;

// This class implements the remote interface "Pi"
public class PiImpl extends UnicastRemoteObject implements Pi {
	
	private static final long serialVersionUID = 1;
	
	// Create a struct(Hashtable) that will contains all calculated Pi values for given number of steps 
	private Hashtable<Long,Double> calculatedPi = new Hashtable<Long,Double>();

	// Constructor
	public PiImpl(Hashtable<Long, Double> calculatedPi) throws RemoteException {
		this.calculatedPi = calculatedPi;
	}
	
	// Implementation code of the remote method "piCalculator", which calculates Pi value for specific number of steps
	public double piCalculator(long numSteps) throws RemoteException {
		
		double sum = 0.0;
		double pi = 0.0;
		
		// Check if Pi value is already calculated for this given number of steps
		if (calculatedPi.containsKey(numSteps)) {
			pi = calculatedPi.get(numSteps);
			// For debugging
			System.out.println("[Already Calculated] Estimated Pi with " + numSteps + " steps is: " + calculatedPi.get(numSteps));
		}
		else {
			double step = 1.0 / (double)numSteps;
	        /* do computation */
	        for (long i=0; i < numSteps; ++i) {
	            double x = ((double)i+0.5)*step;
	            sum += 4.0/(1.0+x*x);
	        }
	        pi = sum * step;
	        
	        // Add the Pi value for this specific number of steps to my struct
	        // HashMap is non-thread-safe struct, need lock(s) or synchronized at critical section(s)
	        PiServer.lock.lock(); // Lock the Critical Section
	        try {
	        	calculatedPi.put(numSteps, pi); // Put new item(Pi value) in struct
	        } finally {
	        	PiServer.lock.unlock(); // UnLock the Critical Section
	        }
	        
	     // For debugging
	     System.out.println("[New Calculation] Estimated Pi with " + numSteps + " steps is: " + pi);
		}
		
		// Some info for server side
		System.out.println("Thread is: "+ Thread. currentThread().getName()+" Pi => " + pi);
	    System.out.println("Already calculated Pi values: " + calculatedPi);
					
		// Return result
		return pi;
	}
	

}
