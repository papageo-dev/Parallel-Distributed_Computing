package pdc_lab_2_2021_1_1;


class ThreadParGroupSqrt
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
		
		
        // Create array and add (random) elements
		double[] a = new double[size];

		for (int i = 0; i < size; i++) {
			a[i] = i;
			//a[i] = Math.random()*size; 
		}
     
		// Get current time
        long start = System.currentTimeMillis();

		// Create threads
		SqrtGroupThread threads[] = new SqrtGroupThread[numThreads];
		
		// Thread execution   
		for(int i = 0; i < numThreads; i++) 
		{
			threads[i] = new SqrtGroupThread(i, numThreads, a, size);
			// Arguments: 1. thread's ID, 2. Total threads' number, 3. Shared struct(array), 4. Problem's size
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