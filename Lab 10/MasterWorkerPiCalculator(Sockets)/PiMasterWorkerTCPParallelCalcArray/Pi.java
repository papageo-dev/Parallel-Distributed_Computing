package PiMasterWorkerTCPParallelCalcArray;

public class Pi {
	
	private static long numSteps;
	private static double resultPi;
	
	// Constructor
	public Pi (int numSteps) {
		Pi.numSteps = numSteps;
		resultPi = 0.0;
	}

	// Method to add each worker's sum to total Pi result
	public synchronized void add(double localSum) {
		resultPi += localSum;
	}

	// Method to print total Pi result
    public void printResult () {
	    System.out.println("Result = " + resultPi);
    }
        
    // Method to return number of steps
    public String printInit () {
	    return String.valueOf(numSteps);
    }
        
}