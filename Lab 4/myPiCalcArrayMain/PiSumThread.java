package myPiCalcArrayMain;

public class PiSumThread extends Thread {
	
	private double [] sums;
    private int myId;
    private int myStart;
    private int myStop;
    private int numSteps;
   

    // Constructor
    public PiSumThread(int id, int numThreads, double[] ts, int numSteps) {
    	this.numSteps = numSteps;
    	sums = ts;
        myId = id;
		
		// SPMD
        myStart = myId * (numSteps / numThreads);
        myStop = myStart + (numSteps / numThreads);
        if (myId == (numThreads - 1)) myStop = numSteps;
    }

    // Thread code
    public void run() {
    	
		// Calculate a piece/block of total calculation
    	double step = 1.0 / (double)numSteps;
    	for(int i = myStart; i < myStop; i++) {
    		double x = ((double)i+0.5)*step;
    		// Add thread's sum to sums[], by Thread ID
            sums[myId] += (4.0/(1.0+x*x))*step;
    	}
    }
}
