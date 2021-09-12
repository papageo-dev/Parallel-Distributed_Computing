package numIntExecutorSPMDSyncObj;

class ExecutorSPMDFixedThread implements Runnable
{
	
	private int myid;
	private int numSteps;
	private int blockSize;
	private SumObj pi;


	// Constructor
	public ExecutorSPMDFixedThread(int i, int numSteps, int blockSize, SumObj pi) {
		this.myid = i;
		this.numSteps = numSteps;
		this.blockSize = blockSize;
		this.pi = pi;	
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
		
	    double mySum = 0.0;
		
		double step = 1.0 / (double)numSteps;
		
		//Calculate a piece/task of total calculation/work
		for(int i=myfrom; i<myto; i++) {
			// Calculate local thread's result
			double x = ((double)i+0.5)*step;
            mySum += 4.0/(1.0+x*x);
		} 
		// Add local result to total result
		pi.add(mySum*step);
	}
	
}