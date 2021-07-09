package myPiCalcLocalGetMain;

public class PiSumThread extends Thread {
	
    public double mySum;
    private int myId;
    private int myStart;
    private int myStop;
    private int numSteps;

    // Constructor
    public PiSumThread(int id, int numThreads, int numSteps) {
    	this.numSteps = numSteps;
    	mySum = 0.0;
        myId = id;
		
		// SPMD
        myStart = myId * (numSteps / numThreads);
        myStop = myStart + (numSteps / numThreads);
        if (myId == (numThreads - 1)) myStop = numSteps;
    }

    // Thread code
    public void run() {
    	
    	double step = 1.0 / (double)numSteps;
		
    	// Calculate a piece/block of total calculation
    	for(int i = myStart; i < myStop; i++) {
    		double x = ((double)i+0.5)*step;
			// Thread's calculation
            mySum += (4.0/(1.0+x*x))*step;
    	}
    }

    // Method to return thread's result
    public double getSum() {
    	return mySum;
    }

}
