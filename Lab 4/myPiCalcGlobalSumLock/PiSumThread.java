package myPiCalcGlobalSumLock;

public class PiSumThread extends Thread {
	
    private int myId;
	private int myStart;
    private int myStop;
    private int numSteps;

	// Constructor
	public PiSumThread(int id, int numThreads, int numSteps) {
		this.numSteps = numSteps;
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
			// Lock and run the code in Critical Section. Only one thread have access and can change values.
			PiCalcGlobalSumLock.lock.lock();
            try {
            	// Critical Section
				// Calculate and add result to total Pi(sumPi)
            	double x = ((double)i+0.5)*step;
                PiCalcGlobalSumLock.sumPi += (4.0/(1.0+x*x))*step;
            } finally {
            	// Unlock the Critical Section. One other thread can run code in Critical Section.
            	PiCalcGlobalSumLock.lock.unlock();
            }
        }
	}

}
