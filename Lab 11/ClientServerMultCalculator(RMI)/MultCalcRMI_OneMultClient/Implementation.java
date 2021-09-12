package MultCalcRMI_OneMultClient;

import java.rmi.*;
import java.rmi.server.*;

// This class implements the remote interface methods
public class Implementation extends UnicastRemoteObject implements Interface {
	
	private static final long serialVersionUID = 1;

	// Constructor
	public Implementation() throws RemoteException {
	}
	
	
	// Implementation code of the remote method "piCalculator", which calculates Pi value for specific number of steps
	public double piCalculator(long numSteps) throws RemoteException {
		
		double sum = 0.0;
		
		double step = 1.0 / (double)numSteps;
        /* do computation */
        for (long i=0; i < numSteps; ++i) {
            double x = ((double)i+0.5)*step;
            sum += 4.0/(1.0+x*x);
        }
        double pi = sum * step;
        
        // Some info for server side
        System.out.println("Thread is: "+ Thread. currentThread().getName() 
        		+ "- NumSteps = " + numSteps + ", Pi = " + pi);
     	
        // Return result
		return pi;
	}
	
	
	// Implementation code of the remote method "cosCalculator", which calculates COS for a given number
	public double cosCalculator(int input) throws RemoteException {
		
		// Calculate COS
		double cos = Math.cos(input);
		
		// Some info for server side
        System.out.println("Thread is: "+ Thread. currentThread().getName() 
        		+ "- Input Num = " + input + ", COS = " + cos);
        
        // Return result
		return cos;
	}

	
	// Implementation code of the remote method "cosCalculator", which calculates SIN for a given number
	public double sinCalculator(int input) throws RemoteException {
		
		// Calculate SIN
		double sin = Math.sin(input);
		
		// Some info for server side
        System.out.println("Thread is: "+ Thread. currentThread().getName() 
        		+ "- Input Num = " + input + ", SIN = " + sin);
        
        // Return result
		return sin;
	}
	
	
}
