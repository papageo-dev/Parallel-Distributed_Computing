package MultCalcRMI_OneMultClient_HashTables;

import java.rmi.*;
import java.rmi.server.*;
import java.util.Hashtable;

// This class implements the remote interface methods
public class Implementation extends UnicastRemoteObject implements Interface {
	
	private static final long serialVersionUID = 1;
	
	// Create a struct(Hashtable) that will contains all calculated Pi values for given number of steps 
	private Hashtable<Long,Double> calculatedPi = new Hashtable<Long,Double>();
	// Create a struct(Hashtable) that will contains all calculated Sin values for given number
	private Hashtable<Integer,Double> calculatedSin = new Hashtable<Integer,Double>();
	// Create a struct(Hashtable) that will contains all calculated Cos values for given number
	private Hashtable<Integer,Double> calculatedCos = new Hashtable<Integer,Double>();

	// Constructor
	public Implementation(Hashtable<Long, Double> calculatedPi, 
			Hashtable<Integer, Double> calculatedSin, 
			Hashtable<Integer, Double> calculatedCos) throws RemoteException {
				this.calculatedPi = calculatedPi;
				this.calculatedSin = calculatedSin;
				this.calculatedCos = calculatedCos;
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
	        // HashMap is thread-safe struct, needn't lock(s) or synchronized
	        calculatedPi.put(numSteps, pi);
	        
	     // For debugging
	     System.out.println("[New Calculation] Estimated Pi with " + numSteps + " steps is: " + pi);
		}
        
        // Some info for server side
        System.out.println("Thread is: "+ Thread. currentThread().getName() 
        		+ "- NumSteps = " + numSteps + ", Pi = " + pi);
        System.out.println("*** Already calculated Pi values: " + calculatedPi);
     	
        // Return result
		return pi;
	}
	
	
	// Implementation code of the remote method "cosCalculator", which calculates COS for a given number
	public double cosCalculator(int input) throws RemoteException {
		
		double cos = 0.0;
		
		// Check if COS value is already calculated for this given number
		if (calculatedCos.containsKey(input)) {
			cos = calculatedCos.get(input);
			// For debugging
			System.out.println("[Already Calculated] COS for " + input + " is: " + calculatedCos.get(input));
		}
		else {
			// Calculate COS
			cos = Math.cos(input);
			
			// Add the COS value for this specific number of steps to my struct
	        // HashMap is thread-safe struct, needn't lock(s) or synchronized
	        calculatedCos.put(input, cos);
	        
	         // For debugging
		     System.out.println("[New Calculation] COS for: " + input + " is: " + cos);
		}
		
		// Some info for server side
        System.out.println("Thread is: "+ Thread. currentThread().getName() 
        		+ "- Input Num = " + input + ", COS = " + cos);
        System.out.println("*** Already calculated COS values: " + calculatedCos);
        
        // Return result
		return cos;
	}

	
	// Implementation code of the remote method "sinCalculator", which calculates SIN for a given number
	public double sinCalculator(int input) throws RemoteException {
		
		double sin = 0.0;
		
		// Check if COS value is already calculated for this given number
		if (calculatedSin.containsKey(input)) {
			sin = calculatedSin.get(input);
			// For debugging
			System.out.println("[Already Calculated] SIN for " + input + " is: " + calculatedSin.get(input));
		}
		else {
			// Calculate COS
			sin = Math.sin(input);
			
			// Add the SIN value for this specific number of steps to my struct
	        // HashMap is thread-safe struct, needn't lock(s) or synchronized
	        calculatedSin.put(input, sin);
	        
	        // For debugging
		    System.out.println("[New Calculation] SIN for: " + input + " is: " + sin);
		}
		
		// Some info for server side
        System.out.println("Thread is: "+ Thread. currentThread().getName() 
        		+ "- Input Num = " + input + ", SIN = " + sin);
        System.out.println("*** Already calculated SIN values: " + calculatedSin);
        
        // Return result
		return sin;
	}
	
	
}
