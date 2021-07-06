package pdc_lab_2_2021_1_1;


class SqrtGroupThread extends Thread
{
	private double [] table;
	private int myStart;
    private int myStop;

	// Constructor
	public SqrtGroupThread(int myId, int numThreads, double[] array, int size)
	{
		table = array;
		
		// Break the problem into pieces (SPMD)
		myStart = myId * (size / numThreads);
        myStop = myStart + (size / numThreads);
        if (myId == (numThreads - 1)) myStop = size;
	}

	// Thread code
	public void run()
	{
		// Each thread calculates a piece of problem, from "myStart" to "myStop"
		for(int i = myStart; i < myStop; i++) 
			// Calculate the square root of table[i]
	        table[i] = Math.sqrt(table[i]);
	}
}