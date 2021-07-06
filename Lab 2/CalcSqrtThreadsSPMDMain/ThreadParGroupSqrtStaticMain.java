package pdc_lab_2_2021_1_2;

class ThreadParGroupSqrtStaticMain
{
	public static void main(String[] args)
	{
		int size = 0; // Problem size
	    int numThreads = 0; // Total number of threads
	        
	    // CMD input     
		if (args.length != 2) {
	    		System.out.println("Usage: java ThreadParSqrt <vector size> <number of threads>");
	    		System.exit(1);
		}

		try {
			size = Integer.parseInt(args[0]);
	    		numThreads = Integer.parseInt(args[1]);
		}
		catch (NumberFormatException nfe) {
	   		System.out.println("Integer argument expected");
	    		System.exit(1);
		}
        if (numThreads == 0) numThreads = Runtime.getRuntime().availableProcessors();
		
		
        // Create table and add (random) elements
		double[] a = new double[size];

		for(int i = 0; i < size; i++) {
            a[i] = i;
			//a[i] = Math.random()*size; 
		}
     
		// Get current time
        long start = System.currentTimeMillis();

		//  SPMD block partitioning
        int block = size / numThreads;
        int from = 0;
        int to = 0;
	       
		// Thread creation 
		SqrtGroupThreadStaticMain threads[] = new SqrtGroupThreadStaticMain[numThreads];
		
		// Thread execution   
		for(int i = 0; i < numThreads; i++) 
		{
			// Break the problem into pieces/blocks
			from = i * block;
			to = i * block + block;
			if (i == (numThreads - 1)) to = size;
			
			// Give a block to a thread to do its piece calculation
			threads[i] = new SqrtGroupThreadStaticMain(a, from, to); // Arguments: 1. Shared struct(array), 2. start calculation, 3. stop calculation
			threads[i].start();
		}

		// Wait for threads to terminate            
		for(int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
           		} catch (InterruptedException e) {}
		}

		// Get current time and calculate elapsed time
             	long elapsedTimeMillis = System.currentTimeMillis()-start;
             	System.out.println("time in ms = "+ elapsedTimeMillis);

	}
}