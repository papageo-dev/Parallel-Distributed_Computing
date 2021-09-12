package PiMasterWorkerTCPParallelCalcSumLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ThreadParallerCalcLock extends Thread {
	
    private int myId;
    private int myStart;
    private int myStop;
    private int myBlock;
    private int numSteps;
    private double mySum = 0.0;
    
    

    // constructor
    public ThreadParallerCalcLock(int id, int numThreads, int numSteps, int start, int stop, int block) {
    	this.numSteps = numSteps;
        myId = id;
        
        myBlock = block / numThreads;
        myStart = start + myId * myBlock;
        myStop = myStart + myBlock;
        if (myId == (numThreads - 1)) myStop = stop;
    }

    // thread code
    public void run() {
    	
    	double step = 1.0 / (double)numSteps;
    	for(int i = myStart; i < myStop; i++) {
    		// Lock Critical Section. Only one per time thread have access.
    		WorkerProtocol.lock.lock();
            try {
            	// Critical Section
            	double x = ((double)i+0.5)*step;
            	mySum += (4.0/(1.0+x*x))*step;
            } finally {
            	// Unlock Critical Section. Other threads try to access and change shared counter(mySum)
            	WorkerProtocol.lock.unlock();
            }
    	}
    	WorkerProtocol.pi += mySum;
    }

}
