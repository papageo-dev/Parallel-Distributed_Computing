package myPiCalcArrayMain;

// Pi calculation, using array

public class PiCalcArrayMain {

	public static void main(String[] args) {
		
		int numSteps = 0;   // Problem size
		int numThreads = 0; // Total number of threads
	    
		// CMD input		
		if (args.length != 2) {
	    		System.out.println("Usage: java ThreadParArray <vector size> <number of threads>");
	    		System.exit(1);
		}

		try {
			numSteps = Integer.parseInt(args[0]);
	    		numThreads = Integer.parseInt(args[1]);
		}
		catch (NumberFormatException nfe) {
	   		System.out.println("Integer argument expected");
	   		System.exit(1);
		}
        if (numThreads == 0) numThreads = Runtime.getRuntime().availableProcessors();
  		
	    // Initalize array for threads' calculation
		double[] tsum = new double[numThreads];
		for(int i = 0; i < numThreads; i++)
             tsum[i] = 0.0;
  
		// Get current time
        long start = System.currentTimeMillis();

		// Create threads
        PiSumThread threads[] = new PiSumThread[numThreads];
		
		// Thread execution   
		for (int i = 0; i < numThreads; i++) 
		{
			threads[i] = new PiSumThread(i, numThreads, tsum, numSteps);
			threads[i].start();
		}

		// Wait for threads to terminate            
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
        		} catch (InterruptedException e) {}
		}

		// Initialize variable for total pi calculation
		double pi = 0.0;
		// Add threads' calcuation to total pi
		for (int i = 0; i < numThreads; i++) {
            //System.out.println(tsum[i]);
			pi = pi + tsum[i];
		}

		// Get current time and calculate elapsed time
        long elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("time in ms = "+ elapsedTimeMillis);

	    // Print results
	    System.out.printf("sequential program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (elapsedTimeMillis - start) / 1000);
	}
}
