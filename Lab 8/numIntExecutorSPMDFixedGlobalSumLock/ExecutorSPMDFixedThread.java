package numIntExecutorSPMDFixedGlobalSumLock;

class ExecutorSPMDFixedThread implements Runnable
{
	
	private int myid;
	private int numSteps;
	private int blockSize;

	// Constructor
	public ExecutorSPMDFixedThread(int i, int numSteps, int blockSize) {
		this.myid = i;
		this.numSteps = numSteps;
		this.blockSize = blockSize;
	}

	// Thread code
	public void run()
	{
	    int myfrom = 0;
		int myto = 0;
		
		// SPMD
		myfrom = myid * blockSize;
		myto = myfrom + blockSize;
		if (myto > numSteps) myto = numSteps;
		
		double step = 1.0 / (double)numSteps;

		// Calculate a piece/task of total calculation/work
		for(int i=myfrom; i<myto; i++) {
			// Lock. Only one thread per time can run the code in critical section
			MainPiExecutorGlobalSumLock.lock.lock();
            try {
            	// Critical Section
            	double x = ((double)i+0.5)*step;
            	MainPiExecutorGlobalSumLock.sumPi += (4.0/(1.0+x*x))*step;
            } finally {
            	// Unlock. Let other threads try to lock and run the code in critical section 
            	MainPiExecutorGlobalSumLock.lock.unlock();
            }
		}
			
	}
}