package myPiCalcLocalSumGlobalLock;

public class PiSumThread extends Thread {
	
    private double mySum;
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
			// Add calculated value to local variable
        	mySum += (4.0/(1.0+x*x))*step;
    	}
    	
		// Lock Critical Section. Only one thread have access and can change values.
    	PiCalcLocalSumGlobalLock.lock.lock();
    	try {
    		// Critical Section
			// Add local thread's result to global total result
    		PiCalcLocalSumGlobalLock.sumPi += mySum;
            } finally {
            	// Unlock the Critical Section. One other thread can run code in Critical Section.
            	PiCalcLocalSumGlobalLock.lock.unlock();
            }
    }


}
