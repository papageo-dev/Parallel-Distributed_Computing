package numIntExecutorSPMDLocalArraySum;

class ExecutorSPMDFixedThread implements Runnable
{
	
	private int myid;
	private int numSteps;
	private int blockSize;
	private double [] sums;
    private double mySum;

	// Constructor
	public ExecutorSPMDFixedThread(int i, int numSteps, int blockSize, double[] tsum) {
		this.myid = i;
		this.numSteps = numSteps;
		this.blockSize = blockSize;
		this.sums = tsum;
		mySum = 0.0;
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
		
		//Calculate a piece/task of total calculation/work
		for(int i=myfrom; i<myto; i++) {
			//Calculate and store to local variable "mySum"
			double x = ((double)i+0.5)*step;
            mySum += 4.0/(1.0+x*x); 
		}
		//Add mySum to sums[], by Thread ID
    	sums[myid] = mySum*step;  
	}
	
}