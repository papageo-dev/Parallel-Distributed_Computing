package PiCalcRMI;

import java.rmi.*;
import java.rmi.server.*;

// This class implements the remote interface "Pi"
public class PiImpl extends UnicastRemoteObject implements Pi {
	
	private static final long serialVersionUID = 1;

	// Constructor
	public PiImpl() throws RemoteException {
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
        
		return pi;
	}

}
