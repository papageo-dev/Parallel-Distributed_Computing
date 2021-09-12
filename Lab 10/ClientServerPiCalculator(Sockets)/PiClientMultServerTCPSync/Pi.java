package PiClientMultServerTCPSync;

public class Pi {
	
	// Variable to store Pi calculation result
	private static volatile double sumPi;
	
	// Constructor
	public Pi () {
		sumPi = 0.0;
	}

	// Function to calculate Pi value
	public synchronized void calculator(long numSteps) {
		
		// Initialize temporary local sum variable
		double tempSum = sumPi;
        
        double step = 1.0 / (double)numSteps;
        /* do computation */
        for (long i=0; i<numSteps; ++i) {
            double x = ((double)i+0.5)*step;
            tempSum += 4.0/(1.0+x*x);
        }
        sumPi = (tempSum*step);
        
        // Some info for server side
		System.out.println("Thread is: "+ Thread. currentThread().getName()+" Pi => " + sumPi);
	
    }

	// Function to return value of Pi calculation, as String
    public synchronized String print () {
        return String.valueOf(sumPi);
    }
    
    
}