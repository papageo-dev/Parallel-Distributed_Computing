package PiMasterWorkerTCPParallelCalcArray;

public class ThreadParallerCalc extends Thread {
	
	private double [] sums;
    private double mySum;
    private int myId;
    private int myStart;
    private int myStop;
    private int myBlock;
    private int numSteps;

    // constructor
    public ThreadParallerCalc(int id, int numThreads, double[] ts, int numSteps, int start, int stop, int block) {
    	this.numSteps = numSteps;
    	sums = ts;
        mySum = 0.0;
        myId = id;
        
        myBlock = block / numThreads;
        myStart = start + myId * myBlock;
        myStop = myStart + myBlock;
        if (myId == (numThreads - 1)) myStop = stop;
    }

    // thread code
    public void run() {
    	
    	// Calculate each worker's result
    	double step = 1.0 / (double)numSteps;
    	for(int i = myStart; i < myStop; i++) {
    		double x = ((double)i+0.5)*step;
            mySum += 4.0/(1.0+x*x);   
    	}
    	// Add worker's local sum to sums[], by thread ID
    	sums[myId] = (mySum*step);  
    }

}
