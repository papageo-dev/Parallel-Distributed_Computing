package myPiCalcSumGlobalMon;

public class PiSumThread extends Thread {
	
	private int numSteps;
    private SumObj globalSum;
    private int myId;
	private int myStart;
    private int myStop;

	// Constructor
	public PiSumThread(int id, int numThreads, int numSteps, SumObj s) {
		this.numSteps = numSteps;
        globalSum = s;
        myId = id;
		
		// SPMD
        myStart = myId * (numSteps / numThreads);
        myStop = myStart + (numSteps / numThreads);
        if (myId == (numThreads - 1)) myStop = numSteps;
	}

	// Thread code
	public void run() {
		
		// Local result variable
	    double mySum = 0.0;
		
		double step = 1.0 / (double)numSteps;
		
		// Calculate a piece/block of total calculation
		for(int i = myStart; i < myStop; i++) {
			double x = ((double)i+0.5)*step;
			// Add calculated value to local variable
            mySum += 4.0/(1.0+x*x);
		}
		// Add local thread's result to global total result
		globalSum.add(mySum*step);
	}

}
